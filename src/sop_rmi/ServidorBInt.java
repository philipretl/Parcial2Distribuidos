/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorB.AdministradorBDTO;
import AdministradorB.UsuarioBDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface ServidorBInt extends Remote {
    boolean AccesoAdministrador(AdministradorBDTO adminB) throws RemoteException;
    ArrayList<UsuarioBDTO> ConsultarUsuariosIngresados() throws RemoteException;
    boolean modificarCredenciales(String antiguo,String login, String pass, int opcion) throws RemoteException;
    void registrarCallback(AdministradorBCallbackInt objcllbck) throws RemoteException;
}
