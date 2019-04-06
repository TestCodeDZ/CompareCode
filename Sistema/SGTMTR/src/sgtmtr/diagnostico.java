/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import static sgtmtr.Principal.jdpescritorio;
/**
 *
 * @author ZuluCorp
 */
public class diagnostico extends javax.swing.JInternalFrame {
ValidarCaracteres validarLetras = new ValidarCaracteres();
CalculaPrecioTB calcula = new CalculaPrecioTB();
    /**
     * Creates new form diagnostico
     */
    
    public diagnostico() {
        initComponents();
        setTitle("Ingreso de Diagnóstico");
        this.setLocation(280,0);
        txtnumdiag.setDisabledTextColor(Color.red);
        txtpatentediag.setDisabledTextColor(Color.blue);
        //txtfecha.setDisabledTextColor(Color.blue);
        //txtfecha.setText(fechaact());
        txtrutcliente.setDisabledTextColor(Color.blue);  
        CargarComboED();
        numeros();
        /*Deshabilitar txt del jcalendar*/
        fing.getDateEditor().setEnabled(false);
        fent.getDateEditor().setEnabled(false);
        txtmecanico.setText("" + Login.nomUsuario);
        txtmecanico.setDisabledTextColor(Color.blue);
        //jdatechooser vacio
        /*if(jdcfed.getDate()==null){
        }*/
        //fechaingreso.setEnabled(false);
        
        // hasta 255 caracteres o cta regresiva conteo = txtrepuestos.getText()).length();
        
        lbconteo.setText("0");
    }
    
    private void CargarComboED(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st = con.createStatement();
            String sql = "SELECT Estado FROM estadodiag";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cmbed.setModel(new DefaultComboBoxModel());
                      
            //Recorremos los registros traidos
            while(rs.next()){
                //Agregamos elemento al combo
                cmbed.addItem(rs.getObject(1));
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    public static String fechaact(){
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatofecha.format(fecha);
    }
    
    void numeros() {
        String c = "";
        String SQL = "select max(ID_Diagnostico) from controldiag";
        //String SQL="select count(*) from boleta";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtnumdiag.setText("00000001");
            } else {
                int j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                txtnumdiag.setText(gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void controldiag() {
            String InsertarSQL = "INSERT INTO controldiag(ID_Diagnostico,Patente,RUTCliente,Mecanico,F_Ing_Diagnostico,F_Ent_Diag,Repuestos,Total,Estado_Diag) VALUES (?,?,?,?,?,?,?,?,?)";
            String numdiag = txtnumdiag.getText();
            String patente = txtpatentediag.getText();
            String RC = txtrutcliente.getText();
            String mecanico = txtmecanico.getText();
            Date fc1=fing.getDate();
            DateFormat fecha1 = new SimpleDateFormat("dd-MM-yyyy");
            String convertido1 = fecha1.format(fc1);
            Date fc2=fing.getDate();
            DateFormat fecha2 = new SimpleDateFormat("dd-MM-yyyy");
            String convertido2 = fecha2.format(fc2);
            String rep = txtrepuestos.getText();
            String total = txtve.getText();
            String ediag = cmbed.getSelectedItem().toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1, numdiag);
                pst.setString(2, patente);
                pst.setString(3, RC);
                pst.setString(4, mecanico);
                pst.setString(5, convertido1);
                pst.setString(6, convertido2);
                pst.setString(7, rep);
                pst.setString(8, total);
                pst.setString(9, ediag);

                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se ha ingresado el diagnóstino N° " + numdiag);
                }
            } catch (SQLException ex) {
                Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
            }

        /* no sirve
            //convertir fechas a string
            DateFormat df1 = DateFormat.getDateInstance();
            String fecha1 = df1.format(fing.getDate());
            DateFormat df2 = DateFormat.getDateInstance();
            String fecha2 = df2.format(fent.getDate());*/
    }

    void detallediag() {
        for (int i = 0; i < tbdiag.getRowCount(); i++) {
            String InsertarSQL = "INSERT INTO detallediag(ID_Diag,Cod_Desp,Desc_Desperfecto,Cantidad,PrecioDesp,Ptotal,Estado) VALUES (?,?,?,?,?,?,?)";
            String ND = txtnumdiag.getText();
            String CD = tbdiag.getValueAt(i, 0).toString();
            String DD = tbdiag.getValueAt(i, 1).toString();
            String CDesp = tbdiag.getValueAt(i, 2).toString();
            String PD = tbdiag.getValueAt(i, 3).toString();
            String PT = tbdiag.getValueAt(i, 4).toString();
            String ED = tbdiag.getValueAt(i, 5).toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1, ND);
                pst.setString(2, CD);
                pst.setString(3, DD);
                pst.setString(4, CDesp);
                pst.setString(5, PD);
                pst.setString(6, PT);
                pst.setString(7, ED);

                pst.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        txtnumdiag = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtpatentediag = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtrutcliente = new javax.swing.JTextField();
        btejecutartbvehiculo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbed = new javax.swing.JComboBox();
        fent = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btbuscadesperfecto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtve = new javax.swing.JTextField();
        btingresodiag = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtrepuestos = new javax.swing.JTextArea();
        lbconteo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fing = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtmecanico = new javax.swing.JTextField();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diagnóstico N°", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtnumdiag.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Diagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Fecha Entrega Diagnóstico");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Patente");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtpatentediag.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtpatentediag.setEnabled(false);
        jPanel4.add(txtpatentediag, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 30, 110, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("RUT Cliente");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtrutcliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrutcliente.setEnabled(false);
        jPanel4.add(txtrutcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 88, -1));

        btejecutartbvehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecutartbvehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutartbvehiculoActionPerformed(evt);
            }
        });
        jPanel4.add(btejecutartbvehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 30, 33, 32));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Estado del Diagnóstico");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, 20));

        cmbed.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbedItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbed, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 130, 20));

        fent.setDate(Calendar.getInstance().getTime());
        fent.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fentPropertyChange(evt);
            }
        });
        jPanel4.add(fent, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 130, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbdiag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Desperfecto", "Descripción Desperfecto", "Cantidad", "Precio Desperfecto", "Precio Total", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jsp.setViewportView(tbdiag);
        if (tbdiag.getColumnModel().getColumnCount() > 0) {
            tbdiag.getColumnModel().getColumn(0).setResizable(false);
            tbdiag.getColumnModel().getColumn(1).setResizable(false);
            tbdiag.getColumnModel().getColumn(2).setResizable(false);
            tbdiag.getColumnModel().getColumn(3).setResizable(false);
            tbdiag.getColumnModel().getColumn(4).setResizable(false);
            tbdiag.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Desperfectos");

        btbuscadesperfecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btbuscadesperfecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscadesperfectoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Valor Estimado");

        txtve.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtve.setEnabled(false);

        btingresodiag.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btingresodiag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/multi.png"))); // NOI18N
        btingresodiag.setText("Ingresar Diagnóstico");
        btingresodiag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btingresodiag.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btingresodiag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btingresodiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btingresodiagActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        jButton1.setText("Eliminar Desperfecto");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btbuscadesperfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(24, 24, 24)
                                .addComponent(txtve, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btingresodiag)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btbuscadesperfecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btingresodiag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Repuestos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtrepuestos.setColumns(20);
        txtrepuestos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrepuestos.setRows(5);
        txtrepuestos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrepuestosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtrepuestos);

        lbconteo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbconteo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 586, Short.MAX_VALUE)
                        .addComponent(lbconteo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbconteo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Fecha de Ingreso");

        fing.setDate(Calendar.getInstance().getTime());
        fing.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Mecánico");

        txtmecanico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmecanico.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(fing, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btejecutartbvehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutartbvehiculoActionPerformed
        try {
            bv bv = new bv(); //crear el nuevo formulario
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (bv.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Busca Vehículo: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Busca Vehículo: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(bv);
            }
            bv.show();
            bv.toFront();

            /*Principal.jdpescritorio.add(bv);
            
             bv.setVisible(true);*/
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btejecutartbvehiculoActionPerformed

    private void cmbedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbedItemStateChanged
        /*if(evt.getStateChange() == ItemEvent.SELECTED) {
             CargartextfieldED();
        }*/
    }//GEN-LAST:event_cmbedItemStateChanged
     private String validarVacios() {
        String errores="";
        if(txtrutcliente.getText().equals("")){
            errores+="Por favor busque los datos del cliente\n";
        }
        if(txtve.getText().equals("")){
            errores+="Calcule el monto total \n";
        }
        if(txtve.getText().equals("0")){
            errores+="Revise el detalle de la venta \n";
        }
        if(txtrepuestos.getText().trim().isEmpty()){
            errores+="Ingrese el valor que el cliente pagó\n";
        }
        return errores;       
    }
     public void generapdf() {
        try {
            conectar cc = new conectar();
            Map parametro = new HashMap();
            JasperReport reportes = JasperCompileManager.compileReport("reportediagnostico.jrxml");
            parametro.put("num", txtnumdiag.getText());
                //se carga el reporte
            //se procesa el archivo jasper
            JasperPrint print = JasperFillManager.fillReport(reportes, parametro, cc.conexion());
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }
    private void btingresodiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresodiagActionPerformed
        String errores=validarVacios();
        if(errores.equals("")){
            try{
            controldiag();
            detallediag();
            generapdf();
            JOptionPane.showMessageDialog(null, "Se han insertado registros del diagnóstico", "El sistema está generando el comprobante de diagnóstico", JOptionPane.WARNING_MESSAGE);
            //generapdf();
            txtpatentediag.setText("");
            txtrutcliente.setText("");
            cmbed.setSelectedIndex(0);
            //dejar jcalendar con la fecha de hoy.
            txtrepuestos.setText("");
            txtve.setText("");
            DefaultTableModel modelo = (DefaultTableModel) tbdiag.getModel();
            int a = tbdiag.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }
            numeros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error"+ e.getMessage().toString());
            }  
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }         
    }//GEN-LAST:event_btingresodiagActionPerformed

    private void btbuscadesperfectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscadesperfectoActionPerformed
        SeleccionDesperfecto sd = new SeleccionDesperfecto(null, true);
        sd.setVisible(true);
    }//GEN-LAST:event_btbuscadesperfectoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbdiag.getModel();
        int fila = tbdiag.getSelectedRow();
        if (fila >= 0) {
            model.removeRow(fila);
            calcula.calcularRep();
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila para eliminar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtrepuestosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrepuestosKeyTyped
        validarLetras.soloLetras(evt);
        if (txtrepuestos.getText().length() == 255) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        int xD = txtrepuestos.getText().length();
        lbconteo.setText(String.valueOf(xD));
    }//GEN-LAST:event_txtrepuestosKeyTyped

    private void fentPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fentPropertyChange
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, fent.getCalendar().getInstance().getTime() );
        
    }//GEN-LAST:event_fentPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbuscadesperfecto;
    private javax.swing.JButton btejecutartbvehiculo;
    private javax.swing.JButton btingresodiag;
    private javax.swing.JComboBox cmbed;
    private com.toedter.calendar.JDateChooser fent;
    private com.toedter.calendar.JDateChooser fing;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel lbconteo;
    public static javax.swing.JTable tbdiag;
    private javax.swing.JTextField txtmecanico;
    private javax.swing.JTextField txtnumdiag;
    public static javax.swing.JTextField txtpatentediag;
    private javax.swing.JTextArea txtrepuestos;
    public static javax.swing.JTextField txtrutcliente;
    public static javax.swing.JTextField txtve;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();  
}