/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import java.awt.Toolkit;
import java.sql.Connection;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class ClientesSistema extends javax.swing.JInternalFrame {
    ValidarCaracteres validarLetras = new ValidarCaracteres();
    /**
     * Creates new form ClientesSistema
     */
    javax.swing.ImageIcon icono= new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aprobado.png"));
    javax.swing.ImageIcon iconoNo= new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cross.png"));
    
    public ClientesSistema() {
        initComponents();
        this.setLocation(150,15 );
        setTitle("Mantenedor de Clientes");
        mostrardatos("");
        anchocolumnas();
        bloquear();
    }
    
     void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        String rut=txtrut.getText();
        String dv=txtdv.getText();
        String union=rut+"-"+dv;
        modelo.addColumn("RUT");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fono");
        modelo.addColumn("Dirección");
        modelo.addColumn("E-mail");
        tbclientes.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM clientes ";
        } else {
            sql = "SELECT * FROM clientes WHERE RUT='" + union + "'";
        }

        String[] datos = new String[6];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            tbclientes.setModel(modelo);
            //tbclientes.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
    }

    void anchocolumnas() {
        tbclientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbclientes.getColumnModel().getColumn(0).setWidth(100);
        tbclientes.getColumnModel().getColumn(0).setMaxWidth(100);
        tbclientes.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbclientes.getColumnModel().getColumn(1).setWidth(100);
        tbclientes.getColumnModel().getColumn(1).setMaxWidth(100);
        tbclientes.getColumnModel().getColumn(1).setMinWidth(100);

        tbclientes.getColumnModel().getColumn(2).setWidth(100);
        tbclientes.getColumnModel().getColumn(2).setMaxWidth(100);
        tbclientes.getColumnModel().getColumn(2).setMinWidth(100);

        tbclientes.getColumnModel().getColumn(3).setWidth(120);
        tbclientes.getColumnModel().getColumn(3).setMaxWidth(120);
        tbclientes.getColumnModel().getColumn(3).setMinWidth(120);

        tbclientes.getColumnModel().getColumn(4).setWidth(90);
        tbclientes.getColumnModel().getColumn(4).setMaxWidth(90);
        tbclientes.getColumnModel().getColumn(4).setMinWidth(90);

        tbclientes.getColumnModel().getColumn(5).setWidth(200);
        tbclientes.getColumnModel().getColumn(5).setMaxWidth(200);
        tbclientes.getColumnModel().getColumn(5).setMinWidth(200);
    }

    private String validarVacios() {
        
        String errores="";
        
        if(txtrut.getText().equals("")){
            errores+="Por favor digite el RUT \n";
        }
        if(txtdv.getText().equals("")){
            errores+="Por favor genere el dígito verificador \n";
        }
        if(txtapellidos.getText().trim().isEmpty()){
            errores+="El campo nombre está vacio \n";
        }
        if(txtnombres.getText().trim().isEmpty()){
            errores+="Por favor escriba el nombre \n";
        }
        if(txtapellidos.getText().trim().isEmpty()){
            errores+="Por favor escriba el apellido \n";
        }
        if(txtfono.getText().trim().isEmpty()){
            errores+="Escriba el número de contacto \n";
        }
        if(txtdireccion.getText().trim().isEmpty()){
            errores+="Por favor escriba la dirección \n";
        }
        if(txtemail.getText().trim().isEmpty()){
            errores+="Por favor escriba el correo electrónico \n";
        }
        return errores;       
    }
    
      private String validarRUTVacio() {
        String errores="";
        if(txtrut.getText().equals("")){
            errores+="Por favor digite el RUT \n";
        }
        if(txtdv.getText().equals("")){
            errores+="Por favor genere el dígito verificador \n";
        }
        return errores;       
    }
       void desbloquear(){
        txtrut.setEnabled(true);
        txtnombres.setEnabled(true);
        txtapellidos.setEnabled(true);
        txtfono.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtemail.setEnabled(true);
        btbuscar.setEnabled(true);
        btingresar.setEnabled(true);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(true);
        txtrut.requestFocus();
    }
      void bloquear(){
        txtrut.setEnabled(false);
        txtnombres.setEnabled(false);
        txtapellidos.setEnabled(false);
        txtfono.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtemail.setEnabled(false);
        btbuscar.setEnabled(false);
        btingresar.setEnabled(false);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(false);
        txtrut.requestFocus();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtrut = new javax.swing.JTextField();
        txtdv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        txtapellidos = new javax.swing.JTextField();
        txtfono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        lblimg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnuevo = new javax.swing.JButton();
        btingresar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        btborrar = new javax.swing.JButton();
        btbuscar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("RUT");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombres");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Fono");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Dirección");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("E-mail");

        txtrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrutFocusLost(evt);
            }
        });
        txtrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrutKeyTyped(evt);
            }
        });

        txtdv.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtdv.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("-");

        txtnombres.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresKeyTyped(evt);
            }
        });

        txtapellidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidosKeyTyped(evt);
            }
        });

        txtfono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfonoKeyTyped(evt);
            }
        });

        txtdireccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtemailFocusLost(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });

        lblimg.setPreferredSize(new java.awt.Dimension(34, 32));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtnombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtapellidos, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtfono, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtdv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-icon.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        btingresar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept.png"))); // NOI18N
        btingresar.setText("Ingresar");
        btingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btingresarActionPerformed(evt);
            }
        });

        btmodificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/page_edit.png"))); // NOI18N
        btmodificar.setText("Modificar");
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
            }
        });

        btborrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btborrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btborrar.setText("Borrar");
        btborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btborrarActionPerformed(evt);
            }
        });

        btbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/92125_find_user-512.png"))); // NOI18N
        btbuscar.setText("Buscar");
        btbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscarActionPerformed(evt);
            }
        });

        btlimpiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btingresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btborrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btborrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación Tabla Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbclientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbclientes.setSelectionBackground(Color.LIGHT_GRAY);
        tbclientes.setSelectionForeground(Color.blue);
        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jsp.setViewportView(tbclientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        desbloquear();
        //limpiar();
        //codigosusuarios();
        txtrut.requestFocus();
    }//GEN-LAST:event_btnuevoActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        limpiar();
        mostrardatos("");
        txtrut.setEnabled(true);
        txtrut.requestFocus();
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void txtrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrutFocusLost
         String codigo;
        int multiplo=2;
        int cont=0;
        for (int x=0;x<txtrut.getText().length();x++){
            cont=cont+(Integer.parseInt(txtrut.getText().substring(txtrut.getText().length()-x-1, txtrut.getText().length()-x))*multiplo);
            multiplo++;
            if(multiplo==8){
                multiplo=2;
            }

        }
        cont=11-(cont%11);
        if(cont<=9){
            codigo=""+cont;
        }else if(cont==11){
            codigo="0";
        }else{
            codigo="K";
        }
        if(codigo!=null){
            txtdv.setText(codigo);
        }
        if(txtrut.getText().length()>=7){
            lblimg.setIcon(icono);
        }else{
            lblimg.setIcon(iconoNo);
            JOptionPane.showMessageDialog(null, "El RUT ingresado no contiene los caracteres necesarios para ser validado");
            txtrut.setText("");
            txtdv.setText("");
        }
        
        //int numrut;
       // numrut=Integer.parseInt(txtrut.getText());
        //if((numrut==00000000) && (numrut<01000000)){
        //JOptionPane.showMessageDialog(null, "El RUT ingresado no puede ser validado");
        //}
    }//GEN-LAST:event_txtrutFocusLost

    private void txtrutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutKeyTyped
        validarLetras.soloNumeros(evt);
        if(txtrut.getText().length()>=8){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtrutKeyTyped
    void limpiar(){
        txtrut.setText("");
        txtdv.setText("");
        txtnombres.setText("");
        txtapellidos.setText("");
        txtfono.setText("");
        txtdireccion.setText("");
        txtemail.setText("");
    }
    private void btingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarActionPerformed
        String errores=validarVacios();
        if(errores.equals("")){
             try {
                    String rut=txtrut.getText();
                    String dv=txtdv.getText();
                    String union=rut+"-"+dv;
            //Cargar driver de conexión
                Class.forName("com.mysql.jdbc.Driver");
                //Crear conexión
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
                //Crear consulta
                Statement st = con.createStatement();
                String sql = "INSERT INTO clientes (RUT,Nombres,Apellidos,Contacto,Direccion,Correo)"
                        + "VALUES('" + union + "','" + txtnombres.getText() + "','" + txtapellidos.getText() + "',"
                        + "'" + txtfono.getText() + "','" + txtdireccion.getText() + "',"
                        + "'" + txtemail.getText() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                //Cerrar conexion
                con.close();
                    if (String.valueOf(txtrut.getText()).compareTo("") == 0
                    && String.valueOf(txtemail.getText()).compareTo("") == 0) {
                    validarVacios(); 
                    }else{
                    JOptionPane.showMessageDialog(this, "Cliente Ingresado");
                    mostrardatos("");
                    //Limpiar
                    limpiar(); 
                    anchocolumnas();
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error"+ e.getMessage().toString());
            }  
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }    
    }//GEN-LAST:event_btingresarActionPerformed
    
    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost
       
    }//GEN-LAST:event_txtemailFocusLost

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        if (Login.tipoUsuario==2) {
            btborrar.setVisible(false);
        }  
    }//GEN-LAST:event_formInternalFrameOpened

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        String error=validarRUTVacio();
        if(error.equals("")){
        // Buscar registro en la base de datos
        try {
            String rut=txtrut.getText();
            String dv=txtdv.getText();
            String union=rut+"-"+dv;
            //Cargar driver de conexión
            Class.forName("com.mysql.jdbc.Driver");
            //Crear conexión
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            //Crear consulta
            Statement st = con.createStatement();
            String sql = sql = "SELECT * FROM clientes WHERE RUT='" + union + "'";
            //Ejecutar la consulta
            ResultSet rs = st.executeQuery(sql);
            mostrardatos(sql);
            if (rs.next()) {
                //existe
                txtnombres.setText(rs.getObject("Nombres").toString());
                txtapellidos.setText(rs.getObject("Apellidos").toString());
                txtfono.setText(rs.getObject("Contacto").toString());
                txtdireccion.setText(rs.getObject("Direccion").toString());
                txtemail.setText(rs.getObject("Correo").toString());
                btborrar.setEnabled(true);
                btmodificar.setEnabled(true);
                txtrut.setEnabled(false);
            } else {
                //no existe
                if (String.valueOf(txtrut.getText()).compareTo("") == 0) {
                    validarVacios();
                }else {
                JOptionPane.showMessageDialog(this, "El cliente no existe");
                btborrar.setEnabled(false);
                btmodificar.setEnabled(false);
                txtrut.setEnabled(true);
                txtrut.requestFocus();
                limpiar();
            }
            }
            //Cerrar conexion
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
        anchocolumnas();
        }else{
            JOptionPane.showMessageDialog(null, error);
        }
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
         String errores=validarVacios();
        if(errores.equals("")){
        try {
            String rut=txtrut.getText();
            String dv=txtdv.getText();
            String union=rut+"-"+dv;
            if (String.valueOf(txtrut.getText()).compareTo("") == 0
                    && String.valueOf(txtemail.getText()).compareTo("") == 0) {
            validarVacios();
            }else{
            JOptionPane.showMessageDialog(this, "Clientes Actualizado");
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE clientes SET Nombres='" + txtnombres.getText() + "',Apellidos='" + txtapellidos.getText()
                    + "',Contacto='" + txtfono.getText() + "',Direccion='" + txtdireccion.getText()
                    + "',Correo='" + txtemail.getText()
                    + "' WHERE RUT='" + union + "'");
            
            pst.executeUpdate();
            //cerrar conexion
            con.close();
            btingresar.setEnabled(true);
            //limpiar textfields
            limpiar();
            mostrardatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
        }
            anchocolumnas();
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btmodificarActionPerformed

    private void btborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btborrarActionPerformed
        String error=validarRUTVacio();
        if(error.equals("")){
        try {
            String rut=txtrut.getText();
            String dv=txtdv.getText();
            String union=rut+"-"+dv;
            if (String.valueOf(txtrut.getText()).compareTo("") == 0
            && String.valueOf(txtemail.getText()).compareTo("") == 0) {
            validarVacios();
            }else{
            JOptionPane.showMessageDialog(this, "Cliente Eliminado");
            btmodificar.setEnabled(false);
            btborrar.setEnabled(false);
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("DELETE FROM clientes WHERE RUT='" + union + "'");
            pst.executeUpdate();
            limpiar();
            con.close();
            
            mostrardatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
        anchocolumnas();
        btingresar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, error);
        }     
    }//GEN-LAST:event_btborrarActionPerformed

    private void txtnombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombresKeyPressed

    private void txtnombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresKeyTyped
        validarLetras.soloLetras(evt);
        if (txtnombres.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtnombresKeyTyped

    private void txtapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyTyped
        validarLetras.soloLetras(evt);
        if (txtapellidos.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtapellidosKeyTyped

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
        validarLetras.mail(evt);
        if (txtemail.getText().length() == 40) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtemailKeyTyped

    private void txtfonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfonoKeyTyped
        validarLetras.soloNumeros(evt);
        if (txtfono.getText().length() == 15) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtfonoKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        validarLetras.LNE(evt);
        if (txtdireccion.getText().length() == 30) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtdireccionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btborrar;
    private javax.swing.JButton btbuscar;
    private javax.swing.JButton btingresar;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JButton btmodificar;
    private javax.swing.JButton btnuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel lblimg;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdv;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfono;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txtrut;
    // End of variables declaration//GEN-END:variables
}
