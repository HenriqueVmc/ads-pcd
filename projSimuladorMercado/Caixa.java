package pct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Caixa implements Runnable {

    private static Random generator = new Random();
    private Buffer esteira;
    private Buffer rampa;
    private ItensMercado item;
    private MinhaCompra compra;
    private String dataFormatada;
    private SimpleDateFormat horas;
    
    public Caixa(Buffer esteira, Buffer rampa) {
        this.esteira = esteira;
        this.rampa = rampa;
        this.compra = new MinhaCompra();
        this.horas = new SimpleDateFormat("HH:mm:ss");
    }

    public void run() {

        try {            
            for (int i = 0; i < 10; i++) {
                Thread.sleep(generator.nextInt(3000));

                // Pegar Itens da esteira                
                item = esteira.get(i);

                dataFormatada = horas.format(Calendar.getInstance().getTime());
                System.out.println("Caixa: [" + dataFormatada + "] Passando item " + item.getNome() + "");

                // Realizar Calculos
                compra.setItens(item);
                compra.setValorTotal(item.getValor());
                compra.setNotaFiscal("ID: " + i + " | " + item.getNome() + ", R$ " + item.getValor() + "\n");

                // Colocar na Rampa
                rampa.set(item);

                // Fluxo de Pagamento
            }

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.printf("\n");
    }
}
