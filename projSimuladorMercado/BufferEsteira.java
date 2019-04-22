/*
    BUFFER: Comprador/Carrinho vs Caixa
 */
package pct;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferEsteira implements Buffer {

    ArrayList<ItensMercado> itensEsteira = null;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();

    public BufferEsteira() {
        itensEsteira = new ArrayList<ItensMercado>();
    }

    @Override
    public void set(ItensMercado item) {
        
        mutex.lock();
        
        try {
            itensEsteira.add(item);
            canGet.signal();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public ItensMercado get(int cod) {

        mutex.lock();

        try {
            // Esperar Buffer encher...
            while (itensEsteira.size() == 0) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }
        
        if(cod > itensEsteira.size())
            cod = itensEsteira.size() -1;
        
        return itensEsteira.get(cod);
    }

    @Override
    public ArrayList<ItensMercado> getAll() {
        return itensEsteira;
    }
}
