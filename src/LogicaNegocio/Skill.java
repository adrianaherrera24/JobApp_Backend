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
public class Skill implements Serializable {
    
    int id;
    String usuario;
    String nombre;
    String descripcion;
    
    public Skill(){
        id = 0;
        usuario = new String();
        nombre = new String();
        descripcion = new String();
    }
    
    public Skill(String usuario,String nombre,String descripcion ){
        this.usuario = usuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Skill(int id, String usuario,String nombre,String descripcion ){
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Habilidades{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
