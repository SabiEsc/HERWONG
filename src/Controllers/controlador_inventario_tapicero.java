
package Controllers;

import Models.consultas_inventario_tapicero;
import Models.inventarioTapicero;
import Views.ventana_principal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import Models.usuarios;
import Models.codigosBarras;


public class controlador_inventario_tapicero {
    
    private ventana_principal vista;
    private consultas_inventario_tapicero modelo;
    private inventarioTapicero it;
    DefaultTableModel tmodel = new DefaultTableModel();
    ArrayList invtapiceros = new ArrayList();
    ArrayList NombresModelos = new ArrayList();
    ArrayList trabajadores = new ArrayList();

    public controlador_inventario_tapicero(ventana_principal vista, consultas_inventario_tapicero modelo, inventarioTapicero it) {
        this.vista = vista;
        this.modelo = modelo;
        this.it = it;
    }
    /******************************************************************************************************************/
    public void Inicializar_TablaTapiceros() {
        tmodel = (DefaultTableModel) vista.jTable_info.getModel();
        invtapiceros = modelo.tabla_tapiceros();
        Iterator i = invtapiceros.iterator();
        
        while (i.hasNext()) {            
            inventarioTapicero it = (inventarioTapicero) i.next();
            tmodel.addRow(new String[]{it.getCodigoBarras(), it.getModelo(), it.getNombre_tapicero(), it.getClave_fabricante(), it.getPiezas(), it.getFecha()});
        }
    }
    
    public void Inicializar_ComboTrabajadores(){
        vista.jComboBox_usuarios.removeAllItems();
        trabajadores = modelo.obtenerEmpleados();
        Iterator i = trabajadores.iterator();
        
        while (i.hasNext()) {            
            usuarios u = (usuarios) i.next();
            vista.jComboBox_usuarios.addItem(u.getNombre());
        }
    }
    
    public void Inicializar_ComboModelos(){
        vista.jComboBox_Modelo.removeAllItems();
        NombresModelos = modelo.obtenerModelos();
        Iterator i = NombresModelos.iterator();
        
        while (i.hasNext()) {
            codigosBarras cb = (codigosBarras) i.next();
            vista.jComboBox_Modelo.addItem(cb.getModelo());
        }
    }
    /********************************************************************************************************************/
}
