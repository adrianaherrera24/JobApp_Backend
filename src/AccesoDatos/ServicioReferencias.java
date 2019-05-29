/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Referencia;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Adriana
 */
public class ServicioReferencias extends AccesoServicios{
    
    private static final String listarReferencias = "{?=call listarReferencias (?)}";
    private static final String insertarReferencia = "{call insertarReferencia (?,?,?,?,?)}";
    private static final String modificarReferencias = "{call modificarReferencias (?,?,?,?,?,?)}";
    private static final String eliminarReferencia  = "{call eliminarReferencia (?)}";
    
    //TO DO: No sé cómo se comporta la clase Skill, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioReferencias(){}
    
    public List<Referencia> listarReferencias(String id) throws GlobalException, NoDataException
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
        List<Referencia> coleccion = new ArrayList();
        Referencia ref = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarReferencias);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                ref = new Referencia(
                    rs.getInt("n_ref"),
                    rs.getString("usuario_id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("descripcion")
                );
                coleccion.add(ref);
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
    
    
    public boolean insertarReferencia(Referencia ref) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarReferencia);
            pstmt.setString(1, ref.getUsuario());
            pstmt.setString(2, ref.getNombre());
            pstmt.setString(3, ref.getTelefono());
            pstmt.setString(4, ref.getEmail());
            pstmt.setString(5, ref.getDescripcion());
            
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
    
    public void modificarReferencias(Referencia ref) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarReferencias);
            pstmt.setInt(1, ref.getId());
            pstmt.setString(2, ref.getUsuario());
            pstmt.setString(3, ref.getNombre());
            pstmt.setString(4, ref.getTelefono());
            pstmt.setString(5, ref.getEmail());
            pstmt.setString(6, ref.getDescripcion());
            
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
    
    public boolean eliminarReferencia(int id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarReferencia);
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
