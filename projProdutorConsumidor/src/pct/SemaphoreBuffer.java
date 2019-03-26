package pct;

import java.util.concurrent.Semaphore;

public class SemaphoreBuffer implements Buffer {

	private int buffer = -1;

	Semaphore sem = new Semaphore(1);

	@Override
	public void set(int value) {

		try {

			System.out.printf("Producer writes\t%2d", value);
			buffer = value;

			while (buffer != -1) {
				sem.release();
				sem.acquire();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int get() {
		int valor;

		try {

			while (buffer == -1) {
				sem.release();
				sem.acquire();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		valor = buffer;
		buffer = -1;

		System.out.printf("Consumer reads\t%2d", valor);

		return valor;
	}
}
