package servidor;

import cliente.InterfazCliente;
import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface InterfazServidor extends Remote { 
     public void procesarPunto(int x, int y) throws RemoteException;
     public void conectar(String ip) throws RemoteException;
     public void desconectar(InterfazCliente c) throws RemoteException;
}
