/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Calendar;
/**
 *
 * @author ZuluCorp
 */
public class ConsultaComprobantes extends javax.swing.JInternalFrame {
ValidarCaracteres validarLetras = new ValidarCaracteres();
DateFormat df = DateFormat.getDateInstance();
    /**
     * Creates new form ConsultaComprobantes
     */
    public ConsultaComprobantes() {
        initComponents();
        //CalendarioVta.setEnabled(false);
        this.setTitle("Consulta y detalle de comprobante de Venta");
        this.setLocation(280,0);
        this.mostrardatos();
        this.anchocolumnas();
        txtcomp.setDisabledTextColor(Color.red);
        txtrutcte.setDisabledTextColor(Color.blue);
        txtfecha.setDisabledTextColor(Color.blue);
        txthora.setDisabledTextColor(Color.blue);
        txtvendedor.setDisabledTextColor(Color.blue);
        txttotal.setDisabledTextColor(Color.red);
        mniVerDetalle.setVisible(false);
        bfecha.getDateEditor().setEnabled(false);
    }
    
    private void LimpiaTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        tbdetvtains.setModel(new DefaultTableModel());
        for (int i = 0; i < tbdetvtains.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    void mostrardatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Número");
        modelo.addColumn("Cliente");
        modelo.addColumn("Total");
        modelo.addColumn("Pagado Con");
        modelo.addColumn("Vuelto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Vendedor");
        tbbusqins.setModel(modelo);
        String sql = "";
        sql = "SELECT Numero,Cliente,Total,PagadoCon,Vuelto,Fecha,Hora,Vendedor FROM comprobante";
        String[] datos = new String[8];
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
                modelo.addRow(datos);
                if (primerId.isEmpty()) {
                    primerId = rs.getString(1);
                }
            }
            tbbusqins.setModel(modelo);
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
        modelo2.addColumn("N° Comprobante");
        modelo2.addColumn("Código de Producto");
        modelo2.addColumn("Descripción de Producto");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Precio Unitario");
        modelo2.addColumn("Precio Total");
        int filaseleccionada = tbbusqins.getSelectedRow();
        if (filaseleccionada == -1) {
            //JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            String numcomp = tbbusqins.getValueAt(filaseleccionada, 0).toString();
            String rut = tbbusqins.getValueAt(filaseleccionada, 1).toString();
            String total = tbbusqins.getValueAt(filaseleccionada, 2).toString();
            String pc = tbbusqins.getValueAt(filaseleccionada, 3).toString();
            String vuelto = tbbusqins.getValueAt(filaseleccionada, 4).toString();
            String fecha = tbbusqins.getValueAt(filaseleccionada, 5).toString();
            String hora = tbbusqins.getValueAt(filaseleccionada, 6).toString();
            String vendedor = tbbusqins.getValueAt(filaseleccionada, 7).toString();
            txtcomp.setText(numcomp);
            txtrutcte.setText(rut);
            txttotal.setText(total);
            txtfecha.setText(fecha);
            txthora.setText(hora);
            txtfecha.setText(fecha);
            txtvendedor.setText(vendedor);
            tbdetvtains.setModel(modelo2);
            String sql1 = "";
            sql1 = "SELECT * "
                    + "FROM detallecomprobante INNER JOIN comprobante ON detallecomprobante.NumComp = comprobante.Numero "
                    + "WHERE NumComp =" + iddetalle;
            String[] datos2 = new String[6];
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
                    modelo2.addRow(datos2);
                }
                tbdetvtains.setModel(modelo2);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
            }
        }
    }

    void anchocolumnas() {
        tbbusqins.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbbusqins.getColumnModel().getColumn(0).setWidth(100);
        tbbusqins.getColumnModel().getColumn(0).setMaxWidth(100);
        tbbusqins.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbbusqins.getColumnModel().getColumn(1).setWidth(100);
        tbbusqins.getColumnModel().getColumn(1).setMaxWidth(100);
        tbbusqins.getColumnModel().getColumn(1).setMinWidth(100);
        
        tbbusqins.getColumnModel().getColumn(2).setWidth(100);
        tbbusqins.getColumnModel().getColumn(2).setMaxWidth(100);
        tbbusqins.getColumnModel().getColumn(2).setMinWidth(100);
        
        tbbusqins.getColumnModel().getColumn(3).setWidth(135);
        tbbusqins.getColumnModel().getColumn(3).setMaxWidth(135);
        tbbusqins.getColumnModel().getColumn(3).setMinWidth(135);
        
        tbbusqins.getColumnModel().getColumn(4).setWidth(100);
        tbbusqins.getColumnModel().getColumn(4).setMaxWidth(100);
        tbbusqins.getColumnModel().getColumn(4).setMinWidth(100);
        
        tbbusqins.getColumnModel().getColumn(5).setWidth(100);
        tbbusqins.getColumnModel().getColumn(5).setMaxWidth(100);
        tbbusqins.getColumnModel().getColumn(5).setMinWidth(100);
    }
    private String validarnumeroVacio() {
        String errores="";
        if(txtnumcomp.getText().equals("")){
            errores+="Por favor busque el número de comprobante\n";
        }
        return errores;       
    }
  
    private String validartxtimprimeVacio() {
        String errores="";
        if(txtcomp.getText().equals("")){
            errores+="Busque el comprobante que desea guardar o imprimir\n";
        }
        return errores;       
    }
      void limpiarcampos() {
        txtcomp.setText("");
        txtfecha.setText("");
        txtrutcte.setText("");
        txthora.setText("");
        txtvendedor.setText("");
        txttotal.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mniVerDetalle = new javax.swing.JMenuItem();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelTranslucido1 = new elaprendiz.gui.panel.PanelTranslucido();
        jsp = new javax.swing.JScrollPane();
        tbbusqins = new javax.swing.JTable();
        rbtbxn = new javax.swing.JRadioButton();
        txtnumcomp = new javax.swing.JTextField();
        btnbuscardetalle = new javax.swing.JButton();
        bfecha = new com.toedter.calendar.JDateChooser();
        rbtbxf = new javax.swing.JRadioButton();
        rbtmt = new javax.swing.JRadioButton();
        panelTranslucido2 = new elaprendiz.gui.panel.PanelTranslucido();
        txttotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jsp1 = new javax.swing.JScrollPane();
        tbdetvtains = new javax.swing.JTable();
        txthora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();
        txtcomp = new javax.swing.JTextField();
        txtrutcte = new javax.swing.JTextField();
        txtvendedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        mniVerDetalle.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mniVerDetalle.setText("Ver Detalle");
        mniVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVerDetalleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mniVerDetalle);

        setClosable(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoazulceleste.jpg"))); // NOI18N

        panelTranslucido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione Opción de Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbbusqins.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbbusqins = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbbusqins.setSelectionBackground(Color.LIGHT_GRAY);
        tbbusqins.setSelectionForeground(Color.blue);
        tbbusqins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbbusqins.setComponentPopupMenu(jPopupMenu1);
        tbbusqins.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbbusqins.getTableHeader().setResizingAllowed(false);
        tbbusqins.getTableHeader().setReorderingAllowed(false);
        tbbusqins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbusqinsMouseClicked(evt);
            }
        });
        jsp.setViewportView(tbbusqins);

        buttonGroup1.add(rbtbxn);
        rbtbxn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtbxn.setForeground(new java.awt.Color(255, 255, 255));
        rbtbxn.setSelected(true);
        rbtbxn.setText("Buscar Por Número");
        rbtbxn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtbxnActionPerformed(evt);
            }
        });

        txtnumcomp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnumcomp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumcompKeyTyped(evt);
            }
        });

        btnbuscardetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busquedadetodo.png"))); // NOI18N
        btnbuscardetalle.setText("Buscar");
        btnbuscardetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscardetalleActionPerformed(evt);
            }
        });

        bfecha.setDate(Calendar.getInstance().getTime());
        bfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bfechaMousePressed(evt);
            }
        });

        buttonGroup1.add(rbtbxf);
        rbtbxf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtbxf.setForeground(new java.awt.Color(255, 255, 255));
        rbtbxf.setText("Buscar Por Fecha");
        rbtbxf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtbxfActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtmt);
        rbtmt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbtmt.setForeground(new java.awt.Color(255, 255, 255));
        rbtmt.setText("Mostrar Todos");
        rbtmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtmtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtmt)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtbxf)
                            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                                .addComponent(rbtbxn)
                                .addGap(27, 27, 27)
                                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35)
                        .addComponent(btnbuscardetalle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtbxn)
                            .addComponent(txtnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtbxf)))
                    .addComponent(btnbuscardetalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtmt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelTranslucido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        panelTranslucido2.setForeground(new java.awt.Color(255, 255, 255));

        txttotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txttotal.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total");

        tbdetvtains.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbdetvtains = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbdetvtains.setSelectionBackground(Color.LIGHT_GRAY);
        tbdetvtains.setSelectionForeground(Color.blue);
        tbdetvtains.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdetvtains.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdetvtains.getTableHeader().setResizingAllowed(false);
        tbdetvtains.getTableHeader().setReorderingAllowed(false);
        tbdetvtains.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdetvtainsMouseClicked(evt);
            }
        });
        jsp1.setViewportView(tbdetvtains);

        txthora.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txthora.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha");

        txtfecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfecha.setEnabled(false);

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconopdf.gif"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        txtcomp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcomp.setEnabled(false);

        txtrutcte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrutcte.setEnabled(false);

        txtvendedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtvendedor.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vendedor");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RUT Cliente");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Comprobante N°");

        javax.swing.GroupLayout panelTranslucido2Layout = new javax.swing.GroupLayout(panelTranslucido2);
        panelTranslucido2.setLayout(panelTranslucido2Layout);
        panelTranslucido2Layout.setHorizontalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createSequentialGroup()
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(41, 41, 41)
                        .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcomp, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                    .addComponent(txtrutcte))
                                .addGap(18, 18, 18)
                                .addComponent(btnimprimir)
                                .addGap(18, 18, 18)
                                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jsp1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTranslucido2Layout.setVerticalGroup(
            panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido2Layout.createSequentialGroup()
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir)
                    .addComponent(jLabel3)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtrutcte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranslucido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVerDetalleActionPerformed
        /*int filaseleccionada = tbbusqins.getSelectedRow();
        if (filaseleccionada == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        } else {
            DetalleComprobante detalle = new DetalleComprobante();
            Principal.jdpescritorio.add(detalle);
            detalle.toFront();
            detalle.setVisible(true);
            String numcomp = tbbusqins.getValueAt(filaseleccionada, 0).toString();
            String rut = tbbusqins.getValueAt(filaseleccionada, 1).toString();
            String total = tbbusqins.getValueAt(filaseleccionada, 2).toString();
            String fecha = tbbusqins.getValueAt(filaseleccionada, 5).toString();
            String hora = tbbusqins.getValueAt(filaseleccionada, 6).toString();
            String sucursal = tbbusqins.getValueAt(filaseleccionada, 7).toString();
            DetalleComprobante.txtcomp.setText(numcomp);
            DetalleComprobante.txtrutcte.setText(rut);
            DetalleComprobante.txttotal.setText(total);
            DetalleComprobante.txtfecha.setText(fecha);
            DetalleComprobante.txthora.setText(hora);
            DetalleComprobante.txtsucursal.setText(sucursal);
            DetalleComprobante.txtfecha.setText(fecha);
            DefaultTableModel model = (DefaultTableModel) DetalleComprobante.tbdetvtains.getModel();
            String ver = "SELECT * FROM detallecomprobante WHERE NumComp='" + numcomp + "'";
            String[] datos = new String[5];
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ver);
                while (rs.next()) {
                    datos[0] = rs.getString("CodPro");
                    datos[1] = rs.getString("DescProducto");
                    datos[2] = rs.getString("Cantidad");
                    datos[3] = rs.getString("PrecioUnitario");
                    datos[4] = rs.getString("PrecioTotal");
                    model.addRow(datos);

                }
                DetalleComprobante.tbdetvtains.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_mniVerDetalleActionPerformed

    private void rbtbxnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtbxnActionPerformed
        if (rbtbxn.isSelected() == true) {
            txtnumcomp.setEnabled(true);
            txtnumcomp.requestFocus();
           // CalendarioVta.setEnabled(false);
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtbxnActionPerformed

    private void rbtbxfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtbxfActionPerformed
        if (rbtbxf.isSelected() == true) {
            //CalendarioVta.setEnabled(true);
            txtnumcomp.setEnabled(false);
            txtnumcomp.setText("");
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtbxfActionPerformed

    private void rbtmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtmtActionPerformed
        if (rbtmt.isSelected() == true) {
           // CalendarioVta.setEnabled(false);
            txtnumcomp.setText("");
            txtnumcomp.setEnabled(false);
            mostrardatos();
            anchocolumnas();
        }
    }//GEN-LAST:event_rbtmtActionPerformed

    private void btnbuscardetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscardetalleActionPerformed
        String num = txtnumcomp.getText();
        String consulta = "";
        if (rbtbxn.isSelected() == true) {
            consulta = "SELECT * FROM comprobante WHERE Numero LIKE '%"+num+"%'";
            anchocolumnas();
            LimpiaTabla();
            limpiarcampos();
        }
        if (rbtbxf.isSelected() == true) {
            String fecha = df.format(bfecha.getDate());
            //vefecha.setText(fecha);
            consulta = "SELECT * FROM comprobante WHERE Fecha='"+fecha+"'";
            anchocolumnas();
            LimpiaTabla();
            limpiarcampos();
        }
        if (rbtmt.isSelected() == true) {
            consulta = "SELECT * FROM comprobante ";
            LimpiaTabla();
            limpiarcampos();
        }
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Número", "Cliente", "Total", "Pagado", "Vuelto", "Fecha", "Hora", "Vendedor", "Sucursal"};
        tabla.setColumnIdentifiers(titulos);
        tbbusqins.setModel(tabla);

        String[] Datos = new String[9];
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("Numero");
                Datos[1] = rs.getString("Cliente");
                Datos[2] = rs.getString("Total");
                Datos[3] = rs.getString("PagadoCon");
                Datos[4] = rs.getString("Vuelto");
                Datos[5] = rs.getString("Fecha");
                Datos[6] = rs.getString("Hora");
                Datos[7] = rs.getString("Vendedor");
                Datos[8] = rs.getString("Sucursal");
                tabla.addRow(Datos);
                anchocolumnas();
                //LimpiaTabla();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
    }//GEN-LAST:event_btnbuscardetalleActionPerformed

    private void tbbusqinsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbusqinsMouseClicked
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            String valorId = "";
            valorId = (String) target.getValueAt(row, 0); //obtener el valor de la columna ID
            mostrarDetalle(valorId);
            anchocolumnas();
    }//GEN-LAST:event_tbbusqinsMouseClicked

    private void tbdetvtainsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetvtainsMouseClicked

    }//GEN-LAST:event_tbdetvtainsMouseClicked

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        String errores = validartxtimprimeVacio();
        if (errores.equals("")) {
            try {
                conexion = claseConectar.ConexionConBaseDatos.getConexion();
                Map parametro = new HashMap();
                JasperReport reportes = JasperCompileManager.compileReport("reporteevtains.jrxml");
                parametro.put("num", txtcomp.getText());
                //se carga el reporte
                //se procesa el archivo jasper
                JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conexion);
                JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos, espere porfavor", "El sistema está generando el comprobante de venta", JOptionPane.WARNING_MESSAGE);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, errores,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void bfechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bfechaMousePressed
//        String fecha = df.format(bfecha.getDate());
//        vefecha.setText(fecha);        
    }//GEN-LAST:event_bfechaMousePressed

    private void txtnumcompKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumcompKeyTyped
        validarLetras.soloNumeros(evt);
        //limite de caracteres
        if (txtnumcomp.getText().length() == 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtnumcompKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser bfecha;
    private javax.swing.JButton btnbuscardetalle;
    private javax.swing.JButton btnimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JScrollPane jsp1;
    private javax.swing.JMenuItem mniVerDetalle;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido1;
    private elaprendiz.gui.panel.PanelTranslucido panelTranslucido2;
    private javax.swing.JRadioButton rbtbxf;
    private javax.swing.JRadioButton rbtbxn;
    private javax.swing.JRadioButton rbtmt;
    private javax.swing.JTable tbbusqins;
    public static javax.swing.JTable tbdetvtains;
    public static javax.swing.JTextField txtcomp;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txthora;
    private javax.swing.JTextField txtnumcomp;
    public static javax.swing.JTextField txtrutcte;
    public static javax.swing.JTextField txttotal;
    public static javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
