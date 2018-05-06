/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.AdministradorA;
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
public class ImplTextoAdministradorA implements InterfazAdministradorADAO{

    @Override
    public ArrayList<AdministradorA> getAdministradores() throws FileNotFoundException, IOException {
        ArrayList<AdministradorA> administradores=new ArrayList<>();
        AdministradorA adm;
        String cadena;
        
        FileReader f = new FileReader("administradoresA.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] split=cadena.split("/");
            adm=new AdministradorA(split[0],split[1]);
            administradores.add(adm);
            //System.out.println(cadena);
        }
        b.close();
        
        return administradores;
    }

    @Override
    public void guardarAdministradores(ArrayList<AdministradorA> administradores) throws IOException {
        File archivo = new File("administradoresA.txt");
        BufferedWriter bw;
        String linea;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< administradores.size(); i++) {
                linea=administradores.get(i).getLogin()+"/"+administradores.get(i).getClave()+"/";
                bw.write(linea);
            }
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< administradores.size(); i++) {
                linea=administradores.get(i).getLogin()+"/"+administradores.get(i).getClave()+"/";
                bw.write(linea);
            }
        }
        bw.close();
    }
    
}
