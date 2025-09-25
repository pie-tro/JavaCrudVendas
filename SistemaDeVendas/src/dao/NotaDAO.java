package dao;

import java.sql.Timestamp;
import beans.NotasFiscais;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
    private Conexao conexao;
    private Connection conn;

    public NotaDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public NotasFiscais getNota(int codNota) {
        String sql = "SELECT * FROM NotasFiscais WHERE codNota = ?";
        NotasFiscais nota = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, codNota);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                nota = new NotasFiscais();
                nota.setCodNota(rs.getInt("codNota"));
                nota.setCliente(rs.getInt("codCliente"));
                nota.setIdProduto(rs.getInt("IdProduto"));
                nota.setData(rs.getTimestamp("dataVenda"));
                nota.setsubtotal(rs.getDouble("subtotal"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar nota: " + ex.getMessage());
        }
        return nota;
    }
    public List<String> listarNomesClientes() {
    List<String> nomesClientes = new ArrayList<>();
    String sql = "SELECT nome FROM clientes ORDER BY nome";
    
    try (
        PreparedStatement stmt = this.conn.prepareStatement(sql);     
        ResultSet rs = stmt.executeQuery()
    ) {
       
        while (rs.next()) {
            nomesClientes.add(rs.getString("nome"));
        }
    } catch (SQLException ex) {
      
        System.err.println("Erro ao listar clientes: " + ex.getMessage());
    }
     System.out.println("Número de clientes encontrados: " + nomesClientes.size());
    
    return nomesClientes;
}
    public List<String> listarProdutos() {
    List<String> nomesProdutos = new ArrayList<>();
    String sql = "SELECT nome FROM produtos ORDER BY nome";
    
    try (
       
        PreparedStatement stmt = this.conn.prepareStatement(sql);
      
        ResultSet rs = stmt.executeQuery()
    ) {
        while (rs.next()) {
            nomesProdutos.add(rs.getString("nome"));
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao listar clientes: " + ex.getMessage());
    }
    return nomesProdutos;
    }
    public void inserir (NotasFiscais notas){
        String sql = "INSERT INTO notasfiscais (codNota, codCliente, codProduto, dataVenda, subtotal) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, notas.getCodNota());
            stmt.setInt(2, notas.getCliente());
            stmt.setInt(3, notas.getIdProduto());
            stmt.setTimestamp(4, notas.getData());
            stmt.setDouble(5, notas.getSubtotal());
            
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao inserir nota fiscal"+ex.getMessage());
        }
    }
}
