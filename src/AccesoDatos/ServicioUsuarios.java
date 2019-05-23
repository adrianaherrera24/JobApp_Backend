/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Usuario;
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
public class ServicioUsuarios extends AccesoServicios{
    
    private static final String listarUsuarios  = "{?=call listarUsuarios ()}";
    private static final String insertarUsuarios  = "{?=call insertarUsuarios (?,?,?,?,?,?)}";
    private static final String modificarUsuarios  = "{?=call modificarUsuarios (?,?,?,?,?,?)}";
    private static final String eliminarUsuarios  = "{?=call eliminarUsuarios (?)}";
    
    public void ServicioUsuarios(){}
    
    public List<Usuario> listarUsuarios() throws GlobalException, NoDataException
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
        List<Usuario> coleccion = new ArrayList();
        Usuario usuario = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarUsuarios);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
               /* usuario = new Usuario(
                                    rs.getString("nombreusuario"),
                                    rs.getString("contrasena"),
                                    rs.getString("rol")
                );*/
                coleccion.add(usuario);
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
    
    public boolean insertarUsuarios(Usuario usuario) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarUsuarios);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, usuario.getId());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setInt(4, usuario.getEdad());
            pstmt.setString(5, usuario.getTelefono());
            pstmt.setString(6, usuario.getEmail());
            pstmt.setInt(7, usuario.getPrivilegio());
            pstmt.execute();
            
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
    
    public boolean modificarUsuarios(Usuario usuario) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarUsuarios);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, usuario.getId());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setInt(4, usuario.getEdad());
            pstmt.setString(5, usuario.getTelefono());
            pstmt.setString(6, usuario.getEmail());
            pstmt.setInt(7, usuario.getPrivilegio());
            pstmt.execute();
            
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
    
    public boolean eliminarUsuarios(String id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarUsuarios);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
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
