package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public Categoria buscarPorId(Long id) {
        //o método find necessita da classe e o id
        //e retorna um objeto da classe passada
        return this.em.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos() {
        //o método create query necessita de uma query em jpql
        //que é um sql adaptado para o java
        String jpql = "SELECT c FROM Categoria c";
        //passamos como parametro também a classe para ele saber que é uma lista daquela classe
        //O createQuery apenas monta a query sendo necesaário chamar o
        //getResultList para devolver uma lista
        return this.em.createQuery(jpql, Categoria.class).getResultList();
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria) {
        this.em.merge(categoria);
        this.em.remove(categoria);
    }

}
