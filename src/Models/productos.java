
package Models;


public class productos {
    
    private String id;
    private String codigo_barras;
    private String modelo;
    private String imagen;

    public productos() {
    }

    
    
    public productos(String id, String codigo_barras, String modelo, String imagen) {
        this.id = id;
        this.codigo_barras = codigo_barras;
        this.modelo = modelo;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
}
