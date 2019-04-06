/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import static sgtmtr.ComprobanteVta.tbvprod;

/**
 *
 * @author ZuluCorp
 */
public class SeleccionProducto extends javax.swing.JDialog {
    CalculaPrecioTB calcula = new CalculaPrecioTB();
    /**
     * Creates new form SeleccionProducto
     */
    public SeleccionProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Seleccione el Insumo");
        mostrardatos("");
        anchocolumnas();
    }
    void mostrardatos(String valor) {
        /*String id=txtidmarca.getText();
        String NM=txtmn.getText();*/
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        tbprod.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM insumos";
        } else {
            //sql = "SELECT * FROM insumos WHERE Codigo='" + id + "'";
        }

        String[] datos = new String[4];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
            tbprod.setModel(modelo);
           //tbmarcas.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
    }
    
    void anchocolumnas() {
        tbprod.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbprod.getColumnModel().getColumn(0).setWidth(100);
        tbprod.getColumnModel().getColumn(0).setMaxWidth(100);
        tbprod.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbprod.getColumnModel().getColumn(1).setWidth(200);
        tbprod.getColumnModel().getColumn(1).setMaxWidth(200);
        tbprod.getColumnModel().getColumn(1).setMinWidth(200);
        
        tbprod.getColumnModel().getColumn(0).setWidth(100);
        tbprod.getColumnModel().getColumn(0).setMaxWidth(100);
        tbprod.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbprod.getColumnModel().getColumn(1).setWidth(100);
        tbprod.getColumnModel().getColumn(1).setMaxWidth(100);
        tbprod.getColumnModel().getColumn(1).setMinWidth(100);
    }
    
    String comparar(String cod) {
        String option = "";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM insumos WHERE Codigo='" + cod + "'");
            while (rs.next()) {
                option = rs.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SeleccionProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return option;

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
        jsp = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione Producto a Vender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbprod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbprod = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbprod.setSelectionBackground(Color.LIGHT_GRAY);
        tbprod.setSelectionForeground(Color.blue);
        tbprod.setModel(new javax.swing.table.DefaultTableModel(
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
        tbprod.getTableHeader().setResizingAllowed(false);
        tbprod.getTableHeader().setReorderingAllowed(false);
        tbprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbprodMousePressed(evt);
            }
        });
        jsp.setViewportView(tbprod);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void tbprodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodMousePressed
        /*SeleccionaCantidad sc = new SeleccionaCantidad(null, true);
        sc.setVisible(true);*/
        SpinnerNumberModel numerito = new SpinnerNumberModel(0, 0, 999, 1);
        JSpinner spinner = new JSpinner(numerito);
        int option = JOptionPane.showOptionDialog(null, spinner, "Seleccione o Ingrese Cantidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.CANCEL_OPTION) {
    // user hit cancel
        } else if (option == JOptionPane.OK_OPTION) {
            try {
            DefaultTableModel tabladet = (DefaultTableModel) ComprobanteVta.tbvprod.getModel();
            String[] dato = new String[5];
            int fila = SeleccionProducto.tbprod.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro");
            } else {
                String cod = SeleccionProducto.tbprod.getValueAt(fila, 0).toString();
                String desc = SeleccionProducto.tbprod.getValueAt(fila, 1).toString();
                String preunit = SeleccionProducto.tbprod.getValueAt(fila, 2).toString();
                int c = 0;
                int j = 0;
                //SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
                //JSpinner spinner = new JSpinner(sModel);
                String cant = numerito.getValue().toString();
                //validar que no sea menor que 0 o sea -1 etc menos letras... o cambiar tema aca
                if ((cant.equals("0"))) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un valor mayor que 0");
                } else {
                    int canting = Integer.parseInt(cant);
                    int comp = Integer.parseInt(comparar(cod));
                    if (canting > comp) {
                        JOptionPane.showMessageDialog(this, "No se cuenta con el stock suficiente");
                    } else {
                        if (canting > 0) {
                            for (int i = 0; i < ComprobanteVta.tbvprod.getRowCount(); i++) {
                                Object com = ComprobanteVta.tbvprod.getValueAt(i, 0);
                                if (cod.equals(com)) {
                                    j = i;
                                    ComprobanteVta.tbvprod.setValueAt(canting, i, 2);
                                    c = c + 1;
                                    calcula.calcular();
                                }
                                this.dispose();
                            }
                        } else {
                        }
                        if (c == 0) {
                            dato[0] = cod;
                            dato[1] = desc;
                            dato[2] = cant;
                            dato[3] = preunit;
                            tabladet.addRow(dato);
                            ComprobanteVta.tbvprod.setModel(tabladet);
                            calcula.calcular();
                            this.dispose();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        }
        
    }//GEN-LAST:event_tbprodMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(SeleccionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SeleccionProducto dialog = new SeleccionProducto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jsp;
    public static javax.swing.JTable tbprod;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();
}
