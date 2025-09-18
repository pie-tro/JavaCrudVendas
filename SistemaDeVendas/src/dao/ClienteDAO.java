package dao;


import conexao.Conexao;
import beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pietr
 */
public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ClienteDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
      public void inserir (Cliente cliente){
        String sql = "INSERT INTO clientes (nome, endereco, email, telefone) VALUES (?,?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao inserir pessoa"+ex.getMessage());
        }
    }
      public Cliente getCliente(int id) 
    {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
            
            Cliente c = new Cliente();
            // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
            // Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            
            c.setId(id);
            c.setNome(rs.getString("nome"));
            c.setEndereco(rs.getString("endereco"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            return c;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
            return null;
        }
    }
        public void editar(Cliente cliente){
            try{
                String sql = "UPDATE pessoa set nome=?, sexo=?, idioma=? WHERE id=?";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,cliente.getNome());
                stmt.setString(2,cliente.getEndereco());
                stmt.setString(3,cliente.getEmail());
                stmt.setString(4,cliente.getTelefone());
                stmt.setInt(5,cliente.getId());
                stmt.execute();
            }catch(SQLException ex){
                System.out.println("Erro ao atualizar pessoa:" + ex.getMessage());
            }
        }
      public void excluir (int id){
        try {
          String sql = "DELETE from cliente WHERE id=?";

          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.execute();
      }
        catch(SQLException ex){
          System.out.println("Erro ao excluir cliente: " + ex.getMessage());
      }
  }   
      public int buscarIdPorNome(String nomeCliente) {
    int idCliente = -1; // Valor padrão em caso de não encontrar o cliente
    String sql = "SELECT codCliente FROM clientes WHERE nome = ?";
    
    try (
        PreparedStatement stmt = this.conn.prepareStatement(sql)
    ) {
        stmt.setString(1, nomeCliente);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                idCliente = rs.getInt("codCliente");
            }
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao buscar Código do cliente: " + ex.getMessage());
    }
    
    return idCliente;
}
 }

