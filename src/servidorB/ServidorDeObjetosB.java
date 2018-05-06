/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorB;


import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sop_rmi.SolicitudServidorInt;
//import sop_rmi.ServidorUsuariosImpl;

public class ServidorDeObjetosB
    
{
    
    public static void main(String args[]) throws RemoteException
    {
                 
        
        String direccionIpRMIRegistry =  args[0]; 
        int numPuertoRMIRegistry = Integer.parseInt(args[1]);		
     
      //  ServidorUsuariosImpl objRemoto = new ServidorUsuariosImpl();        
        
        try
        {
                    
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
    //       UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ServidorUsuarios");           
      
	    } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
    
    
}
