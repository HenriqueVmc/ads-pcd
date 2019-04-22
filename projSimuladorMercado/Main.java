package pct;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService application = Executors.newFixedThreadPool(3);
        SimpleDateFormat horas = new SimpleDateFormat("HH:mm:ss");        
        Mercado mercadinho = new Mercado();

        Buffer carrinho = new BufferCarrinhoCompras();
        Buffer itens = new BufferItensMercado(mercadinho);
        Buffer esteira = new BufferEsteira();
        Buffer rampa = new BufferRampaItensCaixa();

        System.out.println("\n--- Iniciando ---\n");

        try {
            application.execute(new Comprador(carrinho, itens, esteira));
            application.execute(new Caixa(esteira, rampa));
            application.execute(new EuSemFaculdade(rampa));

        } // end try
        catch (Exception exception) {
            exception.printStackTrace();
        } // end catch
                        
        application.shutdown(); // terminate application when threads end       
    }
}