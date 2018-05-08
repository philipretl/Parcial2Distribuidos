/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sop_rmi;

import AdministradorA.UsuarioADTO;
import AdministradorB.UsuarioBDTO;
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
 * @author Mauricio
 */
public class GestionClienteImpl extends UnicastRemoteObject implements GestionClienteInt{
    
    //private int semaforo;
    private ArrayList<UsuarioBDTO> usuarios;
    //static SolicitudServidorInt srvA;
    SolicitudServidorAInt srvA;
    private UsuarioBDTO usrb;
    private UsuarioBDTO usrS;
    private String ip;
    int puerto;
    private ArrayList<String> meses;
    private ConexionSB gui;
    
    public GestionClienteImpl(String ip,int puerto,ConexionSB gui) throws RemoteException{
        super();
        //semaforo=0;
        usuarios=new ArrayList<>();
        this.ip=ip;
        this.puerto=puerto;
        meses=new ArrayList<>();
        crearMeses();
        this.gui=gui;
    }

    @Override
    public int ingresoUsuario(String codigo) throws RemoteException {
        gui.consola("$ serverAcceso: Ingreso Usuario");
        try {
            cargarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int retorno=0;
        Calendar calendario;
        UsuarioADTO usr = conexionServidorA(codigo);
        
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
                usrb=new UsuarioBDTO(usr.getNombre(),usr.getApellidos(),usr.getRol(),usr.getCodigo(),hora,fecha);
                usuarios.add(usrb);
            
                ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
                try {
                    atxt.guardarUsuarios(usuarios);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int retorno = 0;
        int pos=-1;
        
        UsuarioADTO usr = conexionServidorA(codigo);
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
                    Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                retorno=3;//salida exitosa
            }else{
                retorno=2;//usuario ya ha salido
            }
        }
        
        
        return retorno;
    }
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioB usuariosB=new ImplTextoUsuarioB();
        usuarios=usuariosB.getUsuarios();
    }
    
    public UsuarioADTO conexionServidorA(String codigo) throws RemoteException{
        UsuarioADTO usuario;
        usuario=srvA.solicitarUsuario(codigo);
        return usuario;
    }
    
    public UsuarioADTO solicitarUsuario(String codigo) throws RemoteException {
        //gui.consola("$ serverAcceso: Solicitar Usuario ");
        UsuarioADTO usr = null;
        
        //gestionCliente.
        usr=srvA.solicitarUsuario(codigo);
        
        return usr;
    }

    @Override
    public UsuarioBDTO consultarUsuarioIngresado() throws RemoteException {
         gui.consola("$ serverAcceso: Consultar Usuario Ingresado");
        return usrb;
    }

    @Override
    public UsuarioBDTO consultarUsuarioSalida() throws RemoteException {
        gui.consola("$ serverAcceso: Consultar Usuario Salida");
        return usrS;
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
}
