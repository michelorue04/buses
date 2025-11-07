
package solicitudes;

import Login.Principalfrm;
import config.CConexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Taller_Mecanico extends javax.swing.JFrame {

    CConexion conexion = new CConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;

    public Taller_Mecanico() {
        initComponents();
        setLocationRelativeTo(null);
        Listar();  // Se debe llamar aquí para listar los registros desde el inicio
    }

    // Método para agregar un registro de atención a la base de datos
void Agregar() {
    // Obtener los datos de los campos de texto
    String fecha = txtFecha.getText();
    String hora = txtHora.getText();
    String clienteNombre = txtClienteNombre.getText();
    String clienteNumero = txtClienteNumero.getText();
    String carroMarcaModelo = txtCarroMarcaModelo.getText();
    String carroPlacas = txtCarroPlacas.getText();
    String motivoAtencion = txtMotivoAtencion.getText();
    String descripcionServicio = txtDescripcionServicio.getText();
    String costoEstimado = txtCostoEstimado.getText();
    String estadoServicio = cmbEstadoServicio.getSelectedItem().toString();
    String observaciones = txtObservaciones.getText();

    // Verificar si los campos están vacíos
    if (fecha.equals("") || hora.equals("") || clienteNombre.equals("") || clienteNumero.equals("") || 
        carroMarcaModelo.equals("") || carroPlacas.equals("") || motivoAtencion.equals("") || 
        descripcionServicio.equals("") || costoEstimado.equals("") || estadoServicio.equals("")) {
        JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos");
    } else {
        try {
            // Crear la consulta SQL para insertar los datos en la base de datos
            String sql = "INSERT INTO registros_atencion (fecha, hora, cliente_nombre, cliente_numero, carro_marca_modelo, "
                        + "carro_placas, motivo_atencion, descripcion_servicio, costo_estimado, estado_servicio, observaciones) "
                        + "VALUES ('" + fecha + "', '" + hora + "', '" + clienteNombre + "', '" + clienteNumero + "', '" + 
                        carroMarcaModelo + "', '" + carroPlacas + "', '" + motivoAtencion + "', '" + descripcionServicio + "', " +
                        costoEstimado + ", '" + estadoServicio + "', '" + observaciones + "')";

            // Establecer la conexión y ejecutar la consulta
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Insertado...");
            limpiartabla();  // Limpiar la tabla
            Listar();        // Volver a listar los registros en la tabla
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar registro: " + e.getMessage());
            e.printStackTrace();  // Para ver el error en consola
        }
    }
}



    // Método para modificar un registro en la base de datos
    void modificar() {
        String idAtencion = txtId.getText();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();
        String clienteNombre = txtClienteNombre.getText();
        String clienteNumero = txtClienteNumero.getText();
        String carroMarcaModelo = txtCarroMarcaModelo.getText();
        String carroPlacas = txtCarroPlacas.getText();
        String motivoAtencion = txtMotivoAtencion.getText();
        String descripcionServicio = txtDescripcionServicio.getText();
        String costoEstimado = txtCostoEstimado.getText();
        String estadoServicio = cmbEstadoServicio.getSelectedItem().toString();
        String observaciones = txtObservaciones.getText();

        String sql = "UPDATE registros_atencion SET fecha='" + fecha + "', hora='" + hora + "', cliente_nombre='" + 
                     clienteNombre + "', cliente_numero='" + clienteNumero + "', carro_marca_modelo='" + carroMarcaModelo + 
                     "', carro_placas='" + carroPlacas + "', motivo_atencion='" + motivoAtencion + "', descripcion_servicio='" +
                     descripcionServicio + "', costo_estimado=" + costoEstimado + ", estado_servicio='" + estadoServicio + 
                     "', observaciones='" + observaciones + "' WHERE id=" + idAtencion;
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Actualizado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + e.getMessage());
        }
    }

    // Método para eliminar un registro de la base de datos
    void eliminar() {
        String idAtencion = txtId.getText();
        String sql = "DELETE FROM registros_atencion WHERE id=" + idAtencion;
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro Eliminado...");
            limpiartabla();
            Listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro: " + e.getMessage());
        }
    }

    // Método para listar los registros de atención en la tabla
    void Listar() {
        String sql = "SELECT * FROM registros_atencion";
        try {
            cn = conexion.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] registro = new Object[12];  // 12 columnas en total
            modelo = (DefaultTableModel) TABLADATOS.getModel();
            limpiartabla();
            while (rs.next()) {
                registro[0] = rs.getString("id");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("hora");
                registro[3] = rs.getString("cliente_nombre");
                registro[4] = rs.getString("cliente_numero");
                registro[5] = rs.getString("carro_marca_modelo");
                registro[6] = rs.getString("carro_placas");
                registro[7] = rs.getString("motivo_atencion");
                registro[8] = rs.getString("descripcion_servicio");
                registro[9] = rs.getString("costo_estimado");
                registro[10] = rs.getString("estado_servicio");
                registro[11] = rs.getString("observaciones");

                modelo.addRow(registro);
            }
            TABLADATOS.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los registros: " + e.getMessage());
        }
    }

    // Método para limpiar la tabla
    void limpiartabla() {
        modelo.setRowCount(0);  // Limpiar todas las filas
    }

    // Método para limpiar los campos de texto
    void nuevo() {
        txtId.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtClienteNombre.setText("");
        txtClienteNumero.setText("");
        txtCarroMarcaModelo.setText("");
        txtCarroPlacas.setText("");
        txtMotivoAtencion.setText("");
        txtDescripcionServicio.setText("");
        txtCostoEstimado.setText("");
        cmbEstadoServicio.setSelectedIndex(0);  // Reiniciar el combo a "En proceso"
        txtObservaciones.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCarroPlacas = new javax.swing.JTextField();
        txtMotivoAtencion = new javax.swing.JTextField();
        txtDescripcionServicio = new javax.swing.JTextField();
        txtClienteNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtClienteNumero = new javax.swing.JTextField();
        txtCarroMarcaModelo = new javax.swing.JTextField();
        txtCostoEstimado = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        cmbEstadoServicio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABLADATOS = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        BTNSALIR9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Taller Mecanico Auto Fix");

        jLabel6.setText("ID");

        cmbEstadoServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En proceso", "Pendiente", "Finalizado" }));

        jLabel7.setText("Fecha");

        jLabel8.setText("hora");

        jLabel9.setText("Nombre_Cliente");

        jLabel10.setText("N° cliente");

        jLabel11.setText("Modelo carro");

        jLabel12.setText("Placa del carro");

        jLabel13.setText("Motivo/Atención");

        jLabel14.setText("Descripción/");

        jLabel15.setText("Costo");

        jLabel16.setText("Servicio");

        jLabel17.setText("Observaciones");

        TABLADATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Hora", "Nombre_Cliente", "N° Cliente", "M_Carro", "PLaca", "Motivo/Atención", "Descripción", "Costo", "Servicio", "Observaciones"
            }
        ));
        TABLADATOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLADATOSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TABLADATOS);

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

        jButton4.setText("nuevo");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtClienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(13, 13, 13))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCarroMarcaModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtCostoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(cmbEstadoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(txtMotivoAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(txtCarroPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jButton2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jButton4)))))
                        .addGap(0, 69, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCarroPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMotivoAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtClienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCarroMarcaModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtCostoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cmbEstadoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(97, 97, 97)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        BTNSALIR9.setBackground(new java.awt.Color(255, 0, 0));
        BTNSALIR9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNSALIR9.setForeground(new java.awt.Color(255, 255, 255));
        BTNSALIR9.setIcon(new javax.swing.ImageIcon("F:\\CUARTO CICLO\\JAVA PROYECTO\\Imagenes\\salir_1.png")); // NOI18N
        BTNSALIR9.setText("Salir");
        BTNSALIR9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNSALIR9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNSALIR9)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
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
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Registro no seleccionado");
        } else {
            txtId.setText(TABLADATOS.getValueAt(fila, 0).toString());
            txtFecha.setText(TABLADATOS.getValueAt(fila, 1).toString());
            txtHora.setText(TABLADATOS.getValueAt(fila, 2).toString());
            txtClienteNombre.setText(TABLADATOS.getValueAt(fila, 3).toString());
            txtClienteNumero.setText(TABLADATOS.getValueAt(fila, 4).toString());
            txtCarroMarcaModelo.setText(TABLADATOS.getValueAt(fila, 5).toString());
            txtCarroPlacas.setText(TABLADATOS.getValueAt(fila, 6).toString());
            txtMotivoAtencion.setText(TABLADATOS.getValueAt(fila, 7).toString());
            txtDescripcionServicio.setText(TABLADATOS.getValueAt(fila, 8).toString());
            txtCostoEstimado.setText(TABLADATOS.getValueAt(fila, 9).toString());
            cmbEstadoServicio.setSelectedItem(TABLADATOS.getValueAt(fila, 10).toString());
            txtObservaciones.setText(TABLADATOS.getValueAt(fila, 11).toString());
        }
    }//GEN-LAST:event_TABLADATOSMouseClicked

    private void BTNSALIR9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR9ActionPerformed
        // TODO add your handling code here:
        Principalfrm abrir= new Principalfrm ();
        abrir.setVisible(true);
        
    }//GEN-LAST:event_BTNSALIR9ActionPerformed

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
            java.util.logging.Logger.getLogger(Taller_Mecanico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Taller_Mecanico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Taller_Mecanico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Taller_Mecanico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Taller_Mecanico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR9;
    private javax.swing.JTable TABLADATOS;
    private javax.swing.JComboBox<String> cmbEstadoServicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtCarroMarcaModelo;
    private javax.swing.JTextField txtCarroPlacas;
    private javax.swing.JTextField txtClienteNombre;
    private javax.swing.JTextField txtClienteNumero;
    private javax.swing.JTextField txtCostoEstimado;
    private javax.swing.JTextField txtDescripcionServicio;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMotivoAtencion;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
