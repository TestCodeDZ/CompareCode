/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

public class Principal extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lbhora.setText(hora + ":" + minutos + ":" + segundos);
            //lblHora.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
}
   
    //Metodo calcular hora     
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    //calcular fecha
    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }
    
    /**
     * Creates new form Principal
     */
    javax.swing.ImageIcon iconoConectado= new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"));
    javax.swing.ImageIcon iconoDesconectado= new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_offline.png"));
    
    public Principal() {
        super("Sistema de Gestión de Taller Mecánico");
        //Abrir ventana de validacion de usuarios
        new Login(this, true).setVisible(true);
        initComponents();
        h1 = new Thread(this);
        h1.start();
        lbfecha.setText(fechaActual());
        //centrar la pantalla
        setLocationRelativeTo(null);
        //fondos
        fondo1.setVisible(true);
        this.setSize(1000, 768);
        //this.setLocation(600,150);
         this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
               
            }

            @Override
            public void windowClosing(WindowEvent e) {
              
            int rpt=JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir del sistema?","¡Advertencia!",JOptionPane.YES_NO_OPTION);
            if(rpt==JOptionPane.YES_OPTION){
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
         //Mostramos el nombre de Usuario
        lblusuario.setText("Usuario Conectado: " + Login.nomUsuario);
        //lbnombres.setText("Nombres: " + Login.Nombres);
       // lbapellidos.setText("Apellidos: " + Login.Apellidos);
        //lbtipouser.setText("Tipo de Usuario: " + Login.tipoUsuario);
    }
    private void deshabilitarmenu(){
        this.mniproceso.setVisible(false);
        this.mniconsultas.setVisible(false);
        this.mnireportes.setVisible(false);
        this.mnisistema.setVisible(false);
        this.mnibd.setVisible(false);
        this.mnicambiarpass.setVisible(false);
        this.mnicerrarsesion.setVisible(false);
        lblimg.setIcon(iconoDesconectado);   
        lblusuario.setText(" Usuario Desconectado ");
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
        lblimg = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lbfecha = new javax.swing.JLabel();
        lbhora = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        fondo2 = new javax.swing.JLabel();
        fondo3 = new javax.swing.JLabel();
        fondo4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnipersonal = new javax.swing.JMenu();
        mnicambiarpass = new javax.swing.JMenuItem();
        mnicerrarsesion = new javax.swing.JMenuItem();
        mnisalir = new javax.swing.JMenuItem();
        mnisistema = new javax.swing.JMenu();
        mniusuarios = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        mnibd = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnif1 = new javax.swing.JMenuItem();
        mnif2 = new javax.swing.JMenuItem();
        mnif3 = new javax.swing.JMenuItem();
        mniproceso = new javax.swing.JMenu();
        mnidatos = new javax.swing.JMenu();
        mniclientes = new javax.swing.JMenuItem();
        mnimarca = new javax.swing.JMenuItem();
        mnivehiculos = new javax.swing.JMenuItem();
        mniaverias = new javax.swing.JMenu();
        mnidesperfectos = new javax.swing.JMenuItem();
        mnicpd = new javax.swing.JMenuItem();
        mniprincipal = new javax.swing.JMenu();
        mnidiag = new javax.swing.JMenuItem();
        mnirep = new javax.swing.JMenuItem();
        mnivi = new javax.swing.JMenuItem();
        mniconsultas = new javax.swing.JMenu();
        mnireportes = new javax.swing.JMenu();
        mniusers = new javax.swing.JMenuItem();
        mnirepclientes = new javax.swing.JMenuItem();
        mnirepmarcas = new javax.swing.JMenuItem();
        mnirepdesperfectos = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        mniayuda = new javax.swing.JMenu();
        mniacercade = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jdpescritorio.setBackground(new java.awt.Color(25, 153, 153));

        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/status_online.png"))); // NOI18N
        jdpescritorio.add(lblimg);
        lblimg.setBounds(10, 10, 40, 30);

        lblusuario.setBackground(new java.awt.Color(0, 153, 153));
        lblusuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(0, 102, 102));
        lblusuario.setText("Usuario Conectado: ");
        jdpescritorio.add(lblusuario);
        lblusuario.setBounds(60, 10, 260, 17);

        lblhora.setBackground(new java.awt.Color(0, 153, 153));
        lblhora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblhora.setForeground(new java.awt.Color(0, 102, 102));
        lblhora.setText("Hora:");
        jdpescritorio.add(lblhora);
        lblhora.setBounds(580, 10, 50, 17);

        lblfecha.setBackground(new java.awt.Color(0, 153, 153));
        lblfecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(0, 102, 102));
        lblfecha.setText("Fecha:");
        jdpescritorio.add(lblfecha);
        lblfecha.setBounds(350, 10, 60, 17);

        lbfecha.setBackground(new java.awt.Color(0, 153, 153));
        lbfecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbfecha.setForeground(new java.awt.Color(0, 102, 102));
        lbfecha.setText("jLabel1");
        jdpescritorio.add(lbfecha);
        lbfecha.setBounds(420, 10, 80, 17);

        lbhora.setBackground(new java.awt.Color(0, 153, 153));
        lbhora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbhora.setForeground(new java.awt.Color(0, 102, 102));
        lbhora.setText("hola");
        jdpescritorio.add(lbhora);
        lbhora.setBounds(650, 10, 80, 14);

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/fondoapp.jpg"))); // NOI18N
        jdpescritorio.add(fondo1);
        fondo1.setBounds(0, 0, 1366, 911);

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/audi-rs5-en-carretera-2125_3.jpg"))); // NOI18N
        jdpescritorio.add(fondo2);
        fondo2.setBounds(0, 0, 1505, 950);

        fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/audi-a3-coupe-wallpaper-1920x1080-1011076-002.jpg"))); // NOI18N
        jdpescritorio.add(fondo3);
        fondo3.setBounds(0, 0, 1600, 900);

        fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageVentana/audi-s5-rear-angle-1920x1080-wallpaper-1377.jpg"))); // NOI18N
        jdpescritorio.add(fondo4);
        fondo4.setBounds(0, 0, 1024, 576);

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

        mnisalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnisalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir1.JPG"))); // NOI18N
        mnisalir.setText("Salir de la Aplicación");
        mnisalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnisalirActionPerformed(evt);
            }
        });
        mnipersonal.add(mnisalir);

        jMenuBar1.add(mnipersonal);

        mnisistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sistema.png"))); // NOI18N
        mnisistema.setText("Sistema");
        mnisistema.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnisistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnisistema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnisistema.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mnisistema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mniusuarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agente.png"))); // NOI18N
        mniusuarios.setText("Usuarios");
        mniusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniusuariosActionPerformed(evt);
            }
        });
        mnisistema.add(mniusuarios);

        jMenuItem7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem7.setText("Insumos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnisistema.add(jMenuItem7);

        mnibd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnibd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/copia-seguridad.png"))); // NOI18N
        mnibd.setText("Base de Datos");
        mnibd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnibdActionPerformed(evt);
            }
        });
        mnisistema.add(mnibd);

        jMenuBar1.add(mnisistema);

        jMenu1.setText("Fondo");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        mnif1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnif1.setText("Fondo 1");
        mnif1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnif1ActionPerformed(evt);
            }
        });
        jMenu1.add(mnif1);

        mnif2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnif2.setText("Fondo 2");
        mnif2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnif2ActionPerformed(evt);
            }
        });
        jMenu1.add(mnif2);

        mnif3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnif3.setText("Fondo 3");
        mnif3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnif3ActionPerformed(evt);
            }
        });
        jMenu1.add(mnif3);

        jMenuBar1.add(jMenu1);

        mniproceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/procesos.png"))); // NOI18N
        mniproceso.setText("Proceso");
        mniproceso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniproceso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mniproceso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mniproceso.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mniproceso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnidatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/application_form.png"))); // NOI18N
        mnidatos.setText("Datos");
        mnidatos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        mniclientes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clientesG-32x32.png"))); // NOI18N
        mniclientes.setText("Clientes");
        mniclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniclientesActionPerformed(evt);
            }
        });
        mnidatos.add(mniclientes);

        mnimarca.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnimarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ico_bmw_0.png"))); // NOI18N
        mnimarca.setText("Marca");
        mnimarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnimarcaActionPerformed(evt);
            }
        });
        mnidatos.add(mnimarca);

        mnivehiculos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnivehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/92341_steering_wheel-512.png"))); // NOI18N
        mnivehiculos.setText("Vehículos");
        mnivehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnivehiculosActionPerformed(evt);
            }
        });
        mnidatos.add(mnivehiculos);

        mniproceso.add(mnidatos);

        mniaverias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/multi.png"))); // NOI18N
        mniaverias.setText("Averías");
        mniaverias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        mnidesperfectos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnidesperfectos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/multi.png"))); // NOI18N
        mnidesperfectos.setText("Desperfectos");
        mnidesperfectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnidesperfectosActionPerformed(evt);
            }
        });
        mniaverias.add(mnidesperfectos);

        mnicpd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnicpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/money_dollar.png"))); // NOI18N
        mnicpd.setText("Cambio Precios Desperfectos");
        mnicpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnicpdActionPerformed(evt);
            }
        });
        mniaverias.add(mnicpd);

        mniproceso.add(mniaverias);

        mniprincipal.setText("Principal");
        mniprincipal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        mnidiag.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnidiag.setText("Diagnóstico");
        mnidiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnidiagActionPerformed(evt);
            }
        });
        mniprincipal.add(mnidiag);

        mnirep.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnirep.setText("Reparaciones");
        mnirep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnirepActionPerformed(evt);
            }
        });
        mniprincipal.add(mnirep);

        mnivi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnivi.setText("Venta Insumos");
        mnivi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniviActionPerformed(evt);
            }
        });
        mniprincipal.add(mnivi);

        mniproceso.add(mniprincipal);

        jMenuBar1.add(mniproceso);

        mniconsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/consultas.JPG"))); // NOI18N
        mniconsultas.setText("Consultas");
        mniconsultas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mniconsultas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mniconsultas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mniconsultas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mniconsultas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar1.add(mniconsultas);

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
        jMenuItem3.setText("Cotizaciones");
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

        jMenuBar1.add(mniayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
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

    private void mnisalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnisalirActionPerformed
        int salir = JOptionPane.showConfirmDialog(this, "¿Realmente desea cerrar la aplicación?","Cerrando programa",0,3);
        if(salir==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_mnisalirActionPerformed

    private void mniusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniusuariosActionPerformed
        UsuariosSistema us= new UsuariosSistema();
        jdpescritorio.add(us);
        us.show();
    }//GEN-LAST:event_mniusuariosActionPerformed

    private void mniclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniclientesActionPerformed
        ClientesSistema cs= new ClientesSistema();
        jdpescritorio.add(cs);
        cs.show();
    }//GEN-LAST:event_mniclientesActionPerformed

    private void mnicerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnicerrarsesionActionPerformed
        this.dispose();
        JOptionPane.showMessageDialog(this, "Has salido del sistema");
        
        Login log = new Login(null, true);
        log.setVisible(true);
      
        
        
    }//GEN-LAST:event_mnicerrarsesionActionPerformed

    private void mnicerrarsesionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mnicerrarsesionItemStateChanged
        
    }//GEN-LAST:event_mnicerrarsesionItemStateChanged

    private void mnibdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnibdActionPerformed
        BaseDatos base = new BaseDatos(null, true);
        base.setVisible(true);
    }//GEN-LAST:event_mnibdActionPerformed

    private void mnimarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnimarcaActionPerformed
        MarcaVehiculo mv= new  MarcaVehiculo();
        jdpescritorio.add(mv);
        mv.show();
    }//GEN-LAST:event_mnimarcaActionPerformed

    private void mnidesperfectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnidesperfectosActionPerformed
        Desperfectos d= new  Desperfectos();
        jdpescritorio.add(d);
        d.show();
    }//GEN-LAST:event_mnidesperfectosActionPerformed

    private void mnicpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnicpdActionPerformed
        CPD cpd=new CPD();
        jdpescritorio.add(cpd);
        cpd.show();
    }//GEN-LAST:event_mnicpdActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void mnirepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepActionPerformed
        reparacion rep=new reparacion();
        jdpescritorio.add(rep);
        rep.show();
    }//GEN-LAST:event_mnirepActionPerformed

    private void mnirepdesperfectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepdesperfectosActionPerformed
       try {
            conectar cc= new conectar();
            
            JasperReport reportes=JasperCompileManager.compileReport("reportesdesp.jrxml");
            JasperPrint print=JasperFillManager.fillReport(reportes, null,cc.conexion());
            JasperViewer.viewReport(print,false);
            
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepdesperfectosActionPerformed

    private void mnirepmarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepmarcasActionPerformed
        try {
            conectar cc= new conectar();
            
            JasperReport reportes=JasperCompileManager.compileReport("reportemarcas.jrxml");
            JasperPrint print=JasperFillManager.fillReport(reportes, null,cc.conexion());
            JasperViewer.viewReport(print,false);
            
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepmarcasActionPerformed

    private void mniusersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniusersActionPerformed
        try {
            conectar cc= new conectar();
            
            JasperReport reportes=JasperCompileManager.compileReport("reporteempleados.jrxml");
            JasperPrint print=JasperFillManager.fillReport(reportes, null,cc.conexion());
            JasperViewer.viewReport(print,false);
            
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mniusersActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Privilegios del sistema
         if (Login.tipoUsuario==1){
            mniproceso.setVisible(false);
        }
        if (Login.tipoUsuario==2) {
            mnibd.setVisible(false);
            mnireportes.setVisible(false);
            mniprincipal.setVisible(false);
        }       
        
        if (Login.tipoUsuario==3) {
            mnidatos.setVisible(false);
            mnireportes.setVisible(false);
            mniaverias.setVisible(false);
            mnisistema.setVisible(false);
            mniconsultas.setVisible(false);
            mnireportes.setVisible(false);
        }
        if (Login.tipoUsuario==4) {
            mnisistema.setVisible(false);
            mniproceso.setVisible(false);
            mniconsultas.setVisible(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void mnidiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnidiagActionPerformed
        diagnostico diag=new diagnostico();
        jdpescritorio.add(diag);
        diag.show();
    }//GEN-LAST:event_mnidiagActionPerformed

    private void mnirepclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnirepclientesActionPerformed
          try {
            conectar cc= new conectar();
            JasperReport reportes=JasperCompileManager.compileReport("reporteclientes.jrxml");
            JasperPrint print=JasperFillManager.fillReport(reportes, null,cc.conexion());
            JasperViewer.viewReport(print,false);
            
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }//GEN-LAST:event_mnirepclientesActionPerformed

    private void mnivehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnivehiculosActionPerformed
        // TODO add your handling code here:
        vehiculos vehi= new  vehiculos();
        jdpescritorio.add(vehi);
        vehi.show();
    }//GEN-LAST:event_mnivehiculosActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Insumos in= new  Insumos();
        jdpescritorio.add(in);
        in.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void mnif1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnif1ActionPerformed
        fondo1.setVisible(true);
        fondo2.setVisible(false);
        fondo3.setVisible(false);
    }//GEN-LAST:event_mnif1ActionPerformed

    private void mnif2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnif2ActionPerformed
        fondo1.setVisible(false);
        fondo2.setVisible(true);
        fondo3.setVisible(false);
    }//GEN-LAST:event_mnif2ActionPerformed

    private void mnif3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnif3ActionPerformed
        fondo1.setVisible(false);
        fondo2.setVisible(false);
        fondo3.setVisible(true);
    }//GEN-LAST:event_mnif3ActionPerformed

    private void mniviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniviActionPerformed
        ComprobanteVta cvta= new  ComprobanteVta();
        jdpescritorio.add(cvta);
        cvta.show();
    }//GEN-LAST:event_mniviActionPerformed

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
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel fondo3;
    private javax.swing.JLabel fondo4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    public static javax.swing.JDesktopPane jdpescritorio;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JLabel lbhora;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblhora;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JMenuItem mniacercade;
    private javax.swing.JMenu mniaverias;
    private javax.swing.JMenu mniayuda;
    private javax.swing.JMenuItem mnibd;
    private javax.swing.JMenuItem mnicambiarpass;
    private javax.swing.JMenuItem mnicerrarsesion;
    private javax.swing.JMenuItem mniclientes;
    private javax.swing.JMenu mniconsultas;
    private javax.swing.JMenuItem mnicpd;
    private javax.swing.JMenu mnidatos;
    private javax.swing.JMenuItem mnidesperfectos;
    private javax.swing.JMenuItem mnidiag;
    private javax.swing.JMenuItem mnif1;
    private javax.swing.JMenuItem mnif2;
    private javax.swing.JMenuItem mnif3;
    private javax.swing.JMenuItem mnimarca;
    private javax.swing.JMenu mnipersonal;
    private javax.swing.JMenu mniprincipal;
    private javax.swing.JMenu mniproceso;
    private javax.swing.JMenuItem mnirep;
    private javax.swing.JMenuItem mnirepclientes;
    private javax.swing.JMenuItem mnirepdesperfectos;
    private javax.swing.JMenuItem mnirepmarcas;
    private javax.swing.JMenu mnireportes;
    private javax.swing.JMenuItem mnisalir;
    private javax.swing.JMenu mnisistema;
    private javax.swing.JMenuItem mniusers;
    private javax.swing.JMenuItem mniusuarios;
    private javax.swing.JMenuItem mnivehiculos;
    private javax.swing.JMenuItem mnivi;
    // End of variables declaration//GEN-END:variables
}
/*ñeeee
        private String horas, minutos, segundos;
        private boolean Estado;
        Thread Hilo;

        public void run(){
        while (Estado ==true) {
        Calendar fecha = new GregorianCalendar();
        int h= fecha.get(Calendar.HOUR_OF_DAY);
        int m = fecha.get(Calendar.MINUTE);
        int s = fecha.get(Calendar.SECOND);

        horas = Integer.toString(h);
        minutos = Integer.toString(m);
        segundos = Integer.toString(s);

        lbhora.setText(horas+" : "+minutos+" : "+segundos);

        try{
        Thread.sleep(1000);
        }catch (InterruptedException ex){
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }

        public void Iniciar(){
        Hilo=new Thread(this);
        Estado = true;
        Hilo.start();

        }

        public void stop(){
        Estado = false;
        }

        

*/
