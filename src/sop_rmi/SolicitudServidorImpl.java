/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioA;
import DAO.ImplTextoUsuarioA;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorA.ConexionSA;
import sop_rmi.SolicitudServidorInt;

/**
 *
 * @author Mauricio
 */
public class SolicitudServidorImpl extends UnicastRemoteObject implements SolicitudServidorInt{
    ConexionSA gui;

    public SolicitudServidorImpl(ConexionSA gui) throws RemoteException{
        super();
        this.gui=gui;
    }
    
    @Override
    public UsuarioA soliciarUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverGestion: Solicitar Usuario ");
        UsuarioA usr = null;
        ArrayList<UsuarioA> usuarios = new ArrayList<>();
        ImplTextoUsuarioA usuariosA=new ImplTextoUsuarioA();
        try {
            usuarios=usuariosA.getUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(SolicitudServidorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getCodigo().equals(codigo)){
               usr=new UsuarioA(usuarios.get(i).getNombre(),usuarios.get(i).getApellidos(),usuarios.get(i).getRol(),usuarios.get(i).getCodigo());
            }
        }
        return usr;
    }
    
}
