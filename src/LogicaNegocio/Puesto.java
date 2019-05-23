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
public class Puesto implements Serializable {
    
    String id;
    String nombre;
    String descripcion;
    String requisitos;
    String horario;
    int vigente;
    
    public Puesto(){
        id= new String();
        nombre = new String();
        descripcion = new String();
        requisitos = new String();
        horario = new String();  
        vigente = 0;
    }
    
    public Puesto(String id, String nombre,String descripcion, String requisitos, String horario, int vigente){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.horario = horario;
        this.vigente = vigente; 
    }

    @Override
    public String toString() {
        return "Puestos{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", requisitos=" + requisitos + ", horario=" + horario + ", vigente=" + vigente + '}';
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getVigente() {
        return vigente;
    }

    public void setVigente(int vigente) {
        this.vigente = vigente;
    }
}
