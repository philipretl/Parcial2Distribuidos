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
    SolicitudServidorInt srvA;
    
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
    
    public void conexionServidorA(){
        try{
            int numPuertoRMIRegistry=0;
            String direccionIpRMIRegistry="localhost";
            numPuertoRMIRegistry = 2023;
                            
            srvA= (SolicitudServidorInt) cliente.UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry, direccionIpRMIRegistry,"ServidorA");
                            
                            
        }catch(Exception e){
            System.out.println("No se pudo registrar la conexion...");
            System.out.println(e.getMessage());
        }
    }
}
