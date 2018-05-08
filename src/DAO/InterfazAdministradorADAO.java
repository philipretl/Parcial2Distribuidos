/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.AdministradorADTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazAdministradorADAO {
    
    public ArrayList<AdministradorADTO> getAdministradores() throws FileNotFoundException, IOException;
    public void guardarAdministradores(ArrayList<AdministradorADTO> administradores) throws IOException;
    
}
