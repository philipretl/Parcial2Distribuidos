
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
        static ConexionSB gui;
        
	public static void arrancarNS(int numPuertoRMI,ConexionSB gui) throws RemoteException 
	{
            UtilidadesRegistroSB.gui=gui;
		try
		{
                        
			Registry registro = LocateRegistry.getRegistry(numPuertoRMI);  
            String[] nombresLigados= registro.list();
                        
            //System.out.println("El registro se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI); 
		UtilidadesRegistroSB.gui.consola("El registro se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI);
                    //System.out.println("Nombres registrados");
                    UtilidadesRegistroSB.gui.consola("Nombres registrados");
			for(String nombreRegistrado: nombresLigados)
			{
				//System.out.println("nombre: " + nombreRegistrado);
                                UtilidadesRegistroSB.gui.consola("nombre: " + nombreRegistrado);
			}
		}
		catch(RemoteException e)
		{
			//System.out.println("El registro RMI no se localizó en el puerto: " + numPuertoRMI);
			UtilidadesRegistroSB.gui.consola("El registro RMI no se localizó en el puerto: " + numPuertoRMI);
			Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
			//System.out.println("El registro se ha creado en el puerto: " + numPuertoRMI);
                        UtilidadesRegistroSB.gui.consola("El registro se ha creado en el puerto: " + numPuertoRMI);
		}
		
	}
        
        	
	public static void RegistrarObjetoRemoto(Remote objetoRemoto, String dirIP, int numPuerto, String nombreObjeto)
	{
		String UrlRegistro = "rmi://"+dirIP+":"+numPuerto+"/"+nombreObjeto;
		try
		{
			Naming.rebind(UrlRegistro, objetoRemoto);
			//System.out.println("Se realizo el registro con la direccion: " +UrlRegistro);
                        UtilidadesRegistroSB.gui.consola("Se realizo el registro con la direccion: " +UrlRegistro);
			//System.out.println("Esperando peticiones ...");
                        UtilidadesRegistroSB.gui.consola("Esperando peticiones ...");
		} catch (RemoteException e)
		{
			//System.out.println("Error en el registro del objeto remoto");
                        UtilidadesRegistroSB.gui.consola("Error en el registro del objeto remoto");
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			//System.out.println("Error url inválida");
                        UtilidadesRegistroSB.gui.consola("Error url inválida");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
