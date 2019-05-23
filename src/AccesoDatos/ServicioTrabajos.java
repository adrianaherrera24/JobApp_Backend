/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Trabajo;
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
public class ServicioTrabajos extends AccesoServicios{
    
    private static final String listarTrabajo = "{?=call listarTrabajo ()}";
    private static final String insertarTrabajo = "{call insertarTrabajo (?,?,?,?,?,?)}";
    private static final String modificarTrabajo = "{call modificarTrabajo (?,?,?,?,?,?,?)}";
    private static final String eliminarTrabajo  = "{call eliminarTrabajo (?)}";
    
    //TO DO: No sé cómo se comporta la clase Educación, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioTrabajo(){}
    
    public List<Trabajo> listarTrabajo() throws GlobalException, NoDataException
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
        List<Trabajo> coleccion = new ArrayList();
        Trabajo trabajo = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarTrabajo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                trabajo = new Trabajo(
                    rs.getInt("n_trab"),
                    rs.getString("usuario_id"),
                    rs.getString("empresa"),
                    rs.getString("puesto"),
                    rs.getString("descripcion"),
                    rs.getInt("anno_inicio"),
                    rs.getInt("anno_final"));
                coleccion.add(trabajo);
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
    
    
    public boolean insertarTrabajo(Trabajo trabajo) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarTrabajo);
            pstmt.setString(1, trabajo.getUsuario());
            pstmt.setString(2, trabajo.getEmpresa());
            pstmt.setString(3, trabajo.getPuesto());
            pstmt.setString(4, trabajo.getDescripcion());
            pstmt.setInt(5, trabajo.getAnno_inicio());
            pstmt.setInt(6, trabajo.getAnno_final());
            
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
    
    public void modificarTrabajo(Trabajo trabajo) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarTrabajo);
            pstmt.setInt(1, trabajo.getId());
            pstmt.setString(2, trabajo.getUsuario());
            pstmt.setString(3, trabajo.getEmpresa());
            pstmt.setString(4, trabajo.getPuesto());
            pstmt.setString(5, trabajo.getDescripcion());
            pstmt.setInt(6, trabajo.getAnno_inicio());
            pstmt.setInt(7, trabajo.getAnno_final());
            
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
    
    public boolean eliminarTrabajo(int id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarTrabajo);
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
