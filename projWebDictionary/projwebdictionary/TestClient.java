/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projwebdictionary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import org.omg.CORBA.portable.UnknownException;

public class TestClient {

    public static void main(String[] args) {
        Socket client;
        DataInputStream is;
        DataOutputStream os;

        try {

            System.out.println("\nCliente vai se conectar ao server");
            // localhost pois cliente/servidor estao locais				
            client = new Socket("192.168.25.94", 1234);
            System.out.println("\nconectado!");

            // Sistema de comunicação (RxT)			
            is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());

            os.writeUTF("good");
            
            String msg = is.readUTF();
            System.out.println("(Client)Mensagem recebida: " + msg);

            is.close();
            os.close();

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
