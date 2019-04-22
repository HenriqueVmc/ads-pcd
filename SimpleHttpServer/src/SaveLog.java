
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveLog extends Thread {

    private LinkedBlockingQueue<String> linked;
    private String fileName, message;
    //private FileOutputStream fOut;
    //private File file;
    private int cont = 0;
    private Calendar dataEvento;

    public SaveLog(LinkedBlockingQueue<String> linked, String logFileName) {

            this.linked = linked;
            this.fileName = "/home/henrique/NetBeansProjects/projSimpleServer/SimpleHttpServer/nbproject/"+logFileName;
            this.dataEvento = Calendar.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            writeFile();
            cont++;
        }
    }

    private void writeFile() {
               
        try {
            File file = new File(this.fileName);
            FileOutputStream fOut = new FileOutputStream(file);
            
            try {
                try {

                    this.message = linked.take();
                    String numMessage = Integer.toString(cont);
                    String formatMessage = numMessage + " - " + message + " ( " + dataEvento.getTime().toString() + " )\n";
                    byte[] text = formatMessage.getBytes();

                    fOut.write(text);
                    fOut.flush();
                    fOut.close();
                }
                catch (IOException ex) {}
            }
            catch (InterruptedException ex) {}
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro> "+ex.getMessage());
        }       
    }
}
