/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorB.AdministradorB;
import AdministradorB.UsuarioB;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author philipretl
 */
public class GestionAdminBImpl extends UnicastRemoteObject implements GestionAdmBInt{
    ArrayList<AdministradorB> admins;
    ArrayList<UsuarioB> usuariosB;
    
     public GestionAdminBImpl () throws RemoteException {
        super();
        admins = new ArrayList();
        usuariosB= new ArrayList();
        rellenar();
                
        
    }
    
    
    public void rellenar(){// borrar esta mierda
        AdministradorB admin1= new AdministradorB("aaaaaaaa","aaaaaaaa");
        AdministradorB admin2= new AdministradorB("bbbbbbbb","bbbbbbbb");
        admins.add(admin1);
        admins.add(admin2);

        UsuarioB user1 = new UsuarioB("Carlos","Perez","Administrativo","ccccccccc","1 am","24/02/18");
        UsuarioB user2 = new UsuarioB("Andres","Vega","Estudiante","dddddddd","2 am","24/02/18");
        UsuarioB user3 = new UsuarioB("Mauricio","Manzano","Profesor","eeeeeeee","3 am","24/02/18");
        usuariosB.add(user1);
        usuariosB.add(user2);
        usuariosB.add(user3);
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
    public ArrayList<UsuarioB> ConsultarUsuariosIngresados() throws RemoteException {
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
        
        return flag;
        
        
        
    }


}
