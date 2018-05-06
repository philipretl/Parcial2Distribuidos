/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorB.AdministradorB;
import AdministradorB.UsuarioB;
import DAO.ImplTextoAdministradorB;
import DAO.ImplTextoUsuarioB;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipretl
 */
public class GestionAdminBImpl extends UnicastRemoteObject implements GestionAdmBInt{
    ArrayList<AdministradorB> admins;
    ArrayList<UsuarioB> usuariosB;
    ImplTextoUsuarioB txtU;
    ImplTextoAdministradorB txtA;
    
     public GestionAdminBImpl () throws RemoteException, IOException {
        super();
        admins = new ArrayList();
        usuariosB= new ArrayList();
        txtU= new ImplTextoUsuarioB();
        txtA= new ImplTextoAdministradorB();
        rellenar();
                
        
    }
    
    
    public void rellenar() throws IOException{// borrar esta mierda
        /*AdministradorB admin1= new AdministradorB("aaaaaaaa","aaaaaaaa");
        AdministradorB admin2= new AdministradorB("bbbbbbbb","bbbbbbbb");
        admins.add(admin1);
        admins.add(admin2);
        
        txtA.guardarAdministradores(admins);

        UsuarioB user1 = new UsuarioB("Carlos","Perez","Administrativo","ccccccccc","1 am","24/02/18");
        UsuarioB user2 = new UsuarioB("Andres","Vega","Estudiante","dddddddd","2 am","24/02/18");
        UsuarioB user3 = new UsuarioB("Mauricio","Manzano","Profesor","eeeeeeee","3 am","24/02/18");
        usuariosB.add(user1);
        usuariosB.add(user2);
        usuariosB.add(user3);
        
        txtU.guardarUsuarios(usuariosB);
        */
        
        usuariosB=txtU.getUsuarios();
        admins= txtA.getAdministradores();
    }
    
  
    
    @Override
    public boolean AccesoAdministrador(AdministradorB adminB) throws RemoteException {
        System.out.println("$ serverB: Acceso a administrador" + adminB.getLogin() + adminB.getClave());
        boolean flag = false;
        for (int i = 0; i < admins.size(); i++) {
            if(adminB.getLogin().equals(admins.get(i).getLogin()) && adminB.getClave().equals(admins.get(i).getClave())){
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public ArrayList<UsuarioB> ConsultarUsuariosIngresados() {
        try {
            usuariosB=txtU.getUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuariosB;
    }

    @Override
    public boolean modificarCredenciales(String antiguo, String login, String pass, int opcion) throws RemoteException {
        boolean flag=false;
        
        for (int i = 0; i < admins.size(); i++) {
            if(antiguo.equals(admins.get(i).getLogin())){
                
                switch(opcion){
                    case 0:
                        if(login.length()>=8 & login.length()<=15){
                            admins.get(i).setLogin(login); 
                            flag=true;
                        }
                        break;
                    case 1:
                        if(pass.length()>=8 & pass.length()<=15){
                             admins.get(i).setClave(pass);
                             flag=true;
                        }
                       
                        break;
                    case 2:
                        if(pass.length()>=8 & pass.length()<=15 & login.length()>=8 & login.length()<=15){
                            admins.get(i).setLogin(login);
                            admins.get(i).setClave(login);
                            flag=true;
                        }   
                        break;
                }
                
                
                break;
            }
        }
        
        try {
            txtA.guardarAdministradores(admins);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
        
        
        
    }


}
