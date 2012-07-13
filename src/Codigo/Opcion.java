package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:37:45
 */
public class Opcion {

	private int codigo;
	private String descripcion;
	private int nivel;
	private String nombre;
	private String pagina;

	public Opcion(){

	}

	public void finalize() throws Throwable {

	}

	public int getcodigo(){
		return codigo;
	}

	public String getdescripcion(){
		return descripcion;
	}

	public int getnivel(){
		return nivel;
	}

	public String getnombre(){
		return nombre;
	}

	public String getpagina(){
		return pagina;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcodigo(int newVal){
		codigo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnivel(int newVal){
		nivel = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setpagina(String newVal){
		pagina = newVal;
	}

}