package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoFactory;
import model.vo.OficinaVO;

public class OficinaDAO {
	private Connection conexao;

	public OficinaDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexaoBD();
    }
    // Método para cadastrar oficina no banco de dados
    public int cadastrarOficina(OficinaVO oficina) {
        int idOficinaGerado = -1;

        try {

            String sql = "INSERT INTO TB_JSY_OFICINA (nome_fantasia, cnpj, endereco, email, telefone, senha, preco_mao_obra) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, oficina.getNomeFantasia());
            stmt.setInt(2, oficina.getCnpj()); 
            stmt.setString(3, oficina.getEndereco());
            stmt.setString(4, oficina.getEmail());
            stmt.setInt(5, oficina.getTelefone());
            stmt.setString(6, oficina.getSenha());
            stmt.setFloat(7, oficina.getPrecoMaoObra());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idOficinaGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar oficina: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idOficinaGerado;
    }

    // Método para consultar oficina por ID
    public OficinaVO consultarOficinaPorId(int idOficina) {
        OficinaVO oficina = null;

        try {

            String sql = "SELECT id_oficina, nome_fantasia, cnpj, endereco, email, telefone, senha, preco_mao_obra "
                       + "FROM TB_JSY_OFICINA WHERE id_oficina = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idOficina);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Instancia o objeto usando o construtor
                oficina = new OficinaVO(
                    rs.getInt("id_oficina"),
                    rs.getString("nome_fantasia"),
                    rs.getInt("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("cnpj"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getFloat("preco_mao_obra")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar oficina: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return oficina;
    }

    // Método para atualizar oficina no banco de dados
    public void atualizarOficina(OficinaVO oficina) {

        try {

            String sql = "UPDATE TB_JSY_OFICINA SET nome_fantasia = ?, cnpj = ?, endereco = ?, email = ?, telefone = ?, senha = ?, preco_mao_obra = ? "
                       + "WHERE id_oficina = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, oficina.getNomeFantasia());
            stmt.setInt(2, oficina.getCnpj());
            stmt.setString(3, oficina.getEndereco());
            stmt.setString(4, oficina.getEmail());
            stmt.setInt(5, oficina.getTelefone());
            stmt.setString(6, oficina.getSenha());
            stmt.setFloat(7, oficina.getPrecoMaoObra());
            stmt.setInt(8, oficina.getIdOficina());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar oficina: " + e.getMessage(), e);
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

    // Método para excluir oficina por ID
    public void excluirOficina(int idOficina) {

        try {

            String sql = "DELETE FROM TB_JSY_OFICINA WHERE id_oficina = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idOficina);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir oficina: " + e.getMessage(), e);
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
