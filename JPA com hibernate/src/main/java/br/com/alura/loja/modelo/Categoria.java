package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
//JPA exige que tenha construtor padrão pada dar o select, neste caso está implícito
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

}
