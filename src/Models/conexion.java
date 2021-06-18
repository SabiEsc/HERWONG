package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class conexion {
    //com.mysql.cj.jdbc.Driver
    String usuario = "root";
    String pass = "";
    String db = "jdbc:mysql://localhost:3306/herrwong_db";
    
    public Connection getConnection(){
        Connection conexion = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(db, usuario, pass);
            
        } catch (SQLException e) {
            System.out.println("Error de conexion 123:" + e);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}
