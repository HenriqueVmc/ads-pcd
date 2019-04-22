
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;


/*
 * O que o logger deve fazer:
 * (1) deverah possuir um buffer compartilhado (sugestao: LinkedBlockingQueue)
 * (2) deverah possuir uma thread para gravacao em arquivo (consumidor)
 * (3) para inserir uma mensagem no log, deve-se utilizar o metodo putMessage (produtor)
 * (4) cada mensagem, ao ser gravada em arquivo, deverah conter o numero (contador) e a hora do evento
 * (5) inclua o que for necessario (metodos e atributos)
 */
public class Logger {

    private static Logger instance = null;
    private final static String logFileName = "serverlog.txt";
    // incluir campos necessarios
    private LinkedBlockingQueue<String> linked;
    private SaveLog saveLog;
    
    // singleton
    public static synchronized Logger getInstance() {

        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private Logger() {        
        // seu codigo
        //ExecutorService application = Executors.newFixedThreadPool( 0 );
        
        linked = new LinkedBlockingQueue<String>();
        saveLog = new SaveLog(linked, logFileName);
        
        //application.execute(saveLog);        
    }

    public String getMessage(){
        
        try {
            return this.linked.take();
        } catch (InterruptedException ex) { }
        
        return null;
    }
    
    public void putMessage(String message) {
        try {
            // seu codigo
            linked.put(message);
            saveLog.start();
        } catch (InterruptedException ex) { }
    }

}
