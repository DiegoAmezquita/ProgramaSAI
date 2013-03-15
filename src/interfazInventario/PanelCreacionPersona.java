package interfazInventario;

import Codigo.Datos;
import Codigo.Persona;
import DAO.DAOPersona;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class PanelCreacionPersona extends JDialog {

	private JLabel labelProveedor;
	private JTextField campoBusquedaProveedor;
	private JButton buttonSearchProveedor;
	private DefaultListModel modelListResultProveedor;
	private JList listResultProveedor;
	private JScrollPane scrollPaneResultProveedor;
	private DAOvarios daoVarios;
	private DAOPersona daoPersona;
	int distancia = 30;
	int tamanioY = 25;
	private FrameMain frameMain;
	static String padre = "";
	private JPanel panelBusqueda;
	private static PanelCreacionPersona instancia;

	private PanelCreacionPersona(final FrameMain frameMain, final String padre2) {
		super(frameMain, true);
		this.frameMain = frameMain;
		setLayout(null);
		setSize(740, 300);
		setLocationRelativeTo(null);

		this.padre = padre2;

		panelBusqueda = new JPanel();
		panelBusqueda.setBounds(0, 0, 740, 300);
		panelBusqueda.setLayout(null);
		add(panelBusqueda);

		labelProveedor = new JLabel("Proveedor");
		labelProveedor.setBounds(10, 20, 80, 30);
		panelBusqueda.add(labelProveedor);

		campoBusquedaProveedor = new JTextField();
		campoBusquedaProveedor.setBounds(90, 20, 410, 30);
		panelBusqueda.add(campoBusquedaProveedor);

		buttonSearchProveedor = new JButton("BUSCAR");
		buttonSearchProveedor.setBounds(510, 20, 100, 30);
		buttonSearchProveedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarResultadosProveedor();
			}
		});

		panelBusqueda.add(buttonSearchProveedor);

		ActionMap mapaAccion = panelBusqueda.getActionMap();
		InputMap map = panelBusqueda
				.getInputMap(buttonSearchProveedor.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke key_Enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		map.put(key_Enter, "buscar");
		mapaAccion.put("buscar", buscarProveedor());

		modelListResultProveedor = new DefaultListModel();
		listResultProveedor = new JList(modelListResultProveedor);
		listResultProveedor.setCellRenderer(new DatosCellRenderer());
		scrollPaneResultProveedor = new JScrollPane(listResultProveedor);
		scrollPaneResultProveedor.setBounds(10, 60, 705, 190);

		panelBusqueda.add(scrollPaneResultProveedor);

		MouseListener mouseListener = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (padre.equals("INVENTARIO")) {
						cargarEnInventario();
					} else {
						cargarEnFacturacion();
					}

				}
			}
		};
		listResultProveedor.addMouseListener(mouseListener);

		daoVarios = new DAOvarios();
		daoPersona = new DAOPersona();

	}

	public static PanelCreacionPersona getInstancia(FrameMain frame,
			String padre1) {

		if (instancia == null) {
			instancia = new PanelCreacionPersona(frame, padre1);
		}
		padre = padre1;
		return instancia;
	}

	public void cambiarLabels() {
		System.out.println("ASKDHASJDHLSAHDLSAHDLASHLJD     "+padre);
		if (padre.equals("INVENTARIO")) {
			System.out.println("CAMBIA EL LABEL PROVEEDOR");
			labelProveedor.setText("Proveedor");
		} else {
			System.out.println("CAMBIA EL LABEL CLIENTE");
			labelProveedor.setText("Cliente");
		}
	}

	public AbstractAction buscarProveedor() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarResultadosProveedor();

			}
		};
	}

	public void cargarResultadosProveedor() {
		ArrayList<Persona> listaPersonas = daoPersona
				.consultarOptimizado(campoBusquedaProveedor.getText());
		modelListResultProveedor.clear();
		String mostrar = "";
		for (int i = 0; i < listaPersonas.size(); i++) {
			String documentoFormato = daoVarios.darFormatoNumeros(listaPersonas
					.get(i).getNumeroDocumento() + "");
			mostrar = listaPersonas.get(i).getApellido() + " "
					+ listaPersonas.get(i).getNombre() + " "
					+ listaPersonas.get(i).getNumeroDocumento();
			StringTokenizer tk = new StringTokenizer(
					campoBusquedaProveedor.getText(), " ");
			mostrar = "<html>" + mostrar.toUpperCase() + "</html>";
			String nuevo = "";
			String palabra = "";
			while (tk.hasMoreTokens()) {
				palabra = tk.nextToken().toUpperCase();
				nuevo = "<b>" + palabra + "</b>";
				mostrar = mostrar.replaceAll(palabra, nuevo);
			}
			mostrar = mostrar.replaceAll(listaPersonas.get(i)
					.getNumeroDocumento() + "", documentoFormato);
			modelListResultProveedor.addElement(new Datos(listaPersonas.get(i)
					.getCodigo(), mostrar));
			mostrar = "";
		}
		if (listaPersonas.size() == 1) {
			if (padre.equals("INVENTARIO")) {
				cargarEnInventario();
			} else if (padre.equals("FACTURACION")) {
				cargarEnFacturacion();
			}
		}
	}

	public void cargarEnFacturacion() {
		Datos d = (Datos) listResultProveedor.getSelectedValue();
		Persona personaTempo = null;
		if (modelListResultProveedor.size() > 1) {
			personaTempo = daoPersona.buscarPorCodigo(d.getCodigo());
		} else {
			personaTempo = daoPersona
					.buscarPorCodigo(((Datos) modelListResultProveedor.get(0))
							.getCodigo());
		}
		frameMain.getPanelFacturacion().setIdClienteCargado(
				personaTempo.getCodigo());
		frameMain
				.getPanelFacturacion()
				.getLabelClienteSeleccionado()
				.setText(
						personaTempo.getNombre() + " "
								+ personaTempo.getApellido() + " "
								+ personaTempo.getNumeroDocumento());
		frameMain.getPanelFacturacion().getLabelInfoCliente().setVisible(true);
		frameMain.getPanelFacturacion().cargarClienteInfo();
		setVisible(false);
	}

	public void cargarEnInventario() {
		Datos d = (Datos) listResultProveedor.getSelectedValue();
		Persona personaTempo = null;
		if (modelListResultProveedor.size() > 1) {
			personaTempo = daoPersona.buscarPorCodigo(d.getCodigo());
		} else {
			personaTempo = daoPersona
					.buscarPorCodigo(((Datos) modelListResultProveedor.get(0))
							.getCodigo());
		}

		frameMain.getPanelInventario().setIdProveedorCargado(
				personaTempo.getCodigo());
		frameMain
				.getPanelInventario()
				.getLabelProveedorSeleccionado()
				.setText(
						personaTempo.getNombre() + " "
								+ personaTempo.getApellido() + " "
								+ personaTempo.getNumeroDocumento());

		frameMain.getPanelInventario().getLabelInfoProveedor().setVisible(true);
		setVisible(false);
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

}
