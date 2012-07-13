package Codigo;

public class PerfilProducto {

	private int id;
	private String nombre;
	private float margenMinimo;
	private float margenMaximo;
	private float margenSugerido;
	private float iva;
	private int tieneSerial;
	private int garantiaMeses;
	
	
	
	
	public int getGarantiaMeses() {
		return garantiaMeses;
	}
	public void setGarantiaMeses(int garantiaMeses) {
		this.garantiaMeses = garantiaMeses;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMargenMinimo() {
		return margenMinimo;
	}
	public void setMargenMinimo(float margenMinimo) {
		this.margenMinimo = margenMinimo;
	}
	public float getMargenMaximo() {
		return margenMaximo;
	}
	public void setMargenMaximo(float margenMaximo) {
		this.margenMaximo = margenMaximo;
	}
	public float getMargenSugerido() {
		return margenSugerido;
	}
	public void setMargenSugerido(float margenSugerido) {
		this.margenSugerido = margenSugerido;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}
	public int getTieneSerial() {
		return tieneSerial;
	}
	public void setTieneSerial(int tieneSerial) {
		this.tieneSerial = tieneSerial;
	}
	
	
	
	
}
