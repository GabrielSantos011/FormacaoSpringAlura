package br.com.alura.ligacaoComBD.testeConexao.tres;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactoryComDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteListagemComPreparedStatement {
    public static void main(String args[]) throws SQLException {
        ConnectionFactoryComDataSource criaConexao = new ConnectionFactoryComDataSource();
        Connection conexao = criaConexao.getConexao();

        PreparedStatement stm = conexao.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO;");

        stm.execute();

        ResultSet rst = stm.getResultSet();


        while (rst.next()) {
            Integer id;
            String nome;
            String descricao;

            id = rst.getInt("ID");
            nome = rst.getString("NOME");
            descricao = rst.getString("DESCRICAO");

            System.out.println("id: " + id + " / nome: " + nome + " / descricao: " + descricao);
        }

        conexao.close();
    }
}
