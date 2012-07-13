package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:19:31
 */
public class Persona {

    private String apellido;
    private int codigo;
    private int digitoChequeo;
    private int digitoControl;
    private int estado;
    private String fechaNacimiento;
    private String info;
    private String nombre;
    private int numeroDocumento;
    private int tipo;
    private int tipoDocumento;
    private int genero;

    public Persona() {
        digitoChequeo = 0;
        digitoControl= 0;
        genero = 0;
        tipo = 0;
        tipoDocumento=0;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDigitoChequeo() {
        return digitoChequeo;
    }

    public void setDigitoChequeo(int digitoChequeo) {
        this.digitoChequeo = digitoChequeo;
    }

    public int getDigitoControl() {
        return digitoControl;
    }

    public void setDigitoControl(int digitoControl) {
        this.digitoControl = digitoControl;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
    
}
