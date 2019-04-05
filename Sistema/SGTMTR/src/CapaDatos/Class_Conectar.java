/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ZuluCorp
 */
public class Class_Conectar {
    
    public Connection conectar(){
        Connection cn=null;
        try{
           
            Class.forName("com.mysql.jdbc.Driver");
             cn=DriverManager.getConnection("jdbc:mysql://localhost/Techorojo","root","");
             
             /* Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                cn=DriverManager.getConnection("jdbc:sqlserver://AVALOS-PC:1433;DatabaseName=BAHIA_CONTINENTAL;","sa","123");*/
                }catch(  ClassNotFoundException | SQLException c){}
        return cn;
         }
    }

