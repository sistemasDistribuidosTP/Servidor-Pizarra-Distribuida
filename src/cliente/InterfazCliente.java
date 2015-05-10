/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface InterfazCliente extends Remote {
    public void pintar(int x, int y, int aSetear) throws RemoteException;
    public void procesarPizarra(int[][] mat) throws RemoteException;
}
