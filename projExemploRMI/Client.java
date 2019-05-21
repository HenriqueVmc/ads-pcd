package pct;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args){
		
		String service = "rmi://10.61.2.154/HelloWorldService";
		
		// VERIFICA SE EXISTE ALGUM REGISTRO COM ESSA 'URL'
		try {
			
			IExemploRMI remoteRef = (IExemploRMI)Naming.lookup(service);
			System.out.println(remoteRef.sayParada("FUnciona msm?"));
			
		}
		catch (Exception e) { e.printStackTrace(); }		
	}
}
