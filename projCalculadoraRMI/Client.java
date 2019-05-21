package projCalculadoraRMI;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {	

		String op = "x";
		
		try {

			String service = "rmi://localhost/operacoesService";
			IOperacoes remoteRef = (IOperacoes) Naming.lookup(service);
			
			do{
				int n1 = BuscarValor("primeiro");
				int n2 = BuscarValor("segundo");

				op = BuscarOperacao();

				double result = 0;

				switch (op) {
					case "+":
						result = remoteRef.somar(n1, n2);
						break;
					case "-":
						result = remoteRef.subtrair(n1, n2);
						break;
					case "*":
						result = remoteRef.multiplicar(n1, n2);
						break;
					case "/":
						result = remoteRef.dividir(n1, n2);
						break;
					default:
						break;
				}

				System.out.println("Resultado: " + result);
			
			}while(!op.toLowerCase().equals("x"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String BuscarOperacao() {
		System.out.println("\nDigite a operação [+],[-],[*],[/] ou [x - para sair]: ");		
		return in.next();
	}

	private static int BuscarValor(String string) {
		System.out.println("\nDigite o "+string+" Numero: ");
		return in.nextInt();
	}
}
