package Codigo;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class Login {

    private String claveLog;
    private int codigoPers;
    private String nombreLog;
    private int codigoLog;
    private int tipoUsuario;

    public Login() {
        nombreLog = "";
        claveLog = "";
        codigoPers = 0;
        codigoLog = 0;
        tipoUsuario = 0;
    }


    public String getClaveLog() {
        return claveLog;
    }

    public void setClaveLog(String claveLog) {
        this.claveLog = claveLog;
    }

    public int getCodigoLog() {
        return codigoLog;
    }

    public void setCodigoLog(int codigoLog) {
        this.codigoLog = codigoLog;
    }

    public int getCodigoPers() {
        return codigoPers;
    }

    public void setCodigoPers(int codigoPers) {
        this.codigoPers = codigoPers;
    }

    public String getNombreLog() {
        return nombreLog;
    }

    public void setNombreLog(String nombreLog) {
        this.nombreLog = nombreLog;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


}
