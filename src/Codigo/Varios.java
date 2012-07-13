package Codigo;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class Varios {

	private String abreviadoVario;
	private int codVar;
	private String nombreVario;
	private int tipoNivel1Var;
	private int tipoNivel2Var;
	private int tipoNivel3Var;

	public Varios(){
            abreviadoVario="";
            nombreVario="";
            codVar=0;
            tipoNivel1Var=0;
            tipoNivel2Var=0;
            tipoNivel3Var=0;
	}

	
	public String getabreviadoVario(){
		return abreviadoVario;
	}

	public int getcodVar(){
		return codVar;
	}

	public String getnombreVario(){
		return nombreVario;
	}

	public int gettipoNivel1Var(){
		return tipoNivel1Var;
	}

	public int gettipoNivel2Var(){
		return tipoNivel2Var;
	}

	public int gettipoNivel3Var(){
		return tipoNivel3Var;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setabreviadoVario(String newVal){
		abreviadoVario = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setcodVar(int newVal){
		codVar = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void setnombreVario(String newVal){
		nombreVario = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void settipoNivel1Var(int newVal){
		tipoNivel1Var = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void settipoNivel2Var(int newVal){
		tipoNivel2Var = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	public void settipoNivel3Var(int newVal){
		tipoNivel3Var = newVal;
	}

}