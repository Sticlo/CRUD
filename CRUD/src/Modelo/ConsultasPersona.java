package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConsultasPersona extends Conexion {

    public boolean registrar(Persona pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO persona (nombre,apellido) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setString(2, pe.getApellido());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            }

        }
    }
    
    public boolean modificar(Persona pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE persona SET nombre=?,apellido=? WHERE id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setString(2, pe.getApellido());
            ps.setInt(3, pe.getId());
            ps.execute();
            return true; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            }

        }
    }
    
    public boolean eliminar(Persona pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM persona WHERE id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getId());
            ps.execute();
            return true; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR" + e);
            }

        }
    }
    
    public boolean buscar(Persona pe) {
        PreparedStatement ps = null;
        ResultSet rs= null;
        Connection con = getConexion();

        String sql = "SELECT * FROM persona WHERE nombre=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.nombre);
            rs=ps.executeQuery();
            
            if (rs.next())
            {
               pe.setId(Integer.parseInt(rs.getString("id")));
               pe.setNombre(rs.getString("nombre"));
               pe.setApellido(rs.getString("apellido"));
               return true;
            }
            return false; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            }

        }
    }
}
