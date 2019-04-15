package pct;

public class ItensMercado {

    private String Nome;
    private double Valor;
    private String Descricao;
    private int Quantidade;
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public double getValor() {
        return Valor;
    }
    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public String getDescricao() {
        return Descricao;
    }
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQuantidade() {
        return Quantidade;
    }
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
}
