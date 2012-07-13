package Codigo;

public class FacturaVenta {

	private int idFactura;
	private String prefijoFactura;
	private String numeroFactura;
	private String fechaFactura;
	private int idCliente;
	private String nitCliente;
	private String digitoCliente;
	private String direccionCliente;
	private String ciudadClient;
	private String telefonoCliente;
	private double baseIva;
	private double iva;
	private double totalFactura;
	private String fechaVence;
	private String fechaAceptada;
	private int estado;
	
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getPrefijoFactura() {
		return prefijoFactura;
	}
	public void setPrefijoFactura(String prefijoFactura) {
		this.prefijoFactura = prefijoFactura;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNitCliente() {
		return nitCliente;
	}
	public void setNitCliente(String nitCliente) {
		this.nitCliente = nitCliente;
	}
	public String getDigitoCliente() {
		return digitoCliente;
	}
	public void setDigitoCliente(String digitoCliente) {
		this.digitoCliente = digitoCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getCiudadClient() {
		return ciudadClient;
	}
	public void setCiudadClient(String ciudadClient) {
		this.ciudadClient = ciudadClient;
	}
	public String getTelefonoCliente() {
		return telefonoCliente;
	}
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	public double getBaseIva() {
		return baseIva;
	}
	public void setBaseIva(double baseIva) {
		this.baseIva = baseIva;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}
	public String getFechaVence() {
		return fechaVence;
	}
	public void setFechaVence(String fechaVence) {
		this.fechaVence = fechaVence;
	}
	public String getFechaAceptada() {
		return fechaAceptada;
	}
	public void setFechaAceptada(String fechaAceptada) {
		this.fechaAceptada = fechaAceptada;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
	
}
