package pct;

import java.util.Random;

public class Corredor implements Runnable{

	private String nome;
	private int tempo;
	
	public String getNome(){
		return this.nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public int getTempo(){
		return this.tempo;
	}
	public void setTempo(int tempo){
		this.tempo += tempo;
	}
	
	// --------------------------------
	public Corredor(String nome){
		setNome(nome);
		setTempo(0);
	}
	
	public void venceu(Corredor adversario){
		
		if(this.getTempo() < adversario.getTempo()){ 
			System.out.println(this.getNome() +" Venceu! - Tempo: "+ this.getTempo());
			System.out.println(adversario.getNome() + " - Tempo: "+ this.getTempo());
		}
		else if(this.getTempo() > adversario.getTempo()){
			System.out.println(adversario.getNome() +" Venceu! - Tempo: "+ adversario.getTempo());
			System.out.println(this.getNome() + " - Tempo: "+ this.getTempo());			
		}
		else{
			System.out.println("Empate!");
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		try {
			
			System.out.println(nome + " deu partida!\n");
			
			int voltas = 0;
			
			while(voltas < 6){	
			
				int stop = (new Random().nextInt(1000));			
			
				Thread.sleep(stop);
				System.out.println(nome + " concluiu uma volta em: " + stop + "s");
				
				this.setTempo(stop);
				
				voltas++;
			}
			
			System.out.println("\n ### " + nome + " Finalizou! ###\n");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
