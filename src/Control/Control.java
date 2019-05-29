/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;

import AccesoDatos.ServicioEducacion;
import AccesoDatos.ServicioEmpresa;
import AccesoDatos.ServicioPuesto;
import AccesoDatos.ServicioSkills;
import AccesoDatos.ServicioTrabajos;
import AccesoDatos.ServicioUsuarios;

import LogicaNegocio.Educacion;
import LogicaNegocio.Empresa;
import LogicaNegocio.Puesto;
import LogicaNegocio.Skill;
import LogicaNegocio.Trabajo;
import LogicaNegocio.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adriana Herrera
 */
public class Control {
    
    private ServicioEducacion sed;
    private ServicioEmpresa sem;
    private ServicioPuesto sp;
    private ServicioSkills ss;
    private ServicioTrabajos st;
    private ServicioUsuarios su;
    
    // Preguntar sobre instancia unica
    private static Control uniqueInstance;
    
    public static Control instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Control();
        }
        return uniqueInstance;
    }
    
    // Constructor
    public Control(){
        sed = new ServicioEducacion();
        sem = new ServicioEmpresa();
        sp = new ServicioPuesto();
        ss = new ServicioSkills();
        st = new ServicioTrabajos();
        su = new ServicioUsuarios();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// EDUCACION /////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Educacion> listarEducacion(String id) throws GlobalException, NoDataException{
        List<Educacion> educ = new ArrayList();
        educ = sed.listarEducacion(id);
        return educ;
    }
    
    public void eliminarEducacion(int id) throws GlobalException, NoDataException{
        sed.eliminarEducacion(id);
    }
    
    public void insertarEducacion(Educacion educ) throws GlobalException, NoDataException{
        sed.insertarEducacion(educ);
    }
    
    public void modificarEducacion(Educacion educ) throws GlobalException, NoDataException{
        sed.modificarEducacion(educ);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// EMPRESA ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Empresa> opcionesEmpresa(String id) throws GlobalException, NoDataException, GlobalException, GlobalException, GlobalException{
        
        List<Empresa> empresa = new ArrayList();
        empresa = sem.listarEmpresa();
        return empresa;
    }
    
    public void eliminarEmpresa(String id) throws GlobalException, NoDataException{
        sem.eliminarEmpresa(id);
    }
    
    public void insertarEmpresa(Empresa emp) throws GlobalException, NoDataException{
        sem.insertarEmpresa(emp);
    }
    
    public void modificarEmpresa(Empresa emp) throws GlobalException, NoDataException{
        sem.modificarEmpresa(emp);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// PUESTO ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Puesto> listarPuesto(String id) throws GlobalException, NoDataException{
        
        List<Puesto> puesto = new ArrayList();
        puesto = sp.listarPuesto(id);
        return puesto;
    }
    public void eliminarPuesto(String id) throws GlobalException, NoDataException{
        sp.eliminarPuesto(id);
    }
    
    public void insertarPuesto(Puesto pues) throws GlobalException, NoDataException{
        sp.insertarPuesto(pues);
    }
    
    public void modificarPuesto(Puesto pues) throws GlobalException, NoDataException{
        sp.modificarPuesto(pues);
    }
   
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// SKILLS //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Skill> listarSkills(String id) throws GlobalException, NoDataException{
        List<Skill> skill = new ArrayList();
        skill = ss.listarSkills(id);
        return skill;
    }
    
    public void eliminarSkill(int id) throws GlobalException, NoDataException{
        ss.eliminarSkill(id);
    }
    
    public void insertarSkills(Skill skill) throws GlobalException, NoDataException{
        ss.insertarSkills(skill);
    }
    
    public void modificarSkills(Skill skill) throws GlobalException, NoDataException{
        ss.modificarSkills(skill);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// TRABAJOS ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Trabajo> listarTrabajos(String id) throws GlobalException, NoDataException{
        List<Trabajo> trabajo = new ArrayList();
        trabajo = st.listarTrabajo(id);
        return trabajo;
    }
    
    public void eliminarTrabajo(int id) throws GlobalException, NoDataException{
        st.eliminarTrabajo(id);
    }
    
    public void insertarTrabajos(Trabajo trabajo) throws GlobalException, NoDataException{
        st.insertarTrabajo(trabajo);
    }
    
    public void modificarTrabajos(Trabajo trabajo) throws GlobalException, NoDataException{
        st.modificarTrabajo(trabajo);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// USUARIOS ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Usuario> opcionesUsuarios(String op2, String codigo) throws GlobalException, NoDataException{
        
        List<Usuario> usuario = new ArrayList();
                
        switch(op2){
            case "LISTAR":
                usuario = su.listarUsuarios();
            break;
            case "ELIMINAR":
                su.eliminarUsuarios(codigo);
            break;
        }
        return usuario;
    }
    
    public void opcionesUsuarios(String op2, Usuario usuario) throws GlobalException, NoDataException{
        switch(op2){
            case "AGREGAR":
                su.insertarUsuarios(usuario);
            break;
            case "MODIFICAR":
                su.modificarUsuarios(usuario);
            break;
        }
    }
}
