package projCalculadoraRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperacoes extends Remote{

	int somar(int n1, int n2) throws RemoteException;
	int subtrair(int n1, int n2) throws RemoteException;
	double multiplicar(double n1, double n2) throws RemoteException;
	double dividir(double n1, double n2) throws RemoteException;	
}
