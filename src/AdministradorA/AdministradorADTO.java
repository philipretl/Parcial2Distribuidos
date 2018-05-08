/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradorA;

import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
import java.io.Serializable;

/**
 *
 * @author Mauricio
 */
public class AdministradorADTO implements Serializable{
    
    private String login;
    private String clave;

    public AdministradorADTO() {
    }

    public AdministradorADTO(String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    
}
