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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

/**
 *
 * @author ZuluCorp
 */
public class EliminacionDiagnosticosMalIngresados extends javax.swing.JInternalFrame implements Runnable {

    CalculaPrecioTB calcula = new CalculaPrecioTB();
    /**
     * Creates new form reparacion
     */
    
    String hora, minutos, segundos, ampm;
    Thread h1;

    public EliminacionDiagnosticosMalIngresados() {
        initComponents();
        this.setTitle("Diagnósticos Mal Ingresados");
        this.setLocation(280, 15);
        mostrardatos();
        anchocolumnas();
        txtnum.setDisabledTextColor(Color.red);
        txtnumdiag.setDisabledTextColor(Color.red);
        txtcd.setDisabledTextColor(Color.red);
        txtconteorep.setVisible(false);
        txtconteototal.setVisible(false);
        CargarConteo();
        txtnom.setVisible(false);
        txtmail.setVisible(false);
        txtpatente.setVisible(false);
        txtfin.setText("Finalizado");
        txtfin.setVisible(false);
        fpago.setDisabledTextColor(Color.blue);
        h1 = new Thread(this);
        h1.start();
        fpago.setText(fechaactual());
        txtnumdiag.setVisible(false);
        txtcd.setVisible(false);
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatofecha.format(fecha);
    }
    
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            LbHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }


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
        sql = "SELECT ID_Diagnostico,Patente,RUTCliente,Nombres,Apellidos,Correo,Mecanico,F_ing_Diagnostico,F_Ent_Diag,Repuestos,Estado_Diag FROM controldiag cd,clientes cl WHERE cd.RUTCliente=cl.RUT AND Estado_Diag = 'Ingresado'";
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
            txtnom.setText(nombre + " " + apellido);
            txtmail.setText(correo);
            txtpatente.setText(patente);
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

    private String validaestrepauto() {
        String error = "";
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

    private void LimpiaTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        tbrep.setModel(new DefaultTableModel());
        for (int i = 0; i < tbrep.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
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
        jsp1 = new javax.swing.JScrollPane();
        tbdiag = new javax.swing.JTable();
        txtnum = new javax.swing.JTextField();
        btneliminar = new javax.swing.JButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        txtnumdiag = new javax.swing.JTextField();
        txtcd = new javax.swing.JTextField();
        jsp2 = new javax.swing.JScrollPane();
        tbrep = new javax.swing.JTable();
        txtconteorep = new javax.swing.JTextField();
        txtconteototal = new javax.swing.JTextField();
        txtnom = new javax.swing.JTextField();
        txtmail = new javax.swing.JTextField();
        txtpatente = new javax.swing.JTextField();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel4 = new javax.swing.JLabel();
        fpago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        LbHora = new javax.swing.JLabel();
        txtfin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();

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

        txtnum.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnum.setEnabled(false);
        txtnum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnumFocusLost(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
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
                .addGap(234, 234, 234)
                .addComponent(btneliminar)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnum)
                    .addComponent(btneliminar))
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
                        .addContainerGap(310, Short.MAX_VALUE))))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumdiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtconteorep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtconteototal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsp2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTranslucido3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha");

        fpago.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        fpago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fpago.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hora");

        LbHora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LbHora.setForeground(new java.awt.Color(255, 255, 255));
        LbHora.setText("Relojito");

        txtfin.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario Conectado");

        txtuser.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtuser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtuser.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(fpago, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(LbHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LbHora)
                        .addComponent(txtfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }//GEN-LAST:event_tbdiagMouseClicked

    private void tbrepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrepMouseClicked
        mostrarcoddiag();
    }//GEN-LAST:event_tbrepMouseClicked

    private void txtnumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnumFocusLost

    }//GEN-LAST:event_txtnumFocusLost
    
    private String validacampos() {
        String error = "";
        
        if (txtnum.getText().equals("")) {
            error += "Debe seleccionar un diagnóstico\n";
        }
        return error;
    }

    void limpiar() {
        txtnum.setText("");
        txtnumdiag.setText("");
        txtcd.setText("");
    }
    
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        String error = validacampos();
        if (error.equals("")) {
            try {
                int opc = JOptionPane.showConfirmDialog(this, "¿Desea proceder a la eliminación \n del diagnóstico?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opc == JOptionPane.YES_OPTION) {
                    conexion = claseConectar.ConexionConBaseDatos.getConexion();
                    //Crear consulta a las 2 tablas relacionadas con los diagnósticosa ingresados y erróneos (mal ingresados)
                    PreparedStatement pst1 = conexion.prepareStatement("DELETE FROM controldiag WHERE ID_Diagnostico='" + txtnum.getText() + "'");
                    pst1.executeUpdate();
                    PreparedStatement pst2 = conexion.prepareStatement("DELETE FROM detallediag WHERE ID_Diag='" + txtnum.getText() + "'");
                    pst2.executeUpdate();
                    if (String.valueOf(txtuser.getText()).compareTo("") == 0
                            && String.valueOf(txtnum.getText()).compareTo("") == 0) {
                        validacampos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Diagnóstico eliminado correctamente", "Ingreso de Pago de Reparación", JOptionPane.INFORMATION_MESSAGE);
                        //Limpiar
                        limpiar();
                        mostrardatos();
                        anchocolumnas();
                        LimpiaTabla();
                        //reporte pago reparacion
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }

        } else {
            JOptionPane.showMessageDialog(null, error, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    public String completarFecha(int d) {
        return d < 10 ? "0" + d : d + "";
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbHora;
    private javax.swing.JButton btneliminar;
    private javax.swing.JTextField fpago;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jsp1;
    private javax.swing.JScrollPane jsp2;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
    private javax.swing.JTable tbdiag;
    public static javax.swing.JTable tbrep;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtconteorep;
    private javax.swing.JTextField txtconteototal;
    private javax.swing.JTextField txtfin;
    private javax.swing.JTextField txtmail;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtnum;
    private javax.swing.JTextField txtnumdiag;
    private javax.swing.JTextField txtpatente;
    public static javax.swing.JTextField txtuser;
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
