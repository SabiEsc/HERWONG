
package Controllers;

import Models.codigosBarras;
import Models.consultas_codigos;
import Views.ventana_principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;
import java.util.Iterator;


public class controlador_codigos implements ActionListener{
    
    ArrayList lista_modelos = new ArrayList();
    private ventana_principal view;
    private codigosBarras codigos;
    private consultas_codigos modelo;

    public controlador_codigos(ventana_principal view, codigosBarras codigos, consultas_codigos modelo) {
        this.view = view;
        this.codigos = codigos;
        this.modelo = modelo;
        view.jButton_descargar.addActionListener(this);
    }
    
    public void inicializarCombo_modelos() {
        view.jComboBox_modelos.removeAllItems();
        lista_modelos = modelo.obtenerModelos();
        Iterator it = lista_modelos.iterator();
        
        while (it.hasNext()) {
            codigosBarras obj = (codigosBarras) it.next();
            view.jComboBox_modelos.addItem(obj.getModelo());
        }
    }
    
    public void LlamarCodigosBarra() {
        Integer valor_spinner = 0;
        
        String codigo = (String) view.jComboBox_modelos.getSelectedItem();
        valor_spinner = (Integer)view.jSpinner_cantidad.getValue();
        modelo.generarCodigosEnPDF(codigo, valor_spinner);
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == view.jButton_descargar ) {
            LlamarCodigosBarra();
        }
    }
    
    
}
