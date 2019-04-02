package pct;

import java.util.concurrent.Semaphore;

public class SemaphoreBuffer implements Buffer {

	private int buffer = -1;

	Semaphore sem = new Semaphore(1);	

	@Override
	public void set(int value) {

		try {
			sem.acquire();		

			while (buffer != -1) {
				sem.release();
				sem.acquire();
			}
			System.out.printf("Producer writes\t%2d", value);
			buffer = value;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			sem.release();
		}
	}

	@Override
	public int get() {
		int valor = 0;

		try {
			
			sem.acquire();
			
			// Temos um problema, Gasta muita energia:			
			while (buffer == -1) {
				sem.release();
				sem.acquire();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			sem.release();
		}

		valor = buffer;
		buffer = -1;

		System.out.printf("Consumer reads\t%2d", valor);

		return valor;
	}
}
