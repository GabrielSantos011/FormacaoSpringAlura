package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TesteInserirProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria();
        celulares.setNome("celulares");

        Produto celular = new Produto();
        celular.setNome("Iphone XR");
        celular.setDescricao("Muito bom");
        celular.setPreco(new BigDecimal("2800"));
        celular.setCategoria(celulares);

        //sempre que quisermos fazer uma transação utilizaremos o EntityManager
        //para criar um EntityManager precisamos de um EntityManagerFactory - para criar
        //passamos o atributo name que colocamos na tag persistence-unit no persistence.xml
        //obs1: atualização de código, deixando mais limpo e orientado a objeto
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        //para inserir
        //como na tag persistence-unit no persistence.xml dissemos que nosso tipo de transação
        //é RESOURCE_LOCAL nós mesmos temos que iniciar a transação
        //obs1: atualização de código, deixando mais limpo e orientado a objeto
        em.getTransaction().begin();
        //para inserirmos o celular a categoria criada tem que estar criada por isso vamos gravar a categoria primeiro
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
