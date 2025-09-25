package dao;

import conexao.Conexao;
import beans.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pietr
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, descricao, precoVenda, qtdEstoque) VALUES (?,?,?,?);";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getPVenda());
            stmt.setString(4, produto.getQtdEstoque());

            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir produto: " + ex.getMessage());
        }
    }

    public Produto getProduto(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Produto p = new Produto();

            if (rs.first()) {
                p.setId(id);
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPVenda(rs.getString("pVenda"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
            }
            return p;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }
    public int buscarIdPorNome(String nomeProduto) {
        int idProduto = -1; 
        String sql = "SELECT codProduto FROM produtos WHERE nome = ?";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idProduto = rs.getInt("codProduto");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Código do produto: " + ex.getMessage());
        }
        
        return idProduto;
    }
}
