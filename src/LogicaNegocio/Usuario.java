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
    int edad;
    String telefono;
    String email;
    int privilegio;
    
    public Usuario(){
        id= new String();
        nombre = new String();
        edad = 0;
        telefono = new String();
        email = new String();  
        privilegio = 0;
    }
    
    public Usuario(String id, String nombre,int edad, String telefono, String email, int privilegio){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.privilegio = privilegio;
        
    }

    @Override
    public String toString() {
        return "Experiencia{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + ", email=" + email + ", privilegio=" + privilegio + '}';
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }
}
