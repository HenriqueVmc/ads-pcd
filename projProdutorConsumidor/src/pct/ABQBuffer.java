package pct;

import java.util.concurrent.ArrayBlockingQueue;

public class ABQBuffer implements Buffer{

	// Já é implementado internamente o monitor / sincronização / sinalização / esperar e etc..
	private ArrayBlockingQueue<Integer> buffer;
	
	public ABQBuffer(int size) {
		this.buffer = new ArrayBlockingQueue<Integer>(size);
	}
	
	@Override
	public void set(int value) {
		try {
			buffer.put(value);
			System.out.printf("Producer writer\t%2d", value);
		} catch (InterruptedException e) { e.printStackTrace(); }	
	}

	@Override
	public int get() {		
		int valor = 0;
		try {
			valor = buffer.take();
			System.out.printf("Consumer reads\t%2d", valor);
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		return valor;
	}

}
