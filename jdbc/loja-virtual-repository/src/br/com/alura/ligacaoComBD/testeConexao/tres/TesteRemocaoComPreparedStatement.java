package br.com.alura.ligacaoComBD.testeConexao.tres;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactoryComDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteRemocaoComPreparedStatement {
    public static void main(String args[]) throws SQLException {
        ConnectionFactoryComDataSource criaConexao = new ConnectionFactoryComDataSource();
        Connection conexao = criaConexao.getConexao();

        PreparedStatement stm = conexao.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");

        stm.setInt(1,1);

        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("linhas modificadas: " + linhasModificadas);

        conexao.close();
    }
}
