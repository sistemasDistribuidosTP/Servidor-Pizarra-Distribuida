package servidor;

import cliente.InterfazCliente;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PizarraImpl extends UnicastRemoteObject
    implements InterfazServidor {
    int[][] mat;
    ArrayList<InterfazCliente> lista;
    int x,y;
     Registry registry;
     InterfazCliente cliente; 
   public PizarraImpl() throws RemoteException {
        super();
        mat= new int[500][500];
        lista=new ArrayList<>();
    }

    @Override
    public synchronized void procesarPunto(int x, int y) throws RemoteException {
        try {
            if (mat[x][y] == 1) {
                mat[x][y] = 0;
            } else {
                mat[x][y] = 1;
            }
            System.out.println("Punto (" + x + "," + y + ") recibido!");
            Iterator a = lista.iterator();
            while (a.hasNext()) {
                try {
                    cliente = (InterfazCliente) a.next();

                    cliente.pintar(x, y, mat[x][y]);
                } catch (Exception e) {
                    if (lista.remove(cliente)) {
                        System.out.println("Cliente no disponible, Registro de clientes limpiado");
                        a = lista.iterator();
                    }
                   
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

        }

    }

    @Override
    public void conectar(String ip) throws RemoteException{
        try {
            //Creando el objeto remoto
            
            registry = LocateRegistry.getRegistry(ip, 1098);
            cliente=(InterfazCliente) registry.lookup("Cliente");
            lista.add(cliente);
            
            System.out.println("Objeto cliente registrado ...");
            System.out.println("Clientes conectados: "+lista.size());
            //Enviar la pizarra
            cliente.procesarPizarra(mat);
            
            
        } catch (NotBoundException ex) {
            Logger.getLogger(PizarraImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(PizarraImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void desconectar(InterfazCliente c) throws RemoteException {
        if( lista.remove(c)){
            System.out.println("ID Cliente desconectado");
        
        System.out.println("Clientes conectados: "+lista.size());
        }else
            System.out.println("Cliente quiso desconectarse pero no pudo");
            
        
    }

  
    
}
