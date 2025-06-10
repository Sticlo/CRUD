package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultasCategoria extends Conexion {

    public boolean registrar(Categoria cat) {
        Connection con = getConexion();
        String sql = "INSERT INTO categoria (id_categoria, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cat.getId_categoria());
            ps.setString(2, cat.getNombre());
            ps.setString(3, cat.getDescripcion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR" + e);
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { }
        }
    }

    public boolean modificar(Categoria cat) {
        Connection con = getConexion();
        String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE id_categoria=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat.getNombre());
            ps.setString(2, cat.getDescripcion());
            ps.setInt(3, cat.getId_categoria());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR" + e);
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { }
        }
    }

    public boolean eliminar(int id) {
        Connection con = getConexion();
        String sql = "DELETE FROM categoria WHERE id_categoria=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR" + e);
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { }
        }
    }

    public Categoria buscar(int id) {
        Connection con = getConexion();
        String sql = "SELECT * FROM categoria WHERE id_categoria=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria c = new Categoria();
                    c.setId_categoria(rs.getInt("id_categoria"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDescripcion(rs.getString("descripcion"));
                    return c;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO BUSCAR" + e);
        } finally {
            try { con.close(); } catch (SQLException e) { }
        }
        return null;
    }

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        Connection con = getConexion();
        String sql = "SELECT * FROM categoria";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LISTAR" + e);
        } finally {
            try { con.close(); } catch (SQLException e) { }
        }
        return lista;
    }
}
