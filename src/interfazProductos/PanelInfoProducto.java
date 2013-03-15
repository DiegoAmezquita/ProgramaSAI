package interfazProductos;

import Codigo.PerfilProducto;
import Codigo.Persona;
import Codigo.Producto;
import Codigo.Varios;
import DAO.DAOImagen;
import DAO.DAOPerfilProducto;
import DAO.DAOPersona;
import DAO.DAOProducto;
import DAO.DAOvarios;
import Interfaz.FrameMain;
import Interfaz.JMandatoryLabel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.itextpdf.text.pdf.parser.TextMarginFinder;

public class PanelInfoProducto extends JPanel {

	private JMandatoryLabel labelUPC;
	private JMandatoryLabel labelEAN;
	private JMandatoryLabel labelPLU;
	private JLabel labelTypeProduct;
	private JLabel labelPartNumber;
	private JLabel labelReference;
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
	private JLabel labelIva;

	private JTextField fieldIva;
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
	private JButton buttonClone;
	private JButton buttonCancel;
	private DAOvarios daoVarios;
	private DAOImagen daoImagen;
	private DAOPerfilProducto daoPerfilProducto;
	private DAOProducto daoProducto;
	private boolean newProduct;
	private int productLoad;
	private boolean creacionProductoCorrecta = true;
	private JLabel labelPrecioMinimo;
	private JTextField fieldPrecioMinimo;
	private JButton buttonCalcularPrecio;
	private FrameMain frameMain;
	private JRadioButton radioButtonSiSerial;
	private JRadioButton radioButtonNoSerial;
	private ButtonGroup buttonGroup;
	private JComboBox comboPerfilesProducto;
	private JLabel labelGarantia;
	private JTextField fieldGarantia;
	ArrayList<PerfilProducto> listaTempo;

	static String formato_moneda = "$###,###,###,###,###,###,###";
	static DecimalFormat df = new DecimalFormat(formato_moneda);

	public PanelInfoProducto(FrameMain frameMain) {
		setLayout(null);
		// setSize(1000, 500);
		// setLocationRelativeTo(null);

		int distancia = 35;
		int tamanioY = 25;
		int y = 20;

		labelTypeProduct = new JLabel("Tipo Producto:");
		labelTypeProduct.setBounds(20, y, 100, tamanioY);
		add(labelTypeProduct);

		comboBoxTypeProduct = new JComboBox();
		comboBoxTypeProduct.setBounds(110, y, 200, tamanioY);
		comboBoxTypeProduct.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTypeProduct.getItemCount() > 0) {
					if (comboBoxTypeProduct.getSelectedItem().equals(
							"--NUEVO--")) {
						nuevoVario("TipoProd");
					} else {
						generarAbreviado();
						generarPlu();
					}

				}

			}
		});
		comboBoxTypeProduct.setName("comboBoxTypeProduct");
		add(comboBoxTypeProduct);

		labelCategoryProduct = new JLabel("Categoria:");
		labelCategoryProduct.setBounds(330, y, 70, tamanioY);
		add(labelCategoryProduct);

		comboBoxCategoryProduct = new JComboBox();
		comboBoxCategoryProduct.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxCategoryProduct.getItemCount() > 0) {
					if (comboBoxCategoryProduct.getSelectedItem().equals(
							"--NUEVO--")) {
						nuevoVario("TipoEle");
					} else {
						generarAbreviado();
					}

				}

			}
		});
		comboBoxCategoryProduct.setBounds(400, y, 200, tamanioY);
		add(comboBoxCategoryProduct);

		labelBrandProduct = new JLabel("Marca:");
		labelBrandProduct.setBounds(620, y, 50, tamanioY);
		add(labelBrandProduct);

		comboBoxBrandProduct = new JComboBox();
		comboBoxBrandProduct.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxBrandProduct.getItemCount() > 0) {
					if (comboBoxBrandProduct.getSelectedItem().equals(
							"--NUEVO--")) {
						nuevoVario("Marca");
					} else {
						generarAbreviado();
					}

				}
			}
		});
		comboBoxBrandProduct.setBounds(680, y, 200, tamanioY);
		add(comboBoxBrandProduct);

		y = y + distancia;

		labelPLU = new JMandatoryLabel("PLU:", true);
		labelPLU.setBounds(20, y, 50, tamanioY);
		add(labelPLU);

		fieldPLU = new JTextField();
		fieldPLU.setBounds(50, y, 100, tamanioY);
		fieldPLU.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
				if (fieldPLU.getText().length() > 12) {
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

		labelUPC = new JMandatoryLabel("UPC:", true);
		labelUPC.setBounds(165, y, 50, tamanioY);
		add(labelUPC);

		fieldUPC = new JTextField();
		fieldUPC.setBounds(200, y, 100, tamanioY);
		fieldUPC.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
				if (fieldUPC.getText().length() > 12) {
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

		labelEAN = new JMandatoryLabel("EAN:", true);
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
		comboPerfilesProducto = new JComboBox();
		comboPerfilesProducto.setBounds(20, y, 120, tamanioY);
		add(comboPerfilesProducto);

		labelCosto = new JLabel("Costo:");
		labelCosto.setBounds(150, y, 50, tamanioY);
		add(labelCosto);

		fieldCosto = new JTextField("$0");
		fieldCosto.setBounds(200, y, 100, tamanioY);

		fieldCosto.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} else if (fieldCosto.getText().length() >= 9) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {

			}
		});

		fieldCosto.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!fieldCosto.getText().equals("")) {
					fieldCosto.setText(limpiarNumero(fieldCosto.getText()) + "");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!fieldCosto.getText().equals("")) {
					fieldCosto.setText(df.format(Integer.parseInt(fieldCosto
							.getText())));

				}

			}

		});
		add(fieldCosto);

		labelMargen = new JLabel("Margen:");
		labelMargen.setBounds(310, y, 50, tamanioY);
		add(labelMargen);

		fieldMargen = new JTextField();
		fieldMargen.setBounds(360, y, 50, tamanioY);
		fieldMargen.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} else if (fieldMargen.getText().length() >= 4) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

		fieldMargen.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!fieldMargen.getText().equals("")) {
					calcularPrecio();
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		add(fieldMargen);

		labelMargenMinimo = new JLabel("Margen Minimo:");
		labelMargenMinimo.setBounds(420, y, 100, tamanioY);
		add(labelMargenMinimo);

		fieldMargenMinimo = new JTextField();
		fieldMargenMinimo.setBounds(520, y, 50, tamanioY);
		fieldMargenMinimo.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} else if (fieldMargenMinimo.getText().length() >= 4) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

		fieldMargenMinimo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!fieldMargenMinimo.getText().equals("")) {
					calcularPrecio();
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		add(fieldMargenMinimo);

		// buttonCalcularPrecio = new JButton("CALCULAR");
		// buttonCalcularPrecio.addActionListener(frameMain);
		// buttonCalcularPrecio.setActionCommand("CALCULARPRECIO");
		// buttonCalcularPrecio.setBounds(450, y, 100, tamanioY);
		// add(buttonCalcularPrecio);

		labelPrecio = new JLabel("Precio:");
		labelPrecio.setBounds(590, y, 50, tamanioY);
		add(labelPrecio);

		fieldPrecio = new JTextField();
		fieldPrecio.setBounds(640, y, 100, tamanioY);
		fieldPrecio.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} else if (fieldPrecio.getText().length() >= 9) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		fieldPrecio.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!fieldPrecio.getText().equals("")) {
					fieldPrecio.setText(limpiarNumero(fieldPrecio.getText())
							+ "");
				}

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!fieldPrecio.getText().equals("")) {
					fieldPrecio.setText(df.format(Integer.parseInt(fieldPrecio
							.getText())));
				}

			}

		});
		add(fieldPrecio);

		labelPrecioMinimo = new JLabel("Precio Minimo:");
		labelPrecioMinimo.setBounds(750, y, 100, tamanioY);
		add(labelPrecioMinimo);

		fieldPrecioMinimo = new JTextField();
		fieldPrecioMinimo.setBounds(840, y, 100, tamanioY);
		fieldPrecioMinimo.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				} else if (fieldPrecioMinimo.getText().length() >= 9) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

		fieldPrecioMinimo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!fieldPrecioMinimo.getText().equals("")) {
					fieldPrecioMinimo.setText(limpiarNumero(fieldPrecioMinimo
							.getText()) + "");
				}

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!fieldPrecioMinimo.getText().equals("")) {
					fieldPrecioMinimo.setText(df.format(Integer
							.parseInt(fieldPrecioMinimo.getText())));

				}

			}

		});
		add(fieldPrecioMinimo);

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
		radioButtonSiSerial.setBounds(330, y, 60, tamanioY);
		add(radioButtonSiSerial);

		radioButtonNoSerial = new JRadioButton("NO");
		radioButtonNoSerial.setBounds(370, y, 60, tamanioY);
		radioButtonNoSerial.setSelected(true);
		add(radioButtonNoSerial);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonNoSerial);
		buttonGroup.add(radioButtonSiSerial);

		labelExistencias = new JLabel("Existencias:");
		labelExistencias.setBounds(450, y, 70, tamanioY);
		add(labelExistencias);

		fieldExistencias = new JTextField();
		fieldExistencias.setBounds(530, y, 50, tamanioY);
		fieldExistencias.setEditable(false);
		add(fieldExistencias);

		labelIva = new JLabel("Iva:");
		labelIva.setBounds(590, y, 70, tamanioY);
		add(labelIva);

		fieldIva = new JTextField("16");
		fieldIva.setBounds(615, y, 50, tamanioY);
		fieldIva.setEditable(false);
		fieldIva.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))
						&& c != '.') {
					getToolkit().beep();
					e.consume();
				} else if (!Character.isDigit(c)
						&& fieldIva.getText().contains(".")) {
					getToolkit().beep();
					e.consume();
				} else if (fieldIva.getText().length() >= 5) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldIva);

		labelGarantia = new JLabel("Garantia:");
		labelGarantia.setBounds(670, y, 70, tamanioY);
		add(labelGarantia);

		fieldGarantia = new JTextField("3");
		fieldGarantia.setBounds(725, y, 50, tamanioY);
		fieldGarantia.setEditable(false);
		fieldGarantia.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))
						&& c != '.') {
					getToolkit().beep();
					e.consume();
				} else if (fieldGarantia.getText().length() >= 5) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		add(fieldGarantia);

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

		y = y + distancia - 10;
		areaDescription = new JTextArea();
		scrollDescripcion = new JScrollPane(areaDescription);
		scrollDescripcion.setBounds(20, y, 930, tamanioY * 3);
		add(scrollDescripcion);

		buttonNew = new JButton("NUEVO");
		buttonNew.addActionListener(frameMain);
		buttonNew.setActionCommand("NUEVOPRODUCTO");
		buttonNew.setBounds(260, 320, 100, tamanioY);
		add(buttonNew);

		buttonEdit = new JButton("EDITAR");
		buttonEdit.addActionListener(frameMain);
		buttonEdit.setActionCommand("EDITARPRODUCTO");
		buttonEdit.setBounds(370, 320, 100, tamanioY);
		add(buttonEdit);

		buttonClone = new JButton("CLONAR");
		buttonClone.addActionListener(frameMain);
		buttonClone.setActionCommand("CLONARPRODUCTO");
		buttonClone.setBounds(480, 320, 100, tamanioY);
		add(buttonClone);

		buttonSave = new JButton("GUARDAR");
		buttonSave.addActionListener(frameMain);
		buttonSave.setActionCommand("GUARDARPRODUCTO");
		buttonSave.setBounds(590, 320, 100, tamanioY);
		add(buttonSave);

		buttonCancel = new JButton("CANCELAR");
		buttonCancel.setBounds(700, 320, 100, tamanioY);
		buttonCancel.addActionListener(frameMain);
		buttonCancel.setActionCommand("CANCELARPRODUCTO");
		add(buttonCancel);

		daoVarios = new DAOvarios();
		daoPerfilProducto = new DAOPerfilProducto();
		daoProducto = new DAOProducto();
		cargarFecha();
		cargarPerfilesProducto();

		generarPlu();
	}

	public void cargarPerfilesProducto() {
		listaTempo = daoPerfilProducto.getListaPerfiles();
		for (int i = 0; i < listaTempo.size(); i++) {
			comboPerfilesProducto.addItem(listaTempo.get(i).getNombre());
		}
		comboPerfilesProducto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				cambiarPerfilProducto();
			}
		});
	}

	public void cambiarPerfilProducto() {
		PerfilProducto perfil = listaTempo.get(comboPerfilesProducto
				.getSelectedIndex());
		fieldMargenMinimo.setText(perfil.getMargenMinimo() + "");
		fieldMargen.setText(perfil.getMargenSugerido() + "");
		fieldIva.setText(perfil.getIva() + "");
		if (perfil.getTieneSerial() == 0) {
			radioButtonNoSerial.setSelected(true);
		} else if (perfil.getTieneSerial() == 1) {
			radioButtonSiSerial.setSelected(true);
		}
		fieldGarantia.setText(perfil.getGarantiaMeses() + "");
	}

	public void nuevoVario(String nuevo) {
		System.out.println("LLEGA POR " + nuevo);
		boolean seguir = true;
		String mensaje = "Ingrese el nuevo nombre para ";
		while (seguir) {
			if (nuevo.equals("Marca")) {
				mensaje += "la nueva Marca";
			} else if (nuevo.equals("TipoEle")) {
				mensaje += "el nuevo tipo de Elemento";
			} else if (nuevo.equals("TipoProd")) {
				mensaje += "el nuevo tipo de Producto";
			}
			String nuevoTipo = JOptionPane.showInputDialog(mensaje);
			if (nuevoTipo == null) {
				if (nuevo.equals("Marca")) {
					comboBoxBrandProduct.setSelectedIndex(0);
				} else if (nuevo.equals("TipoEle")) {
					comboBoxCategoryProduct.setSelectedIndex(0);
				} else if (nuevo.equals("TipoProd")) {
					comboBoxTypeProduct.setSelectedIndex(0);
				}

				seguir = false;
			} else if (!nuevoTipo.equals("")) {

				if (nuevo.equals("Marca")) {
					daoVarios.insert(nuevoTipo, "Marca Elemento");
					iniciarCombobox(nuevo);
					comboBoxBrandProduct.setSelectedItem(nuevoTipo);
				} else if (nuevo.equals("TipoEle")) {
					daoVarios.insert(nuevoTipo, "Tipo de Elemento");
					iniciarCombobox(nuevo);
					comboBoxCategoryProduct.setSelectedItem(nuevoTipo);
				} else if (nuevo.equals("TipoProd")) {
					daoVarios.insert(nuevoTipo, "Tipo Producto");
					iniciarCombobox(nuevo);
					comboBoxTypeProduct.setSelectedItem(nuevoTipo);
				}

				seguir = false;
			} else if (nuevoTipo.equals("")) {
				JOptionPane.showMessageDialog(null,
						"El nombre no puede estar vacio");
			}
		}

	}

	public void cargarFecha() {
		String[] fecha = daoVarios.retornarFechaBD();
		fieldFecha.setText(fecha[2] + "-" + fecha[1] + "-" + fecha[0] + "  "
				+ fecha[3]);
	}

	public int limpiarNumero(String numero) {
		String tempo = "";
		for (int i = 0; i < numero.length(); i++) {
			if (Character.isDigit(numero.charAt(i))) {
				tempo += numero.charAt(i);
			}
		}
		return Integer.parseInt(tempo);
	}

	public void calcularPrecio() {
		boolean calcular = true;
		if (fieldCosto.getText().equals("")) {
			calcular = false;
			JOptionPane.showMessageDialog(frameMain,
					"El campo Costo no puede estar vacio");
		} else if (fieldMargen.getText().equals("")) {
			calcular = false;
			JOptionPane.showMessageDialog(frameMain,
					"El campo Margen no puede estar vacio");
		} else if (fieldMargenMinimo.getText().equals("")) {
			fieldMargenMinimo.setText(fieldMargen.getText());
		}

		if (calcular) {
			int precio = (int) ((limpiarNumero(fieldCosto.getText())
					* Float.parseFloat(fieldMargen.getText()) / 100)
					+ limpiarNumero(fieldCosto.getText()) + (limpiarNumero(fieldCosto
					.getText()) * Float.parseFloat(fieldIva.getText()) / 100));
			fieldPrecio.setText(df.format(precio));
			precio = (int) (((limpiarNumero(fieldCosto.getText()) * Float
					.parseFloat(fieldMargenMinimo.getText())) / 100)
					+ limpiarNumero(fieldCosto.getText()) + (limpiarNumero(fieldCosto
					.getText()) * Float.parseFloat(fieldIva.getText()) / 100));
			fieldPrecioMinimo.setText(df.format(precio));
		}

	}

	public void reiniciarCampos() {
		fieldEAN.setText("");
		fieldPartNumber.setText("");
		fieldPLU.setText("");
		fieldReference.setText("");
		areaDescription.setText("");
		fieldUPC.setText("");
		fieldCosto.setText("$0");
		fieldMargen.setText("");
		fieldAbreviado.setText("");
		fieldMargenMinimo.setText("");
		// fieldFecha.setText("");
		fieldPrecio.setText("");
		fieldPrecioMinimo.setText("");
		radioButtonNoSerial.setSelected(true);
		fieldIva.setText("16");
		cambiarPerfilProducto();

		comboBoxBrandProduct.setSelectedIndex(0);
		comboBoxCategoryProduct.setSelectedIndex(0);
		comboBoxTypeProduct.setSelectedIndex(0);
		generarPlu();

	}

	public void clonarCampos() {
		fieldEAN.setText("");
		fieldPartNumber.setText("");
		fieldPLU.setText("");
		fieldReference.setText("");
		fieldUPC.setText("");
		generarPlu();

	}

	public void iniciarCombobox(String combo) {

		ArrayList<Varios> listaVarios = null;
		if (combo.equals("Marca") || combo.equals("Todos")) {
			comboBoxBrandProduct.removeAllItems();
			listaVarios = daoVarios
					.consultarVariosPorCategoria("Marca Elemento");
			for (int i = 0; i < listaVarios.size(); i++) {
				comboBoxBrandProduct.addItem(listaVarios.get(i)
						.getnombreVario());
			}
			comboBoxBrandProduct.addItem("--NUEVO--");
		}
		if (combo.equals("TipoEle") || combo.equals("Todos")) {
			comboBoxCategoryProduct.removeAllItems();
			listaVarios = daoVarios
					.consultarVariosPorCategoria("Tipo de Elemento");
			for (int i = 0; i < listaVarios.size(); i++) {
				comboBoxCategoryProduct.addItem(listaVarios.get(i)
						.getnombreVario());
			}
			comboBoxCategoryProduct.addItem("--NUEVO--");
		}
		if (combo.equals("TipoProd") || combo.equals("Todos")) {
			listaVarios = daoVarios
					.consultarVariosPorCategoria("Tipo Producto");
			comboBoxTypeProduct.removeAllItems();
			for (int i = 0; i < listaVarios.size(); i++) {
				comboBoxTypeProduct
						.addItem(listaVarios.get(i).getnombreVario());
			}
			comboBoxTypeProduct.addItem("--NUEVO--");
		}
	}

	public void generarAbreviado() {
		fieldAbreviado.setText(comboBoxTypeProduct.getSelectedItem() + "-"
				+ comboBoxCategoryProduct.getSelectedItem() + "-"
				+ comboBoxBrandProduct.getSelectedItem() + "-"
				+ fieldReference.getText());
	}

	public void bloquearElementos() {
		fieldEAN.setEditable(false);
		fieldPartNumber.setEditable(false);
		fieldPLU.setEditable(false);
		fieldReference.setEditable(false);
		areaDescription.setEditable(false);
		fieldUPC.setEditable(false);
		fieldMargen.setEditable(false);
		fieldAbreviado.setEditable(false);
		fieldCosto.setEditable(false);
		fieldPrecio.setEditable(false);
		fieldMargenMinimo.setEditable(false);
		// fieldFecha.setEditable(false);
		fieldPrecioMinimo.setEditable(false);
		fieldIva.setEditable(false);
		comboPerfilesProducto.setEnabled(false);
		// buttonCalcularPrecio.setEnabled(false);

		radioButtonNoSerial.setEnabled(false);
		radioButtonSiSerial.setEnabled(false);
		comboBoxBrandProduct.setEnabled(false);
		comboBoxCategoryProduct.setEnabled(false);
		comboBoxTypeProduct.setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonSave.setEnabled(false);

	}

	public void desbloquearElementos() {
		generarAbreviado();

		fieldEAN.setEditable(true);
		fieldPartNumber.setEditable(true);
		fieldPLU.setEditable(true);
		fieldReference.setEditable(true);
		areaDescription.setEditable(true);
		fieldUPC.setEditable(true);
		fieldIva.setEditable(true);
		fieldMargen.setEditable(true);
		fieldAbreviado.setEditable(true);
		// fieldCosto.setEditable(true);
		fieldPrecio.setEditable(true);
		fieldMargenMinimo.setEditable(true);
		// fieldFecha.setEditable(true);
		fieldPrecioMinimo.setEditable(true);
		comboPerfilesProducto.setEnabled(true);
		// buttonCalcularPrecio.setEnabled(true);
		comboBoxBrandProduct.setEnabled(true);
		comboBoxCategoryProduct.setEnabled(true);
		comboBoxTypeProduct.setEnabled(true);

		radioButtonNoSerial.setEnabled(true);
		radioButtonSiSerial.setEnabled(true);

		buttonCancel.setEnabled(true);
		buttonSave.setEnabled(true);
	}

	public void bloquearEnUso() {
		buttonNew.setEnabled(false);
		buttonEdit.setEnabled(false);
		buttonClone.setEnabled(false);
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
	}

	public void desbloquearEnUso() {
		buttonNew.setEnabled(true);
		// buttonEdit.setEnabled(true);
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
	}

	public void desbloquearEdicion() {
		buttonEdit.setEnabled(true);
		buttonClone.setEnabled(true);
	}

	public void bloquearEdicion() {
		buttonEdit.setEnabled(false);
		buttonClone.setEnabled(false);
	}

	public void generarPlu() {
		fieldPLU.setText(daoProducto.getMaximoPlu(comboBoxTypeProduct
				.getSelectedIndex()) + "");
	}

	//
	public boolean validarCampos(boolean nuevo) {
		if (fieldPLU.getText().equals("") || fieldUPC.getText().equals("")
				|| fieldEAN.getText().equals("")) {
			return false;
		}
		if (nuevo) {
			if (daoProducto.isExistePlu(fieldPLU.getText())) {
				JOptionPane.showMessageDialog(null,
						"El PLU ya se encuentra registrado");
				return false;
			}
		}

		return true;
	}

	public boolean isFechaValidaa(String anio, String mes, String dia) {
		try {
			if (anio.length() == 2) {
				anio = "19" + anio;
			} else if (anio.length() == 3) {
				anio = "1" + anio;
			} else if (anio.length() == 1) {
				anio = "200" + anio;
			}
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd",
					Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(anio + "/" + mes + "/" + dia);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public String evitarSQLInjection(String texto) {
		String temporal = texto.replaceAll("'", "-");
		return temporal;
	}

	public Producto crearProducto() {

		// TODO optimizar la busqueda de las caracteristicas de marca, categoria
		// y tipo
		Producto producto = new Producto();
		try {
			producto.setEANCodeProducto(evitarSQLInjection(fieldEAN.getText()));
			// if (!daoProducto.isExistePlu(fieldPLU.getText())) {
			producto.setPluProducto(fieldPLU.getText());
			// }else{
			//
			// }
			producto.setUPCCodeProducto(fieldUPC.getText());
			producto.setReferenciaProducto(evitarSQLInjection(fieldReference
					.getText()));
			producto.setIdCategoriaProducto(
					(buscarCodigoNivel2("Tipo de Elemento",
							comboBoxCategoryProduct.getSelectedItem() + "")
							+ ""));
			producto.setIdMarcaProducto((buscarCodigoNivel2(
					"Marca Elemento", comboBoxBrandProduct.getSelectedItem()
							+ "")
					+ ""));
			producto.setIdTipoProducto(buscarCodigoNivel2("Tipo Producto",
					comboBoxTypeProduct.getSelectedItem() + "")+"");
			producto.setNumeroParteProducto(evitarSQLInjection(fieldPartNumber
					.getText()));

			producto.setDescripcionProducto(evitarSQLInjection(areaDescription
					.getText()));
			if (fieldMargen.getText().equals("")) {
				producto.setMargenProducto(0);
			} else {
				producto.setMargenProducto(Float.parseFloat(fieldMargen
						.getText()));
			}
			if (fieldMargenMinimo.getText().equals("")) {
				producto.setMargenMinimo(0);
			} else {
				producto.setMargenMinimo(Float.parseFloat(fieldMargenMinimo
						.getText()));
			}
			if (fieldCosto.getText().equals("")) {
				producto.setCostoProducto(0);
			} else {
				producto.setCostoProducto(limpiarNumero(fieldCosto.getText()));
			}
			String[] fecha = daoVarios.retornarFechaBD();
			producto.setFechaActualizacionProducto(fecha[2] + "-" + fecha[1]
					+ "-" + fecha[0]);
			if (fieldPrecio.getText().equals("")) {
				producto.setPrecioProducto(0);
			} else {
				producto.setPrecioProducto(limpiarNumero(fieldPrecio.getText()));
			}

			producto.setIvaProducto(Float.parseFloat(fieldIva.getText()));

			if (fieldGarantia.getText().equals("")) {
				fieldGarantia.setText("0");
			}
			producto.setGarantiaMeses(Integer.parseInt(fieldGarantia.getText()));

			producto.setAbreviadoProducto(fieldAbreviado.getText());
			if (radioButtonSiSerial.isSelected()) {
				producto.setTieneSerial(1);
			} else {
				producto.setTieneSerial(0);
			}
			creacionProductoCorrecta = true;
		} catch (Exception e) {
			creacionProductoCorrecta = false;
			JOptionPane.showMessageDialog(null, "ERROR EN LOS DATOS");
		}
		return producto;

	}

	public int buscarCodigoNivel2(String categoria, String elemento) {
		ArrayList<Varios> tempo = daoVarios
				.consultarVariosPorCategoria(categoria);
		for (int i = 0; i < tempo.size(); i++) {
			if (tempo.get(i).getnombreVario().equals(elemento)) {
				return tempo.get(i).gettipoNivel2Var();
			}
		}

		return 0;
	}

	public void cargarProducto(Producto productoResultado) {

		fieldEAN.setText(productoResultado.getEANCodeProducto());
		fieldPartNumber
				.setText(productoResultado.getNumeroParteProducto() + "");
		fieldUPC.setText(productoResultado.getUPCCodeProducto() + "");
		fieldReference.setText(productoResultado.getReferenciaProducto() + "");
		areaDescription.setText(productoResultado.getDescripcionProducto());
		fieldMargen.setText(productoResultado.getMargenProducto() + "");
		fieldMargenMinimo.setText(productoResultado.getMargenMinimo() + "");
		if (productoResultado.getCostoProducto() != -1) {
			fieldCosto.setText(df.format(productoResultado.getCostoProducto()));
		}
		calcularPrecio();
		if (productoResultado.getPrecioProducto() != -1) {
			fieldPrecio
					.setText(df.format(productoResultado.getPrecioProducto()));
		}
		fieldAbreviado.setText(productoResultado.getAbreviadoProducto() + "");
		fieldFecha.setText(productoResultado.getFechaActualizacionProducto());
		if (productoResultado.getTieneSerial() == 0) {
			radioButtonNoSerial.setSelected(true);
		} else {
			radioButtonSiSerial.setSelected(true);
		}
		fieldExistencias.setText("0");
		fieldExistencias.setText(productoResultado.getExistenciasProducto()
				+ "");

		fieldIva.setText(productoResultado.getIvaProducto() + "");

		fieldGarantia.setText(productoResultado.getGarantiaMeses() + "");

		// calcularPrecio();
		// if(fieldPrecio.getText().equals("")){
		//
		// }

		iniciarCombobox("Todos");
		// mostrarInfoTipoPersona();
		// panelResult.getPanelInfoPersona().getComboBoxTypeDocument().setSelectedIndex(2);

		comboBoxBrandProduct.setSelectedItem(daoVarios
				.consultarVariosPorCategoriaNivel2("Marca Elemento",
						Integer.parseInt(productoResultado.getIdMarcaProducto())));
		comboBoxCategoryProduct.setSelectedItem(daoVarios
				.consultarVariosPorCategoriaNivel2("Tipo de Elemento",
						Integer.parseInt(productoResultado.getIdCategoriaProducto())));
		comboBoxTypeProduct.setSelectedItem(daoVarios
				.consultarVariosPorCategoriaNivel2("Tipo Producto",
						Integer.parseInt(productoResultado.getIdTipoProducto())));

		generarAbreviado();
		desbloquearEdicion();
		fieldPLU.setText(productoResultado.getPluProducto() + "");
	}

	public JLabel getLabelTypeProduct() {
		return labelTypeProduct;
	}

	public void setLabelTypeProduct(JLabel labelTypeProduct) {
		this.labelTypeProduct = labelTypeProduct;
	}

	public JLabel getLabelPartNumber() {
		return labelPartNumber;
	}

	public void setLabelPartNumber(JLabel labelPartNumber) {
		this.labelPartNumber = labelPartNumber;
	}

	public JLabel getLabelReference() {
		return labelReference;
	}

	public void setLabelReference(JLabel labelReference) {
		this.labelReference = labelReference;
	}

	public JLabel getLabelCategoryProduct() {
		return labelCategoryProduct;
	}

	public void setLabelCategoryProduct(JLabel labelCategoryProduct) {
		this.labelCategoryProduct = labelCategoryProduct;
	}

	public JLabel getLabelBrandProduct() {
		return labelBrandProduct;
	}

	public void setLabelBrandProduct(JLabel labelBrandProduct) {
		this.labelBrandProduct = labelBrandProduct;
	}

	public JTextField getFieldPLU() {
		return fieldPLU;
	}

	public void setFieldPLU(JTextField fieldPLU) {
		this.fieldPLU = fieldPLU;
	}

	public JTextField getFieldUPC() {
		return fieldUPC;
	}

	public void setFieldUPC(JTextField fieldUPC) {
		this.fieldUPC = fieldUPC;
	}

	public JTextField getFieldEAN() {
		return fieldEAN;
	}

	public void setFieldEAN(JTextField fieldEAN) {
		this.fieldEAN = fieldEAN;
	}

	public JTextField getFieldPartNumber() {
		return fieldPartNumber;
	}

	public void setFieldPartNumber(JTextField fieldPartNumber) {
		this.fieldPartNumber = fieldPartNumber;
	}

	public JTextField getFieldReference() {
		return fieldReference;
	}

	public void setFieldReference(JTextField fieldReference) {
		this.fieldReference = fieldReference;
	}

	public JComboBox getComboBoxTypeProduct() {
		return comboBoxTypeProduct;
	}

	public void setComboBoxTypeProduct(JComboBox comboBoxTypeProduct) {
		this.comboBoxTypeProduct = comboBoxTypeProduct;
	}

	public JComboBox getComboBoxBrandProduct() {
		return comboBoxBrandProduct;
	}

	public void setComboBoxBrandProduct(JComboBox comboBoxBrandProduct) {
		this.comboBoxBrandProduct = comboBoxBrandProduct;
	}

	public JComboBox getComboBoxCategoryProduct() {
		return comboBoxCategoryProduct;
	}

	public void setComboBoxCategoryProduct(JComboBox comboBoxCategoryProduct) {
		this.comboBoxCategoryProduct = comboBoxCategoryProduct;
	}

	public JButton getButtonSave() {
		return buttonSave;
	}

	public void setButtonSave(JButton buttonSave) {
		this.buttonSave = buttonSave;
	}

	public JButton getButtonNew() {
		return buttonNew;
	}

	public void setButtonNew(JButton buttonNew) {
		this.buttonNew = buttonNew;
	}

	public JButton getButtonEdit() {
		return buttonEdit;
	}

	public void setButtonEdit(JButton buttonEdit) {
		this.buttonEdit = buttonEdit;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public void setButtonCancel(JButton buttonCancel) {
		this.buttonCancel = buttonCancel;
	}

	public DAOvarios getDaoVarios() {
		return daoVarios;
	}

	public void setDaoVarios(DAOvarios daoVarios) {
		this.daoVarios = daoVarios;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public int getProductLoad() {
		return productLoad;
	}

	public void setProductLoad(int productLoad) {
		this.productLoad = productLoad;
	}

	public boolean isCreacionProductoCorrecta() {
		return creacionProductoCorrecta;
	}

	public void setCreacionProductoCorrecta(boolean creacionProductoCorrecta) {
		this.creacionProductoCorrecta = creacionProductoCorrecta;
	}

}
