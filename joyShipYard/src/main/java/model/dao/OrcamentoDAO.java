package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import connection.ConexaoFactory;
import model.vo.OrcamentoVO;

public class OrcamentoDAO {
	private Connection conexao;

    public OrcamentoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFactory().conexaoBD();
    }

    // Método para cadastrar orçamento no banco de dados
    public int cadastrarOrcamento(OrcamentoVO orcamento) {
        int idOrcamentoGerado = -1;

        try {

            String sql = "INSERT INTO TB_JSY_ORCAMENTO (qtd_peca, valor_total, data_orcamento) "
                       + "VALUES (?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, orcamento.getQtdPeca());
            stmt.setFloat(2, orcamento.getValorTotal());
            stmt.setTimestamp(3, new Timestamp(orcamento.getDataOrcamento().getTime()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idOrcamentoGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar orçamento: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idOrcamentoGerado;
    }

    // Método para consultar orçamento por ID
    public OrcamentoVO consultarOrcamentoPorId(int idOrcamento) {
        OrcamentoVO orcamento = null;

        try {

            String sql = "SELECT id_orcamento, qtd_peca, valor_total, data_orcamento "
                       + "FROM TB_JSY_ORCAMENTO WHERE id_orcamento = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idOrcamento);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Instancia o objeto utilizando o construtor
                orcamento = new OrcamentoVO(
                    rs.getInt("id_orcamento"),
                    rs.getInt("qtd_peca"),
                    rs.getFloat("valor_total"),
                    rs.getTimestamp("data_orcamento")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar orçamento: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return orcamento;
    }

    // Método para atualizar orçamento
    public void atualizarOrcamento(OrcamentoVO orcamento) {

        try {

            String sql = "UPDATE TB_JSY_ORCAMENTO SET qtd_peca = ?, valor_total = ?, data_orcamento = ? "
                       + "WHERE id_orcamento = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, orcamento.getQtdPeca());
            stmt.setFloat(2, orcamento.getValorTotal());
            stmt.setTimestamp(3, new Timestamp(orcamento.getDataOrcamento().getTime()));
            stmt.setInt(4, orcamento.getIdOrcamento());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar orçamento: " + e.getMessage(), e);
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

    // Método para excluir orçamento por ID
    public void excluirOrcamento(int idOrcamento) {

        try {

            String sql = "DELETE FROM TB_JSY_ORCAMENTO WHERE id_orcamento = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idOrcamento);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir orçamento: " + e.getMessage(), e);
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
