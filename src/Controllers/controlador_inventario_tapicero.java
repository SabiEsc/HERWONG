
package Controllers;

import Models.consultas_inventario_tapicero;
import Models.inventarioTapicero;
import Views.ventana_principal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import Models.usuarios;
import Models.codigosBarras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class controlador_inventario_tapicero implements ActionListener{
    
    private ventana_principal vista;
    private consultas_inventario_tapicero modelo;
    private inventarioTapicero it;
    DefaultTableModel tmodel = new DefaultTableModel();
    ArrayList invtapiceros = new ArrayList();
    ArrayList NombresModelos = new ArrayList();
    ArrayList trabajadores = new ArrayList();

    public controlador_inventario_tapicero() {
    }
    
    

    public controlador_inventario_tapicero(ventana_principal vista, consultas_inventario_tapicero modelo, inventarioTapicero it) {
        this.vista = vista;
        this.modelo = modelo;
        this.it = it;
        //inicializar algunas otras cosas
        Calendar cal = new GregorianCalendar();
        vista.jButton_mostrar.addActionListener(this);
        vista.jButton_resetear.addActionListener(this);
        vista.fecha1.setCalendar(cal);
        vista.fecha2.setCalendar(cal);
    }
    /******************************************************************************************************************/
    public void Inicializar_TablaTapiceros() {
        tmodel = (DefaultTableModel) vista.jTable_info.getModel();
        tmodel.setRowCount(0);
        invtapiceros = modelo.tabla_tapiceros();
        Iterator i = invtapiceros.iterator();
        
        while (i.hasNext()) {            
            inventarioTapicero it = (inventarioTapicero) i.next();
            tmodel.addRow(new String[]{it.getCodigoBarras(), it.getModelo(), it.getNombre_tapicero(), it.getClave_fabricante(), it.getPiezas(), it.getFecha()});
        }
    }
    
    public void Inicializar_ComboTrabajadores(){
        vista.jComboBox_usuarios.removeAllItems(); //removemos todos los items que vienen por defecto
        trabajadores = modelo.obtenerEmpleados();
        Iterator i = trabajadores.iterator();
        
        while (i.hasNext()) {            
            usuarios u = (usuarios) i.next();
            vista.jComboBox_usuarios.addItem(u.getNombre());
        }
    }
    
    public void Inicializar_ComboModelos(){
        vista.jComboBox_Modelo.removeAllItems(); //Removemos todos los items que vienne por defecto
        vista.jComboBox_Modelo.addItem(" -- VER TODOS -- ");
        NombresModelos = modelo.obtenerModelos();
        Iterator i = NombresModelos.iterator();
        
        while (i.hasNext()) {
            codigosBarras cb = (codigosBarras) i.next();
            vista.jComboBox_Modelo.addItem(cb.getModelo());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_mostrar) {
            DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd"); //objeto para convertir fecha
            DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd"); //objeto para convertir fecha

            Date fecha1 = vista.fecha1.getDate();
            Date fecha2 = vista.fecha2.getDate();
            String usuario = vista.jComboBox_usuarios.getSelectedItem().toString();
            String modelos = vista.jComboBox_Modelo.getSelectedItem().toString();
        
            if (modelos.equals(" -- VER TODOS -- ")) {

                tmodel = (DefaultTableModel) vista.jTable_info.getModel();
                tmodel.setRowCount(0);
                invtapiceros = modelo.Consulta_Tabla1(usuario, fmt1.format(fecha1), fmt1.format(fecha2));
                
                if (invtapiceros.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No existen registros en la Base de Datos");
                } else {
                Iterator i = invtapiceros.iterator();
                

                while (i.hasNext()) {            
                    inventarioTapicero it = (inventarioTapicero) i.next();
                    tmodel.addRow(new String[]{it.getCodigoBarras(), it.getModelo(), it.getNombre_tapicero(), it.getClave_fabricante(), it.getPiezas(), it.getFecha()});
                    
                    }
                }
            } else {
                tmodel = (DefaultTableModel) vista.jTable_info.getModel();
                tmodel.setRowCount(0);
                invtapiceros = modelo.Consulta_Tabla2(modelos, usuario, fmt1.format(fecha1), fmt1.format(fecha2));
                
                if (invtapiceros.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No existen registros en la Base de Datos");
                } else {
                Iterator i = invtapiceros.iterator();
                

                while (i.hasNext()) {            
                    inventarioTapicero it = (inventarioTapicero) i.next();
                    tmodel.addRow(new String[]{it.getCodigoBarras(), it.getModelo(), it.getNombre_tapicero(), it.getClave_fabricante(), it.getPiezas(), it.getFecha()});
                    
                    }
                }
            }
        } else if( e.getSource() == vista.jButton_resetear) {
                Inicializar_TablaTapiceros();
        }
    }
    /********************************************************************************************************************/
}
