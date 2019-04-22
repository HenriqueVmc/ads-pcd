/*
    BUFFER: Empacotador vs Caixa
 */
package pct;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferRampaItensCaixa implements Buffer{

    ArrayList<ItensMercado> itensRampa = null;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();
    
    public BufferRampaItensCaixa(){
        itensRampa = new ArrayList<ItensMercado>();
    }
    
    @Override
    public void set(ItensMercado item) {
        mutex.lock();
        
        try {
            itensRampa.add(item);
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
            while (itensRampa.size() <= 1) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }
        
        if(cod > itensRampa.size())
            cod = itensRampa.size() -1;
        
        return itensRampa.get(cod);
    }

    @Override
    public ArrayList<ItensMercado> getAll() {
        return itensRampa;
    }

}
