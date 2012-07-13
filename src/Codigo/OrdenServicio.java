package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:37:50
 */
public class OrdenServicio {

	private int codigo;
	private String fechaCreacion;
	private int numero;
        private String motivo;
        private int codPersDuenio;
        private int usuarioRecibe;




	public OrdenServicio(){

	}

   
 

    public int getCodPersDuenio() {
		return codPersDuenio;
	}




	public void setCodPersDuenio(int codPersDuenio) {
		this.codPersDuenio = codPersDuenio;
	}




	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(int usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }




}