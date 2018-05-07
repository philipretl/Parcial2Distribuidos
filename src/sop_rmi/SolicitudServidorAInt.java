/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioA;
import java.rmi.RemoteException;

/**
 *
 * @author philipretl
 */
public interface SolicitudServidorAInt {
    UsuarioA solicitarUsuario(String codigo) throws RemoteException;
}
