package projwebdictionary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestWebDictionary {

    public static void main(String[] args) {

        try {
            
            ServerSocket serverSocket = new ServerSocket(1234);            
            ExecutorService application = Executors.newFixedThreadPool(20);
            
            IBuffer cacheTraducoes = new BufferTraducoesRecentes();
            
            while (true) {
                
                System.out.println("\nAguardando cliente...");
                Socket inSoc = serverSocket.accept();
                System.out.println("\nconectado!");
                
                ProcessRequest request = new ProcessRequest(inSoc, cacheTraducoes);
                
                application.execute(request);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TestWebDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}