package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:38:01
 */
public class Ubicacion {

	private int codigo;
	private int dependencia;
	private String nombre;
	private int ubi_codigo;



	public void finalize() throws Throwable {

	}

	public Ubicacion(){

	}

	public int getcodigo(){
		return codigo;
	}

	public int getdependencia(){
		return dependencia;
	}

	public String getnombre(){
		return nombre;
	}

	public int getubi_codigo(){
		return ubi_codigo;
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
	public void setdependencia(int newVal){
		dependencia = newVal;
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
	public void setubi_codigo(int newVal){
		ubi_codigo = newVal;
	}

}