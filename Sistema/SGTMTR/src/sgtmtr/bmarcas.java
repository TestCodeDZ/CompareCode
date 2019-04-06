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
public class bmarcas extends javax.swing.JInternalFrame {
DefaultTableModel modelo;
    /**
     * Creates new form bv
     */
    public bmarcas() {
        initComponents();
        setTitle("Selección de Marcas");
        this.setLocation(280,200);
        mostrarvehiculos("");
        anchocolumnas();
    }
void mostrarvehiculos(String valor)
    {
        String[]titulos={"ID","Nombre de la Marca"} ;  
        String []Registros= new String[2];
        modelo=new DefaultTableModel(null,titulos);
        String Sql="SELECT * FROM marcas";
       
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(Sql);
             while(rs.next())
             {
                 Registros[0]=rs.getString("ID");  
                 Registros[1]=rs.getString("NombreMarca");
                 modelo.addRow(Registros);
             } 
             tbmarca.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(bv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void anchocolumnas() {
        tbmarca.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbmarca.getColumnModel().getColumn(0).setWidth(100);
        tbmarca.getColumnModel().getColumn(0).setMaxWidth(100);
        tbmarca.getColumnModel().getColumn(0).setMinWidth(100);
        
        tbmarca.getColumnModel().getColumn(1).setWidth(200);
        tbmarca.getColumnModel().getColumn(1).setMaxWidth(200);
        tbmarca.getColumnModel().getColumn(1).setMinWidth(200);
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
        tbmarca = new javax.swing.JTable();
        btnenviar = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione Marca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        //Deshabilitar edicion de tabla
        tbmarca = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbmarca.setSelectionBackground(Color.LIGHT_GRAY);
        tbmarca.setSelectionForeground(Color.blue);
        tbmarca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbmarca.setModel(new javax.swing.table.DefaultTableModel(
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
        tbmarca.getTableHeader().setResizingAllowed(false);
        tbmarca.getTableHeader().setReorderingAllowed(false);
        jsp.setViewportView(tbmarca);

        btnenviar.setText("Enviar");
        btnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnenviar)
                .addGap(121, 121, 121))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnenviar))
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

    private void btnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenviarActionPerformed
        String IDm = "", nombremarca = "";
        int fila = tbmarca.getSelectedRow();
        try {
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna marca");

            } else {
                IDm = (String) tbmarca.getValueAt(fila, 0);
                nombremarca = (String) tbmarca.getValueAt(fila, 1);
                vehiculos.txtnm.setDisabledTextColor(Color.blue);
                vehiculos.txtnm.setText(nombremarca);
                this.dispose();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnenviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnenviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbmarca;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn = cc.conexion();
}
