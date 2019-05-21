package pct;

import java.rmi.Remote;
import java.rmi.RemoteException;

// INTERFACES REMOTAS EXTENDEM 'REMOTE'
public interface IExemploRMI extends Remote {

	// DEVE TER O TRATAMENTO DE EXCESSAO
	public String sayParada(String who)throws RemoteException;
}
