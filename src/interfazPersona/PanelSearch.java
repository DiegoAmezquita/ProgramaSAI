package interfazPersona;

import Codigo.Datos;
import Codigo.Persona;
import DAO.DAOPersona;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

public class PanelSearch extends JPanel {

	private JTextField fieldSearch;
	private JButton buttonSearch;
	private JList listResult;
	private DefaultListModel modelListResult;
	private JScrollPane scrollPaneResult;
	private TitledBorder rotulo;
	private DAOPersona daoPersona;
	private DAOvarios daoVarios;
	private FrameMain frameMain;

	public PanelSearch(FrameMain frameMain) {
		this.frameMain=frameMain;
		setSize(1000, 300);

		rotulo = BorderFactory.createTitledBorder("Busqueda Personas");
		rotulo.setTitleColor(new Color(0, 0, 128));
		setBorder(rotulo);
		setLayout(null);
		fieldSearch = new JTextField();
		fieldSearch.setBounds(20, 20, 500, 30);
		add(fieldSearch);

		buttonSearch = new JButton("BUSCAR");
		buttonSearch.setBounds(530, 20, 100, 30);
		buttonSearch.setActionCommand("BUSCAR");
		buttonSearch.addActionListener(frameMain);


		ActionMap mapaAccion = this.getActionMap();
		InputMap map = this.getInputMap(buttonSearch.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke key_Enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
		map.put(key_Enter, "buscar");
		mapaAccion.put("buscar",buscarPersona());




		add(buttonSearch);

		modelListResult = new DefaultListModel();
		listResult = new JList(modelListResult);      
		listResult.setCellRenderer(new DatosCellRenderer());
		scrollPaneResult = new JScrollPane(listResult);
		scrollPaneResult.setBounds(20, 60, 960, 200);
		listResult.setName("listResult");
		listResult.addListSelectionListener(frameMain);
		add(scrollPaneResult);

		//   JEditorPane editor = new JEditorPane();
		//  editor.setContentType("text/html");
		// editor.setText("<b>hola</b><br>");
		// modelListResult.addElement(editor.get));
		daoPersona = new DAOPersona();
		daoVarios = new DAOvarios();

	}

	public AbstractAction buscarPersona(){
		return new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameMain.buscarPersona();
				
			}
		};
	}



	public void bloquearElementos() {
		fieldSearch.setEnabled(false);
		buttonSearch.setEnabled(false);
		scrollPaneResult.setEnabled(false);
		listResult.setEnabled(false);

	}

	public void cargarResultados() {
		ArrayList<Persona> listaPersonas = daoPersona.consultarOptimizado(getFieldSearch().getText());
		getModelListResult().clear();
		String mostrar = "";
		for (int i = 0; i < listaPersonas.size(); i++) {
			String documentoFormato = daoVarios.darFormatoNumeros(listaPersonas.get(i).getNumeroDocumento() + "");
			mostrar = listaPersonas.get(i).getApellido() + " "
					+ listaPersonas.get(i).getNombre() + " "
					+ listaPersonas.get(i).getNumeroDocumento();
			StringTokenizer tk = new StringTokenizer(getFieldSearch().getText(), " "); // Cambia aquí el separador
			mostrar = "<html>" + mostrar.toUpperCase() + "</html>";
			String nuevo = "";
			String palabra = "";
			while (tk.hasMoreTokens()) {
				palabra = tk.nextToken().toUpperCase();             
				//nuevo = "<font color=blue>" + palabra + "</font>";
				nuevo = "<b>" + palabra + "</b>";
				mostrar = mostrar.replaceAll(palabra, nuevo);
			}
			mostrar = mostrar.replaceAll(listaPersonas.get(i).getNumeroDocumento()+"",documentoFormato);
			getModelListResult().addElement(new Datos(listaPersonas.get(i).getCodigo(), mostrar));
			mostrar = "";
		}
	}

	public void desbloquearElementos() {
		fieldSearch.setEnabled(true);
		buttonSearch.setEnabled(true);
		scrollPaneResult.setEnabled(true);
		listResult.setEnabled(true);
	}

	public JTextField getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(JTextField fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	public DefaultListModel getModelListResult() {
		return modelListResult;
	}

	public void setModelListResult(DefaultListModel modelListResult) {
		this.modelListResult = modelListResult;
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}

	public void setButtonSearch(JButton buttonSearch) {
		this.buttonSearch = buttonSearch;
	}
}
