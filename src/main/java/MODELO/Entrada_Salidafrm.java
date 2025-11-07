
package MODELO;

import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Entrada_Salidafrm extends javax.swing.JFrame {

    // Conexión a la base de datos
    CConexion conexion = new CConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    public Entrada_Salidafrm() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana
        Listar(); // Llamar a Listar() para cargar los registros desde el inicio
        CargarComboBox(); // Cargar el ComboBox con las opciones "Entrada" y "Salida"
    }

    // Método para agregar un registro a la tabla RegistroEntradasSalidas
    void Agregar() {
        String bus = txtBus.getText();
        String conductor = txtCondu.getText();
        String ruta = txtRuta.getText();
        String tipoEntradaSalida = (String) cmboSAL.getSelectedItem(); // ComboBox: Entrada o Salida
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();

        if (bus.equals("") || conductor.equals("") || ruta.equals("") || tipoEntradaSalida == null || fecha.equals("") || hora.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
        } else {
            String sql = "INSERT INTO RegistroEntradasSalidas(id_bus, id_conductor, id_ruta, tipo_entrada_salida, fecha, hora) "
                    + "VALUES (" + bus + ", " + conductor + ", " + ruta + ", '" + tipoEntradaSalida + "', '" + fecha + "', '" + hora + "')";
            try {
                cn = conexion.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Registro Insertado...");
                limpiartabla(); // Limpiar la tabla antes de recargar
                Listar(); // Listar los registros después de agregar
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar registro: " + e.getMessage());
            }
        }
    }

    // Método para modificar un registro de la tabla RegistroEntradasSalidas
    void modificar() {
        String id_registro = txtRegis.getText();
        String bus = txtBus.getText();
        String conductor = txtCondu.getText();
        String ruta = txtRuta.getText();
        String tipoEntradaSalida = (String) cmboSAL.getSelectedItem(); // ComboBox: Entrada o Salida
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();

        String sql = "UPDATE RegistroEntradasSalidas SET id_bus=" + bus + ", id_conductor=" + conductor + ", id_ruta=" + ruta + ", "
                + "tipo_entrada_salida='" + tipoEntradaSalida + "', fecha='" + fecha + "', hora='" + hora + "' WHERE id_registro=" + id_registro;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Actualizado...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar los registros después de actualizar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + e.getMessage());
        }
    }

    // Método para eliminar un registro de la tabla RegistroEntradasSalidas
    void eliminar() {
        String id_registro = txtRegis.getText();
        String sql = "DELETE FROM RegistroEntradasSalidas WHERE id_registro=" + id_registro;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Eliminado...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar los registros después de eliminar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro: " + e.getMessage());
        }
    }

    // Método para listar los registros de la tabla RegistroEntradasSalidas
    void Listar() {
        String sql = "SELECT * FROM RegistroEntradasSalidas";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] registro = new Object[7]; // Ahora tenemos 7 columnas
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            limpiartabla(); // Limpiar la tabla antes de cargar los nuevos datos
            while (rs.next()) {
                registro[0] = rs.getString("id_registro");
                registro[1] = rs.getString("id_bus");
                registro[2] = rs.getString("id_conductor");
                registro[3] = rs.getString("id_ruta");
                registro[4] = rs.getString("tipo_entrada_salida");
                registro[5] = rs.getString("fecha");
                registro[6] = rs.getString("hora");
                modelo.addRow(registro);  // Agregar la fila a la tabla
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los registros: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla de registros
    void limpiartabla() {
        for (int i = 0; i < TABLADATOS.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    // Método para cargar el ComboBox con las opciones "Entrada" y "Salida"
    void CargarComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Entrada");
        model.addElement("Salida");
        cmboSAL.setModel(model);
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtRegis.setText("");
        txtBus.setText("");
        txtCondu.setText("");
        txtRuta.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtBus.requestFocus();
    }


    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLADATOS = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtRegis = new javax.swing.JTextField();
        txtBus = new javax.swing.JTextField();
        txtCondu = new javax.swing.JTextField();
        txtRuta = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cmboSAL = new javax.swing.JComboBox<>();
        BTNSALIR4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Registro", "ID_Bus", "ID_Conductor", "ID_Ruta", "Entrada/Salida", "Fecha", "Hora"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLADATOS);

        jLabel1.setText("ID_Registro");

        jLabel2.setText("ID_Bus");

        jLabel3.setText("ID_Conductor");

        jLabel4.setText("ID_Ruta");

        jLabel5.setText("Tipo Entrada/Salida");

        jLabel6.setText("Fecha");

        jLabel7.setText("Hora");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Nuevo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRegis, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(83, 83, 83)
                                                .addComponent(txtCondu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(txtRuta))
                                    .addComponent(txtBus)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmboSAL, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addGap(24, 24, 24))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton3)
                                                .addComponent(jButton1)
                                                .addComponent(jButton2)))
                                        .addGap(251, 251, 251)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 23, Short.MAX_VALUE)))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtCondu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmboSAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)
                        .addGap(20, 20, 20)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BTNSALIR4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(BTNSALIR4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Agregar();
        nuevo();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         modificar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        eliminar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        nuevo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TABLADATOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLADATOSMouseClicked
        // TODO add your handling code here:
    int fila = TABLADATOS.getSelectedRow();
    
    // Verificar si se ha seleccionado una fila
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla.");
    } else {
        // Cargar los datos de la fila seleccionada en los campos de texto
        txtRegis.setText(TABLADATOS.getValueAt(fila, 0).toString()); // id_registro
        txtBus.setText(TABLADATOS.getValueAt(fila, 1).toString()); // id_bus
        txtCondu.setText(TABLADATOS.getValueAt(fila, 2).toString()); // id_conductor
        txtRuta.setText(TABLADATOS.getValueAt(fila, 3).toString()); // id_ruta
        cmboSAL.setSelectedItem(TABLADATOS.getValueAt(fila, 4).toString()); // tipo_entrada_salida (Entrada o Salida)
        txtFecha.setText(TABLADATOS.getValueAt(fila, 5).toString()); // fecha
        txtHora.setText(TABLADATOS.getValueAt(fila, 6).toString()); // hora
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
            java.util.logging.Logger.getLogger(Entrada_Salidafrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entrada_Salidafrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entrada_Salidafrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entrada_Salidafrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entrada_Salidafrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR1;
    private javax.swing.JButton BTNSALIR2;
    private javax.swing.JButton BTNSALIR3;
    private javax.swing.JButton BTNSALIR4;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JComboBox<String> cmboSAL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBus;
    private javax.swing.JTextField txtCondu;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtRegis;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
