
package Controlador;

import Modelo.producto;
import Modelo.ConsultasProducto;
import Vista.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class CtrlProducto implements ActionListener {

    private producto mod;
    private ConsultasProducto modC;
    private Producto frm;

    public CtrlProducto(producto mod, ConsultasProducto modC, Producto frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);

        // Agregar los items al ComboBox de categoría
        frm.cmbCategoria.addItem("TECLADO");
        frm.cmbCategoria.addItem("MOUSE");
        frm.cmbCategoria.addItem("PANTALLA");
        

        // Manejar el evento de selección de categoría
        frm.cmbCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarSubcategorias();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            String idProducto = frm.txtIdProducto.getText();
            String nombre = frm.txtNombre.getText();
            String descripcion = frm.txtDescripcion.getText();
            int iva = Integer.parseInt(frm.txtIVA.getText());
            int precio = Integer.parseInt(frm.txtPrecio.getText());
            String serializado = frm.txtSerializado.getText();
            String categoria = (String) frm.cmbCategoria.getSelectedItem();
            String subcategoria = (String) frm.cmbSubcategoria.getSelectedItem();

            if (nombre.isEmpty() || descripcion.isEmpty() || serializado.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setIdProducto(Integer.parseInt(frm.txtIdProducto.getText()));
                mod.setNombre(nombre);
                mod.setDescripcion(descripcion);
                mod.setIva(iva);
                mod.setPrecio(precio);
                mod.setSerializado(serializado);
                mod.setCategoria(categoria);
                mod.setSubcategoria(subcategoria);

                if (modC.agregarProducto(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado exitosamente");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el registro");
                }
            }
        }
        if (e.getSource() == frm.btnModificar) {
            String nombre = frm.txtNombre.getText();
            String descripcion = frm.txtDescripcion.getText();
            int iva = Integer.parseInt(frm.txtIVA.getText());
            int precio = Integer.parseInt(frm.txtPrecio.getText());
            String serializado = frm.txtSerializado.getText();
            String categoria = (String) frm.cmbCategoria.getSelectedItem();
            String subcategoria = (String) frm.cmbSubcategoria.getSelectedItem();

            if (nombre.isEmpty() || descripcion.isEmpty() || serializado.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setIdProducto(Integer.parseInt(frm.txtIdProducto.getText()));
                mod.setNombre(nombre);
                mod.setDescripcion(descripcion);
                mod.setIva(iva);
                mod.setPrecio(precio);
                mod.setSerializado(serializado);
                mod.setCategoria(categoria);
                mod.setSubcategoria(subcategoria);

                if (modC.modificarProducto(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar el registro");
                }
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            int idProducto = Integer.parseInt(frm.txtIdProducto.getText());

            if (modC.eliminarProducto(idProducto)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            int idProducto = Integer.parseInt(frm.txtIdProducto.getText());

            producto productoEncontrado = modC.obtenerProducto(idProducto);
            if (productoEncontrado != null) {
                frm.txtNombre.setText(productoEncontrado.getNombre());
                frm.txtDescripcion.setText(productoEncontrado.getDescripcion());
                frm.txtIVA.setText(String.valueOf(productoEncontrado.getIva()));
                frm.txtPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
                frm.txtSerializado.setText(productoEncontrado.getSerializado());
                frm.cmbCategoria.setSelectedItem(productoEncontrado.getCategoria());
                frm.cmbSubcategoria.setSelectedItem(productoEncontrado.getSubcategoria());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID especificado");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void actualizarSubcategorias() {
        String categoriaSeleccionada = (String) frm.cmbCategoria.getSelectedItem();
        frm.cmbSubcategoria.removeAllItems();

        // Obtener las subcategorías correspondientes a la categoría seleccionada y agregarlas al ComboBox de subcategoría
        if (categoriaSeleccionada.equals("TECLADO")) {
            frm.cmbSubcategoria.addItem("GAMER");
            frm.cmbSubcategoria.addItem("OFICINA");
            frm.cmbSubcategoria.addItem("CORPORATIVO");
        } else if (categoriaSeleccionada.equals("MOUSE")) {
            frm.cmbSubcategoria.addItem("GAMER");
            frm.cmbSubcategoria.addItem("OFICINA");
            frm.cmbSubcategoria.addItem("CORPORATIVO");
        } else if (categoriaSeleccionada.equals("PANTALLA")) {
            frm.cmbSubcategoria.addItem("MONITOR");
            frm.cmbSubcategoria.addItem("TV");
            frm.cmbSubcategoria.addItem("VIDEO BEAM");
        }
    }

    public void limpiarCampos() {
        frm.txtIdProducto.setText("");
        frm.txtNombre.setText("");
        frm.txtDescripcion.setText("");
        frm.txtIVA.setText("");
        frm.txtPrecio.setText("");
        frm.txtSerializado.setText("");
        frm.cmbCategoria.setSelectedIndex(0);
        frm.cmbSubcategoria.removeAllItems();
    }
}