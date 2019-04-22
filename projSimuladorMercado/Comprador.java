package pct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.xml.crypto.Data;

public class Comprador implements Runnable {

    private static Random generator = new Random();
    private Buffer carrinho;
    private Buffer itens;
    private Buffer esteira;
    MeusItens meusItens;
    private String dataFormatada;
    private SimpleDateFormat horas;
    
    public Comprador(Buffer carrinho, Buffer itens, Buffer esteira) {
        this.carrinho = carrinho;
        this.itens = itens;
        this.esteira = esteira;
        this.meusItens = new MeusItens();                
        horas = new SimpleDateFormat("HH:mm:ss");
    }

    public void run() {
        dataFormatada = horas.format(Calendar.getInstance().getTime());
        System.out.println("Comprador: ["+ dataFormatada +"] Iniciando Compras");
        try {
            
            for (int i = 0; i < 10; i++) {
                Thread.sleep(generator.nextInt(1000));
                // Pegar Itens
                ItensMercado item = itens.get(i);
                meusItens.add(item);
                
                // Colocar no carrinho
                carrinho.set(item);
                
                dataFormatada = horas.format(Calendar.getInstance().getTime());
                System.out.println("Comprador: ["+ dataFormatada +"] Colocando item "+item.getNome()+" no carrinho");
            }
            
            System.out.println("Comprador: ["+ dataFormatada +"] Indo para o caixa");
            
            for (int i = 0; i < 10; i++) {
                // Inserir itens na esteira
                ItensMercado item = carrinho.get(i);
                esteira.set(item);
                
                dataFormatada = horas.format(Calendar.getInstance().getTime());
                System.out.println("Comprador: ["+ dataFormatada +"] Depositando item "+item.getNome()+" na esteira");
            }
            System.out.println("Comprador: ["+ dataFormatada +"] Pagando conta de R$ "+meusItens.getValorTotal());

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.printf("\n");
    }
}
