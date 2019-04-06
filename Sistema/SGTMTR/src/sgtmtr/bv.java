/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import claseConectar.conectar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class bv extends javax.swing.JInternalFrame {
DefaultTableModel modelo;
    /**
     * Creates new form bv
     */
    public bv() {
        initComponents();
        setTitle("Búsqueda y selección de vehículo");
        this.setLocation(280,200);
        mostrarvehiculos("");
    }
void mostrarvehiculos(String valor)
    {
        String[]titulos={"Patente","Marca","Modelo","Año","Color","RUT Dueño"} ;  
        String []Registros= new String[6];
        modelo=new DefaultTableModel(null,titulos);
        String Sql="SELECT * FROM vehiculo WHERE CONCAT(Patente,Marca,Modelo,Año) LIKE '%"+valor+"%'";
       
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(Sql);
             while(rs.next())
             {
                 Registros[0]=rs.getString("Patente");  
                 Registros[1]=rs.getString("Marca");  
                 Registros[2]=rs.getString("Modelo");  
                 Registros[3]=rs.getString("Año");  
                 Registros[4]=rs.getString("Color");  
                 Registros[5]=rs.getString("Dueño"); 
                 modelo.addRow(Registros);
             } 
             tbvehiculo.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(bv.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpatente = new javax.swing.JTextField();
        jsp = new javax.swing.JScrollPane();
        tbvehiculo = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda de Vehículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setText("Patente");

        txtpatente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpatenteKeyReleased(evt);
            }
        });

        tbvehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbvehiculo = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbvehiculo.setSelectionBackground(Color.LIGHT_GRAY);
        tbvehiculo.setSelectionForeground(Color.blue);
        tbvehiculo.setModel(new javax.swing.table.DefaultTableModel(
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
        tbvehiculo.getTableHeader().setResizingAllowed(false);
        tbvehiculo.getTableHeader().setReorderingAllowed(false);
        tbvehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbvehiculoMousePressed(evt);
            }
        });
        jsp.setViewportView(tbvehiculo);

        jButton1.setText("+");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtpatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpatenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpatenteKeyReleased
         mostrarvehiculos(txtpatente.getText());
    }//GEN-LAST:event_txtpatenteKeyReleased

    private void tbvehiculoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbvehiculoMousePressed
        String patente = "", marca = "", modelo = "", año = "", color = "", dueño = "";
        int fila = tbvehiculo.getSelectedRow();
        try {
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado fila");

            } else {
                patente = (String) tbvehiculo.getValueAt(fila, 0);
                marca = (String) tbvehiculo.getValueAt(fila, 1);
                modelo = (String) tbvehiculo.getValueAt(fila, 2);
                año = (String) tbvehiculo.getValueAt(fila, 3);
                color = (String) tbvehiculo.getValueAt(fila, 4);
                dueño = (String) tbvehiculo.getValueAt(fila, 5);

                diagnostico.txtpatentediag.setDisabledTextColor(Color.blue);
                diagnostico.txtpatentediag.setText(patente);
                diagnostico.txtrutcliente.setDisabledTextColor(Color.blue);
                diagnostico.txtrutcliente.setText(dueño);
                //campos de datos del cliente agregar (nombre, apellido, fono, correo).
                this.dispose();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbvehiculoMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbvehiculo;
    private javax.swing.JTextField txtpatente;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();
}
