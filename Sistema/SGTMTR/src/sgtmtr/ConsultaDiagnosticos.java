/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author ZuluCorp
 */
public class ConsultaDiagnosticos extends javax.swing.JInternalFrame {
ValidarCaracteres validarLetras = new ValidarCaracteres();
DateFormat df = DateFormat.getDateInstance();
    /**
     * Creates new form ConsultaComprobantes
     */
    public ConsultaDiagnosticos() {
        initComponents();
        //CalendarioVta.setEnabled(false);
        this.setTitle("Consulta y detalle de comprobante de diagnóstico");
        this.setLocation(280,0);
        this.mostrardatos();
        this.anchocolumnas();
        mniVerDetalle.setVisible(false);
        bfecha.getDateEditor().setEnabled(false);
        CargarComboEstDiag();
    }
    
    private void LimpiaTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        tbdetdiags.setModel(new DefaultTableModel());
        for (int i = 0; i < tbdetdiags.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    private void CargarComboEstDiag() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st = conexion.createStatement();
            String sql = "SELECT Estado FROM estadodiag ORDER BY ID ASC";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);

            //Limpiamos el Combo
            cbbusqxest.setModel(new DefaultComboBoxModel());

            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cbbusqxest.addItem(rs.getObject(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void mostrardatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Número Diagnóstico");
        modelo.addColumn("Patente");
        modelo.addColumn("RUT Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Apellidos Cliente");
        modelo.addColumn("E-mail Cliente");
        modelo.addColumn("Mecánico");
        modelo.addColumn("Ingreso D.");
        modelo.addColumn("Entrega D.");
        modelo.addColumn("Repuestos");
        modelo.addColumn("Estado de la Reparación");
        tbbusqdiag.setModel(modelo);
        String sql = "";
        sql = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT";
        String[] datos = new String[11];
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
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                modelo.addRow(datos);
                if (primerId.isEmpty()) {
                    primerId = rs.getString(1);
                }
            }
            tbbusqdiag.setModel(modelo);
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
        int filaseleccionada = tbbusqdiag.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            /*String diag = tbbusqdiag.getValueAt(filaseleccionada, 0).toString();
            String patente= tbbusqdiag.getValueAt(filaseleccionada, 1).toString();
            String rut = tbbusqdiag.getValueAt(filaseleccionada, 2).toString();
            String nc = tbbusqdiag.getValueAt(filaseleccionada, 3).toString();
            String ac = tbbusqdiag.getValueAt(filaseleccionada, 4).toString();
            String fecha = tbbusqdiag.getValueAt(filaseleccionada, 5).toString();
            String hora = tbbusqdiag.getValueAt(filaseleccionada, 6).toString();
            String vendedor = tbbusqdiag.getValueAt(filaseleccionada, 7).toString();
            txtcomp.setText(diag);
            txtrutcte.setText(rut);
            Patente.setText(patente);
            txtfecha.setText(fecha);
            txthora.setText(hora);
            txtfecha.setText(fecha);
            txtvendedor.setText(vendedor);*/
            tbdetdiags.setModel(modelo2);
            String sql1 = "";
            sql1 = "SELECT ID_Diag,Cod_Desp,Desc_Desperfecto,Cantidad,PrecioDesp,Ptotal,Estado FROM detallediag INNER JOIN controldiag ON detallediag.ID_Diag = controldiag.ID_Diagnostico "
                + "WHERE ID_Diag =" + iddetalle;
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
                tbdetdiags.setModel(modelo2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaDiagnosticos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        }
    }

    void anchocolumnas() {
        tbbusqdiag.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbbusqdiag.getColumnModel().getColumn(0).setWidth(100);
        tbbusqdiag.getColumnModel().getColumn(0).setMaxWidth(100);
        tbbusqdiag.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbbusqdiag.getColumnModel().getColumn(1).setWidth(100);
        tbbusqdiag.getColumnModel().getColumn(1).setMaxWidth(100);
        tbbusqdiag.getColumnModel().getColumn(1).setMinWidth(100);
        
        tbbusqdiag.getColumnModel().getColumn(2).setWidth(100);
        tbbusqdiag.getColumnModel().getColumn(2).setMaxWidth(100);
        tbbusqdiag.getColumnModel().getColumn(2).setMinWidth(100);
        
        tbbusqdiag.getColumnModel().getColumn(3).setWidth(135);
        tbbusqdiag.getColumnModel().getColumn(3).setMaxWidth(135);
        tbbusqdiag.getColumnModel().getColumn(3).setMinWidth(135);
        
        tbbusqdiag.getColumnModel().getColumn(4).setWidth(100);
        tbbusqdiag.getColumnModel().getColumn(4).setMaxWidth(100);
        tbbusqdiag.getColumnModel().getColumn(4).setMinWidth(100);
        
        tbbusqdiag.getColumnModel().getColumn(5).setWidth(100);
        tbbusqdiag.getColumnModel().getColumn(5).setMaxWidth(100);
        tbbusqdiag.getColumnModel().getColumn(5).setMinWidth(100);
    }
    private String validarnumeroVacio() {
        String errores="";
        if(txtpatente.getText().equals("")){
            errores+="Por favor busque el número de comprobante\n";
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mniVerDetalle = new javax.swing.JMenuItem();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbbusqdiag = new javax.swing.JTable();
        rbtbxn = new javax.swing.JRadioButton();
        txtpatente = new javax.swing.JTextField();
        btnbuscardetalle = new javax.swing.JButton();
        bfecha = new com.toedter.calendar.JDateChooser();
        rbtbxf = new javax.swing.JRadioButton();
        rbtmt = new javax.swing.JRadioButton();
        rbtnbxe = new javax.swing.JRadioButton();
        cbbusqxest = new javax.swing.JComboBox();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp1 = new javax.swing.JScrollPane();
        tbdetdiags = new javax.swing.JTable();

        mniVerDetalle.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mniVerDetalle.setText("Ver Detalle");
        mniVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerDetalleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mniVerDetalle);

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione Opción de Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbbusqdiag.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbbusqdiag = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbbusqdiag.setSelectionBackground(Color.LIGHT_GRAY);
        tbbusqdiag.setSelectionForeground(Color.blue);
        tbbusqdiag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbbusqdiag.setComponentPopupMenu(jPopupMenu1);
        tbbusqdiag.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbbusqdiag.getTableHeader().setResizingAllowed(false);
        tbbusqdiag.getTableHeader().setReorderingAllowed(false);
        tbbusqdiag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbusqdiagMouseClicked(evt);
            }
        });
        jsp.setViewportView(tbbusqdiag);

        buttonGroup1.add(rbtbxn);
        rbtbxn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtbxn.setForeground(new java.awt.Color(255, 255, 255));
        rbtbxn.setSelected(true);
        rbtbxn.setText("Buscar Por Patente");
        rbtbxn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtbxnActionPerformed(evt);
            }
        });

        txtpatente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpatente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpatenteKeyTyped(evt);
            }
        });

        btnbuscardetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busquedadetodo.png"))); // NOI18N
        btnbuscardetalle.setText("Buscar");
        btnbuscardetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscardetalleActionPerformed(evt);
            }
        });

        bfecha.setDate(Calendar.getInstance().getTime());
        bfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bfechaMousePressed(evt);
            }
        });

        buttonGroup1.add(rbtbxf);
        rbtbxf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtbxf.setForeground(new java.awt.Color(255, 255, 255));
        rbtbxf.setText("Buscar Por Fecha de Ingreso");
        rbtbxf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtbxfActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtmt);
        rbtmt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtmt.setForeground(new java.awt.Color(255, 255, 255));
        rbtmt.setText("Mostrar Todos");
        rbtmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtmtActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnbxe);
        rbtnbxe.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtnbxe.setForeground(new java.awt.Color(255, 255, 255));
        rbtnbxe.setText("Búsqueda por Estado de Reparación");
        rbtnbxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnbxeActionPerformed(evt);
            }
        });

        cbbusqxest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtbxf)
                            .addComponent(rbtbxn)
                            .addComponent(rbtmt)
                            .addComponent(rbtnbxe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(cbbusqxest, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscardetalle))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtbxn)
                            .addComponent(txtpatente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtbxf)))
                    .addComponent(btnbuscardetalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnbxe)
                    .addComponent(cbbusqxest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtmt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Diagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        panelTranslucido2.setForeground(new java.awt.Color(255, 255, 255));

        tbdetdiags.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbdetdiags = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbdetdiags.setSelectionBackground(Color.LIGHT_GRAY);
        tbdetdiags.setSelectionForeground(Color.blue);
        tbdetdiags.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdetdiags.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdetdiags.getTableHeader().setResizingAllowed(false);
        tbdetdiags.getTableHeader().setReorderingAllowed(false);
        tbdetdiags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdetdiagsMouseClicked(evt);
            }
        });
        jsp1.setViewportView(tbdetdiags);

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp1)
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addComponent(jsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerDetalleActionPerformed
        /*int filaseleccionada = tbbusqins.getSelectedRow();
        if (filaseleccionada == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            DetalleComprobante detalle = new DetalleComprobante();
            Principal.jdpescritorio.add(detalle);
            detalle.toFront();
            detalle.setVisible(true);
            String numcomp = tbbusqins.getValueAt(filaseleccionada, 0).toString();
            String rut = tbbusqins.getValueAt(filaseleccionada, 1).toString();
            String total = tbbusqins.getValueAt(filaseleccionada, 2).toString();
            String fecha = tbbusqins.getValueAt(filaseleccionada, 5).toString();
            String hora = tbbusqins.getValueAt(filaseleccionada, 6).toString();
            String sucursal = tbbusqins.getValueAt(filaseleccionada, 7).toString();
            DetalleComprobante.txtcomp.setText(numcomp);
            DetalleComprobante.txtrutcte.setText(rut);
            DetalleComprobante.txttotal.setText(total);
            DetalleComprobante.txtfecha.setText(fecha);
            DetalleComprobante.txthora.setText(hora);
            DetalleComprobante.txtsucursal.setText(sucursal);
            DetalleComprobante.txtfecha.setText(fecha);
            DefaultTableModel model = (DefaultTableModel) DetalleComprobante.tbdetvtains.getModel();
            String ver = "SELECT * FROM detallecomprobante WHERE NumComp='" + numcomp + "'";
            String[] datos = new String[5];
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ver);
                while (rs.next()) {
                    datos[0] = rs.getString("CodPro");
                    datos[1] = rs.getString("DescProducto");
                    datos[2] = rs.getString("Cantidad");
                    datos[3] = rs.getString("PrecioUnitario");
                    datos[4] = rs.getString("PrecioTotal");
                    model.addRow(datos);

                }
                DetalleComprobante.tbdetvtains.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_mniVerDetalleActionPerformed

    private void rbtbxnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtbxnActionPerformed
        if (rbtbxn.isSelected() == true) {
            txtpatente.setEnabled(true);
            txtpatente.requestFocus();
            cbbusqxest.setEnabled(false);
           // CalendarioVta.setEnabled(false);
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtbxnActionPerformed

    private void rbtbxfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtbxfActionPerformed
        if (rbtbxf.isSelected() == true) {
            //CalendarioVta.setEnabled(true);
            txtpatente.setEnabled(false);
            txtpatente.setText("");
            cbbusqxest.setEnabled(false);
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtbxfActionPerformed

    private void rbtmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtmtActionPerformed
        if (rbtmt.isSelected() == true) {
           // CalendarioVta.setEnabled(false);
            txtpatente.setText("");
            txtpatente.setEnabled(false);
            cbbusqxest.setEnabled(false);
            mostrardatos();
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtmtActionPerformed

    private void btnbuscardetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscardetalleActionPerformed
        String patente = txtpatente.getText();
        String consulta = "";
        if (rbtbxn.isSelected() == true) {
            consulta = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT AND Patente = '"+patente+"'";
            anchocolumnas();
            LimpiaTabla();
        }
        if (rbtbxf.isSelected() == true) {
            String fecha = df.format(bfecha.getDate());
            consulta = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT AND  F_ing_Diagnostico='"+fecha+"'";
            anchocolumnas();
            LimpiaTabla();
        }
        if (rbtnbxe.isSelected() == true) {
            consulta = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT AND Estado_Diag='"+cbbusqxest.getSelectedItem()+"'";
            anchocolumnas();
            LimpiaTabla();
        }
        if (rbtmt.isSelected() == true) {
            consulta = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT";
            LimpiaTabla();
            mostrardatos();
            anchocolumnas();
        }
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Número", "Patente", "RUT del Dueño", "Nombre Dueño", "Apellidos Dueño", "E-mail", "Mecánico Asignado", "Ingreso Diag.", "Entrega Diag.", "Repuestos", "Estado"};
        tabla.setColumnIdentifiers(titulos);
        tbbusqdiag.setModel(tabla);

        String[] Datos = new String[11];
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("ID_Diagnostico");
                Datos[1] = rs.getString("Patente");
                Datos[2] = rs.getString("RUTCliente");
                Datos[3] = rs.getString("Nombres");
                Datos[4] = rs.getString("Apellidos");
                Datos[5] = rs.getString("Correo");
                Datos[6] = rs.getString("Mecanico");
                Datos[7] = rs.getString("F_ing_Diagnostico");
                Datos[8] = rs.getString("F_Ent_Diag");
                Datos[9] = rs.getString("Repuestos");
                Datos[10] = rs.getString("Estado_Diag");
                tabla.addRow(Datos);
                anchocolumnas();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }//GEN-LAST:event_btnbuscardetalleActionPerformed

    private void tbbusqdiagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbusqdiagMouseClicked
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            String valorId = "";
            valorId = (String) target.getValueAt(row, 0); //obtener el valor de la columna ID
            mostrarDetalle(valorId);
            anchocolumnas();
    }//GEN-LAST:event_tbbusqdiagMouseClicked

    private void tbdetdiagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetdiagsMouseClicked

    }//GEN-LAST:event_tbdetdiagsMouseClicked

    private void bfechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bfechaMousePressed
//        String fecha = df.format(bfecha.getDate());
//        vefecha.setText(fecha);        
    }//GEN-LAST:event_bfechaMousePressed

    private void txtpatenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpatenteKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtpatente.getText().length() == 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtpatenteKeyTyped

    private void rbtnbxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnbxeActionPerformed
        if (rbtnbxe.isSelected() == true) {
            cbbusqxest.setEnabled(true);
            txtpatente.setEnabled(false);
            txtpatente.setText("");
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtnbxeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser bfecha;
    private javax.swing.JButton btnbuscardetalle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbbusqxest;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JScrollPane jsp1;
    private javax.swing.JMenuItem mniVerDetalle;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private javax.swing.JRadioButton rbtbxf;
    private javax.swing.JRadioButton rbtbxn;
    private javax.swing.JRadioButton rbtmt;
    private javax.swing.JRadioButton rbtnbxe;
    private javax.swing.JTable tbbusqdiag;
    public static javax.swing.JTable tbdetdiags;
    private javax.swing.JTextField txtpatente;
    // End of variables declaration//GEN-END:variables
}
