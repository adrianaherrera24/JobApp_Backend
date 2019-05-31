/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Perfil;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Adriana
 */
public class ServicioPerfil extends AccesoServicios{
    
    private static final String listarPerfil = "{?=call listarPerfil (?)}";
    private static final String insertarPerfil = "{call insertarPerfil (?,?,?,?,?,?,?)}";
    private static final String modificarPerfil = "{call modificarPerfil (?,?,?,?,?,?,?)}";
    
    public void ServicioPerfil(){}
    
    public List<Perfil> listarPerfil(String id) throws GlobalException, NoDataException
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
        List<Perfil> coleccion = new ArrayList();
        Perfil perfil = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarPerfil);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                perfil = new Perfil(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("lugar_habitacion"),
                    rs.getInt("edad"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("descripcion")
                );
                coleccion.add(perfil);
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
    
    public void insertarPerfil(Perfil perfil) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarPerfil);
            pstmt.setString(1, perfil.getId());
            pstmt.setString(2, perfil.getNombre());
            pstmt.setString(3, perfil.getLugar_habitacion());
            pstmt.setInt(4, perfil.getEdad());
            pstmt.setString(5, perfil.getCorreo());
            pstmt.setString(6, perfil.getTelefono());
            pstmt.setString(7, perfil.getDescripcion());
            
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
    
    public void modificarPerfil(Perfil perfil) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarPerfil);
            pstmt.setString(1, perfil.getId());
            pstmt.setString(2, perfil.getNombre());
            pstmt.setString(3, perfil.getLugar_habitacion());
            pstmt.setInt(4, perfil.getEdad());
            pstmt.setString(5, perfil.getCorreo());
            pstmt.setString(6, perfil.getTelefono());
            pstmt.setString(7, perfil.getDescripcion());
            
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
}
