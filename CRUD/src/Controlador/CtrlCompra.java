package Controlador;

import Modelo.compra;
import Modelo.ConsultasCompra;
import Modelo.ConsultasProducto;
import Modelo.producto;
import Vista.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlCompra implements ActionListener {

    private compra mod;
    private ConsultasCompra modC;
    private Compras frm;

    public CtrlCompra(compra mod, ConsultasCompra modC, Compras frm) {
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
        int idCompra = Integer.parseInt(frm.txtIdCompra.getText());
        int cantidad = Integer.parseInt(frm.txtCantidad.getText());
        int idProducto = Integer.parseInt(frm.txtIdProducto.getText());
        int rut = Integer.parseInt(frm.txtRut.getText());

        int[] precioYIVA = obtenerPrecioYIVAProducto(idProducto);
        int precioProducto = precioYIVA[0];
        int ivaProducto = precioYIVA[1];

        int subTotal = precioProducto * cantidad;
        int descuento = obtenerDescuento(subTotal);
        int total = calcularTotal(subTotal, descuento, ivaProducto);

            mod.setIdCompra(idCompra);
            mod.setTotal(total);
            mod.setSubTotal(subTotal);
            mod.setDescuento(descuento);
            mod.setIva(ivaProducto);
            mod.setCantidad(cantidad);
            mod.setIdProducto(idProducto);
            mod.setRut(rut);

            if (modC.agregarCompra(mod)) {
                JOptionPane.showMessageDialog(null, "Compra registrada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la compra");
            }
        }
        if (e.getSource() == frm.btnModificar) {
            int idCompra = Integer.parseInt(frm.txtIdCompra.getText());
            int iva = Integer.parseInt(frm.txtIVA.getText());
            int cantidad = Integer.parseInt(frm.txtCantidad.getText());
            int idProducto = Integer.parseInt(frm.txtIdProducto.getText());
            int rut = Integer.parseInt(frm.txtRut.getText());

            int precioProducto = obtenerPrecioProducto(idProducto);
            int subTotal = precioProducto * cantidad;
            int descuento = obtenerDescuento(subTotal);
            int total = calcularTotal(subTotal, descuento, iva);

            mod.setIdCompra(idCompra);
            mod.setTotal(total);
            mod.setSubTotal(subTotal);
            mod.setDescuento(descuento);
            mod.setIva(iva);
            mod.setCantidad(cantidad);
            mod.setIdProducto(idProducto);
            mod.setRut(rut);

            if (modC.modificarCompra(mod)) {
                JOptionPane.showMessageDialog(null, "Compra modificada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar la compra");
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            int idCompra = Integer.parseInt(frm.txtIdCompra.getText());

            if (modC.eliminarCompra(idCompra)) {
                JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la compra");
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            int idCompra = Integer.parseInt(frm.txtIdCompra.getText());

            compra compraEncontrada = modC.obtenerCompra(idCompra);
            if (compraEncontrada != null) {
                frm.txtTotal.setText(String.valueOf(compraEncontrada.getTotal()));
                frm.txtSubTotal.setText(String.valueOf(compraEncontrada.getSubTotal()));
                frm.txtDescuento.setText(String.valueOf(compraEncontrada.getDescuento()));
                frm.txtIVA.setText(String.valueOf(compraEncontrada.getIva()));
                frm.txtCantidad.setText(String.valueOf(compraEncontrada.getCantidad()));
                frm.txtIdProducto.setText(String.valueOf(compraEncontrada.getIdProducto()));
                frm.txtRut.setText(String.valueOf(compraEncontrada.getRut()));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna compra con el ID especificado");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiarCampos();
        }
    }

private int obtenerPrecioProducto(int idProducto) {
    ConsultasProducto consultasProducto = new ConsultasProducto(); // Instancia de la clase ConsultasProducto

    producto productoEncontrado = consultasProducto.obtenerProducto(idProducto); // Obtener el producto por su ID

    if (productoEncontrado != null) {
        return productoEncontrado.getPrecio(); // Retornar el precio del producto encontrado
    } else {
        return 0; // Retornar 0 en caso de que no se encuentre el producto
    }
}

private int obtenerDescuento(int subTotal) {
    
    // Ejemplo: Si el subtotal es mayor a 100000, se aplica un descuento del 10%
    if (subTotal > 100000) {
        int descuento = (subTotal * 10) / 100;
        return descuento;
    } else {
        return 0; // Si no se cumple ninguna condición de descuento, retorna 0
    }
}
private int[] obtenerPrecioYIVAProducto(int idProducto) {
    ConsultasProducto consultasProducto = new ConsultasProducto(); // Instancia de la clase ConsultasProducto

    producto productoEncontrado = consultasProducto.obtenerProducto(idProducto); // Obtener el producto por su ID

    if (productoEncontrado != null) {
        int precio = productoEncontrado.getPrecio(); // Obtener el precio del producto
        int iva = productoEncontrado.getIva(); // Obtener el IVA del producto
        int[] precioYIVA = {precio, iva};
        return precioYIVA; // Retornar el precio y el IVA del producto encontrado
    } else {
        int[] precioYIVA = {0, 0};
        return precioYIVA; // Retornar 0 en caso de que no se encuentre el producto
    }
}

    private int calcularTotal(int subTotal, int descuento, int iva) {
        int total = subTotal - descuento;
        total += (total * iva) / 100;
        return total;
    }

    private void limpiarCampos() {
        frm.txtIdCompra.setText("");
        frm.txtTotal.setText("");
        frm.txtSubTotal.setText("");
        frm.txtDescuento.setText("");
        frm.txtIVA.setText("");
        frm.txtCantidad.setText("");
        frm.txtIdProducto.setText("");
        frm.txtRut.setText("");
    }
}
