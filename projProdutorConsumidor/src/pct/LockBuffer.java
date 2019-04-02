package pct;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockBuffer implements Buffer{

	private int buffer = -1;
	ReentrantLock mutex = new ReentrantLock();
	Condition canGet = mutex.newCondition();
	Condition canSet = mutex.newCondition();
	
	@Override
	public void set(int value) {
		
		mutex.lock();
		
		try {
			// Esperar Buffer esvaziar...
			while(buffer != -1){
				// Tratatar await no loop, pois caso a VM libere, ocorrerá uma exceção.
				// No entanto o loop irá persistir o await
				try { canSet.await(); } catch (InterruptedException e) {}
			}
			System.out.printf("Producer writer\t%2d", value);
			buffer = value;			
			
			canGet.signal();
		
		}finally {
			mutex.unlock();
		}		
	}

	@Override
	public int get() {		
		
		int valor = 0;		
		mutex.lock();
		
		try {
			// Esperar Buffer encher...
			while(buffer == -1){
				try { canGet.await(); } catch (InterruptedException e) {}
			}
			valor = buffer;
			System.out.printf("Consumer reads\t%2d", valor);
			buffer = -1;
			
			canSet.signal();
			
		}finally {
			mutex.unlock();
		}
	
		return valor;
	}
}