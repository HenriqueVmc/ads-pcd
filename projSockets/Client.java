package pct;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class Client {

	public static void main(String[] args) {
		Socket client;
		DataInputStream is;
		DataOutputStream os;
		
		try {
			try {
				
				System.out.println("\nCliente vai se conectar ao server");
				// localhost pois cliente/servidor estao locais				
				client = new Socket("10.61.2.238", 8080);
				System.out.println("\nconectado!");		
				
				// Sistema de comunicação (RxT)			
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				
				//os.writeUTF("Olá eu sou o cliente, mano!");
				
				String msg = is.readUTF();
				System.out.println("(Client)Mensagem recebida: " + msg);
				
				is.close();		
				os.close();
				
				client.close();
				
			}catch (UnknownException e) {}						
			
		} catch (IOException e) { e.printStackTrace(); }
	}
}
