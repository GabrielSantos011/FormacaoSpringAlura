package br.com.alura.ligacaoComBD.testeConexao.quatro.dao;

import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Categoria;
import br.com.alura.ligacaoComBD.testeConexao.quatro.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//produto data access object
//centraliza os acessos ao banco de dados
//então aqui teremos métodos como listar, inserir, e por ai vai
public class ProdutoDAO {

    private Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Produto produto) {
        try (PreparedStatement pstm = conexao.prepareStatement(
                "INSERT INTO produto (nome, descricao) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rts = pstm.getGeneratedKeys()) {

                while (rts.next()) {
                    produto.setId(rts.getInt(1));
                }
                System.out.println(produto + " - adicionado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement pstm = conexao.prepareStatement(
                "SELECT ID, NOME, DESCRICAO FROM PRODUTO;")) {

            pstm.execute();

            try (ResultSet rts = pstm.getResultSet()) {

                while (rts.next()) {
                    Produto produto = new Produto();

                    produto.setId(rts.getInt("ID"));
                    produto.setNome(rts.getString("NOME"));
                    produto.setDescricao(rts.getString("DESCRICAO"));

                    produtos.add(produto);
                }

                return produtos;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro!");
        }
    }

    public List<Produto> buscar(Categoria ct) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        System.out.println("Executando a query de buscar produto por categoria");

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try(PreparedStatement pstm = conexao.prepareStatement(sql)) {
            pstm.setInt(1, ct.getId());
            pstm.execute();

            trasformarResultSetEmProduto(produtos, pstm);
        }
        return produtos;
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
        try(ResultSet rst = pstm.getResultSet()) {
            while(rst.next()) {
                Produto produto =
                        new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                produtos.add(produto);
            }
        }
    }

}
