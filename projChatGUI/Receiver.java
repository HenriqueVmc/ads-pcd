package projchatgui;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver implements Runnable {

    MulticastSocket socket;
    String group;

    Buffer mensagensChat;

    public Receiver(MulticastSocket socket, Buffer mensagensChat, String group) {
        this.socket = socket;
        this.mensagensChat = mensagensChat;
        this.group = group;
    }

    @Override
    public void run() {
        while (true) {
            try {

                socket.joinGroup(InetAddress.getByName(group));

                byte buf[] = new byte[3072];
                DatagramPacket pack = new DatagramPacket(buf, buf.length);

                socket.receive(pack);

                String IP = pack.getAddress().toString();
                int porta = pack.getPort();

                String msg = new String(pack.getData(), pack.getOffset(), pack.getLength());

                //Log:
                System.out.println("\nGrupo: " + group + " | End.: " + IP + ":" + porta + " | Msg.: " + msg);

                mensagensChat.set(msg);

                socket.leaveGroup(InetAddress.getByName(group));

            } catch (Exception w) {
                socket.close();
            }
        }
    }
}
