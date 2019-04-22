package pct;

// --- Mapeamento do projeto --- //

// Itens de Compra -> Buffer(?)
//				- get Itens
//				- set Itens
//

// Carrinho -> Buffer (?)
// 				- get Carrinho
//				- set Carrinho
//

// Comprador -> Consumidor (Thread)
//				- get Carrinho
//				- get Itens
//				- set Carrinho
//

// Esteira -> Buffer
//				- set Itens
//				- get Itens
//

// Caixa -> Produtor (Thread)
// 				- get Esteira
//				- set Empacotamento
//

// Rampa de Empacotametno -> Buffer
//				- set Itens -> Caixa
//				- get Itens -> Empacotador
//

// Empacotador -> Consumidor(Thread)
//				- get Rampa
//				- set Sacola
//

// --- CENÁRIO --- //
// Comprador -> getCarrinho
// Comprador -> getItens
// Comprador -> setCarrinho(Itens)
// Comprador/Carrinho -> setEsteira(Itens)
// Caixa -> getEsteira
// Caixa -> setRampa(Itens)
// --- Valor Total ---
// Empacotador -> getRampa
// Empacotador -> setSacola


// Teremos Threads Produtoras, Consumidoras e Buffers :
