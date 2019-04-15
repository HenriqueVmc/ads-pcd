package pct;

import java.util.List;

public interface Buffer {

    public void set(ItensMercado item);

    public ItensMercado get(int cod);
    
    public List<ItensMercado> getAll(int cod);
}
