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

            MensagensJogosBuffer sendMensagensJogos = new MensagensJogosBuffer();
            MensagensFutebolBuffer sendMensagensFutebol = new MensagensFutebolBuffer();
            ChatBuffer mensagensChat = new ChatBuffer();                        

            int porta = 5000;
            String groupJogos = "230.0.0.0"; // Sala para Jogos
            String groupFutebol = "231.1.1.1"; // Sala para 

            MulticastSocket socketJogos = new MulticastSocket(porta);
            MulticastSocket socketFutebol = new MulticastSocket(porta);           

            ExecutorService application = Executors.newFixedThreadPool(5);
            
            System.out.println("\n--- Log do Chat ---\n");
            try {

                application.execute(new ChatGUI(sendMensagensJogos, sendMensagensFutebol, mensagensChat));

                application.execute(new Receiver(socketJogos, mensagensChat, groupJogos));
                application.execute(new Sender(socketJogos, sendMensagensJogos, groupJogos));

                application.execute(new Receiver(socketFutebol, mensagensChat, groupFutebol));
                application.execute(new Sender(socketFutebol, sendMensagensFutebol, groupFutebol));

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
