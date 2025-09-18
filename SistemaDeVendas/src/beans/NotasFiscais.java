package beans;

import java.sql.Timestamp;
/**
 *
 * @author pietr
 */
public class NotasFiscais {
    private int codNota;
    private int codCliente;
    private int IdProduto;
    private double subtotal;
    private Timestamp data;
    
    
    public int getIdProduto(){
        return IdProduto;
    }
    public void setIdProduto(int IdProduto){
        this.IdProduto = IdProduto;
    }
    public int getCodNota() {
        return codNota;
    }

    public void setCodNota(int codNota) {
        this.codNota = codNota;
    }

    public int getCliente() {
        return codCliente;
    }

    public void setCliente(int cliente) {
        this.codCliente = cliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setsubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
}
