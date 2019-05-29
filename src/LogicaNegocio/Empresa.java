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
public class Empresa implements Serializable{
    
    String id;
    String nombre;
    String locacion;
    
    
    public Empresa(){
        id = new String();
        nombre = new String();
        locacion = new String();
    }
    public Empresa(String id,String nombre,String locacion){
        this.id = id;
        this.nombre = nombre;
        this.locacion = locacion;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nombre=" + nombre + ", locacion=" + locacion+ '}';
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

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

   
}
