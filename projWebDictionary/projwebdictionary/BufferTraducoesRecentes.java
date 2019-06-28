package projwebdictionary;

import java.util.ArrayList;

public class BufferTraducoesRecentes implements IBuffer {

    ArrayList<Traducao> cacheTraducoes;

    public BufferTraducoesRecentes() {
        // TODO Auto-generated constructor stub
        cacheTraducoes = new ArrayList<Traducao>();
    }

    @Override
    public void set(Traducao traducao) {
        Traducao existe = get(traducao.texto);
        
        if(existe == null)
            cacheTraducoes.add(traducao);
    }

    @Override
    public Traducao get(String texto) {
        
        for (Traducao traducao : cacheTraducoes) {

            if (texto.equals(traducao.texto)) {
                return traducao;
            }
        }

        return null;
    }
}
