/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Mauricio
 */
public class GestionClienteImpl extends UnicastRemoteObject implements GestionClienteInt {
    

    @Override
    public boolean IngresoUsuario(String codigo) throws RemoteException {
        boolean flag=false;
        
        return flag;
    }

    @Override
    public boolean SalidaUsuario(String codigo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
