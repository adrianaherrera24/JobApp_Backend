/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Puesto;
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
public class ServicioPuesto extends AccesoServicios{
    
    private static final String listarPuesto = "{?=call listarPuesto (?)}";
    private static final String insertarPuesto = "{?=call insertarPuesto (?,?,?,?,?,?)}";
    private static final String modificarPuesto = "{?=call modificarPuesto (?,?,?,?,?,?)}";
    private static final String eliminarPuesto  = "{?=call eliminarPuesto (?)}";
    
    //TO DO: No sé cómo se comporta la clase Educación, si tiene un objeto Usuario de atributo o qué
    //Voy a asumir que solo tiene un string con el identificador del usuario
    
    public void ServicioPuesto(){}
    
    public List<Puesto> listarPuesto(String id) throws GlobalException, NoDataException
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
        List<Puesto> coleccion = new ArrayList();
        Puesto puesto = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarPuesto);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                puesto = new Puesto(
                                    id,
                                    rs.getString("nombre"),
                                    rs.getString("area"),
                                    rs.getString("descripcion"),
                                    rs.getString("requisitos"),
                                    rs.getString("horario"),
                                    rs.getInt("vigente"),
                                    rs.getString("nombre_empresa"),
                                    rs.getString("locacion_empresa"));
                coleccion.add(puesto);
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
    
    
    public boolean insertarPuesto(Puesto puesto) throws GlobalException, NoDataException
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
            
            pstmt = conexion.prepareCall(insertarPuesto);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, puesto.getId());
            pstmt.setString(3, puesto.getNombre());
            pstmt.setString(4, puesto.getNombre());
            pstmt.setString(5, puesto.getDescripcion());
            pstmt.setString(6, puesto.getRequisitos());
            pstmt.setString(7, puesto.getHorario());
            pstmt.setInt(8, puesto.getVigente());            
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
    
    public boolean modificarPuesto(Puesto puesto) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarPuesto);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, puesto.getId());
            pstmt.setString(3, puesto.getNombre());
            pstmt.setString(4, puesto.getArea());
            pstmt.setString(5, puesto.getDescripcion());
            pstmt.setString(6, puesto.getRequisitos());
            pstmt.setString(7, puesto.getHorario());
            pstmt.setInt(8, puesto.getVigente());
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
    
    public boolean eliminarPuesto(String id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(eliminarPuesto);
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
