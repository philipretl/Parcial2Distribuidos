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
import servidorB.ConexionSB;

/**
 *
 * @author philipretl
 */
public class GestionAdminBImpl extends UnicastRemoteObject implements GestionAdmBInt{
    ArrayList<AdministradorB> admins;
    ArrayList<UsuarioB> usuariosB;
    ArrayList<AdministradorBCallbackInt> usuarioCllbck;
    ImplTextoUsuarioB txtU;
    ImplTextoAdministradorB txtA;
    ConexionSB gui;
    
     public GestionAdminBImpl (ConexionSB gui) throws RemoteException, IOException {
        super();
        admins = new ArrayList();
        usuariosB= new ArrayList();
        usuarioCllbck= new ArrayList<>();
        txtU= new ImplTextoUsuarioB();
        txtA= new ImplTextoAdministradorB();
        rellenar();
        this.gui=gui;
                
        
    }
    
    
    public void rellenar() throws IOException{
        usuariosB=txtU.getUsuarios();
        admins= txtA.getAdministradores();
    }
    
  
    
    @Override
    public boolean AccesoAdministrador(AdministradorB adminB) throws RemoteException {
        gui.consola("$ serverAcceso: Acceso Administrador");
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
         gui.consola("$ serverAcceso: Consultar Usuarios Ingresados");
        try {
            usuariosB=txtU.getUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuariosB;
    }

    @Override
    public boolean modificarCredenciales(String antiguo, String login, String pass, int opcion) throws RemoteException {
         gui.consola("$ serverAcceso: Modificar Credenciales");
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

    @Override
    public synchronized void registrarCallback(AdministradorBCallbackInt objcllbck) throws RemoteException {
        boolean registro=false;
        if(!usuarioCllbck.contains(objcllbck)){
            registro=usuarioCllbck.add(objcllbck);
        }
        doCallbacks();
        if(registro){
            System.out.println("El cambio se registro");      
        }else{
            System.out.println("El cambio no se registro");
        }
        //return registro;
    }
    
    private synchronized void doCallbacks( ) throws RemoteException{
        for (int i = 0; i < usuarioCllbck.size(); i++) {
            System.out.println("doing "+ i +"-th callback\n");
            AdministradorBCallbackInt nextClient = (AdministradorBCallbackInt) usuarioCllbck.get(i);
            UsuarioB get = usuariosB.get(usuariosB.size()-1);
            String cadena=get.getRol()+" "+get.getNombre()+" "+get.getApellidos();
            nextClient.notificarIngresoServidor(cadena);
        } // for
    } // function

    
}
