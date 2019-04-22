/*
    BUFFER: Comprador vs 'Funcionario'(repõe itens)
 */
package pct;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferItensMercado implements Buffer {

    Mercado mercado;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();

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
    public ArrayList<ItensMercado> getAll() {
        return this.mercado.getAll();
    }
}
