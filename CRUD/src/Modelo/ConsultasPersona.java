package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasPersona extends Conexion {
    
    public boolean registrar(Persona pe) {
    PreparedStatement ps = null;
    Connection con = getConexion();

    // Verificar si la cédula ya existe en la tabla
    String verificarSql = "SELECT COUNT(*) FROM persona WHERE id = ?";
    try {
        ps = con.prepareStatement(verificarSql);
        ps.setInt(1, pe.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "La cédula ya existe en la base de datos");
                return false;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar la cédula" + e);
        return false;
    }

    // Insertar el nuevo registro
    String sql = "INSERT INTO persona (id, nombre, apellido) VALUES (?, ?, ?)";
    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, pe.getId());
        ps.setString(2, pe.getNombre());
        ps.setString(3, pe.getApellido());
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR LA CONEXIÓN" + e);
        }
    }
}

    
    public boolean modificar(Persona pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE persona SET nombre=?, apellido=? WHERE id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setString(2, pe.getApellido());
            ps.setInt(3, pe.getId());
            ps.execute();
            return true; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR LA CONEXIÓN" + e);
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
                JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR LA CONEXIÓN" + e);
            }
        }
    }
    
    public boolean buscar(Persona pe) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM persona WHERE nombre=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.nombre);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                pe.setId(Integer.parseInt(rs.getString("id")));
                pe.setNombre(rs.getString("nombre"));
                pe.setApellido(rs.getString("apellido"));
                return true;
            }
            return false; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO BUSCAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR LA CONEXIÓN" + e);
            }
        }
    }
    
    public boolean tabla(Persona pe) {
        DefaultTableModel modelo=new DefaultTableModel ();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id,nombre,apellido FROM persona";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd =rs.getMetaData();
            int cantidadColumnas=rsMd.getColumnCount();
            modelo.addColumn("ID");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("APELLIDO");


            while (rs.next()){
                Object[] filas=new Object [cantidadColumnas];
                for(int i=0;i<cantidadColumnas;i++)
                {
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            return true; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO BUSCAR" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR LA CONEXIÓN" + e);
                return false;
            }
        }
    }
}
