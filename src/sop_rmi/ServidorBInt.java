/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorB.AdministradorB;
import AdministradorB.UsuarioB;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface GestionAdmBInt extends Remote {
    boolean AccesoAdministrador(AdministradorB adminB) throws RemoteException;
    ArrayList<UsuarioB> ConsultarUsuariosIngresados() throws RemoteException;
    boolean modificarCredenciales(String antiguo,String login, String pass, int opcion) throws RemoteException;
    void registrarCallback(AdministradorBCallbackInt objcllbck) throws RemoteException;
}
