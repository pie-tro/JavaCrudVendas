package conexao;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pietr
 */
public class Conexao {
    public static Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bdprojeto?useTimezone=true&serverTimezone=UTC",
                    "root",""
            );
            System.out.println("Conexão realizada com sucesso");
            return conn;
        }
        catch(Exception e){
            System.out.println("Erro ao conectar no BD"+e.getMessage());
            return null;
        }
    }
}
