/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioA;
import AdministradorB.UsuarioB;
import DAO.ImplTextoUsuarioB;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio
 */
public class GestionClienteImpl extends UnicastRemoteObject implements GestionClienteInt {
    
    //private int semaforo;
    private ArrayList<UsuarioB> usuarios;
    static SolicitudServidorInt srvA;
    private UsuarioB usrb;
    String ip;
    int puerto;
    
    public GestionClienteImpl(String ip,int puerto) throws RemoteException{
        super();
        //semaforo=0;
        usuarios=new ArrayList<>();
        this.ip=ip;
        this.puerto=puerto;
    }

    @Override
    public int ingresoUsuario(String codigo) throws RemoteException {
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int retorno=0;
        Calendar calendario;
        //UsuarioA usr = conexionServidorA(codigo);
        
        //UsuarioA usr = null;
        /*if(usr==null){
            retorno=1;//usuario no existe
        }else{*/
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i).getCodigo().equals(codigo)){
                    retorno =2;//usuario ya ha ingresado
                }
            }
            
            if(retorno==0){
                calendario = Calendar.getInstance();
                String hora=String.valueOf(calendario.get(Calendar.HOUR))+":"+String.valueOf(calendario.get(Calendar.MINUTE));
                String fecha=String.valueOf(calendario.get(Calendar.DAY_OF_MONTH))+" de "+String.valueOf(calendario.get(Calendar.MONTH))+" de "+String.valueOf(calendario.get(Calendar.YEAR));
                System.out.println(hora+fecha);
                //usrb=new UsuarioB(usr.getNombre(),usr.getApellidos(),usr.getRol(),usr.getCodigo(),hora,fecha);
                //usuarios.add(usrb);
            
                ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
                /*try {
                    //atxt.guardarUsuarios(usuarios);
                } catch (IOException ex) {
                    Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            
                retorno=3;//Usuario pudo ingresar
            }
        //}
        
        return retorno;
    }

    @Override
    public int salidaUsuario(String codigo) throws RemoteException {
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int retorno = 0;
        int pos=-1;
        
        UsuarioA usr = conexionServidorA(codigo);
        if(usr==null){
            retorno=1;
        }else{
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i).getCodigo().equals(codigo)){
                    pos=i;
                }
            }
        }
        
        if(pos!=-1){
            usuarios.remove(pos);
            ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
            try {
                atxt.guardarUsuarios(usuarios);
            } catch (IOException ex) {
                Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            retorno=3;
        }else{
            retorno=2;
        }
        
        
        return retorno;
    }
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioB usuariosB=new ImplTextoUsuarioB();
        usuarios=usuariosB.getUsuarios();
    }
    
    public UsuarioA conexionServidorA(String codigo) throws RemoteException{
        UsuarioA usuario;
        try{
            srvA= (SolicitudServidorInt) cliente.UtilidadesRegistroC.obtenerObjRemoto(puerto, ip,"Gestion");
                            
                            
        }catch(Exception e){
            System.out.println("No se pudo registrar la conexion...");
            System.out.println(e.getMessage());
        }
        
        usuario = srvA.soliciarUsuario(codigo);
        
        return usuario;
    }
    

    @Override
    public UsuarioB consultarUsuarioIngresado() throws RemoteException {
        return usrb;
    }
}
