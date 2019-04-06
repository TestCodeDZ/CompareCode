/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import sgtmtr.Login;
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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private boolean select = false;
    private int DiaX;
    private int MesX;
    private int YearX;
    private int DiaY;
    private int MesY;
    private int YearY;

    private int Dia;
    private int Mes;
    private int Year;
    private int Dia2;
    private int Mes2;
    private int Year2;

    public diagnostico() {
        initComponents();
        setTitle("Ingreso de Diagnóstico");
        this.setLocation(280, 0);
        txtnumdiag.setDisabledTextColor(Color.red);
        txtpatentediag.setDisabledTextColor(Color.blue);
        //txtfecha.setDisabledTextColor(Color.blue);
        //txtfecha.setText(fechaact());
        txtrutcliente.setDisabledTextColor(Color.blue);
        txtve.setDisabledTextColor(Color.red);
        CargarComboED();
        numeros();
        /*Deshabilitar txt del jcalendar*/
        //fing.getDateEditor().setEnabled(false);
        //fent.getDateEditor().setEnabled(false);

        //jdatechooser vacio
        /*if(jdcfed.getDate()==null){
         }*/
        //fechaingreso.setEnabled(false);
        // hasta 255 caracteres o cta regresiva conteo = txtrepuestos.getText()).length();
        lbconteo.setText("0");
        //fing.setDateFormat(Calendar.getInstance().getTime());
        Date x = fing.getSelectedDate().getTime();
        Dia = x.getDate();
        Mes = (x.getMonth() + 1);
        Year = 2000 + (x.getYear() - 100);
        Date y = fent.getSelectedDate().getTime();
        Dia2 = y.getDate();
        Mes2 = (y.getMonth() + 1);
        Year2 = 2000 + (y.getYear() - 100);
        //Date fing = Calendar.getInstance().getTime();
        //Date fent = Calendar.getInstance().getTime();
        Username = "tallertechorojo@gmail.com";
        PassWord = new String("techrojo");
        txtrepuestos.setLineWrap(true); //Se logra que haya salto de línea en el TextArea
        txtrepuestos.setWrapStyleWord(true); //Se impide la división de palabras en el TestArea
    }

    public static String Username = "";
    public static String PassWord = "";
    String Mensage = "";
    String To = "";
    String Subject = "";
    bv bv = new bv(); //crear el nuevo formulario

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

    private void CargarComboED() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st = conexion.createStatement();
            String sql = "SELECT Estado FROM estadodiag WHERE ID=1";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);

            //Limpiamos el Combo
            cmbed.setModel(new DefaultComboBoxModel());

            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cmbed.addItem(rs.getObject(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void numeros() {
        String c = "";
        String SQL = "select max(ID_Diagnostico) from controldiag";
        //ps = conexion.prepareStatement(SQL);
        //Ejecutar consulta

        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
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
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void controldiag() {
        String InsertarSQL = "INSERT INTO controldiag(ID_Diagnostico,Patente,RUTCliente,Mecanico,F_Ing_Diagnostico,F_Ent_Diag,Repuestos,Total,Estado_Diag) VALUES (?,?,?,?,?,?,?,?,?)";
        String numdiag = txtnumdiag.getText();
        String patente = txtpatentediag.getText();
        String RC = txtrutcliente.getText();
        String mecanico = txtmecanico.getText();

        /*fecha original para insert
         Date fc1 = fing.getDate();
         DateFormat fecha1 = new SimpleDateFormat("dd-MM-yyyy");
         String convertido1 = fecha1.format(fc1);
         Date fc2 = fing.getDate();
         DateFormat fecha2 = new SimpleDateFormat("dd-MM-yyyy");
         String convertido2 = fecha2.format(fc2);
         */
        String rep = txtrepuestos.getText();
        String total = txtve.getText();
        String ediag = cmbed.getSelectedItem().toString();
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            PreparedStatement pst = conexion.prepareStatement(InsertarSQL);
            pst.setString(1, numdiag);
            pst.setString(2, patente);
            pst.setString(3, RC);
            pst.setString(4, mecanico);
            pst.setString(5, completarFecha(Dia) + "-" + completarFecha(Mes) + "-" + Year);
            pst.setString(6, completarFecha2(Dia2) + "-" + completarFecha2(Mes2) + "-" + Year2);
            pst.setString(7, rep);
            pst.setString(8, total);
            pst.setString(9, ediag);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se ha ingresado el diagnóstino N° " + numdiag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
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
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = conexion.prepareStatement(InsertarSQL);
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
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
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

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtnumdiag = new javax.swing.JTextField();
        fing = new datechooser.beans.DateChooserCombo();
        txtmecanico = new javax.swing.JTextField();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel14 = new javax.swing.JLabel();
        txtpatentediag = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtrutcliente = new javax.swing.JTextField();
        btejecutartbvehiculo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtmarca = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtmodelo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtaño = new javax.swing.JTextField();
        txtcolor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtfono = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtmail = new javax.swing.JTextField();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtrepuestos = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cmbed = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        fent = new datechooser.beans.DateChooserCombo();
        lbconteo = new javax.swing.JLabel();
        panelTranslucido4 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel1 = new javax.swing.JLabel();
        btbuscadesperfecto = new javax.swing.JButton();
        jsp = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();
        txtve = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btingresodiag = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("N° Diagnóstico");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Fecha de Ingreso");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Mecánico");

        txtnumdiag.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumdiag.setEnabled(false);

        fing.setCalendarPreferredSize(new java.awt.Dimension(400, 200));
        fing.setNothingAllowed(false);
        fing.setFormat(2);
        fing.setEnabled(false);
        fing.setFieldFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        fing.setNavigateFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        fing.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                fingOnCommit(evt);
            }
        });

        txtmecanico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmecanico.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(fing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Vehículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Patente");

        txtpatentediag.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtpatentediag.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("RUT Cliente");

        txtrutcliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrutcliente.setEnabled(false);

        btejecutartbvehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecutartbvehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutartbvehiculoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Marca");

        txtmarca.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmarca.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nombre");

        txtnombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombre.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Modelo");

        txtmodelo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmodelo.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Año");

        txtaño.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtaño.setEnabled(false);

        txtcolor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcolor.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Color");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fono");

        txtfono.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfono.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("E-mail");

        txtmail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmail.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtpatentediag, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(btejecutartbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel12)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel13)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel17))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(3, 3, 3)
                        .addComponent(txtpatentediag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btejecutartbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(txtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(3, 3, 3)
                        .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addGap(3, 3, 3)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelTranslucido3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Diagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Repuestos");

        txtrepuestos.setColumns(20);
        txtrepuestos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrepuestos.setRows(5);
        txtrepuestos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrepuestosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtrepuestos);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estado del Diagnóstico");

        cmbed.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbedItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha Entrega Diagnóstico");

        fent.setCalendarPreferredSize(new java.awt.Dimension(400, 200));
        fent.setNothingAllowed(false);
        fent.setFieldFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        fent.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                fentOnCommit(evt);
            }
        });

        lbconteo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbconteo.setForeground(new java.awt.Color(255, 255, 255));
        lbconteo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbconteo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbed, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(fent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(lbconteo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbed, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTranslucido4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        panelTranslucido4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Desperfectos");

        btbuscadesperfecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btbuscadesperfecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscadesperfectoActionPerformed(evt);
            }
        });

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
        tbdiag.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdiag.getTableHeader().setResizingAllowed(false);
        tbdiag.getTableHeader().setReorderingAllowed(false);
        jsp.setViewportView(tbdiag);
        if (tbdiag.getColumnModel().getColumnCount() > 0) {
            tbdiag.getColumnModel().getColumn(0).setResizable(false);
            tbdiag.getColumnModel().getColumn(1).setResizable(false);
            tbdiag.getColumnModel().getColumn(2).setResizable(false);
            tbdiag.getColumnModel().getColumn(3).setResizable(false);
            tbdiag.getColumnModel().getColumn(4).setResizable(false);
            tbdiag.getColumnModel().getColumn(5).setResizable(false);
        }

        txtve.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtve.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtve.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Valor Estimado");

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

        javax.swing.GroupLayout panelTranslucido4Layout = new javax.swing.GroupLayout(panelTranslucido4);
        panelTranslucido4.setLayout(panelTranslucido4Layout);
        panelTranslucido4Layout.setHorizontalGroup(
            panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTranslucido4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btbuscadesperfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTranslucido4Layout.createSequentialGroup()
                            .addGap(402, 402, 402)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtve, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jsp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btingresodiag)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido4Layout.setVerticalGroup(
            panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido4Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTranslucido4Layout.createSequentialGroup()
                        .addGroup(panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btbuscadesperfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido4Layout.createSequentialGroup()
                        .addComponent(btingresodiag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelTranslucido4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btejecutartbvehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutartbvehiculoActionPerformed
        if (!bv.isVisible()) {
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
        } else {
            System.out.println("Busca Vehículo: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btejecutartbvehiculoActionPerformed

    private void cmbedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbedItemStateChanged
        /*if(evt.getStateChange() == ItemEvent.SELECTED) {
         CargartextfieldED();
         }*/
    }//GEN-LAST:event_cmbedItemStateChanged
    private String validarVacios() {
        String errores = "";
        if (txtrutcliente.getText().equals("")) {
            errores += "Por favor busque los datos del cliente\n";
        }
        if (txtve.getText().equals("")) {
            errores += "Calcule el monto total \n";
        }
        if (txtve.getText().equals("0")) {
            errores += "Revise el detalle de la venta \n";
        }
        if (txtrepuestos.getText().trim().isEmpty()) {
            errores += "Ingrese el valor que el cliente pagó\n";
        }
        if (txtmail.getText().trim().isEmpty()) {
            errores += "Debe ingresar un correo electrónico para informar al cliente\n";
        }
        return errores;
    }

    public void generapdf() {
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Map parametro = new HashMap();
            JasperReport reportes = JasperCompileManager.compileReport("reportediagnostico.jrxml");
            parametro.put("num", txtnumdiag.getText());
            //se carga el reporte
            //se procesa el archivo jasper
            JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conexion);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta.", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }
    private void btingresodiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresodiagActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            if (!"".equals(fing.getText()) && !"".equals(fent.getText())) {
                if (IsFechaCorrecta()) {
                    //diagnostico diaag = new diagnostico(null, true, (YearX + completarFecha(MesX) + completarFecha(DiaX)), (YearY + completarFecha(MesY) + completarFecha(DiaY)), select);
                    System.out.println((YearX + completarFecha(MesX) + completarFecha(DiaX)) + "-" + (YearY + completarFecha(MesY) + completarFecha(DiaY)));

                    //diaag.setVisible(true);
                    try {
                        int opc = JOptionPane.showConfirmDialog(this, "¿Desea ingresar el diagnóstico?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (opc == JOptionPane.YES_OPTION) {
                            controldiag();
                            detallediag();

                            //Se recoge la información y se envía el email
                            Mensage = "Estimado (a): " + txtnombre.getText() + "\nSe ha ingresado el diagnóstico número " + txtnumdiag.getText() + ".\nPor favor acérquese a nuestro local para confirmación de la reparación de su vehículo con patente " + txtpatentediag.getText() + ".\nAtentamente. \nTaller mecánico Techo Rojo.";
                            To = txtmail.getText();
                            Subject = "Ingreso de diagnóstico.";

                            SendMail();
                            generapdf();

                            txtpatentediag.setText("");
                            txtmarca.setText("");
                            txtmodelo.setText("");
                            txtaño.setText("");
                            txtcolor.setText("");
                            txtrutcliente.setText("");
                            txtnombre.setText("");
                            txtfono.setText("");
                            txtmail.setText("");
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
                        }
                        //this.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una Fecha Correcta!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Fecha!");
            }
        } else {
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btingresodiagActionPerformed

    private void btbuscadesperfectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscadesperfectoActionPerformed
        SeleccionDesperfecto sd = new SeleccionDesperfecto(null, true);
        sd.setVisible(true);
    }//GEN-LAST:event_btbuscadesperfectoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere eliminar \n el desperfecto seleccionado?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opc == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tbdiag.getModel();
            int fila = tbdiag.getSelectedRow();
            if (fila >= 0) {
                model.removeRow(fila);
                calcula.calcularRep();
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila para eliminar");
            }
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

    private void fingOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_fingOnCommit
        //Calendar.getInstance().getTime()
        try {
            Date x = fing.getSelectedDate().getTime();
            Dia = x.getDate();
            Mes = (x.getMonth() + 1);
            Year = 2000 + (x.getYear() - 100);
        } catch (NullPointerException no) {

        }
    }//GEN-LAST:event_fingOnCommit

    private void fentOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_fentOnCommit
        try {
            Date y = fent.getSelectedDate().getTime();
            Dia2 = y.getDate();
            Mes2 = (y.getMonth() + 1);
            Year2 = 2000 + (y.getYear() - 100);
        } catch (NullPointerException no) {

        }
    }//GEN-LAST:event_fentOnCommit
    private boolean IsFechaCorrecta() {
        if (Year > Year2) {
            return false;
        } else if (Mes > Mes2) {
            return false;
        } else if (Dia > Dia2) {
            return false;
        }
        return true;
    }

    public String completarFecha(int d) {
        return d < 10 ? "0" + d : d + "";
    }

    public String completarFecha2(int xd) {
        return xd < 10 ? "0" + xd : xd + "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbuscadesperfecto;
    private javax.swing.JButton btejecutartbvehiculo;
    private javax.swing.JButton btingresodiag;
    private javax.swing.JComboBox cmbed;
    private datechooser.beans.DateChooserCombo fent;
    private datechooser.beans.DateChooserCombo fing;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel lbconteo;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido4;
    public static javax.swing.JTable tbdiag;
    public static javax.swing.JTextField txtaño;
    public static javax.swing.JTextField txtcolor;
    public static javax.swing.JTextField txtfono;
    public static javax.swing.JTextField txtmail;
    public static javax.swing.JTextField txtmarca;
    public static javax.swing.JTextField txtmecanico;
    public static javax.swing.JTextField txtmodelo;
    public static javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnumdiag;
    public static javax.swing.JTextField txtpatentediag;
    private javax.swing.JTextArea txtrepuestos;
    public static javax.swing.JTextField txtrutcliente;
    public static javax.swing.JTextField txtve;
    // End of variables declaration//GEN-END:variables
}
