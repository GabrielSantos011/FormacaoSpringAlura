package br.com.alura.ligacaoComBD.testeConexao;
//atentar para os imports
//java.sql.*
//são do jdbc
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String args[]) throws SQLException {
        //abrindo uma conexao com o banco de dados
        //para dar certo precisamos adicionar em libraries nosso jar
        //que está na pasta lib
        Connection conexao = DriverManager
                .getConnection("jdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC",
                        "root", "123456");
        //fechando conexão
        conexao.close();
    }
}
