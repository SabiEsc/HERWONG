
package Models;


public class inventarioTapicero {
    
    public String codigoBarras;
    public String modelo;
    public String nombre_tapicero;
    public String clave_fabricante;
    public String piezas;
    public String fecha;
    public int idproductos;
    public int idusuarios;

    public inventarioTapicero(String codigoBarras, String modelo, String nombre_tapicero, String clave_fabricante, String piezas, String fecha) {
        this.codigoBarras = codigoBarras;
        this.modelo = modelo;
        this.nombre_tapicero = nombre_tapicero;
        this.clave_fabricante = clave_fabricante;
        this.piezas = piezas;
        this.fecha = fecha;
    }
    
    public inventarioTapicero(int idproducto, int idusuario, String clavefabricante, String piezas){
        this.idproductos = idproducto;
        this.idusuarios = idusuario;
        this.clave_fabricante = clavefabricante;
        this.piezas = piezas;
    }
    
    
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombre_tapicero() {
        return nombre_tapicero;
    }

    public void setNombre_tapicero(String nombre_tapicero) {
        this.nombre_tapicero = nombre_tapicero;
    }

    public String getClave_fabricante() {
        return clave_fabricante;
    }

    public void setClave_fabricante(String clave_fabricante) {
        this.clave_fabricante = clave_fabricante;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }
    
    public inventarioTapicero() {
    }
    
}
