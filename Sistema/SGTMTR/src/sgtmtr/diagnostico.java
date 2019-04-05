/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class diagnostico extends javax.swing.JInternalFrame {

    /**
     * Creates new form diagnostico
     */
    public diagnostico() {
        initComponents();
        setTitle("Ingreso de Diagnóstico");
        txtnumdiag.setDisabledTextColor(Color.red);
        txtpatentediag.setDisabledTextColor(Color.blue);
        txtfecha.setDisabledTextColor(Color.blue);
        txtfecha.setText(fechaact());
        txtrutcliente.setDisabledTextColor(Color.blue);
        txtmecanico.setDisabledTextColor(Color.blue);
        txtnumrep.setDisabledTextColor(Color.blue);
        txtestdiag.setDisabledTextColor(Color.blue);    
        CargarComboTipoUsers();
        Cargartextfieldmecanico();
        CargarComboED();
        CargartextfieldED();
        numeros();
    }
    
    private void CargarComboTipoUsers(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st = con.createStatement();
            String sql = "SELECT *  FROM usuarios WHERE TipoUsuario=3";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cmbmecanico.setModel(new DefaultComboBoxModel());
                      
            //Recorremos los registros traidos
            while(rs.next()){
                //Agregamos elemento al combo
                cmbmecanico.addItem(rs.getObject(1));
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void Cargartextfieldmecanico(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st1 = con.createStatement();  
            String sql1 = "SELECT Usuario FROM usuarios WHERE TipoUsuario='"+cmbmecanico.getSelectedItem()+"'";
            //Ejecutar consulta
            ResultSet rs1= st1.executeQuery(sql1);
            //Recorremos los registros traidos
            while(rs1.next()){
                //Agregamos elemento al text
                txtmecanico.setText(rs1.getObject("Usuario").toString());
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
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
            String sql = "SELECT ID, Estado FROM estadodiag";
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
    private void CargartextfieldED(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st1 = con.createStatement();  
            String sql1 = "SELECT Estado FROM estadodiag WHERE ID='"+cmbed.getSelectedItem()+"'";
            //Ejecutar consulta
            ResultSet rs1= st1.executeQuery(sql1);
            //Recorremos los registros traidos
            while(rs1.next()){
                //Agregamos elemento al text
                txtestdiag.setText(rs1.getObject("Estado").toString());
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    public static String fechaact(){
    Date fecha= new Date();
    SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
    return formatofecha.format(fecha);
    }
    
    void numeros()
    {
        
        
        String c="";
        String SQL="select max(ID_Diagnostico) from controldiag";
        //String SQL="select count(*) from boleta";
    //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
     //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next())
            {              
                 c=rs.getString(1);
            }
            if(c==null){
                txtnumdiag.setText("00000001");
            }
            else{
            int j=Integer.parseInt(c);
            GenerarNumero gen= new GenerarNumero();
            gen.generar(j);
             txtnumdiag.setText(gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void controldiag(){
        for(int i=0;i<tbdiag.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO controldiag(ID_Diagnostico,Patente,RUTCliente,Mecanico,F_Ing_Diagnostico,F_Ent_Diag,Repuestos,Cotizacion,Estado_Diag VALUES (?,?,?,?,?,?,?,?,?)";
        String numdiag=txtnumdiag.getText();
        String patente=tbdiag.getValueAt(i, 0).toString();
        String RC=tbdiag.getValueAt(i, 1).toString();
        String mecanico=tbdiag.getValueAt(i, 3).toString();
        String ingd=tbdiag.getValueAt(i, 2).toString();
        String entd=tbdiag.getValueAt(i, 4).toString();
        String rep=tbdiag.getValueAt(i, 5).toString();
        String cotiza=tbdiag.getValueAt(i, 6).toString();
        String ediag=tbdiag.getValueAt(i, 7).toString();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numdiag);
            pst.setString(2,patente);
            pst.setString(3,RC);
            pst.setString(4,mecanico);
            pst.setString(5,ingd);
            pst.setString(6,entd);
            pst.setString(7,rep);
            pst.setString(8,cotiza);
            pst.setString(9,ediag);
          
            pst.executeUpdate();
          
           
        } catch (SQLException ex) {
            Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
    void detallediag(){
        for(int i=0;i<tbdiag.getRowCount();i++)
        {
        String InsertarSQL="INSERT INTO detalleboleta(num_bol,cod_pro,des_pro,cant_pro,pre_unit,pre_venta) VALUES (?,?,?,?,?,?)";
        String numbol=txtnumdiag.getText();
        String codpro=tbdiag.getValueAt(i, 0).toString();
        String despro=tbdiag.getValueAt(i, 1).toString();
        String cantpro=tbdiag.getValueAt(i, 3).toString();
        String preunit=tbdiag.getValueAt(i, 2).toString();
        String importe=tbdiag.getValueAt(i, 4).toString();
    
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codpro);
            pst.setString(3,despro);
            pst.setString(4,cantpro);
            pst.setString(5,preunit);
            pst.setString(6,importe);
          
             pst.executeUpdate();
          
           
        } catch (SQLException ex) {
            Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtpatentediag = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtrutcliente = new javax.swing.JTextField();
        cmbmecanico = new javax.swing.JComboBox();
        txtmecanico = new javax.swing.JTextField();
        jdcfed = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtrepuestos = new javax.swing.JTextArea();
        btejecutartbvehiculo = new javax.swing.JButton();
        btejecutartb = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtnumrep = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtestdiag = new javax.swing.JTextField();
        cmbed = new javax.swing.JComboBox();
        btingresodiag = new javax.swing.JButton();
        btejecutarcotizacion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();

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

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Fecha de Ingreso");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Fecha Entrega del Diagnóstico");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Mecánico Asignado");

        txtfecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfecha.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Patente");

        txtpatentediag.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpatentediag.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("RUT Cliente");

        txtrutcliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrutcliente.setEnabled(false);

        cmbmecanico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbmecanico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbmecanico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmecanicoItemStateChanged(evt);
            }
        });

        txtmecanico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmecanico.setEnabled(false);

        jdcfed.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Repuestos");

        txtrepuestos.setColumns(20);
        txtrepuestos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrepuestos.setRows(5);
        jScrollPane1.setViewportView(txtrepuestos);

        btejecutartbvehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecutartbvehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutartbvehiculoActionPerformed(evt);
            }
        });

        btejecutartb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecutartb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutartbActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Cotización");

        txtnumrep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnumrep.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Estado del Diagnóstico");

        txtestdiag.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtestdiag.setEnabled(false);

        cmbed.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbedItemStateChanged(evt);
            }
        });

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

        btejecutarcotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecutarcotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutarcotizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcfed, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel14))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtpatentediag, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btejecutartbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel1)
                            .addComponent(jLabel16)))
                    .addComponent(btingresodiag))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btejecutartb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtnumrep, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btejecutarcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbed, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtestdiag, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpatentediag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cmbmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btejecutartb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 15, Short.MAX_VALUE))
                            .addComponent(btejecutartbvehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(txtnumrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(cmbed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtestdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btejecutarcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcfed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btingresodiag)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbdiag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jsp.setViewportView(tbdiag);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btejecutartbvehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutartbvehiculoActionPerformed
        try {
            bv bv= new bv();
            Principal.jdpescritorio.add(bv);
            bv.toFront();
            bv.setVisible(true);

            } catch (Exception e) {
            }
    }//GEN-LAST:event_btejecutartbvehiculoActionPerformed

    private void cmbmecanicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmecanicoItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
             Cargartextfieldmecanico();
        }
    }//GEN-LAST:event_cmbmecanicoItemStateChanged

    private void cmbedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbedItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
             CargartextfieldED();
        }
    }//GEN-LAST:event_cmbedItemStateChanged

    private void btejecutartbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutartbActionPerformed
        try {
            bcliente bc= new bcliente();
            Principal.jdpescritorio.add(bc);
            bc.toFront();
            bc.setVisible(true);

            } catch (Exception e) {
            }
    }//GEN-LAST:event_btejecutartbActionPerformed

    private void btingresodiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresodiagActionPerformed
        if((txtpatentediag.getText().equals(""))||(txtrutcliente.getText().equals(""))){
        JOptionPane.showMessageDialog(this, "Ingrese patente, RUT o realice operacion");
    }
    else{
         String capcod="",capcan="";
    //for(int i=0;i<diagnostico.tbdiag.getRowCount();i++)
    //{
        ////capcod=diagnostico.tbdiag.getValueAt(i, 0).toString();
       // capcan=diagnostico.tbdiag.getValueAt(i, 3).toString();
        //descontarstock(capcod, capcan);
        
    //}
    controldiag();
    //detalleboleta();
    txtpatentediag.setText("");
        txtrutcliente.setText("");
        txtnumrep.setText("");
        txtrepuestos.setText("");
       
        DefaultTableModel modelo = (DefaultTableModel) tbdiag.getModel();
        int a =tbdiag.getRowCount()-1;
        int i;
        for(i=a;i>=0;i--)
        {
            modelo.removeRow(i);
        }
        numeros();
    }
    }//GEN-LAST:event_btingresodiagActionPerformed

    private void btejecutarcotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutarcotizacionActionPerformed
        // TODO add your handling code here:
     
        
    }//GEN-LAST:event_btejecutarcotizacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btejecutarcotizacion;
    private javax.swing.JButton btejecutartb;
    private javax.swing.JButton btejecutartbvehiculo;
    private javax.swing.JButton btingresodiag;
    private javax.swing.JComboBox cmbed;
    private javax.swing.JComboBox cmbmecanico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcfed;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbdiag;
    private javax.swing.JTextField txtestdiag;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtmecanico;
    private javax.swing.JTextField txtnumdiag;
    public static javax.swing.JTextField txtnumrep;
    public static javax.swing.JTextField txtpatentediag;
    private javax.swing.JTextArea txtrepuestos;
    public static javax.swing.JTextField txtrutcliente;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();  
}
