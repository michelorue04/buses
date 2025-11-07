
package otros;

import Login.Principalfrm;
import config.CConexion; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Papeletapuestas extends javax.swing.JFrame {

    CConexion conexion = new CConexion();

    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;

    public Papeletapuestas() {
        initComponents();
        setLocationRelativeTo(null);
        Listar(); // Se debe llamar aquí para listar las papeletas desde el inicio
    }

    // Método para agregar una papeleta a la base de datos
    void Agregar() {
        String idConductor = txtIdConductor.getText();
        String idBus = txtIdBus.getText();
        String monto = txtMonto.getText();
        String tipoTransaccion = txtTipoTransaccion.getText();
        String fechaEmision = txtFechaEmision.getText();

        if (idConductor.equals("") || idBus.equals("") || monto.equals("") || tipoTransaccion.equals("") || fechaEmision.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
        } else {
            String sql = "INSERT INTO Papeletas(id_conductor, id_bus, monto, tipo_transaccion, fecha_emision) VALUES ('" 
                         + idConductor + "', '" + idBus + "', '" + monto + "', '" + tipoTransaccion + "', '" + fechaEmision + "')";
            try {
                cn = conexion.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Papeleta Insertada...");
                limpiartabla();
                Listar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar papeleta: " + e.getMessage());
            }
        }
    }

    // Método para modificar una papeleta en la base de datos
    void modificar() {
        String idPapeleta = txtIdPapeleta.getText();
        String idConductor = txtIdConductor.getText();
        String idBus = txtIdBus.getText();
        String monto = txtMonto.getText();
        String tipoTransaccion = txtTipoTransaccion.getText();
        String fechaEmision = txtFechaEmision.getText();

        String sql = "UPDATE Papeletas SET id_conductor='" + idConductor + "', id_bus='" + idBus + "', monto=" + monto + ", "
                   + "tipo_transaccion='" + tipoTransaccion + "', fecha_emision='" + fechaEmision + "' WHERE id_papeleta=" + idPapeleta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Papeleta Actualizada...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar papeleta: " + e.getMessage());
        }
    }

    // Método para eliminar una papeleta de la base de datos
    void eliminar() {
        String idPapeleta = txtIdPapeleta.getText();
        String sql = "DELETE FROM Papeletas WHERE id_papeleta=" + idPapeleta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Papeleta Eliminada...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar papeleta: " + e.getMessage());
        }
    }

    // Método para listar las papeletas en la tabla
    void Listar() {
        String sql = "SELECT * FROM Papeletas";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] papeleta = new Object[6]; // Hay 6 columnas en la tabla
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            // Limpia la tabla antes de agregar los nuevos registros
            limpiartabla();
            while (rs.next()) {
                papeleta[0] = rs.getString("id_papeleta");
                papeleta[1] = rs.getString("id_conductor");
                papeleta[2] = rs.getString("id_bus");
                papeleta[3] = rs.getString("monto");
                papeleta[4] = rs.getString("tipo_transaccion");
                papeleta[5] = rs.getString("fecha_emision");
                modelo.addRow(papeleta);
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las papeletas: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla
    void limpiartabla() {
        modelo.setRowCount(0); // Limpiar todas las filas
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtIdPapeleta.setText("");
        txtIdConductor.setText("");
        txtIdBus.setText("");
        txtMonto.setText("");
        txtTipoTransaccion.setText("");
        txtFechaEmision.setText("");
        txtIdConductor.requestFocus();
    }


  



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnNUEVO = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMODIFICAR = new javax.swing.JButton();
        btnAGREGAR = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        txtTipoTransaccion = new javax.swing.JTextField();
        txtIdConductor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdPapeleta = new javax.swing.JTextField();
        txtIdBus = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLADATOS = new javax.swing.JTable();
        BTNSALIR7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Carga papeleta impuesta");

        btnNUEVO.setText("Nuevo");
        btnNUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNUEVOActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnMODIFICAR.setText("Modificar");
        btnMODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMODIFICARActionPerformed(evt);
            }
        });

        btnAGREGAR.setText("Agregar");
        btnAGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGARActionPerformed(evt);
            }
        });

        jLabel4.setText("Monto");

        jLabel6.setText("Fecha Emisión");

        jLabel5.setText("Tipo trasacción");

        jLabel2.setText("Conductor");

        jLabel1.setText("ID_Papeleta");

        jLabel3.setText("Bus");

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Papeleta", "ID_Conductor", "Title ID_Bus", "Monto", "Tipo Trasacción", "Fecha Emisión"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLADATOS);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdPapeleta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIdBus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAGREGAR)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMODIFICAR)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)
                                        .addGap(32, 32, 32)
                                        .addComponent(btnNUEVO))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 230, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdPapeleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAGREGAR)
                    .addComponent(btnMODIFICAR)
                    .addComponent(btnEliminar)
                    .addComponent(btnNUEVO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        BTNSALIR7.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR7.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR7.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR7.setText("Salir");
        BTNSALIR7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNSALIR7)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addComponent(BTNSALIR7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TABLADATOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLADATOSMouseClicked
        // TODO add your handling code here:
        int fila = TABLADATOS.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Papeleta NO Seleccionada");
        } else {
            String idPapeleta = (String) TABLADATOS.getValueAt(fila, 0);
            String idConductor = (String) TABLADATOS.getValueAt(fila, 1);
            String idBus = (String) TABLADATOS.getValueAt(fila, 2);
            String monto = (String) TABLADATOS.getValueAt(fila, 3);
            String tipoTransaccion = (String) TABLADATOS.getValueAt(fila, 4);
            String fechaEmision = (String) TABLADATOS.getValueAt(fila, 5);

            txtIdPapeleta.setText(idPapeleta);
            txtIdConductor.setText(idConductor);
            txtIdBus.setText(idBus);
            txtMonto.setText(monto);
            txtTipoTransaccion.setText(tipoTransaccion);
            txtFechaEmision.setText(fechaEmision);
        }

    }//GEN-LAST:event_TABLADATOSMouseClicked

    private void btnAGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGREGARActionPerformed
        // TODO add your handling code here:
        Agregar();
        nuevo();
    }//GEN-LAST:event_btnAGREGARActionPerformed

    private void btnMODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMODIFICARActionPerformed
        // TODO add your handling code here:
        modificar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnMODIFICARActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNUEVOActionPerformed
        // TODO add your handling code here:
     nuevo();
    }//GEN-LAST:event_btnNUEVOActionPerformed

    private void BTNSALIR7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR7ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIR7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Papeletapuestas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Papeletapuestas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Papeletapuestas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Papeletapuestas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Papeletapuestas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR5;
    private javax.swing.JButton BTNSALIR6;
    private javax.swing.JButton BTNSALIR7;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JButton btnAGREGAR;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMODIFICAR;
    private javax.swing.JButton btnNUEVO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtIdBus;
    private javax.swing.JTextField txtIdConductor;
    private javax.swing.JTextField txtIdPapeleta;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtTipoTransaccion;
    // End of variables declaration//GEN-END:variables
}
