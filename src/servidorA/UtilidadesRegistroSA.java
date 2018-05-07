
package servidorA;

import servidorB.*;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class UtilidadesRegistroSA
{       
        static ConexionSA gui;
        
	public static void arrancarNS(int numPuertoRMI,ConexionSA gui) throws RemoteException 
	{
            UtilidadesRegistroSA.gui=gui;
		try
		{
                        
			Registry registro = LocateRegistry.getRegistry(numPuertoRMI);  
            String[] nombresLigados= registro.list();
                        
            //System.out.println("El registro se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI); 
		UtilidadesRegistroSA.gui.consola("El registro se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI);
                    //System.out.println("Nombres registrados");
                    UtilidadesRegistroSA.gui.consola("Nombres registrados");
			for(String nombreRegistrado: nombresLigados)
			{
				//System.out.println("nombre: " + nombreRegistrado);
                                UtilidadesRegistroSA.gui.consola("nombre: " + nombreRegistrado);
			}
		}
		catch(RemoteException e)
		{
			//System.out.println("El registro RMI no se localizó en el puerto: " + numPuertoRMI);
			UtilidadesRegistroSA.gui.consola("El registro RMI no se localizó en el puerto: " + numPuertoRMI);
			Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
			//System.out.println("El registro se ha creado en el puerto: " + numPuertoRMI);
                        UtilidadesRegistroSA.gui.consola("El registro se ha creado en el puerto: " + numPuertoRMI);
		}
		
	}
        
        	
	public static void RegistrarObjetoRemoto(Remote objetoRemoto, String dirIP, int numPuerto, String nombreObjeto)
	{
		String UrlRegistro = "rmi://"+dirIP+":"+numPuerto+"/"+nombreObjeto;
		try
		{
			Naming.rebind(UrlRegistro, objetoRemoto);
			//System.out.println("Se realizo el registro con la direccion: " +UrlRegistro);
                        UtilidadesRegistroSA.gui.consola("Se realizo el registro con la direccion: " +UrlRegistro);
			//System.out.println("Esperando peticiones ...");
                        UtilidadesRegistroSA.gui.consola("Esperando peticiones ...");
		} catch (RemoteException e)
		{
			//System.out.println("Error en el registro del objeto remoto");
                        UtilidadesRegistroSA.gui.consola("Error en el registro del objeto remoto");
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			//System.out.println("Error url inválida");
                        UtilidadesRegistroSA.gui.consola("Error url inválida");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
