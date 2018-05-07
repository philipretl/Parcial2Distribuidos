
package servidorB;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class UtilidadesRegistroSB
{       
        //static ConexionSB gui;
        
	public static void arrancarNS(int numPuertoRMI,ConexionSB gui) throws RemoteException 
	{
          //  UtilidadesRegistroSB.gui=gui;
		try
		{
                        
			Registry registro = LocateRegistry.getRegistry(numPuertoRMI);  
            String[] nombresLigados= registro.list();
                        
            gui.consola("El registro se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI); 
			System.out.println("Nombres registrados");
			for(String nombreRegistrado: nombresLigados)
			{
				System.out.println("nombre: " + nombreRegistrado);
			}
		}
		catch(RemoteException e)
		{
			gui.consola("El registro RMI no se localizó en el puerto: " + numPuertoRMI);
			
			Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
			gui.consola("El registro se ha creado en el puerto: " + numPuertoRMI);
		}
		
	}
        
        	
	public static void RegistrarObjetoRemoto(Remote objetoRemoto, String dirIP, int numPuerto, String nombreObjeto,ConexionSB gui)
	{
		String UrlRegistro = "rmi://"+dirIP+":"+numPuerto+"/"+nombreObjeto;
		try
		{
			Naming.rebind(UrlRegistro, objetoRemoto);
			gui.consola("Se realizo el registro con la direccion: " +UrlRegistro);
			gui.consola("Esperando peticiones ...");
		} catch (RemoteException e)
		{
			gui.consola("Error en el registro del objeto remoto");
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			gui.consola("Error url inválida");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
