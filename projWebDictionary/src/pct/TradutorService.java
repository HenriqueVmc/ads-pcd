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
	
	public void tradutor(){
		
		Traducao traducao = cacheTraducoes.get(texto);
		
		if(traducao != null){
			//montarPacote(traducao);
		}
		else{
			// Consultar Base por Texto
			//montarPacote(traducao);
		}
	}
	
	public void montarPacote(Traducao traducao){
		
	}
}
