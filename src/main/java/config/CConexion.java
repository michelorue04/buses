

package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CConexion {
    Connection conectar= null;
    String usuario="root";
    String contrasenia="miranda";
    String bd="Buses70";
    String ip="localhost";
    String puerto="3306";
    
    String cadena="jdbc:mysql://"+ip +":"+puerto+"/"+bd;
    
    public CConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,contrasenia);
        } catch(ClassNotFoundException | SQLException e) {           
        }
     }   
    public Connection getConexion(){
        return conectar;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

