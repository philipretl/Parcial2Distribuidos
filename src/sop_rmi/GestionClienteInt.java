/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorB.UsuarioB;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mauricio
 */
public interface GestionClienteInt extends Remote{
    int ingresoUsuario(String codigo) throws RemoteException;
    int salidaUsuario(String codigo) throws RemoteException;
    UsuarioB consultarUsuarioIngresado() throws RemoteException;
}
