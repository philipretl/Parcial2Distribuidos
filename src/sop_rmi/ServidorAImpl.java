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
import servidorA.ConexionSA;

/**
 *
 * @author philipretl
 */
public class ServidorAImpl extends UnicastRemoteObject implements ServidorAInt {
    private ArrayList<AdministradorADTO> admins;
    private ArrayList<UsuarioADTO> usuariosA;
    
    private ImplTextoUsuarioA txtA;
    private ImplTextoAdministradorA txtAdm;
    private ConexionSA gui;
    

    
    public ServidorAImpl(ConexionSA gui) throws RemoteException, IOException {
        super();
        admins = new ArrayList();
        usuariosA = new ArrayList();
        txtA=new ImplTextoUsuarioA();
        txtAdm=new ImplTextoAdministradorA();
        rellenar();
        this.gui=gui;
        
    }
    
        
    public void rellenar() throws IOException{ // borrar esta mierda
        usuariosA= txtA.getUsuarios();
        admins=txtAdm.getAdministradores();
 
        
    }
    
    @Override
    public boolean AccesoAdministrador(AdministradorADTO adminA) throws RemoteException {
        //System.out.println("$ server: Acceso a administrador" + adminA.getLogin() + adminA.getClave());
        gui.consola("$ serverGestion: Agregar Administrador");
        boolean flag = false;
        for (int i = 0; i < admins.size(); i++) {
            if(adminA.getLogin().equals(admins.get(i).getLogin()) && adminA.getClave().equals(admins.get(i).getClave())){
                flag=true;
            }
        }
        return flag;
    }

    
    @Override
    public boolean ModificarUsuario(String viejo,UsuarioADTO user) throws RemoteException {
        gui.consola("$ serverGestion: Modificar Usuario");
       boolean flag=false;
       int pos;
       pos=buscarUsuario(viejo);
        if(pos!=-1){
            usuariosA.set(pos,user);
            flag=true;
        }
        try {
            txtA.guardarUsuarios(usuariosA);
        } catch (IOException ex) {
            Logger.getLogger(ServidorAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return flag;
    }

    @Override
    public boolean BorrarUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverGestion: Borrar Usuario");
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
            Logger.getLogger(ServidorAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public boolean RegistrarUsuario(UsuarioADTO user) throws RemoteException {
        gui.consola("$ serverGestion: Registrar Usuario");
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
            Logger.getLogger(ServidorAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return flag;
    }
    
    @Override
    public UsuarioADTO solicitarUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverGestion: Solicitar Usuario");
        UsuarioADTO user=null;
        int pos;
        pos=buscarUsuario(codigo);
        if(pos!=-1){
            user= new UsuarioADTO(usuariosA.get(pos).getNombre(),usuariosA.get(pos).getApellidos(),usuariosA.get(pos).getRol(),usuariosA.get(pos).getCodigo());
        
        }
        
        return user;
        
    }
    @Override
    public int buscarUsuario(String codigo){
        gui.consola("$ serverGestion: Buscar Usuario");
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
        gui.consola("$ serverGestion: Modificar Credenciales");
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
                            admins.get(i).setClave(pass);
                            flag=true;
                        }   
                        break;
                }
                
                
                break;
            }
        }
        try {
            txtAdm.guardarAdministradores(admins);
        } catch (IOException ex) {
            Logger.getLogger(ServidorAImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    
    }

    @Override
    public ArrayList<UsuarioADTO> consultarUsuarios() throws RemoteException {
        gui.consola("$ serverGestion: consultar Usuarios");
        return usuariosA;
    }
    
}
