/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
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
    javax.swing.ImageIcon icono = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aprobado.png"));
    javax.swing.ImageIcon iconoNo = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cross.png"));

    public ClientesSistema() {
        initComponents();
        this.setLocation(280, 15);
        setTitle("Mantenedor de Clientes");
        mostrardatos("");
        anchocolumnas();
        bloquear();
    }

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        String rut = txtrut.getText();
        String dv = txtdv.getText();
        String union = rut + "-" + dv;
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
                modelo.addRow(datos);
            }
            tbclientes.setModel(modelo);
            //tbclientes.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
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

        String errores = "";

        if (txtrut.getText().equals("")) {
            errores += "Por favor digite el RUT \n";
        }
        if (txtdv.getText().equals("")) {
            errores += "Por favor genere el dígito verificador \n";
        }
        if (txtapellidos.getText().trim().isEmpty()) {
            errores += "El campo nombre está vacio \n";
        }
        if (txtnombres.getText().trim().isEmpty()) {
            errores += "Por favor escriba el nombre \n";
        }
        if (txtapellidos.getText().trim().isEmpty()) {
            errores += "Por favor escriba el apellido \n";
        }
        if (txtfono.getText().trim().isEmpty()) {
            errores += "Escriba el número de contacto \n";
        }
        if (txtdireccion.getText().trim().isEmpty()) {
            errores += "Por favor escriba la dirección \n";
        }
        if (txtemail.getText().trim().isEmpty()) {
            errores += "Por favor escriba el correo electrónico \n";
        }
        if (!ValidarCaracteres.emailCorrecto(txtemail.getText())) {
            errores += "El email no tiene el formato correcto\n";
        }
        return errores;
    }

    private String validarRUTVacio() {
        String errores = "";
        if (txtrut.getText().equals("")) {
            errores += "Por favor digite el RUT \n";
        }
        if (txtdv.getText().equals("")) {
            errores += "Por favor genere el dígito verificador \n";
        }
        return errores;
    }

    void desbloquear() {
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

    void bloquear() {
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
    private void corterut() {
        String rut = txtrut.getText().substring(0, 8);
        String dv = txtdv.getText().substring(9, 10);
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
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtrut = new javax.swing.JTextField();
        txtdv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        lblimg = new javax.swing.JLabel();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        btnuevo = new javax.swing.JButton();
        btbuscar = new javax.swing.JButton();
        btingresar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        btborrar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();

        setClosable(true);
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

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("-");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RUT");

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

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres");

        txtnombres.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos");

        txtapellidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidosKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fono");

        txtfono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfonoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dirección");

        txtdireccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("E-mail");

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

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtapellidos)
                        .addComponent(txtfono)
                        .addComponent(txtdireccion)
                        .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addComponent(txtnombres))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtdv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-icon.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
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

        btlimpiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btingresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btborrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
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
        tbclientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbclientes.getTableHeader().setResizingAllowed(false);
        tbclientes.getTableHeader().setReorderingAllowed(false);
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesMouseClicked(evt);
            }
        });
        jsp.setViewportView(tbclientes);

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        String rut       = txtrut.getText();
        long   convierto = 0;
        try {
            convierto = Long.parseLong(rut);
            if (convierto > 30000000) {
                JOptionPane.showMessageDialog(null, "Lo siento, no puedo validar este RUT");
                txtrut.setText("");
                txtdv.setText("");
                txtrut.requestFocus();
            }
            if (convierto < 2000000) {
                JOptionPane.showMessageDialog(null, "Lo siento, no puedo validar este RUT");
                txtrut.setText("");
                txtdv.setText("");
                txtrut.requestFocus();
            } else {
                String codigo;
                int multiplo = 2;
                int cont = 0;
                for (int x = 0; x < txtrut.getText().length(); x++) {
                    cont = cont + (Integer.parseInt(txtrut.getText().substring(txtrut.getText().length() - x - 1, txtrut.getText().length() - x)) * multiplo);
                    multiplo++;
                    if (multiplo == 8) {
                        multiplo = 2;
                    }

                }
                cont = 11 - (cont % 11);
                if (cont <= 9) {
                    codigo = "" + cont;
                } else if (cont == 11) {
                    codigo = "0";
                } else {
                    codigo = "K";
                }
                if (codigo != null) {
                    txtdv.setText(codigo);
                }
                if (txtrut.getText().length() >= 7) {
                    lblimg.setIcon(icono);
                } else {
                    lblimg.setIcon(iconoNo);
                    JOptionPane.showMessageDialog(null, "El RUT ingresado no contiene los caracteres necesarios para ser validado");
                    txtrut.setText("");
                    txtdv.setText("");
                }
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_txtrutFocusLost

    private void txtrutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutKeyTyped
        validarLetras.soloNumeros(evt);
        if (txtrut.getText().length() >= 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtrutKeyTyped
    void limpiar() {
        txtrut.setText("");
        txtdv.setText("");
        txtnombres.setText("");
        txtapellidos.setText("");
        txtfono.setText("");
        txtdireccion.setText("");
        txtemail.setText("");
    }
    private void btingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                String rut = txtrut.getText();
                String dv = txtdv.getText();
                String union = rut + "-" + dv;
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = "INSERT INTO clientes (RUT,Nombres,Apellidos,Contacto,Direccion,Correo)"
                        + "VALUES('" + union + "','" + txtnombres.getText() + "','" + txtapellidos.getText() + "',"
                        + "'" + txtfono.getText() + "','" + txtdireccion.getText() + "',"
                        + "'" + txtemail.getText() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                if (String.valueOf(txtrut.getText()).compareTo("") == 0
                        && String.valueOf(txtemail.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente Ingresado","Ingreso", JOptionPane.INFORMATION_MESSAGE);
                    txtrut.requestFocus();
                    mostrardatos("");
                    //Limpiar
                    limpiar();
                    anchocolumnas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,"El RUT del cliente ya existe","RUT existente", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btingresarActionPerformed

    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost

    }//GEN-LAST:event_txtemailFocusLost

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        if (Login.tipoUsuario == 2) {
            btborrar.setVisible(false);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        String error = validarRUTVacio();
        if (error.equals("")) {
            // Buscar registro en la base de datos
            try {
                String rut = txtrut.getText();
                String dv = txtdv.getText();
                String union = rut + "-" + dv;
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
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
                    } else {
                        JOptionPane.showMessageDialog(this, "El cliente no existe","Inexistente", JOptionPane.ERROR_MESSAGE);
                        btborrar.setEnabled(false);
                        btmodificar.setEnabled(false);
                        txtrut.setEnabled(true);
                        txtrut.requestFocus();
                        limpiar();
                        anchocolumnas();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }

        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                String rut = txtrut.getText();
                String dv = txtdv.getText();
                String union = rut + "-" + dv;
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("UPDATE clientes SET Nombres='" + txtnombres.getText() + "',Apellidos='" + txtapellidos.getText()
                        + "',Contacto='" + txtfono.getText() + "',Direccion='" + txtdireccion.getText()
                        + "',Correo='" + txtemail.getText()
                        + "' WHERE RUT='" + union + "'");

                pst.executeUpdate();
                if (String.valueOf(txtrut.getText()).compareTo("") == 0
                        && String.valueOf(txtemail.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Datos del cliente actualizados", "Datos Cliente ", JOptionPane.INFORMATION_MESSAGE);
                    btingresar.setEnabled(true);
                    //limpiar textfields
                    limpiar();
                    mostrardatos("");
                    anchocolumnas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,"El RUT del cliente ya existe","RUT existente", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btmodificarActionPerformed

    private void btborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btborrarActionPerformed
        String error = validarRUTVacio();
        if (error.equals("")) {
            try {
                String rut = txtrut.getText();
                String dv = txtdv.getText();
                String union = rut + "-" + dv;
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("DELETE FROM clientes WHERE RUT='" + union + "'");
                pst.executeUpdate();
                if (String.valueOf(txtrut.getText()).compareTo("") == 0
                        && String.valueOf(txtemail.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente Eliminado del sistema","Eliminación", JOptionPane.INFORMATION_MESSAGE);
                    btmodificar.setEnabled(false);
                    btborrar.setEnabled(false);
                    anchocolumnas();
                    btingresar.setEnabled(true);
                    limpiar();
                    mostrardatos("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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

    private void tbclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMouseClicked
       
    }//GEN-LAST:event_tbclientesMouseClicked

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

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
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel lblimg;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
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
