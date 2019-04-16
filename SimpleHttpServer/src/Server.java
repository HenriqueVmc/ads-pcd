import java.net.*;

/*
 * O que servidor irah fazer:
 * (1) deverah aceitar cada conexao (isto estah implementado)
 * (2) deverah possuir um pool estatico de threads para atendimento
 * (3) devarah delegar cada atendimento para uma thread
 */


public class Server {
	
	public static void main(String args[]) throws Exception {
		ServerSocket serverSocket = new ServerSocket(1234);
		boolean cont = true;
		while (cont) {
			Socket inSoc = serverSocket.accept();
			HttpRequest request = new HttpRequest(inSoc);
			request.process();
		}
		serverSocket.close();
	}
}