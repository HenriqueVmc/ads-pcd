package pct;

import java.util.ArrayList;

public class MeusItens {

    private ArrayList<ItensMercado> itens;
    private double valorTotal;
    
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal += valorTotal;
    }    
    
    public MeusItens(){
        itens = new ArrayList<ItensMercado>();
        valorTotal = 0;
    }
    public ItensMercado get(int cod) {
        return itens.get(cod);
    }
    
    public ArrayList<ItensMercado> getAll() {
        return itens;
    }

    public void add(ItensMercado item) {        
        this.itens.add(item);
        setValorTotal(item.getValor());
    }
}
