/*
    BUFFER: Empacotador vs Caixa
 */
package pct;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferRampaItensCaixa implements Buffer{

    ArrayList<ItensMercado> itensRampa = null;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();
    ItensMercado itemTemp;
    
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
            while (itensRampa.size() == 0) {
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
        
        itemTemp = itensRampa.get(cod);
        itensRampa.remove(cod);
        
        return itemTemp;
    }

    @Override
    public ArrayList<ItensMercado> getAll() {
        return itensRampa;
    }

    @Override
    public ItensMercado getRandom() {
        mutex.lock();

        try {
            // Esperar Buffer encher...
            while (itensRampa.size() == 0) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }

        int cod = new Random().nextInt(itensRampa.size());                

        itemTemp = itensRampa.get(cod);
        itensRampa.remove(cod);

        return itemTemp;
    }
}
