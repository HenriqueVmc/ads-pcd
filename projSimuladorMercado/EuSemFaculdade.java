package pct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class EuSemFaculdade implements Runnable {

    private static Random generator = new Random();
    private Buffer rampa;
    private Sacola sacola;
    private String dataFormatada;
    private SimpleDateFormat horas;
    
    public EuSemFaculdade(Buffer rampa) {
        this.rampa = rampa;
        this.sacola = new Sacola();
        this.horas = new SimpleDateFormat("HH:mm:ss");
    }

    public void run() {

        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(generator.nextInt(3000)); 
                
                ItensMercado item = rampa.get(i);
                sacola.setCompra(rampa.get(i));
                
                dataFormatada = horas.format(Calendar.getInstance().getTime());
                System.out.println("Eu sem Facul: [" + dataFormatada + "] Passando item " + item.getNome() + "");                
            }

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.printf("\n");
        String dataFormatada = horas.format(Calendar.getInstance().getTime());        
        System.out.println("Fim da simulação: [" + dataFormatada + "]");
    }
}