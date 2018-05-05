/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mauricio
 */
public interface GestionAdmAInt extends Remote{
    boolean AccesoAdministrador(String login, String clave) throws RemoteException;
    boolean RegistrarUsuario(String nombre,String apellido, String rol,String codigo) throws RemoteException;
    boolean ModificarUsuario(String codigo) throws RemoteException;
    boolean BorrarUsuario(String codigo) throws RemoteException;
}
