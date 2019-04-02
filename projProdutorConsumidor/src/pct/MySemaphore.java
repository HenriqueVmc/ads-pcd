package pct;

public class MySemaphore { // Implementação relativa ao Semaphore do JAVA

	private int cont = 0; // Quantidade de Threads que passará pelo Semaphore
	
	public MySemaphore(int cont) {
		this.cont = cont;
	}
	
	public synchronized void acquire(){
		try {
			
			while(cont == 0) wait();
			cont--; // Reservando posição no Semaforo
			
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public synchronized void release(){				
		cont++;
		notifyAll();
	}
}
