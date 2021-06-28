
package Controllers;

import Models.codigosBarras;
import Models.consultas_productos;
import java.util.ArrayList;
import Models.productos;
import Views.ventana_principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class controlador_productos implements ActionListener {
    
    ArrayList productos = new ArrayList();
    private productos pro;
    private ventana_principal vista;
    private consultas_productos modelo;
    public String codigoCapturado = "";
    public String idproducto = "";
    public String Modelo = "";
    public String imagen = "";

    public controlador_productos(productos pro, ventana_principal vista, consultas_productos modelo) {
        this.pro = pro;
        this.vista = vista;
        this.modelo = modelo;
        vista.jTextField_CodigoBarra.addActionListener(this);
        
    }
    
    public void producto_encontrado(){
        
        codigoCapturado = vista.jTextField_CodigoBarra.getText();
        productos = modelo.BuscarProducto(codigoCapturado);
        
        if (productos.size() == 0) {
        
            JOptionPane.showMessageDialog(null, "Error, se detecto un codigo distinto, porfavor, vuelva a intentar",
                    "Advertencia", JOptionPane.ERROR_MESSAGE);
        
        } else {
            Iterator i = productos.iterator();

            while (i.hasNext()) {
                productos obj = (productos) i.next();

                codigoCapturado = obj.getCodigo_barras();
                idproducto = obj.getId();
                Modelo = obj.getModelo();
                imagen = obj.getImagen();
                System.out.println(imagen);
                vista.label_codigodetectado.setText("CODIGO DE BARRAS DETECTADO: "+codigoCapturado);
                vista.label_idproducto.setText("ID PRODUCTO: "+idproducto);
                vista.label_modeloproducto.setText("MODELO :"+Modelo);

                ImageIcon img = new ImageIcon(getClass().getResource("/img/"+imagen));
                ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance(vista.label_imagenproducto.getWidth(), vista.label_imagenproducto.getHeight(), 0));
                vista.label_imagenproducto.setIcon(icono);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jTextField_CodigoBarra) {
            System.out.println("Jtextfield con Action Funcionando");
            producto_encontrado();
            vista.jTextField_CodigoBarra.setText("");
        }
    }
}
