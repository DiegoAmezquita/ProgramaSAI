package interfazFacturacion;

import java.util.ArrayList;

import Codigo.Articulo;
import Codigo.Producto;

public class ItemFacturaTabla {

	int numero;
	String descripcion;
	int cantidad;
	float valorUnitarioSinIva;
	float subtotalSinIva;
	float valorUnitarioConIva;
	float subtotalConIva;
	Producto producto;
	ArrayList<Articulo> articulos;

	public ItemFacturaTabla() {
		numero = 0;
		descripcion = "";
		cantidad = 0;
		valorUnitarioSinIva = 0;
		subtotalSinIva = 0;
		valorUnitarioConIva = 0;
		subtotalConIva = 0;
		articulos = new ArrayList<Articulo>();
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

	public float getValorUnitarioSinIva() {
		return valorUnitarioSinIva;
	}

	public void setValorUnitarioSinIva(float valorUnitarioSinIva) {
		this.valorUnitarioSinIva = valorUnitarioSinIva;
	}

	public float getSubtotalSinIva() {
		return subtotalSinIva;
	}

	public void setSubtotalSinIva(float subtotalSinIva) {
		this.subtotalSinIva = subtotalSinIva;
	}

	public float getValorUnitarioConIva() {
		return valorUnitarioConIva;
	}

	public void setValorUnitarioConIva(float valorUnitarioConIva) {
		this.valorUnitarioConIva = valorUnitarioConIva;
	}

	public float getSubtotalConIva() {
		return subtotalConIva;
	}

	public void setSubtotalConIva(float subtotalConIva) {
		this.subtotalConIva = subtotalConIva;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	

	
	
	
}
