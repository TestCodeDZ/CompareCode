/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class ComprobanteVta extends javax.swing.JInternalFrame implements Runnable {

    ValidarCaracteres validarLetras = new ValidarCaracteres();
    CalculaPrecioTB calcula = new CalculaPrecioTB();
    /**
     * Creates new form ComprobanteVta
     */
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public ComprobanteVta() {
        initComponents();
        setTitle("Venta de Insumos");
        this.setLocation(280, 15);
        txtsucursal.setText("Casa Matriz");
        txtsucursal.setDisabledTextColor(Color.blue);
        txtnumcomp.setDisabledTextColor(Color.red);
        txtrut.setDisabledTextColor(Color.blue);
        txtnombrecomp.setDisabledTextColor(Color.blue);
        txtfono.setDisabledTextColor(Color.blue);
        txtdireccion.setDisabledTextColor(Color.blue);
        txtemail.setDisabledTextColor(Color.blue);
        txtfecha.setText(fechaactual());
        txtfecha.setDisabledTextColor(Color.blue);
        txtmtotal.setDisabledTextColor(Color.red);
        txtvuelto.setDisabledTextColor(Color.red);
        CargarComboMP();
        numeros();
        h1 = new Thread(this);
        h1.start();
        txtmtotal.setText("0");
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
    
    private void CargarComboMP() {
        //Carga de Combo
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear Consulta
            Statement st = conexion.createStatement();
            String sql = "SELECT Desc_MP FROM modopago";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbmp.setModel(new DefaultComboBoxModel());
            cbmp.addItem("Seleccione Medio de Pago");
            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cbmp.addItem(rs.getObject(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void numeros() {
        String c = "";
        String SQL = "select max(NumComp) from detallecomprobante";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtnumcomp.setText("00000001");
            } else {
                int j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                txtnumcomp.setText(gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(diagnostico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatofecha.format(fecha);
    }

    void descuentastock(String codi, String can) {
        int des = Integer.parseInt(can);
        String cap = "";
        int desfinal;
        String consul = "SELECT * FROM insumos WHERE  Codigo='" + codi + "'";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                cap = rs.getString(4);
            }

        } catch (Exception e) {
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
        desfinal = Integer.parseInt(cap) - des;
        String modi = "UPDATE insumos SET Stock='" + desfinal + "' WHERE Codigo = '" + codi + "'";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            PreparedStatement pst = conexion.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void comprobantevta() {
        /*String InsertarSQL = "INSERT INTO comprobante(Numero,Cliente,Total,PagadoCon,Vuelto,Fecha,Hora,Vendedor,Sucursal) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String numcomp = txtnumcomp.getText();
        String rutcli = txtrut.getText();
        Object mp = cbmp.getSelectedItem();
        String total = txtmtotal.getText();
        String pc = txtpagado.getText();
        String dev = txtvuelto.getText();
        String fecha = txtfecha.getText();
        String hora = LbHora.getText();
        String vendedor = txtvendedor.getText();
        String sucursal = txtsucursal.getText();*/
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            //Crear consulta
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO comprobante (Numero,Cliente,ModoPago,Total,PagadoCon,Vuelto,Fecha,Hora,Vendedor,Sucursal)"
                    + "VALUES('" + txtnumcomp.getText() + "','" + txtrut.getText() + "','" + cbmp.getSelectedItem() + "',"
                    + "'" + txtmtotal.getText() + "','" + txtpagado.getText() + "',"
                    + "'" + txtvuelto.getText() + "','" + txtfecha.getText() + "',"
                    + "'" + LbHora.getText() + "','" + txtvendedor.getText() + "',"
                    + "'" + txtsucursal.getText() + "')";
            //Ejecutar la consulta
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se ha generado el comprobante de venta número " + txtnumcomp.getText());
            /*PreparedStatement pst = conexion.prepareStatement(InsertarSQL);
            pst.setString(1, numcomp);
            pst.setString(2, rutcli);
            pst.setString(3, (String) mp);
            pst.setString(4, total);
            pst.setString(5, pc);
            pst.setString(6, dev);
            pst.setString(7, fecha);
            pst.setString(8, hora);
            pst.setString(9, vendedor);
            pst.setString(10, sucursal);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se ha generado el comprobante de venta número " + numcomp);
            }*/

        } catch (SQLException ex) {
            Logger.getLogger(ComprobanteVta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }

    void detallecomprobante() {
        //ver columnas vacias al momento de intentar vender mas de 1 producto para que el sistema lo valide que esta vacio asi impide ingresarlo
        for (int i = 0; i < tbvprod.getRowCount(); i++) {
            String Inserta = "INSERT INTO detallecomprobante(NumComp,CodPro,DescProducto,Cantidad,PrecioUnitario,PrecioTotal) VALUES (?,?,?,?,?,?)";
            String numcomp = txtnumcomp.getText();
            String codpro = tbvprod.getValueAt(i, 0).toString();
            String descprod = tbvprod.getValueAt(i, 1).toString();
            String cantidad = tbvprod.getValueAt(i, 2).toString();
            String preunit = tbvprod.getValueAt(i, 3).toString();
            String pretotal = tbvprod.getValueAt(i, 4).toString();
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                PreparedStatement pst = conexion.prepareStatement(Inserta);
                pst.setString(1, numcomp);
                pst.setString(2, codpro);
                pst.setString(3, descprod);
                pst.setString(4, cantidad);
                pst.setString(5, preunit);
                pst.setString(6, pretotal);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ComprobanteVta.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        txtnumcomp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LbHora = new javax.swing.JLabel();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel1 = new javax.swing.JLabel();
        txtrut = new javax.swing.JTextField();
        txtfono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btejecuta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtnombrecomp = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btbuscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        panelTranslucido3 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbvprod = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtsucursal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbmp = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtmtotal = new javax.swing.JTextField();
        txtpagado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtvuelto = new javax.swing.JTextField();
        btanular = new javax.swing.JButton();
        btvender = new javax.swing.JButton();

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Comprobante N°");

        txtnumcomp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumcomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnumcomp.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha");

        txtfecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfecha.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vendedor");

        txtvendedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtvendedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtvendedor.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora");

        LbHora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LbHora.setForeground(new java.awt.Color(255, 255, 255));
        LbHora.setText("Relojito");

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(LbHora)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(LbHora))
                .addContainerGap())
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        panelTranslucido2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RUT");

        txtrut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrut.setEnabled(false);

        txtfono.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfono.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fono");

        btejecuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sr (a)");

        txtnombrecomp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombrecomp.setEnabled(false);

        txtdireccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdireccion.setEnabled(false);
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dirección");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Productos");

        btbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1368267893_shipping.png"))); // NOI18N
        btbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("E-Mail");

        txtemail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtemail.setEnabled(false);

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtrut, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(txtfono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btejecuta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(btbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE))
                            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtdireccion)
                                .addGap(36, 36, 36)))
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(44, 44, 44)
                        .addComponent(txtnombrecomp, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249))))
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btejecuta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnombrecomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btbuscar)))
        );

        panelTranslucido3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de la Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbvprod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbvprod = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbvprod.setSelectionBackground(Color.LIGHT_GRAY);
        tbvprod.setSelectionForeground(Color.blue);
        tbvprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Producto", "Descripción", "Cantidad", "Precio Unitario", "Precio Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbvprod.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbvprod.getTableHeader().setResizingAllowed(false);
        tbvprod.getTableHeader().setReorderingAllowed(false);
        jsp.setViewportView(tbvprod);
        if (tbvprod.getColumnModel().getColumnCount() > 0) {
            tbvprod.getColumnModel().getColumn(0).setResizable(false);
            tbvprod.getColumnModel().getColumn(1).setResizable(false);
            tbvprod.getColumnModel().getColumn(2).setResizable(false);
            tbvprod.getColumnModel().getColumn(3).setResizable(false);
            tbvprod.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Sucursal");

        txtsucursal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtsucursal.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Forma de Pago");

        cbmp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbmp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbmp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmpItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Monto Total $");

        txtmtotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmtotal.setEnabled(false);

        txtpagado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpagado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtpagado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpagadoFocusLost(evt);
            }
        });
        txtpagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpagadoKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Pagado con $");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Vuelto $");

        txtvuelto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtvuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtvuelto.setEnabled(false);

        btanular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btanular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btanular.setText("Eliminar Producto");
        btanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btanularActionPerformed(evt);
            }
        });

        btvender.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btvender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realizavta.jpg"))); // NOI18N
        btvender.setText("Realizar Venta");
        btvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btvenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido3Layout = new javax.swing.GroupLayout(panelTranslucido3);
        panelTranslucido3.setLayout(panelTranslucido3Layout);
        panelTranslucido3Layout.setHorizontalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtmtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtvuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtpagado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btanular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btvender, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        panelTranslucido3Layout.setVerticalGroup(
            panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btanular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btvender)))
                .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTranslucido3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel11)))
                        .addGap(12, 12, 12)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(txtpagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(panelTranslucido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtvuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btejecutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutaActionPerformed
        busquedaclientevtains bcvi = new busquedaclientevtains(null, true);
        bcvi.setVisible(true);
        /*bcliente2 cli = new bcliente2(); //crear el nuevo formulario
        boolean mostrar = true;
        for (int a = 0; a < jdpescritorio.getComponentCount(); a++) { // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (cli.getClass().isInstance(jdpescritorio.getComponent(a))) {
                System.out.println("Selección Cliente: Esto no se volverá a mostrar porque ya está abierta la ventana");
                mostrar = false;
            } else {
                System.out.println("Selección Cliente: No lo es, puede mostrarse");
            }
        }
        if (mostrar) {
            jdpescritorio.add(cli);
        }
        cli.show();
        cli.toFront();*/
    }//GEN-LAST:event_btejecutaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        SeleccionProducto sp = new SeleccionProducto(null, true);
        sp.setVisible(true);
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btanularActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere eliminar \n el producto de la venta actual?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opc == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tbvprod.getModel();
            int fila = tbvprod.getSelectedRow();
            if (fila >= 0) {
                model.removeRow(fila);
                calcula.calcular();
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila para eliminar");
            }
        }
    }//GEN-LAST:event_btanularActionPerformed
    public void generapdf() {
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Map parametro = new HashMap();
            JasperReport reportes = JasperCompileManager.compileReport("reporteevtains.jrxml");
            parametro.put("num", txtnumcomp.getText());
            //se carga el reporte
            //se procesa el archivo jasper
            JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conexion);
            JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta", JOptionPane.WARNING_MESSAGE);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }

    private String validarVacios() {
        String errores = "";
        if (txtrut.getText().equals("")) {
            errores += "Por favor busque los datos del cliente\n";
        }
        if (txtmtotal.getText().equals("")) {
            errores += "Calcule el monto total \n";
        }
        if (txtmtotal.getText().equals("0")) {
            errores += "Revise el detalle de la venta \n";
        }
        if (txtpagado.getText().trim().isEmpty()) {
            errores += "Ingrese el valor que el cliente pagó\n";
        }
        if (txtvuelto.getText().trim().isEmpty()) {
            errores += "Por favor calcule el vuelto \n";
        }
        Integer indice = cbmp.getSelectedIndex();
        if (indice.equals(0))
        {
            errores += "Seleccione un modo de pago \n";
        }
        return errores;
    }
    private void btvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btvenderActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                int opc = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere \n realizar esta venta?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opc == JOptionPane.YES_OPTION) {
                    String capcod = "", capcan = "";
                    for (int i = 0; i < ComprobanteVta.tbvprod.getRowCount(); i++) {
                        capcod = ComprobanteVta.tbvprod.getValueAt(i, 0).toString();
                        capcan = ComprobanteVta.tbvprod.getValueAt(i, 2).toString();
                        descuentastock(capcod, capcan);
                    }
                    comprobantevta();
                    detallecomprobante();
                    generapdf();
                    txtrut.setText("");
                    txtnombrecomp.setText("");
                    txtfono.setText("");
                    txtdireccion.setText("");
                    txtemail.setText("");
                    txtmtotal.setText("");
                    txtpagado.setText("");
                    txtvuelto.setText("");
                    cbmp.setSelectedIndex(0);
                    DefaultTableModel modelo = (DefaultTableModel) tbvprod.getModel();
                    int a = tbvprod.getRowCount() - 1;
                    int i;
                    for (i = a; i >= 0; i--) {
                        modelo.removeRow(i);
                    }
                    numeros();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btvenderActionPerformed

    private void calculavuelto() {
       try {
            int vs = Integer.parseInt(txtmtotal.getText());
            int pagado = Integer.parseInt(txtpagado.getText());
            int diferencia = pagado - vs;
            String vuelto = Integer.toString(diferencia);
            if (vs > pagado) {
                JOptionPane.showMessageDialog(this, "El vuelto no puede ser valor negativo" + JOptionPane.INFORMATION_MESSAGE);
                txtvuelto.setText("");
                txtpagado.setText("");
            } else {
                txtvuelto.setText(vuelto);
            }
        } catch (Exception e) {
            
        }
//             try {
//        } catch (NumberFormatException ex) {
//            //JOptionPane.showMessageDialog(null, ex);
//            Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    private void txtpagadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpagadoFocusLost
        if (txtpagado.equals("")) {
            txtpagado.setText("0");
        }
        calculavuelto();
    }//GEN-LAST:event_txtpagadoFocusLost

    private void txtpagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagadoKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtpagado.getText().length() == 7) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtpagadoKeyTyped

    private void cbmpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmpItemStateChanged
        Integer indice = cbmp.getSelectedIndex();
        if (indice.equals(1)) {
            txtpagado.setEnabled(false);
            txtpagado.setText(txtmtotal.getText());
            calculavuelto();
        } else if (indice.equals(2)) {
            txtpagado.setEnabled(true);
            txtpagado.requestFocus();
            txtpagado.setText("0");
        } else if (indice.equals(3)) {
            txtpagado.setEnabled(false);
            txtpagado.setText(txtmtotal.getText());
            calculavuelto();
        }
    }//GEN-LAST:event_cbmpItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbHora;
    private javax.swing.JButton btanular;
    private javax.swing.JButton btbuscar;
    private javax.swing.JButton btejecuta;
    private javax.swing.JButton btvender;
    private javax.swing.JComboBox cbmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jsp;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido3;
    public static javax.swing.JTable tbvprod;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtfono;
    public static javax.swing.JTextField txtmtotal;
    public static javax.swing.JTextField txtnombrecomp;
    private javax.swing.JTextField txtnumcomp;
    private javax.swing.JTextField txtpagado;
    public static javax.swing.JTextField txtrut;
    private javax.swing.JTextField txtsucursal;
    public static javax.swing.JTextField txtvendedor;
    private javax.swing.JTextField txtvuelto;
    // End of variables declaration//GEN-END:variables
}
