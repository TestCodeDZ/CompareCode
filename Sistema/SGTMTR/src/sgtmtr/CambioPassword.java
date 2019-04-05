/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static claseConectar.ConexionConBaseDatos.conexion;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ZuluCorp
 */
public class CambioPassword extends javax.swing.JDialog {

    ValidarCaracteres validarLetras = new ValidarCaracteres();

    /**
     * Creates new form CambioPassword
     */
    public CambioPassword(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //centrar la pantalla
        setLocationRelativeTo(null);
        setTitle("Cambio de Contraseña");
    }

    private String getValorClaveUsuario(int usuario) {
        String salida = "";
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE IDTB=" + usuario;
            //Ejecutar la consulta
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                salida = rs.getString("Password").toLowerCase();
                rs.close();
            }
        } catch (Exception e) {
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }

        return salida;
    }

    Boolean actualizarClave(int usuario, String nuevaClave) {
        Boolean salida = false;
        try {
            conexion = claseConectar.ConexionConBaseDatos.getConexion();
            Statement st = conexion.createStatement();
            //query de actualización de password
            String sql = "UPDATE usuarios SET Password='" + nuevaClave + "' "
                    + "WHERE IDTB = " + usuario;
            //Ejecutar la consulta
            int filas = st.executeUpdate(sql);

            // hay filas afectadas???
            if (filas > 0) {
                salida = true;
            } else {
                salida = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        } finally {
            claseConectar.ConexionConBaseDatos.metodoCerrarConexiones(conexion);
        }
        return salida;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btcambiarpass = new javax.swing.JButton();
        pfca = new javax.swing.JPasswordField();
        pfcn = new javax.swing.JPasswordField();
        pfccn = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulario Cambio de Contraseña", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/images(2).jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Contraseña Actual");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Contraseña Nueva");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Confirmar Nueva Contraseña");

        btcambiarpass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btcambiarpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group_key.png"))); // NOI18N
        btcambiarpass.setText("Cambiar Contraseña");
        btcambiarpass.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btcambiarpass.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btcambiarpass.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btcambiarpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcambiarpassActionPerformed(evt);
            }
        });

        pfca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pfca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfcaActionPerformed(evt);
            }
        });
        pfca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfcaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfcaKeyTyped(evt);
            }
        });

        pfcn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pfcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfcnActionPerformed(evt);
            }
        });
        pfcn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfcnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfcnKeyTyped(evt);
            }
        });

        pfccn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pfccn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfccnActionPerformed(evt);
            }
        });
        pfccn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfccnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfccnKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btcambiarpass)
                        .addContainerGap(46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pfcn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pfca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pfccn))
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(pfca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(pfcn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(pfccn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btcambiarpass)))
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

    private void pfcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfcaActionPerformed

    }//GEN-LAST:event_pfcaActionPerformed

    private void pfcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfcnActionPerformed

    }//GEN-LAST:event_pfcnActionPerformed

    private void pfccnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfccnActionPerformed

    }//GEN-LAST:event_pfccnActionPerformed

    private void pfcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfcaKeyPressed

    }//GEN-LAST:event_pfcaKeyPressed

    private void pfcnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfcnKeyPressed

    }//GEN-LAST:event_pfcnKeyPressed

    private void pfccnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfccnKeyPressed

    }//GEN-LAST:event_pfccnKeyPressed

    private void pfcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfcaKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (pfca.getText().length() == 15) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_pfcaKeyTyped

    private void pfcnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfcnKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (pfcn.getText().length() == 15) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_pfcnKeyTyped

    private void pfccnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfccnKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (pfccn.getText().length() == 15) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_pfccnKeyTyped

    private void btcambiarpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcambiarpassActionPerformed
        // revisar que los campos tengan datos en el formulario
        String cn = pfcn.getText();
        String ccn = pfccn.getText();
        if (pfca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡Debe ingresar contraseña antigua!");
            return;
        }
        if (pfcn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡Debe ingresar contraseña nueva!");
            return;
        }
        if (pfccn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡Debe confirmar la contraseña nueva!");
            return;
        }
        // revisar que el valor de la clave existe para el usuario conectado
        if (this.getValorClaveUsuario(ClassUtils.USUARIO_CONECTADO).compareToIgnoreCase(
                pfca.getText().trim()) != 0) {
            JOptionPane.showMessageDialog(this, "¡Contraseña antigua no corresponde!");
            return;
        }
        //no logro hacer funcionar esta parte del codigo
        if (!cn.equalsIgnoreCase(ccn)) {
            JOptionPane.showMessageDialog(this, "¡Confirmación de contraseña nueva no corresponde!");
            return;
        }
        //JOptionPane.showMessageDialog(this, App.pwd);
        if (pfcn.getText().equals(Login.pwd)) {
            JOptionPane.showMessageDialog(this, "¡Ud. no puede volver a poner la misma contraseña que tiene actualmente!");
            return;
        }
        if (actualizarClave(ClassUtils.USUARIO_CONECTADO, pfcn.getText().trim())) {
            //se actualizó la clave
            JOptionPane.showMessageDialog(this, "¡Contraseña actualizada con éxito!");
            //Limpiar passwordfields
            pfca.setText("");
            pfcn.setText("");
            pfccn.setText("");
            return;
        } else {
            //no se actualizó la clave
            JOptionPane.showMessageDialog(this, "¡La Contraseña no pudo cambiarse. Intente más tarde.");
            return;
        }
    }//GEN-LAST:event_btcambiarpassActionPerformed

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
            java.util.logging.Logger.getLogger(CambioPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambioPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambioPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambioPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CambioPassword dialog = new CambioPassword(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btcambiarpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pfca;
    private javax.swing.JPasswordField pfccn;
    private javax.swing.JPasswordField pfcn;
    // End of variables declaration//GEN-END:variables
}
