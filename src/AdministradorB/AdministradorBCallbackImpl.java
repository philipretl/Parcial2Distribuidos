/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradorB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sop_rmi.GestionClienteImpl;
import sop_rmi.GestionClienteInt;
import sop_rmi.AdministradorBCallbackInt;

/**
 *
 * @author Mauricio
 */
public class AdministradorBCallbackImpl extends UnicastRemoteObject implements AdministradorBCallbackInt{
    
    private GuiAdminB guiA;

    public AdministradorBCallbackImpl(GuiAdminB guiA) throws RemoteException {
        super();
        this.guiA=guiA;
    }


    @Override
    public void notificarIngresoServidor(String datosUsuarioIngreso) throws RemoteException {
        String cadena="Ingreso:"+datosUsuarioIngreso;
        guiA.fijarCambios(cadena);
    }
    
}
