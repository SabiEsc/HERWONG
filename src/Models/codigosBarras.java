
package Models;

public class codigosBarras {
    
    private String modelo;
    private String codigo;
    
    public codigosBarras(){
    }

    public codigosBarras(String modelo, String codigo) {
        this.modelo = modelo;
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
