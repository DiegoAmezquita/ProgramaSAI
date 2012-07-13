package interfazProductos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


public class menu_grafico3
{
	static BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
	static String formato_moneda="$###,###,###"; //declarando la variable con un tipo de formato
	static DecimalFormat df=new DecimalFormat(formato_moneda); //aplicando el formato a la variable formato_moneda
	public static void main (String args[])throws IOException
	{
		int salario;
		System.out.println("ingrese su salario");
		salario=Integer.parseInt(leer.readLine());
		System.out.println("su salario es :"+df.format(salario)); // al resultado salario aplicamos el formato
	}
}

