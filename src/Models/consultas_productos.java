
package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class consultas_productos extends conexion {
    
    public ArrayList BuscarProducto(String codigo){
        ArrayList lista = new ArrayList();
        String query = "SELECT id, codigo_barras, modelo, Imagenes FROM `productos` WHERE codigo_barras = '"+codigo+"'";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {                
                productos p = new productos(rs.getString("id").toString(), rs.getString("codigo_barras"), rs.getString("modelo"), rs.getString("Imagenes"));
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTAS PRODUCTOS 390: " + ex);
        }
        return lista;
    }
}
