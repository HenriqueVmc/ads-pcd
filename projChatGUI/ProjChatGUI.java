/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projchatgui;

import java.io.IOException;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrique
 */
public class ProjChatGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            MensagensJogosBuffer mensagensJogos = new MensagensJogosBuffer();
            MensagensFutebolBuffer mensagensFutebol = new MensagensFutebolBuffer();

            ExecutorService application = Executors.newFixedThreadPool(5);

            int porta = 5000;MulticastSocket s2 = new MulticastSocket(porta);
            String groupJogos = "230.0.0.0";
            String groupFutebol = "231.1.1.1";
            
            MulticastSocket s = new MulticastSocket(porta);            

            System.out.println("\n--- Iniciando ---\n");

            try {
                application.execute(new ChatGUI(mensagensJogos, mensagensFutebol));
                
                application.execute(new Receiver(s, mensagensJogos, groupJogos));
                application.execute(new Receiver(s, mensagensFutebol, groupFutebol));
                application.execute(new Sender(s, mensagensJogos, groupJogos));                                         
                application.execute(new Sender(s, mensagensFutebol, groupFutebol));                                

            } // end try
            catch (Exception exception) {
                exception.printStackTrace();
            } // end catch

            application.shutdown();

        } catch (IOException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
