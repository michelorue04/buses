
package MODELO;

import Login.Principalfrm;
import config.CConexion; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Rutasfrm extends javax.swing.JFrame {

    // Conexión a la base de datos
    CConexion conexion = new CConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    public Rutasfrm() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana
        Listar(); // Llamar a Listar() para cargar los registros desde el inicio
    }

    // Método para agregar una nueva ruta
    void Agregar() {
        String origen = txtOrigen.getText();
        String destino = txtDestino.getText();
        String salida = txtsalida.getText();
        String llegada = txtllegada.getText();

        if (origen.equals("") || destino.equals("") || salida.equals("") || llegada.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
        } else {
            String sql = "INSERT INTO Rutas (origen, destino, horario_salida, horario_llegada) "
                    + "VALUES ('" + origen + "', '" + destino + "', '" + salida + "', '" + llegada + "')";
            try {
                cn = conexion.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Ruta Insertada...");
                limpiartabla(); // Limpiar la tabla antes de recargar
                Listar(); // Listar las rutas después de agregar
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar ruta: " + e.getMessage());
            }
        }
    }

    // Método para modificar una ruta existente
    void modificar() {
        String id_ruta = txtRuta.getText();
        String origen = txtOrigen.getText();
        String destino = txtDestino.getText();
        String salida = txtsalida.getText();
        String llegada = txtllegada.getText();

        String sql = "UPDATE Rutas SET origen='" + origen + "', destino='" + destino + "', horario_salida='" + salida + "', horario_llegada='" + llegada + "' "
                + "WHERE id_ruta=" + id_ruta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ruta Actualizada...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar las rutas después de actualizar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar ruta: " + e.getMessage());
        }
    }

    // Método para eliminar una ruta
    void eliminar() {
        String id_ruta = txtRuta.getText();
        String sql = "DELETE FROM Rutas WHERE id_ruta=" + id_ruta;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ruta Eliminada...");
            limpiartabla(); // Limpiar la tabla antes de recargar
            Listar(); // Listar las rutas después de eliminar
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar ruta: " + e.getMessage());
        }
    }

    // Método para listar todas las rutas en la tabla
    void Listar() {
        String sql = "SELECT * FROM Rutas";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] ruta = new Object[5]; // Ahora tenemos 5 columnas
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            limpiartabla(); // Limpiar la tabla antes de cargar los nuevos datos
            while (rs.next()) {
                ruta[0] = rs.getString("id_ruta");
                ruta[1] = rs.getString("origen");
                ruta[2] = rs.getString("destino");
                ruta[3] = rs.getString("horario_salida");
                ruta[4] = rs.getString("horario_llegada");
                modelo.addRow(ruta);  // Agregar la fila a la tabla
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las rutas: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla de rutas
    void limpiartabla() {
        for (int i = 0; i < TABLADATOS.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtRuta.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        txtsalida.setText("");
        txtllegada.setText("");
        txtOrigen.requestFocus();
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
        txtRuta = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        txtsalida = new javax.swing.JTextField();
        txtllegada = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        BTNSALIR4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Ruta", "Origen", "Destino", "H_Salida", "H_Llegada"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLADATOS);

        jLabel1.setText("ID_Ruta");

        jLabel2.setText("Origen");

        jLabel3.setText("Destino");

        jLabel4.setText("Horario_Salida");

        jLabel5.setText("Horario_Llegada");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar)
                        .addGap(30, 30, 30)
                        .addComponent(btnModificar)
                        .addGap(29, 29, 29)
                        .addComponent(btnEliminar)
                        .addGap(33, 33, 33)
                        .addComponent(btnNuevo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtOrigen))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtllegada, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDestino))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtsalida))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtllegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(568, 568, 568)
                        .addComponent(BTNSALIR4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNSALIR4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
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
    
    // Si no se selecciona ninguna fila, mostramos un mensaje de advertencia
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Ruta NO Seleccionada");
    } else {
        // Si hay una fila seleccionada, extraemos los datos y los cargamos en los campos de texto
        String id = (String) TABLADATOS.getValueAt(fila, 0);  // Obtener el ID de la ruta
        String origen = (String) TABLADATOS.getValueAt(fila, 1);  // Obtener el origen
        String destino = (String) TABLADATOS.getValueAt(fila, 2);  // Obtener el destino
        String salida = (String) TABLADATOS.getValueAt(fila, 3);  // Obtener la hora de salida
        String llegada = (String) TABLADATOS.getValueAt(fila, 4);  // Obtener la hora de llegada

        // Cargar los datos en los campos de texto
        txtRuta.setText(id);
        txtOrigen.setText(origen);
        txtDestino.setText(destino);
        txtsalida.setText(salida);
        txtllegada.setText(llegada);
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
            java.util.logging.Logger.getLogger(Rutasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rutasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rutasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rutasfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rutasfrm().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtllegada;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables
}
