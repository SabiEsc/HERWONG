
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


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
    
    public boolean InsertarProducto(String idproducto, String idusuarios, String clavefabricante, String piezas) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = fmt.format(new Date(System.currentTimeMillis()));
        
        
        String query = "INSERT INTO inventario_tapicero (id, idproductos, idusuarios, clave_fabricante, piezas, fecha)"
                + " VALUES (NULL, '"+idproducto+"', '"+idusuarios+"', '"+clavefabricante+"', '"+piezas+"', '"+fecha+"');";
    
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();)
        {
            int resultado = st.executeUpdate(query);
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL "+e);
            return false;
        }
    } 
    
    public String IdEmpleado(String nombre, String pass){
        String query = "SELECT id FROM usuarios WHERE nombre = '"+nombre+"' AND password = '"+pass+"'";
        String resultado = "";
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query))
        {
            while (rs.next()) {                
                resultado = rs.getString("id");
            }
        } catch(SQLException E) {
        
        }
        return resultado;
    }
}

