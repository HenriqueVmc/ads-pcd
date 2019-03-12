package pct;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCircularBuffer implements Buffer{

	private int buffer[];
	ReentrantLock mutex = new ReentrantLock();
	Condition canGet = mutex.newCondition();
	Condition canSet = mutex.newCondition();
	
	int posRead = 0, posWrite = 0, numElements = 0;
	
	public LockCircularBuffer(int size) {
		buffer = new int[size];
	}
	
	@Override
	public void set(int value) {
		
		mutex.lock();		
		
		try {
			// Esperar Buffer esvaziar...
			while(numElements > 2){
				// Tratatar await no loop, pois caso a VM libere, ocorrerá uma exceção.
				// No entanto o loop irá persistir o await
				try { canSet.await(); } catch (InterruptedException e) {}
			}
			System.out.printf("Producer writer\t%2d", value);
			
			buffer[posWrite] = value;			
			
			posWrite = (posWrite + 1) % buffer.length;
			
			numElements++;
			
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
			while(numElements == 0){
				try { canGet.await(); } catch (InterruptedException e) {}
			}
			valor = buffer[posRead];
			System.out.printf("Consumer reads\t%2d", valor);
			
			buffer[posRead] = 0;
			
			posRead = (posRead + 1) % buffer.length; 	
			
			numElements--;
			
			canSet.signal();
			
		}finally {
			mutex.unlock();
		}
	
		return valor;
	}
}