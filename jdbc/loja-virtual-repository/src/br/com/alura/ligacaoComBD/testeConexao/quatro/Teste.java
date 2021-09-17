package br.com.alura.ligacaoComBD.testeConexao.quatro;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactoryComDataSource;
import br.com.alura.ligacaoComBD.testeConexao.quatro.dao.ProdutoDAO;
import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teste {

    public static void main(String args[]) throws SQLException {
        ConnectionFactoryComDataSource criaConexao = new ConnectionFactoryComDataSource();
        try (Connection conexao = criaConexao.getConexao()) {
            Scanner scan = new Scanner(System.in);
            Produto produto = new Produto();
            List<Produto> produtos;
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            //testando inserção
            System.out.println("Vamos inserir um Produto!!!");
            System.out.println();

            System.out.print("Insira o nome: ");
            produto.setNome(scan.nextLine());

            System.out.print("Insira a Descrição: ");
            produto.setDescricao(scan.nextLine());

            produtoDAO.salvar(produto);

            //testando listagem
            System.out.println();
            System.out.println("Listando");
            produtos = produtoDAO.listar();
            produtos.forEach(p -> System.out.println(p+"\n"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
