
package Models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;


public class consultas_codigos extends conexion {
        
        Random r = new Random();
        PdfWriter pdf;
    
    public ArrayList obtenerModelos(){
        ArrayList lista = new ArrayList();
        String query = "SELECT codigo_barras, modelo FROM productos";
        
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
    
    public void generarCodigosEnPDF(String codigo, int cantidad){   
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
        String fecha = hourdateFormat.format(date);
        int codigo_aleatorio = 0;
        
        String query = "SELECT codigo_barras FROM productos WHERE modelo = '"+codigo+"';";
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
            Document doc = new Document(); //paso 1: creamos el documento

            pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigo_barras_"+fecha+".pdf")); //paso 2: instanciamos el doc y donde se creara
            doc.open(); //paso 3: abrimos el documento
            Barcode128 code128 = new Barcode128();
            
            while (i <= cantidad) {                
                codigo_aleatorio = (r.nextInt(10000)+1);
                code128.setCode(resultado_codigo);
                code128.setCodeType(Barcode128.CODE128);
                

                Image img = code128.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                img.scalePercent(220);
                img.setAlignment(Element.ALIGN_CENTER);
                
                Paragraph titulo = new Paragraph("Codigo de Fabricante: " + codigo_aleatorio, new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12));
                titulo.setAlignment(Element.ALIGN_CENTER);
                float scaler = ((doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin() - 0 / img.getWidth()) * 60);
                
                
                doc.add(titulo); //añadimos el doc
                doc.add(img);
                doc.add(new Paragraph(" "));
            i++; //iterador
            
            }
            doc.close(); //cerramos el documento
            pdf.close();
            JOptionPane.showMessageDialog(null, "Codigos de Barra Generados Correctamente!");           
            
        } catch (DocumentException | HeadlessException | IOException ex) {
            System.out.println("Error 6164:" + ex);
        }      
    }
}
