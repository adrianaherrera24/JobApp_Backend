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
    String area;
    
    public Empresa(){
        id = new String();
        nombre = new String();
        locacion = new String();
        area = new String();
    }
    public Empresa(String id,String nombre,String locacion,String area){
        this.id = id;
        this.nombre = nombre;
        this.locacion = locacion;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nombre=" + nombre + ", locacion=" + locacion + ", area=" + area + '}';
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
