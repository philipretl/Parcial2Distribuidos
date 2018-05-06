/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import ServidorB.SolicitudServidorImpl;
import AdministradorB.UsuarioB;
import DAO.ImplTextoUsuarioB;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio
 */
public class GestionClienteImpl extends UnicastRemoteObject implements GestionClienteInt {
    
    private int semaforo;
    private ArrayList<UsuarioB> usuarios;
    
    public GestionClienteImpl() throws RemoteException{
        super();
        semaforo=0;
        usuarios=new ArrayList<>();
    }

    @Override
    public boolean IngresoUsuario(String codigo) throws RemoteException {
        
        boolean flag=false;
        if(semaforo==0){
            SolicitudServidorImpl ss=new SolicitudServidorImpl();
            flag=ss.BuscarUsuario(codigo);
            semaforo=1;
        }else{
            try {
                cargarUsuarios();
            } catch (IOException ex) {
                Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int i = 0; i < usuarios.size(); i++) {
                if(codigo.equals(usuarios.get(i).getCodigo())){
                    flag=true;
                }
            }
        }
        
        return flag;
    }

    @Override
    public boolean SalidaUsuario(String codigo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioB usuariosB=new ImplTextoUsuarioB();
        usuarios=usuariosB.getUsuarios();
    }
}
