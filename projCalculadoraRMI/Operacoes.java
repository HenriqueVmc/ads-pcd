package projCalculadoraRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operacoes extends UnicastRemoteObject implements IOperacoes {

	protected Operacoes() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int somar(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1 + n2;
	}

	@Override
	public int subtrair(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1 - n2;
	}

	@Override
	public double multiplicar(double n1, double n2) {
		// TODO Auto-generated method stub
		return n1 * n2;
	}

	@Override
	public double dividir(double n1, double n2) {
		// TODO Auto-generated method stub
		return (n1 > 0 && n2 > 0) ? n1 / n2 : 0;
	}

}
