package pct;

public class SynchronizedBuffer implements Buffer{

	private int buffer = -1;
	
	public synchronized void set(int value) {
		// Não produzir em buffer cheio
		while(buffer != -1){
			try {
				wait(); // Sempre reterstar wait() quando 'acordar' -> notify()
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
		
		// Liberar alguém que quer consumir
		notify();
	}

	public synchronized int get() {
		// Não consumir buffer vazio						
		int dado;
		
		// Sempre While para mais de uma Thread, para retestar a condição:
		while(buffer == -1){
			try {
				wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}		
		}
		
		// Notifico quem está aguardando para produzir
		// Poderia ser All neste caso
		notify();
		dado = buffer;		
		buffer = -1;
		
		System.out.printf("Consumer reads\t%2d", dado);		
					
		return dado;		
	} 
}

