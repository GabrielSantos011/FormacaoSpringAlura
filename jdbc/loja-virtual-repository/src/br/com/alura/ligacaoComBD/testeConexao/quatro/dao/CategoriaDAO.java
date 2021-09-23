package br.com.alura.ligacaoComBD.testeConexao.quatro.dao;

import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Categoria;
import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection conexao;

    public CategoriaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement pstm = conexao.prepareStatement(
                "SELECT ID, NOME FROM CATEGORIA;")) {

            pstm.execute();

            try (ResultSet rts = pstm.getResultSet()) {

                while (rts.next()) {
                    Categoria categoria = new Categoria();

                    categoria.setId(rts.getInt("ID"));
                    categoria.setNome(rts.getString("NOME"));

                    categorias.add(categoria);
                }

                return categorias;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro!");
        }
    }

    public List<Categoria> listarComProduto() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a query de listar categoria");

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";

        try(PreparedStatement pstm = conexao.prepareStatement(sql)) {
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) {
                    if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                        Categoria categoria =
                                new Categoria(rst.getInt(1), rst.getString(2));

                        categorias.add(categoria);
                        ultima = categoria;
                    }
                    Produto produto =
                            new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
                    ultima.adicionar(produto);
                }
            }
            return categorias;
        }
    }

}
