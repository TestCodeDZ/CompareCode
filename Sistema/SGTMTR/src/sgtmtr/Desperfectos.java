/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class Desperfectos extends javax.swing.JInternalFrame {

    Connection connection = null;
    ResultSet rs = null;
    Statement s = null;
    ValidarCaracteres validarLetras = new ValidarCaracteres();

    /**
     * Creates new form MarcaVehiculo
     */
    public Desperfectos() {
        initComponents();
        this.setTitle("Mantenedor de Desperfectos");
        this.setLocation(280, 15);
        mostrardatos("");
        anchocolumnas();
        bloquear();
        codigodesp();
        txtiddesp.setDisabledTextColor(Color.blue);
    }

    void mostrardatos(String valor) {
        String id = txtiddesp.getText();
        String NM = txtnd.getText();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Descripción del Desperfecto");
        modelo.addColumn("Costo");
        tbdesp.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM desperfectos";
        } else {
            sql = "SELECT * FROM desperfectos WHERE ID='" + id + "'";
        }
        String[] datos = new String[3];
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }
            tbdesp.setModel(modelo);
            //tbdesp.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void anchocolumnas() {
        tbdesp.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbdesp.getColumnModel().getColumn(0).setWidth(100);
        tbdesp.getColumnModel().getColumn(0).setMaxWidth(100);
        tbdesp.getColumnModel().getColumn(0).setMinWidth(100);

        tbdesp.getColumnModel().getColumn(1).setWidth(250);
        tbdesp.getColumnModel().getColumn(1).setMaxWidth(250);
        tbdesp.getColumnModel().getColumn(1).setMinWidth(250);

        tbdesp.getColumnModel().getColumn(2).setWidth(130);
        tbdesp.getColumnModel().getColumn(2).setMaxWidth(130);
        tbdesp.getColumnModel().getColumn(2).setMinWidth(130);
    }

    void bloquear() {
        txtiddesp.setEnabled(false);
        txtnd.setEnabled(false);
        txtcd.setEnabled(false);
        btbuscar.setEnabled(false);
        btingresar.setEnabled(false);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(false);
    }

    void limpiar() {
        codigodesp();
        txtnd.setText("");
        txtcd.setText("");
    }

    void desbloquear() {
        txtnd.setEnabled(true);
        txtcd.setEnabled(true);
        btbuscar.setEnabled(true);
        btingresar.setEnabled(true);
        btlimpiar.setEnabled(true);
    }

    void codigodesp() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(ID) from desperfectos";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtiddesp.setText("CD0001");
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
                txtiddesp.setText("CD" + gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosSistema.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtiddesp = new javax.swing.JTextField();
        txtnd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbdesp = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btingresar = new javax.swing.JButton();
        btborrar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnuevo = new javax.swing.JButton();
        btbuscar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mantenedor de Desperfectos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");

        txtiddesp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtiddesp.setEnabled(false);
        txtiddesp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtiddespKeyTyped(evt);
            }
        });

        txtnd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtndKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Costo");

        txtcd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcdKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnd)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtiddesp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtiddesp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación Tabla Desperfectos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbdesp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbdesp = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbdesp.setSelectionBackground(Color.LIGHT_GRAY);
        tbdesp.setSelectionForeground(Color.blue);
        tbdesp.setModel(new javax.swing.table.DefaultTableModel(
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
        tbdesp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdesp.getTableHeader().setResizingAllowed(false);
        tbdesp.getTableHeader().setReorderingAllowed(false);
        tbdesp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdespMouseClicked(evt);
            }
        });
        tbdesp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbdespKeyPressed(evt);
            }
        });
        jsp.setViewportView(tbdesp);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btingresar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept.png"))); // NOI18N
        btingresar.setText("Ingresar");
        btingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btingresarActionPerformed(evt);
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

        btmodificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/page_edit.png"))); // NOI18N
        btmodificar.setText("Modificar");
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btborrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btingresar)
                    .addComponent(btborrar)
                    .addComponent(btmodificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-icon.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        btbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/92125_find_user-512(2).png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btlimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String validarVacios() {

        String errores = "";

        if (txtiddesp.getText().equals("")) {
            errores += "Por favor genere el código del desperfecto \n";
        }
        if (txtnd.getText().equals("")) {
            errores += "Por favor ingrese el nombre del desperfecto \n";
        }
        if (txtcd.getText().equals("")) {
            errores += "Por favor ingrese el costo \n";
        }
        return errores;
    }

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        desbloquear();
        codigodesp();
        txtiddesp.setEnabled(false);
        btborrar.setEnabled(false);
        txtnd.requestFocus();
        btmodificar.setEnabled(false);
        txtnd.setText("");
        txtcd.setText("");
        txtnd.requestFocus();
    }//GEN-LAST:event_btnuevoActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        limpiar();
        mostrardatos("");
        anchocolumnas();
        bloquear();
        btbuscar.setEnabled(true);
        txtiddesp.setEnabled(true);
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void btingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            // Insertar registro en la base de datos
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = "INSERT INTO desperfectos (ID,descripcion,costo)"
                        + "VALUES('" + txtiddesp.getText() + "','" + txtnd.getText() + "','" + txtcd.getText() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                if (String.valueOf(txtnd.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Desperfecto Ingresado","Ingresado", JOptionPane.INFORMATION_MESSAGE);
                    txtnd.requestFocus();
                    mostrardatos("");
                    anchocolumnas();
                    //Limpiar
                    limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,"El desperfecto ya existe","Desperfecto existente", JOptionPane.ERROR_MESSAGE /*+ e.getMessage().toString()*/);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btingresarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            String ID = txtiddesp.getText();
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("UPDATE desperfectos SET Descripcion='" + txtnd.getText() + "',Costo='" + txtcd.getText()
                        + "' WHERE ID='" + txtiddesp.getText() + "'");
                pst.executeUpdate();
                if (String.valueOf(txtiddesp.getText()).compareTo("") == 0) {

                } else {
                    JOptionPane.showMessageDialog(this, "Desperfecto Actualizado","Modificado", JOptionPane.INFORMATION_MESSAGE);
                    //limpiar textfields
                    limpiar();
                    mostrardatos("");
                    anchocolumnas();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,"El desperfecto ya existe","Desperfecto existente", JOptionPane.ERROR_MESSAGE/* + e.getMessage().toString()*/);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btmodificarActionPerformed
    private String validartxtiddesp() {
        String error = "";
        if (txtiddesp.getText().equals("")) {
            error += "Por favor ingrese el ID del desperfecto a buscar o eliminar\n";
        }
        return error;
    }

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        String error = validartxtiddesp();
        if (error.equals("")) {
            // Buscar registro en la base de datos
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = "SELECT * FROM desperfectos WHERE ID='" + txtiddesp.getText() + "'";
                //Ejecutar la consulta
                ResultSet rs = st.executeQuery(sql);
                mostrardatos(txtiddesp.getText());

                if (rs.next()) {
                    //existe
                    txtiddesp.setText(rs.getObject("ID").toString());
                    txtnd.setText(rs.getObject("Descripcion").toString());
                    txtcd.setText(rs.getObject("Costo").toString());
                    txtnd.setEnabled(true);
                    txtcd.setEnabled(true);
                    btborrar.setEnabled(true);
                    btmodificar.setEnabled(true);
                } else {
                    //no existe
                    if (String.valueOf(txtiddesp.getText()).compareTo("") == 0) {
                        validartxtiddesp();
                    } else {
                        JOptionPane.showMessageDialog(this, "El desperfecto no existe","Datos Inexistentes", JOptionPane.ERROR_MESSAGE);
                        mostrardatos("");
                        btborrar.setEnabled(false);
                        btmodificar.setEnabled(false);
                        limpiar();
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
            anchocolumnas();
        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btborrarActionPerformed
        String error = validartxtiddesp();
        if (error.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                String ID = txtiddesp.getText();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("DELETE FROM desperfectos WHERE ID='" + ID + "'");
                pst.executeUpdate();
                if (String.valueOf(txtiddesp.getText()).compareTo("") == 0) {

                } else {
                    JOptionPane.showMessageDialog(this, "Desperdecto Eliminado","Eliminado", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    mostrardatos("");
                    bloquear();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "No se pudo borrar el desperfecto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE/*+ e.getMessage().toString()*/);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
            anchocolumnas();
            btingresar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btborrarActionPerformed

    private void txtcdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcdKeyPressed

    }//GEN-LAST:event_txtcdKeyPressed

    private void txtcdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcdKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtcd.getText().length() == 7) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtcdKeyTyped

    private void txtiddespKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtiddespKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtiddesp.getText().length() == 4) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtiddespKeyTyped

    private void txtndKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtndKeyTyped
        validarLetras.soloLetras(evt);
        //limite de caracteres
        if (txtnd.getText().length() == 30) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtndKeyTyped

    private void tbdespKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbdespKeyPressed
        
    }//GEN-LAST:event_tbdespKeyPressed

    private void tbdespMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdespMouseClicked
        //al momento de hacer click derecho aparecerá el menu modificar
        //que se irá directamente con los valores de la BD a sus respectivos 
        //textfields para hacer las respectivas modificaciones
        int fila = tbdesp.getSelectedRow();
        btingresar.setEnabled(false);

        if (fila >= 0) {
            txtiddesp.setText(tbdesp.getValueAt(fila, 0).toString());
            txtnd.setText(tbdesp.getValueAt(fila, 1).toString());
            txtcd.setText(tbdesp.getValueAt(fila, 2).toString());
            txtnd.setEnabled(true);
            txtcd.setEnabled(true);
            btmodificar.setEnabled(true);
            btborrar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        }
    }//GEN-LAST:event_tbdespMouseClicked


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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbdesp;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtiddesp;
    private javax.swing.JTextField txtnd;
    // End of variables declaration//GEN-END:variables
}
