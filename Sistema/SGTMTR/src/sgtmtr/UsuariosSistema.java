/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

/**
 *
 * @author ZuluCorp
 */
import claseConectar.conectar;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UsuariosSistema extends javax.swing.JInternalFrame {
    ValidarCaracteres validarLetras = new ValidarCaracteres();
    /**
     * Creates new form UsuariosSistema
     */
    public static String base = "0123456789";//abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
    public UsuariosSistema() {
        initComponents();
        setTitle("Mantenedor de Datos de Usuarios del Sistema");
        this.setLocation(150,15);
        inicio();
        bloquear();
        CargarComboTipoUsers();
        Cargartextfieldusuario();
        generapassword();
        CargarCombosucursales();
        Cargartextfieldsucursal();
        mostrardatos("");
        anchocolumnas();
    }
    void inicio(){
        txtid.setEnabled(false);
        txtntu.setEnabled(false);
        txtnombresucursal.setEnabled(false);
        txtusuario.setEnabled(false);
        txtpass.setEnabled(false);
    }
    void bloquear(){
        txtid.setEnabled(false);
        txtnombres.setEnabled(false);
        txtapellidos.setEnabled(false);
        //ver combos index
        btbuscar.setEnabled(false);
        btingresar.setEnabled(false);
        btmodificar.setEnabled(false);
        btborrar.setEnabled(false);
        btlimpiar.setEnabled(false);
        btgenerar.setEnabled(false);
    }
    void limpiar(){
        txtid.setText("");
        txtnombres.setText("");
        txtapellidos.setText("");
        txtusuario.setText("");
        cbtu.setSelectedIndex(0);
        cbidsucursal.setSelectedIndex(0);
    }
    void desbloquear(){
        txtnombres.setEnabled(true);
        //txtapellidos.setEnabled(true);
        //ver combos index
        btbuscar.setEnabled(true);
        btingresar.setEnabled(true);
        btmodificar.setEnabled(true);
        btborrar.setEnabled(true);
        btlimpiar.setEnabled(true);
    }
    
    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Tipo de Usuario");
        modelo.addColumn("Usuario");
        modelo.addColumn("Sucursal");
        tbusuarios.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT ID,Nombres,Apellidos,TipoUser,Usuario,NombreSucursal FROM usuarios u, tipousuario tu, sucursal s where (u.TipoUsuario=tu.IDTU) and (u.sucursal=s.ID_Suc)";
        } else {
            sql = "SELECT * FROM usuarios WHERE ID='" + txtid.getText() + "'";
        }

        String[] datos = new String[6];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            tbusuarios.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
    }

    void anchocolumnas() {
        tbusuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbusuarios.getColumnModel().getColumn(0).setWidth(40);
        tbusuarios.getColumnModel().getColumn(0).setMaxWidth(40);
        tbusuarios.getColumnModel().getColumn(0).setMinWidth(40);
        
        tbusuarios.getColumnModel().getColumn(1).setWidth(100);
        tbusuarios.getColumnModel().getColumn(1).setMaxWidth(100);
        tbusuarios.getColumnModel().getColumn(1).setMinWidth(100);

        tbusuarios.getColumnModel().getColumn(2).setWidth(100);
        tbusuarios.getColumnModel().getColumn(2).setMaxWidth(100);
        tbusuarios.getColumnModel().getColumn(2).setMinWidth(100);

        tbusuarios.getColumnModel().getColumn(3).setWidth(120);
        tbusuarios.getColumnModel().getColumn(3).setMaxWidth(120);
        tbusuarios.getColumnModel().getColumn(3).setMinWidth(120);

        tbusuarios.getColumnModel().getColumn(4).setWidth(90);
        tbusuarios.getColumnModel().getColumn(4).setMaxWidth(90);
        tbusuarios.getColumnModel().getColumn(4).setMinWidth(90);

        tbusuarios.getColumnModel().getColumn(5).setWidth(90);
        tbusuarios.getColumnModel().getColumn(5).setMaxWidth(90);
        tbusuarios.getColumnModel().getColumn(5).setMinWidth(90);
    }
    
    void codigosusuarios(){
     int j;
        int cont=1;
        String num="";
        String c="";
        String SQL="select max(ID) from usuarios";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next())
            {              
                 c=rs.getString(1);
            }    
            if(c==null){
                txtid.setText("U001");
            }
            else{
            char r1=c.charAt(2);
            char r2=c.charAt(3);
            String r="";
            r=""+r1+r2;
            
                 j=Integer.parseInt(r);
                 sgtmtr.GenerarCodigos gen= new sgtmtr.GenerarCodigos();
                 gen.generar(j);
                 txtid.setText("U"+gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(UsuariosSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void CargarComboTipoUsers(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st = con.createStatement();
            String sql = "SELECT IDTU,TipoUser FROM tipousuario";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbtu.setModel(new DefaultComboBoxModel());
                      
            //Recorremos los registros traidos
            while(rs.next()){
                //Agregamos elemento al combo
                cbtu.addItem(rs.getObject(1));
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void Cargartextfieldusuario(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st1 = con.createStatement();  
            String sql1 = "SELECT TipoUser FROM tipousuario WHERE IDTU='"+cbtu.getSelectedItem()+"'";
            //Ejecutar consulta
            ResultSet rs1= st1.executeQuery(sql1);
            //Recorremos los registros traidos
            while(rs1.next()){
                //Agregamos elemento al text
                txtntu.setText(rs1.getObject("TipoUser").toString());
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void CargarCombosucursales(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st = con.createStatement();
            String sql = "SELECT ID_Suc,NombreSucursal FROM sucursal";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbidsucursal.setModel(new DefaultComboBoxModel());
                      
            //Recorremos los registros traidos
            while(rs.next()){
                //Agregamos elemento al combo
                cbidsucursal.addItem(rs.getObject(1));
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void Cargartextfieldsucursal(){
        //Carga de Combo
        try{
            //Cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Crear Conexion
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
            //Crear Consulta
            Statement st1 = con.createStatement();  
            String sql1 = "SELECT NombreSucursal FROM sucursal WHERE ID_Suc='"+cbidsucursal.getSelectedItem()+"'";
            //Ejecutar consulta
            ResultSet rs1= st1.executeQuery(sql1);
            //Recorremos los registros traidos
            while(rs1.next()){
                //Agregamos elemento al text
                txtnombresucursal.setText(rs1.getObject("NombreSucursal").toString());
            }
            //Cerramos conexión
            con.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
    
    private void generapassword(){
        int LargoContrasena=4;
        String contrasena="";
        int longitud = base.length();
        for(int i=0; i<LargoContrasena;i++){ 
        int numero = (int)(Math.random()*(longitud)); 
        String caracter=base.substring(numero, numero+1);
        contrasena=contrasena+caracter; 
      }
          txtpass.setText(contrasena);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnimod = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnombres = new javax.swing.JTextField();
        txtapellidos = new javax.swing.JTextField();
        txtntu = new javax.swing.JTextField();
        cbtu = new javax.swing.JComboBox();
        txtusuario = new javax.swing.JTextField();
        txtpass = new javax.swing.JTextField();
        cbidsucursal = new javax.swing.JComboBox();
        txtnombresucursal = new javax.swing.JTextField();
        btgenerar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnuevo = new javax.swing.JButton();
        btingresar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        btborrar = new javax.swing.JButton();
        btbuscar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jsp = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbusuarios = new javax.swing.JTable();

        mnimod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mnimod.setText("Modificar");
        mnimod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnimodActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnimod);

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("N° Identificador");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 40, 96, 20);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombres");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 80, 56, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 120, 57, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Tipo de Usuario");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 160, 100, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Usuario");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 200, 48, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 240, 74, 20);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Sucursal");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 280, 55, 17);

        txtid.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });
        jPanel1.add(txtid);
        txtid.setBounds(160, 30, 60, 30);

        txtnombres.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnombresFocusLost(evt);
            }
        });
        txtnombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombresActionPerformed(evt);
            }
        });
        txtnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombres);
        txtnombres.setBounds(160, 70, 170, 30);

        txtapellidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtapellidos.setEnabled(false);
        txtapellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtapellidosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtapellidosFocusLost(evt);
            }
        });
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidosKeyTyped(evt);
            }
        });
        jPanel1.add(txtapellidos);
        txtapellidos.setBounds(160, 110, 170, 30);

        txtntu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(txtntu);
        txtntu.setBounds(220, 150, 110, 30);

        cbtu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbtu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbtu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtuItemStateChanged(evt);
            }
        });
        jPanel1.add(cbtu);
        cbtu.setBounds(160, 150, 50, 30);

        txtusuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtusuario);
        txtusuario.setBounds(160, 190, 120, 30);

        txtpass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(txtpass);
        txtpass.setBounds(160, 230, 60, 30);

        cbidsucursal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbidsucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbidsucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbidsucursalItemStateChanged(evt);
            }
        });
        jPanel1.add(cbidsucursal);
        cbidsucursal.setBounds(160, 270, 50, 30);

        txtnombresucursal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(txtnombresucursal);
        txtnombresucursal.setBounds(220, 270, 110, 30);

        btgenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group_key.png"))); // NOI18N
        btgenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgenerarActionPerformed(evt);
            }
        });
        jPanel1.add(btgenerar);
        btgenerar.setBounds(290, 200, 40, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-icon.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        btingresar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept.png"))); // NOI18N
        btingresar.setText("Ingresar");
        btingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btingresarActionPerformed(evt);
            }
        });

        btmodificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/page_edit.png"))); // NOI18N
        btmodificar.setText("Modificar");
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
            }
        });

        btborrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btborrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btborrar.setText("Borrar");
        btborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btborrarActionPerformed(evt);
            }
        });

        btbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/92125_find_user-512.png"))); // NOI18N
        btbuscar.setText("Buscar");
        btbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscarActionPerformed(evt);
            }
        });

        btlimpiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btingresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btborrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btborrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación de la tabla Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbusuarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        //Deshabilitar edicion de tabla
        tbusuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        //cambiar color de fila
        tbusuarios.setSelectionBackground(Color.LIGHT_GRAY);
        tbusuarios.setSelectionForeground(Color.blue);
        tbusuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tbusuarios.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tbusuarios);

        jsp.add(jScrollPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        generapassword();
        desbloquear();
        limpiar();
        codigosusuarios();
        txtnombres.requestFocus();
        btborrar.setEnabled(false);
        btmodificar.setEnabled(false);
    }//GEN-LAST:event_btnuevoActionPerformed

    private void cbtuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtuItemStateChanged
        Integer indice = cbtu.getSelectedIndex();
        if (indice.equals(0))
        {
        Cargartextfieldusuario();
        }
        else if (indice.equals(1))
        {
        Cargartextfieldusuario();
        }else if (indice.equals(2))
        {
        Cargartextfieldusuario();
        }else if (indice.equals(3))
        {
        Cargartextfieldusuario();
        }
    }//GEN-LAST:event_cbtuItemStateChanged

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        limpiar();
        generapassword();
        txtid.setEnabled(true);
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void cbidsucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbidsucursalItemStateChanged
        Integer indice = cbtu.getSelectedIndex();
        if (indice.equals(0))
        {
        Cargartextfieldsucursal();
        }
        else if (indice.equals(1))
        {
        Cargartextfieldsucursal();
        }
    }//GEN-LAST:event_cbidsucursalItemStateChanged
       private void cortecaracteres(){
        /* otra wea http://www.youtube.com/watch?v=Y9FSd3JXPAk
        link http://lineadecodigo.com/tag/string/
        http://algoritmosjavafc.blogspot.com/p/cadenas-en-java.html
        http://www.youtube.com/watch?v=ppfpRygui1U
        http://www.discoduroderoer.es/*/

       String nombreRecortado=txtnombres.getText().substring(0,3);
       String apellidoRecortado=txtapellidos.getText().substring(0,4);
       String union=nombreRecortado+"."+apellidoRecortado;
       txtusuario.setText(union);
    }
    private void txtusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyTyped
       
    }//GEN-LAST:event_txtusuarioKeyTyped
    
   // private String validarNombre() {
        //String error="";
        //if(txtidmarca.getText().equals("")){
        //    error+="Por favor ingrese el ID de la marca a buscar o eliminar\n";
        //}
        //return error;
    //}
    
    private void txtapellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtapellidosFocusLost
       if(txtapellidos.getText().length() < 3) {
           btgenerar.setEnabled(false);
           JOptionPane.showMessageDialog(this, "¡El Apellido debe contener al menos 3 caracteres para generar nick!");
           txtapellidos.requestFocus();
       }
       else
       {
            btgenerar.setEnabled(true);
       }
    }//GEN-LAST:event_txtapellidosFocusLost

    private void btgenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgenerarActionPerformed
       cortecaracteres();
    }//GEN-LAST:event_btgenerarActionPerformed

    private void txtnombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresKeyTyped
        validarLetras.soloLetras(evt);
        if (txtnombres.getText().length() < 4){
            txtapellidos.setEnabled(false);
                   
        }else{
            txtapellidos.setEnabled(true);
        }
        
        if (txtnombres.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtnombresKeyTyped

    private void txtapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyTyped
        validarLetras.soloLetras(evt);
        if (txtapellidos.getText().length() == 50) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtapellidosKeyTyped

    private void txtnombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnombresFocusLost
        if(txtnombres.getText().length() < 4) {
           btgenerar.setEnabled(false);
           JOptionPane.showMessageDialog(this, "¡El Nombre debe contener al menos 4 caracteres para generar nick!");
           txtnombres.requestFocus();
        }
    }//GEN-LAST:event_txtnombresFocusLost
     
    private String validarVacios() {
        
        String errores="";
        
        if(txtid.getText().equals("")){
            errores+="Por favor genere el ID de Usuario \n";
        }
        if(txtnombres.getText().equals("")){
            errores+="Por favor ingrese el Nombre de la persona \n";
        }
        if(txtapellidos.getText().trim().isEmpty()){
            errores+="El campo nombre está vacio \n";
        }
        if(txtusuario.getText().trim().isEmpty()){
            errores+="Por favor genere el nombre de usuario \n";
        }
        if(txtpass.getText().trim().isEmpty()){
            errores+="Por favor genere la conraseña \n";
        }
        return errores;       
    }
      private String validarIDVacio() {
        String errores="";
        if(txtid.getText().equals("")){
            errores+="Por favor ingrese el ID a buscar o eliminar\n";
        }
        return errores;       
    }
    private void btingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarActionPerformed
        String errores=validarVacios();
        if(errores.equals("")){
             try {
            //Cargar driver de conexión
                Class.forName("com.mysql.jdbc.Driver");
                //Crear conexión
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
                //Crear consulta
                Statement st = con.createStatement();
                String sql = "INSERT INTO usuarios (ID,Nombres,Apellidos,TipoUsuario,Usuario,Password,Sucursal)"
                        + "VALUES('" + txtid.getText() + "','" + txtnombres.getText() + "','" + txtapellidos.getText() + "',"
                        + "'" + cbtu.getSelectedItem() + "','" + txtusuario.getText() + "',"
                        + "'" + txtpass.getText() + "','" + cbidsucursal.getSelectedItem() + "')";
                //Ejecutar la consulta
                st.executeUpdate(sql);
                //Cerrar conexion
                con.close();
                    if (String.valueOf(txtid.getText()).compareTo("") == 0
                    && String.valueOf(txtusuario.getText()).compareTo("") == 0) {
                    validarVacios(); 
                    }else{
                    JOptionPane.showMessageDialog(this, "Usuario Ingresado");
                    mostrardatos("");
                    //Limpiar
                    limpiar(); 
                    anchocolumnas();
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error"+ e.getMessage().toString());
            }  
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }    
    }//GEN-LAST:event_btingresarActionPerformed

    private void btbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscarActionPerformed
        String error=validarIDVacio();
        if(error.equals("")){
        // Buscar registro en la base de datos
        try {
            //Cargar driver de conexión
            Class.forName("com.mysql.jdbc.Driver");
            //Crear conexión
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            //Crear consulta
            Statement st = con.createStatement();
            String sql = sql = "SELECT * FROM usuarios WHERE ID='" + txtid.getText() + "'";
            //Ejecutar la consulta
            ResultSet rs = st.executeQuery(sql);
            mostrardatos(sql);
            if (rs.next()) {
                //existe
                txtnombres.setText(rs.getObject("Nombres").toString());
                txtapellidos.setText(rs.getObject("Apellidos").toString());
                cbtu.setSelectedItem(rs.getObject("TipoUsuario"));
                txtusuario.setText(rs.getObject("Usuario").toString());
                cbidsucursal.setSelectedItem(rs.getObject("Sucursal"));
                btborrar.setEnabled(true);
                btmodificar.setEnabled(true);
            } else {
                //no existe
                if (String.valueOf(txtid.getText()).compareTo("") == 0) {
                    validarVacios();
                }else {
                JOptionPane.showMessageDialog(this, "El usuario no existe");
                btborrar.setEnabled(false);
                btmodificar.setEnabled(false);
                limpiar();
            }
            }
            //Cerrar conexion
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
        anchocolumnas();
        }else{
            JOptionPane.showMessageDialog(null, error);
        }
    }//GEN-LAST:event_btbuscarActionPerformed

    private void btborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btborrarActionPerformed
        String error=validarIDVacio();
        if(error.equals("")){
        try {
            if (String.valueOf(txtid.getText()).compareTo("") == 0
            && String.valueOf(txtusuario.getText()).compareTo("") == 0) {
            validarVacios();
            }else{
            JOptionPane.showMessageDialog(this, "Usuario Eliminado");
            btmodificar.setEnabled(false);
            btborrar.setEnabled(false);
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("DELETE FROM usuarios WHERE ID='" + txtid.getText() + "'");
            pst.executeUpdate();
            limpiar();
            con.close();
            
            mostrardatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
        anchocolumnas();
        btingresar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, error);
        }     
    }//GEN-LAST:event_btborrarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        String errores=validarVacios();
        if(errores.equals("")){
        try {
            if (String.valueOf(txtid.getText()).compareTo("") == 0
                    && String.valueOf(txtusuario.getText()).compareTo("") == 0) {
            validarVacios();
            }else{
            JOptionPane.showMessageDialog(this, "Usuario Actualizado");
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE usuarios SET Nombres='" + txtnombres.getText() + "',Apellidos='" + txtapellidos.getText()
                    + "',TipoUsuario='" + cbtu.getSelectedItem() + "',Usuario='" + txtusuario.getText()
                    + "',Password='" + txtpass.getText() + "',Sucursal='" + cbidsucursal.getSelectedItem()
                    + "' WHERE ID='" + txtid.getText() + "'");
            
            pst.executeUpdate();
            //cerrar conexion
            con.close();
            btingresar.setEnabled(true);
            //limpiar textfields
            limpiar();
            mostrardatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage().toString());
        }
            anchocolumnas();
        }else{
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btmodificarActionPerformed

    private void txtnombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombresActionPerformed

    private void txtapellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtapellidosFocusGained
        // TODO add your handling code here:
        if(txtnombres.getText().length() < 4) {
           btgenerar.setEnabled(false);
           txtnombres.requestFocus();
        }   
    }//GEN-LAST:event_txtapellidosFocusGained

    private void mnimodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnimodActionPerformed
        //al momento de hacer click derecho aparecerá el menu modificar
        //que se irá directamente con los valores de la BD a sus respectivos 
        //textfields para hacer las respectivas modificaciones
        int fila = tbusuarios.getSelectedRow();
        txtid.setEnabled(false);
        btingresar.setEnabled(false);

        if (fila >= 0) {
            txtid.setText(tbusuarios.getValueAt(fila, 0).toString());
            txtnombres.setText(tbusuarios.getValueAt(fila, 1).toString());
            txtapellidos.setText(tbusuarios.getValueAt(fila, 2).toString());     
            //cbtu.getSelectedItem(tbusuarios.getCellEditor(fila,3));    
            txtusuario.setText(tbusuarios.getValueAt(fila, 4).toString());
            //txtpass.setText(tbusuarios.getValueAt(fila, 5).toString());
            //cbidsucursal.getSelectedItem(tbusuarios.getValueAt(fila,6).toString());
            btmodificar.setEnabled(true);
            btborrar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado fila");
        }
    }//GEN-LAST:event_mnimodActionPerformed

    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        validarLetras.soloLetrasyNumeros(evt);
        //limite de caracteres
        if (txtid.getText().length() == 4) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtidKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btborrar;
    private javax.swing.JButton btbuscar;
    private javax.swing.JButton btgenerar;
    private javax.swing.JButton btingresar;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JButton btmodificar;
    private javax.swing.JButton btnuevo;
    private javax.swing.JComboBox cbidsucursal;
    private javax.swing.JComboBox cbtu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.ScrollPane jsp;
    private javax.swing.JMenuItem mnimod;
    public static javax.swing.JTable tbusuarios;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txtnombresucursal;
    private javax.swing.JTextField txtntu;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
    conectar cc= new conectar();
    Connection cn= cc.conexion();
}