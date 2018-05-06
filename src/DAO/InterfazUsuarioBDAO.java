/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorB.UsuarioB;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazUsuarioBDAO {
    public ArrayList<UsuarioB> getUsuarios();
    public void guardarUsuario(UsuarioB usuario);
    public void borrarUsuario(String codigo);
}
