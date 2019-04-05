/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class bv extends javax.swing.JInternalFrame {
DefaultTableModel modelo;
    /**
     * Creates new form bv
     */
    public bv() {
        initComponents();
        setTitle("Búsqueda y selección de vehículo");
        this.setLocation(280,250);
        mostrardc();
    }
    void mostrardc() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("RUT");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Contacto");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");

        String[] Registros = new String[6];
        String dv = "";
        String sql = "";
        sql = "SELECT * FROM clientes";
        //sql = "SELECT * FROM vehiculo WHERE CONCAT(Patente,Marca,Modelo,Año) LIKE '%" + txtrut.getText() + "%'";
        /*String Sql="SELECT Patente,Marca,Modelo,Año,Color,Dueño FROM vehiculo"
         +" WHERE CONCAT(Patente,Marca,Modelo,Año) LIKE '%"+valor+"%'";*/
        /*SELECT Patente,Marca,Modelo,Año,Color,Dueño,Nombres,Apellidos,Contacto,Direccion,Correo 
         FROM vehiculo inner join clientes on vehiculo.Dueño = clientes.RUT WHERE vehiculo.Patente LIKE '%s%'
         CONCAT(Patente,Marca,Modelo,Año)*/
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Registros[0] = rs.getString("RUT");
                Registros[1] = rs.getString("Nombres");
                Registros[2] = rs.getString("Apellidos");
                Registros[3] = rs.getString("Contacto");
                Registros[4] = rs.getString("Direccion");
                Registros[5] = rs.getString("Correo");
                modelo.addRow(Registros);
                if (dv.isEmpty()) {
                    dv = rs.getString(1);
                }
            }
            tbclientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(bv.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
        mostrarDV(dv);
    }

    private void mostrarDV(String idcte) {
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2.addColumn("Patente");
        modelo2.addColumn("Marca");
        modelo2.addColumn("Modelo");
        modelo2.addColumn("Año");
        modelo2.addColumn("Color");
        modelo2.addColumn("Dueño");
        int filaseleccionada = tbclientes.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            String rut = tbclientes.getValueAt(filaseleccionada, 0).toString();
            String nom = tbclientes.getValueAt(filaseleccionada, 1).toString();
            String ap = tbclientes.getValueAt(filaseleccionada, 2).toString();
            String fono = tbclientes.getValueAt(filaseleccionada, 3).toString();
            String dire = tbclientes.getValueAt(filaseleccionada, 4).toString();
            String correo = tbclientes.getValueAt(filaseleccionada, 5).toString();
            /*txtcomp.setText(numcomp);
            txtrutcte.setText(rut);
            txttotal.setText(total);
            txtfecha.setText(fecha);
            txthora.setText(hora);
            txtsucursal.setText(sucursal);
            txtfecha.setText(fecha);*/
            tbvehiculo.setModel(modelo2);
            String sql1 = "";
            sql1 = "SELECT * "
                    + "FROM vehiculo INNER JOIN clientes ON vehiculo.Dueño = clientes.RUT WHERE Dueño='" + idcte + "'";
            String[] datos2 = new String[6];
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql1);
                while (rs.next()) {
                    datos2[0] = rs.getString(1);
                    datos2[1] = rs.getString(2);
                    datos2[2] = rs.getString(3);
                    datos2[3] = rs.getString(4);
                    datos2[4] = rs.getString(5);
                    datos2[5] = rs.getString(6);
                    modelo2.addRow(datos2);
                }
                tbvehiculo.setModel(modelo2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        jButton1 = new javax.swing.JButton();
        txtrut = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jspp = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbvehiculo = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección del Dueño", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtrut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrutKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RUT");

        //Deshabilitar edicion de tabla
        tbclientes = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbclientes.setSelectionBackground(Color.LIGHT_GRAY);
        tbclientes.setSelectionForeground(Color.blue);
        tbclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbclientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbclientes.getTableHeader().setResizingAllowed(false);
        tbclientes.getTableHeader().setReorderingAllowed(false);
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbclientesMousePressed(evt);
            }
        });
        jspp.setViewportView(tbclientes);

        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setText("jButton2");

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jspp, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jspp, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.getAccessibleContext().setAccessibleParent(panelTranslucido1);
        txtrut.getAccessibleContext().setAccessibleParent(panelTranslucido1);
        jLabel1.getAccessibleContext().setAccessibleParent(panelTranslucido1);
        jspp.getAccessibleContext().setAccessibleParent(panelTranslucido1);
        jButton2.getAccessibleContext().setAccessibleParent(panelTranslucido1);

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección de Vehículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N
        panelTranslucido2.setForeground(new java.awt.Color(255, 255, 255));

        tbvehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbvehiculo = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbvehiculo.setSelectionBackground(Color.LIGHT_GRAY);
        tbvehiculo.setSelectionForeground(Color.blue);
        tbvehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbvehiculo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbvehiculo.getTableHeader().setResizingAllowed(false);
        tbvehiculo.getTableHeader().setReorderingAllowed(false);
        tbvehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbvehiculoMousePressed(evt);
            }
        });
        jsp.setViewportView(tbvehiculo);

        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setText("jButton4");

        jButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jsp)
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jsp.getAccessibleContext().setAccessibleParent(panelTranslucido2);
        jButton4.getAccessibleContext().setAccessibleParent(panelTranslucido2);
        jButton3.getAccessibleContext().setAccessibleParent(panelTranslucido2);
        jTextField1.getAccessibleContext().setAccessibleParent(panelTranslucido2);
        jLabel2.getAccessibleContext().setAccessibleParent(panelTranslucido2);

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTranslucido2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        panelTranslucido1.getAccessibleContext().setAccessibleParent(panelCurves1);
        panelTranslucido2.getAccessibleContext().setAccessibleParent(panelCurves1);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelCurves1.getAccessibleContext().setAccessibleParent(panelImage1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutKeyReleased
        // mostrarvehiculos(txtpatente.getText());
    }//GEN-LAST:event_txtrutKeyReleased

    private void tbvehiculoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbvehiculoMousePressed
        String patente = "", marca = "", modelo = "", año = "", color = "", dueño = "", nombres = "", apellidos = "", fono = "", correo = "";
        int fila = tbvehiculo.getSelectedRow();
        try {
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado fila");

            } else {
                patente = (String) tbvehiculo.getValueAt(fila, 0);
                marca = (String) tbvehiculo.getValueAt(fila, 1);
                modelo = (String) tbvehiculo.getValueAt(fila, 2);
                año = (String) tbvehiculo.getValueAt(fila, 3);
                color = (String) tbvehiculo.getValueAt(fila, 4);
                dueño = (String) tbvehiculo.getValueAt(fila, 5);

                diagnostico.txtpatentediag.setDisabledTextColor(Color.blue);
                diagnostico.txtpatentediag.setText(patente);
                diagnostico.txtrutcliente.setDisabledTextColor(Color.blue);
                diagnostico.txtrutcliente.setText(dueño);
                diagnostico.txtmarca.setDisabledTextColor(Color.blue);
                diagnostico.txtmarca.setText(marca);
                diagnostico.txtmodelo.setDisabledTextColor(Color.blue);
                diagnostico.txtmodelo.setText(modelo);
                diagnostico.txtaño.setDisabledTextColor(Color.blue);
                diagnostico.txtaño.setText(año);
                diagnostico.txtcolor.setDisabledTextColor(Color.blue);
                diagnostico.txtcolor.setText(color);

                this.dispose();
            }

        } catch (Exception e) {
        }
        
        int fila1 = tbclientes.getSelectedRow();
        try {
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado fila");

            } else {

                nombres = (String) tbclientes.getValueAt(fila1, 1);
                apellidos = (String) tbclientes.getValueAt(fila1, 2);
                fono = (String) tbclientes.getValueAt(fila1, 3);
                correo = (String) tbclientes.getValueAt(fila1, 5);

                diagnostico.txtnombre.setDisabledTextColor(Color.blue);
                diagnostico.txtnombre.setText(nombres + " " + apellidos);
                diagnostico.txtfono.setDisabledTextColor(Color.blue);
                diagnostico.txtfono.setText(fono);
                diagnostico.txtmail.setDisabledTextColor(Color.blue);
                diagnostico.txtmail.setText(correo);
                //campos de datos del cliente agregar (nombre, apellido, fono, correo).
                
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbvehiculoMousePressed

    private void tbclientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMousePressed
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        String valorId = "";
        valorId = (String) target.getValueAt(row, 0); //obtener el valor de la columna ID
        mostrarDV(valorId);
    }//GEN-LAST:event_tbclientesMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        IngresoClientesSM icsm = new IngresoClientesSM(null, true);
        icsm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        IngresoVehiculoM ivm = new IngresoVehiculoM(null, true);
        ivm.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JScrollPane jspp;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    public static javax.swing.JTable tbclientes;
    private javax.swing.JTable tbvehiculo;
    private javax.swing.JTextField txtrut;
    // End of variables declaration//GEN-END:variables
}
