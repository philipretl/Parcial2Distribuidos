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
        
        int retorno=0;
        UsuarioA usr = conexionServidorA(ip,puerto,codigo);
        UsuarioB usrb;
        Calendar calendario;
        if(usr==null){
            retorno=1;
        }else{
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i).getCodigo().equals(codigo)){
                    retorno =2;
                }
            }
        }
        
        if(retorno==0){
            calendario = Calendar.getInstance();
            String hora=String.valueOf(calendario.get(Calendar.HOUR))+":"+String.valueOf(calendario.get(Calendar.MINUTE));
            String fecha=String.valueOf(calendario.get(Calendar.DAY_OF_MONTH))+" de "+String.valueOf(calendario.get(Calendar.MONTH))+" de "+String.valueOf(calendario.get(Calendar.YEAR));
            usrb=new UsuarioB(usr.getNombre(),usr.getApellidos(),usr.getRol(),usr.getCodigo(),hora,fecha);
            usuarios.add(usrb);
            
            ImplTextoUsuarioB atxt= new ImplTextoUsuarioB();
            try {
                atxt.guardarUsuarios(usuarios);
            } catch (IOException ex) {
                Logger.getLogger(GestionClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            retorno=3;
        }
        
        return retorno;
    }

    @Override
    public boolean salidaUsuario(String codigo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void cargarUsuarios() throws IOException{
        ImplTextoUsuarioB usuariosB=new ImplTextoUsuarioB();
        usuarios=usuariosB.getUsuarios();
    }
    
    public static UsuarioA conexionServidorA(String ip,int puerto, String codigo) throws RemoteException{
        UsuarioA usuario;
        try{
            int numPuertoRMIRegistry=0;
            String direccionIpRMIRegistry=ip;
            numPuertoRMIRegistry = puerto;
            srvA= (SolicitudServidorInt) cliente.UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry, direccionIpRMIRegistry,"Gestion");
                            
                            
        }catch(Exception e){
            System.out.println("No se pudo registrar la conexion...");
            System.out.println(e.getMessage());
        }
        
        return usuario = srvA.soliciarUsuario(codigo);
    }
}
