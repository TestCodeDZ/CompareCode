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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class Insumos extends javax.swing.JInternalFrame {
    ValidarCaracteres validarLetras = new ValidarCaracteres();
    /**
     * Creates new form MantenedorInsumos
     */
    public Insumos() {
        initComponents();
        setTitle("Mantenedor de Insumos");
        this.setLocation(280,15);
        codigoinsumo();
        mostrardatos("");
        anchocolumnas();
        bloquear();
        txtcod.setDisabledTextColor(Color.blue);
    }
    
    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código Producto");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Actual");
        tbprod.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM insumos";
        } else {
            sql = "SELECT * FROM insumos WHERE Codigo LIKE '%"+txtbusca.getText()+"%'";
        }

        String[] datos = new String[4];
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
            tbprod.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void anchocolumnas() {
        tbprod.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbprod.getColumnModel().getColumn(0).setWidth(90);
        tbprod.getColumnModel().getColumn(0).setMaxWidth(90);
        tbprod.getColumnModel().getColumn(0).setMinWidth(90);
        
        tbprod.getColumnModel().getColumn(1).setWidth(100);
        tbprod.getColumnModel().getColumn(1).setMaxWidth(100);
        tbprod.getColumnModel().getColumn(1).setMinWidth(100);

        tbprod.getColumnModel().getColumn(2).setWidth(100);
        tbprod.getColumnModel().getColumn(2).setMaxWidth(100);
        tbprod.getColumnModel().getColumn(2).setMinWidth(100);

        tbprod.getColumnModel().getColumn(3).setWidth(120);
        tbprod.getColumnModel().getColumn(3).setMaxWidth(120);
        tbprod.getColumnModel().getColumn(3).setMinWidth(120);
    }
    private String validarVacios() {
        
        String errores="";
        
        if(txtcod.getText().equals("")){
            errores+="Por favor genere el código \n";
        }
        if(txtdesc.getText().equals("")){
            errores+="Por favor digite la descripción \n";
        }
        if(txtprecio.getText().trim().isEmpty()){
            errores+="Ingrese el Precio \n";
        }
        if(txtstock.getText().trim().isEmpty()){
            errores+="Por favor ingrese el stock \n";
        }
        return errores;       
    }
    
    private String validarcodVacio() {
        String errores="";
        if(txtcod.getText().equals("")){
            errores+="El Código esta Vacío \n";
        }
        return errores;       
    }
    void bloquear(){
        txtprecio.setEnabled(false);
        txtstock.setEnabled(false);
        btmt.setEnabled(false);
        btinsertar.setEnabled(false);
        btactualizar.setEnabled(false);
        bteliminar.setEnabled(false);
        btlimpiar.setEnabled(false);
        txtdesc.setEnabled(false);
    }
    void desbloquear(){
        txtdesc.setEnabled(true);
        txtprecio.setEnabled(true);
        txtstock.setEnabled(true);
        btmt.setEnabled(true);
        btinsertar.setEnabled(true);
        btlimpiar.setEnabled(true);
    }
    void limpiar() {
        codigoinsumo();
        txtdesc.setText("");
        txtprecio.setText("");
        txtstock.setText("");
    }
    
    void codigoinsumo() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(Codigo) from insumos";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtcod.setText("CI0001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;

                j = Integer.parseInt(r);
                sgtmtr.GenerarCodigos gen = new sgtmtr.GenerarCodigos();
                gen.generar(j);
                txtcod.setText("CI" + gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Insumos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
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
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        btlimpiar = new javax.swing.JButton();
        bteliminar = new javax.swing.JButton();
        btactualizar = new javax.swing.JButton();
        btinsertar = new javax.swing.JButton();
        btnuevo = new javax.swing.JButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel1 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtdesc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        txtstock = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel5 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        btmt = new javax.swing.JButton();
        jsp = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        btlimpiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        bteliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        bteliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        bteliminar.setText("Eliminar");
        bteliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteliminarActionPerformed(evt);
            }
        });

        btactualizar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/application_form.png"))); // NOI18N
        btactualizar.setText("Modificar");
        btactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btactualizarActionPerformed(evt);
            }
        });

        btinsertar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btinsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept.png"))); // NOI18N
        btinsertar.setText("Insertar");
        btinsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertarActionPerformed(evt);
            }
        });

        btnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-icon.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btactualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btinsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bteliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btinsertar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bteliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Producto", 0, 0, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Código");

        txtcod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcod.setEnabled(false);
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodKeyTyped(evt);
            }
        });

        txtdesc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtdesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripción");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Precio");

        txtprecio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        txtstock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stock");

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        panelTranslucido3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda de Productos", 0, 0, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Buscar:");

        txtbusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        btmt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btmt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busquedadetodo.png"))); // NOI18N
        btmt.setText("Mostrar Todo");
        btmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmtActionPerformed(evt);
            }
        });

        //Deshabilitar edicion de tabla
        tbprod = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbprod.setSelectionBackground(Color.LIGHT_GRAY);
        tbprod.setSelectionForeground(Color.blue);
        tbprod.setModel(new javax.swing.table.DefaultTableModel(
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
        tbprod.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbprod.getTableHeader().setResizingAllowed(false);
        tbprod.getTableHeader().setReorderingAllowed(false);
        tbprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbprodMouseClicked(evt);
            }
        });
        jsp.setViewportView(tbprod);

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(47, 47, 47)
                .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btmt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btmt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmtActionPerformed
        mostrardatos("");
        txtbusca.setText("");
        anchocolumnas();
        btactualizar.setEnabled(false);
        bteliminar.setEnabled(false);
    }//GEN-LAST:event_btmtActionPerformed

    private void btinsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = "INSERT INTO insumos (Codigo,Descripcion,Precio,Stock)"
                        + "VALUES('" + txtcod.getText() + "','" + txtdesc.getText() + "','" + txtprecio.getText() + "',"
                        + "'" + txtstock.getText() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                if (String.valueOf(txtcod.getText()).compareTo("") == 0
                        && String.valueOf(txtdesc.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Insumo Ingresado", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                    txtdesc.requestFocus();
                    mostrardatos("");
                    //Limpiar
                    limpiar();
                    anchocolumnas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btinsertarActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        limpiar();
        txtdesc.requestFocus();
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        txtdesc.requestFocus();
        limpiar();
        desbloquear();
    }//GEN-LAST:event_btnuevoActionPerformed

    private void btactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btactualizarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("UPDATE insumos SET Descripcion='" + txtdesc.getText()
                        + "',Precio='" + txtprecio.getText() + "',Stock='" + txtstock.getText()
                        + "' WHERE Codigo='" + txtcod.getText() + "'");
                pst.executeUpdate();
                if (String.valueOf(txtcod.getText()).compareTo("") == 0
                        && String.valueOf(txtdesc.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Insumo Actualizado","Actualizado", JOptionPane.INFORMATION_MESSAGE);
                }
                btinsertar.setEnabled(true);
                //limpiar textfields
                limpiar();
                mostrardatos("");
                anchocolumnas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }

        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btactualizarActionPerformed

    private void bteliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteliminarActionPerformed
        String error = validarcodVacio();
        if (error.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("DELETE FROM insumos WHERE Codigo='" + txtcod.getText() + "'");
                pst.executeUpdate();
                if (String.valueOf(txtcod.getText()).compareTo("") == 0
                        && String.valueOf(txtdesc.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Insumo Eliminado del Sistema","Eliminación", JOptionPane.INFORMATION_MESSAGE);
                    btactualizar.setEnabled(false);
                    bteliminar.setEnabled(false);
                    limpiar();
                    mostrardatos("");
                    anchocolumnas();
                    btinsertar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "No se pudo borrar el desperfecto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }

        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bteliminarActionPerformed

    private void txtcodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtcod.getText().length() == 10) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtcodKeyTyped

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtprecio.getText().length() == 6) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtstock.getText().length() == 4) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtstockKeyTyped

    private void txtdescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescKeyTyped
        validarLetras.soloLetras(evt);
        //limite de caracteres
        if (txtdesc.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtdescKeyTyped

    private void tbprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodMouseClicked
        //al momento de hacer click derecho aparecerá el menu modificar
        //que se irá directamente con los valores de la BD a sus respectivos 
        //textfields para hacer las respectivas modificaciones
        int fila = tbprod.getSelectedRow();
        txtcod.setEnabled(false);
        btinsertar.setEnabled(false);

        if (fila >= 0) {
            txtcod.setText(tbprod.getValueAt(fila, 0).toString());
            txtdesc.setText(tbprod.getValueAt(fila, 1).toString());
            txtprecio.setText(tbprod.getValueAt(fila, 2).toString()); 
            txtstock.setText(tbprod.getValueAt(fila, 3).toString()); 
            txtdesc.setEnabled(true);
            txtprecio.setEnabled(true);
            txtstock.setEnabled(true);
            btactualizar.setEnabled(true);
            bteliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        }
    }//GEN-LAST:event_tbprodMouseClicked

    private void txtbuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyReleased
        mostrardatos(txtbusca.getText());
        anchocolumnas();
        btactualizar.setEnabled(true);
        bteliminar.setEnabled(true);
    }//GEN-LAST:event_txtbuscaKeyReleased

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtbusca.getText().length() == 6) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtbuscaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btactualizar;
    private javax.swing.JButton bteliminar;
    private javax.swing.JButton btinsertar;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JButton btmt;
    private javax.swing.JButton btnuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jsp;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
    private javax.swing.JTable tbprod;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
