package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PizarraServer{

    public PizarraServer(){    
    }
    
    
    public void iniciarServidor(){

        try { 
            Registry registry = LocateRegistry.createRegistry(1099);
             
            registry.rebind("miPizarra", new PizarraImpl());
            
            System.out.println("Servidor Pizarra iniciado ...");
            
        } catch (Exception e) { 
            System.out.println("Pizarra server iniciado: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    
    }

    public static void main(String args[]) { 
        PizarraServer p = new PizarraServer();
        p.iniciarServidor();
        
    } 
}
