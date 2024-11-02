package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import model.vo.PecaVO;
import model.vo.ServicoVO;

public class ServicoDAO {
	public int cadastrarServico(ServicoVO servico) {
		Connection conexao = null;
		int idServicoGerado = -1;
		
		try {
            Conexao conexaoDB = new Conexao();
            conexao = conexaoDB.getConn();

            String sql = "INSERT INTO TB_JSY_SERVICO (desc_servico, preco_mao_obra) "
                       + "VALUES (?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, servico.getDescServico());
            stmt.setDouble(2, servico.getPrecoMaoObra());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
            	idServicoGerado = rs.getInt(1); 

            rs.close();
            stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao o serviço peça: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }

        return idServicoGerado;
    }
	
	 public ServicoVO consultarPeca(int idServico) {
	        Connection conexao = null;
	        ServicoVO servico = null;

	        try {
	            Conexao conexaoDB = new Conexao();
	            conexao = conexaoDB.getConn();

	            String sql = "SELECT * "
	                       + "FROM TB_JSY_SERVICO WHERE id_servico = ?";

	            PreparedStatement stmt = conexao.prepareStatement(sql);
	            stmt.setInt(1, idServico);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	            	servico = new ServicoVO(
	                    rs.getInt("id_servico"),
	                    rs.getString("desc_servico"),
	                    rs.getDouble("preco_mao_obra")
	                );
	            }

	            rs.close();
	            stmt.close();

	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao consultar serviço: " + e.getMessage(), e);
	        } finally {
	            try {
	                if (conexao != null) {
	                    conexao.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
	            }
	        }

	        return servico;
	    }

}
