/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorB.UsuarioBDTO;
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
    public ArrayList<UsuarioBDTO> getUsuarios() throws FileNotFoundException, IOException {
         ArrayList<UsuarioBDTO> usuarios=new ArrayList<>();
        UsuarioBDTO usr;
        String cadena;
        
        FileReader f = new FileReader("usuariosB.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] split1=cadena.split("#");
            for (int i = 0; i < split1.length; i++) {
                String[] split2 = split1[i].split("/");
                usr=new UsuarioBDTO(split2[0],split2[1],split2[2],split2[3],split2[4],split2[5]);
                usuarios.add(usr);
            //System.out.println(cadena);
            }
        }
        b.close();
        
        return usuarios;
    }

    @Override
    public void guardarUsuarios(ArrayList<UsuarioBDTO> usuarios) throws IOException {
        File archivo = new File("usuariosB.txt");
        BufferedWriter bw;
        String linea;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"/"+usuarios.get(i).getHora()+"/"+usuarios.get(i).getFecha()+"#";
                bw.write(linea);
            }
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"/"+usuarios.get(i).getHora()+"/"+usuarios.get(i).getFecha()+"#";
                bw.write(linea);
            }
        }
        bw.close();
    }

    
    
}
