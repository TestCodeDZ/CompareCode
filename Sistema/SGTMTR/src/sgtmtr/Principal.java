/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZuluCorp
 */
public class Principal extends javax.swing.JFrame {

    //calcular fecha
    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatoFecha.format(fecha);
    }

    /**
     * Creates new form Principal
     */
    javax.swing.ImageIcon iconoConectado = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"));
    javax.swing.ImageIcon iconoDesconectado = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_offline.png"));

    public Principal() {
        super("Sistema de Gestión de Taller Mecánico");
        //Abrir ventana de validacion de usuarios
        new Login(this, true).setVisible(true);
        initComponents();
        clockDigital1.show();
        lbfecha.setText(fechaActual());
        lbfecha1.setText(fechaActual());
        lbfecha2.setText(fechaActual());
        lbfecha3.setText(fechaActual());
        //centrar la pantalla
        //setLocationRelativeTo(null);
        //fondo1.setVisible(false);
        //this.setSize(1000, 768);
        //maximizar pantalla por defecto al abrir el form
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.setLocation(600,150);
        //icono
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/business_logo.png"));
        setIconImage(icon);
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel4.setOpaque(false);
        //Mostramos el nombre de Usuario y tipo de usuario del sistema en c/u de los fondos de usuarios
        lblusuario.setText("Usuario Conectado: " + Login.Nombres + " " + Login.Apellidos);
        lbltipo.setText("Tipo de Usuario: " + Login.Cargo);
        lblusuario1.setText("Usuario Conectado: " + Login.Nombres + " " + Login.Apellidos);
        lbltipo1.setText("Tipo de Usuario: " + Login.Cargo);
        lblusuario2.setText("Usuario Conectado: " + Login.Nombres + " " + Login.Apellidos);
        lbltipo2.setText("Tipo de Usuario: " + Login.Cargo);
        lblusuario3.setText("Usuario Conectado: " + Login.Nombres + " " + Login.Apellidos);
        lbltipo3.setText("Tipo de Usuario: " + Login.Cargo);
        btndmi.setText("<html><div align='center'>Diagnósticos Mal Ingresados</div></html>");
        btnvtains.setText("<html><div align='center'>Venta De Insumos</div></html>");
        btnactestdiag.setText("<html><div align='center'>Actualización Estado Diagnóstico</div></html>");
        btnpagorep.setText("<html><div align='center'>Pago de Reparaciones</div></html>");
        btnbd.setText("<html><div align='center'>Administración de la Base de Datos</div></html>");
        btncvi.setText("<html><div align='center'>Consulta de Venta de Insumos</div></html>");
        btnconsultadiags.setText("<html><div align='center'>Consulta de Diagnósticos</div></html>");
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                int rpt = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir del programa?", "¡Advertencia!", JOptionPane.YES_NO_OPTION);
                if (rpt == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

        //lbnombres.setText("Nombres: " + Login.Nombres);
        // lbapellidos.setText("Apellidos: " + Login.Apellidos);
        /*btnusuarios.setOpaque(false);
         .setContentAreaFilled(false);
         btnusuarios.setBorderPainted(false);*/
    }

    //llamado jInternalframes
    UsuariosSistemaAdmin us = new UsuariosSistemaAdmin();
    Insumos in = new Insumos();
    ConsultaComprobantes ccvta = new ConsultaComprobantes();
    UsuariosSistema us2 = new UsuariosSistema();
    ClientesSistema cs = new ClientesSistema();
    MarcaVehiculo mv = new MarcaVehiculo();
    vehiculos v = new vehiculos();
    Desperfectos d = new Desperfectos();
    diagnostico diag = new diagnostico();
    ComprobanteVta cvta = new ComprobanteVta();
    EliminacionDiagnosticosMalIngresados edmi = new EliminacionDiagnosticosMalIngresados();
    ConsultaDiagnosticos cdiags = new ConsultaDiagnosticos();

    private void deshabilitarmenu() {
        this.mnireportes.setVisible(false);
        this.mnicambiarpass.setVisible(false);
        this.mnicerrarsesion.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jdpescritorio = new javax.swing.JDesktopPane();
        PanelPrincipal = new elaprendiz.gui.panel.PanelImage();
        panelCurves2 = new elaprendiz.gui.panel.PanelCurves();
        PanelPrincipalDueño = new elaprendiz.gui.panel.PanelImage();
        panelCurves3 = new elaprendiz.gui.panel.PanelCurves();
        lbltipo = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        lbfecha = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        lblimg = new javax.swing.JLabel();
        clockDigital1 = new elaprendiz.gui.varios.ClockDigital();
        jPanel1 = new javax.swing.JPanel();
        btninsumos = new javax.swing.JButton();
        btncvi = new javax.swing.JButton();
        btnconsultadiags = new javax.swing.JButton();
        btnbd = new javax.swing.JButton();
        btnusuarios = new javax.swing.JButton();
        PanelPrincipalJefe = new elaprendiz.gui.panel.PanelImage();
        panelCurves4 = new elaprendiz.gui.panel.PanelCurves();
        lbltipo1 = new javax.swing.JLabel();
        lblhora1 = new javax.swing.JLabel();
        lbfecha1 = new javax.swing.JLabel();
        lblfecha1 = new javax.swing.JLabel();
        lblusuario1 = new javax.swing.JLabel();
        lblimg1 = new javax.swing.JLabel();
        clockDigital2 = new elaprendiz.gui.varios.ClockDigital();
        jPanel2 = new javax.swing.JPanel();
        btnusuarios1 = new javax.swing.JButton();
        btnclientes = new javax.swing.JButton();
        btnmarcas = new javax.swing.JButton();
        btnvehiculos = new javax.swing.JButton();
        btndesperfectos = new javax.swing.JButton();
        btndmi = new javax.swing.JButton();
        PanelPrincipalMec = new elaprendiz.gui.panel.PanelImage();
        panelCurves6 = new elaprendiz.gui.panel.PanelCurves();
        clockDigital3 = new elaprendiz.gui.varios.ClockDigital();
        jPanel3 = new javax.swing.JPanel();
        btndiagnostico = new javax.swing.JButton();
        btnreparaciones = new javax.swing.JButton();
        lbltipo2 = new javax.swing.JLabel();
        lbfecha2 = new javax.swing.JLabel();
        lblfecha2 = new javax.swing.JLabel();
        lblimg2 = new javax.swing.JLabel();
        lblusuario2 = new javax.swing.JLabel();
        lblhora2 = new javax.swing.JLabel();
        PanelPrincipalSecretaria = new elaprendiz.gui.panel.PanelImage();
        panelCurves7 = new elaprendiz.gui.panel.PanelCurves();
        clockDigital4 = new elaprendiz.gui.varios.ClockDigital();
        jPanel4 = new javax.swing.JPanel();
        btnvtains = new javax.swing.JButton();
        btnactestdiag = new javax.swing.JButton();
        btnpagorep = new javax.swing.JButton();
        lbltipo3 = new javax.swing.JLabel();
        lbfecha3 = new javax.swing.JLabel();
        lblfecha3 = new javax.swing.JLabel();
        lblimg3 = new javax.swing.JLabel();
        lblusuario3 = new javax.swing.JLabel();
        lblhora3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnipersonal = new javax.swing.JMenu();
        mnicambiarpass = new javax.swing.JMenuItem();
        mnicerrarsesion = new javax.swing.JMenuItem();
        mnireportes = new javax.swing.JMenu();
        mniusers = new javax.swing.JMenuItem();
        mnirepclientes = new javax.swing.JMenuItem();
        mnirepmarcas = new javax.swing.JMenuItem();
        mnirepdesperfectos = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        mniayuda = new javax.swing.JMenu();
        mniacercade = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jdpescritorio.setBackground(new java.awt.Color(25, 153, 153));
        jdpescritorio.setPreferredSize(new java.awt.Dimension(1366, 768));

        PanelPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/img.jpg"))); // NOI18N
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(1366, 768));

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves2, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves2, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        PanelPrincipalDueño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/img.jpg"))); // NOI18N
        PanelPrincipalDueño.setPreferredSize(new java.awt.Dimension(1366, 768));

        lbltipo.setBackground(new java.awt.Color(0, 153, 153));
        lbltipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbltipo.setForeground(new java.awt.Color(255, 255, 255));
        lbltipo.setText("Tipo:");

        lblhora.setBackground(new java.awt.Color(0, 153, 153));
        lblhora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblhora.setForeground(new java.awt.Color(255, 255, 255));
        lblhora.setText("Hora:");

        lbfecha.setBackground(new java.awt.Color(0, 153, 153));
        lbfecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbfecha.setForeground(new java.awt.Color(255, 255, 255));
        lbfecha.setText("jLabel1");

        lblfecha.setBackground(new java.awt.Color(0, 153, 153));
        lblfecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha.setText("Fecha:");

        lblusuario.setBackground(new java.awt.Color(0, 153, 153));
        lblusuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario.setText("Usuario Conectado: ");

        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"))); // NOI18N

        clockDigital1.setBackground(new java.awt.Color(0, 153, 153));
        clockDigital1.setForeground(new java.awt.Color(255, 255, 255));
        clockDigital1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelCurves3Layout = new javax.swing.GroupLayout(panelCurves3);
        panelCurves3.setLayout(panelCurves3Layout);
        panelCurves3Layout.setHorizontalGroup(
            panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblusuario)
                .addGap(57, 57, 57)
                .addComponent(lbltipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                .addComponent(lblfecha)
                .addGap(18, 18, 18)
                .addComponent(lbfecha)
                .addGap(37, 37, 37)
                .addComponent(lblhora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clockDigital1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(298, 298, 298))
        );
        panelCurves3Layout.setVerticalGroup(
            panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves3Layout.createSequentialGroup()
                .addGroup(panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblusuario)
                        .addComponent(lbltipo))
                    .addComponent(lblimg)
                    .addGroup(panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCurves3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblfecha)
                            .addComponent(lbfecha))
                        .addComponent(lblhora)
                        .addComponent(clockDigital1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 768, Short.MAX_VALUE))
        );

        lbltipo.getAccessibleContext().setAccessibleParent(panelCurves3);
        lblhora.getAccessibleContext().setAccessibleParent(panelCurves3);
        lbfecha.getAccessibleContext().setAccessibleParent(panelCurves3);
        lblfecha.getAccessibleContext().setAccessibleParent(panelCurves3);
        lblusuario.getAccessibleContext().setAccessibleParent(panelCurves3);
        lblimg.getAccessibleContext().setAccessibleParent(panelCurves3);

        btninsumos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btninsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/icontexto-mountain-bike-pedal (Custom).png"))); // NOI18N
        btninsumos.setText("Insumos");
        btninsumos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btninsumos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btninsumos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btninsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsumosActionPerformed(evt);
            }
        });

        btncvi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncvi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/iconopdf.gif"))); // NOI18N
        btncvi.setText("Consulta de Ventas");
        btncvi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btncvi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btncvi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btncvi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncviActionPerformed(evt);
            }
        });

        btnconsultadiags.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnconsultadiags.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/icon-32-reparacion.png"))); // NOI18N
        btnconsultadiags.setText("Consulta de Diagnósticos");
        btnconsultadiags.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnconsultadiags.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnconsultadiags.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnconsultadiags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultadiagsActionPerformed(evt);
            }
        });

        btnbd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/copia-seguridad.png"))); // NOI18N
        btnbd.setText("Base de Datos");
        btnbd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbd.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnbd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnbd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbdActionPerformed(evt);
            }
        });

        btnusuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/agente.png"))); // NOI18N
        btnusuarios.setText("Usuarios");
        btnusuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnusuarios.setOpaque(false);
        btnusuarios.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnusuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnusuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(282, Short.MAX_VALUE)
                .addComponent(btnusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btninsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnbd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncvi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnconsultadiags, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnusuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btninsumos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnconsultadiags, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncvi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelPrincipalDueñoLayout = new javax.swing.GroupLayout(PanelPrincipalDueño);
        PanelPrincipalDueño.setLayout(PanelPrincipalDueñoLayout);
        PanelPrincipalDueñoLayout.setHorizontalGroup(
            PanelPrincipalDueñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDueñoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCurves3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelPrincipalDueñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalDueñoLayout.createSequentialGroup()
                    .addContainerGap(83, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(314, Short.MAX_VALUE)))
        );
        PanelPrincipalDueñoLayout.setVerticalGroup(
            PanelPrincipalDueñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDueñoLayout.createSequentialGroup()
                .addComponent(panelCurves3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelPrincipalDueñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalDueñoLayout.createSequentialGroup()
                    .addGap(0, 443, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        PanelPrincipalJefe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/img.jpg"))); // NOI18N
        PanelPrincipalJefe.setPreferredSize(new java.awt.Dimension(1366, 768));

        lbltipo1.setBackground(new java.awt.Color(0, 153, 153));
        lbltipo1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbltipo1.setForeground(new java.awt.Color(255, 255, 255));
        lbltipo1.setText("Tipo:");

        lblhora1.setBackground(new java.awt.Color(0, 153, 153));
        lblhora1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblhora1.setForeground(new java.awt.Color(255, 255, 255));
        lblhora1.setText("Hora:");

        lbfecha1.setBackground(new java.awt.Color(0, 153, 153));
        lbfecha1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbfecha1.setForeground(new java.awt.Color(255, 255, 255));
        lbfecha1.setText("jLabel1");

        lblfecha1.setBackground(new java.awt.Color(0, 153, 153));
        lblfecha1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblfecha1.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha1.setText("Fecha:");

        lblusuario1.setBackground(new java.awt.Color(0, 153, 153));
        lblusuario1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblusuario1.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario1.setText("Usuario Conectado: ");

        lblimg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"))); // NOI18N

        clockDigital2.setBackground(new java.awt.Color(0, 153, 153));
        clockDigital2.setForeground(new java.awt.Color(255, 255, 255));
        clockDigital2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnusuarios1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnusuarios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/agente.png"))); // NOI18N
        btnusuarios1.setText("Usuarios");
        btnusuarios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnusuarios1.setOpaque(false);
        btnusuarios1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnusuarios1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnusuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnusuarios1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnusuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 119, 131));

        btnclientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/clientesG-32x32.png"))); // NOI18N
        btnclientes.setText("Clientes");
        btnclientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnclientes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnclientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 115, 131));

        btnmarcas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnmarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/ico_bmw_0.png"))); // NOI18N
        btnmarcas.setText("Marcas");
        btnmarcas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnmarcas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnmarcas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnmarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmarcasActionPerformed(evt);
            }
        });
        jPanel2.add(btnmarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 11, 115, 131));

        btnvehiculos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnvehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/92341_steering_wheel-512.png"))); // NOI18N
        btnvehiculos.setText("Vehículos");
        btnvehiculos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnvehiculos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnvehiculos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnvehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvehiculosActionPerformed(evt);
            }
        });
        jPanel2.add(btnvehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(679, 11, 115, 131));

        btndesperfectos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndesperfectos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/multi.png"))); // NOI18N
        btndesperfectos.setText("Desperfectos");
        btndesperfectos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndesperfectos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btndesperfectos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndesperfectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndesperfectosActionPerformed(evt);
            }
        });
        jPanel2.add(btndesperfectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(804, 11, -1, 131));

        btndmi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndmi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/delete.png"))); // NOI18N
        btndmi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndmi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btndmi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndmiActionPerformed(evt);
            }
        });
        jPanel2.add(btndmi, new org.netbeans.lib.awtextra.AbsoluteConstraints(933, 11, 115, 131));

        javax.swing.GroupLayout panelCurves4Layout = new javax.swing.GroupLayout(panelCurves4);
        panelCurves4.setLayout(panelCurves4Layout);
        panelCurves4Layout.setHorizontalGroup(
            panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(269, Short.MAX_VALUE))
                    .addGroup(panelCurves4Layout.createSequentialGroup()
                        .addComponent(lblimg1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblusuario1)
                        .addGap(192, 192, 192)
                        .addComponent(lbltipo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblfecha1)
                        .addGap(18, 18, 18)
                        .addComponent(lbfecha1)
                        .addGap(37, 37, 37)
                        .addComponent(lblhora1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298))))
        );
        panelCurves4Layout.setVerticalGroup(
            panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves4Layout.createSequentialGroup()
                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblusuario1)
                        .addComponent(lbltipo1))
                    .addComponent(lblimg1)
                    .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblfecha1)
                            .addComponent(lbfecha1))
                        .addComponent(lblhora1)
                        .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        javax.swing.GroupLayout PanelPrincipalJefeLayout = new javax.swing.GroupLayout(PanelPrincipalJefe);
        PanelPrincipalJefe.setLayout(PanelPrincipalJefeLayout);
        PanelPrincipalJefeLayout.setHorizontalGroup(
            PanelPrincipalJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalJefeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCurves4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelPrincipalJefeLayout.setVerticalGroup(
            PanelPrincipalJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalJefeLayout.createSequentialGroup()
                .addComponent(panelCurves4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelPrincipalMec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/img.jpg"))); // NOI18N
        PanelPrincipalMec.setPreferredSize(new java.awt.Dimension(1366, 768));

        panelCurves6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clockDigital3.setBackground(new java.awt.Color(0, 153, 153));
        clockDigital3.setForeground(new java.awt.Color(255, 255, 255));
        clockDigital3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelCurves6.add(clockDigital3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btndiagnostico.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/steam (Custom).png"))); // NOI18N
        btndiagnostico.setText("Diagnósticos");
        btndiagnostico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndiagnostico.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btndiagnostico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndiagnosticoActionPerformed(evt);
            }
        });
        jPanel3.add(btndiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 150, 130));

        btnreparaciones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnreparaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/icon-32-reparacion.png"))); // NOI18N
        btnreparaciones.setText("Reparaciones");
        btnreparaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnreparaciones.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnreparaciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnreparaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreparacionesActionPerformed(evt);
            }
        });
        jPanel3.add(btnreparaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 150, 130));

        panelCurves6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, -1, -1));

        lbltipo2.setBackground(new java.awt.Color(0, 153, 153));
        lbltipo2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbltipo2.setForeground(new java.awt.Color(255, 255, 255));
        lbltipo2.setText("Tipo:");
        panelCurves6.add(lbltipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        lbfecha2.setBackground(new java.awt.Color(0, 153, 153));
        lbfecha2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbfecha2.setForeground(new java.awt.Color(255, 255, 255));
        lbfecha2.setText("jLabel1");
        panelCurves6.add(lbfecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, -1));

        lblfecha2.setBackground(new java.awt.Color(0, 153, 153));
        lblfecha2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblfecha2.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha2.setText("Fecha:");
        panelCurves6.add(lblfecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        lblimg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"))); // NOI18N
        panelCurves6.add(lblimg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        lblusuario2.setBackground(new java.awt.Color(0, 153, 153));
        lblusuario2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblusuario2.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario2.setText("Usuario Conectado: ");
        panelCurves6.add(lblusuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        lblhora2.setBackground(new java.awt.Color(0, 153, 153));
        lblhora2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblhora2.setForeground(new java.awt.Color(255, 255, 255));
        lblhora2.setText("Hora:");
        panelCurves6.add(lblhora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, -1, -1));

        javax.swing.GroupLayout PanelPrincipalMecLayout = new javax.swing.GroupLayout(PanelPrincipalMec);
        PanelPrincipalMec.setLayout(PanelPrincipalMecLayout);
        PanelPrincipalMecLayout.setHorizontalGroup(
            PanelPrincipalMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves6, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        PanelPrincipalMecLayout.setVerticalGroup(
            PanelPrincipalMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves6, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        PanelPrincipalSecretaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/img.jpg"))); // NOI18N
        PanelPrincipalSecretaria.setPreferredSize(new java.awt.Dimension(1366, 768));

        panelCurves7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clockDigital4.setBackground(new java.awt.Color(0, 153, 153));
        clockDigital4.setForeground(new java.awt.Color(255, 255, 255));
        clockDigital4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelCurves7.add(clockDigital4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, -1, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnvtains.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnvtains.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/handy-icon_14_bw (Custom).png"))); // NOI18N
        btnvtains.setText("Venta de Insumos");
        btnvtains.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnvtains.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnvtains.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnvtains.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvtainsActionPerformed(evt);
            }
        });
        jPanel4.add(btnvtains, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 161, 150));

        btnactestdiag.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnactestdiag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/clientesG-32x32.png"))); // NOI18N
        btnactestdiag.setText("Actualización Estado Diagnóstico");
        btnactestdiag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnactestdiag.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnactestdiag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnactestdiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactestdiagActionPerformed(evt);
            }
        });
        jPanel4.add(btnactestdiag, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 161, 150));

        btnpagorep.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnpagorep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redimensionar/money.png"))); // NOI18N
        btnpagorep.setText("Pago de Reparaciones");
        btnpagorep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnpagorep.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnpagorep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnpagorep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpagorepActionPerformed(evt);
            }
        });
        jPanel4.add(btnpagorep, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 161, 150));

        panelCurves7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 930, 150));

        lbltipo3.setBackground(new java.awt.Color(0, 153, 153));
        lbltipo3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbltipo3.setForeground(new java.awt.Color(255, 255, 255));
        lbltipo3.setText("Tipo:");
        panelCurves7.add(lbltipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        lbfecha3.setBackground(new java.awt.Color(0, 153, 153));
        lbfecha3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbfecha3.setForeground(new java.awt.Color(255, 255, 255));
        lbfecha3.setText("jLabel1");
        panelCurves7.add(lbfecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, -1));

        lblfecha3.setBackground(new java.awt.Color(0, 153, 153));
        lblfecha3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblfecha3.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha3.setText("Fecha:");
        panelCurves7.add(lblfecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        lblimg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"))); // NOI18N
        panelCurves7.add(lblimg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblusuario3.setBackground(new java.awt.Color(0, 153, 153));
        lblusuario3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblusuario3.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario3.setText("Usuario Conectado: ");
        panelCurves7.add(lblusuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        lblhora3.setBackground(new java.awt.Color(0, 153, 153));
        lblhora3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblhora3.setForeground(new java.awt.Color(255, 255, 255));
        lblhora3.setText("Hora:");
        panelCurves7.add(lblhora3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, -1, -1));

        javax.swing.GroupLayout PanelPrincipalSecretariaLayout = new javax.swing.GroupLayout(PanelPrincipalSecretaria);
        PanelPrincipalSecretaria.setLayout(PanelPrincipalSecretariaLayout);
        PanelPrincipalSecretariaLayout.setHorizontalGroup(
            PanelPrincipalSecretariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves7, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        PanelPrincipalSecretariaLayout.setVerticalGroup(
            PanelPrincipalSecretariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves7, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jdpescritorioLayout = new javax.swing.GroupLayout(jdpescritorio);
        jdpescritorio.setLayout(jdpescritorioLayout);
        jdpescritorioLayout.setHorizontalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalDueño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalJefe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalMec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jdpescritorioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelPrincipalSecretaria, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jdpescritorioLayout.setVerticalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jdpescritorioLayout.createSequentialGroup()
                    .addComponent(PanelPrincipalDueño, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalJefe, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalMec, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
            .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jdpescritorioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelPrincipalSecretaria, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jdpescritorio.setLayer(PanelPrincipal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(PanelPrincipalDueño, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(PanelPrincipalJefe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(PanelPrincipalMec, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(PanelPrincipalSecretaria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        mnipersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/folder.png"))); // NOI18N
        mnipersonal.setText("Personal");
        mnipersonal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnipersonal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnipersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnipersonal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mnipersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnicambiarpass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnicambiarpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/key_2_32x32.png"))); // NOI18N
        mnicambiarpass.setText("Cambiar Contraseña");
        mnicambiarpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnicambiarpassActionPerformed(evt);
            }
        });
        mnipersonal.add(mnicambiarpass);

        mnicerrarsesion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnicerrarsesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disconnect.png"))); // NOI18N
        mnicerrarsesion.setText("Cerrar Sesión");
        mnicerrarsesion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mnicerrarsesionItemStateChanged(evt);
            }
        });
        mnicerrarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnicerrarsesionActionPerformed(evt);
            }
        });
        mnipersonal.add(mnicerrarsesion);

        jMenuBar1.add(mnipersonal);

        mnireportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconopdf.gif"))); // NOI18N
        mnireportes.setText("Reportes");
        mnireportes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnireportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnireportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnireportes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mnireportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mniusers.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniusers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group_key.png"))); // NOI18N
        mniusers.setText("Usuarios");
        mniusers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniusersActionPerformed(evt);
            }
        });
        mnireportes.add(mniusers);

        mnirepclientes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnirepclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clientesG-32x32.png"))); // NOI18N
        mnirepclientes.setText("Clientes");
        mnirepclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnirepclientesActionPerformed(evt);
            }
        });
        mnireportes.add(mnirepclientes);

        mnirepmarcas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnirepmarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ico_bmw_0.png"))); // NOI18N
        mnirepmarcas.setText("Marcas");
        mnirepmarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnirepmarcasActionPerformed(evt);
            }
        });
        mnireportes.add(mnirepmarcas);

        mnirepdesperfectos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnirepdesperfectos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/multi.png"))); // NOI18N
        mnirepdesperfectos.setText("Desperfectos");
        mnirepdesperfectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnirepdesperfectosActionPerformed(evt);
            }
        });
        mnireportes.add(mnirepdesperfectos);

        jMenuItem3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins.png"))); // NOI18N
        jMenuItem3.setText("Diagnósticos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnireportes.add(jMenuItem3);

        jMenuItem6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/history_button.png"))); // NOI18N
        jMenuItem6.setText("Reparaciones");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnireportes.add(jMenuItem6);

        jMenuItem8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem8.setText("Insumos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        mnireportes.add(jMenuItem8);

        jMenuItem7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem7.setText("Ventas Diarias");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnireportes.add(jMenuItem7);

        jMenuItem10.setText("Ventas Mensuales");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        mnireportes.add(jMenuItem10);

        jMenuBar1.add(mnireportes);

        mniayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/help.png"))); // NOI18N
        mniayuda.setText("Ayuda");
        mniayuda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniayuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mniayuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mniayuda.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mniayuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mniacercade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniacercade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/information.png"))); // NOI18N
        mniacercade.setText("Acerca de");
        mniacercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniacercadeActionPerformed(evt);
            }
        });
        mniayuda.add(mniacercade);

        jMenuItem9.setText("jMenuItem9");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mniayuda.add(jMenuItem9);

        jMenuBar1.add(mniayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnicambiarpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnicambiarpassActionPerformed
        CambioPassword cpass = new CambioPassword(null, true);
        cpass.setVisible(true);
    }//GEN-LAST:event_mnicambiarpassActionPerformed

    private void mniacercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniacercadeActionPerformed
        Acercade acerca = new Acercade(null, true);
        acerca.setVisible(true);
    }//GEN-LAST:event_mniacercadeActionPerformed

    private void mnicerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnicerrarsesionActionPerformed
        int salir = JOptionPane.showConfirmDialog(this, "¿Realmente desea cerrar la sesión actual?", "Cerrando sesión", 0, 3);
        if (salir == JOptionPane.OK_OPTION) {
            //deshabilitar todo
            mnipersonal.setVisible(false);
            mnireportes.setVisible(false);
            PanelPrincipal.setVisible(true);
            PanelPrincipalDueño.setVisible(false);
            PanelPrincipalJefe.setVisible(false);
            PanelPrincipalMec.setVisible(false);
            PanelPrincipalSecretaria.setVisible(false);
            JOptionPane.showMessageDialog(this, "Has cerrado correctamente la sesión");
            //System.exit(0);
            this.dispose();
            Principal x = new Principal();
            x.setVisible(true);
        }
    }//GEN-LAST:event_mnicerrarsesionActionPerformed

    private void mnicerrarsesionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mnicerrarsesionItemStateChanged

    }//GEN-LAST:event_mnicerrarsesionItemStateChanged

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void mnirepdesperfectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepdesperfectosActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            JasperReport reportes = JasperCompileManager.compileReport("reportesdesp.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reportes, null, conexion/*getConexion()*/);
            JasperViewer view = new JasperViewer(print, false);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el reporte", JOptionPane.WARNING_MESSAGE);
            view.setTitle("Reporte de Desperfectos");
            view.setExtendedState(this.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepdesperfectosActionPerformed

    private void mnirepmarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepmarcasActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            JasperReport reportes = JasperCompileManager.compileReport("reportemarcas.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reportes, null, conexion/*getConexion()*/);
            JasperViewer view = new JasperViewer(print, false);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el reporte", JOptionPane.WARNING_MESSAGE);
            view.setTitle("Reporte de Marcas");
            view.setExtendedState(this.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepmarcasActionPerformed

    private void mniusersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniusersActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            JasperReport reportes = JasperCompileManager.compileReport("reporteusuarios.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reportes, null, conexion/*getConexion()*/);
            JasperViewer view = new JasperViewer(print, false);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el reporte", JOptionPane.WARNING_MESSAGE);
            view.setTitle("Reporte de Usuarios del Sistema");
            view.setExtendedState(this.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mniusersActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Privilegios del sistema
        if (Login.tipoUsuario == 1) {
            PanelPrincipal.setVisible(false);
            PanelPrincipalDueño.setVisible(true);
            PanelPrincipalJefe.setVisible(false);
            PanelPrincipalMec.setVisible(false);
            PanelPrincipalSecretaria.setVisible(false);
        }
        if (Login.tipoUsuario == 2) {
            PanelPrincipalJefe.setVisible(true);
            PanelPrincipalMec.setVisible(false);
            PanelPrincipal.setVisible(false);
            PanelPrincipalDueño.setVisible(false);
            PanelPrincipalSecretaria.setVisible(false);
        }

        if (Login.tipoUsuario == 3) {
            PanelPrincipalMec.setVisible(true);
            PanelPrincipalJefe.setVisible(false);
            PanelPrincipal.setVisible(false);
            PanelPrincipalDueño.setVisible(false);
            PanelPrincipalSecretaria.setVisible(false);
        }
        if (Login.tipoUsuario == 4) {
            PanelPrincipalSecretaria.setVisible(true);
            PanelPrincipalMec.setVisible(false);
            PanelPrincipalJefe.setVisible(false);
            PanelPrincipal.setVisible(false);
            PanelPrincipalDueño.setVisible(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void mnirepclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepclientesActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            JasperReport reportes = JasperCompileManager.compileReport("reporteclientes.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reportes, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el reporte", JOptionPane.WARNING_MESSAGE);
            view.setTitle("Reporte de Clientes");
            view.setExtendedState(this.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepclientesActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            JasperReport reportes = JasperCompileManager.compileReport("reporteinsumos.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reportes, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el reporte", JOptionPane.WARNING_MESSAGE);
            view.setTitle("Reporte de Insumos");
            view.setExtendedState(this.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void btnbdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbdActionPerformed
        BaseDatos base = new BaseDatos(null, true);
        base.setVisible(true);
    }//GEN-LAST:event_btnbdActionPerformed

    private void btnusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnusuariosActionPerformed
        if (!us.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (us.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Usuarios: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Usuarios: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(us);
            }
            us.setVisible(true);
        } else {
            System.out.println("Usuarios: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnusuariosActionPerformed

    private void btninsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsumosActionPerformed
        if (!in.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (in.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Insumos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Insumos: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(in);
            }
            in.show();
        } else {
            System.out.println("Insumos: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btninsumosActionPerformed

    private void btncviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncviActionPerformed
        if (!ccvta.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (ccvta.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Consulta Comprobante: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Consulta Comprobante: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(ccvta);
            }
            ccvta.show();
        } else {
            System.out.println("Consulta Comprobante: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btncviActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void btnvtainsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvtainsActionPerformed
        if (!cvta.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (cvta.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Comprobante: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Comprobante: No lo es, puede mostrarse");
                    ComprobanteVta.txtvendedor.setText("" + Login.Nombres + " " + Login.Apellidos);
                    ComprobanteVta.txtvendedor.setDisabledTextColor(Color.blue);
                }
            }
            if (mostrar) {
                jdpescritorio.add(cvta);
            }
            cvta.show();
        } else {
            System.out.println("Comprobante: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnvtainsActionPerformed

    private void btnreparacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreparacionesActionPerformed
        reparacion rep = new reparacion(); //crear el nuevo formulario
        boolean mostrar = true;
        for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (rep.getClass().isInstance(jdpescritorio.getComponent(a))) {
                System.out.println("Reparación Precios Desperfectos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                mostrar = false;
            } else {
                System.out.println("Reparación: No lo es, puede mostrarse");
            }
        }
        if (mostrar) {
            jdpescritorio.add(rep);
        }
        rep.show();
    }//GEN-LAST:event_btnreparacionesActionPerformed

    private void btndiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndiagnosticoActionPerformed
        if (!diag.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (diag.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Diagnóstico: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Diagnóstico: No lo es, puede mostrarse");
                    diagnostico.txtmecanico.setText("" + Login.Nombres + " " + Login.Apellidos);
                    diagnostico.txtmecanico.setDisabledTextColor(Color.blue);
                }
            }
            if (mostrar) {
                jdpescritorio.add(diag);
            }
            diag.show();
        } else {
            System.out.println("Diagnóstico: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btndiagnosticoActionPerformed

    private void btndesperfectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndesperfectosActionPerformed
        if (!d.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (d.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Desperfectos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Desperfectos: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(d);
            }
            d.show();
        } else {
            System.out.println("Desperfectos: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btndesperfectosActionPerformed

    private void btnvehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvehiculosActionPerformed
        if (!v.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (v.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Vehículos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Vehículos: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(v);
            }
            v.show();
        } else {
            System.out.println("Vehículos: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnvehiculosActionPerformed

    private void btnmarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmarcasActionPerformed
        if (!mv.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (mv.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Marcas: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Marcas: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(mv);
            }
            mv.show();
        } else {
            System.out.println("Marcas: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnmarcasActionPerformed

    private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        if (!cs.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (cs.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Clientes: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Clientes: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(cs);
            }
            cs.show();
        } else {
            System.out.println("Clientes: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnclientesActionPerformed

    private void btnusuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnusuarios1ActionPerformed
        if (!us2.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (us2.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Usuarios: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Usuarios: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(us2);
            }
            us2.show();
        } else {
            System.out.println("Usuarios: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnusuarios1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Map parametro = new HashMap();
            JasperReport reportes = JasperCompileManager.compileReport("reportevtasdiarias.jrxml");
            parametro.put("Fecha", lbfecha.getText());
            //se carga el reporte
            //se procesa el archivo jasper
            JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conexion);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        bxfecha bxf = new bxfecha(null, true);
        bxf.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void btnactestdiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactestdiagActionPerformed
        AceptacionReparaciones Arep = new AceptacionReparaciones(); //crear el nuevo formulario
        boolean mostrar = true;
        for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (Arep.getClass().isInstance(jdpescritorio.getComponent(a))) {
                System.out.println("Reparación Precios Desperfectos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                mostrar = false;
            } else {
                System.out.println("Reparación: No lo es, puede mostrarse");
            }
        }
        if (mostrar) {
            jdpescritorio.add(Arep);
        }
        Arep.show();
    }//GEN-LAST:event_btnactestdiagActionPerformed

    private void btnpagorepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpagorepActionPerformed
        PagoReparaciones Prep = new PagoReparaciones(); //crear el nuevo formulario
        boolean mostrar = true;
        for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (Prep.getClass().isInstance(jdpescritorio.getComponent(a))) {
                System.out.println("Pago Reparación Precios Desperfectos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                mostrar = false;
            } else {
                System.out.println("Pago Reparación: No lo es, puede mostrarse");
            }
        }
        if (mostrar) {
            jdpescritorio.add(Prep);
        }
        Prep.show();
    }//GEN-LAST:event_btnpagorepActionPerformed

    private void btndmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndmiActionPerformed
        if (!edmi.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (edmi.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Eliminación Diagnósticos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Eliminación Diagnósticos: No lo es, puede mostrarse");
                    edmi.txtuser.setText("" + Login.Nombres + " " + Login.Apellidos);
                    edmi.txtuser.setDisabledTextColor(Color.blue);
                }
            }
            if (mostrar) {
                jdpescritorio.add(edmi);
            }
            edmi.show();
        } else {
            System.out.println("Eliminación Diagnósticos: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btndmiActionPerformed

    private void btnconsultadiagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultadiagsActionPerformed
        if (!cdiags.isVisible()) {
            boolean mostrar = true;
            for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
                if (cdiags.getClass().isInstance(jdpescritorio.getComponent(a))) {
                    System.out.println("Consulta Diagnósticos: Esto no se volverá a mostrar porque ya está abierta la ventana");
                    mostrar = false;
                } else {
                    System.out.println("Consulta Diagnósticos: No lo es, puede mostrarse");
                }
            }
            if (mostrar) {
                jdpescritorio.add(cdiags);
            }
            cdiags.show();
        } else {
            System.out.println("Consulta Diagnósticos: Esto no se volverá a mostrar porque ya está abierta la ventana");
        }
    }//GEN-LAST:event_btnconsultadiagsActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowIconified

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*new Principal().setVisible(true);*/
                new Thread(new start()).start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.panel.PanelImage PanelPrincipal;
    private elaprendiz.gui.panel.PanelImage PanelPrincipalDueño;
    private elaprendiz.gui.panel.PanelImage PanelPrincipalJefe;
    private elaprendiz.gui.panel.PanelImage PanelPrincipalMec;
    private elaprendiz.gui.panel.PanelImage PanelPrincipalSecretaria;
    private javax.swing.JButton btnactestdiag;
    private javax.swing.JButton btnbd;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btnconsultadiags;
    private javax.swing.JButton btncvi;
    private javax.swing.JButton btndesperfectos;
    private javax.swing.JButton btndiagnostico;
    private javax.swing.JButton btndmi;
    private javax.swing.JButton btninsumos;
    private javax.swing.JButton btnmarcas;
    private javax.swing.JButton btnpagorep;
    private javax.swing.JButton btnreparaciones;
    private javax.swing.JButton btnusuarios;
    private javax.swing.JButton btnusuarios1;
    private javax.swing.JButton btnvehiculos;
    private javax.swing.JButton btnvtains;
    private elaprendiz.gui.varios.ClockDigital clockDigital1;
    private elaprendiz.gui.varios.ClockDigital clockDigital2;
    private elaprendiz.gui.varios.ClockDigital clockDigital3;
    private elaprendiz.gui.varios.ClockDigital clockDigital4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JDesktopPane jdpescritorio;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JLabel lbfecha1;
    private javax.swing.JLabel lbfecha2;
    private javax.swing.JLabel lbfecha3;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblfecha1;
    private javax.swing.JLabel lblfecha2;
    private javax.swing.JLabel lblfecha3;
    private javax.swing.JLabel lblhora;
    private javax.swing.JLabel lblhora1;
    private javax.swing.JLabel lblhora2;
    private javax.swing.JLabel lblhora3;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblimg1;
    private javax.swing.JLabel lblimg2;
    private javax.swing.JLabel lblimg3;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JLabel lbltipo1;
    private javax.swing.JLabel lbltipo2;
    private javax.swing.JLabel lbltipo3;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblusuario1;
    private javax.swing.JLabel lblusuario2;
    private javax.swing.JLabel lblusuario3;
    private javax.swing.JMenuItem mniacercade;
    private javax.swing.JMenu mniayuda;
    private javax.swing.JMenuItem mnicambiarpass;
    private javax.swing.JMenuItem mnicerrarsesion;
    private javax.swing.JMenu mnipersonal;
    private javax.swing.JMenuItem mnirepclientes;
    private javax.swing.JMenuItem mnirepdesperfectos;
    private javax.swing.JMenuItem mnirepmarcas;
    private javax.swing.JMenu mnireportes;
    private javax.swing.JMenuItem mniusers;
    private elaprendiz.gui.panel.PanelCurves panelCurves2;
    private elaprendiz.gui.panel.PanelCurves panelCurves3;
    private elaprendiz.gui.panel.PanelCurves panelCurves4;
    private elaprendiz.gui.panel.PanelCurves panelCurves6;
    private elaprendiz.gui.panel.PanelCurves panelCurves7;
    // End of variables declaration//GEN-END:variables
}
