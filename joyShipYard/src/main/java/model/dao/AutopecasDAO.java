package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoFactory;
import model.vo.AutopecasVO;

public class AutopecasDAO {

    private Connection conexao;

    public AutopecasDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFactory().conexaoBD();
    }

    // Método para cadastrar Autopecas
    public int cadastrarAutopecas(AutopecasVO autopeca) {
        int idAutopecaGerado = -1;

        try {
            String sql = "INSERT INTO TB_JSY_AUTOPECAS (nome_fantasia, cnpj, endereco, email, telefone, senha, frete) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, autopeca.getNomeFantasia());
            stmt.setInt(2, autopeca.getCnpj());
            stmt.setString(3, autopeca.getEndereco());
            stmt.setString(4, autopeca.getEmail());
            stmt.setInt(5, autopeca.getTelefone());
            stmt.setString(6, autopeca.getSenha());
            stmt.setFloat(7, autopeca.getFrete());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idAutopecaGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar autopeças: " + e.getMessage(), e);
        } finally {
        	try {
	            if (this.conexao != null) {
	                this.conexao.close();
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
	        }
        }

        return idAutopecaGerado;
    }

    // Método para consultar Autopecas por ID
    public AutopecasVO consultarAutopecasPorId(int idAutopeca) {
        AutopecasVO autopeca = null;

        try {
            String sql = "SELECT id_autopeca, nome_fantasia, cnpj, endereco, email, telefone, senha, frete "
                       + "FROM TB_JSY_AUTOPECAS WHERE id_autopeca = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAutopeca);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                autopeca = new AutopecasVO(
                    rs.getInt("id_autopeca"),
                    rs.getString("nome_fantasia"),
                    rs.getInt("cnpj"),
                    rs.getString("endereco"),
                    rs.getInt("telefone"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getFloat("frete")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar autopeças: " + e.getMessage(), e);
        } finally {
        	try {
	            if (this.conexao != null) {
	                this.conexao.close();
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
	        }
        }

        return autopeca;
    }

    // Método para atualizar Autopecas
    public void atualizarAutopecas(AutopecasVO autopeca) {
        try {
            String sql = "UPDATE TB_JSY_AUTOPECAS SET nome_fantasia = ?, cnpj = ?, endereco = ?, email = ?, telefone = ?, senha = ?, frete = ? "
                       + "WHERE id_autopeca = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autopeca.getNomeFantasia());
            stmt.setInt(2, autopeca.getCnpj());
            stmt.setString(3, autopeca.getEndereco());
            stmt.setString(4, autopeca.getEmail());
            stmt.setInt(5, autopeca.getTelefone());
            stmt.setString(6, autopeca.getSenha());
            stmt.setFloat(7, autopeca.getFrete());
            stmt.setInt(8, autopeca.getIdAutopeca());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar autopeças: " + e.getMessage(), e);
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

    // Método para excluir Autopecas
    public void excluirAutopecas(int idAutopeca) {
        try {
            String sql = "DELETE FROM TB_JSY_AUTOPECAS WHERE id_autopeca = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAutopeca);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir autopeças: " + e.getMessage(), e);
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

    // Método para enviar o valor da peça (exemplo)
    public float enviarValorPeca(float valorPeca) {
        return valorPeca;
    }
}
