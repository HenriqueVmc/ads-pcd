package pct;

import java.util.Random;

public class Comprador implements Runnable {

    private static Random generator = new Random();
    private Buffer carrinho;
    private Buffer itens;
    private Buffer esteira;
    MeusItens meusItens;
    
    public Comprador(Buffer carrinho, Buffer itens, Buffer esteira) {
        this.carrinho = carrinho;
        this.itens = itens;
        this.esteira = esteira;
        meusItens = new MeusItens();
    }

    public void run() {
        int sum = 0;

        for (int count = 1; count <= 5; count++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                // Pegar Itens                
                meusItens.add(itens.get());
                sum += count;
                System.out.printf("\t%2d\n", sum);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.printf("\n%s\n%s\n", "Producer done producing.", "Terminating Producer.");
    }
}
