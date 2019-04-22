package pct;

import java.util.ArrayList;

public class Sacola {

    private ArrayList<ItensMercado> compra;

    public void setCompra(ItensMercado itemCompra) {
        this.compra.add(itemCompra);
    }

    public Sacola(){
        this.compra = new ArrayList<ItensMercado>();
    }
}
