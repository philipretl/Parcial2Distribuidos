/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import AdministradorA.*;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface ServidorAInt extends Remote{
    boolean AccesoAdministrador(AdministradorADTO adminA) throws RemoteException;
    boolean RegistrarUsuario(UsuarioADTO user) throws RemoteException;
    boolean ModificarUsuario(String viejo,UsuarioADTO user) throws RemoteException;
    boolean BorrarUsuario(String codigo) throws RemoteException;
    UsuarioADTO solicitarUsuario(String codigo) throws RemoteException;
    int buscarUsuario(String codigo) throws RemoteException;
    ArrayList<UsuarioADTO> consultarUsuarios() throws RemoteException;
    boolean modificarCredenciales(String antiguo,String login, String pass, int opcion) throws RemoteException;
}
