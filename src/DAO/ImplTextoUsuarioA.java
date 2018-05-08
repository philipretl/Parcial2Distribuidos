/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import AdministradorA.UsuarioADTO;
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
public class ImplTextoUsuarioA implements InterfazUsuarioADAO{

    @Override
    public ArrayList<UsuarioADTO> getUsuarios() throws FileNotFoundException, IOException{
        
        ArrayList<UsuarioADTO> usuarios=new ArrayList<>();
        UsuarioADTO usr;
        String cadena;
        
        FileReader f = new FileReader("usuariosA.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] split1=cadena.split("#");
            for (int i = 0; i < split1.length; i++) {
                String[] split2 = split1[i].split("/");
                usr=new UsuarioADTO(split2[0],split2[1],split2[2],split2[3]);
                usuarios.add(usr);
            }
            
            //System.out.println(cadena);
        }
        b.close();
        
        return usuarios;
    }
        

    @Override
    public void guardarUsuarios(ArrayList<UsuarioADTO> usuarios) throws IOException{
        
        File archivo = new File("usuariosA.txt");
        BufferedWriter bw;
        String linea;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"#";
                bw.write(linea);
            }
            
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i< usuarios.size(); i++) {
                linea=usuarios.get(i).getNombre()+"/"+usuarios.get(i).getApellidos()+"/"+usuarios.get(i).getRol()+"/"+usuarios.get(i).getCodigo()+"#";
                bw.write(linea);
            }
        }
        bw.close();
    }

    
}
