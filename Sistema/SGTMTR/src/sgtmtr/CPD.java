/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import CapaDatos.Class_Conectar;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sgtmtr.Principal.jdpescritorio;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZuluCorp
 */
public class CPD extends javax.swing.JInternalFrame {

    /**
     * Creates new form CPD
     */
    public CPD() {
        initComponents();
        this.setTitle("Cambio de Precios de Desperfectos");
        CargarComboDesperfectos();
        Cargartextfield();
        mostrardatos("");
        anchocolumnas();
    }

    private void CargarComboDesperfectos(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st = con.createStatement();
            String sql = "SELECT ID,Descripcion,Costo FROM desperfectos order by ID asc";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            
            //Limpiamos el Combo
            cbdesperfecto.setModel(new DefaultComboBoxModel());
                      
            //Recorremos los registros traidos
            while(rs.next()){
                //Agregamos elemento al combo
                cbdesperfecto.addItem(rs.getObject(2));  
            }
                       
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void Cargartextfield(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st1 = con.createStatement();  
            String sql1 = "SELECT Costo FROM desperfectos WHERE Descripcion='"+cbdesperfecto.getSelectedItem()+"'";
            //Ejecutar consulta
            ResultSet rs1= st1.executeQuery(sql1);
            //Recorremos los registros traidos
            while(rs1.next()){
                //Agregamos elemento al text
                txtprecio.setText(rs1.getObject("Costo").toString());
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Descripciób Desperfecto");
        modelo.addColumn("Costo");
        tbdesperfectos.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM desperfectos order by ID asc";
        } else {
            sql = "SELECT * FROM desperfectos WHERE Descripcion='" + cbdesperfecto.getSelectedItem() + "'";
        }

        String[] datos = new String[3];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }
            tbdesperfectos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }

    }

    void anchocolumnas() {
        tbdesperfectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbdesperfectos.getColumnModel().getColumn(0).setWidth(40);
        tbdesperfectos.getColumnModel().getColumn(0).setMaxWidth(40);
        tbdesperfectos.getColumnModel().getColumn(0).setMinWidth(40);
        
        tbdesperfectos.getColumnModel().getColumn(1).setWidth(300);
        tbdesperfectos.getColumnModel().getColumn(1).setMaxWidth(300);
        tbdesperfectos.getColumnModel().getColumn(1).setMinWidth(300);
        
        tbdesperfectos.getColumnModel().getColumn(2).setWidth(100);
        tbdesperfectos.getColumnModel().getColumn(2).setMaxWidth(100);
        tbdesperfectos.getColumnModel().getColumn(2).setMinWidth(100);
    }
    
    private String validarTarifaVacia() {
        String error="";
        if(txtprecio.getText().equals("")){
            error+="Por favor ingrese el precio a cobrar \n";
        }
        return error;        
    }
     private void limpiarcampos(){
        cbdesperfecto.setSelectedIndex(0);
        txtprecio.setText("");
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
        btcambiar = new javax.swing.JButton();
        cbdesperfecto = new javax.swing.JComboBox();
        txtprecio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btagregar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbdesperfectos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambio de Precios de Desperfectos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Seleccione desperfecto");

        btcambiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btcambiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins.png"))); // NOI18N
        btcambiar.setText("Cambiar Precio");
        btcambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcambiarActionPerformed(evt);
            }
        });

        cbdesperfecto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbdesperfecto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbdesperfecto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbdesperfectoItemStateChanged(evt);
            }
        });

        txtprecio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Precio");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("$");

        btagregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins_add.png"))); // NOI18N
        btagregar.setText("Agregar Nuevo");
        btagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btagregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbdesperfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(btagregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btcambiar)
                .addGap(55, 55, 55))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbdesperfecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btcambiar)
                    .addComponent(btagregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vista de Desperfectos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbdesperfectos.setModel(new javax.swing.table.DefaultTableModel(
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
        jsp.setViewportView(tbdesperfectos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btagregarActionPerformed
        Desperfectos d= new  Desperfectos();
        jdpescritorio.add(d);
        d.show();
    }//GEN-LAST:event_btagregarActionPerformed

    private void btcambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcambiarActionPerformed
        String errores=validarTarifaVacia();
        if(errores.equals("")){
            String ID=txtprecio.getText();
        try {
            if (String.valueOf(txtprecio.getText()).compareTo("") == 0) {
            
            }else{
            JOptionPane.showMessageDialog(this, "Tarifa Actualizada");
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE desperfectos SET Costo='" + txtprecio.getText()
                    + "' WHERE Descripcion='" + cbdesperfecto.getSelectedItem() + "'");
            pst.executeUpdate();
            //cerrar conexion
            con.close();
            //limpiar textfields
            limpiarcampos();
            mostrardatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
        }
            anchocolumnas();
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }    
    }//GEN-LAST:event_btcambiarActionPerformed

    private void cbdesperfectoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbdesperfectoItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED /*&& terminado*/){
            Cargartextfield();
        }
    }//GEN-LAST:event_cbdesperfectoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btagregar;
    private javax.swing.JButton btcambiar;
    private javax.swing.JComboBox cbdesperfecto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbdesperfectos;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
