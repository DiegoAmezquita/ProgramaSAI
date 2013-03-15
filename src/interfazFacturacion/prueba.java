package interfazFacturacion;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class prueba {

	
	
	public static void main(String[] args) {
		float numero = 0.0f;
		
		
		int numero2 = (int)numero;
		DecimalFormat df = new DecimalFormat("#,###,###,###.##");
		System.out.println(df.format(numero));
		System.out.println("PRUEBA DAÑO");
		System.out.println(Float.parseFloat("0,00"));
	}
}
