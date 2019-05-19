/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projchatgui;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author henrique
 */
public class ChatBuffer implements Buffer{


    ArrayList<String> mensagens = null;
    ArrayList<String> mensagensSender = null;
    ReentrantLock mutex = new ReentrantLock();
    Condition canGet = mutex.newCondition();

    public ChatBuffer() {
        mensagens = new ArrayList<String>();
        mensagensSender = new ArrayList<String>();
    }    
    
    public void set(String msg) {

        mutex.lock();

        try {
            mensagens.add(msg);
            mensagensSender.add(msg);
            canGet.signal();
        } finally {
            mutex.unlock();
        }
    }

    public String get() {

        mutex.lock();

        try {
            // Esperar Buffer encher...
            while (mensagens.size() == 0) {
                try {
                    canGet.await();
                }
                catch (InterruptedException e) {}
            }

        } finally {
            mutex.unlock();
        }

        int index = mensagens.size() - 1;
        String msgTemp = mensagens.get(index);
        mensagens.remove(index);

        return msgTemp;
    }
}
