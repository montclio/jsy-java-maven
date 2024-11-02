package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import connection.Conexao;
import model.vo.AvariacaoVO;

public class AvariacaoDAO {

    // Método para cadastrar uma avariação no banco de dados
    public int cadastrarAvariacao(AvariacaoVO avariacao) {
        Connection conexao = null;
        int idAvariacaoGerado = -1;

        try {
            Conexao conexaoDB = new Conexao();
            conexao = conexaoDB.getConn();

            String sql = "INSERT INTO TB_JSY_AVARIACAO (peca_danificada, tipo_avariacao, qtd_peca, data_avariacao) "
                       + "VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, avariacao.getPecaDanificada());
            stmt.setString(2, avariacao.getTipoAvariacao());
            stmt.setInt(3, avariacao.getQtdPeca());
            stmt.setTimestamp(4, new Timestamp(avariacao.getDataAvariacao().getTime()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idAvariacaoGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar avariação: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idAvariacaoGerado;
    }

    // Método para consultar uma avariação por ID
    public AvariacaoVO consultarAvariacao(int idAvariacao) {
        Connection conexao = null;
        AvariacaoVO avariacao = null;

        try {
            Conexao conexaoDB = new Conexao();
            conexao = conexaoDB.getConn();

            String sql = "SELECT id_avariacao, peca_danificada, tipo_avariacao, qtd_peca, data_avariacao "
                       + "FROM TB_JSY_AVARIACAO WHERE id_avariacao = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAvariacao);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                avariacao = new AvariacaoVO(
                    rs.getInt("id_avariacao"),
                    rs.getString("peca_danificada"),
                    rs.getString("tipo_avariacao"),
                    rs.getInt("qtd_peca"),
                    rs.getTimestamp("data_avariacao")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar avariação: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return avariacao;
    }

    // Método para atualizar uma avariação
    public void atualizarAvariacao(AvariacaoVO avariacao) {
        Connection conexao = null;

        try {
            Conexao conexaoDB = new Conexao();
            conexao = conexaoDB.getConn();

            String sql = "UPDATE TB_JSY_AVARIACAO SET peca_danificada = ?, tipo_avariacao = ?, qtd_peca = ?, data_avariacao = ? "
                       + "WHERE id_avariacao = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, avariacao.getPecaDanificada());
            stmt.setString(2, avariacao.getTipoAvariacao());
            stmt.setInt(3, avariacao.getQtdPeca());
            stmt.setTimestamp(4, new Timestamp(avariacao.getDataAvariacao().getTime()));
            stmt.setInt(5, avariacao.getIdAvariacao());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar avariação: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }

    // Método para excluir uma avariação por ID
    public void excluirAvariacao(int idAvariacao) {
        Connection conexao = null;

        try {
            Conexao conexaoDB = new Conexao();
            conexao = conexaoDB.getConn();

            String sql = "DELETE FROM TB_JSY_AVARIACAO WHERE id_avariacao = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAvariacao);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avariação: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }
}
