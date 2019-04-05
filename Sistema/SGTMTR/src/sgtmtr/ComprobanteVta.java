/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class ComprobanteVta extends javax.swing.JInternalFrame {
CalculaPrecioTB calcula = new CalculaPrecioTB();
    /**
     * Creates new form ComprobanteVta
     */
    public ComprobanteVta() {
        initComponents();
        setTitle("Comprobante de Venta de Insumos");
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
        txtvendedor.setText("" + Login.nomUsuario);
        txtvendedor.setDisabledTextColor(Color.blue);
        txtmtotal.setDisabledTextColor(Color.red);
        numeros();
    }
    void numeros() {
        String c = "";
        String SQL = "select max(NumComp) from detallecomprobante";
        //String SQL="select count(*) from boleta";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
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
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                cap = rs.getString(4);
            }

        } catch (Exception e) {
        }
        desfinal = Integer.parseInt(cap) - des;
        String modi = "UPDATE insumos SET Stock='" + desfinal + "' WHERE Codigo = '" + codi + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    void comprobantevta() {
        String InsertarSQL = "INSERT INTO comprobante(Numero,Cliente,Total,Fecha,Vendedor,Sucursal) VALUES (?,?,?,?,?,?)";
        String numcomp = txtnumcomp.getText();
        String rutcli = txtrut.getText();
        String total = txtmtotal.getText();
        String fecha = txtfecha.getText();
        String vendedor = txtvendedor.getText();
        String sucursal = txtsucursal.getText();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1, numcomp);
            pst.setString(2, rutcli);
            pst.setString(3, total);
            pst.setString(4, fecha);
            pst.setString(5, vendedor);
            pst.setString(6, sucursal);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se ha generado el comprobante de venta número "+numcomp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprobanteVta.class.getName()).log(Level.SEVERE, null, ex);
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
                PreparedStatement pst = cn.prepareStatement(Inserta);
                pst.setString(1, numcomp);
                pst.setString(2, codpro);
                pst.setString(3, descprod);
                pst.setString(4, cantidad);
                pst.setString(5, preunit);
                pst.setString(6, pretotal);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ComprobanteVta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private String validarColVacias() {
        
        String errores="";
        
        
        return errores;       
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtrut = new javax.swing.JTextField();
        txtnombrecomp = new javax.swing.JTextField();
        txtfono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        btejecuta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btbuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtnumcomp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbvprod = new javax.swing.JTable();
        txtmtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtsucursal = new javax.swing.JTextField();
        btcalculo = new javax.swing.JButton();
        btanular = new javax.swing.JButton();
        btvender = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txthora = new elaprendiz.gui.varios.ClockDigital();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Cliente", 0, 0, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("RUT");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Sr (a)");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Fono");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Dirección");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("E-Mail");

        txtrut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrut.setEnabled(false);

        txtnombrecomp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombrecomp.setEnabled(false);

        txtfono.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfono.setEnabled(false);

        txtdireccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdireccion.setEnabled(false);
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtemail.setEnabled(false);

        btejecuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eject.png"))); // NOI18N
        btejecuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btejecutaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Productos");

        btbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbuscar.setText("Buscar");
        btbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtfono)
                        .addGap(110, 110, 110))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtrut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btejecuta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(btbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(38, 38, 38)
                                .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnombrecomp, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btejecuta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombrecomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(btbuscar))))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Comprobante N°");

        txtnumcomp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumcomp.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Fecha");

        txtfecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfecha.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tbvprod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        /*deshabilitar movimiento de columnas
        JTable tbvprod = new JTable();
        tbvprod.getTableHeader().setReorderingAllowed(false);*/
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

        txtmtotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmtotal.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Monto Total $");

        jLabel12.setText("Sucursal");

        txtsucursal.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btcalculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btcalculo.setText("Calcular Precios");
        btcalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcalculoActionPerformed(evt);
            }
        });

        btanular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btanular.setText("Anular Venta");
        btanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btanularActionPerformed(evt);
            }
        });

        btvender.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btvender.setText("Realizar Venta");
        btvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btvenderActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Vendedor");

        txtvendedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtvendedor.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Hora");

        txthora.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btcalculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btanular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btvender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btcalculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btanular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btvender)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btejecutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btejecutaActionPerformed
        bcliente2 cli = new bcliente2();
        Principal.jdpescritorio.add(cli);
        cli.toFront();
        cli.setVisible(true);
    }//GEN-LAST:event_btejecutaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        SeleccionProducto sp = new SeleccionProducto(null, true);
        sp.setVisible(true);
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btcalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcalculoActionPerformed
        if (tbvprod.getRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Ingrese algún producto");
        } else {
            calcula.calcular();
        }
    }//GEN-LAST:event_btcalculoActionPerformed

    private void btanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btanularActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbvprod.getModel();
        int fila = tbvprod.getSelectedRow();
        if (fila >= 0) {
            model.removeRow(fila);
            calcula.calcular();
        } else {
            JOptionPane.showMessageDialog(null, "Tabla vacia o no seleccione ninguna fila");
        }
    }//GEN-LAST:event_btanularActionPerformed

    private void btvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btvenderActionPerformed
        if ((txtrut.getText().equals("")) || (txtmtotal.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Seleccione al cliente, producto o realice operación");
        } else {
            String capcod = "", capcan = "";
            for (int i = 0; i < ComprobanteVta.tbvprod.getRowCount(); i++) {
                capcod = ComprobanteVta.tbvprod.getValueAt(i, 0).toString();
                capcan = ComprobanteVta.tbvprod.getValueAt(i, 2).toString();
                descuentastock(capcod, capcan);
            }
            comprobantevta();
            detallecomprobante();
            txtrut.setText("");
            txtnombrecomp.setText("");
            txtfono.setText("");
            txtdireccion.setText("");
            txtemail.setText("");
            txtmtotal.setText("");

            DefaultTableModel modelo = (DefaultTableModel) tbvprod.getModel();
            int a = tbvprod.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }
            numeros();
        }
    }//GEN-LAST:event_btvenderActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btanular;
    private javax.swing.JButton btbuscar;
    private javax.swing.JButton btcalculo;
    private javax.swing.JButton btejecuta;
    private javax.swing.JButton btvender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jsp;
    public static javax.swing.JTable tbvprod;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtfono;
    private elaprendiz.gui.varios.ClockDigital txthora;
    public static javax.swing.JTextField txtmtotal;
    public static javax.swing.JTextField txtnombrecomp;
    private javax.swing.JTextField txtnumcomp;
    public static javax.swing.JTextField txtrut;
    private javax.swing.JTextField txtsucursal;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();
}
