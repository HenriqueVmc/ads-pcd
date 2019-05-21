package projCalculadoraRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args){
		
		String host = "localhost";
		String service = "operacoesService";
		
		try {
						
			Registry r = LocateRegistry.createRegistry(1099);
			System.setProperty("java.rmi.server.hostname", "10.61.2.144");
			
			IOperacoes remote = new Operacoes();
						
			Naming.rebind("rmi://"+host+"/"+service, remote);
			
			System.out.println("Server is running");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	
}
