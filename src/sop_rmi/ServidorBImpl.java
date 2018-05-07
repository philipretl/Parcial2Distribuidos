/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioA;
import AdministradorB.AdministradorB;
import AdministradorB.UsuarioB;
import DAO.ImplTextoAdministradorB;
import DAO.ImplTextoUsuarioA;
import DAO.ImplTextoUsuarioB;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorB.ConexionSB;

/**
 *
 * @author philipretl
 */
public class ServidorBImpl extends UnicastRemoteObject implements GestionAdmBInt,GestionClienteInt,SolicitudServidorInt{
    //Variables GestionAdministrador
    ArrayList<AdministradorB> admins;
    ArrayList<UsuarioB> usuariosB;
    ArrayList<AdministradorBCallbackInt> usuarioCllbck;
    ImplTextoUsuarioB txtU;
    ImplTextoAdministradorB txtA;
    ConexionSB gui;
    
    //Variables GestionUsuario
    private ArrayList<UsuarioB> usuarios;
    static ServidorAImpl srvA;
    private UsuarioB usrb;
    private UsuarioB usrS;
    ArrayList<String> meses;
    
    
    
     public ServidorBImpl (ConexionSB gui) throws RemoteException, IOException {
        super();
        admins = new ArrayList();
        usuariosB= new ArrayList();
        usuarioCllbck= new ArrayList<>();
        txtU= new ImplTextoUsuarioB();
        txtA= new ImplTextoAdministradorB();
        rellenar();
        usuarios=new ArrayList<>();
        meses=new ArrayList<>();
        crearMeses();
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
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
        
        
        
    }

    @Override
    public void registrarCallback(AdministradorBCallbackInt objcllbck) throws RemoteException {
        gui.consola("$ serverGestion: registrar callback");
        boolean registro=false;
        if(!usuarioCllbck.contains(objcllbck)){
            registro=usuarioCllbck.add(objcllbck);
        }
        try {
            UsuarioB get = usuariosB.get(usuariosB.size()-1);
            String cadena="";
            cadena=get.getRol()+" "+get.getNombre()+" "+get.getApellidos();
            doCallbacks(cadena);
        } catch (IOException ex) {
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(registro){
            System.out.println("El cambio se registro");      
        }else{
            System.out.println("El cambio no se registro");
        }
        //return registro;
    }
    
    private void doCallbacks(String cadena) throws RemoteException, IOException{
        for (int i = 0; i < usuarioCllbck.size(); i++) {
            System.out.println("Haciendo "+ (i+1)+" callback\n");
           
            System.out.println("Cadena enviada: "+cadena);
        
            usuarioCllbck.get(i).notificarIngresoServidor(cadena);
        } // for
    } // function

    @Override
    public int ingresoUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverAcceso: Ingreso Usuario");
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int retorno=0;
        Calendar calendario;
        UsuarioA usr = soliciarUsuario(codigo);
        
        //UsuarioA usr = null;
        if(usr==null){
            retorno=1;//usuario no existe
        }else{
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i).getCodigo().equals(codigo)){
                    retorno =2;//usuario ya ha ingresado
                }
            }
            
            if(retorno==0){
                calendario = Calendar.getInstance();
                String hora=String.valueOf(calendario.get(Calendar.HOUR))+":"+String.valueOf(calendario.get(Calendar.MINUTE));
                String fecha1=String.valueOf(calendario.get(Calendar.DAY_OF_MONTH))+" de ";
                String fecha2=meses.get(calendario.get(Calendar.MONTH))+" de "+String.valueOf(calendario.get(Calendar.YEAR));
                String fecha=fecha1+fecha2;
                System.out.println(hora+fecha);
                usrb=new UsuarioB(usr.getNombre(),usr.getApellidos(),usr.getRol(),usr.getCodigo(),hora,fecha);
                usuarios.add(usrb);
                String cadena="";
                cadena=usrb.getRol()+" "+usrb.getNombre()+" "+usrb.getApellidos();
            
                ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
                try {
                    atxt.guardarUsuarios(usuarios);
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                try {
                    doCallbacks(cadena);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
                retorno=3;//Usuario pudo ingresar
                
            }
        }
        
        return retorno;
    }

    @Override
    public int salidaUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverAcceso: Salida Usuario");
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int retorno = 0;
        int pos=-1;
        
        UsuarioA usr = soliciarUsuario(codigo);
        if(usr==null){
            retorno=1;//usuario no existe
        }else{
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i).getCodigo().equals(codigo)){
                    pos=i;
                }
            }
            
            if(pos!=-1){
                usrS=usuarios.get(pos);
                usuarios.remove(pos);
                ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
                try {
                    atxt.guardarUsuarios(usuarios);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                retorno=3;//salida exitosa
            }else{
                retorno=2;//usuario ya ha salido
            }
        }
        
        
        return retorno;
    }

    @Override
    public UsuarioB consultarUsuarioIngresado() throws RemoteException {
        gui.consola("$ serverAcceso: Consultar Usuario Ingresado");
        return usrb;
    }

    @Override
    public UsuarioB consultarUsuarioSalida() throws RemoteException {
        gui.consola("$ serverAcceso: Consultar Usuario Salida");
        return usrS;
    }
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioB usuariosB=new ImplTextoUsuarioB();
        usuarios=usuariosB.getUsuarios();
    }
    
    
    private void crearMeses(){
        meses.add("Enero");
        meses.add("Febrero");
        meses.add("Marzo");
        meses.add("Abril");
        meses.add("Mayo");
        meses.add("Junio");
        meses.add("Julio");
        meses.add("Agosto");
        meses.add("Septiembre");
        meses.add("Octubre");
        meses.add("Noviembre");
        meses.add("Diciembre");
    }
    
    @Override
    public UsuarioA soliciarUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverGestion: Solicitar Usuario ");
        UsuarioA usr = null;
        ArrayList<UsuarioA> usuarios = new ArrayList<>();
        ImplTextoUsuarioA usuariosA=new ImplTextoUsuarioA();
        try {
            usuarios=usuariosA.getUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getCodigo().equals(codigo)){
               usr=new UsuarioA(usuarios.get(i).getNombre(),usuarios.get(i).getApellidos(),usuarios.get(i).getRol(),usuarios.get(i).getCodigo());
            }
        }
        return usr;
    }

    
}
