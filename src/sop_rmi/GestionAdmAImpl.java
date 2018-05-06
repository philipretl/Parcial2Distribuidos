/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sop_rmi.*;
import AdministradorA.*;
import DAO.ImplTextoAdministradorA;
import DAO.ImplTextoUsuarioA;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipretl
 */
public class GestionAdmAImpl extends UnicastRemoteObject implements GestionAdmAInt {
    ArrayList<AdministradorA> admins;
    ArrayList<UsuarioA> usuariosA;
    
    ImplTextoUsuarioA txtA;
    ImplTextoAdministradorA txtAdm;
    

    
    public GestionAdmAImpl() throws RemoteException, IOException {
        super();
        admins = new ArrayList();
        usuariosA = new ArrayList();
        txtA=new ImplTextoUsuarioA();
        txtAdm=new ImplTextoAdministradorA();
        rellenar();
        
    }
    
        
    public void rellenar() throws IOException{ // borrar esta mierda
        usuariosA= txtA.getUsuarios();
        admins=txtAdm.getAdministradores();
        
        /*AdministradorA admin1= new AdministradorA("aaaaaaaa","aaaaaaaa");
        AdministradorA admin2= new AdministradorA("bbbbbbbb","bbbbbbbb");
        admins.add(admin1);
        admins.add(admin2);
        txtAdm.guardarAdministradores(admins);
        */
        
        /*UsuarioA user1 = new UsuarioA("Carlos","Perez","Administrativo","ccccccccc");
        UsuarioA user2 = new UsuarioA("Andres","Vega","Estudiante","dddddddd");
        UsuarioA user3 = new UsuarioA("Mauricio","Manzano","Profesor","eeeeeeee");
        usuariosA.add(user1);
        usuariosA.add(user2);
        usuariosA.add(user3);
        */
        //txtA.guardarUsuarios(usuariosA);
        
        /*for (int i = 0; i < usuariosA.size(); i++) {
            System.out.print("usuario: "+usuariosA.get(i).getNombre()+" - "+usuariosA.get(i).getApellidos());
            
        }*/
        
    }
    
    @Override
    public boolean AccesoAdministrador(AdministradorA adminA) throws RemoteException {
        //System.out.println("$ server: Acceso a administrador" + adminA.getLogin() + adminA.getClave());
        boolean flag = false;
        for (int i = 0; i < admins.size(); i++) {
            if(adminA.getLogin().equals(admins.get(i).getLogin()) && adminA.getClave().equals(admins.get(i).getClave())){
                flag=true;
            }
        }
        return flag;
    }

    
    @Override
    public boolean ModificarUsuario(UsuarioA user) throws RemoteException {
       
       boolean flag=false;
       int pos;
       pos=buscarUsuario(user.getCodigo());
        if(pos!=-1){
            usuariosA.set(pos,user);
            flag=true;
        }
        try {
            txtA.guardarUsuarios(usuariosA);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdmAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return flag;
    }

    @Override
    public boolean BorrarUsuario(String codigo) throws RemoteException {
        boolean flag=false;
        int pos;
        
        pos=buscarUsuario(codigo);
        if(pos!=-1){
             usuariosA.remove(pos);
             flag=true;
        }  
       
        try {
            txtA.guardarUsuarios(usuariosA);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdmAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public boolean RegistrarUsuario(UsuarioA user) throws RemoteException {
        boolean flag=false;
        int pos;
        
        pos=buscarUsuario(user.getCodigo());
        if(pos==-1){
             usuariosA.add(user);
             flag=true;
        }
        
        try {
            txtA.guardarUsuarios(usuariosA);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdmAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return flag;
    }
    
    @Override
    public UsuarioA soliciarUsuario(String codigo) throws RemoteException {
        UsuarioA user=null;
        int pos;
        pos=buscarUsuario(codigo);
        if(pos!=-1){
            user= new UsuarioA(usuariosA.get(pos).getNombre(),usuariosA.get(pos).getApellidos(),usuariosA.get(pos).getRol(),usuariosA.get(pos).getCodigo());
        
        }
        
        return user;
        
    }
    @Override
    public int buscarUsuario(String codigo){
        int pos=-1;
        for (int i = 0; i <usuariosA.size(); i++) {
            if(usuariosA.get(i).getCodigo().equals(codigo)){
                pos=i;
                break;
            }
        }
    
        return pos;
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
