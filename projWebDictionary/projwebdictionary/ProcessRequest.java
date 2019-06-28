package projwebdictionary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/*
 * Criar mensagem de log contendo:
 * (1) IP requisitante
 * (2) Arquivo solicitado
 * (3) Cod/status
 * 
 * Ex: 127.0.0.1 - GET / HTTP/1.1 - 200 OK - (Nome da thread que atendeu)
 */
public class ProcessRequest implements Runnable {

    private Socket client;
    private IBuffer cacheTraducoes;

    public ProcessRequest(Socket client, IBuffer cacheTraducoes) throws Exception {
        this.client = client;
        this.cacheTraducoes = cacheTraducoes;
    }

    public void process() throws Exception {

        DataInputStream is;
        DataOutputStream os;

        try {

            is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());

            String msg = is.readUTF();
            System.out.println("(Server)Mensagem recebida: " + "'" + msg + "'");
            
            Traducao cacheResult = cacheTraducoes.get(msg);
            Traducao result = (cacheResult == null) ? (new TradutorService(msg, cacheTraducoes).traduzir()) : cacheResult;
                                                
            os.writeUTF(result.traducao);
            
            is.close();
            os.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception ex) { }
    }
}
