/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradorB;

import java.io.Serializable;

/**
 *
 * @author Mauricio
 */
public class UsuarioB implements Serializable{
    
    private String nombre;
    private String apellidos;
    private String rol;
    private String codigo;
    private String hora;
    private String fecha;

    public UsuarioB() {
    }

    public UsuarioB(String nombre, String apellidos, String rol, String codigo, String hora, String fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.codigo = codigo;
        this.hora = hora;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
