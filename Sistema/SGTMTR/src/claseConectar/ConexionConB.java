package claseConectar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionConBaseDatos {

    public static Connection conexion = null;

    public static Connection getConexion() {
        try {

            conexion = null;
            String servidorBD = "jdbc:mysql://localhost/techorojo";
            String usuarioDB = "root";
            String passwordDB = "";
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(servidorBD, usuarioDB, passwordDB);

            //JOptionPane.showMessageDialog(null, "Operacion Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion " + e);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en el driver" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Desconocido (conexionBD)" + e);
        }

        return conexion;
    }//cierra metodo obtenerConexion

    public static void metodoCerrarConexiones(Connection conexion) {

        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "No se pudo cerrar Connection (conexion)\n Error " + e1);
            }
            conexion = null;
        }
    }//cierra metodo
}//fin class
