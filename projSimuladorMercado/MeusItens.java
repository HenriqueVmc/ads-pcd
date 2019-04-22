package pct;

import java.util.ArrayList;

public class MeusItens {

    private ArrayList<ItensMercado> itens;
    private double valorTotal;

    public double getValorTotal() {
        double valor = 0;
        
        for (ItensMercado item : itens) {
            valor += item.getValor();
        }
        return valor;
    }

    public MeusItens() {
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
    }
}
