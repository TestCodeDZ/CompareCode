/*package sgtmtr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {

    Connection conexion;
    Statement instrucion;

    public Reportes() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Creamos un enlace hacia la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/techorojo", "root", "");
            instrucion = conexion.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

//METODO PARA EXPORTAR A PDF UN REPORTE   
    public void resportesPDF(String ruta, String archi) {
        try {
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion);
            JasperExportManager.exportReportToPdfFile(informe, archi);

            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("INFORME");
            ventanavisor.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
        }
    }

}*/
//esto iria en el form que se usaria para exportar a pdf
 /*public void guardapdf() {
        Reportes re = new Reportes();//CREAMOS UN OBJETO DE LA CLASE REPORTES
        String ruta = "SGTMTR\\reporteevtains.jasper";//RUTA DONDE TIENEN SU REPORTE --
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    printwriter.print(ruta);
                }
                re.resportesPDF(ruta, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo
                }
                JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos,espere porfavor", "Estamos Generando el Reporte", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
