package pct;

import java.util.List;

public class Mercado {

    private List<ItensMercado> estoque;

    public Mercado(List<ItensMercado> itens) {
        this.estoque.addAll(itens);
    }

    public ItensMercado getProduto(int indice) {
        return estoque.get(indice);
    }

    public List<ItensMercado> getAll() {
        return estoque;
    }

    public void addProduto(ItensMercado item) {
        this.estoque.add(item);
    }
}