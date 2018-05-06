/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.AdministradorA;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazAdministradorADAO {
    
    public ArrayList<AdministradorA> getAdministradores() throws FileNotFoundException, IOException;
    public void guardarAdministradores(ArrayList<AdministradorA> administradores) throws IOException;
    
}
