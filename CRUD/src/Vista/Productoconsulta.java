package Vista;

import javax.swing.JOptionPane;
import Controlador.CtrlPersona;
import Modelo.Conexion;
import java.awt.BorderLayout;
import java.io.FileOutputStream;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Productoconsulta extends javax.swing.JPanel {

    private CtrlPersona controlador;

    public Productoconsulta() {
        initComponents();
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jtpersona.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT id_producto,nombre,descripcion,iva,precio,serializado, categoria,subcategoria FROM producto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("DESCRIPCION");
            modelo.addColumn("IVA");
            modelo.addColumn("PRECIO");
            modelo.addColumn("SERIALIZADO");
            modelo.addColumn("CATEGORIA");
            modelo.addColumn("SUBCATEGORIA");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE ENCUENTRO NADIE");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        BIENVENIDOS = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpersona = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(795, 640));

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setPreferredSize(new java.awt.Dimension(795, 640));

        BIENVENIDOS.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        BIENVENIDOS.setForeground(new java.awt.Color(0, 0, 0));
        BIENVENIDOS.setText("PRODUCTOS REGISTRADOS");

        jtpersona.setBackground(new java.awt.Color(255, 255, 255));
        jtpersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCION", "IVA", "PRECIO", "SERIALIZADO", "CATEGORIA", "SUBCATEGORIA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtpersona);

        jButton1.setBackground(new java.awt.Color(0, 0, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("VOLVER");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("PDF");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentLayout.createSequentialGroup()
                                .addComponent(BIENVENIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BIENVENIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ShowJPanel(new Producto());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            String filePath = System.getProperty("user.home") + "/Downloads/tabla_productos.pdf";
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Crear encabezado personalizado
            com.itextpdf.text.pdf.PdfPTable headerTable = new com.itextpdf.text.pdf.PdfPTable(1);
            headerTable.setWidthPercentage(100);
            headerTable.getDefaultCell().setBackgroundColor(com.itextpdf.text.BaseColor.GRAY);
            headerTable.getDefaultCell().setPadding(10);
            headerTable.getDefaultCell().setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            headerTable.addCell(new com.itextpdf.text.Phrase("TODOCOMPUTADORES", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.WHITE)));
            headerTable.addCell(""); // Espacio vacío para ajustar la posición vertical
            document.open();
            document.add(headerTable);

            com.itextpdf.text.pdf.PdfPTable pdfTable = new com.itextpdf.text.pdf.PdfPTable(jtpersona.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Asignar colores diferentes a cada columna
            com.itextpdf.text.pdf.PdfPCell[] cells = new com.itextpdf.text.pdf.PdfPCell[jtpersona.getColumnCount()];
            com.itextpdf.text.BaseColor[] columnColors = {
                com.itextpdf.text.BaseColor.WHITE,
                com.itextpdf.text.BaseColor.YELLOW,
                com.itextpdf.text.BaseColor.CYAN,
                com.itextpdf.text.BaseColor.GREEN
            };

            for (int i = 0; i < jtpersona.getColumnCount(); i++) {
                // Asegurarse de que no se exceda la longitud del array columnColors
                int colorIndex = i % columnColors.length;
                cells[i] = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(jtpersona.getColumnName(i), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD)));
                cells[i].setBackgroundColor(columnColors[colorIndex]);
                pdfTable.addCell(cells[i]);
            }

            // Agregar filas de datos al PDF
            for (int rows = 0; rows < jtpersona.getRowCount(); rows++) {
                for (int cols = 0; cols < jtpersona.getColumnCount(); cols++) {
                    // Asegurarse de que no se exceda la longitud del array columnColors
                    int colorIndex = cols % columnColors.length;
                    cells[cols] = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(jtpersona.getModel().getValueAt(rows, cols).toString(), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10)));
                    cells[cols].setBackgroundColor(columnColors[colorIndex]);
                    pdfTable.addCell(cells[cols]);
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "El archivo PDF se generó exitosamente. Ruta: " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ShowJPanel(JPanel p) {
        p.setSize(795, 640);
        p.setLocation(0, 0);
        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BIENVENIDOS;
    public javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtpersona;
    // End of variables declaration//GEN-END:variables
}
