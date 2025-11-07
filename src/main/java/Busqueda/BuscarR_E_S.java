
package Busqueda;

import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BuscarR_E_S extends javax.swing.JFrame {

    // Declaración de variables
    CConexion conexion = new CConexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    // Constructor
    public BuscarR_E_S() {
        initComponents();  // Inicializa los componentes gráficos
        setLocationRelativeTo(null);  // Centra la ventana
    }

    // Método para realizar la búsqueda
    void buscar() {
        String id = txtResultados.getText().trim();  // Obtenemos el ID ingresado por el usuario
        String sql = "SELECT * FROM RegistroEntradasSalidas WHERE id_registro = ?";  // Consulta SQL para buscar el registro por ID

        // Verificar si el ID está vacío
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID para buscar.");
            return;  // Si el ID está vacío, salimos del método
        }

        try {
            // Establecer la conexión a la base de datos
            cn = conexion.getConexion();
            ps = cn.prepareStatement(sql);  // Preparamos la consulta con parámetros
            ps.setString(1, id);  // Asignamos el valor del ID en el primer parámetro

            // Ejecutamos la consulta
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si encontramos un resultado, mostramos los datos en el campo txtmuestra
                String idBus = rs.getString("id_bus");
                String idConductor = rs.getString("id_conductor");
                String idRuta = rs.getString("id_ruta");
                String tipoEntradaSalida = rs.getString("tipo_entrada_salida");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");

                // Mostrar los resultados en el campo txtmuestra
                txtmuestra.setText("ID Bus: " + idBus + "\n" +
                                   "ID Conductor: " + idConductor + "\n" +
                                   "ID Ruta: " + idRuta + "\n" +
                                   "Tipo Entrada/Salida: " + tipoEntradaSalida + "\n" +
                                   "Fecha: " + fecha + "\n" +
                                   "Hora: " + hora);
            } else {
                // Si no encontramos resultados, mostramos un mensaje
                JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
                txtmuestra.setText("");  // Limpiamos el campo txtmuestra
            }

        } catch (SQLException e) {
            // Si ocurre un error en la consulta, mostramos un mensaje
            JOptionPane.showMessageDialog(null, "Error al realizar la búsqueda: " + e.getMessage());
        }
    }


    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtResultados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmuestra = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        BTNSALIR11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtmuestra.setColumns(20);
        txtmuestra.setRows(5);
        jScrollPane1.setViewportView(txtmuestra);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar por ID en la Tabla R Entradas y salidas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(55, 55, 55)
                            .addComponent(jButton1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel1))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        BTNSALIR11.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR11.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR11.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR11.setText("Salir");
        BTNSALIR11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNSALIR11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(BTNSALIR11)
                .addGap(0, 387, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BTNSALIR11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR11ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
    }//GEN-LAST:event_BTNSALIR11ActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarR_E_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarR_E_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarR_E_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarR_E_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarR_E_S().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR10;
    private javax.swing.JButton BTNSALIR11;
    private javax.swing.JButton BTNSALIR9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtResultados;
    private javax.swing.JTextArea txtmuestra;
    // End of variables declaration//GEN-END:variables
}
