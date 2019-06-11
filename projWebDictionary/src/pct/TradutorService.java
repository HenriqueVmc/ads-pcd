package pct;

public class TradutorService implements Runnable{

	private String texto;
	private IBuffer cacheTraducoes;
	
	@Override
	public void run() {
		// TODO Manipular Buffer de Cache, Base de dados e montar pacote de retorno 
	}

	public TradutorService(String texto, IBuffer cacheTraducoes) {

		this.texto = texto;
		this.cacheTraducoes = cacheTraducoes;
	}
}
