package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoFactory;
import model.vo.AutomovelVO;

public class AutomovelDAO {
    public Connection conexao;

    public AutomovelDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexaoBD();
    }

    // Método para cadastrar automóvel no banco de dados
    public int cadastrarAutomovel(AutomovelVO automovel) {
        int idAutomovelGerado = -1;

        try {
            String sql = "INSERT INTO TB_JSY_AUTOMOVEL (cor, crvl, modelo, fabricante, placa, assegurado, quilometragem, ano_circulacao, ano_fabricacao) "
                       + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, automovel.getCor());
            stmt.setInt(2, automovel.getCrvl());
            stmt.setString(3, automovel.getModelo());
            stmt.setString(4, automovel.getFabricante());
            stmt.setString(5, automovel.getPlaca());
            stmt.setBoolean(6, automovel.isAssegurado());
            stmt.setInt(7, automovel.getQuilometragem());
            stmt.setDate(8, new Date(automovel.getAnoCirculacao().getTime()));
            stmt.setDate(9, new Date(automovel.getAnoFabricacao().getTime()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idAutomovelGerado = rs.getInt(1); // O ID gerado é o primeiro valor retornado
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar automóvel: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idAutomovelGerado;
    }

    // Método para consultar um automóvel por ID
    public AutomovelVO consultarAutomovelPorId(int idAutomovel) {
        AutomovelVO automovel = null;

        try {

            String sql = "SELECT id_automovel, cor, crvl, modelo, fabricante, placa, assegurado, quilometragem, ano_circulacao, ano_fabricacao "
                       + "FROM TB_JSY_AUTOMOVEL WHERE id_automovel = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAutomovel);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                automovel = new AutomovelVO(
                    rs.getInt("id_automovel"),
                    rs.getString("cor"),
                    rs.getInt("crvl"),
                    rs.getString("modelo"),
                    rs.getString("fabricante"),
                    rs.getString("placa"),
                    rs.getBoolean("assegurado"),
                    rs.getInt("quilometragem"),
                    rs.getDate("ano_fabricacao"),
                    rs.getDate("ano_circulacao")
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar automóvel: " + e.getMessage(), e);
        } finally {
            try {
                if (this.conexao != null) {
                	this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return automovel;
    }

    // Método para atualizar um automóvel no banco de dados
    public void atualizarAutomovel(AutomovelVO automovel) {

        try {

            String sql = "UPDATE TB_JSY_AUTOMOVEL SET cor = ?, crvl = ?, modelo = ?, fabricante = ?, placa = ?, assegurado = ?, quilometragem = ?, ano_circulacao = ?, ano_fabricacao = ? "
                       + "WHERE id_automovel = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, automovel.getCor());
            stmt.setInt(2, automovel.getCrvl());
            stmt.setString(3, automovel.getModelo());
            stmt.setString(4, automovel.getFabricante());
            stmt.setString(5, automovel.getPlaca());
            stmt.setBoolean(6, automovel.isAssegurado());
            stmt.setInt(7, automovel.getQuilometragem());
            stmt.setDate(8, new Date(automovel.getAnoCirculacao().getTime()));
            stmt.setDate(9, new Date(automovel.getAnoFabricacao().getTime()));
            stmt.setInt(10, automovel.getIdAutomovel());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar automóvel: " + e.getMessage(), e);
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

    // Método para excluir um automóvel por ID
    public void excluirAutomovel(int idAutomovel) {

        try {

            String sql = "DELETE FROM TB_JSY_AUTOMOVEL WHERE id_automovel = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAutomovel);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir automóvel: " + e.getMessage(), e);
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
