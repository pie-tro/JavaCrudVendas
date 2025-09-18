package beans;

/**
 *
 * @author pietr
 */
public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private String pVenda;
    private String qtdEstoque;

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPVenda() {
        return pVenda;
    }

    public String getQtdEstoque() {
        return qtdEstoque;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPVenda(String pVenda) {
        this.pVenda = pVenda;
    }

    public void setQtdEstoque(String qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}
