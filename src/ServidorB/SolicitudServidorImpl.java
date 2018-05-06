/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorB;

import AdministradorA.UsuarioA;
import DAO.ImplTextoUsuarioA;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sop_rmi.SolicitudServidorInt;

/**
 *
 * @author Mauricio
 */
public class SolicitudServidorImpl extends UnicastRemoteObject implements SolicitudServidorInt{

    private ArrayList<UsuarioA> usuarios;

    public SolicitudServidorImpl() throws RemoteException{
        super();
    }
    
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioA usuariosA=new ImplTextoUsuarioA();
        usuarios=usuariosA.getUsuarios();
    }
    
    @Override
    public boolean BuscarUsuario(String codigo) throws RemoteException {
        boolean flag=false;
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(SolicitudServidorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<usuarios.size();i++){
            if(codigo.equals(usuarios.get(i).getCodigo())){
                flag=true;
            }
        }
        return flag;
    }
    
}
