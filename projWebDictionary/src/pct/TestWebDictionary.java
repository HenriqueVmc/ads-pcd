package pct;

import java.io.IOException;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestWebDictionary {

	public static void main(String[] args) {
		
		ExecutorService application = Executors.newFixedThreadPool(3);				        
        int porta = 5000;
        //String group = "230.0.0.0"; 
                
        try {
        	System.out.println("\n--- Iniciando ---\n");
        	
        	IBuffer cacheTraducoes = new BufferTraducoesRecentes();        	
			MulticastSocket socket = new MulticastSocket(porta);		

			application.execute(new Receiver(socket, cacheTraducoes));

        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
                        
        application.shutdown();
	}
}

/*

	Socket inSoc = serverSocket.accept();
    HttpRequest request = new HttpRequest(inSoc);

	application.execute(request);

*/