/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sgtmtr.Principal.jdpescritorio;

/**
 *
 * @author ZuluCorp
 */
public class vehiculos extends javax.swing.JInternalFrame {

    ValidarCaracteres validarLetras = new ValidarCaracteres();
    bmarcas bm = new bmarcas(); //crear el nuevo formulario
    bcliente bc = new bcliente(); //crear el nuevo formulario

    /**
     * Creates new form vehiculos
     */
    public vehiculos() {
        initComponents();
        setTitle("Mantenedor de Vehículos");
        this.setLocation(280, 15);
        txtnm.setEnabled(false);
        txtrutdueño.setEnabled(false);
        mostrardatos("");
        anchocolumnas();
        bloquear();
    }

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Patente");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Año");
        modelo.addColumn("Color");
        modelo.addColumn("RUT del Dueño");
        tbvehiculos.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM vehiculo";
        } else {
            sql = "SELECT * FROM vehiculo WHERE Patente='" + txtpatente.getText() + "'";
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
            tbvehiculos.setModel(modelo);
            //tbvehiculos.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void anchocolumnas() {
        tbvehiculos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbvehiculos.getColumnModel().getColumn(0).setWidth(100);
        tbvehiculos.getColumnModel().getColumn(0).setMaxWidth(100);
        tbvehiculos.getColumnModel().getColumn(0).setMinWidth(100);

        tbvehiculos.getColumnModel().getColumn(1).setWidth(100);
        tbvehiculos.getColumnModel().getColumn(1).setMaxWidth(100);
        tbvehiculos.getColumnModel().getColumn(1).setMinWidth(100);

        tbvehiculos.getColumnModel().getColumn(2).setWidth(100);
        tbvehiculos.getColumnModel().getColumn(2).setMaxWidth(100);
        tbvehiculos.getColumnModel().getColumn(2).setMinWidth(100);

        tbvehiculos.getColumnModel().getColumn(3).setWidth(120);
        tbvehiculos.getColumnModel().getColumn(3).setMaxWidth(120);
        tbvehiculos.getColumnModel().getColumn(3).setMinWidth(120);

        tbvehiculos.getColumnModel().getColumn(4).setWidth(90);
        tbvehiculos.getColumnModel().getColumn(4).setMaxWidth(90);
        tbvehiculos.getColumnModel().getColumn(4).setMinWidth(90);

        tbvehiculos.getColumnModel().getColumn(5).setWidth(200);
        tbvehiculos.getColumnModel().getColumn(5).setMaxWidth(200);
        tbvehiculos.getColumnModel().getColumn(5).setMinWidth(200);
    }

    private String validarVacios() {

        String errores = "";

        if (txtpatente.getText().equals("")) {
            errores += "Por favor digite la patente \n";
        }
        if (txtnm.getText().equals("")) {
            errores += "Por favor seleccione la marca \n";
        }
        if (txtmodelo.getText().trim().isEmpty()) {
            errores += "El campo modelo está vacio \n";
        }
        if (txtaño.getText().trim().isEmpty()) {
            errores += "Por favor escriba el año del vehìculo \n";
        }
        if (txtcolor.getText().trim().isEmpty()) {
            errores += "Por favor escriba el color \n";
        }
        if (txtrutdueño.getText().trim().isEmpty()) {
            errores += "Por favor seleccione el RUT del dueño \n";
        }
        return errores;
    }

    private String validarPatenteVacia() {
        String errores = "";
        if (txtpatente.getText().equals("")) {
            errores += "Por favor digite la patente \n";
        }
        return errores;
    }

    void desbloquear() {
        txtpatente.setEnabled(true);
        txtmodelo.setEnabled(true);
        txtaño.setEnabled(true);
        txtcolor.setEnabled(true);
        btbuscar.setEnabled(true);
        btingresar.setEnabled(true);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(true);
        txtpatente.requestFocus();
        btbusca1.setEnabled(true);
        btbusca2.setEnabled(true);
    }

    void bloquear() {
        txtpatente.setEnabled(false);
        txtmodelo.setEnabled(false);
        txtaño.setEnabled(false);
        txtcolor.setEnabled(false);
        btbuscar.setEnabled(false);
        btingresar.setEnabled(false);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(false);
        btbusca1.setEnabled(false);
        btbusca2.setEnabled(false);
    }

    void limpiar() {
        txtpatente.setText("");
        txtnm.setText("");
        txtmodelo.setText("");
        txtaño.setText("");
        txtcolor.setText("");
        txtrutdueño.setText("");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtpatente = new javax.swing.JTextField();
        txtnm = new javax.swing.JTextField();
        btbusca1 = new javax.swing.JButton();
        txtmodelo = new javax.swing.JTextField();
        txtaño = new javax.swing.JTextField();
        txtcolor = new javax.swing.JTextField();
        txtrutdueño = new javax.swing.JTextField();
        btbusca2 = new javax.swing.JButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        btnuevo = new javax.swing.JButton();
        btbuscar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        btborrar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        btingresar = new javax.swing.JButton();
        panelTranslucido4 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbvehiculos = new javax.swing.JTable();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del vehículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N
        panelTranslucido1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Modelo");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Color");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Marca");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Año");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dueño");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patente");

        txtpatente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpatente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpatenteKeyTyped(evt);
            }
        });

        txtnm.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btbusca1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbusca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btbusca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbusca1ActionPerformed(evt);
            }
        });

        txtmodelo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmodelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmodeloKeyTyped(evt);
            }
        });

        txtaño.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtañoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtañoKeyTyped(evt);
            }
        });

        txtcolor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcolor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcolorKeyTyped(evt);
            }
        });

        txtrutdueño.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btbusca2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbusca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btbusca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbusca2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmodelo)
                        .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelTranslucido1Layout.createSequentialGroup()
                            .addComponent(txtrutdueño, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btbusca2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(txtnm, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtrutdueño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(btbusca2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        btbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busquedadetodo.png"))); // NOI18N
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

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btlimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btborrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btborrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btborrar.setText("Borrar");
        btborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btborrarActionPerformed(evt);
            }
        });

        btmodificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/update.png"))); // NOI18N
        btmodificar.setText("Modificar");
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
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

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btborrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btingresar)
                    .addComponent(btmodificar)
                    .addComponent(btborrar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelTranslucido4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación Tabla Vehículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.white)); // NOI18N

        //Deshabilitar edicion de tabla
        tbvehiculos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbvehiculos.setSelectionBackground(Color.LIGHT_GRAY);
        tbvehiculos.setSelectionForeground(Color.blue);
        tbvehiculos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbvehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbvehiculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbvehiculos.getTableHeader().setResizingAllowed(false);
        tbvehiculos.getTableHeader().setReorderingAllowed(false);
        tbvehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbvehiculosMouseClicked(evt);
            }
        });
        jsp.setViewportView(tbvehiculos);

        javax.swing.GroupLayout panelTranslucido4Layout = new javax.swing.GroupLayout(panelTranslucido4);
        panelTranslucido4.setLayout(panelTranslucido4Layout);
        panelTranslucido4Layout.setHorizontalGroup(
            panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelTranslucido4Layout.setVerticalGroup(
            panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jsp.getAccessibleContext().setAccessibleParent(panelTranslucido4);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelTranslucido4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        desbloquear();
        limpiar();

        txtpatente.requestFocus();
    }//GEN-LAST:event_btnuevoActionPerformed

    private void btbusca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbusca1ActionPerformed
        if (!bm.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (bm.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Búsqueda Marcas: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Búsqueda Marcas: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(bm);
            }
            bm.show();
            bm.toFront();
        } else {
            System.out.println("Búsqueda Marcas: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btbusca1ActionPerformed

    private void txtañoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtañoKeyPressed

    }//GEN-LAST:event_txtañoKeyPressed

    private void txtañoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtañoKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtaño.getText().length() == 4) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtañoKeyTyped

    private void btbusca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbusca2ActionPerformed
        if (!bc.isVisible()) {

            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (bc.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Búsqueda Dueño: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Búsqueda Dueño: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(bc);
            }
            bc.show();
            bc.toFront();
        } else {
            System.out.println("Búsqueda Dueño: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }

    }//GEN-LAST:event_btbusca2ActionPerformed

    private void btingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = "INSERT INTO vehiculo (Patente,Marca,Modelo,Año,Color,Dueño)"
                        + "VALUES('" + txtpatente.getText() + "','" + txtnm.getText() + "','" + txtmodelo.getText() + "',"
                        + "'" + txtaño.getText() + "','" + txtcolor.getText() + "',"
                        + "'" + txtrutdueño.getText() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                if (String.valueOf(txtpatente.getText()).compareTo("") == 0
                        && String.valueOf(txtrutdueño.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Vehìculo Ingresado", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                    txtpatente.requestFocus();
                    mostrardatos("");
                    //Limpiar
                    limpiar();
                    anchocolumnas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "La patente ya existe", "Patente existente", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btingresarActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        limpiar();
        mostrardatos("");
        txtpatente.setEnabled(true);
        txtpatente.requestFocus();
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("UPDATE vehiculo SET Marca='" + txtnm.getText() + "',Modelo='" + txtmodelo.getText()
                        + "',Año='" + txtaño.getText() + "',Color='" + txtcolor.getText()
                        + "',Dueño='" + txtrutdueño.getText()
                        + "' WHERE Patente='" + txtpatente.getText() + "'");

                pst.executeUpdate();
                if (String.valueOf(txtpatente.getText()).compareTo("") == 0
                        && String.valueOf(txtrutdueño.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Datos del Vehículo Actualizados", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                    btingresar.setEnabled(true);
                    //limpiar textfields
                    limpiar();
                    mostrardatos("");
                    anchocolumnas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "La patente ya existe", "Patente existente", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btmodificarActionPerformed

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        String error = validarPatenteVacia();
        if (error.equals("")) {
            // Buscar registro en la base de datos
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                //Crear consulta
                Statement st = conexion.createStatement();
                String sql = sql = "SELECT * FROM vehiculo WHERE Patente='" + txtpatente.getText() + "'";
                //Ejecutar la consulta
                ResultSet rs = st.executeQuery(sql);
                mostrardatos(sql);
                if (rs.next()) {
                    //existe
                    txtnm.setText(rs.getObject("Marca").toString());
                    txtmodelo.setText(rs.getObject("Modelo").toString());
                    txtaño.setText(rs.getObject("Año").toString());
                    txtcolor.setText(rs.getObject("Color").toString());
                    txtrutdueño.setText(rs.getObject("Dueño").toString());
                    txtpatente.setEnabled(false);
                    btborrar.setEnabled(true);
                    btmodificar.setEnabled(true);
                } else {
                    //no existe
                    if (String.valueOf(txtpatente.getText()).compareTo("") == 0) {
                        validarVacios();
                    } else {
                        JOptionPane.showMessageDialog(this, "El vehículo que busca no existe", "Vehículo Inexistente", JOptionPane.ERROR_MESSAGE);
                        txtpatente.setEnabled(true);
                        btborrar.setEnabled(false);
                        btmodificar.setEnabled(false);
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
            JOptionPane.showMessageDialog(null, error);
        }
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btborrarActionPerformed
        String error = validarPatenteVacia();
        if (error.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = (PreparedStatement) conexion.prepareStatement("DELETE FROM vehiculo WHERE Patente='" + txtpatente.getText() + "'");
                pst.executeUpdate();
                if (String.valueOf(txtpatente.getText()).compareTo("") == 0
                        && String.valueOf(txtrutdueño.getText()).compareTo("") == 0) {
                    validarVacios();
                } else {
                    JOptionPane.showMessageDialog(this, "Vehículo Eliminado", "Eliminación vehículo", JOptionPane.INFORMATION_MESSAGE);
                    btmodificar.setEnabled(false);
                    btborrar.setEnabled(false);
                    limpiar();
                    mostrardatos("");
                    anchocolumnas();
                    btingresar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, error);
        }
    }//GEN-LAST:event_btborrarActionPerformed

    private void txtmodeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmodeloKeyTyped
        validarLetras.LNE(evt);
        if (txtmodelo.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtmodeloKeyTyped

    private void txtcolorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolorKeyTyped
        validarLetras.soloLetras(evt);
        //limite de caracteres
        if (txtcolor.getText().length() == 12) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtcolorKeyTyped

    private void txtpatenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpatenteKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtpatente.getText().length() == 6) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtpatenteKeyTyped

    private void tbvehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbvehiculosMouseClicked
        //al momento de hacer click derecho aparecerá el menu modificar
        //que se irá directamente con los valores de la BD a sus respectivos 
        //textfields para hacer las respectivas modificaciones
        int fila = tbvehiculos.getSelectedRow();
        btingresar.setEnabled(false);

        if (fila >= 0) {
            txtpatente.setText(tbvehiculos.getValueAt(fila, 0).toString());
            txtnm.setText(tbvehiculos.getValueAt(fila, 1).toString());
            txtmodelo.setText(tbvehiculos.getValueAt(fila, 2).toString());
            txtaño.setText(tbvehiculos.getValueAt(fila, 3).toString());
            txtcolor.setText(tbvehiculos.getValueAt(fila, 4).toString());
            txtrutdueño.setText(tbvehiculos.getValueAt(fila, 5).toString());

            btmodificar.setEnabled(true);
            btborrar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        }
    }//GEN-LAST:event_tbvehiculosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btborrar;
    private javax.swing.JButton btbusca1;
    private javax.swing.JButton btbusca2;
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
    private javax.swing.JScrollPane jsp;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido4;
    public static javax.swing.JTable tbvehiculos;
    private javax.swing.JTextField txtaño;
    private javax.swing.JTextField txtcolor;
    private javax.swing.JTextField txtmodelo;
    public static javax.swing.JTextField txtnm;
    private javax.swing.JTextField txtpatente;
    public static javax.swing.JTextField txtrutdueño;
    // End of variables declaration//GEN-END:variables
}
