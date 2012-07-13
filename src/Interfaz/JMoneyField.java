package Interfaz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;

public class JMoneyField extends JTextField implements KeyListener {

	static String formato_moneda = "$###,###,###,###";
	static DecimalFormat df = new DecimalFormat(formato_moneda);

	public JMoneyField() {
		addKeyListener(this);
		setHorizontalAlignment(this.RIGHT);
	}

	public int getMoney() {
		String texto = super.getText();
		String nuevoTexto = "";
		for (int i = 0; i < texto.length(); i++) {
			if (Character.isDigit(texto.charAt(i))) {
				nuevoTexto += texto.charAt(i);
			}
		}
		if (!nuevoTexto.equals("")) {
			try {
				return Integer.parseInt(nuevoTexto);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("MONEY "+getMoney());
		char c = e.getKeyChar();
		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
				|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))	) {
			getToolkit().beep();
			e.consume();
		}else if(getText().length()>=12){
			getToolkit().beep();
			e.consume();			
		}else if(Character.isDigit(c)){
			e.consume();
			String tempo = getMoney()+""+c+"";
			super.setText(df.format(Integer.parseInt(tempo)));
		}else{
			super.setText(df.format(getMoney()));
		}
		
		
		
	}
	
	@Override
	public void setText(String arg0) {		
		try{
			super.setText(df.format(arg0));
		}catch (Exception e) {
			System.out.println("NO SE PUDO CONVERTIR EL TEXTO");
		}
	}
	
	

	public void darFormato(char c) {

	}

}
