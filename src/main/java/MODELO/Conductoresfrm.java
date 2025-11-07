
package MODELO;


import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Conductoresfrm extends javax.swing.JFrame {

    CConexion conexion = new CConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;

    public Conductoresfrm() {
        initComponents();
        setLocationRelativeTo(null);
        Listar(); // Se debe llamar aquí para listar los conductores desde el inicio
    }

    // Método para agregar un conductor a la base de datos
    void Agregar() {
    String nombre = txtNombre.getText();
    String documento = txtDocu.getText();
    String telefono = txtTelefono.getText();
    String edad = txtedad.getText();
    String sexo = (String) comboSexo.getSelectedItem(); // Asumiendo que hay un JComboBox llamado comboSexo
    String email = txtEmail.getText(); // Obtener el email desde el campo txtEmail

    if (nombre.equals("") || documento.equals("") || edad.equals("") || sexo == null || email.equals("")) {
        JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
    } else {
        String sql = "INSERT INTO Conductores(nombre, documento_identidad, telefono, edad, sexo, email) VALUES ('" 
            + nombre + "', '" + documento + "', '" + telefono + "', " + edad + ", '" + sexo + "', '" + email + "')";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Conductor Insertado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar conductor: " + e.getMessage());
        }
    }
}


    // Método para modificar un conductor en la base de datos
    void modificar() {
    String id_conductor = txtId.getText();
    String nombre = txtNombre.getText();
    String documento = txtDocu.getText();
    String telefono = txtTelefono.getText();
    String edad = txtedad.getText();
    String sexo = (String) comboSexo.getSelectedItem();
    String email = txtEmail.getText(); // Obtener el email desde el campo txtEmail

    String sql = "UPDATE Conductores SET nombre='" + nombre + "', documento_identidad='" + documento + "', "
        + "telefono='" + telefono + "', edad=" + edad + ", sexo='" + sexo + "', email='" + email + "' WHERE id_conductor=" + id_conductor;

    try {
        cn = conexion.getConexion();
        st = cn.createStatement();
        st.executeUpdate(sql);
        JOptionPane.showMessageDialog(null, "Conductor Actualizado...");
        limpiartabla();
        Listar();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar conductor: " + e.getMessage());
    }
}


    // Método para eliminar un conductor de la base de datos
    void eliminar() {
        String id_conductor = txtId.getText();
        String sql = "DELETE FROM Conductores WHERE id_conductor=" + id_conductor;

        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Conductor Eliminado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar conductor: " + e.getMessage());
        }
    }

    // Método para listar los conductores en la tabla
   void Listar() {
    String sql = "SELECT * FROM Conductores";
    try {
        cn = conexion.getConexion();
        st = cn.createStatement();
        rs = st.executeQuery(sql);
        Object[] conductor = new Object[8]; // Ahora tenemos 8 columnas (incluyendo el email)
        modelo = (DefaultTableModel) TABLADATOS.getModel();
        // Limpia la tabla antes de agregar los nuevos registros
        limpiartabla();
        while (rs.next()) {
            conductor[0] = rs.getString("id_conductor");
            conductor[1] = rs.getString("nombre");
            conductor[2] = rs.getString("documento_identidad");
            conductor[3] = rs.getString("telefono");
            conductor[4] = rs.getString("edad");
            conductor[5] = rs.getString("sexo");
            conductor[6] = rs.getString("email"); // Aquí agregamos el email a la tabla
            modelo.addRow(conductor);
        }
        TABLADATOS.setModel(modelo);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar los conductores: " + e.getMessage());
    }
}

    // Método para limpiar la tabla
    void limpiartabla() {
        modelo.setRowCount(0); // Limpiar todas las filas
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtId.setText("");
        txtNombre.setText("");
        txtDocu.setText("");
        txtTelefono.setText("");
        txtedad.setText("");
        comboSexo.setSelectedIndex(0); // Resetea el combo de sexo
        txtNombre.requestFocus();
    }

    // Métodos de eventos de la interfaz gráfica


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTNSALIR = new javax.swing.JButton();
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
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDocu = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtedad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        comboSexo = new javax.swing.JComboBox<>();
        BTNSALIR1 = new javax.swing.JButton();

        BTNSALIR.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR.setText("Salir");
        BTNSALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIRActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID-Conduc", "Nombre", "Dcumento", "Telefono", "Edad", "Sexo", "Email"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLADATOS);

        jLabel1.setText("ID_Conduc");

        jLabel2.setText("Nombre");

        jLabel3.setText("DNI");

        jLabel4.setText("Telefono");

        jLabel5.setText("Email");

        jLabel6.setText("Edad");

        jLabel7.setText("Sexo");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDocu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEditar))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnEliminar))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNuevo))))
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))))
                        .addContainerGap(261, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        BTNSALIR1.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR1.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR1.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR1.setText("Salir");
        BTNSALIR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNSALIR1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNSALIR1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TABLADATOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLADATOSMouseClicked
       // Obtener la fila seleccionada
    int fila = TABLADATOS.getSelectedRow();
    
    // Verificar si se ha seleccionado una fila
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla.");
    } else {
        // Cargar los datos de la fila seleccionada en los campos de texto
        txtId.setText(TABLADATOS.getValueAt(fila, 0).toString()); // id_conductor
        txtNombre.setText(TABLADATOS.getValueAt(fila, 1).toString()); // nombre
        txtDocu.setText(TABLADATOS.getValueAt(fila, 2).toString()); // documento_identidad
        txtTelefono.setText(TABLADATOS.getValueAt(fila, 3).toString()); // telefono
        txtedad.setText(TABLADATOS.getValueAt(fila, 4).toString()); // edad
        comboSexo.setSelectedItem(TABLADATOS.getValueAt(fila, 5).toString()); // sexo
        txtEmail.setText(TABLADATOS.getValueAt(fila, 6).toString()); // email
    }
    }//GEN-LAST:event_TABLADATOSMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        Agregar();
        nuevo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
         modificar();
        limpiartabla();
        Listar();
        nuevo();
    }//GEN-LAST:event_btnEditarActionPerformed

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

    private void BTNSALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIRActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIRActionPerformed

    private void BTNSALIR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR1ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIR1ActionPerformed

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
            java.util.logging.Logger.getLogger(Conductoresfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Conductoresfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Conductoresfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conductoresfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conductoresfrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR;
    private javax.swing.JButton BTNSALIR1;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDocu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtedad;
    // End of variables declaration//GEN-END:variables
}
