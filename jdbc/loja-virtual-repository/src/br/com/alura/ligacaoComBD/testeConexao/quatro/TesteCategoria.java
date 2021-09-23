package br.com.alura.ligacaoComBD.testeConexao.quatro;

import br.com.alura.ligacaoComBD.testeConexao.factory.ConnectionFactoryComDataSource;
import br.com.alura.ligacaoComBD.testeConexao.quatro.dao.CategoriaDAO;
import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Categoria;
import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TesteCategoria {

    public static void main(String args[]) throws SQLException {
        ConnectionFactoryComDataSource criaConexao = new ConnectionFactoryComDataSource();
        try (Connection conexao = criaConexao.getConexao()) {
            Categoria categoria = new Categoria();
            List<Categoria> categorias;
            CategoriaDAO categoriaDAO = new CategoriaDAO(conexao);

            //testando listagem
            System.out.println("Listando");
            categorias = categoriaDAO.listar();
            categorias.forEach(c -> System.out.println(c+"\n"));

            //listando com produtos
            categorias = categoriaDAO.listarComProduto();
            categorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for(Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}