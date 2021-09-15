package br.com.alura.ligacaoComBD.testeConexao.dois;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteRemocaoComPreparedStatement {
    public static void main(String args[]) throws SQLException {
        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection conexao = criaConexao.getConexao();

        PreparedStatement stm = conexao.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");

        stm.setInt(1,1);

        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("linhas modificadas: " + linhasModificadas);

        conexao.close();
    }
}
