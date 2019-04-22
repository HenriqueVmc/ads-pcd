/*
    BUFFER: Esteira/Caixa/Comprador vc Comprador
 */
package pct;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferCarrinhoCompras implements Buffer {

    ArrayList<ItensMercado> itensCarrinho = null;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();
    
    public BufferCarrinhoCompras(){
        itensCarrinho = new ArrayList<ItensMercado>();
    }
    
    @Override
    public void set(ItensMercado item) {
                
        mutex.lock();
        
        try {
            itensCarrinho.add(item);
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
            while (itensCarrinho.size() == 0) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }

        if(cod > itensCarrinho.size())
            cod = itensCarrinho.size() -1;
                
        return itensCarrinho.get(cod);
    }

    @Override
    public ArrayList<ItensMercado> getAll() {
        return itensCarrinho;
    }
}
