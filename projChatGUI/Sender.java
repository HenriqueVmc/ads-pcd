package projchatgui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender implements Runnable {

    MulticastSocket socket;
    int port = 5000;
    String group;

    Buffer sendMensagens;

    public Sender(MulticastSocket socket, Buffer sendMensagens, String group) {
        this.socket = socket;
        this.sendMensagens = sendMensagens;
        this.group = group;
    }

    @Override
    public void run() {
        while (true) {
            try {

                String texto = sendMensagens.get();
                byte data[] = texto.getBytes();

                DatagramPacket pack = new DatagramPacket(data, data.length, InetAddress.getByName(group), port);

                if (texto.length() > 0) socket.send(pack);
                
            } catch (IOException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
                socket.close();
            }
        }
    }
}
