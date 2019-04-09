package pct;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {

		ServerSocket server;
		DataInputStream is;
		DataOutputStream os;

		boolean begin = true;
		
		try {			
			server = new ServerSocket(1111);

			while (begin) {

				System.out.println("\nAguardando cliente...");

				// Aguarda conexão do cliente
				// Não faz nada enquando não for requisitado
				Socket client = server.accept();
				System.out.println("\nconectado!");

				// Sistema de comunicação (RxT)
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());

				// Definimos quem "fala" primeiro
				// os.writeUTF("Olá eu sou o server, mano!");

				String msg = is.readUTF();
				System.out.println("(Server)Mensagem recebida: " + "'" + msg + "'");

				os.writeUTF("Olá eu sou o server, mano!");

				is.close();
				os.close();

				client.close();				
			}
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
