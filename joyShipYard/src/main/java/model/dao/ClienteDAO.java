package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoFactory;
import model.vo.ClienteVO;
import java.sql.Date;

public class ClienteDAO {
	
	 public Connection conexao;

	    public ClienteDAO() throws ClassNotFoundException, SQLException {
	        super();
	        this.conexao = new ConexaoFactory().conexaoBD();
	    }
	    
    // Método para cadastrar um cliente no banco de dados
	public int cadastrarCliente(ClienteVO cliente) {
	    int idClienteGerado = -1; // Inicialmente, -1 significa que o ID não foi gerado

	    try {

	        String sql = "INSERT INTO TB_JSY_CLIENTE (nome_cliente, sobrenome_cliente, data_nascimento, sexo, cep, endereco, cpf, cnh, celular, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement stmt = this.conexao.prepareStatement(sql);
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getSobrenome());
	        stmt.setDate(3, cliente.getDataNascimento()); // certifique-se de que a data esteja no formato correto
	        stmt.setString(4, cliente.getSexo());
	        stmt.setInt(5, cliente.getCep());
	        stmt.setString(6, cliente.getEndereco());

	        // Garantir que CPF, CNH e Celular são números, caso contrário, usar stmt.setString()
	        stmt.setLong(7, cliente.getCpf());
	        stmt.setLong(8, cliente.getCnh());
	        stmt.setLong(9, cliente.getCelular());

	        stmt.setString(10, cliente.getEmail());
	        stmt.setString(11, cliente.getSenha());

	        stmt.executeUpdate();


	        // Obtendo o ID gerado
	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            idClienteGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
	        }

	        rs.close();
	        stmt.close();

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
	    } finally {
	        try {
	            if (this.conexao != null) {
	                this.conexao.close();
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
	        }
	    }

	    return idClienteGerado;
	}

    
    // Método para consultar um cliente por email e senha
    public ClienteVO consultarClientePorEmail(String email, String senha) {
        ClienteVO cliente = null;

        try {

            String sql = "SELECT id_cliente, nome_cliente, sobrenome_cliente, cpf, sexo, cep, endereco, cnh, celular, email, senha, data_nascimento "
                       + "FROM TB_JSY_CLIENTE WHERE email = ? AND senha = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new ClienteVO(
                    rs.getInt("id_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("sobrenome_cliente"),
                    rs.getDate("data_nascimento"),
                    rs.getString("sexo"),
                    rs.getInt("cep"),
                    rs.getString("endereco"),
                    rs.getLong("cpf"),
                    rs.getLong("cnh"),
                    rs.getLong("celular"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cliente por email e senha: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return cliente;
    }

    // Método para consultar um cliente por ID
    public ClienteVO consultarClientePorId(int idCliente) {
        ClienteVO cliente = null;

        try { 

            String sql = "SELECT id_cliente, nome_cliente, sobrenome_cliente, cpf, sexo, cep, endereco, cnh, celular, email, senha, data_nascimento "
                       + "FROM TB_JSY_CLIENTE WHERE id_cliente = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new ClienteVO(
                    rs.getInt("id_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("sobrenome_cliente"),
                    rs.getDate("data_nascimento"), // Usando java.sql.Date
                    rs.getString("sexo"),
                    rs.getInt("cep"),
                    rs.getString("endereco"),
                    rs.getLong("cpf"),
                    rs.getLong("cnh"),
                    rs.getLong("celular"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cliente: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return cliente;
    }

    // Método para atualizar um cliente no banco de dados
    public void atualizarCliente(ClienteVO cliente) {

        try {

            String sql = "UPDATE TB_JSY_CLIENTE SET nome_cliente = ?, sobrenome_cliente = ?, data_nascimento = ?, cpf = ?, sexo = ?, cep = ?, endereco = ?, cnh = ?, celular = ?, email = ?, senha = ? "
                       + "WHERE id_cliente = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setDate(3, new Date(cliente.getDataNascimento().getTime())); // Usando java.sql.Date diretamente
            stmt.setLong(4, cliente.getCpf());
            stmt.setString(5, cliente.getSexo());
            stmt.setInt(6, cliente.getCep());
            stmt.setString(7, cliente.getEndereco());
            stmt.setLong(8, cliente.getCnh());
            stmt.setLong(9, cliente.getCelular());
            stmt.setString(10, cliente.getEmail());
            stmt.setString(11, cliente.getSenha());
            stmt.setInt(12, cliente.getId());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }

    // Método para excluir um cliente por ID
    public void excluirCliente(int idCliente) {

        try {


            String sql = "DELETE FROM TB_JSY_CLIENTE WHERE id_cliente = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }
}
