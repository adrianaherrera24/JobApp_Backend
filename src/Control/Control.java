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
import AccesoDatos.ServicioPerfil;
import AccesoDatos.ServicioPuesto;
import AccesoDatos.ServicioReferencias;
import AccesoDatos.ServicioSkills;
import AccesoDatos.ServicioTrabajos;
import AccesoDatos.ServicioUsuarios;

import LogicaNegocio.Educacion;
import LogicaNegocio.Empresa;
import LogicaNegocio.Perfil;
import LogicaNegocio.Puesto;
import LogicaNegocio.Referencia;
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
    private ServicioReferencias sr;
    private ServicioPerfil spp;
    
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
        sr = new ServicioReferencias();
        spp = new ServicioPerfil();
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
    ///////////////////////////////////////// REFERENCIAS //////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Referencia> listaReferencia(String id) throws GlobalException, NoDataException{
        List<Referencia> ref = new ArrayList();
        ref = sr.listarReferencias(id);
        return ref;
    }
    
    public void eliminarReferencia(int id) throws GlobalException, NoDataException{
        sr.eliminarReferencia(id);
    }
    
    public void insertarReferencia(Referencia ref) throws GlobalException, NoDataException{
        sr.insertarReferencia(ref);
    }
    
    public void modificarReferencias(Referencia ref) throws GlobalException, NoDataException{
        sr.modificarReferencias(ref);
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
    public List<Puesto> listarPuestoEmpresa(String id) throws GlobalException, NoDataException{
        
        List<Puesto> puesto = new ArrayList();
        puesto = sp.listarPuestoEmpresa(id);
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
    public List<Usuario> listarUsuarios() throws GlobalException, NoDataException{
        List<Usuario> usuario = new ArrayList();
        usuario = su.listarUsuarios();
        return usuario;
    }
    
    public void eliminarUsuario(String id) throws GlobalException, NoDataException{
        su.eliminarUsuarios(id);
    }
    
    public void insertarUsuarios(Usuario u) throws GlobalException, NoDataException{
        su.insertarUsuarios(u);
    }
    
    public void modificarUsuarios(Usuario u) throws GlobalException, NoDataException{
        su.modificarUsuarios(u);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// PERFIL ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Perfil> listarPerfil(String id) throws GlobalException, NoDataException{
        
        List<Perfil> p = new ArrayList();
        p = spp.listarPerfil(id);
        return p;
    }
    
    public void insertarPerfil(Perfil p) throws GlobalException, NoDataException{
        spp.insertarPerfil(p);
    }
    
    public void modificarPerfil(Perfil p) throws GlobalException, NoDataException{
        spp.modificarPerfil(p);
    }
}
