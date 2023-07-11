package Modelo;

import java.sql.ResultSet;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasEmpleados extends Conexion {

    public boolean registrar(empleados pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // Verificar si la cédula ya existe en la tabla
        String verificarSql = "SELECT COUNT(*) FROM empleado WHERE id_empleado = ?";
        try {
            ps = con.prepareStatement(verificarSql);
            ps.setInt(1, pe.getId_empleado());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "el id del empleado ya existe en la base de datos");
                    return false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el id" + e);
            return false;
        }
        // Insertar el nuevo registro
        String sql = "INSERT INTO empleado (id_empleado, nombre, apellido,correo,genero,fecha_de_nacimiento,cargo,documento,telefono, salario, cantidad_ventas) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getId_empleado());
            ps.setString(2, pe.getNombre());
            ps.setString(3, pe.getApellido());
            ps.setString(4, pe.getCorreo());
            ps.setString(5, pe.getGenero());
            ps.setString(6, pe.getFecha_de_nacimiento());
            ps.setString(7, pe.getCargo());
            ps.setInt(8, pe.getDocumento());
            ps.setInt(9,pe.getTelefono());
            ps.setInt(10, pe.salario);
            ps.setInt(11, 0);
            
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

    public boolean modificar(empleados pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE empleado SET nombre=?, apellido=? ,correo=?,genero=?,fecha_de_nacimiento=?,cargo=?,documento=?,telefono=?, salario=?, cantidad_ventas = ? WHERE id_empleado=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pe.getNombre());
            ps.setString(2, pe.getApellido());
            ps.setString(3, pe.getCorreo());
            ps.setString(4, pe.getGenero());
            ps.setString(5, pe.getFecha_de_nacimiento());
            ps.setString(6, pe.getCargo());
            ps.setInt(7, pe.getDocumento());
            ps.setInt(8, pe.getTelefono());
            ps.setInt(9, pe.getId_empleado());
            ps.setInt(10, pe.getSalario());
            ps.setInt(11, pe.getCantidad_ventas());
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

    public boolean eliminar(empleados pe) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM empleado WHERE id_empleado=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.getId_empleado());
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

    public boolean buscar(empleados pe) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM empleado WHERE id_empleado=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.id_empleado);
            rs = ps.executeQuery();
            if (rs.next()) {
                pe.setId_empleado(Integer.parseInt(rs.getString("id_empleado")));
                pe.setNombre(rs.getString("nombre"));
                pe.setApellido(rs.getString("apellido"));
                pe.setCorreo(rs.getString("correo"));
                pe.setGenero(rs.getString("genero"));
                pe.setFecha_de_nacimiento(rs.getString("fecha_de_nacimiento"));
                pe.setCargo(rs.getString("cargo"));
                pe.setDocumento(Integer.parseInt(rs.getString("documento")));
                pe.setTelefono(Integer.parseInt(rs.getString("telefono")));
                pe.setSalario(Integer.parseInt(rs.getString("salario")));
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
