
package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasProducto extends Conexion {

    public boolean agregarProducto(producto producto) {
        Connection con = getConexion();
        String sql = "INSERT INTO producto (id_producto, nombre, descripcion, iva, precio, serializado, categoria, subcategoria) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getIva());
            ps.setInt(5, producto.getPrecio());
            ps.setString(6, producto.getSerializado());
            ps.setString(7, producto.getCategoria());
            ps.setString(8, producto.getSubcategoria());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean modificarProducto(producto producto) {
        Connection con = getConexion();
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, iva = ?, precio = ?, serializado = ?, categoria = ? ,subcategoria=? WHERE id_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getIva());
            ps.setInt(4, producto.getPrecio());
            ps.setString(5, producto.getSerializado());
            ps.setString(6, producto.getCategoria());
            ps.setString(7, producto.getSubcategoria());
            ps.setInt(8, producto.getIdProducto());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean eliminarProducto(int idProducto) {
        Connection con = getConexion();
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public producto obtenerProducto(int idProducto) {
        Connection con = getConexion();
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto producto = new producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setIva(rs.getInt("iva"));
                    producto.setPrecio(rs.getInt("precio"));
                    producto.setSerializado(rs.getString("serializado"));
                    producto.setCategoria(rs.getString("categoria"));
                    producto.setSubcategoria(rs.getString("subcategoria"));
                    return producto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return null;
    }

    public List<producto> obtenerTodosLosProductos() {
        List<producto> productos = new ArrayList<>();
        Connection con = getConexion();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                producto producto = new producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIva(rs.getInt("iva"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setSerializado(rs.getString("serializado"));
                producto.setCategoria(rs.getString("categoria"));
                
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return productos;
    }

    private void cerrarConexion(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
