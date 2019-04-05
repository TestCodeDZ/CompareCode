/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZuluCorp
 */
public class reparacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form reparacion
     */
    public reparacion() {
        initComponents();
        this.setTitle("Ingreso de reparación");
        this.setLocation(280, 15);
        mostrardatos();
        anchocolumnas();
        txtnum.setDisabledTextColor(Color.red);
        txtnumdiag.setDisabledTextColor(Color.red);
        txtcd.setDisabledTextColor(Color.red);
        txter.setText("En Reparación");
        txter.setEnabled(false);
        txter.setVisible(false);
        txtrep.setText("Reparado");
        txtrep.setEnabled(false);
        txtrep.setVisible(false);
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    void mostrardatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Número Diagnóstico");
        modelo.addColumn("Patente");
        modelo.addColumn("RUT Cliente");
        modelo.addColumn("Mecánico");
        modelo.addColumn("Ingreso D.");
        modelo.addColumn("Entrega D.");
        modelo.addColumn("Repuestos");
        tbdiag.setModel(modelo);
        String sql = "";
        sql = "SELECT * FROM controldiag WHERE Mecanico='" + Login.Nombres + " " + Login.Apellidos + "'";
        String[] datos = new String[7];
        String primerId = "";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                modelo.addRow(datos);
                if (primerId.isEmpty()) {
                    primerId = rs.getString(1);
                }
            }
            tbdiag.setModel(modelo);
            //tbdesp.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
        mostrarDetalle(primerId);
        anchocolumnas();
    }

    private void mostrarDetalle(String iddetalle) {
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2.addColumn("Núm. Diagnóstico");
        modelo2.addColumn("Código del Desperfecto");
        modelo2.addColumn("Descripción Desperfecto");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Precio Unitario");
        modelo2.addColumn("Precio Total");
        modelo2.addColumn("Estado");
        String sql1 = "";
        sql1 = "SELECT *"
                + " FROM detallediag INNER JOIN controldiag ON detallediag.ID_Diag = controldiag.ID_Diagnostico"
                + " WHERE Estado <> 'Reparado' AND ID_Diag =" + iddetalle;
        String[] datos2 = new String[7];
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
                datos2[6] = rs.getString(7);
                modelo2.addRow(datos2);
            }
            tbrep.setModel(modelo2);

        } catch (SQLException ex) {
            System.out.printf(ex.getMessage());
            //Logger.getLogger(reparacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }

    }

    private void mostrarcoddiag() {
        int filaseleccionada = tbrep.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            String nd = tbrep.getValueAt(filaseleccionada, 0).toString();
            String cd = tbrep.getValueAt(filaseleccionada, 1).toString();
            //String er = tbrep.getValueAt(filaseleccionada, 6).toString();
            txtnumdiag.setText(nd);
            txtcd.setText(cd);
        }

    }

    private void mostrarnumdiag() {
        int filaseleccionada = tbdiag.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            String nd = tbdiag.getValueAt(filaseleccionada, 0).toString();
            txtnum.setText(nd);
        }
    }

    void anchocolumnas() {
        tbdiag.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbdiag.getColumnModel().getColumn(0).setWidth(100);
        tbdiag.getColumnModel().getColumn(0).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(0).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(1).setWidth(100);
        tbdiag.getColumnModel().getColumn(1).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(1).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(2).setWidth(100);
        tbdiag.getColumnModel().getColumn(2).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(2).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(3).setWidth(135);
        tbdiag.getColumnModel().getColumn(3).setMaxWidth(135);
        tbdiag.getColumnModel().getColumn(3).setMinWidth(135);

        tbdiag.getColumnModel().getColumn(4).setWidth(100);
        tbdiag.getColumnModel().getColumn(4).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(4).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(5).setWidth(100);
        tbdiag.getColumnModel().getColumn(5).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(5).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(6).setWidth(250);
        tbdiag.getColumnModel().getColumn(6).setMaxWidth(250);
        tbdiag.getColumnModel().getColumn(6).setMinWidth(250);

    }

    private String validarnumdiag() {

        String errores = "";

        if (txtnum.getText().equals("")) {
            errores += "Por favor seleccione el diagnóstico de la tabla \n";
        }
        return errores;
    }

    private String validartxtrep() {

        String errores = "";

        if (txtnumdiag.getText().equals("")) {
            errores += "Seleccione la condición Número de diagnóstico\n";
        }
        if (txtcd.getText().equals("")) {
            errores += "seleccione la condición Código de Desperfecto \n";
        }
        return errores;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp1 = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtnum = new javax.swing.JTextField();
        txter = new javax.swing.JTextField();
        txtrep = new javax.swing.JTextField();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        txtnumdiag = new javax.swing.JTextField();
        txtcd = new javax.swing.JTextField();
        jsp2 = new javax.swing.JScrollPane();
        tbrep = new javax.swing.JTable();
        btnrep = new javax.swing.JButton();
        rbtner = new javax.swing.JRadioButton();
        rbtnrep = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione el Diagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        tbdiag.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbdiag = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbdiag.setSelectionBackground(Color.LIGHT_GRAY);
        tbdiag.setSelectionForeground(Color.blue);
        tbdiag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbdiag.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdiag.getTableHeader().setResizingAllowed(false);
        tbdiag.getTableHeader().setReorderingAllowed(false);
        tbdiag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdiagMouseClicked(evt);
            }
        });
        jsp1.setViewportView(tbdiag);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconopdf.gif"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtnum.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnum.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(46, 46, 46)
                .addComponent(txter, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtrep, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnum)
                    .addComponent(jButton1)
                    .addComponent(txter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Reparación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        txtnumdiag.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumdiag.setEnabled(false);

        txtcd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcd.setEnabled(false);

        tbrep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbrep = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbrep.setSelectionBackground(Color.LIGHT_GRAY);
        tbrep.setSelectionForeground(Color.blue);
        tbrep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbrep.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbrep.getTableHeader().setResizingAllowed(false);
        tbrep.getTableHeader().setReorderingAllowed(false);
        tbrep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrepMouseClicked(evt);
            }
        });
        jsp2.setViewportView(tbrep);

        btnrep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnrep.setText("Cambiar Estado");
        btnrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtner);
        rbtner.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtner.setSelected(true);
        rbtner.setText("En Reparación");
        rbtner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnerActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnrep);
        rbtnrep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnrep.setText("Reparado");
        rbtnrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnrepActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton1.setText("En Taller");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rbtner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnrep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioButton1))
                .addGap(13, 13, 13)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnrep)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnrep, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnrep)
                        .addGap(5, 5, 5)))
                .addComponent(jsp2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbdiagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdiagMouseClicked
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        String valorId = "";
        valorId = (String) target.getValueAt(row, 0); //obtener el valor de la columna ID
        mostrarDetalle(valorId);
        anchocolumnas();
        txtnumdiag.setText("");
        txtcd.setText("");
        mostrarnumdiag();
    }//GEN-LAST:event_tbdiagMouseClicked

    private void tbrepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrepMouseClicked
        mostrarcoddiag();
    }//GEN-LAST:event_tbrepMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String errores = validarnumdiag();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                Map parametro = new HashMap();
                JasperReport reportes = JasperCompileManager.compileReport("reportediagnostico.jrxml");
                parametro.put("num", txtnum.getText());
                //se carga el reporte
                //se procesa el archivo jasper
                JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conexion);
                JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta", JOptionPane.WARNING_MESSAGE);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepActionPerformed
        if (rbtner.isSelected() == true) {
            String errores = validartxtrep();
            if (errores.equals("")) {
                try {
                    conexion = claseConectar.ConexionConBaseDatos.getConexion();
                    PreparedStatement pst = conexion.prepareStatement("UPDATE detallediag SET Estado='"
                            + txter.getText() + "' WHERE ID_Diag='" + txtnumdiag.getText() + "' AND Cod_Desp='" + txtcd.getText() + "'");
                    pst.executeUpdate();
                    //UPDATE `detallediag` SET `Estado`= 'En Reparación' WHERE ID_Diag= 00000001 AND Cod_Desp = 'CD0001'
                    JOptionPane.showMessageDialog(this, "Estado Actualizado", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                    mostrardatos();
                    anchocolumnas();
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
                }
            } else {
                JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rbtnrep.isSelected() == true) {
            String errores = validartxtrep();
            if (errores.equals("")) {
                try {
                    conexion = claseConectar.ConexionConBaseDatos.getConexion();
                    PreparedStatement pst = conexion.prepareStatement("UPDATE detallediag SET Estado='"
                            + txtrep.getText() + "' WHERE ID_Diag='" + txtnumdiag.getText() + "' AND Cod_Desp='" + txtcd.getText() + "'");
                    pst.executeUpdate();
                    //UPDATE `detallediag` SET `Estado`= 'En Reparación' WHERE ID_Diag= 00000001 AND Cod_Desp = 'CD0001'
                    JOptionPane.showMessageDialog(this, "Estado Actualizado", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                    mostrardatos();
                    anchocolumnas();
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
                }
            } else {
                JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnrepActionPerformed

    private void rbtnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnerActionPerformed

    }//GEN-LAST:event_rbtnerActionPerformed

    private void rbtnrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnrepActionPerformed

    }//GEN-LAST:event_rbtnrepActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // Arreglarlo con el combobox la weaita 
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrep;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jsp1;
    private javax.swing.JScrollPane jsp2;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private javax.swing.JRadioButton rbtner;
    private javax.swing.JRadioButton rbtnrep;
    private javax.swing.JTable tbdiag;
    private javax.swing.JTable tbrep;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txter;
    private javax.swing.JTextField txtnum;
    private javax.swing.JTextField txtnumdiag;
    private javax.swing.JTextField txtrep;
    // End of variables declaration//GEN-END:variables
}
