package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TesteConsulta {

    public static void main(String args[]) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        Categoria celulares = new Categoria();
        celulares.setNome("celulares");

        Produto celular = new Produto();
        celular.setNome("Iphone XR");
        celular.setDescricao("Muito bom");
        celular.setPreco(new BigDecimal("2800"));
        celular.setCategoria(celulares);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        Produto p = produtoDao.buscarPorId(1l);
        List<Produto> produtos = produtoDao.buscarTodos();
        em.close();

        produtos.forEach((p2)-> System.out.println(p.getNome()));

        System.out.println(p.getNome());
    }

}
