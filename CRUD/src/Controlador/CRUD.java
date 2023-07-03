package Controlador;

import Vista.Dashboard;
import Modelo.Conexion;

public class CRUD {

    Dashboard view = new Dashboard();
    Conexion model = new Conexion();

    public CRUD() {
    }
    public void iniciar() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setTitle("CRUD MULTINIVEL");
                view.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        CRUD ctrl = new CRUD();
        ctrl.iniciar();
    }
}
