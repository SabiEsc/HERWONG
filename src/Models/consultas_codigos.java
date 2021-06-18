
package Models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class consultas_codigos extends conexion {

        PdfWriter pdf;
        Document doc = new Document();
    
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
            int i = 1;
            pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigobarrasnuevo.pdf"));
            doc.open();
            Barcode128 code128 = new Barcode128();
            
            while (i <= 6) {                
                
                code128.setCode(resultado_codigo);
                code128.setCodeType(Barcode128.CODE128);
                

                Image img = code128.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                img.scalePercent(250);
                
                Paragraph titulo = new Paragraph("XD", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12));
                float scaler = ((doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin() - 0 / img.getWidth()) * 60);
                
                
                doc.add(titulo);
                doc.add(img);
                doc.add(new Paragraph(" "));
            i++; //iterador
            }
            doc.close();
            
            JOptionPane.showMessageDialog(null, "Codigos de Barra Generados Correctamente!");           
            Desktop.getDesktop().open(new File("codigobarrasnuevo.pdf"));
            
        } catch (DocumentException | HeadlessException | IOException ex) {
            System.out.println("Error 6164:" + ex);
        }      
    }
}
