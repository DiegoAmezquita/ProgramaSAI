package Codigo;

public class Articulo {

	private int idArticulo;
	private int idProductoArticulo;
	private String numeroSerie;
	private int estado;
	private String idProveedor;
	private int costoArticulo;
	private String fechaIngreso;
	private String idDocumentoSoporte;
	private String idSitio;
	private int cantidad;

	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getIdProductoArticulo() {
		return idProductoArticulo;
	}
	public void setIdProductoArticulo(int idProductoArticulo) {
		this.idProductoArticulo = idProductoArticulo;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getCostoArticulo() {
		return costoArticulo;
	}
	public void setCostoArticulo(int costoArticulo) {
		this.costoArticulo = costoArticulo;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getIdDocumentoSoporte() {
		return idDocumentoSoporte;
	}
	public void setIdDocumentoSoporte(String idDocumentoSoporte) {
		this.idDocumentoSoporte = idDocumentoSoporte;
	}
	public String getIdSitio() {
		return idSitio;
	}
	public void setIdSitio(String idSitio) {
		this.idSitio = idSitio;
	}
	
}
