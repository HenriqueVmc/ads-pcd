package pct;

import java.util.ArrayList;

public class MinhaCompra {

    private double valorTotal;
    private ArrayList<ItensMercado> itens = null;
    private String notaFiscal;
    
    public MinhaCompra(){
        this.itens = new ArrayList<ItensMercado>();
        this.notaFiscal = "";
        this.valorTotal = 0;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal += valorTotal;
    }

    public ItensMercado getItens(int cod) {
        return itens.get(cod);
    }
        
    public ArrayList<ItensMercado> getAll(int cod) {
        return itens;
    }

    public void setItens(ItensMercado itens) {
        this.itens.add(itens);
    }
    
    public String getNotaFiscal() {
        return notaFiscal;
    }
    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal += notaFiscal;
    }
       
}
