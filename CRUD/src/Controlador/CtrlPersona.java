package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlPersona implements ActionListener {

    private Persona mod;
    private ConsultasPersona modC;
    private Clientes frm;

    public CtrlPersona(Persona mod, ConsultasPersona modC, Clientes frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frm.btnGuardar)) {
            String nombre = frm.txtnombre.getText();
            String apellido = frm.txtapellido.getText();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setId(Integer.parseInt(frm.txtid.getText())); // Asigna el valor del campo txtid al ID de mod
                mod.setNombre(nombre);
                mod.setApellido(apellido);

                if (modC.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRO FALLIDO");
                    limpiar();
                }
            }
        }
        if (e.getSource().equals(frm.btnModificar)) {
            mod.setId(Integer.parseInt(frm.txtid.getText()));
            mod.setNombre(frm.txtnombre.getText());
            mod.setApellido(frm.txtapellido.getText());
            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "MODIFICACIÓN FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            mod.setId(Integer.parseInt(frm.txtid.getText()));
            if (modC.eliminar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ELIMINACIÓN FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setNombre(frm.txtnombre.getText());
            if (modC.buscar(mod)) {
                frm.txtid.setText(String.valueOf(mod.getId()));
                frm.txtnombre.setText(mod.getNombre());
                frm.txtapellido.setText(mod.getApellido());
            } else {
                JOptionPane.showMessageDialog(null, "BÚSQUEDA FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }

    public void limpiar() {
        frm.txtid.setText(null);
        frm.txtnombre.setText(null);
        frm.txtapellido.setText(null);
    }
}
