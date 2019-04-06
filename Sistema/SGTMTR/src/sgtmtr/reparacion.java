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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        CargarComboER();
        txtconteorep.setVisible(false);
        txtconteototal.setVisible(false);
        cbcambioestauto.setVisible(false);
        btcev.setVisible(false);
        CargarConteo();
        CargarComboERV();
        Username = "tallertechorojo@gmail.com";
        PassWord = new String("techrojo");
        txtnom.setVisible(false);
        txtmail.setVisible(false);
        txtpatente.setVisible(false);
    }
    public static String Username = "";
    public static String PassWord = "";
    String Mensage = "";
    String To = "";
    String Subject = "";
    
     public void SendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Se ha enviado un e-mail al cliente");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
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
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Apellidos Cliente");
        modelo.addColumn("E-mail Cliente");
        modelo.addColumn("Mecánico");
        modelo.addColumn("Ingreso D.");
        modelo.addColumn("Entrega D.");
        modelo.addColumn("Repuestos");
        modelo.addColumn("Estado de la Reparación");
        tbdiag.setModel(modelo);
        String sql = "";
        sql = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT AND Estado_Diag = 'Aceptado' AND Estado_Diag <> 'Finalizado' AND Mecanico='" + Login.Nombres +" "+ Login.Apellidos  + "'";
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
        sql1 = "SELECT ID_Diag,Cod_Desp,Desc_Desperfecto,Cantidad,PrecioDesp,Ptotal,Estado FROM detallediag INNER JOIN controldiag ON detallediag.ID_Diag = controldiag.ID_Diagnostico "
                + "WHERE ID_Diag =" + iddetalle; //Estado <> 'Reparado' AND
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
            txtconteototal.setText("" + tbrep.getRowCount());
        } catch (SQLException ex) {
            //System.out.println(ex);
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
            String er = tbrep.getValueAt(filaseleccionada, 6).toString();
            txtnumdiag.setText(nd);
            txtcd.setText(cd);
            cbestadorep.setSelectedItem(er);
            if (er.equals("Reparado")) {
                cbestadorep.setEnabled(false);
                btnrep.setEnabled(false);
            } else {
                cbestadorep.setEnabled(true);
                btnrep.setEnabled(true);
            }
        }

    }

    private void mostrarnumdiag() {
        int filaseleccionada = tbdiag.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            String nd = tbdiag.getValueAt(filaseleccionada, 0).toString();
            String patente = tbdiag.getValueAt(filaseleccionada, 1).toString();
            String nombre = tbdiag.getValueAt(filaseleccionada, 3).toString();
            String apellido = tbdiag.getValueAt(filaseleccionada, 4).toString();
            String correo = tbdiag.getValueAt(filaseleccionada, 5).toString();
            txtnum.setText(nd);
            txtnom.setText(nombre +" "+apellido);
            txtmail.setText(correo);
            txtpatente.setText(patente);
        }
    }

    private void CargarComboER() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st = conexion.createStatement();
            String sql = "SELECT EstadoReparacion FROM estadoreparacion";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbestadorep.setModel(new DefaultComboBoxModel());
            cbestadorep.addItem("Seleccione Estado de la Reparación");
            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cbestadorep.addItem(rs.getObject(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    private void CargarComboERV() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st = conexion.createStatement();
            String sql = "SELECT Estado FROM estadodiag WHERE Estado <> 'Ingresado' AND Estado <> 'Rechazado'";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbcambioestauto.setModel(new DefaultComboBoxModel());
            // cbcambioestauto.addItem("Seleccione Estado de la Reparación");
            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cbcambioestauto.addItem(rs.getObject(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    private void CargarConteo() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st1 = conexion.createStatement();
            String sql1 = "SELECT count(*) as 'total' FROM controldiag, detallediag WHERE controldiag.ID_Diagnostico=detallediag.ID_Diag AND ID_Diag='" + txtnum.getText() + "' AND Estado='Reparado'";
            //Ejecutar consulta
            ResultSet rs1 = st1.executeQuery(sql1);
            //Recorremos los registros traidos
            // Creamos el Statement
            int nRegistros;
            if (rs1.next()) {
                nRegistros = Integer.parseInt(rs1.getString("total"));
                String convierte = Integer.toString(nRegistros);
                txtconteorep.setText(convierte);
            } else {
                nRegistros = 0;
            }
            //return count;
            /*if (count >= 0) {
                
             } else {
             txtconteorep.setText("" + count);
             }*/

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    private void IgualarNumeros() {
        String conteoRep = txtconteorep.getText();
        String total = txtconteototal.getText();
        if (total.equals(conteoRep)) {
            cbcambioestauto.setVisible(true);
            btcev.setVisible(true);
        } else {
            cbcambioestauto.setVisible(false);
            btcev.setVisible(false);
        }
    }

    void anchocolumnas() {
        tbdiag.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbdiag.getColumnModel().getColumn(0).setWidth(135);
        tbdiag.getColumnModel().getColumn(0).setMaxWidth(135);
        tbdiag.getColumnModel().getColumn(0).setMinWidth(135);

//        tbdiag.getColumnModel().getColumn(1).setWidth(180);
//        tbdiag.getColumnModel().getColumn(1).setMaxWidth(180);
//        tbdiag.getColumnModel().getColumn(1).setMinWidth(180);

        tbdiag.getColumnModel().getColumn(2).setWidth(100);
        tbdiag.getColumnModel().getColumn(2).setMaxWidth(100);
        tbdiag.getColumnModel().getColumn(2).setMinWidth(100);

        tbdiag.getColumnModel().getColumn(3).setWidth(135);
        tbdiag.getColumnModel().getColumn(3).setMaxWidth(135);
        tbdiag.getColumnModel().getColumn(3).setMinWidth(135);

        tbdiag.getColumnModel().getColumn(4).setWidth(135);
        tbdiag.getColumnModel().getColumn(4).setMaxWidth(135);
        tbdiag.getColumnModel().getColumn(4).setMinWidth(135);

        tbdiag.getColumnModel().getColumn(5).setWidth(200);
        tbdiag.getColumnModel().getColumn(5).setMaxWidth(200);
        tbdiag.getColumnModel().getColumn(5).setMinWidth(200);

        tbdiag.getColumnModel().getColumn(6).setWidth(150);
        tbdiag.getColumnModel().getColumn(6).setMaxWidth(150);
        tbdiag.getColumnModel().getColumn(6).setMinWidth(150);
        
//        tbdiag.getColumnModel().getColumn(7).setWidth(135);
//        tbdiag.getColumnModel().getColumn(7).setMaxWidth(135);
//        tbdiag.getColumnModel().getColumn(7).setMinWidth(135);
//        
//        tbdiag.getColumnModel().getColumn(8).setWidth(135);
//        tbdiag.getColumnModel().getColumn(8).setMaxWidth(135);
//        tbdiag.getColumnModel().getColumn(8).setMinWidth(135);
        
        tbdiag.getColumnModel().getColumn(9).setWidth(250);
        tbdiag.getColumnModel().getColumn(9).setMaxWidth(250);
        tbdiag.getColumnModel().getColumn(9).setMinWidth(250);
        
        tbdiag.getColumnModel().getColumn(10).setWidth(150);
        tbdiag.getColumnModel().getColumn(10).setMaxWidth(150);
        tbdiag.getColumnModel().getColumn(10).setMinWidth(150);

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
        Integer indice = cbestadorep.getSelectedIndex();
        if (indice.equals(0)) {
            errores += "Seleccione Estado de Reparación \n";
        }
        return errores;
    }
    
    private void LimpiaTabla(){
       DefaultTableModel modelo = new DefaultTableModel();
       tbrep.setModel(new DefaultTableModel());
       for (int i = 0; i < tbrep.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
   } 
    
    private String validaestrepauto() {
        String error = "";
        Integer i2 = cbcambioestauto.getSelectedIndex();
        if (i2.equals(0)) {
            error += "Seleccione Estado de Reparación General del Vehículo \n";
        }
        if (txtnom.getText().equals("")) {
           error += "Debe seleccionar algún dato de la tabla para obtener el nombre\n"; 
        }
        if (txtmail.getText().equals("")) {
             error += "Debe seleccionar algún dato de la tabla para obtener el E-mail\n"; 
        }
        if (txtpatente.getText().equals("")) {
             error += "Debe seleccionar algún dato de la tabla para obtener la patente\n"; 
        }  
        return error;
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
        jsp1 = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();
        btnimprimir = new javax.swing.JButton();
        txtnum = new javax.swing.JTextField();
        cbcambioestauto = new javax.swing.JComboBox();
        btcev = new javax.swing.JButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        txtnumdiag = new javax.swing.JTextField();
        txtcd = new javax.swing.JTextField();
        jsp2 = new javax.swing.JScrollPane();
        tbrep = new javax.swing.JTable();
        btnrep = new javax.swing.JButton();
        cbestadorep = new javax.swing.JComboBox();
        txtconteorep = new javax.swing.JTextField();
        txtconteototal = new javax.swing.JTextField();
        txtnom = new javax.swing.JTextField();
        txtmail = new javax.swing.JTextField();
        txtpatente = new javax.swing.JTextField();

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

        btnimprimir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconopdf.gif"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        txtnum.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnum.setEnabled(false);
        txtnum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnumFocusLost(evt);
            }
        });

        cbcambioestauto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbcambioestauto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btcev.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btcev.setText("Cambio Estado del Auto");
        btcev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnimprimir)
                .addGap(32, 32, 32)
                .addComponent(cbcambioestauto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btcev)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnum)
                    .addComponent(btnimprimir)
                    .addComponent(cbcambioestauto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcev))
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
                "Núm. Diagnóstico", "Código del Desperfecto", "Descripción Desperfecto", "Cantidad", "Precio Unitario", "Precio Total", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbrep.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbrep.getTableHeader().setResizingAllowed(false);
        tbrep.getTableHeader().setReorderingAllowed(false);
        tbrep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrepMouseClicked(evt);
            }
        });
        jsp2.setViewportView(tbrep);
        if (tbrep.getColumnModel().getColumnCount() > 0) {
            tbrep.getColumnModel().getColumn(0).setResizable(false);
            tbrep.getColumnModel().getColumn(1).setResizable(false);
            tbrep.getColumnModel().getColumn(2).setResizable(false);
            tbrep.getColumnModel().getColumn(3).setResizable(false);
            tbrep.getColumnModel().getColumn(4).setResizable(false);
            tbrep.getColumnModel().getColumn(6).setResizable(false);
        }

        btnrep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnrep.setText("Cambiar Estado");
        btnrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepActionPerformed(evt);
            }
        });

        cbestadorep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbestadorep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbestadorepActionPerformed(evt);
            }
        });

        txtconteorep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtconteorep.setEnabled(false);

        txtconteototal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtconteototal.setEnabled(false);

        txtnom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnom.setEnabled(false);

        txtmail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmail.setEnabled(false);

        txtpatente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtpatente.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbestadorep, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnrep)
                        .addGap(29, 29, 29)
                        .addComponent(txtconteorep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtconteototal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbestadorep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrep, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtconteorep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtconteototal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jsp2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addGap(38, 38, 38))
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
                .addContainerGap(40, Short.MAX_VALUE))
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
        CargarConteo();
        IgualarNumeros();
    }//GEN-LAST:event_tbdiagMouseClicked

    private void tbrepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrepMouseClicked
        mostrarcoddiag();
    }//GEN-LAST:event_tbrepMouseClicked

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
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
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btnrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepActionPerformed
        String errores = validartxtrep();
        if (errores.equals("")) {
            try {
                int opc = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere cambiar \n el estado de la reparación del desperfecto?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opc == JOptionPane.YES_OPTION) {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = conexion.prepareStatement("UPDATE detallediag SET Estado='"
                        + cbestadorep.getSelectedItem() + "' WHERE ID_Diag='" + txtnumdiag.getText() + "' AND Cod_Desp='" + txtcd.getText() + "'");
                pst.executeUpdate();
                //UPDATE `detallediag` SET `Estado`= 'En Reparación' WHERE ID_Diag= 00000001 AND Cod_Desp = 'CD0001'
                JOptionPane.showMessageDialog(this, "Estado Actualizado", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                mostrardatos();
                anchocolumnas();
                }
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, errores, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnrepActionPerformed

    private void cbestadorepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbestadorepActionPerformed
        // Arreglarlo con el combobox la weaita 
    }//GEN-LAST:event_cbestadorepActionPerformed

    private void txtnumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnumFocusLost

    }//GEN-LAST:event_txtnumFocusLost

    private void btcevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcevActionPerformed
        String error = validaestrepauto();
        if (error.equals("")) {
            try {
                int opc = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere cambiar \n el estado de la reparación del vehículo?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opc == JOptionPane.YES_OPTION) {
                    conexion = claseConectar.ConexionConBaseDatos.getConexion();
                    PreparedStatement pst = conexion.prepareStatement("UPDATE controldiag SET Estado_Diag='"
                            + cbcambioestauto.getSelectedItem() + "' WHERE ID_Diagnostico='" + txtnum.getText() + "'");
                    pst.executeUpdate();
                    //UPDATE `detallediag` SET `Estado`= 'En Reparación' WHERE ID_Diag= 00000001 AND Cod_Desp = 'CD0001'
                    JOptionPane.showMessageDialog(this, "Estado de Reparación Actualizado", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                    //Se recoge la información y se envía el email
                    Mensage = "Estimado (a): " + txtnom.getText() + "\nSe ha modificado el estado de la reparación número " + txtnum.getText() + ".\nPor favor acérquese a nuestro local para el retiro de su vehículo con patente " + txtpatente.getText() + ".\nAtentamente. \nTaller mecánico Techo Rojo.";
                    To = txtmail.getText();
                    Subject = "Vehículo Reparado.";

                    SendMail();
                    txtnom.setText("");
                    txtmail.setText("");
                    txtpatente.setText("");
                    cbcambioestauto.setVisible(false);
                    btcev.setVisible(false);
                    cbcambioestauto.setSelectedIndex(0);
                    mostrardatos();
                    anchocolumnas();
                    LimpiaTabla();
                }
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(rootPane, "El insumo ya existe en el sistema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btcevActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcev;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnrep;
    private javax.swing.JComboBox cbcambioestauto;
    private javax.swing.JComboBox cbestadorep;
    private javax.swing.JScrollPane jsp1;
    private javax.swing.JScrollPane jsp2;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private javax.swing.JTable tbdiag;
    private javax.swing.JTable tbrep;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtconteorep;
    private javax.swing.JTextField txtconteototal;
    private javax.swing.JTextField txtmail;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtnum;
    private javax.swing.JTextField txtnumdiag;
    private javax.swing.JTextField txtpatente;
    // End of variables declaration//GEN-END:variables
}
/*
 radiobuttons

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


 */
