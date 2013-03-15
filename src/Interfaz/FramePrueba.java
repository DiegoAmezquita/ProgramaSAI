package Interfaz;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFrame;

public class FramePrueba extends JFrame{

	
	public FramePrueba() {
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JMoneyField prueba = new JMoneyField();
		prueba.setBounds(100, 100, 100, 50);
		add(prueba);
		
		
		
	}
	
	
	public static void main(String[] args) {
//		FramePrueba a = new FramePrueba();
//		a.setVisible(true);
//		 a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 DecimalFormat format=(DecimalFormat) DecimalFormat.getInstance();
			DecimalFormatSymbols symbols=format.getDecimalFormatSymbols();
			char sep=symbols.getDecimalSeparator();
			char mill = symbols.getGroupingSeparator();
			String symbol = symbols.getCurrencySymbol();
			
			System.out.println("decimales "+sep+" miles "+mill+" simbolo moneda "+symbol);
				
	}
	
}
