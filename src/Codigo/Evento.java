package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:37:41
 */
public class Evento {

    private int codigo;
    private int codigoElemento;
    private String descripcion;
    private String fechaCreacion;
    private boolean leido = false;
    private int responsableEven;
    private String tipo;
    private int siguienteEven;

    public Evento() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoElemento() {
        return codigoElemento;
    }

    public void setCodigoElemento(int codigoElemento) {
        this.codigoElemento = codigoElemento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public int getResponsableEven() {
        return responsableEven;
    }

    public void setResponsableEven(int responsableEven) {
        this.responsableEven = responsableEven;
    }

    public int getSiguienteEven() {
        return siguienteEven;
    }

    public void setSiguienteEven(int siguienteEven) {
        this.siguienteEven = siguienteEven;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
