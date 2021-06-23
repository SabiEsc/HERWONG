
package Controllers;

import Models.consultas_inventario_tapicero;
import Models.inventarioTapicero;
import Views.ventana_principal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;


public class controlador_inventario_tapicero {
    
    private ventana_principal vista;
    private consultas_inventario_tapicero modelo;
    private inventarioTapicero it;
    DefaultTableModel tmodel = new DefaultTableModel();
    ArrayList invtapiceros = new ArrayList();

    public controlador_inventario_tapicero(ventana_principal vista, consultas_inventario_tapicero modelo, inventarioTapicero it) {
        this.vista = vista;
        this.modelo = modelo;
        this.it = it;
    }
    
    public void Inicializar_TablaTapiceros() {
        tmodel = (DefaultTableModel) vista.jTable_info.getModel();
        invtapiceros = modelo.tabla_tapiceros();
        Iterator i = invtapiceros.iterator();
        
        while (i.hasNext()) {            
            inventarioTapicero it = (inventarioTapicero) i.next();
            tmodel.addRow(new String[]{it.getCodigoBarras(), it.getModelo(), it.getNombre_tapicero(), it.getClave_fabricante(), it.getPiezas(), it.getFecha()});
        }
    }
}
