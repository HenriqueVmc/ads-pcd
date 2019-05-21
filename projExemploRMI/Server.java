package pct;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args){
		
		// * CLIENTE/SERVIDOR DEVEM POSSUIR MSM 'VERSAO' BINARIA(.class) DA INTERFACE *
		
		// DEVE HAVER UM SERVICO DE REGISTRO DISPONÍVEL
		// RODAR NO TERMINAL: rmiregistry
		String host = "localhost";
		String service = "helloService";
		
		try {
			
			// RODA O RMIREGISTRY POR AQUI:
			Registry r = LocateRegistry.createRegistry(1099);
			System.setProperty("java.rmi.server.hostname", "10.61.2.144");
			
			IExemploRMI remote = new ExemploImpl();
			
			// COMO SE FOSSE UM DNS DO RMI:
			// DEVMOS REGISTRAR AO RMI, NOSSO ENDERECO
			// FAZ O BIDING(VINCULACAO) DA URL COM OBJ:			
			Naming.rebind("rmi://"+host+"/"+service, remote);
			
			System.out.println("Server is running");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
