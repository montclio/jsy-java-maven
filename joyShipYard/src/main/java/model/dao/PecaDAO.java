package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoFactory;
import model.vo.PecaVO;

public class PecaDAO {
	private Connection conexao;

    public PecaDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFactory().conexaoBD();
    }


    // Método para cadastrar uma peça no banco de dados
    public int cadastrarPeca(PecaVO peca) {
        int idPecaGerado = -1;

        try {


            String sql = "INSERT INTO TB_JSY_PECA (tipo_peca, nome_peca, fabricante_peca, modelo_peca) "
                       + "VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setString(3, peca.getFabricantePeca());
            stmt.setString(4, peca.getModelo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idPecaGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar peça: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idPecaGerado;
    }

    // Método para consultar uma peça por ID
    public PecaVO consultarPeca(int idPeca) {
        PecaVO peca = null;

        try {

            String sql = "SELECT id_peca, tipo_peca, nome_peca, fabricante_peca, modelo_peca "
                       + "FROM TB_JSY_PECA WHERE id_peca = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idPeca);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Utiliza o construtor para criar o objeto PecaVO com os dados retornados do banco
                peca = new PecaVO(
                    rs.getInt("id_peca"),
                    rs.getString("nome_peca"),
                    null,  // Considerando que o campo precoPeca não faz parte da tabela
                    rs.getString("tipo_peca"),
                    rs.getString("modelo_peca"),
                    rs.getString("fabricante_peca")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar peça: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return peca;
    }

    // Método para atualizar uma peça
    public void atualizarPeca(PecaVO peca) {

        try {

            String sql = "UPDATE TB_JSY_PECA SET tipo_peca = ?, nome_peca = ?, fabricante_peca = ?, modelo_peca = ? "
                       + "WHERE id_peca = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setString(3, peca.getFabricantePeca());
            stmt.setString(4, peca.getModelo());
            stmt.setInt(5, peca.getIdPeca());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar peça: " + e.getMessage(), e);
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

    // Método para excluir uma peça por ID
    public void excluirPeca(int idPeca) {

        try {

            String sql = "DELETE FROM TB_JSY_PECA WHERE id_peca = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idPeca);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir peça: " + e.getMessage(), e);
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
