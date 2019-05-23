/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Skill;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Adriana Herrera
 */
public class ServicioSkills extends AccesoServicios{
    
    private static final String listarSkills = "{?=call listarSkills ()}";
    private static final String insertarSkills = "{call insertarSkills (?,?,?)}";
    private static final String modificarSkills = "{call modificarSkills (?,?,?,?)}";
    private static final String eliminarSkill  = "{call eliminarSkill (?)}";
    
    //TO DO: No sé cómo se comporta la clase Skill, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioSkills(){}
    
    public List<Skill> listarSkills() throws GlobalException, NoDataException
    {
        try
        {
           conectar();
        }
        catch (ClassNotFoundException ex)
        {
            throw new GlobalException("No se ha localizado el Driver");
        }

        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        List<Skill> coleccion = new ArrayList();
        Skill skill = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarSkills);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                skill = new Skill(
                    rs.getInt("n_skill"),
                    rs.getString("usuario_id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                coleccion.add(skill);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    
    public boolean insertarSkills(Skill skill) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(insertarSkills);
            pstmt.setString(1, skill.getUsuario());
            pstmt.setString(2, skill.getNombre());
            pstmt.setString(3, skill.getDescripcion());
            
            boolean resultado = pstmt.execute();
            if (resultado == true)
            {
                throw new NoDataException("No se realizo la inserción");
            }
        }
        catch (SQLException e)
        {
               System.out.println(e);
               throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
            return true;
        }
    }
    
    public void modificarSkills(Skill skill) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(modificarSkills);
            pstmt.setInt(1, skill.getId());
            pstmt.setString(2, skill.getUsuario());
            pstmt.setString(3, skill.getNombre());
            pstmt.setString(4, skill.getDescripcion());
            
            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0)
            {
                System.out.println("\nModificación Satisfactoria!");
            }
            else
            {
                throw new NoDataException("No se realizo la actualización");
            }
        }
        catch (SQLException e)
        {
               System.out.println(e);
               throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    public boolean eliminarSkill(int id) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(eliminarSkill);
            pstmt.setInt(1, id);
            
            int resultado = pstmt.executeUpdate();

            if (resultado != 0)
            {
                System.out.println("\nEliminación Satisfactoria!");
            }
            else
            {
                throw new NoDataException("No se realizo el borrado");
            }
        }
        catch (SQLException e)
        {
               System.out.println(e);
               throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return true;
    }
    
}
