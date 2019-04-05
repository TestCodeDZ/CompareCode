package claseConectar;

import java.sql.*;
import javax.swing.*;
/**
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */

public class conectar {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
           Class.forName("com.mysql.jdbc.Driver");
           conect = DriverManager.getConnection("jdbc:mysql://localhost/techorojo","root","");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
     
}}
