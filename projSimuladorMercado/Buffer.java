package pct;

import java.util.ArrayList;

public interface Buffer {

    public void set(ItensMercado item);

    public ItensMercado get(int cod);
    
    public ItensMercado getRandom();
    
    public ArrayList<ItensMercado> getAll();
}
