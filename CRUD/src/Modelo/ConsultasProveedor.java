package Modelo;

import java.sql.ResultSet;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasProveedor extends Conexion {

    public boolean registrar(proveedor pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Verificar si la cédula ya existe en la tabla
        String verificarSql = "SELECT COUNT(*) FROM provedor WHERE rut = ?";
        try {
            ps = con.prepareStatement(verificarSql);
            ps.setInt(1, pe.getRut());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "el rut del proveedor ya existe en la base de datos");
                    return false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el rut" + e);
            return false;
        }
        // Insertar el nuevo registro
        String sql = "INSERT INTO provedor (rut, nombre, documento,direccion,telefono,correo) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getRut());
            ps.setString(2, pe.getNombre());
            ps.setInt(3, pe.getDocumento());
            ps.setString(4, pe.getDireccion());
            ps.setInt(5,pe.getTelefono());
            ps.setString(6,pe.getCorreo());
            
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

    public boolean modificar(proveedor pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE provedor SET nombre=?,documento=?, direccion=?, telefono=?,correo=? WHERE rut=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setInt(2, pe.getDocumento());
            ps.setString(3,pe.getDireccion());
            ps.setInt(4, pe.getTelefono());
            ps.setString(5, pe.getCorreo());
            
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

    public boolean eliminar(proveedor pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM provedor WHERE rut=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getRut());
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

    public boolean buscar(proveedor pe) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM provedor WHERE rut=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.rut);
            rs = ps.executeQuery();
            if (rs.next()) {
                pe.setRut(Integer.parseInt(rs.getString("rut")));
                pe.setNombre(rs.getString("nombre"));
                pe.setDocumento(Integer.parseInt(rs.getString("documento")));
                pe.setDireccion(rs.getString(rs.getString("direccion")));
                pe.setTelefono(Integer.parseInt(rs.getString("telefono")));
                pe.setCorreo(rs.getString("correo"));
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

    public ArrayList<proveedor> obtenerTodos() {
        ArrayList<proveedor> personas = new ArrayList<>();
        try {
            Connection conn = getConexion();
            String sql = "SELECT * FROM provedor";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String apellido = resultSet.getString("APELLIDO");
                cliente persona = new cliente();
               // persona.setId_cliente(id_);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                //personas.add(proveedor);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
