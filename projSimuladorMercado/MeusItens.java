package pct;

import java.util.List;

public class MeusItens {

    private List<ItensMercado> itens;

    public ItensMercado get(int cod) {
        return itens.get(cod);
    }
    
    public List<ItensMercado> getAll() {
        return itens;
    }

    public void add(ItensMercado item) {        
        this.itens.add(item);
    }
}
