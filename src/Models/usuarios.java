
package Models;


public class usuarios {
    
    private String nombre;
    private String clave; 

    public usuarios(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public usuarios(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
