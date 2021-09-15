package br.com.alura.ligacaoComBD.testeConexao.um;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteListagem {
    public static void main(String args[]) throws SQLException {
        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection conexao = criaConexao.getConexao();

        //os comandos sql aqui no java são statements
        //logo precisamos criar um statement
        Statement stm = conexao.createStatement();

        //então através deles executamos nosso script sql
        stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO;");
        //porém este método retorna um boolean
        //true para quando é uma lista (caso do select)
        //false para quando não é uma lista (casos de insert, delete, update)

        //então como fazemos para pegar o resultado da nossa lista já que aquele
        //método retorna um boolean?

        //criamos um ResultSet e atravé do statement pegamos o getResultSet
        ResultSet rst = stm.getResultSet();

        //o resultSet tem o método next() onde fazemos uma iteração para pegar linha a linha do select
        while (rst.next()) {
            Integer id;
            String nome;
            String descricao;

            //pegamos campo por campo ou através do index ou do label
            id = rst.getInt("ID");
            nome = rst.getString("NOME");
            descricao = rst.getString("DESCRICAO");

            System.out.println("id: " + id + " / nome: " + nome + " / descricao: " + descricao);
        }

        conexao.close();
    }
}
