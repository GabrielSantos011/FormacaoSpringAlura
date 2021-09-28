package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//para dizermos a JPA que essa classe é uma tabela no banco de dados
//usamos a notação @Entity com isso ela entende que é uma entidade do bd
//porém ela usa o mesmo nome da classe mas no bd gostariamos que fosse "produtos"
//portanto usamos a notação @Table com o atributo name indicando qual é o nome

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
    private LocalDate dataCadastro = LocalDate.now();

    //cada produto vai ter uma categoria que aqui no java é um Enum
    //porém se eu não colocar notação nenhuma vai ficar por padrão
    //@Enumerated(EnumType.ORDINAL) ou seja, pela sequencia
    //que não é nada agradavel caso alguem altere essa sequencia
    //no caso queremos que seja um varchar portanto colocamos o outro valor possível
    //@Enumerated(EnumType.STRING) que vai colocar o nome da constante
    //obs1:atualização, Categoria deixou de ser um enum e passou a ser uma classe
    //também com a notação @entity, a partir deste momento a JPA identifica que é um relacionamento
    //porém ela não tem um valor padrão, ou seja, precisamos passar a cardinalidade através de notações
    //temos @OneToOne, @ManyToOne, @OneToMany e @ManyToMany
    //neste caso usaremos o @ManyToOne pois um produto só pode ter uma categoria,
    //mas a categoria pode ter vários produtos
    @ManyToOne
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
