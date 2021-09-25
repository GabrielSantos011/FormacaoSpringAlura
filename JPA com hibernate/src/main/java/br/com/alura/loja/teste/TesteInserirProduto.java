package br.com.alura.loja.teste;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteInserirProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Iphone XR");
        celular.setDescricao("Muito bom");
        celular.setPreco(new BigDecimal("2800"));

        //sempre que quisermos fazer uma transação utilizaremos o EntityManager
        //para criar um EntityManager precisamos de um EntityManagerFactory - para criar
        //passamos o atributo name que colocamos na tag persistence-unit no persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
        EntityManager em = factory.createEntityManager();

        //para inserir
        //como na tag persistence-unit no persistence.xml dissemos que nosso tipo de transação
        //é RESOURCE_LOCAL nós mesmos temos que iniciar a transação
        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }

}
