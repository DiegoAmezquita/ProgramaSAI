package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:19:31
 */
public class Producto {

	 private int idProducto;
	 private int idCategoriaProducto;
	 private int idMarcaProducto;
	 private String referenciaProducto;
	 private String descripcionProducto;
	 private String numeroParteProducto;
	 private String EANCodeProducto;
	 private String UPCCodeProducto;
	 private String pluProducto;
	 private float margenProducto;
	 private String fechaActualizacionProducto;
	 private int existenciasProducto;
	 private int precioProducto;
	 private int costoProducto;
	 private String abreviadoProducto;
	 private float margenMinimo;
	 private int idTipoProducto;
	 private float ivaProducto;
	 private int garantiaMeses;
	 
	 //si tiene serial es 1 es que si tiene serial de lo contrario se maneja por cantidad
	 private int tieneSerial;

    public Producto() {
        idProducto = 0;
        margenProducto = 0;
        existenciasProducto = 0;
        margenMinimo = 0;
        idTipoProducto = 0;
        tieneSerial=0;
        precioProducto=-1;
        costoProducto=-1;
        garantiaMeses = 3;
    }
    
    

	public int getGarantiaMeses() {
		return garantiaMeses;
	}



	public void setGarantiaMeses(int garantiaMeses) {
		this.garantiaMeses = garantiaMeses;
	}



	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdCategoriaProducto() {
		return idCategoriaProducto;
	}

	public void setIdCategoriaProducto(int idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public int getIdMarcaProducto() {
		return idMarcaProducto;
	}

	public void setIdMarcaProducto(int idMarcaProducto) {
		this.idMarcaProducto = idMarcaProducto;
	}

	public String getReferenciaProducto() {
		return referenciaProducto;
	}

	public void setReferenciaProducto(String referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getNumeroParteProducto() {
		return numeroParteProducto;
	}

	public void setNumeroParteProducto(String numeroParteProducto) {
		this.numeroParteProducto = numeroParteProducto;
	}

	public String getEANCodeProducto() {
		return EANCodeProducto;
	}

	public void setEANCodeProducto(String eANCodeProducto) {
		EANCodeProducto = eANCodeProducto;
	}

	public String getUPCCodeProducto() {
		return UPCCodeProducto;
	}

	public void setUPCCodeProducto(String uPCCodeProducto) {
		UPCCodeProducto = uPCCodeProducto;
	}

	public String getPluProducto() {
		return pluProducto;
	}

	public void setPluProducto(String pluProducto) {
		this.pluProducto = pluProducto;
	}

	public float getMargenProducto() {
		return margenProducto;
	}

	public void setMargenProducto(float margenProducto) {
		this.margenProducto = margenProducto;
	}

	public String getFechaActualizacionProducto() {
		return fechaActualizacionProducto;
	}

	public void setFechaActualizacionProducto(String fechaActualizacionProducto) {
		this.fechaActualizacionProducto = fechaActualizacionProducto;
	}

	public int getExistenciasProducto() {
		return existenciasProducto;
	}

	public void setExistenciasProducto(int existenciasProducto) {
		this.existenciasProducto = existenciasProducto;
	}

	public int getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}

	public int getCostoProducto() {
		return costoProducto;
	}

	public void setCostoProducto(int costoProducto) {
		this.costoProducto = costoProducto;
	}

	public String getAbreviadoProducto() {
		return abreviadoProducto;
	}

	public void setAbreviadoProducto(String abreviadoProducto) {
		this.abreviadoProducto = abreviadoProducto;
	}

	public float getMargenMinimo() {
		return margenMinimo;
	}

	public void setMargenMinimo(float margenMinimo) {
		this.margenMinimo = margenMinimo;
	}

	public int getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public float getIvaProducto() {
		return ivaProducto;
	}

	public void setIvaProducto(float ivaProducto) {
		this.ivaProducto = ivaProducto;
	}

	public int getTieneSerial() {
		return tieneSerial;
	}

	public void setTieneSerial(int tieneSerial) {
		this.tieneSerial = tieneSerial;
	}

	
    
    
}
