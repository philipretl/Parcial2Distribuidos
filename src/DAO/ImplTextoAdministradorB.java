/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorB.AdministradorBDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class ImplTextoAdministradorB implements InterfazAdministradorBDAO{

    @Override
    public ArrayList<AdministradorBDTO> getAdministradores() throws FileNotFoundException, IOException {
        ArrayList<AdministradorBDTO> administradores=new ArrayList<>();
        AdministradorBDTO adm;
        String cadena;
        
        FileReader f = new FileReader("administradoresB.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] split1=cadena.split("#");
            for (int i = 0; i < split1.length; i++) {
                String[] split2 = split1[i].split("/");
            adm=new AdministradorBDTO(split2[0],split2[1]);
            administradores.add(adm);
            //System.out.println(cadena);
            }
        }
        b.close();
        
        return administradores;
    }

    @Override
    public void guardarAdministradores(ArrayList<AdministradorBDTO> administradores) throws IOException {
        File archivo = new File("administradoresB.txt");
        BufferedWriter bw;
        String linea;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< administradores.size(); i++) {
                linea=administradores.get(i).getLogin()+"/"+administradores.get(i).getClave()+"#";
                bw.write(linea);
            }
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< administradores.size(); i++) {
                linea=administradores.get(i).getLogin()+"/"+administradores.get(i).getClave()+"#";
                bw.write(linea);
            }
        }
        bw.close();
    
    }

    
}
