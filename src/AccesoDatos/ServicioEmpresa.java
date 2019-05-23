/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Empresa;
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
public class ServicioEmpresa extends AccesoServicios{
    
    private static final String listarEmpresa = "{?=call listarEmpresa ()}";
    private static final String insertarEmpresa = "{call insertarEmpresa (?,?,?,?)}";
    private static final String modificarEmpresa = "{call modificarEmpresa (?,?,?,?)}";
    private static final String eliminarEmpresa  = "{call eliminarEmpresa (?)}";
    
    //TO DO: No sé cómo se comporta la clase Educación, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioEmpresa(){}
    
    public List<Empresa> listarEmpresa() throws GlobalException, NoDataException
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
        List<Empresa> coleccion = new ArrayList();
        Empresa empresa = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarEmpresa);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                empresa = new Empresa(
                    rs.getString("empresa_id"),
                    rs.getString("area"),
                    rs.getString("locacion"),
                    rs.getString("nombre"));
                coleccion.add(empresa);
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
    
    
    public void insertarEmpresa(Empresa empresa) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(insertarEmpresa);
            pstmt.setString(1, empresa.getId());
            pstmt.setString(2, empresa.getArea());
            pstmt.setString(3, empresa.getLocacion());
            pstmt.setString(4, empresa.getNombre());
            pstmt.execute();
            
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
    
    public void modificarEmpresa(Empresa empresa) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarEmpresa);
            pstmt.setString(1, empresa.getId());
            pstmt.setString(2, empresa.getArea());
            pstmt.setString(3, empresa.getLocacion());
            pstmt.setString(4, empresa.getNombre());
            pstmt.execute();
            
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
    
    public boolean eliminarEmpresa(String id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarEmpresa);
            pstmt.setString(1, id);
            
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
