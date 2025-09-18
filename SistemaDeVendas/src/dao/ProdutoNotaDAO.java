package dao;
import beans.ProdutosNota;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pietr
 */
public class ProdutoNotaDAO {
 
    private Conexao conexao;
    private Connection conn;

    public ProdutoNotaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public boolean salvarProdutoNota(ProdutosNota pn) {
        String sql = "INSERT INTO ProdutosNotas (codNota, codProduto, qtdVendida) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pn.getCodNota());
            stmt.setInt(2, pn.getCodProduto());
            stmt.setInt(3, pn.getQtdVendida());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar ProdutoNota: " + ex.getMessage());
            return false;
        }
    }

    public List<ProdutosNota> listarProdutosPorNota(int codNota) {
        List<ProdutosNota> lista = new ArrayList<>();
        String sql = "SELECT * FROM ProdutosNotas WHERE codNota = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codNota);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProdutosNota pn = new ProdutosNota();
                pn.setCodNota(rs.getInt("codNota"));
                pn.setCodProduto(rs.getInt("codProduto"));
                pn.setQtdVendida(rs.getInt("qtdVendida"));
                lista.add(pn);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos da nota: " + ex.getMessage());
        }
        return lista;
    }
}

