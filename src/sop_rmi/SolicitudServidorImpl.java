/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioA;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class SolicitudServidorImpl extends UnicastRemoteObject implements SolicitudServidorInt{

    ArrayList<UsuarioA> usuarios;
    
    public void cargarUsuarios(){
        
    }
    
    @Override
    public boolean BuscarUsuario(String codigo) throws RemoteException {
        boolean flag=false;
        for(int i=0;i<usuarios.size();i++){
            if(codigo.equals(usuarios.get(i).getCodigo())){
                flag=true;
            }
        }
        return flag;
    }
    
}
