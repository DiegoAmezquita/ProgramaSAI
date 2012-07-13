package Interfaz;

import javax.swing.JLabel;

public class JMandatoryLabel extends JLabel{

	
	public JMandatoryLabel(String texto,boolean obligatorio) {
		if(obligatorio){
			texto="<html>"+texto+"<FONT COLOR=RED>*</FONT></html>";
		}
		super.setText(texto);
	}
	
	
}
