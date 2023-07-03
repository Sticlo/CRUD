package Modelo;


import java.sql.ResultSet;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasPersona extends Conexion {
    
    public boolean registrar(   Persona pe) {
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

        String sql = "SELECT * FROM persona WHERE id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pe.id);
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
    
    public ArrayList<Persona> obtenerTodos() {
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            // Obtener la conexión de la clase padre (Conexion en este ejemplo)
            Connection conn = getConexion();

            // Crear una consulta SELECT *
            String sql = "SELECT * FROM persona";

            // Crear un Statement para ejecutar la consulta
            Statement statement = conn.createStatement();

            // Ejecutar la consulta y obtener el resultado en un ResultSet
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterar sobre el ResultSet y crear objetos Persona
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String apellido = resultSet.getString("APELLIDO");

                // Crear un objeto Persona con los datos obtenidos
                Persona persona = new Persona();
                persona.setId(id);
                persona.setNombre(nombre);
                persona.setApellido(apellido);

                personas.add(persona);
            }

            // Cerrar los recursos utilizados
            resultSet.close();
            statement.close();
            // No se cierra la conexión aquí, ya que se obtiene de la clase padre
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }

        return personas;
    }
}
