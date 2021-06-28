
package Controllers;

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
    controlador_inventario_tapicero cit = new controlador_inventario_tapicero();

    public controlador_productos(productos pro, ventana_principal vista, consultas_productos modelo) {
        this.pro = pro;
        this.vista = vista;
        this.modelo = modelo;
        vista.jTextField_CodigoBarra.addActionListener(this);
        vista.jButton_agregar.addActionListener(this);
        
        ImageIcon img = new ImageIcon(getClass().getResource("/img/img_prueba.jpg"));
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance(vista.label_imagenproducto.getWidth(), vista.label_imagenproducto.getHeight(), 0));
        vista.label_imagenproducto.setIcon(icono);
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
    
    public void limpiarCampos(){ //pendiente
    
    }
    
    public void InsertarProductoTapicero(){
        String nombreTapicero = vista.jTextField_tapicero.getText();
        String passTapicero = new String(vista.jPassword_contrase.getPassword());
        String cantidadPiezas = vista.jtextfield_piezas.getText();
        String codigoFabricante = vista.jTextField_claveFabricante.getText();
        String claveEncargado = new String(vista.jPasswordField_contraEncargado.getPassword());
        
        if (nombreTapicero.equals("") || passTapicero.equals("") || cantidadPiezas.equals("") || codigoFabricante.equals("") || claveEncargado.equals("")) {
        
            JOptionPane.showMessageDialog(null, "Cuidado, algunos campos estan vacios, porfavor, llenelos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        
        } else {
            int confirmacion = JOptionPane.showConfirmDialog(null, 
                    "Esta deacuerdo que realizo correctamente \n"
                  + " la inspeccion del producto final y que fue \n"
                  + "realizada por el tapicero que corresponde?",
                    "Cuidado", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (confirmacion == 0) {
                String idusuario = modelo.IdEmpleado(nombreTapicero, passTapicero); 
                if (claveEncargado.equals(vista.CLAVE_ENCARGADO) && idusuario != "") {
                    
                    boolean bandera = modelo.InsertarProducto(idproducto, idusuario, codigoFabricante, cantidadPiezas);
                    if (bandera == true) {
                    
                        JOptionPane.showMessageDialog(null, "Exito! Agregado Correctamente");
                        cit.Inicializar_TablaTapiceros(); //refrescamos la tabla tapiceros 
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, no se pudo...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La clave del encargado o la clave del tapicero es INCORRECTA",
                            "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                }                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jTextField_CodigoBarra) {
            producto_encontrado();
            vista.jTextField_CodigoBarra.setText("");
        
        } else if(e.getSource() == vista.jButton_agregar) {
            InsertarProductoTapicero();
        }
    }
}
