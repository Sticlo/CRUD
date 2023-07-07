package Modelo;

import java.sql.ResultSet;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasEmpleados extends Conexion {

    public boolean registrar(cliente pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Verificar si la cédula ya existe en la tabla
        String verificarSql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
        try {
            ps = con.prepareStatement(verificarSql);
            ps.setInt(1, pe.getId_cliente());
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
        String sql = "INSERT INTO cliente (id_cliente, nombre, apellido,correo,genero,fecha_de_nacimiento) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getId_cliente());
            ps.setString(2, pe.getNombre());
            ps.setString(3, pe.getApellido());
            ps.setString(4, pe.getCorreo());
            ps.setString(5, pe.getGenero());
            ps.setString(6, pe.getFecha_de_nacimiento());
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

    public boolean modificar(cliente pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE cliente SET nombre=?, apellido=? ,correo=?,genero=?,fecha_de_nacimiento=? WHERE id_cliente=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setString(2, pe.getApellido());
            ps.setString(3, pe.getCorreo());
            ps.setString(4, pe.getGenero());
            ps.setString(5, pe.getFecha_de_nacimiento());
            ps.setInt(6, pe.getId_cliente());
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

    public boolean eliminar(cliente pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id_cliente=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getId_cliente());
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

    public boolean buscar(cliente pe) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente WHERE id_cliente=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.id_cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                pe.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
                pe.setNombre(rs.getString("nombre"));
                pe.setApellido(rs.getString("apellido"));
                pe.setCorreo(rs.getString("correo"));
                pe.setGenero(rs.getString("genero"));
                pe.setFecha_de_nacimiento(rs.getString("fecha_de_nacimiento"));
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

    public ArrayList<cliente> obtenerTodos() {
        ArrayList<cliente> personas = new ArrayList<>();
        try {
            Connection conn = getConexion();
            String sql = "SELECT * FROM cliente";
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
                personas.add(persona);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
