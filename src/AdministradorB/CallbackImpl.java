/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradorB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sop_rmi.CallbackInt;

/**
 *
 * @author Mauricio
 */
public class CallbackImpl extends UnicastRemoteObject implements CallbackInt{

    @Override
    public String notificarIngreso() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
