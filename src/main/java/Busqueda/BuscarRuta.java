
package Busqueda;


import Login.Principalfrm;
import config.CConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BuscarRuta extends javax.swing.JFrame {

    // Declaración de variables
    CConexion conexion = new CConexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    // Constructor
    public BuscarRuta() {
        initComponents();  // Inicializa los componentes gráficos
        setLocationRelativeTo(null);  // Centra la ventana
    }

    // Método para realizar la búsqueda
    void buscar() {
        String id = txtResultados.getText().trim();  // Obtenemos el ID ingresado por el usuario
        String sql = "SELECT * FROM Rutas WHERE id_ruta = ?";  // Consulta SQL para buscar la ruta por ID

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
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                String horarioSalida = rs.getString("horario_salida");
                String horarioLlegada = rs.getString("horario_llegada");

                // Mostrar los resultados en el campo txtmuestra
                txtmuestra.setText("Origen: " + origen + "\n" +
                                   "Destino: " + destino + "\n" +
                                   "Horario de Salida: " + horarioSalida + "\n" +
                                   "Horario de Llegada: " + horarioLlegada);
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
        btnBUSCAR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmuestra = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        BTNSALIR11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        btnBUSCAR.setText("Buscar");
        btnBUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSCARActionPerformed(evt);
            }
        });

        txtmuestra.setColumns(20);
        txtmuestra.setRows(5);
        jScrollPane1.setViewportView(txtmuestra);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar por ID en la Tabla Rutas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBUSCAR))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBUSCAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
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
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNSALIR11)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(BTNSALIR11)
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSCARActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnBUSCARActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarRuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarRuta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR11;
    private javax.swing.JButton btnBUSCAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtResultados;
    private javax.swing.JTextArea txtmuestra;
    // End of variables declaration//GEN-END:variables
}
