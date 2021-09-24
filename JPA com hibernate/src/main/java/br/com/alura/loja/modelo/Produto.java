package br.com.alura.loja.modelo;

//para dizermos a JPA que essa classe é uma tabela no banco de dados
//usamos a notação @Entity com isso ela entende que é uma entidade do bd
//porém ela usa o mesmo nome da classe mas no bd gostariamos que fosse "produtos"
//portanto usamos a notação @Table com o atributo name indicando qual é o nome

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {
    //os atributos já são entendidos como colunas no bd, mas caso precise que na tabela
    // seja um nome diferente em algum atributo utilizamos a notação @Column(name = "nome_no_bd")

    //para informarmos que é uma chave primária colocamos um @Id
    //para falarmos que quem vai gerar o valor automáticamente é o bd (auto increment)
    //utilizamos o @GeneretedValue(strategy = GeneretionType.IDENTITY) - De maneira geral utilizamos o Identity
    //caso o banco de dados não tenha o sequence, caso contrario usamos SEQUENCE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

}
