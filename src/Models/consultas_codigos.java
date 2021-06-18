
package Models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class consultas_codigos extends conexion {

    
    public ArrayList obtenerModelos(){
        ArrayList lista = new ArrayList();
        String query = "SELECT codigo_barras, modelo FROM inventario_general";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) 
        {
            while(rs.next()){
            codigosBarras codigos = new codigosBarras(rs.getString("modelo"), rs.getString("codigo_barras"));
            lista.add(codigos);
            }
        } catch (SQLException ex) {
            System.out.println("Error 3948:" + ex);
        }
        return lista;
    }
    
    public void generarCodigosEnPDF(String codigo){
        
        String query = "SELECT codigo_barras FROM inventario_general WHERE modelo = '"+codigo+"';";
        System.out.println(query);
        String resultado_codigo = "";
        
        try (Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query))
        {
            if (rs.next()) {
                resultado_codigo = rs.getString("codigo_barras");
            }
        } catch (SQLException ex) {
            System.out.println("Error 351: " + ex + " No se pudo pana");
        }
        
        try {
            
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigos.pdf"));
            doc.open();
            
            Barcode128 code128 = new Barcode128();
            code128.setCode(resultado_codigo);
            Image img = code128.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            doc.add(img);
            
            doc.close();
            
            JOptionPane.showMessageDialog(null, "Codigos de Barra Generados Correctamente!");
            
        } catch (Exception ex) {
            System.out.println("Error 6164:" + ex);
        }      
    }
}
