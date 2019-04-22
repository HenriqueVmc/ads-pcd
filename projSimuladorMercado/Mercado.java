package pct;

import java.util.ArrayList;

public class Mercado {

    private ArrayList<ItensMercado> estoque;
    
    ItensMercado item1 = null;
    ItensMercado item2 = null;
    ItensMercado item3 = null;
    ItensMercado item4 = null;
    ItensMercado item5 = null;
    ItensMercado item6 = null;
    ItensMercado item7 = null;
    ItensMercado item8 = null;
    ItensMercado item9 = null;
    ItensMercado item10 = null;
    
    ItensMercado itemEsgotado = null;
    
    public Mercado() {
        item1 = new ItensMercado();
        item2 = new ItensMercado();
        item3 = new ItensMercado();
        item4 = new ItensMercado();
        item5 = new ItensMercado();
        item6 = new ItensMercado();
        item7 = new ItensMercado();
        item8 = new ItensMercado();
        item9 = new ItensMercado();
        item10 = new ItensMercado();
        
        itemEsgotado = new ItensMercado();
                
        addEstoque();
    }

    public ItensMercado getProduto(int indice) {
        
        if(indice > estoque.size())
            indice = estoque.size() - 1;
        
        if(indice >= 0){
            ItensMercado itemSelecionado = estoque.get(indice);

            estoque.remove(indice);
            return itemSelecionado;            
        }
        
        return itemEsgotado;
    }

    public ArrayList<ItensMercado> getAll() {
        return estoque;
    }

    public int qtdEstoque() {
        return estoque.size();
    }
        
    public void addProduto(ItensMercado item) {
        this.estoque.add(item);
    }

    private void addEstoque() {
        estoque = new ArrayList<ItensMercado>();
        
        item1.setNome("Arroz");
        item1.setDescricao("Arroz Camil tipo 1 - 5kg");
        item1.setValor(18);
        //item1.setQuantidade(10);                
        
        item2.setNome("Feijão");
        item2.setDescricao("Feijão Camil Carioca - 1kg");
        item2.setValor(15);
        //item2.setQuantidade(16);
        
        item3.setNome("Açucar");
        item3.setDescricao("Açucar União - 1kg");
        item3.setValor(7);
        //item3.setQuantidade(20);
        
        item4.setNome("Sal");
        item4.setDescricao("Sal Cisne - 1kg");
        item4.setValor(5);
        //item4.setQuantidade(50);
        
        item5.setNome("Farinha de trigo");
        item5.setDescricao("Farinha de trigo Dona Benta - 1kg");
        item5.setValor(5);
        //item5.setQuantidade(70);
        
        item6.setNome("Achocolatado em pó");
        item6.setDescricao("Achocolatado em pó Toddy - 500g");
        item6.setValor(8);
        //item6.setQuantidade(40);
        
        item7.setNome("Leite");
        item7.setDescricao("Leite Paulista integral - 1L");
        item7.setValor(4);
        //item7.setQuantidade(40);
        
        item8.setNome("Refrigerante");
        item8.setDescricao("Refrigerante Convenção - 2L");
        item8.setValor(2.50);
        //item8.setQuantidade(80);
        
        item9.setNome("Macarrão");
        item9.setDescricao("Macarrão espaguete Renata - 500g");
        item9.setValor(3);
        //item9.setQuantidade(75);
                
        item10.setNome("Molho de tomate");
        item10.setDescricao("Molho de tomate Quero - 340g");
        item10.setValor(3.50);
        //item10.setQuantidade(67);
        
        itemEsgotado.setNome("Item Esgotado!");
        itemEsgotado.setDescricao("...");
        itemEsgotado.setValor(0);
        //itemEsgotado.setQuantidade(0);
        
        addProduto(item1);
        addProduto(item2);
        addProduto(item3);
        addProduto(item4);
        addProduto(item5);
        addProduto(item6);
        addProduto(item7);
        addProduto(item8);
        addProduto(item9);
        addProduto(item10);  
        
        addProduto(itemEsgotado);  
    }
}