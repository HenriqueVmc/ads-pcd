package pct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// PADRAO PARA APLICACOES RMI:
// ONDE FICARA A IMPLEMENTACAO DO OBJ REMOTO

public class ExemploImpl extends UnicastRemoteObject implements IExemploRMI{

	
	protected ExemploImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayParada(String who) throws RemoteException {
		// TODO Auto-generated method stub
		return "Eae mano "+ who +", suave?";
	}

}
