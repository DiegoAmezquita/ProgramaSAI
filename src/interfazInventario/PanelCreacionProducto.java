package interfazInventario;

import Codigo.Datos;
import Codigo.Producto;
import Codigo.Varios;
import DAO.DAOProducto;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelCreacionProducto extends JDialog {

	static String formato_moneda = "$###,###,###,###,###,###,###";
	static DecimalFormat df = new DecimalFormat(formato_moneda);

	private DAOProducto daoProducto;
	private int idProductoCargado;
	private JTextField campoBusquedaProducto;
	private DefaultListModel modelListResultProducto;
	private JList listResultProducto;
	private DAOvarios daoVarios;
	private static PanelCreacionProducto instancia;
	private JPanel panelInfo;
	private FrameMain frameMain;
	private static String padre;

	private PanelCreacionProducto(FrameMain frameMain, final String padre2) {
		super(frameMain, true);
		this.frameMain = frameMain;
		this.padre = padre2;

		setLayout(null);
		setSize(740, 300);
		setLocationRelativeTo(null);
		panelInfo = new JPanel();
		panelInfo.setBounds(0, 0, 740, 300);
		panelInfo.setLayout(null);
		add(panelInfo);

		JLabel labelProducto = new JLabel("Producto");
		labelProducto.setBounds(10, 20, 80, 30);
		panelInfo.add(labelProducto);

		campoBusquedaProducto = new JTextField();
		campoBusquedaProducto.setBounds(90, 20, 500, 30);
		panelInfo.add(campoBusquedaProducto);

		JButton buttonBuscarProducto = new JButton("BUSCAR");
		buttonBuscarProducto.setBounds(600, 20, 100, 30);
		buttonBuscarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarResultadosProducto();

			}
		});

		ActionMap mapaAccion = panelInfo.getActionMap();
		InputMap map = panelInfo
				.getInputMap(buttonBuscarProducto.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke key_Enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		map.put(key_Enter, "buscar");
		mapaAccion.put("buscar", buscarProducto());

		panelInfo.add(buttonBuscarProducto);

		modelListResultProducto = new DefaultListModel();
		listResultProducto = new JList(modelListResultProducto);
		listResultProducto.setCellRenderer(new DatosCellRenderer());
		JScrollPane scrollPaneResultProducto = new JScrollPane(
				listResultProducto);
		scrollPaneResultProducto.setBounds(10, 60, 700, 170);
		panelInfo.add(scrollPaneResultProducto);

		daoProducto = new DAOProducto();
		daoVarios = new DAOvarios();

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out
							.println("EL PADRE ES______________________________ "
									+ padre);
					if (padre.equals("INVENTARIO")) {
						cargarEnInventario();
					} else {
						cargarEnFactura();
					}
					 setVisible(false);

				}
			}
		};
		listResultProducto.addMouseListener(mouseListener);

	}

	public void cargarEnInventario() {
		System.out.println("AGREGA A INVENTARIO");
		int posicion = listResultProducto.getSelectedIndex();
		frameMain.getPanelInventario().setIdProductoCargado(
				((Datos) modelListResultProducto.get(posicion)).getCodigo());
		Producto productoTempo = daoProducto.buscarPorCodigo(frameMain
				.getPanelInventario().getIdProductoCargado());
		System.out.println("SE SUPONE QUE TIENE SERIAL "
				+ productoTempo.getTieneSerial());
		if (productoTempo.getTieneSerial() == 1) {
			PanelNumeroSerie panelNumeroSerie = new PanelNumeroSerie(
					frameMain.getPanelInventario());
			panelNumeroSerie.setVisible(true);
		} else {
			frameMain
					.getPanelInventario()
					.agregarArticuloFactura(
							daoProducto.buscarPorCodigo(((Datos) modelListResultProducto
									.get(posicion)).getCodigo()), "0", 1);
		}

	}

	public void cargarEnFactura() {
		int posicion = listResultProducto.getSelectedIndex();
		idProductoCargado = ((Datos) modelListResultProducto.get(posicion))
				.getCodigo();
		Producto productoTempo = daoProducto.buscarPorCodigo(idProductoCargado);
		setVisible(false);
		frameMain.getPanelFacturacion().agregarProductoFactura(
				((Datos) modelListResultProducto.get(posicion)).getCodigo());
	}

	public AbstractAction buscarProducto() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarResultadosProducto();
			}
		};
	}

	public void cargarResultadosProducto() {
		// TODO mejorar esta busqueda porque se esta demorando mucho
		ArrayList<Producto> listaProductos = daoProducto
				.consultarOptimizado(campoBusquedaProducto.getText());
		modelListResultProducto.clear();
		String mostrar = "";
		for (int i = 0; i < listaProductos.size(); i++) {
			mostrar = daoVarios.consultarVariosPorCategoriaNivel2(
					"Tipo de Elemento", listaProductos.get(i)
							.getIdCategoriaProducto())
					+ " "
					+ daoVarios.consultarVariosPorCategoriaNivel2(
							"Marca Elemento", listaProductos.get(i)
									.getIdMarcaProducto())
					+ " "
					+ listaProductos.get(i).getReferenciaProducto()
					+ " "
					+ listaProductos.get(i).getNumeroParteProducto()
					+ " "
					+ listaProductos.get(i).getAbreviadoProducto();
			StringTokenizer tk = new StringTokenizer(
					campoBusquedaProducto.getText(), " "); // Cambia aquÃ­ el
			// separador
			mostrar = "<html>" + mostrar.toUpperCase() + "</html>";
			String nuevo = "";
			String palabra = "";
			while (tk.hasMoreTokens()) {
				palabra = tk.nextToken().toUpperCase();
				// nuevo = "<font color=blue>" + palabra + "</font>";
				nuevo = "<b>" + palabra + "</b>";
				mostrar = mostrar.replaceAll(palabra, nuevo);
			}
			modelListResultProducto.addElement(new Datos(listaProductos.get(i)
					.getIdProducto(), mostrar));
			mostrar = "";
		}
		System.out.println("TAMAÑO DE LA LISTA "+listaProductos.size());
		if (listaProductos.size() == 1) {
			System.out
					.println("ESTE ES EL CODIGO QUE SE PSUPONE QUE ESTOY CARGANDO "
							+ ((Datos) modelListResultProducto.get(0))
									.getCodigo());
			Producto prodTempo = daoProducto
					.buscarPorCodigo(((Datos) modelListResultProducto.get(0))
							.getCodigo());
			if (prodTempo.getTieneSerial() == 0) {
				if (padre.equals("INVENTARIO")) {
					frameMain.getPanelInventario().agregarArticuloFactura(
							prodTempo, "0", 1);
					this.setVisible(false);
				} else {
					frameMain.getPanelFacturacion().agregarProductoFactura(
							prodTempo.getIdProducto());
					this.setVisible(false);
				}
			}
		}
	}

	public static PanelCreacionProducto getInstancia(FrameMain frameMain,
			String padre1) {
		if (instancia == null) {
			instancia = new PanelCreacionProducto(frameMain, padre1);
		}
		padre = padre1;
		return instancia;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

}
