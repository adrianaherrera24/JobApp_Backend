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
public class Trabajo implements Serializable {
    
    int id;
    String usuario;
    String empresa;
    String puesto;
    String descripcion;
    int anno_inicio;
    int anno_final;
    
    public Trabajo(){
        id = 0;
        usuario = new String();
        empresa = new String();
        puesto = new String();
        descripcion = new String();
        anno_inicio = 0;  
        anno_final = 0;
    }
    
    public Trabajo(int id, String usuario, String empresa,String puesto, String descripcion, int anno_inicio, int anno_final){
        this.id = id;
        this.usuario = usuario;
        this.empresa = empresa;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.anno_inicio = anno_inicio;
        this.anno_final = anno_final; 
    }
    
    public Trabajo(String usuario, String empresa,String puesto, String descripcion, int anno_inicio, int anno_final){
        this.usuario = usuario;
        this.empresa = empresa;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.anno_inicio = anno_inicio;
        this.anno_final = anno_final; 
    }

    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", usuario="+usuario+", empresa=" + empresa + ", puesto=" + puesto + ", descripcion=" + descripcion + ", anno_inicio=" + anno_inicio + ", anno_final=" + anno_final + '}';
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnno_inicio() {
        return anno_inicio;
    }

    public void setAnno_inicio(int anno_inicio) {
        this.anno_inicio = anno_inicio;
    }

    public int getAnno_final() {
        return anno_final;
    }

    public void setAnno_final(int anno_final) {
        this.anno_final = anno_final;
    }
}
