package interfazProductos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.DAOImagen;
import DAO.DAOvarios;
import Interfaz.FrameMain;

public class PruebaProducto extends JFrame{

	private JLabel labelUPC;
	private JLabel labelEAN;
	private JLabel labelTypeProduct;
	private JLabel labelPartNumber;
	private JLabel labelReference;
	private JLabel labelPLU;
	private JLabel labelCategoryProduct;
	private JLabel labelBrandProduct;
	private JLabel labelDescription;
	private JLabel labelImage;

	private JLabel labelMargen;
	private JLabel labelFecha;
	private JLabel labelPrecio;
	private JLabel labelExistencias;
	private JLabel labelCosto;
	private JLabel labelAbreviado;
	private JLabel labelMargenMinimo;

	private JLabel labelTieneSerial;

	private JTextField fieldPLU;
	private JTextField fieldUPC;
	private JTextField fieldEAN;
	private JTextField fieldPartNumber;
	private JTextField fieldExistencias;
	private JTextField fieldReference;
	private JScrollPane scrollDescripcion;
	private JTextArea areaDescription;

	private JTextField fieldMargen;
	private JTextField fieldFecha;
	private JTextField fieldPrecio;
	private JTextField fieldCosto;
	private JTextField fieldAbreviado;
	private JTextField fieldMargenMinimo;

	private JComboBox comboBoxTypeProduct;
	private JComboBox comboBoxBrandProduct;
	private JComboBox comboBoxCategoryProduct;
	private JButton buttonSave;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonCancel;
	private DAOvarios daoVarios;
	private DAOImagen daoImagen;
	private boolean newProduct;
	private int productLoad;
	private boolean creacionProductoCorrecta= true;
	private JLabel labelPrecioMinimo;
	private JTextField fieldPrecioMinimo;
	private JButton buttonCalcularPrecio;
	private FrameMain frameMain;
	private JRadioButton radioButtonSiSerial;
	private JRadioButton radioButtonNoSerial;
	private ButtonGroup buttonGroup;

	static String formato_moneda="$###,###,###,###,###,###,###"; 
	static DecimalFormat df=new DecimalFormat(formato_moneda);

	public PruebaProducto() {
		
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);

		int distancia = 40;
		int tamanioY = 25;
		int y = 20;
		labelPLU = new JLabel("PLU:");
		labelPLU.setBounds(20, y, 50, tamanioY);
		add(labelPLU);

		fieldPLU = new JTextField();
		fieldPLU.setBounds(50, y, 100, tamanioY);
		fieldPLU.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} if(fieldPLU.getText().length()>13){
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldPLU);

		labelUPC = new JLabel("UPC:");
		labelUPC.setBounds(165, y, 50, tamanioY);
		add(labelUPC);

		fieldUPC = new JTextField();
		fieldUPC.setBounds(200, y, 100, tamanioY);
		fieldUPC.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} if(fieldUPC.getText().length()>13){
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldUPC);

		labelEAN = new JLabel("EAN:");
		labelEAN.setBounds(310, y, 50, tamanioY);
		add(labelEAN);

		fieldEAN = new JTextField();
		fieldEAN.setBounds(350, y, 100, tamanioY);
		add(fieldEAN);

		
		labelPartNumber = new JLabel("Parte Numero:");
		labelPartNumber.setBounds(460, y, 100, tamanioY);
		add(labelPartNumber);

		fieldPartNumber = new JTextField();
		fieldPartNumber.setBounds(550, y, 120, tamanioY);
		add(fieldPartNumber);

		
		labelReference = new JLabel("Referencia:");
		labelReference.setBounds(680, y, 70, tamanioY);
		add(labelReference);

		fieldReference = new JTextField();       
		fieldReference.setBounds(750, y, 200, tamanioY);
		add(fieldReference);
		

		y = y + distancia;
		labelTypeProduct = new JLabel("Tipo Producto:");
		labelTypeProduct.setBounds(20, y, 100, tamanioY);
		add(labelTypeProduct);

		comboBoxTypeProduct = new JComboBox();
		comboBoxTypeProduct.setBounds(110, y, 200, tamanioY);
		comboBoxTypeProduct.setName("comboBoxTypeProduct");
		add(comboBoxTypeProduct);
		

		labelCategoryProduct = new JLabel("Categoria:");
		labelCategoryProduct.setBounds(330, y, 70, tamanioY);
		add(labelCategoryProduct);

		comboBoxCategoryProduct = new JComboBox();
		//comboBoxGender.addItemListener(frameMain);
		comboBoxCategoryProduct.setBounds(400, y, 200, tamanioY);
		add(comboBoxCategoryProduct);
		

		labelBrandProduct = new JLabel("Marca:");
		labelBrandProduct.setBounds(620, y, 50, tamanioY);
		add(labelBrandProduct);

		comboBoxBrandProduct = new JComboBox();
		//comboBoxGender.addItemListener(frameMain);
		comboBoxBrandProduct.setBounds(680, y, 200, tamanioY);
		add(comboBoxBrandProduct);

		
		
		
		
		
		
		
		
		
		
		
		
		y = y + distancia;
		labelCosto = new JLabel("Costo:");
		labelCosto.setBounds(20, y, 50, tamanioY);
		add(labelCosto);

		fieldCosto = new JTextField();
		fieldCosto.setBounds(70, y, 100, tamanioY);
		fieldCosto.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});


		add(fieldCosto);

		labelMargen = new JLabel("Margen:");
		labelMargen.setBounds(180, y, 50, tamanioY);
		add(labelMargen);

		fieldMargen = new JTextField();
		fieldMargen.setBounds(230, y, 50, tamanioY);
		fieldMargen.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		
		add(fieldMargen);



		labelMargenMinimo = new JLabel("Margen Minimo:");
		labelMargenMinimo.setBounds(290, y, 100, tamanioY);
		add(labelMargenMinimo);


		fieldMargenMinimo = new JTextField();
		fieldMargenMinimo.setBounds(390, y, 50, tamanioY);
		fieldMargenMinimo.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldMargenMinimo);
		


		labelPrecio = new JLabel("Precio:");
		labelPrecio.setBounds(450, y, 50, tamanioY);
		add(labelPrecio);

		fieldPrecio = new JTextField();
		fieldPrecio.setBounds(500, y, 100, tamanioY);
		fieldPrecio.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		
		add(fieldPrecio);



		labelPrecioMinimo = new JLabel("Precio Minimo:");
		labelPrecioMinimo.setBounds(610, y, 100, tamanioY);		
		add(labelPrecioMinimo);

		fieldPrecioMinimo = new JTextField();
		fieldPrecioMinimo.setBounds(700, y, 100, tamanioY);
		fieldPrecioMinimo.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)
						|| (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldPrecioMinimo);


		labelExistencias = new JLabel("Existencias:");
		labelExistencias.setBounds(810, y, 70, tamanioY);
		add(labelExistencias);

		fieldExistencias = new JTextField();       
		fieldExistencias.setBounds(885, y, 50, tamanioY);
		fieldExistencias.setEditable(false);
		add(fieldExistencias);
		
		y = y + distancia;
		labelFecha = new JLabel("Fecha");
		labelFecha.setBounds(20, y, 70, tamanioY);
		add(labelFecha);

		fieldFecha = new JTextField();
		fieldFecha.setBounds(90, y, 150, tamanioY);
		fieldFecha.setEditable(false);
		add(fieldFecha);


		labelTieneSerial = new JLabel("Tiene Serial:");
		labelTieneSerial.setBounds(250, y, 80, tamanioY);
		add(labelTieneSerial);


		radioButtonSiSerial = new JRadioButton("SI");
		radioButtonSiSerial.setBounds(330, y, 40, tamanioY);
		add(radioButtonSiSerial);
		
		radioButtonNoSerial = new JRadioButton("NO");
		radioButtonNoSerial.setBounds(370, y, 50, tamanioY);
		radioButtonNoSerial.setSelected(true);
		add(radioButtonNoSerial);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonNoSerial);
		buttonGroup.add(radioButtonSiSerial);
		
		buttonCalcularPrecio = new JButton("CALCULAR");
		buttonCalcularPrecio.addActionListener(frameMain);
		buttonCalcularPrecio.setActionCommand("CALCULARPRECIO");
		buttonCalcularPrecio.setBounds(430, y, 100, tamanioY);
		add(buttonCalcularPrecio);
		

		
		y = y + distancia;
		labelAbreviado = new JLabel("Abreviado:");
		labelAbreviado.setBounds(20, y, 80, tamanioY);
		add(labelAbreviado);
		
		
		fieldAbreviado = new JTextField();
		fieldAbreviado.setBounds(100, y, 850, tamanioY);
		add(fieldAbreviado);
		
		
		
		y = y + distancia;
		labelDescription = new JLabel("Descripcion:");
		labelDescription.setBounds(20, y, 200, tamanioY);
		add(labelDescription);

		y = y + distancia-10;
		areaDescription = new JTextArea();       
		scrollDescripcion = new JScrollPane(areaDescription);
		scrollDescripcion.setBounds(20, y, 930, tamanioY*4);
		add(scrollDescripcion);

		y = y + distancia*2;
		









//
//
//
		y = y + distancia;
		
//
//

//
//
//
//
//
//
//
//
//
//
//
//
//
		
		
		buttonNew = new JButton("NUEVO");
		buttonNew.addActionListener(frameMain);
		buttonNew.setActionCommand("NUEVOPRODUCTO");
		buttonNew.setBounds(260, 370, 100, tamanioY);
		add(buttonNew);

		buttonEdit = new JButton("EDITAR");
		buttonEdit.addActionListener(frameMain);
		buttonEdit.setActionCommand("EDITARPRODUCTO");
		buttonEdit.setBounds(370, 370, 100, tamanioY);
		add(buttonEdit);

		buttonSave = new JButton("GUARDAR");
		buttonSave.addActionListener(frameMain);
		buttonSave.setActionCommand("GUARDARPRODUCTO");
		buttonSave.setBounds(480, 370, 100, tamanioY);
		add(buttonSave);


		buttonCancel = new JButton("CANCELAR");
		buttonCancel.setBounds(590, 370, 100, tamanioY);
		buttonCancel.addActionListener(frameMain);
		buttonCancel.setActionCommand("CANCELARPRODUCTO");
		add(buttonCancel);
	}
	public static void main(String[] args) {
		PruebaProducto pr = new PruebaProducto();
		pr.setVisible(true);

	}

}
