/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Adriana Herrera
 */
public class Usuario implements Serializable{
    
    String id;
    String nombre;
    String email;
    String password;
    String privilegio;
    
    public Usuario(){
        id= new String();
        nombre = new String();
        email = new String(); 
        password = new String();
        privilegio = new String();
    }
    
    public Usuario(String id, String nombre, String email,String telefono,String privilegio){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = telefono;
        this.privilegio = privilegio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", privilegio=" + privilegio + '}';
    }
}
