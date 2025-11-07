
package MODELO;

import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class boletasfrm extends javax.swing.JFrame {

    // Conexión a la base de datos
    CConexion conexion = new CConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    public boletasfrm() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana
        Listar(); // Llamar a Listar() para cargar los registros de boletas al iniciar
    }

    // Método para agregar una nueva boleta
    void Agregar() {
        String idConductor = txtConductor.getText();
        String idBus = txtBus.getText();
        String monto = txtMonto.getText();
        String tipoTransaccion = txtTipoTransaccion.getText();
        String fechaEmision = txtFechaEmision.getText();

        if (idConductor.equals("") || idBus.equals("") || monto.equals("") || tipoTransaccion.equals("") || fechaEmision.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
        } else {
            String sql = "INSERT INTO Boletas (id_conductor, id_bus, monto, tipo_transaccion, fecha_emision) "
                    + "VALUES (" + idConductor + ", " + idBus + ", " + monto + ", '" + tipoTransaccion + "', '" + fechaEmision + "')";
            try {
                cn = conexion.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Boleta Insertada...");
                limpiartabla(); // Limpiar la tabla antes de recargar
                Listar(); // Listar las boletas después de agregar
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar boleta: " + e.getMessage());
            }
        }
    }

    // Método para modificar una boleta existente
    void modificar() {
        String idBoleta = txtBoleta.getText();
        String idConductor = txtConductor.getText();
        String idBus = txtBus.getText();
        String monto = txtMonto.getText();
        String tipoTransaccion = txtTipoTransaccion.getText();
        String fechaEmision = txtFechaEmision.getText();

        String sql = "UPDATE Boletas SET id_conductor=" + idConductor + ", id_bus=" + idBus + ", monto=" + monto 
                    + ", tipo_transaccion='" + tipoTransaccion + "', fecha_emision='" + fechaEmision + "' WHERE id_boleta=" + idBoleta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Boleta Actualizada...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar las boletas después de actualizar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar boleta: " + e.getMessage());
        }
    }

    // Método para eliminar una boleta
    void eliminar() {
        String idBoleta = txtBoleta.getText();
        String sql = "DELETE FROM Boletas WHERE id_boleta=" + idBoleta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Boleta Eliminada...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar las boletas después de eliminar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar boleta: " + e.getMessage());
        }
    }

    // Método para listar todas las boletas en la tabla
    void Listar() {
        String sql = "SELECT * FROM Boletas";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] boleta = new Object[6]; // Ahora tenemos 6 columnas
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            limpiartabla(); // Limpiar la tabla antes de cargar los nuevos datos
            while (rs.next()) {
                boleta[0] = rs.getString("id_boleta");
                boleta[1] = rs.getString("id_conductor");
                boleta[2] = rs.getString("id_bus");
                boleta[3] = rs.getString("monto");
                boleta[4] = rs.getString("tipo_transaccion");
                boleta[5] = rs.getString("fecha_emision");
                modelo.addRow(boleta);  // Agregar la fila a la tabla
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las boletas: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla de boletas
    void limpiartabla() {
        for (int i = 0; i < TABLADATOS.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtBoleta.setText("");
        txtConductor.setText("");
        txtBus.setText("");
        txtMonto.setText("");
        txtTipoTransaccion.setText("");
        txtFechaEmision.setText("");
        txtConductor.requestFocus();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTNSALIR4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBoleta = new javax.swing.JTextField();
        txtConductor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBus = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTipoTransaccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLADATOS = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTNSALIR4.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR4.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR4.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR4.setText("Salir");
        BTNSALIR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR4ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID_Boleta");

        jLabel2.setText("ID_Cpnductor");

        jLabel3.setText("ID_Bus");

        jLabel4.setText("Monto");

        jLabel5.setText("Trasacción");

        jLabel6.setText("Fecha Emisión");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
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
                .addComponent(btnAgregar)
                .addGap(28, 28, 28)
                .addComponent(btnModificar)
                .addGap(34, 34, 34)
                .addComponent(btnEliminar)
                .addGap(29, 29, 29)
                .addComponent(btnNuevo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(34, 34, 34)
                                        .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel4)
                                .addGap(33, 33, 33)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNSALIR4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNSALIR4)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        Agregar();
        nuevo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modificar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void TABLADATOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLADATOSMouseClicked
        // TODO add your handling code here:
    int fila = TABLADATOS.getSelectedRow();
    
    // Verificar que se haya seleccionado una fila
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar una boleta de la tabla.");
    } else {
        // Cargar los datos de la fila seleccionada en los campos de texto
        txtBoleta.setText(TABLADATOS.getValueAt(fila, 0).toString());
        txtConductor.setText(TABLADATOS.getValueAt(fila, 1).toString());
        txtBus.setText(TABLADATOS.getValueAt(fila, 2).toString());
        txtMonto.setText(TABLADATOS.getValueAt(fila, 3).toString());
        txtTipoTransaccion.setText(TABLADATOS.getValueAt(fila, 4).toString());
        txtFechaEmision.setText(TABLADATOS.getValueAt(fila, 5).toString());
    }    
    }//GEN-LAST:event_TABLADATOSMouseClicked

    private void BTNSALIR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR4ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIR4ActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(boletasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(boletasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(boletasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(boletasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new boletasfrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR4;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBoleta;
    private javax.swing.JTextField txtBus;
    private javax.swing.JTextField txtConductor;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtTipoTransaccion;
    // End of variables declaration//GEN-END:variables
}
