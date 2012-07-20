package interfazInventario;

import java.util.ArrayList;

import Codigo.Producto;

public class ItemInventario {

	int numero;
	String descripcion;
	int cantidad;
	int costoUnitario;
	int existencias;
	int precioActual;
	int precioSugerido;
	int precio;
	Producto producto;
	ArrayList<String> seriales;

	public ItemInventario() {
		numero = 0;
		descripcion = "";
		cantidad = 0;
		costoUnitario = 0;
		existencias = 0;
		precioActual = 0;
		precioSugerido = 0;
		precio = 0;
		seriales = new ArrayList<String>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(int costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public int getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(int precioActual) {
		this.precioActual = precioActual;
	}

	public int getPrecioSugerido() {
		return precioSugerido;
	}

	public void setPrecioSugerido(int precioSugerido) {
		this.precioSugerido = precioSugerido;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ArrayList<String> getSeriales() {
		return seriales;
	}

	public void setSeriales(ArrayList<String> seriales) {
		this.seriales = seriales;
	}

	
	
	
}
