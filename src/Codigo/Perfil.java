package Codigo;

/**
 * @version 1.0
 * @created 16-Mar-2011 15:37:54
 */
public class Perfil {

	private int codigo;
	private String nombre;

	public Perfil(){

	}

	public void finalize() throws Throwable {

	}

	public int getcodigo(){
		return codigo;
	}

	public String getnombre(){
		return nombre;
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
	public void setnombre(String newVal){
		nombre = newVal;
	}

}