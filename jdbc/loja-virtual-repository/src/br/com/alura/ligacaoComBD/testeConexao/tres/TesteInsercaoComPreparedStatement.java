package br.com.alura.ligacaoComBD.testeConexao.tres;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactoryComDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteInsercaoComPreparedStatement {
    //para evitar sql injection ou simplesmente um erro do usuário
    //devemos usar o PreparedStatement que, por exemplo, ao adicionarmos um '
    //não ira quebrar nosso códico e tratará como string
    public static void main(String args[]) throws SQLException {
        ConnectionFactoryComDataSource criaConexao = new ConnectionFactoryComDataSource();
        Connection conexao = criaConexao.getConexao();
        //tirando a responsabilidade do commit do jdbc
        conexao.setAutoCommit(false);

        Scanner scan = new Scanner(System.in);
        System.out.println("digite o nome do produto:");
        String nome = scan.nextLine();
        System.out.println("digite a descrição do produto:");
        String descricao = scan.nextLine();

        try {
            //passamos a query aqui agora e colocamos ? onde serão variávei que entrarão
            PreparedStatement stm =
                    conexao.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);

            TesteInsercaoComPreparedStatement.adiciona(nome, descricao, stm);

            //commitando pois tudi deu certo
            conexao.commit();

            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("efetuando rollback");
            conexao.rollback();
        }

    }

    private static void adiciona(String nome, String descricao,PreparedStatement stm) throws SQLException {
        //setando as variaveis no ?
        stm.setString(1,nome);
        stm.setString(2,descricao);

        //execute agora vazio
        stm.execute();

        //se atentar para o método chamado aqui
        ResultSet rts = stm.getGeneratedKeys();

        while (rts.next()) {
            Integer id;

            id = rts.getInt(1);

            System.out.println("O id gerado foi o " + id);
        }
    }
}
