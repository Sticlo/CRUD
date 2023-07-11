package Controlador;

import Modelo.empleados;
import Modelo.ConsultasEmpleados;
import Vista.Empleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlEmpleados implements ActionListener {

    private empleados mod;
    private ConsultasEmpleados modC;
    private Empleados frm;

    public CtrlEmpleados(empleados mod, ConsultasEmpleados modC, Empleados frm) {
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
            String genero=frm.txtgenero.getSelectedItem().toString();
            String fecha_de_nacimiento=frm.txtfecha_nacimiento.getText();
            String cargo=frm.txtcargo.getText();
            int documento=Integer.parseInt(frm.txtdocumento.getText());
            int telefono=Integer.parseInt(frm.txttelefono.getText());
            int salario = Integer.parseInt(frm.txtsalario.getText());
            int cantidad_ventas =  0;

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || genero.isEmpty() || fecha_de_nacimiento.isEmpty() || cargo.isEmpty() || String.valueOf(documento).isEmpty() || String.valueOf(telefono).isEmpty())  {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setId_empleado(Integer.parseInt(frm.txtid.getText()));
                mod.setNombre(nombre);
                mod.setApellido(apellido);
                mod.setCorreo(correo);
                mod.setGenero(genero);
                mod.setFecha_de_nacimiento(fecha_de_nacimiento);
                mod.setCargo(cargo);
                mod.setDocumento(Integer.parseInt(frm.txtdocumento.getText()));
                mod.setTelefono(Integer.parseInt(frm.txttelefono.getText()));
                mod.setSalario(Integer.parseInt(frm.txtsalario.getText()));
                mod.setCantidad_ventas(0);
                
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
            String correo = frm.txtcorreo.getText();
            String genero = frm.txtgenero.getSelectedItem().toString();
            String fecha_de_nacimiento = frm.txtfecha_nacimiento.getText();
            String cargo=frm.txtcargo.getText();
            int documento=Integer.parseInt(frm.txtdocumento.getText());
            int telefono=Integer.parseInt(frm.txttelefono.getText());
            
            
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()|| genero.isEmpty()|| fecha_de_nacimiento.isEmpty() ){
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            } else {
                mod.setId_empleado(Integer.parseInt(frm.txtid.getText()));
                mod.setNombre(frm.txtnombre.getText());
                mod.setApellido(frm.txtapellido.getText());
                mod.setCorreo(frm.txtcorreo.getText());
                mod.setGenero(frm.txtgenero.getSelectedItem().toString());
                mod.setFecha_de_nacimiento(frm.txtfecha_nacimiento.getText());
                mod.setCargo(frm.txtcargo.getText());
                mod.setDocumento(Integer.parseInt(frm.txtdocumento.getText()));
                mod.setTelefono(Integer.parseInt(frm.txttelefono.getText()));
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
            mod.setId_empleado(Integer.parseInt(frm.txtid.getText()));
            if (modC.eliminar(mod)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ELIMINACIÓN FALLIDA");
                limpiar();
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setId_empleado(Integer.parseInt(frm.txtid.getText()));
            if (modC.buscar(mod)) {
                frm.txtid.setText(String.valueOf(mod.getId_empleado()));
                frm.txtnombre.setText(mod.getNombre());
                frm.txtapellido.setText(mod.getApellido());
                frm.txtcorreo.setText(mod.getCorreo());
                frm.txtgenero.setSelectedItem(mod.getGenero());
                frm.txtfecha_nacimiento.setText(mod.getFecha_de_nacimiento());
                frm.txtcargo.setText(mod.getCargo());
                frm.txtdocumento.setText(String.valueOf(mod.getDocumento()));
                frm.txttelefono.setText(String.valueOf(mod.getTelefono()));
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
        frm.txtfecha_nacimiento.setText(null);
        frm.txtcargo.setText(null);
        frm.txtdocumento.setText(null);
        frm.txttelefono.setText(null);
    }
}
