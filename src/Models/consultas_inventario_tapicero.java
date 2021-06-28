
package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class consultas_inventario_tapicero extends conexion {
    
    public ArrayList tabla_tapiceros(){
        ArrayList lista = new ArrayList();
        String query = "SELECT p.codigo_barras as 'Codigo Barras', p.modelo as 'Modelo', u.nombre as 'Tapicero', it.clave_fabricante as 'Clave Fabricante', it.piezas as 'Piezas', it.fecha as 'Fecha' FROM productos as p\n" +
                "INNER JOIN inventario_tapicero as it ON p.id = it.idproductos\n" +
                "INNER JOIN usuarios as u ON u.id = it.idusuarios";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {                
                inventarioTapicero it = new inventarioTapicero(rs.getString("Codigo Barras"), rs.getString("Modelo"), rs.getString("Tapicero"), rs.getString("Clave Fabricante"), rs.getString("Piezas"), rs.getString("Fecha"));
                lista.add(it);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTAS INVENTARIO 390: " + ex);
        }
        return lista;
    }
    
    public ArrayList obtenerModelos(){
        ArrayList lista = new ArrayList();
        String query = "SELECT modelo FROM productos";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) 
        {
            while(rs.next()){
            codigosBarras codigos = new codigosBarras(rs.getString("modelo"));
            lista.add(codigos);
            }
        } catch (SQLException ex) {
            System.out.println("Error 3944:" + ex);
        }
        return lista;
    }
    
    public ArrayList obtenerEmpleados(){
        ArrayList lista = new ArrayList();
        String query = "SELECT nombre FROM usuarios";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) 
        {
            while(rs.next()){
            usuarios user = new usuarios(rs.getString("nombre"));
            lista.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error 3944:" + ex);
        }
        return lista;
    }    
    
    public ArrayList Consulta_Tabla1(String usuario, String fecha1, String fecha2){
        ArrayList lista = new ArrayList();
        String query = "SELECT p.codigo_barras as 'Codigo Barras', p.modelo as 'Modelo', u.nombre as 'Tapicero', it.clave_fabricante as 'Clave Fabricante', it.piezas as 'Piezas', it.fecha as 'Fecha' FROM productos as p"
                     + " INNER JOIN inventario_tapicero as it ON p.id = it.idproductos\n" +
                        "INNER JOIN usuarios as u ON u.id = it.idusuarios\n" +
                        "WHERE U.nombre = '"+usuario+"' AND it.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) 
        {
            if (rs.getRow() == 0 ) {
                System.out.println("esta vacio");
                System.out.println(query);
                System.out.println("");
                
            } else {
                System.out.println("no esta vacio");
            }
            
            
            while (rs.next()) {                
                inventarioTapicero it = new inventarioTapicero(rs.getString("Codigo Barras"), rs.getString("Modelo"), rs.getString("Tapicero"), rs.getString("Clave Fabricante"), rs.getString("Piezas"), rs.getString("Fecha"));
                lista.add(it);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error de consulta 6985:" + ex);
        }
        
        return lista;
    }
    
    public ArrayList Consulta_Tabla2(String modelo, String usuario, String fecha1, String fecha2){
        ArrayList lista = new ArrayList();
        String query = "SELECT p.codigo_barras as 'Codigo Barras', p.modelo as 'Modelo', u.nombre as 'Tapicero', it.clave_fabricante as 'Clave Fabricante', it.piezas as 'Piezas', it.fecha as 'Fecha' FROM productos as p INNER JOIN inventario_tapicero as it ON p.id = it.idproductos\n" +
                       "INNER JOIN usuarios as u ON u.id = it.idusuarios\n" +
                       "WHERE U.nombre = '"+usuario+"' AND p.modelo = '"+modelo+"' AND it.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) 
        {
            
            while (rs.next()) {                
                inventarioTapicero it = new inventarioTapicero(rs.getString("Codigo Barras"), rs.getString("Modelo"), rs.getString("Tapicero"), rs.getString("Clave Fabricante"), rs.getString("Piezas"), rs.getString("Fecha"));
                lista.add(it);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error de consulta 6985:" + ex);
        }
        
        return lista;
    }
}

