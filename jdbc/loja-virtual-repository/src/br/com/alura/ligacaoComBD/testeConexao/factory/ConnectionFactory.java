package br.com.alura.ligacaoComBD.testeConexao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConexao() throws SQLException {
        Connection conexao = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/loja_virtual?userTimezone=true&serverTimezone=UTC",
                        "root", "123456");

        return conexao;
    }
}
