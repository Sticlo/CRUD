package Controlador;

import Modelo.cliente;
import Modelo.ConsultasCliente;
import Vista.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlPersona implements ActionListener {

    private cliente mod;
    private ConsultasCliente modC;
    private Clientes frm;

    public CtrlPersona(cliente mod, ConsultasCliente modC, Clientes frm) {
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
            String correo=frm.txtcorreo.getText();
            String genero=frm.txtgenero.getText();
            String fecha_de_nacimiento=frm.txtfecha_nacimiento.getText();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setId_cliente(Integer.parseInt(frm.txtid.getText()));
                mod.setNombre(nombre);
                mod.setApellido(apellido);
                mod.setCorreo(correo);
                mod.setGenero(genero);
                mod.setFecha_de_nacimiento(fecha_de_nacimiento);
                
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
            String nombre = frm.txtnombre.getText();
            String apellido = frm.txtapellido.getText();
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setId_cliente(Integer.parseInt(frm.txtid.getText()));
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
        }
        if (e.getSource() == frm.btnEliminar) {
            mod.setId_cliente(Integer.parseInt(frm.txtid.getText()));
            if (modC.eliminar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ELIMINACIÓN FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setId_cliente(Integer.parseInt(frm.txtid.getText()));
            if (modC.buscar(mod)) {
                frm.txtid.setText(String.valueOf(mod.getId_cliente()));
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
        frm.txtcorreo.setText(null);
        frm.txtgenero.setText(null);
        frm.txtfecha_nacimiento.setText(null);
    }
}
