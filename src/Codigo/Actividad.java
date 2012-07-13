package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:37:23
 */
public class Actividad {

    private int codAct;
    private int codEle;
    private String codLog;
    private String descripcion;
    private String fechaCreacion;

    public Actividad() {
    }

    public int getCodAct() {
        return codAct;
    }

    public void setCodAct(int codAct) {
        this.codAct = codAct;
    }

    public int getCodEle() {
        return codEle;
    }

    public void setCodEle(int codEle) {
        this.codEle = codEle;
    }

    public String getCodLog() {
        return codLog;
    }

    public void setCodLog(String codLog) {
        this.codLog = codLog;
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
}
