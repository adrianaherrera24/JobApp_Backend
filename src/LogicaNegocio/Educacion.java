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
public class Educacion implements Serializable{
    
    int id;
    String usuario;
    String institucion;
    String carrera;
    String titulo;
    String anno;
    
    public Educacion(){
        id = 0;
        usuario = new String();
        institucion = new String();
        carrera =  new String();
        titulo = new String();
        anno = new String();  
    }
    
    public Educacion(int id, String usuario, String institucion,String carrera, String titulo, String anno){
        this.id = id;
        this.usuario = usuario;
        this.institucion = institucion;
        this.carrera = carrera;
        this.titulo = titulo;
        this.anno = anno; 
    }
    
    public Educacion(String usuario, String institucion,String carrera, String titulo, String anno){
        this.usuario = usuario;
        this.institucion = institucion;
        this.carrera = carrera;
        this.titulo = titulo;
        this.anno = anno; 
    }

    @Override
    public String toString() {
        return "Educacion{" + "id=" + id + "usuario=" + usuario + ", institucion=" + institucion + ", carrera=" + carrera + ", titulo=" + titulo + ", anno=" + anno + '}';
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }
}