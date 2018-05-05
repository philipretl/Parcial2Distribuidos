/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradorA;

/**
 *
 * @author Mauricio
 */
public class UsuarioA {
    
    private String nombre;
    private String apellidos;
    private String rol;
    private String codigo;

    public UsuarioA() {
    }

    public UsuarioA(String nombre, String apellidos, String rol, String codigo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.codigo = codigo;
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
    
    
    
}
