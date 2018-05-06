/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.UsuarioA;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazUsuarioADAO {
    
    public ArrayList<UsuarioA> getUsuarios() throws FileNotFoundException, IOException;
    public void guardarUsuarios(ArrayList<UsuarioA> usuarios) throws IOException;
    
}
