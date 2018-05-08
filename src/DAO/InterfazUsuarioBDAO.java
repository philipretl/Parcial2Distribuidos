/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.UsuarioADTO;
import AdministradorB.UsuarioBDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazUsuarioBDAO {
    public ArrayList<UsuarioBDTO> getUsuarios() throws FileNotFoundException, IOException;
    public void guardarUsuarios(ArrayList<UsuarioBDTO> usuarios) throws IOException;
}
