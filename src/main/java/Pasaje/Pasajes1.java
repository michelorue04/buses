
package Pasaje;


import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pasajes1 extends javax.swing.JFrame {

    CConexion conexion = new CConexion();

    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;

    public Pasajes1() {
        initComponents();
        setLocationRelativeTo(null);
        Listar(); // Se debe llamar aquí para listar los pasajes desde el inicio
    }

    // Método para agregar un pasaje a la base de datos
    void Agregar() {
        String origen = txtorigen.getText();
        String destino = txtdestino.getText();
        String fecha_salida = txtfechasalida.getText(); // Asegúrate de que el formato sea correcto (yyyy-MM-dd HH:mm:ss)
        String precio = txtprecio.getText();
        String numero_asiento = txtnumeroasiento.getText();
        String cliente_nombre = txtcliente.getText();
        String cliente_email = txtclienteemail.getText();

        if (origen.equals("") || destino.equals("") || fecha_salida.equals("") || precio.equals("") || numero_asiento.equals("") || cliente_nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
        } else {
            String sql = "INSERT INTO pasajes (origen, destino, fecha_salida, precio, numero_asiento, cliente_nombre, cliente_email) " +
                         "VALUES ('" + origen + "', '" + destino + "', '" + fecha_salida + "', " + precio + ", " + numero_asiento + ", '" + cliente_nombre + "', '" + cliente_email + "')";
            try {
                cn = conexion.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Pasaje Insertado...");
                limpiartabla();
                Listar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al agregar pasaje: " + e.getMessage());
            }
        }
    }

    // Método para modificar un pasaje en la base de datos
    void modificar() {
        String id_pasaje = txtid.getText();
        String origen = txtorigen.getText();
        String destino = txtdestino.getText();
        String fecha_salida = txtfechasalida.getText();
        String precio = txtprecio.getText();
        String numero_asiento = txtnumeroasiento.getText();
        String cliente_nombre = txtcliente.getText();
        String cliente_email = txtclienteemail.getText();

        String sql = "UPDATE pasajes SET origen='" + origen + "', destino='" + destino + "', fecha_salida='" + fecha_salida + "', precio=" + precio + 
                     ", numero_asiento=" + numero_asiento + ", cliente_nombre='" + cliente_nombre + "', cliente_email='" + cliente_email + 
                     "' WHERE id=" + id_pasaje;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Pasaje Actualizado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pasaje: " + e.getMessage());
        }
    }

    // Método para eliminar un pasaje de la base de datos
    void eliminar() {
        String id_pasaje = txtid.getText();
        String sql = "DELETE FROM pasajes WHERE id=" + id_pasaje;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Pasaje Eliminado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pasaje: " + e.getMessage());
        }
    }

    // Método para listar los pasajes en la tabla
    void Listar() {
        String sql = "SELECT * FROM pasajes";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] pasaje = new Object[8]; // Para 8 columnas en la tabla
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            limpiartabla();
            while (rs.next()) {
                pasaje[0] = rs.getString("id");
                pasaje[1] = rs.getString("origen");
                pasaje[2] = rs.getString("destino");
                pasaje[3] = rs.getString("fecha_salida");
                pasaje[4] = rs.getString("precio");
                pasaje[5] = rs.getString("numero_asiento");
                pasaje[6] = rs.getString("cliente_nombre");
                pasaje[7] = rs.getString("cliente_email");
                modelo.addRow(pasaje);
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los pasajes: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla
    void limpiartabla() {
        modelo.setRowCount(0); // Limpiar todas las filas
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtid.setText("");
        txtorigen.setText("");
        txtdestino.setText("");
        txtfechasalida.setText("");
        txtprecio.setText("");
        txtnumeroasiento.setText("");
        txtcliente.setText("");
        txtclienteemail.setText("");
        txtorigen.requestFocus();
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtorigen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdestino = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfechasalida = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnumeroasiento = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtclienteemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnAGREGAR = new javax.swing.JButton();
        btnMODIFICAR = new javax.swing.JButton();
        btnELIMINAR = new javax.swing.JButton();
        btnNUEVO = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLADATOS = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        BTNSALIR5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setText("ID");

        jLabel2.setText("Origen");

        jLabel3.setText("Destino");

        jLabel4.setText("Fecha salida");

        jLabel5.setText("Precio");

        jLabel6.setText("N° Asiento");

        jLabel7.setText("Cliente");

        jLabel8.setText("Email");

        btnAGREGAR.setText("Agregar");
        btnAGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGARActionPerformed(evt);
            }
        });

        btnMODIFICAR.setText("Modificar");
        btnMODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMODIFICARActionPerformed(evt);
            }
        });

        btnELIMINAR.setText("Eliminar");
        btnELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELIMINARActionPerformed(evt);
            }
        });

        btnNUEVO.setText("Nuevo");
        btnNUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNUEVOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnumeroasiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtclienteemail, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnAGREGAR)
                                .addGap(48, 48, 48)
                                .addComponent(btnMODIFICAR)
                                .addGap(62, 62, 62)
                                .addComponent(btnELIMINAR)
                                .addGap(85, 85, 85)
                                .addComponent(btnNUEVO)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnumeroasiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtfechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtclienteemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAGREGAR)
                    .addComponent(btnMODIFICAR)
                    .addComponent(btnELIMINAR)
                    .addComponent(btnNUEVO))
                .addGap(50, 50, 50))
        );

        TABLADATOS.setBackground(new java.awt.Color(51, 204, 255));
        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Origen", "Destino", "Fecha salida", "Precio", "N° Asiento", "Cliente", "Email"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLADATOS);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel9.setText("    REGISTRO DE PASAJES ");

        BTNSALIR5.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR5.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR5.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR5.setText("Salir");
        BTNSALIR5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNSALIR5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(BTNSALIR5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TABLADATOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLADATOSMouseClicked
        // TODO add your handling code here:
           int fila = TABLADATOS.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Pasaje NO Seleccionado");
        } else {
            String id = (String) TABLADATOS.getValueAt(fila, 0);
            String origen = (String) TABLADATOS.getValueAt(fila, 1);
            String destino = (String) TABLADATOS.getValueAt(fila, 2);
            String fecha_salida = (String) TABLADATOS.getValueAt(fila, 3);
            String precio = (String) TABLADATOS.getValueAt(fila, 4);
            String numero_asiento = (String) TABLADATOS.getValueAt(fila, 5);
            String cliente_nombre = (String) TABLADATOS.getValueAt(fila, 6);
            String cliente_email = (String) TABLADATOS.getValueAt(fila, 7);

            txtid.setText(id);
            txtorigen.setText(origen);
            txtdestino.setText(destino);
            txtfechasalida.setText(fecha_salida);
            txtprecio.setText(precio);
            txtnumeroasiento.setText(numero_asiento);
            txtcliente.setText(cliente_nombre);
            txtclienteemail.setText(cliente_email);
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

    private void btnELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELIMINARActionPerformed
        // TODO add your handling code here:
        eliminar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnELIMINARActionPerformed

    private void btnNUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNUEVOActionPerformed
        // TODO add your handling code here:
      nuevo();
    }//GEN-LAST:event_btnNUEVOActionPerformed

    private void BTNSALIR5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR5ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIR5ActionPerformed

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
            java.util.logging.Logger.getLogger(Pasajes1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pasajes1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pasajes1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pasajes1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pasajes1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR5;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JButton btnAGREGAR;
    private javax.swing.JButton btnELIMINAR;
    private javax.swing.JButton btnMODIFICAR;
    private javax.swing.JButton btnNUEVO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtclienteemail;
    private javax.swing.JTextField txtdestino;
    private javax.swing.JTextField txtfechasalida;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnumeroasiento;
    private javax.swing.JTextField txtorigen;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
