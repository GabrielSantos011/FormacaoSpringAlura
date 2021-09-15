package br.com.alura.ligacaoComBD.testeConexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteRemocao {
    public static void main(String args[]) throws SQLException {
        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection conexao = criaConexao.getConexao();

        Statement stm = conexao.createStatement();
        stm.execute("DELETE FROM PRODUTO WHERE ID > 1");

        //para saber quantas linhas foram modificadas
        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("linhas modificadas: " + linhasModificadas);

        conexao.close();
    }
}
