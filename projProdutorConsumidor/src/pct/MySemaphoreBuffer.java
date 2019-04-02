package pct;

public class MySemaphoreBuffer implements Buffer {

	private int buffer = -1;

	MySemaphore sem = new MySemaphore(1);
	MySemaphore condition = new MySemaphore(0);

	@Override
	public void set(int value) {

		try {
			sem.acquire();

			while (buffer != -1) {
				sem.release();
				condition.acquire(); // await
				sem.acquire();
			}
			
			System.out.printf("Producer writes\t%2d", value);
			buffer = value;
			
			condition.release(); // signal			
			
		}
		finally { sem.release(); }
	}

	@Override
	public int get() {
		int valor = 0;

		try {			
			sem.acquire();
			
			// Solução para o 'problema' do SemaphoreBuffer			
			while (buffer == -1) {
				sem.release();
				condition.acquire(); // await
				sem.acquire();
			}
			
			valor = buffer;
			buffer = -1;
			System.out.printf("Consumer reads\t%2d", valor);
			
			condition.release(); // signal
			
		}
		finally { sem.release(); }

		return valor;
	}
}
