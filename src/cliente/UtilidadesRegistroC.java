package cliente;

import java.rmi.Naming;
import java.rmi.Remote;


public class UtilidadesRegistroC
{   
    
    public static Remote obtenerObjRemoto(int puerto, String dirIP, String nameObjReg)
    {
        String URLRegistro;
        URLRegistro  = "rmi://" + dirIP + ":" + puerto + "/"+nameObjReg;
        try
        {
            return Naming.lookup(URLRegistro);
        }
        catch (Exception e)
        {
            System.out.println("Excepcion en obtencion del objeto remoto"+ e);
            return null;
        }
    }
    
}