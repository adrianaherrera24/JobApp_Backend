/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Educacion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Adriana Herrera
 */
public class ServicioEducacion extends AccesoServicios{

    private static final String listarEducacion = "{?=call listarEducacion (?)}";
    private static final String insertarEducacion = "{call insertarEducacion (?,?,?,?,?)}";
    private static final String modificarEducacion = "{call modificarEducacion (?,?,?,?,?,?)}";
    private static final String eliminarEducacion  = "{call eliminarEducacion (?)}";
    
    //TO DO: No sé cómo se comporta la clase Educación, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioEducacion(){}
    
    public List<Educacion> listarEducacion(String id) throws GlobalException, NoDataException
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
        List<Educacion> coleccion = new ArrayList();
        Educacion educacion = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarEducacion);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                educacion = new Educacion(
                    rs.getInt("n_educ"),
                    rs.getString("usuario_id"),
                    rs.getString("institucion"),
                    rs.getString("carrera"),
                    rs.getString("titulo"),
                    rs.getString("anno"));
                coleccion.add(educacion);
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
    
    
    public void insertarEducacion(Educacion educacion) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarEducacion);
            pstmt.setString(1, educacion.getUsuario());
            pstmt.setString(2, educacion.getInstitucion());
            pstmt.setString(3, educacion.getCarrera());
            pstmt.setString(4, educacion.getTitulo());
            pstmt.setString(5, educacion.getAnno());
            
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
        }
    }
    
    public void modificarEducacion(Educacion educacion) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarEducacion);
            pstmt.setInt(1, educacion.getId());
            pstmt.setString(2, educacion.getUsuario());
            pstmt.setString(3, educacion.getInstitucion());
            pstmt.setString(4, educacion.getCarrera());
            pstmt.setString(5, educacion.getTitulo());
            pstmt.setString(6, educacion.getAnno());
            
            
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
    
    public boolean eliminarEducacion(int id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarEducacion);
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
