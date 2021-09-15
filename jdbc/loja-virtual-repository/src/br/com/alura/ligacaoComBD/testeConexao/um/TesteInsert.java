package br.com.alura.ligacaoComBD.testeConexao.um;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsert {
    public static void main(String args[]) throws SQLException {
        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection conexao = criaConexao.getConexao();

        Statement stm = conexao.createStatement();
        stm.execute("INSERT INTO produto (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
                Statement.RETURN_GENERATED_KEYS);//para retornar o id gerado

        //se atentar para o método chamado aqui
        ResultSet rts = stm.getGeneratedKeys();

        while (rts.next()) {
            Integer id;

            id = rts.getInt(1);//exemplo com o index da coluna

            System.out.println("O id gerado foi o " + id);
        }

        conexao.close();
    }
}
