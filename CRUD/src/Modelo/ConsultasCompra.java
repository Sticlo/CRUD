
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasCompra extends Conexion {

    public boolean agregarCompra(compra compra) {
        Connection con = getConexion();
        String sql = "INSERT INTO compra (id_compra, total, sub_total, descuento, iva, cantidad, id_producto, rut) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, compra.getIdCompra());
            ps.setInt(2, compra.getTotal());
            ps.setInt(3, compra.getSubTotal());
            ps.setInt(4, compra.getDescuento());
            ps.setInt(5, compra.getIva());
            ps.setInt(6, compra.getCantidad());
            ps.setInt(7, compra.getIdProducto());
            ps.setInt(8, compra.getRut());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean modificarCompra(compra compra) {
        Connection con = getConexion();
        String sql = "UPDATE compra SET total = ?, sub_total = ?, descuento = ?, iva = ?, cantidad = ?, id_producto = ?, rut = ? WHERE id_compra = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, compra.getTotal());
            ps.setInt(2, compra.getSubTotal());
            ps.setInt(3, compra.getDescuento());
            ps.setInt(4, compra.getIva());
            ps.setInt(6, compra.getCantidad());
            ps.setInt(7, compra.getIdProducto());
            ps.setInt(8, compra.getRut());
            ps.setInt(9, compra.getIdCompra());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public boolean eliminarCompra(int idCompra) {
        Connection con = getConexion();
        String sql = "DELETE FROM compra WHERE id_compra = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarConexion(con);
        }
    }

    public compra obtenerCompra(int idCompra) {
        Connection con = getConexion();
        String sql = "SELECT * FROM compra WHERE id_compra = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    compra compra = new compra();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setTotal(rs.getInt("total"));
                    compra.setSubTotal(rs.getInt("sub_total"));
                    compra.setDescuento(rs.getInt("descuento"));
                    compra.setIva(rs.getInt("iva"));
                   // compra.setPorcentajeIva(rs.getInt("porcentaje_iva"));
                    compra.setCantidad(rs.getInt("cantidad"));
                    compra.setIdProducto(rs.getInt("id_producto"));
                    compra.setRut(rs.getInt("rut"));
                    return compra;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return null;
    }

    public List<compra> obtenerTodasLasCompras() {
        List<compra> compras = new ArrayList<>();
        Connection con = getConexion();
        String sql = "SELECT * FROM compra";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                compra compra = new compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setTotal(rs.getInt("total"));
                compra.setSubTotal(rs.getInt("sub_total"));
                compra.setDescuento(rs.getInt("descuento"));
                compra.setIva(rs.getInt("iva"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setIdProducto(rs.getInt("id_producto"));
                compra.setRut(rs.getInt("rut"));
                
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return compras;
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
