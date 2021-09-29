package br.com.alura.loja.estadosDaEntidade;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class EstadosDaEntidade {

    public static void main(String[] args) {
        Categoria celulares = new Categoria();
        //ao dar o new em uma entity ela se encontra em estado Transient
        //ou seja, a JPA não está de olho nessa endidade e nada que fizermos será salvo no bd
        celulares.setNome("celulares");

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        em.persist(celulares);
        //a partir deste persist ela passa a ter o estado managed
        //ou seja, tudo aqui a jpa está monitorando
        //ao setar outra coisa no nome por ex
        celulares.setNome("novo nome");
        //a jpa fará um update
        //temos o comando flush() e o commit() que jogam as entidades no bd
        //commit() não damos mais flush()
        em.flush();

        //o close() e o clear() tiram as entidades do estado managed
        //e coloca em Detached que é diferente do transient já que agora o objeto já tem um id
        em.clear();
        //a partir de agora se houver alteração a jpa não está "de olho"
        //portanto não terá update
        celulares.setNome("aaaa");

        //caso precisamos dar um update em uma entity no estado de Detached
        // usamos o merge() que da um select e devolve uma entity no estado managed
        //caso o já tenha sido chamado o em.close() faremos uma busca no banco de dados
        //com o find() e o createQuery() porém é assunto para próxima aula
        celulares = em.merge(celulares);
        //no comando acima celulares já foi atualizado, ou seja, se dermos um select
        //agora o nome será aaaa

        //um novo update
        celulares.setNome("novovrovnkenv");

        em.flush();

        //caso eu queira excluir a entity deve estar no método managed então conseguimos
        //colocar ela no estado de removed e ao dar o flush() ou o commit() ela será deletada
        em.remove(celulares);

        em.getTransaction().commit();
        em.close();
    }

}
