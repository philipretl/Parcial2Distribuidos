/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorB.AdministradorBDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public interface InterfazAdministradorBDAO {
    public ArrayList<AdministradorBDTO> getAdministradores() throws FileNotFoundException, IOException;
    public void guardarAdministradores(ArrayList<AdministradorBDTO> administradores) throws IOException;
}
