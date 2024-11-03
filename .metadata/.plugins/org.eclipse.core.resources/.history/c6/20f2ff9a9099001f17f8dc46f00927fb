package connection;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class Conexao {

    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private Connection conn;

    public Conexao() throws SQLException {
        //DriverManager
        OracleDataSource ods = new OracleDataSource();

        //configurando a url
        ods.setURL(url);

        //configurando o usuário
        ods.setUser(Credenciais.user);

        //configurando a senha
        ods.setPassword(Credenciais.pwd);

        //obtendo a conexão
        conn = ods.getConnection();
        System.out.println("Conexão ok!");
    }

    // Método getter para obter a conexão
    public Connection getConn() {
        return this.conn;
    }
}
