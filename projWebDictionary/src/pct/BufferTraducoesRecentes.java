package pct;

import java.util.ArrayList;

public class BufferTraducoesRecentes implements IBuffer {

	
	ArrayList<Traducao> cacheTraducoes;
	
	public BufferTraducoesRecentes() {
		// TODO Auto-generated constructor stub
		cacheTraducoes = new ArrayList<Traducao>(); 
	}
	
	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Traducao get(String texto) {
		// TODO Auto-generated method stub
		
		for (Traducao traducao : cacheTraducoes) {
			
			if(texto.equals(traducao.texto))
				return traducao;							
		}
		
		return new Traducao();
	}
}
