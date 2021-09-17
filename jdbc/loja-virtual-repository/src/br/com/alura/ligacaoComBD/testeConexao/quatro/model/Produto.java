package br.com.alura.ligacaoComBD.testeConexao.quatro.model;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;

    @Override
    public String toString() {
        return "Produto\nid: " + id +"\nnome: " + nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
