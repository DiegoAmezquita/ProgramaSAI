package interfazPersona;

import Codigo.Persona;
import Codigo.Varios;
import DAO.DAOPersona;
import DAO.DAOvarios;
import Interfaz.FrameMain;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInfoPersona extends JPanel {

	private JTextField fieldName;
	private JTextField fieldLastName;
	private JLabel labelName;
	private JLabel labelLastName;
	private JTextField fieldNumberDocument;
	private JLabel labelNumberDocument;
	private JTextField fieldIdentificationNumber;
	private JLabel labelTypeDocument;
	private JComboBox comboBoxTypeDocument;
	private JLabel labelTypePersona;
	private JComboBox comboBoxTypePersona;
	private JLabel labelGender;
	private JComboBox comboBoxGender;
	private JLabel labelBirthDate;
	private JTextField fieldBirthDateDay;
	private JTextField fieldBirthDateMonth;
	private JTextField fieldBirthDateYear;
	private JButton buttonSave;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonCancel;
	private DAOvarios daoVarios;
	private boolean newPerson;
	private int personLoad;
	private DAOPersona daoPersona;
	private boolean creacionPersonaCorrecta = true;
	private boolean vieneFactura = false;
	int distancia = 35;
	int tamanioY = 25;
	private FrameMain frameMain;

	public PanelInfoPersona(FrameMain frameMain) {
		// GridLayout gridLayout = new GridLayout();
		// gridLayout.setRows(8);
		// gridLayout.setColumns(2);
		// gridLayout.setHgap(10);
		// gridLayout.setVgap(10);
		setLayout(null);
		this.frameMain = frameMain;

		int y = 20;
		labelTypePersona = new JLabel("Tipo Persona");
		labelTypePersona.setBounds(20, y, 200, tamanioY);
		add(labelTypePersona);

		comboBoxTypePersona = new JComboBox();
		comboBoxTypePersona.addItemListener(frameMain);
		comboBoxTypePersona.setBounds(150, y, 200, tamanioY);
		comboBoxTypePersona.setName("comboBoxTypePersona");
		add(comboBoxTypePersona);

		y = y + distancia;
		labelName = new JLabel("Nombre");
		labelName.setBounds(20, y, 200, tamanioY);
		add(labelName);

		fieldName = new JTextField();
		fieldName.setBounds(150, y, 200, tamanioY);
		add(fieldName);

		y = y + distancia;
		labelLastName = new JLabel("Apellido");
		labelLastName.setBounds(20, y, 200, tamanioY);
		add(labelLastName);

		fieldLastName = new JTextField();
		fieldLastName.setBounds(150, y, 200, tamanioY);
		add(fieldLastName);

		y = y + distancia;
		labelTypeDocument = new JLabel("Tipo Documento");
		labelTypeDocument.setBounds(20, y, 200, tamanioY);
		add(labelTypeDocument);

		comboBoxTypeDocument = new JComboBox();
		comboBoxTypeDocument.setBounds(150, y, 200, tamanioY);
		comboBoxTypeDocument.addItemListener(frameMain);
		comboBoxTypeDocument.setName("comboBoxTypeDocument");
		add(comboBoxTypeDocument);

		y = y + distancia;
		labelNumberDocument = new JLabel("Numero Documento");
		labelNumberDocument.setBounds(20, y, 200, tamanioY);
		add(labelNumberDocument);
		fieldNumberDocument = new JTextField();
		fieldNumberDocument.setBounds(150, y, 200, tamanioY);
		fieldNumberDocument.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {				
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				
			}

			public void keyReleased(KeyEvent arg0) {
				colocarDV();
			}
		});

		fieldIdentificationNumber = new JTextField();
		fieldIdentificationNumber.setBounds(300, y, 50, tamanioY);
		fieldIdentificationNumber.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					e.consume();
				}

			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

		add(fieldNumberDocument);

		add(fieldIdentificationNumber);

		y = y + distancia;
		labelGender = new JLabel("Genero");
		labelGender.setBounds(20, y, 200, tamanioY);
		add(labelGender);

		comboBoxGender = new JComboBox();
		// comboBoxGender.addItemListener(frameMain);
		comboBoxGender.setBounds(150, y, 200, tamanioY);
		add(comboBoxGender);

		y = y + distancia;
		labelBirthDate = new JLabel("Fecha de Nacimiento");
		labelBirthDate.setBounds(20, y, 200, tamanioY);
		add(labelBirthDate);
		fieldBirthDateYear = new JTextField();
		/*
		 * fieldBirthDateYear.addKeyListener(new KeyListener() {
		 * 
		 * public void keyTyped(KeyEvent e) { if
		 * (fieldBirthDateYear.getText().length() == 4) { e.consume(); } }
		 * 
		 * public void keyPressed(KeyEvent arg0) { }
		 * 
		 * public void keyReleased(KeyEvent arg0) { } });
		 */
		fieldBirthDateYear.setBounds(150, y, 40, tamanioY);
		add(fieldBirthDateYear);

		fieldBirthDateMonth = new JTextField();

		/*
		 * fieldBirthDateMonth.addKeyListener(new KeyListener() {
		 * 
		 * public void keyTyped(KeyEvent e) { if
		 * (fieldBirthDateMonth.getText().length() == 2) { e.consume(); } }
		 * 
		 * public void keyPressed(KeyEvent arg0) { }
		 * 
		 * public void keyReleased(KeyEvent arg0) { } });
		 */

		fieldBirthDateMonth.setBounds(200, y, 40, tamanioY);
		add(fieldBirthDateMonth);

		fieldBirthDateDay = new JTextField();
		/*
		 * fieldBirthDateDay.addKeyListener(new KeyListener() {
		 * 
		 * public void keyTyped(KeyEvent e) { if
		 * (fieldBirthDateDay.getText().length() == 2) { e.consume(); }
		 * 
		 * }
		 * 
		 * public void keyPressed(KeyEvent arg0) { }
		 * 
		 * public void keyReleased(KeyEvent arg0) { } });
		 */
		fieldBirthDateDay.setBounds(250, y, 40, tamanioY);
		add(fieldBirthDateDay);

		buttonNew = new JButton("NUEVO");
		buttonNew.addActionListener(frameMain);
		buttonNew.setActionCommand("NUEVAPERSONA");
		buttonNew.setBounds(400, 20, 100, tamanioY);
		add(buttonNew);

		buttonEdit = new JButton("EDITAR");
		buttonEdit.addActionListener(frameMain);
		buttonEdit.setActionCommand("EDITARPERSONA");
		buttonEdit.setBounds(400, 20 + distancia, 100, tamanioY);
		add(buttonEdit);

		buttonSave = new JButton("GUARDAR");
		buttonSave.setBounds(400, 20 + distancia * 2, 100, tamanioY);
		buttonSave.addActionListener(frameMain);
		buttonSave.setActionCommand("GUARDARPERSONA");
		add(buttonSave);

		buttonCancel = new JButton("CANCELAR");
		buttonCancel.setBounds(400, 20 + distancia * 3, 100, tamanioY);
		buttonCancel.addActionListener(frameMain);
		buttonCancel.setActionCommand("CANCELARPERSONA");
		add(buttonCancel);

		daoVarios = new DAOvarios();
		daoPersona = new DAOPersona();

	}

	public void colocarDV() {
		if (fieldNumberDocument.getText().length() > 0) {
			fieldIdentificationNumber.setText(generarDv(Long
					.parseLong(fieldNumberDocument.getText())) + "");
		}
	}

	public void reiniciarCampos() {
		getFieldName().setText("");
		getFieldLastName().setText("");
		getFieldBirthDateDay().setText("");
		getFieldBirthDateMonth().setText("");
		getFieldBirthDateYear().setText("");
		getFieldNumberDocument().setText("");
		getFieldIdentificationNumber().setText("");
		getComboBoxGender().setSelectedIndex(0);
		getComboBoxTypeDocument().setSelectedIndex(0);
		getComboBoxTypePersona().setSelectedIndex(0);
	}

	public byte generarDv(long nit) {
		int[] nums = { 3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71 };

		int suma = 0;

		String str = String.valueOf(nit);
		for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
			suma += Character.digit(str.charAt(i), 10) * nums[j];
		}

		byte dv = (byte) ((suma % 11) > 1 ? (11 - (suma % 11)) : (suma % 11));
		return dv;
	}

	public void iniciarCombobox() {
		getComboBoxTypeDocument().removeAllItems();
		getComboBoxTypePersona().removeAllItems();
		getComboBoxGender().removeAllItems();
		ArrayList<Varios> listaVarios = daoVarios
				.consultarVariosPorCategoria("Documento de Identidad");
		for (int i = 0; i < listaVarios.size(); i++) {
			getComboBoxTypeDocument().addItem(
					listaVarios.get(i).getnombreVario());
		}
		listaVarios = daoVarios.consultarVariosPorCategoria("Tipo Sociedad");
		for (int i = 0; i < listaVarios.size(); i++) {
			getComboBoxTypePersona().addItem(
					listaVarios.get(i).getnombreVario());
		}
		listaVarios = daoVarios.consultarVariosPorCategoria("Genero");

		for (int i = 0; i < listaVarios.size(); i++) {
			getComboBoxGender().addItem(listaVarios.get(i).getnombreVario());
		}
	}

	public void mostrarInfoTipoPersona() {
		if (getComboBoxTypePersona().getSelectedIndex() != 0) {
			getComboBoxGender().setVisible(false);
			getLabelGender().setVisible(false);
			getFieldIdentificationNumber().setVisible(true);
			getFieldBirthDateDay().setVisible(false);
			getFieldBirthDateMonth().setVisible(false);
			getFieldBirthDateYear().setVisible(false);
			getLabelBirthDate().setVisible(false);
			getLabelName().setText("Razon Social:");
			getLabelLastName().setText("Sigla");

		} else {
			getComboBoxGender().setVisible(true);
			getLabelGender().setVisible(true);
			getFieldBirthDateDay().setVisible(true);
			getFieldBirthDateMonth().setVisible(true);
			getFieldBirthDateYear().setVisible(true);
			getLabelBirthDate().setVisible(true);
			getLabelName().setText("Nombre:");
			getLabelLastName().setText("Apellido");

		}
	}

	public void intercambioDocumentos() {
		if (getComboBoxTypeDocument().getSelectedIndex() == 2) {
			mostrarCamposNIT();
		} else if (getComboBoxTypeDocument().getSelectedIndex() == 4) {
			getFieldNumberDocument().setText(
					daoPersona.generarDocumentoTemporal() + "");
		} else {
			ocultarCamposNIT();
		}

	}

	public void mostrarCamposNIT() {
		getFieldNumberDocument().setSize(140, 25);
		getFieldIdentificationNumber().setVisible(true);
	}

	public void ocultarCamposNIT() {
		getFieldNumberDocument().setSize(200, 25);
		getFieldIdentificationNumber().setVisible(false);
	}

	public void bloquearElementos() {
		getFieldBirthDateDay().setEditable(false);
		getFieldBirthDateMonth().setEditable(false);
		getFieldBirthDateYear().setEditable(false);
		getFieldIdentificationNumber().setEditable(false);
		getFieldLastName().setEditable(false);
		getFieldName().setEditable(false);
		getFieldNumberDocument().setEditable(false);
		getComboBoxGender().setEnabled(false);
		getComboBoxTypeDocument().setEnabled(false);
		getComboBoxTypePersona().setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonSave.setEnabled(false);
	}

	public void desbloquearElementos() {
		getFieldBirthDateDay().setEditable(true);
		getFieldBirthDateMonth().setEditable(true);
		getFieldBirthDateYear().setEditable(true);
		getFieldIdentificationNumber().setEditable(true);
		getFieldLastName().setEditable(true);
		getFieldName().setEditable(true);
		getFieldNumberDocument().setEditable(true);
		getComboBoxGender().setEnabled(true);
		getComboBoxTypeDocument().setEnabled(true);
		getComboBoxTypePersona().setEnabled(true);
		buttonCancel.setEnabled(true);
		buttonSave.setEnabled(true);
	}

	public void bloquearEnUso() {
		buttonNew.setEnabled(false);
		buttonEdit.setEnabled(false);
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
	}

	public void desbloquearEnUso() {
		buttonNew.setEnabled(true);
		buttonEdit.setEnabled(true);
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
	}

	public void desbloquearEdicion() {
		buttonEdit.setEnabled(true);
	}

	public void bloquearEdicion() {
		buttonEdit.setEnabled(false);
	}

	public boolean validarCampos() {
		if (fieldName.getText().equals("")
				|| fieldLastName.getText().equals("")
				|| fieldNumberDocument.getText().equals("")) {
			System.out.println("SE SUPONE QUE ESTO ESTA EN LOS DOCUMENTOS"
					+ fieldNumberDocument.getText() + "Esto");
			return false;
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

	public Persona crearPersona() {
		Persona persona = new Persona();
		try {
			persona.setNombre(getFieldName().getText());
			persona.setApellido(getFieldLastName().getText());
			persona.setTipo(getComboBoxTypePersona().getSelectedIndex() + 1);
			persona.setTipoDocumento(getComboBoxTypeDocument()
					.getSelectedIndex() + 1);
			persona.setNumeroDocumento(getFieldNumberDocument().getText());
			if (persona.getTipo() != 1) {
				persona.setDigitoChequeo(Integer
						.parseInt(getFieldIdentificationNumber().getText()));
			} else {
				persona.setDigitoChequeo(1);
			}
			persona.setGenero(getComboBoxGender().getSelectedIndex() + 1);
			String anio = getFieldBirthDateYear().getText();
			String mes = getFieldBirthDateMonth().getText();
			String dia = getFieldBirthDateDay().getText();
			if (anio.equals("") && mes.equals("") && dia.equals("")) {
				persona.setFechaNacimiento(null);
			} else {
				if (anio.length() == 2) {
					anio = "19" + anio;
				} else if (anio.length() == 3) {
					anio = "1" + anio;
				} else if (anio.length() == 1) {
					anio = "200" + anio;
				}
				String fecha = anio + "/" + mes + "/" + dia;
				if (isFechaValidaa(anio, mes, dia)) {
					persona.setFechaNacimiento(fecha);
				} else {
					JOptionPane.showMessageDialog(this,
							"ERROR EN LA FECHA(YYYY/MM/DD)");
					return null;
				}
			}

			if (persona.getTipo() != 1) {
				persona.setGenero(1);
			}

			creacionPersonaCorrecta = true;
		} catch (Exception e) {
			creacionPersonaCorrecta = false;
			JOptionPane.showMessageDialog(null, "ERROR EN LOS DATOS");
		}
		return persona;

	}

	public boolean validarDigitoVerificacion() {
		if (comboBoxTypeDocument.getSelectedIndex() != 2) {
			return true;
		} else if (generarDv(Long.parseLong(getFieldNumberDocument().getText())) == (Integer
				.parseInt(getFieldIdentificationNumber().getText()))) {
			return true;
		}
		return false;
	}

	public void cargarPersona(Persona personaResultado) {
		getFieldName().setText(personaResultado.getNombre());
		getFieldLastName().setText(personaResultado.getApellido());
		iniciarCombobox();
		mostrarInfoTipoPersona();
		// panelResult.getPanelInfoPersona().getComboBoxTypeDocument().setSelectedIndex(2);
		getComboBoxTypeDocument().setSelectedIndex(
				personaResultado.getTipoDocumento() - 1);
		getComboBoxTypePersona().setSelectedIndex(
				personaResultado.getTipo() - 1);
		getComboBoxGender().setSelectedIndex(personaResultado.getGenero() - 1);
		if (personaResultado.getFechaNacimiento() != null) {

			String anio = personaResultado.getFechaNacimiento().charAt(0) + "";
			anio = anio + personaResultado.getFechaNacimiento().charAt(1);
			anio = anio + personaResultado.getFechaNacimiento().charAt(2);
			anio = anio + personaResultado.getFechaNacimiento().charAt(3);
			String mes = personaResultado.getFechaNacimiento().charAt(5) + "";
			mes = mes + personaResultado.getFechaNacimiento().charAt(6);
			String dia = personaResultado.getFechaNacimiento().charAt(8) + "";
			dia = dia + personaResultado.getFechaNacimiento().charAt(9);

			fieldBirthDateYear.setText(anio);
			fieldBirthDateMonth.setText(mes);
			fieldBirthDateDay.setText(dia);
		} else {
			fieldBirthDateYear.setText("");
			fieldBirthDateMonth.setText("");
			fieldBirthDateDay.setText("");
		}
		getFieldNumberDocument().setText(
				personaResultado.getNumeroDocumento() + "");
		getFieldIdentificationNumber().setText(
				generarDv(Long.parseLong(getFieldNumberDocument().getText())) + "");
//		getFieldIdentificationNumber().setText(
//				personaResultado.getDigitoChequeo() + "");
		desbloquearEdicion();
	}

	public JTextField getFieldIdentificationNumber() {
		return fieldIdentificationNumber;
	}

	public void setFieldIdentificationNumber(
			JTextField fieldIdentificationNumber) {
		this.fieldIdentificationNumber = fieldIdentificationNumber;
	}

	public JTextField getFieldLastName() {
		return fieldLastName;
	}

	public void setFieldLastName(JTextField fieldLastName) {
		this.fieldLastName = fieldLastName;
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldNumberDocument() {
		return fieldNumberDocument;
	}

	public void setFieldNumberDocument(JTextField fieldNumberDocument) {
		this.fieldNumberDocument = fieldNumberDocument;
	}

	public JComboBox getComboBoxTypeDocument() {
		return comboBoxTypeDocument;
	}

	public void setComboBoxTypeDocument(JComboBox comboBoxTypeDocument) {
		this.comboBoxTypeDocument = comboBoxTypeDocument;
	}

	public JComboBox getComboBoxTypePersona() {
		return comboBoxTypePersona;
	}

	public void setComboBoxTypePersona(JComboBox comboBoxTypePersona) {
		this.comboBoxTypePersona = comboBoxTypePersona;
	}

	public JComboBox getComboBoxGender() {
		return comboBoxGender;
	}

	public void setComboBoxGender(JComboBox comboBoxGender) {
		this.comboBoxGender = comboBoxGender;
	}

	public JLabel getLabelBirthDate() {
		return labelBirthDate;
	}

	public void setLabelBirthDate(JLabel labelBirthDate) {
		this.labelBirthDate = labelBirthDate;
	}

	public JLabel getLabelGender() {
		return labelGender;
	}

	public void setLabelGender(JLabel labelGender) {
		this.labelGender = labelGender;
	}

	public JLabel getLabelLastName() {
		return labelLastName;
	}

	public void setLabelLastName(JLabel labelLastName) {
		this.labelLastName = labelLastName;
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
	}

	public JLabel getLabelNumberDocument() {
		return labelNumberDocument;
	}

	public void setLabelNumberDocument(JLabel labelNumberDocument) {
		this.labelNumberDocument = labelNumberDocument;
	}

	public JLabel getLabelTypeDocument() {
		return labelTypeDocument;
	}

	public void setLabelTypeDocument(JLabel labelTypeDocument) {
		this.labelTypeDocument = labelTypeDocument;
	}

	public JLabel getLabelTypePersona() {
		return labelTypePersona;
	}

	public void setLabelTypePersona(JLabel labelTypePersona) {
		this.labelTypePersona = labelTypePersona;
	}

	public boolean isNewPerson() {
		return newPerson;
	}

	public void setNewPerson(boolean newPerson) {
		this.newPerson = newPerson;
	}

	public int getPersonLoad() {
		return personLoad;
	}

	public void setPersonLoad(int personLoad) {
		this.personLoad = personLoad;
	}

	public JTextField getFieldBirthDateDay() {
		return fieldBirthDateDay;
	}

	public void setFieldBirthDateDay(JTextField fieldBirthDateDay) {
		this.fieldBirthDateDay = fieldBirthDateDay;
	}

	public JTextField getFieldBirthDateMonth() {
		return fieldBirthDateMonth;
	}

	public void setFieldBirthDateMonth(JTextField fieldBirthDateMonth) {
		this.fieldBirthDateMonth = fieldBirthDateMonth;
	}

	public JTextField getFieldBirthDateYear() {
		return fieldBirthDateYear;
	}

	public void setFieldBirthDateYear(JTextField fieldBirthDateYear) {
		this.fieldBirthDateYear = fieldBirthDateYear;
	}

	public boolean isCreacionPersonaCorrecta() {
		return creacionPersonaCorrecta;
	}

	public void setCreacionPersonaCorrecta(boolean creacionPersonaCorrecta) {
		this.creacionPersonaCorrecta = creacionPersonaCorrecta;
	}

	public boolean isVieneFactura() {
		return vieneFactura;
	}

	public void setVieneFactura(boolean vieneFactura) {
		this.vieneFactura = vieneFactura;
	}

}
