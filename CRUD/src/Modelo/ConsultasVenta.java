package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultasVenta extends Conexion {

    public boolean agregarVenta(Venta venta) {
        Connection con = getConexion();
        String sql = "INSERT INTO venta (id_venta, id_cliente, id_producto, id_empleado, cantidad, total, cantidad_productos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, venta.getIdVenta());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdProducto());
            ps.setInt(4, venta.getIdEmpleado());
            ps.setInt(5, venta.getCantidad());
            ps.setDouble(6, venta.getTotal());
            ps.setInt(7, venta.getCantidadProductos());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean modificarVenta(Venta venta) {
        Connection con = getConexion();
        String sql = "UPDATE venta SET id_cliente=?, id_producto=?, id_empleado=?, cantidad=?, total=?, cantidad_productos=? WHERE id_venta=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, venta.getIdCliente());
            ps.setInt(2, venta.getIdProducto());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setInt(4, venta.getCantidad());
            ps.setDouble(5, venta.getTotal());
            ps.setInt(6, venta.getCantidadProductos());
            ps.setInt(7, venta.getIdVenta());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean eliminarVenta(int idVenta) {
        Connection con = getConexion();
        String sql = "DELETE FROM venta WHERE id_venta=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public Venta buscarVenta(int idVenta) {
        Connection con = getConexion();
        String sql = "SELECT * FROM venta WHERE id_venta=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setIdCliente(rs.getInt("id_cliente"));
                    venta.setIdProducto(rs.getInt("id_producto"));
                    venta.setIdEmpleado(rs.getInt("id_empleado"));
                    venta.setCantidad(rs.getInt("cantidad"));
                    venta.setTotal(rs.getDouble("total"));
                    venta.setCantidadProductos(rs.getInt("cantidad_productos"));
                    return venta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return null;
    }

    public boolean agregarProductoVenta(int idVenta, int idProducto, int cantidad) {
        Connection con = getConexion();
        String sql = "INSERT INTO venta_producto (id_venta, id_producto, cantidad) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }


    public ArrayList<Venta> obtenerTodasVentas() {
        ArrayList<Venta> ventas = new ArrayList<>();
        Connection con = getConexion();
        String sql = "SELECT * FROM venta";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdProducto(rs.getInt("id_producto"));
                venta.setIdEmpleado(rs.getInt("id_empleado"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setTotal(rs.getDouble("total"));
                venta.setCantidadProductos(rs.getInt("cantidad_productos"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return ventas;
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
