/*
    BUFFER: Comprador vs 'Funcionario'(repõe itens)
 */
package pct;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferItensMercado implements Buffer {

    Mercado mercado;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();
    ItensMercado itemTemp;
    
    public BufferItensMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    @Override
    public void set(ItensMercado item) {
        mutex.lock();

        try {
            
            this.mercado.addProduto(item);
            
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
            while (mercado.qtdEstoque() <= 0) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }

        return this.mercado.getProduto(cod);
    }

    @Override
    public ItensMercado getRandom() {
        mutex.lock();

        try {
            // Esperar Buffer encher...
            while (mercado.qtdEstoque() == 0) {
                try {
                    canGet.await();
                } catch (InterruptedException e) {
                }
            }

        } finally {
            mutex.unlock();
        }

        int cod = new Random().nextInt(mercado.qtdEstoque());

        itemTemp = mercado.getProduto(cod);        

        return itemTemp;
    }
    
    @Override
    public ArrayList<ItensMercado> getAll() {
        return this.mercado.getAll();
    }
}
