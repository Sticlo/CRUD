package Controlador;

import Modelo.empleados;
import Modelo.ConsultasEmpleados;
import Modelo.ConsultasProveedor;
import Modelo.proveedor;
import Vista.Empleados;
import Vista.Proveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlProveedor implements ActionListener {

    private proveedor mod;
    private ConsultasProveedor modC;
    private Proveedores frm;

    public CtrlProveedor(proveedor mod, ConsultasProveedor modC, Proveedores frm) {
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
            int documento=Integer.parseInt(frm.txtdocumento.getText());
            String direccion = frm.txtdireccion.getText();
            int telefono=Integer.parseInt(frm.txttelefono.getText());
            String correo = frm.txtcorreo.getText();


            if (nombre.isEmpty() || correo.isEmpty() || String.valueOf(documento).isEmpty() || String.valueOf(telefono).isEmpty())  {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setRut(Integer.parseInt(frm.rut.getText()));
                mod.setNombre(nombre);
                mod.setDocumento(Integer.parseInt(frm.txtdocumento.getText()));
                mod.setDireccion(direccion);
                mod.setTelefono(Integer.parseInt(frm.txttelefono.getText()));
                mod.setCorreo(correo);

                
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
            int documento=Integer.parseInt(frm.txtdocumento.getText());
            String direccion = frm.txtdireccion.getText();
            int telefono=Integer.parseInt(frm.txttelefono.getText());
            String correo = frm.txtcorreo.getText();

            
            
            if (nombre.isEmpty() || correo.isEmpty()|| String.valueOf(documento).isEmpty() || direccion.isEmpty() || String.valueOf(telefono).isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setRut(Integer.parseInt(frm.rut.getText()));
                mod.setNombre(frm.txtnombre.getText());
                mod.setDocumento(Integer.parseInt(frm.txtdocumento.getText()));
                mod.setDireccion(frm.txtdireccion.getText());
                mod.setTelefono(Integer.parseInt(frm.txttelefono.getText()));
                mod.setCorreo(frm.txtcorreo.getText());

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
            mod.setRut(Integer.parseInt(frm.rut.getText()));
            if (modC.eliminar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ELIMINACIÓN FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setRut(Integer.parseInt(frm.rut.getText()));
            if (modC.buscar(mod)) {
                frm.rut.setText(String.valueOf(mod.getRut()));
                frm.txtnombre.setText(mod.getNombre());
                frm.txtdocumento.setText(String.valueOf(mod.getDocumento()));
                frm.txtdireccion.setText(mod.getDireccion());
                frm.txttelefono.setText(String.valueOf(mod.getTelefono()));
                frm.txtcorreo.setText(mod.getCorreo());
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
        frm.rut.setText(null);
        frm.txtnombre.setText(null);
        frm.txtcorreo.setText(null);
        frm.txtdocumento.setText(null);
        frm.txttelefono.setText(null);
        frm.txtdireccion.setText(null);
    }
}