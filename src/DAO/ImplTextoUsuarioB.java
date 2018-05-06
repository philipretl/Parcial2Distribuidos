/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorB.UsuarioB;
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
public class ImplTextoUsuarioB implements InterfazUsuarioBDAO{

    @Override
    public ArrayList<UsuarioB> getUsuarios() throws FileNotFoundException, IOException {
         ArrayList<UsuarioB> usuarios=new ArrayList<>();
        UsuarioB usr;
        String cadena;
        
        FileReader f = new FileReader("usuarios.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] split=cadena.split("/");
            usr=new UsuarioB(split[0],split[1],split[2],split[3],split[4],split[5]);
            usuarios.add(usr);
            //System.out.println(cadena);
        }
        b.close();
        
        return usuarios;
    }

    @Override
    public void guardarUsuarios(ArrayList<UsuarioB> usuarios) throws IOException {
        File archivo = new File("usuarios.txt");
        BufferedWriter bw;
        String linea;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"/"+usuarios.get(i).getHora()+"/"+usuarios.get(i).getFecha()+"/";
                bw.write(linea);
            }
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"/"+usuarios.get(i).getHora()+"/"+usuarios.get(i).getFecha()+"/";
                bw.write(linea);
            }
        }
        bw.close();
    }

    
    
}
