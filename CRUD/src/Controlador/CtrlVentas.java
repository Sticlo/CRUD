package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Venta;
import Modelo.ConsultasVenta;
import Modelo.producto;
import Vista.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlVentas implements ActionListener {

    private Venta mod;
    private ConsultasVenta modC;
    private Ventas frm;

    public CtrlVentas(Venta mod, ConsultasVenta modC, Ventas frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            int idVenta = Integer.parseInt(frm.txtIdVenta.getText());
            int idCliente = Integer.parseInt(frm.txtIdCliente.getText());
            int idProducto = Integer.parseInt(frm.txtIdProducto.getText());
            int idEmpleado = Integer.parseInt(frm.txtIdEmpleado.getText());
            int cantidad = Integer.parseInt(frm.txtCantidad.getText());

            double precio = obtenerPrecioProducto(idProducto);
            double iva = obtenerIvaProducto(idProducto);
            double total = calcularTotalVenta(precio, iva, cantidad);

            mod.setIdVenta(idVenta);
            mod.setIdCliente(idCliente);
            mod.setIdProducto(idProducto);
            mod.setIdEmpleado(idEmpleado);
            mod.setCantidad(cantidad);
            mod.setTotal(total);

            if (modC.agregarVenta(mod)) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar más productos a la venta?", "Agregar productos", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    agregarProductosVenta(idVenta); // Llama al método para agregar más productos a la venta
                } else {
                    JOptionPane.showMessageDialog(null, "Venta registrada exitosamente");
                    limpiarCampos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la venta");
            }
        }
        if (e.getSource() == frm.btnModificar) {
            int idVenta = Integer.parseInt(frm.txtIdVenta.getText());
            int idCliente = Integer.parseInt(frm.txtIdCliente.getText());
            int idProducto = Integer.parseInt(frm.txtIdProducto.getText());
            int idEmpleado = Integer.parseInt(frm.txtIdEmpleado.getText());
            int cantidad = Integer.parseInt(frm.txtCantidad.getText());

            double precio = obtenerPrecioProducto(idProducto);
            double iva = obtenerIvaProducto(idProducto);
            double total = calcularTotalVenta(precio, iva, cantidad);

            mod.setIdVenta(idVenta);
            mod.setIdCliente(idCliente);
            mod.setIdProducto(idProducto);
            mod.setIdEmpleado(idEmpleado);
            mod.setCantidad(cantidad);
            mod.setTotal(total);

            if (modC.modificarVenta(mod)) {
                JOptionPane.showMessageDialog(null, "Venta modificada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar la venta");
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            int idVenta = Integer.parseInt(frm.txtIdVenta.getText());

            if (modC.eliminarVenta(idVenta)) {
                JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la venta");
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            int idVenta = Integer.parseInt(frm.txtIdVenta.getText());

            Venta ventaEncontrada = modC.buscarVenta(idVenta);
            if (ventaEncontrada != null) {
                frm.txtIdCliente.setText(String.valueOf(ventaEncontrada.getIdCliente()));
                frm.txtIdProducto.setText(String.valueOf(ventaEncontrada.getIdProducto()));
                frm.txtIdEmpleado.setText(String.valueOf(ventaEncontrada.getIdEmpleado()));
                frm.txtCantidad.setText(String.valueOf(ventaEncontrada.getCantidad()));
                frm.txtTotal.setText(String.valueOf(ventaEncontrada.getTotal()));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna venta con el ID especificado");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiarCampos();
        }
    }

    public double obtenerPrecioProducto(int idProducto) {
        ConsultasProducto consultasProducto = new ConsultasProducto();
        producto producto = consultasProducto.obtenerProducto(idProducto);
        if (producto != null) {
            return producto.getPrecio();
        } else {
            // Si el producto no existe, se puede lanzar una excepción o retornar un valor predeterminado.
            return 0.0;
        }
    }

    public double obtenerIvaProducto(int idProducto) {
        ConsultasProducto consultasProducto = new ConsultasProducto();
        producto producto = consultasProducto.obtenerProducto(idProducto);
        if (producto != null) {
            return producto.getIva();
        } else {
            // Si el producto no existe, puedes manejarlo de acuerdo a tus requerimientos.
            return 0.0;
        }
    }

    public double calcularTotalVenta(double precio, double iva, int cantidad) {
        double total = precio * (1 + (iva / 100)) * cantidad;
        return total;
    }

    private void agregarProductosVenta(int idVenta) {
        do {
            String inputIdProducto = JOptionPane.showInputDialog("Ingrese el ID del producto a agregar (o deje vacío para terminar):");

            // Verificar si se ingresó un ID de producto
            if (inputIdProducto.isEmpty()) {
                break; // Salir del bucle si no se ingresó ningún ID
            }

            String inputCantidad = JOptionPane.showInputDialog("Ingrese la cantidad del producto:");
            try {
                int idProducto = Integer.parseInt(inputIdProducto);
                int cantidad = Integer.parseInt(inputCantidad);

                if (modC.agregarProductoVenta(idVenta, idProducto, cantidad)) {
                    JOptionPane.showMessageDialog(null, "Producto agregado a la venta exitosamente");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar el producto a la venta");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato de entrada incorrecto");
            }

        } while (true);
    }

    public void limpiarCampos() {
        frm.txtIdVenta.setText("");
        frm.txtIdCliente.setText("");
        frm.txtIdProducto.setText("");
        frm.txtIdEmpleado.setText("");
        frm.txtCantidad.setText("");
        frm.txtTotal.setText("");
    }
}
