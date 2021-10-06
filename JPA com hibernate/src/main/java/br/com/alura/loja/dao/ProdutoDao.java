package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public Produto buscarPorId(Long id) {
        //o método find necessita da classe e o id
        //e retorna um objeto da classe passada
        return this.em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        //o método create query necessita de uma query em jpql
        //que é um sql adaptado para o java
        //select o objeto p
        String jpql = "SELECT p FROM Produto p";
        //passamos como parametro também a classe para ele saber que é uma lista daquela classe
        //O createQuery apenas monta a query sendo necesaário chamar o
        //getResultList para devolver uma lista
        return this.em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return this.em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
        return this.em.createQuery(jpql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    //buscar um único parametro
    public BigDecimal buscarPrecoDoProduto(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return this.em.createQuery(jpql, BigDecimal.class)
                .setParameter(1, nome)
                .getSingleResult();
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        this.em.merge(produto);//garantindo que está em managed
        this.em.remove(produto);
    }

}
