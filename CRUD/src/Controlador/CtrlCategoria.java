package Controlador;

import Modelo.Categoria;
import Modelo.ConsultasCategoria;
import Vista.Categorias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlCategoria implements ActionListener {

    private Categoria mod;
    private ConsultasCategoria modC;
    private Categorias frm;

    public CtrlCategoria(Categoria mod, ConsultasCategoria modC, Categorias frm) {
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
            mod.setId_categoria(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "REGISTRO FALLIDO");
            }
        }
        if (e.getSource() == frm.btnModificar) {
            mod.setId_categoria(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "MODIFICACIÓN FALLIDA");
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            int id = Integer.parseInt(frm.txtId.getText());
            if (modC.eliminar(id)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ELIMINACIÓN FALLIDA");
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            int id = Integer.parseInt(frm.txtId.getText());
            Categoria c = modC.buscar(id);
            if (c != null) {
                frm.txtNombre.setText(c.getNombre());
                frm.txtDescripcion.setText(c.getDescripcion());
            } else {
                JOptionPane.showMessageDialog(null, "BÚSQUEDA FALLIDA");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }

    public void limpiar() {
        frm.txtId.setText("");
        frm.txtNombre.setText("");
        frm.txtDescripcion.setText("");
    }
}
