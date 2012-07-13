package interfazFacturacion;

import Codigo.Articulo;
import Codigo.Datos;
import Codigo.Detalle;
import Codigo.FacturaVenta;
import Codigo.ItemFactura;
import Codigo.Persona;
import Codigo.Producto;
import Codigo.Varios;
import DAO.DAOArticulo;
import DAO.DAODetalle;
import DAO.DAOFacturaVenta;
import DAO.DAOItemFactura;
import DAO.DAOOpcion;
import DAO.DAOPersona;
import DAO.DAOProducto;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameImpresion;
import Interfaz.FrameMain;

import interfazInventario.ModeloTablaArticulos;
import interfazInventario.PanelCreacionProducto;
import interfazInventario.PanelInventario;
import interfazInventario.PanelNumeroSerie;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

public class PanelFacturacion extends JPanel implements MouseListener {

	private TitledBorder rotulo;
	private String infoProduct = "PRUEBA FACTURACION";
	private JLabel labelCliente;
	private JLabel labelClienteSeleccionado;
	private JLabel labelProducto;
	private JLabel labelNumeroFactura;
	private JLabel labelFechaFactura;
	private JLabel labelVenceFactura;

	// private JLabel labelClienteInfoCiudad;
	private JTextField campoBusquedaCliente;
	private JTextField campoBusquedaProducto;
	private JTextField campoPrefijoFactura;
	private JTextField campoNumeroFactura;
	private JTextField campoFechaFactura;
	private JTextField campoVenceFactura;

	// private JTextField campoClienteInfoCiudad;

	private JComboBox comboClienteInfoDireccion;
	private JComboBox comboClienteInfoTelefono;
	private JLabel labelInfoCliente;
	private JButton buttonSearchCliente;
	private JButton buttonCrearCliente;
	private JButton buttonSearchProducto;
	private DefaultListModel modelListResultCliente;
	private DefaultListModel modelListResultProducto;
	private JList listResultCliente;
	private JList listResultProducto;
	private JScrollPane scrollPaneResultCliente;
	private JScrollPane scrollPaneResultProducto;
	private JPanel panelInfoFactura;
	private JPanel panelClienteInfo;
	private JPanel panelListaProductos;
	private JPanel panelObservaciones;
	private JPanel panelCostoFactura;
	private JTextArea areaObservaciones;
	private JScrollPane scrollPaneObservaciones;
	private JButton botonImprimir;
	private JLabel labelSubtotal;
	private JTextField campoSubtotal;
	private JLabel labelBase;
	private JList listaBase;
	private DefaultListModel defaultlistaBase;
	private JScrollPane scrollListaBases;
	private JLabel labelIva;
	private JTextField campoIva;
	private JLabel labelTotal;
	private JTextField campoTotal;
	private JScrollPane scrollListaProductos;
	private ModeloTabla modeloTabla;
	private JTable tablaProductos;
	private DAOPersona daoPersona;
	private DAOProducto daoProducto;
	private DAOvarios daoVarios;
	private DAOFacturaVenta daoFacturaVenta;
	private DAOArticulo daoArticulo;
	private DAODetalle daoDetalle;
	private DAOItemFactura daoItemFactura;
	private ArrayList<Producto> productosFactura;
	private FrameMain frameMain;
	private int idClienteCargado = 0;
	private int idProductoCargado = 0;
	private PanelSeleccionArticulo panelTempo;
	private ItemFactura itemFacturaTempo;
	private JButton buttonBuscarProducto;
	private PanelNumeroSerie panelNumeroSerie;
	private JInternalFrame ventanaInterna;

	private JPopupMenu emergente;// es el "marco" donde se pone el menu
	private JMenuItem menus[];// lleva la lista de las opciones

	private JDialog dialogoInformacionCliente;

	// DefaultListModel modelListResultProductoSerial;
	// JList listResultProductoSerial;
	// JScrollPane scrollPaneResultProductoSerial;

	public PanelFacturacion(FrameMain frameMain, JInternalFrame ventanaInterna) {

		this.frameMain = frameMain;
		this.ventanaInterna = ventanaInterna;

		setLayout(null);

		productosFactura = new ArrayList<Producto>();

		labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(10, 20, 80, 30);
		add(labelCliente);

		labelClienteSeleccionado = new JLabel("NO HA SELECCIONADO UN CLIENTE");
		labelClienteSeleccionado.setBounds(90, 20, 720, 30);
		add(labelClienteSeleccionado);

		labelInfoCliente = new JLabel(
				"<html><font color='red'>info</font> </html>");
		labelInfoCliente.setBounds(350, 20, 50, 30);
		labelInfoCliente.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				crearDialogoInformacionCliente();

			}
		});
		labelInfoCliente.setVisible(false);
		add(labelInfoCliente);

		buttonSearchCliente = new JButton("BUSCAR CLIENTE");
		buttonSearchCliente.setBounds(470, 20, 160, 30);
		buttonSearchCliente.setActionCommand("BUSCARCLIENTEFACTURACION");
		buttonSearchCliente.addActionListener(frameMain);
		add(buttonSearchCliente);

		labelFechaFactura = new JLabel("Fecha");
		labelFechaFactura.setBounds(10, 60, 80, 30);
		add(labelFechaFactura);

		campoFechaFactura = new JTextField();
		campoFechaFactura.setBounds(90, 60, 150, 30);
		// campoFechaFactura.setEditable(false);
		campoFechaFactura.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!campoFechaFactura.getText().equals("")) {
					cargarFecha(campoFechaFactura);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!campoFechaFactura.getText().equals("")) {
					SimpleDateFormat formateador = new SimpleDateFormat(
							"yyyy/MM/dd kk:mm:ss");
					formateador.setLenient(false);
					try {
						Date fecha = formateador.parse(campoFechaFactura
								.getText());
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null,
								"La fecha tiene un formato incorrecto");
						campoFechaFactura.requestFocus();
					}
				}

			}

		});

		add(campoFechaFactura);

		labelVenceFactura = new JLabel("Vence");
		labelVenceFactura.setBounds(250, 60, 80, 30);
		add(labelVenceFactura);

		campoVenceFactura = new JTextField();
		campoVenceFactura.setBounds(300, 60, 160, 30);
		campoVenceFactura.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!campoVenceFactura.getText().equals("")) {
					cargarFecha(campoVenceFactura);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!campoVenceFactura.getText().equals("")) {
					SimpleDateFormat formateador = new SimpleDateFormat(
							"yyyy/MM/dd kk:mm:ss");
					formateador.setLenient(false);
					try {
						Date fecha = formateador.parse(campoVenceFactura
								.getText());
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null,
								"La fecha tiene un formato incorrecto");
						campoVenceFactura.requestFocus();
					}
				}

			}

		});
		add(campoVenceFactura);

		buttonSearchProducto = new JButton("BUSCAR PRODUCTO");
		buttonSearchProducto.setBounds(470, 60, 160, 30);
		buttonSearchProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				crearFrameBuscarProducto();
				// imprimir();

			}
		});
		add(buttonSearchProducto);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int posicion = listResultProducto.locationToIndex(e
							.getPoint());
					System.out.println("La posicion es " + posicion);
					agregarProductoFactura(((Datos) modelListResultProducto
							.get(posicion)).getCodigo());
				}
			}
		};

		daoPersona = new DAOPersona();
		daoVarios = new DAOvarios();
		daoProducto = new DAOProducto();
		daoFacturaVenta = new DAOFacturaVenta();
		daoArticulo = new DAOArticulo();
		daoItemFactura = new DAOItemFactura();
		daoDetalle = new DAODetalle();

		crearPanelInfoFactura();
		crearPanelListaProductos();
		crearPanelObservaciones();
		crearPanelCostoFactura();
		crearBotonesAcciones();
		cargarFecha(campoFechaFactura);
		cargarFecha(campoVenceFactura);
		cargarNumeroFactura();

	}

	public void crearDialogoInformacionCliente() {
		if(dialogoInformacionCliente!=null){
			dialogoInformacionCliente.setVisible(false);
			dialogoInformacionCliente = null;
		}
		dialogoInformacionCliente = new JDialog(frameMain);
		dialogoInformacionCliente.setSize(300, 300);
		dialogoInformacionCliente.setLocationRelativeTo(null);
		dialogoInformacionCliente.setLayout(null);
		dialogoInformacionCliente.setVisible(true);
		JLabel labelTitulo = new JLabel("INFORMACION CLIENTE");
		labelTitulo.setBounds(50, 20, 300, 30);
		dialogoInformacionCliente.add(labelTitulo);
		ArrayList<String> listaInfo = cargarInfoCliente();
		JLabel labelDireccion = new JLabel("Direccion: " + listaInfo.get(0));
		labelDireccion.setBounds(50, 60, 300, 30);
		dialogoInformacionCliente.add(labelDireccion);
		JLabel labelCiudad = new JLabel("Ciudad: " + listaInfo.get(1));
		labelCiudad.setBounds(50, 100, 300, 30);
		dialogoInformacionCliente.add(labelCiudad);
		JLabel labelTelefono = new JLabel("Telefono: " + listaInfo.get(2));
		labelTelefono.setBounds(50, 140, 300, 30);
		dialogoInformacionCliente.add(labelTelefono);
		JLabel labelEstado = new JLabel("Estado: " + listaInfo.get(3));
		labelEstado.setBounds(50, 180, 300, 30);
		dialogoInformacionCliente.add(labelEstado);
	}

	public AbstractAction buscarProducto() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarResultadosProducto();
			}
		};
	}

	public void crearFrameBuscarProducto() {
		PanelCreacionProducto.getInstancia(frameMain, "FACTURACION")
				.setVisible(true);

	}

	public void cargarSerialesProducto(int codigoProducto) {
		Producto prodTempo = daoProducto.buscarPorCodigo(codigoProducto);
	}

	public void cargarFecha(JTextField campoFecha) {
		String[] fecha = daoVarios.retornarFechaBD();
		campoFecha.setText(fecha[2] + "/" + fecha[1] + "/" + fecha[0] + "  "
				+ fecha[3]);
	}

	public void crearPanelInfoFactura() {
		panelInfoFactura = new JPanel();
		panelInfoFactura.setBounds(740, 15, 250, 70);
		TitledBorder rotuloInfoFactura = BorderFactory
				.createTitledBorder("Factura de Venta");
		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
		panelInfoFactura.setBorder(rotuloInfoFactura);
		panelInfoFactura.setLayout(null);
		add(panelInfoFactura);

		labelNumeroFactura = new JLabel("Numero");
		labelNumeroFactura.setBounds(20, 20, 80, 30);
		panelInfoFactura.add(labelNumeroFactura);

		campoPrefijoFactura = new JTextField();
		campoPrefijoFactura.setBounds(70, 20, 50, 30);
		panelInfoFactura.add(campoPrefijoFactura);

		campoNumeroFactura = new JTextField();
		campoNumeroFactura.setBounds(130, 20, 100, 30);
		panelInfoFactura.add(campoNumeroFactura);

	}

	public void cargarCliente() {
		Persona persona = daoPersona.buscarPorCodigo(idClienteCargado);
		System.out.println("ACA LLEGA ESTE ID " + idClienteCargado);
		System.out.println("PERSONA " + persona.getNombre() + " dcouemnto "
				+ persona.getNumeroDocumento());
		labelClienteSeleccionado.setText(persona.getNombre() + " "
				+ persona.getApellido() + " " + persona.getNumeroDocumento()
				+ "");
		labelInfoCliente.setVisible(true);
	}

	public void cargarNumeroFactura() {
		Varios varioPrefijo = daoVarios.consultar("501");
		Varios varioNumero = daoVarios.consultar("503");

		campoPrefijoFactura.setText(varioPrefijo.getnombreVario());
		campoNumeroFactura.setText(varioNumero.getnombreVario());
	}

	public void crearPanelListaProductos() {

		panelListaProductos = new JPanel();
		panelListaProductos.setBounds(10, 100, 980, 410);
		TitledBorder rotuloTitulo = BorderFactory
				.createTitledBorder("Articulos");
		rotuloTitulo.setTitleColor(new Color(0, 0, 128));
		panelListaProductos.setBorder(rotuloTitulo);
		panelListaProductos.setLayout(null);
		add(panelListaProductos);

		final String[] columnNames1 = { "ITEM", "PRODUCTO", "#", "VU SIN iva",
				"Sub SIN iva", "VU CON iva", "Sub CON iva", "" };

		modeloTabla = new ModeloTabla(columnNames1, 0, this);
		tablaProductos = new JTable(modeloTabla);
		tablaProductos.getColumnModel().getColumn(2).setMaxWidth(25);
		tablaProductos.getColumnModel().getColumn(2).setMinWidth(25);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(25);

		tablaProductos.getColumnModel().getColumn(7).setMaxWidth(0);
		tablaProductos.getColumnModel().getColumn(7).setMinWidth(0);
		tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(0);

		scrollListaProductos = new JScrollPane(tablaProductos);
		scrollListaProductos.setBounds(10, 20, 960, 380);
		panelListaProductos.add(scrollListaProductos);
		tablaProductos.addMouseListener(this);

		TableColumn columna = tablaProductos.getColumn("PRODUCTO");
		columna.setPreferredWidth(500);
		columna = tablaProductos.getColumn("ITEM");
		columna.setPreferredWidth(10);

	}

	public void crearPanelObservaciones() {
		panelObservaciones = new JPanel();
		panelObservaciones.setBounds(10, 510, 720, 130);
		TitledBorder rotuloTitulo = BorderFactory
				.createTitledBorder("Observaciones");
		rotuloTitulo.setTitleColor(new Color(0, 0, 128));
		panelObservaciones.setBorder(rotuloTitulo);
		panelObservaciones.setLayout(null);
		add(panelObservaciones);

		areaObservaciones = new JTextArea();
		scrollPaneObservaciones = new JScrollPane(areaObservaciones);
		scrollPaneObservaciones.setBounds(10, 20, 700, 100);
		panelObservaciones.add(scrollPaneObservaciones);

	}

	public void crearPanelCostoFactura() {
		panelCostoFactura = new JPanel();
		panelCostoFactura.setBounds(740, 510, 250, 190);
		TitledBorder rotuloInfoFactura = BorderFactory
				.createTitledBorder("Costo");
		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
		panelCostoFactura.setBorder(rotuloInfoFactura);
		panelCostoFactura.setLayout(null);
		add(panelCostoFactura);

		labelSubtotal = new JLabel("SUB-TOTAL");
		labelSubtotal.setBounds(20, 20, 80, 30);
		panelCostoFactura.add(labelSubtotal);

		campoSubtotal = new JTextField();
		campoSubtotal.setBounds(90, 20, 150, 30);
		campoSubtotal.setEnabled(false);
		panelCostoFactura.add(campoSubtotal);

		labelBase = new JLabel("BASE");
		labelBase.setBounds(20, 55, 80, 30);
		panelCostoFactura.add(labelBase);

		defaultlistaBase = new DefaultListModel();
		listaBase = new JList(defaultlistaBase);
		scrollListaBases = new JScrollPane(listaBase);
		scrollListaBases.setBounds(90, 55, 150, 50);
		panelCostoFactura.add(scrollListaBases);

		labelIva = new JLabel("IVA");
		labelIva.setBounds(20, 110, 80, 30);
		panelCostoFactura.add(labelIva);

		campoIva = new JTextField();
		campoIva.setBounds(90, 110, 150, 30);
		campoIva.setEnabled(false);
		panelCostoFactura.add(campoIva);

		labelTotal = new JLabel("TOTAL");
		labelTotal.setBounds(20, 145, 80, 30);
		panelCostoFactura.add(labelTotal);

		campoTotal = new JTextField();
		campoTotal.setBounds(90, 145, 150, 30);
		campoTotal.setEnabled(false);
		panelCostoFactura.add(campoTotal);
	}

	public void crearBotonesAcciones() {
		botonImprimir = new JButton("IMPRIMIR");
		botonImprimir.setBounds(200, 650, 150, 40);
		botonImprimir.setActionCommand("IMPRIMIRFACTURAVENTA");
		botonImprimir.addActionListener(frameMain);
		add(botonImprimir);

	}

	public void cargarResultadosCliente() {
		ArrayList<Persona> listaPersonas = daoPersona
				.consultarOptimizado(getCampoBusquedaCliente().getText());
		getModelListResultCliente().clear();
		String mostrar = "";
		for (int i = 0; i < listaPersonas.size(); i++) {
			String documentoFormato = daoVarios.darFormatoNumeros(listaPersonas
					.get(i).getNumeroDocumento() + "");
			mostrar = listaPersonas.get(i).getApellido() + " "
					+ listaPersonas.get(i).getNombre() + " "
					+ listaPersonas.get(i).getNumeroDocumento();
			StringTokenizer tk = new StringTokenizer(getCampoBusquedaCliente()
					.getText(), " ");
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
			getModelListResultCliente().addElement(
					new Datos(listaPersonas.get(i).getCodigo(), mostrar));
			mostrar = "";
		}
		// if(listaPersonas.size()==1){
		// cargarCliente();
		// }
	}

	public void cargarResultadosProducto() {
		// TODO mejorar esta busqueda porque se esta demorando mucho
		ArrayList<Producto> listaProductos = daoProducto
				.consultarOptimizado(getCampoBusquedaProducto().getText());
		modelListResultProducto.clear();
		String mostrar = "";
		for (int i = 0; i < listaProductos.size(); i++) {
			// String documentoFormato =
			// daoVarios.darFormatoNumeros(listaProductos.get(i).getNumeroDocumento()
			// + "");
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
			StringTokenizer tk = new StringTokenizer(getCampoBusquedaProducto()
					.getText(), " "); // Cambia aquÃ­ el separador
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
		// if(listaProductos.size()==1){
		// agregarProductoFactura(listaProductos.get(0).getIdProducto());
		// }
	}

	public void agregarProductoFactura(int idProducto) {
		Producto productoTempo = daoProducto.buscarPorCodigo(idProducto);
		productosFactura.add(productoTempo);
		itemFacturaTempo = new ItemFactura();
		itemFacturaTempo.setProducto(productoTempo);
		itemFacturaTempo.setPrecio(productoTempo.getPrecioProducto());
		itemFacturaTempo.setCantidad(1);
		ArrayList<Articulo> arrayArticulosTempo = daoArticulo
				.buscarArticulosPorProductoDisponibles(idProducto + "");
		if (productoTempo.getTieneSerial() == 0) {
			if (arrayArticulosTempo.size() > 0) {
				Articulo articuloTempo = arrayArticulosTempo.get(0);
				itemFacturaTempo.setArticulo(articuloTempo);
				itemFacturaTempo.setIdArticulo(articuloTempo.getIdArticulo());
			} else {
				itemFacturaTempo.setArticulo(null);
				itemFacturaTempo.setIdArticulo(0);
				JOptionPane.showMessageDialog(null,
						"NO HAY ARTICULOS REGISTRADOS PARA ESTE PRODUCTO");
				modeloTabla.getProductosFactura().add(itemFacturaTempo);
				modeloTabla.actualizarDatos();
				calcularValorFactura();
			}
			colocarGarantiasObservaciones();
		} else {
			panelTempo = new PanelSeleccionArticulo(frameMain);
			ArrayList<String> arrayTempo = new ArrayList<String>();

			for (int i = 0; i < arrayArticulosTempo.size(); i++) {
				System.out.println("ESTADO DEL PRODUCTO "
						+ arrayArticulosTempo.get(i).getEstado());
				arrayTempo.add(arrayArticulosTempo.get(i).getNumeroSerie());
			}

			panelTempo.cargarNumerosSerie(arrayArticulosTempo);
			panelTempo.setVisible(true);

		}

		// actualizarTabla();a
	}

	public void cargarProductoNumeroSerie() {
		if (panelTempo != null) {
			int numero = 0;
			if (panelTempo.numeroSerieSeleccionado() == 0) {
				String texto = JOptionPane.showInputDialog("Numero de serie:");
				if (texto != null && !texto.equals("")) {
					Articulo temporal = new Articulo();
					temporal.setIdProductoArticulo(itemFacturaTempo
							.getProducto().getIdProducto());
					temporal.setEstado(0);
					temporal.setIdProveedor("0");
					temporal.setCostoArticulo(0);
					String[] fecha = daoVarios.retornarFechaBD();
					temporal.setFechaIngreso(fecha[2] + "-" + fecha[1] + "-"
							+ fecha[0] + " " + fecha[3]);
					temporal.setIdDocumentoSoporte("1");
					temporal.setIdSitio("1");
					temporal.setNumeroSerie(texto);
					daoArticulo.insert(temporal);
					numero = daoProducto.m_DAOConnectionLogin
							.devolverUltimoID();
				}
			} else {
				numero = panelTempo.numeroSerieSeleccionado();
			}

			if (numero != 0) {
				itemFacturaTempo.setIdArticulo(numero);
				itemFacturaTempo.setArticulo(daoArticulo
						.consultar(itemFacturaTempo.getIdArticulo() + ""));

				boolean existe = false;
				for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
					ItemFactura tempo = modeloTabla.getProductosFactura()
							.get(i);
					if (tempo.getIdArticulo() == panelTempo
							.numeroSerieSeleccionado()) {
						JOptionPane.showMessageDialog(null,
								"Ese articulo ya se agrego");
						panelTempo.setVisible(true);
						existe = true;
						break;
					}
				}
				if (!existe) {
					modeloTabla.getProductosFactura().add(itemFacturaTempo);
					modeloTabla.actualizarDatos();
					calcularValorFactura();
					panelTempo.setVisible(false);
					panelTempo = null;
					colocarGarantiasObservaciones();
				}
			}
		}
		actualizarTabla();
	}

	public void ocultarSeleccionNumeroSerie() {
		panelTempo.setVisible(false);
		panelTempo = null;
	}

	// guardar productos de la factura para poder editar la cantidad y el precio
	// al que se deja

	public void actualizarTabla() {
		// while (modeloTabla.getRowCount() != 0) {
		// modeloTabla.removeRow(0);
		// }
		for (int i = 0; i < productosFactura.size(); i++) {

			int valorUnitarioSinIva = (int) (productosFactura.get(i)
					.getCostoProducto() * (1 + (productosFactura.get(i)
					.getMargenProducto() / 100)));

			int valorUnitarioConIva = (int) (productosFactura.get(i)
					.getCostoProducto()
					* (1 + (productosFactura.get(i).getMargenProducto() / 100)) * (1 + (productosFactura
					.get(i).getIvaProducto() / 100)));

			if (!existeProductoFactura(productosFactura.get(i)
					.getDescripcionProducto())) {
				modeloTabla.addRow(new String[] { (i + 1) + "",
						productosFactura.get(i).getDescripcionProducto(), "1",
						valorUnitarioSinIva + "", valorUnitarioSinIva + "",
						valorUnitarioConIva + "", valorUnitarioConIva + "",
						productosFactura.get(i).getIdProducto() + "" });
			}
		}

	}

	public void calcularCambiosProducto(int row) {
		Producto producto = daoProducto.buscarPorCodigo(Integer
				.parseInt(modeloTabla.getValueAt(row, 7) + ""));
		int subConIva = (int) (Integer.parseInt(modeloTabla.getValueAt(row, 2)
				+ "") * Integer.parseInt(modeloTabla.getValueAt(row, 5) + ""));

		int subSinIva = (int) (Integer.parseInt(modeloTabla.getValueAt(row, 5)
				+ "") / (1 + (producto.getIvaProducto() / 100)));
		modeloTabla.setValueAt(subSinIva + "", row, 3);
		subSinIva = subSinIva
				* Integer.parseInt(modeloTabla.getValueAt(row, 2) + "");

		modeloTabla.setValueAt(subConIva + "", row, 6);
		modeloTabla.setValueAt(subSinIva + "", row, 4);
	}

	public boolean existeProductoFactura(String mostrar) {
		for (int i = 0; i < modeloTabla.getRowCount(); i++) {
			String tempo = (String) modeloTabla.getValueAt(i, 1);
			if (tempo.equals(mostrar)) {
				return true;
			}
		}

		return false;
	}

	public void crearFacturaVenta() {

		System.out.println("/n/n/n");
		for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
			ItemFactura aa = modeloTabla.getProductosFactura().get(i);
			System.out.println(aa.getProducto().getDescripcionProducto()
					+ " Cantidad " + aa.getCantidad() + " Precio "
					+ aa.getPrecio());
		}

		FacturaVenta facturaVentaTemporal = new FacturaVenta();
		boolean correcto = true;
		if (idClienteCargado == 0) {
			JOptionPane.showMessageDialog(null,
					"DEBE SELECCIONAR EL CLIENTE PRIMERO");
			correcto = false;
		}
		facturaVentaTemporal.setIdCliente(idClienteCargado);

		facturaVentaTemporal.setNitCliente("0");
		facturaVentaTemporal.setDigitoCliente("0");
		facturaVentaTemporal.setCiudadClient("0");
		facturaVentaTemporal.setFechaVence(campoVenceFactura.getText());
		facturaVentaTemporal.setFechaAceptada(campoFechaFactura.getText());
		facturaVentaTemporal.setEstado(0);

		facturaVentaTemporal.setTotalFactura(Double.parseDouble(campoTotal
				.getText()));
		facturaVentaTemporal.setIva(Double.parseDouble(campoIva.getText()));
		facturaVentaTemporal.setBaseIva(Double.parseDouble(0 + ""));
		if (campoPrefijoFactura.getText().equals("")
				|| campoNumeroFactura.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"PROBLEMAS CON EL NUMERO y/o PREFIJO DE LA FACTURA");
			correcto = false;
		}

		int seleccion = JOptionPane.showConfirmDialog(
				null,
				"¿ES CORRECTO EL NUMERO DE LA FACTURA?\n"
						+ campoPrefijoFactura.getText() + "-"
						+ campoNumeroFactura.getText());
		if (seleccion == 0 && correcto) {
			facturaVentaTemporal.setPrefijoFactura(campoPrefijoFactura
					.getText());
			facturaVentaTemporal.setNumeroFactura(campoNumeroFactura.getText());
			int idFacturaCreada = daoFacturaVenta.insert(facturaVentaTemporal);
			Varios varioNum = new Varios();
			varioNum.setnombreVario((Integer.parseInt(campoNumeroFactura
					.getText()) + 1) + "");
			varioNum.setcodVar(503);
			daoVarios.editar(varioNum);

			for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
				ItemFactura itemfacturaGuardar = modeloTabla
						.getProductosFactura().get(i);
				itemfacturaGuardar.setIdFactura(idFacturaCreada);
				if (itemfacturaGuardar.getArticulo() != null) {
					itemfacturaGuardar.setIdArticulo(itemfacturaGuardar
							.getArticulo().getIdArticulo());
					daoArticulo.cambiarEstadoVendido(itemfacturaGuardar
							.getArticulo().getIdArticulo());
				} else {
					itemfacturaGuardar.setIdArticulo(0);
				}
				itemfacturaGuardar.setIdProducto(itemfacturaGuardar
						.getProducto().getIdProducto());
				itemfacturaGuardar.setSubTotal(itemfacturaGuardar.getCantidad()
						* itemfacturaGuardar.getPrecio());
				itemfacturaGuardar.setGarantia(itemfacturaGuardar.getProducto()
						.getGarantiaMeses());
				itemfacturaGuardar.setIva(itemfacturaGuardar.getProducto()
						.getIvaProducto());
				daoItemFactura.insert(itemfacturaGuardar);
				daoProducto.cambiarExistencias(
						itemfacturaGuardar.getIdProducto(), -1
								* itemfacturaGuardar.getCantidad());

				// Varios varioPre = new Varios();
				// char nuevoPrefijo =
				// (char)(((int)campoPrefijoFactura.getText().charAt(0))+1);
				// varioPre.setnombreVario(nuevoPrefijo+"");
				// varioPre.setcodVar(501);
				// daoVarios.editar(varioPre);

				System.out.println(itemfacturaGuardar.getProducto()
						.getDescripcionProducto()
						+ " Cantidad "
						+ itemfacturaGuardar.getCantidad()
						+ " Precio "
						+ itemfacturaGuardar.getPrecio());
			}

		}

		int renglonesNecesarios = calcularRenglonesNecesarios(modeloTabla
				.getProductosFactura());
		int numeroFacturas = (int) (((double) renglonesNecesarios / (double) 12) - 0.001);
		numeroFacturas += 1;

		if (numeroFacturas > 1) {
			ArrayList<ItemFactura> listaTempo = new ArrayList<ItemFactura>();
			int renglonesUtilizados = 0;
			boolean facturaCreada = false;
			for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
				int renglonesUtilizadosProducto = calcularRenglonesNecesarios(modeloTabla
						.getProductosFactura().get(i).getProducto()
						.getDescripcionProducto());
				System.out
						.println("/////////////////////  LA PRUEBA QUE ESTOY HACIENDO "
								+ (renglonesUtilizados + renglonesUtilizadosProducto));
				if ((renglonesUtilizados + renglonesUtilizadosProducto) <= 12) {
					renglonesUtilizados += renglonesUtilizadosProducto;
					listaTempo.add(modeloTabla.getProductosFactura().get(i));
					facturaCreada = false;
				} else {
					System.out.println("TAMAÑO DE LA LISTA PARA LA FACTURA "
							+ listaTempo.size());
					facturaCreada = true;
					crearFacturaImpresion(listaTempo);
					renglonesUtilizados = 0;
					listaTempo = new ArrayList<ItemFactura>();
					i = i - 1;
				}
			}
			if (!facturaCreada) {
				crearFacturaImpresion(listaTempo);
			}
		} else {
			crearFacturaImpresion(modeloTabla.getProductosFactura());
		}

		cerrarFactura();

		// System.out.println("FACTURAS QUE TOCA GENERAR " + numeroFacturas);
	}

	public ArrayList<ItemFactura> generarListaItems(int inicio, int fin) {
		ArrayList<ItemFactura> listaTempo = new ArrayList<ItemFactura>();
		if (fin <= modeloTabla.getProductosFactura().size())
			for (int i = inicio; i < fin; i++) {
				listaTempo.add(modeloTabla.getProductosFactura().get(i));
			}
		return listaTempo;
	}

	public void crearFacturaImpresion(ArrayList<ItemFactura> listaItems) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
				"yyyy/MM/dd hh:mm:ss");

		Date fecha = null;
		Date fechaVence = null;
		try {
			fecha = formatoDelTexto.parse(campoFechaFactura.getText());
			fechaVence = formatoDelTexto.parse(campoVenceFactura.getText());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		System.out.println(fecha.toString());

		FrameImpresion frame = new FrameImpresion(listaItems,
				daoPersona.buscarPorCodigo(idClienteCargado),
				areaObservaciones.getText(), fecha, fechaVence,
				campoSubtotal.getText(), campoIva.getText(),
				campoTotal.getText());

		frame.setVisible(true);
		frame.imprimir();
	}

	public int calcularRenglonesNecesarios(ArrayList<ItemFactura> listaItems) {
		Font font = new Font("Arial", Font.PLAIN, 12);
		FontMetrics metrics = new FontMetrics(font) {
		};
		int numeroRenglonesOcupados = 0;

		for (int i = 0; i < listaItems.size(); i++) {
			Rectangle2D bounds = metrics.getStringBounds(listaItems.get(i)
					.getProducto().getDescripcionProducto(), null);
			int widthInPixels = (int) bounds.getWidth();
			int renglones = widthInPixels / 450;
			renglones += 1;
			numeroRenglonesOcupados += renglones;
		}

		return numeroRenglonesOcupados;
	}

	@SuppressWarnings("serial")
	public int calcularRenglonesNecesarios(String texto) {
		Font font = new Font("Arial", Font.PLAIN, 12);
		FontMetrics metrics = new FontMetrics(font) {
		};

		Rectangle2D bounds = metrics.getStringBounds(texto, null);
		int widthInPixels = (int) bounds.getWidth();
		int renglones = widthInPixels / 450;
		renglones += 1;
		return renglones;
	}

	// public void calcularValorFactura() {
	// int totalFactura = 0;
	// for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	// totalFactura += Integer.parseInt(modeloTabla.getValueAt(i, 4) + "");
	// }
	// campoTotal.setText(totalFactura + "");
	// campoIva.setText("16");
	// campoSubtotal.setText(totalFactura + "");
	// campoBase.setText(totalFactura + "");
	// }

	public void calcularValorFactura() {
		int precio = 0;
		for (int i = 0; i < modeloTabla.getRowCount(); i++) {

			precio += (int) (Float
					.parseFloat(modeloTabla.getValueAt(i, 3) + ""));
		}
		campoSubtotal.setText(precio + "");

		precio = 0;
		String mostrar = "";
		int contador = 0;
		int precioIva = 0;
		HashMap<Float, Integer> listaBases = new HashMap<Float, Integer>();
		for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
			Producto product = modeloTabla.getProductosFactura().get(i)
					.getProducto();
			if (product.getIvaProducto() > 0) {
				// Integer costoBase = Integer.parseInt(Integer
				// .parseInt(modeloTabla.getValueAt(contador, 3) + "")
				// * Integer.parseInt(modeloTabla.getValueAt(contador, 2)
				// + "") + "");

				int costoBase = Integer.parseInt(modeloTabla.getValueAt(
						contador, 3) + "");
				costoBase = (int) (costoBase * (product.getIvaProducto() / 100));
				costoBase = costoBase
						* Integer.parseInt(modeloTabla.getValueAt(contador, 2)
								+ "");

				if (listaBases.get(product.getIvaProducto()) != null) {
					listaBases.put(product.getIvaProducto(),
							listaBases.get(product.getIvaProducto())
									+ costoBase);
				} else {
					listaBases.put(product.getIvaProducto(), costoBase);
				}
				precioIva += costoBase;

			}
			contador++;
		}

		defaultlistaBase.removeAllElements();
		Iterator itu = listaBases.entrySet().iterator();
		while (itu.hasNext()) {
			System.out.println("ENTRA PARA AGREGAR " + defaultlistaBase.size());
			Map.Entry e = (Map.Entry) itu.next();
			defaultlistaBase.addElement(e.getKey() + "% " + e.getValue());
			precio += Float.parseFloat(e.getValue() + "");
		}

		campoIva.setText(precio + "");
		campoTotal
				.setText((Integer.parseInt(campoSubtotal.getText()) + (Integer
						.parseInt(campoIva.getText()))) + "");

	}

	public ArrayList<String> retornarSeriales(int idProducto) {
		ArrayList<String> temporal = new ArrayList<String>();
		for (int i = 0; i < modeloTabla.getProductosFactura().size(); i++) {
			if (modeloTabla.getProductosFactura().get(i).getIdProducto() == (idProducto)) {
				temporal.add(modeloTabla.getProductosFactura().get(i)
						.getArticulo().getNumeroSerie());
			}
		}

		return temporal;
	}

	// public void colocarNumeroSerieObservaciones() {
	// ArrayList<ItemFactura> listaItemsTempo = modeloTabla
	// .getProductosFactura();
	// String mostrar = "";
	// int contador = 1;
	// ArrayList<String> tempo;
	// for (int i = 0; i < listaItemsTempo.size(); i++) {
	// if (listaItemsTempo.get(i).getProducto().getTieneSerial() == 1) {
	// mostrar = mostrar + "   Item " + contador + ": ";
	// mostrar = mostrar
	// + listaItemsTempo.get(i).getArticulo().getNumeroSerie()
	// + ",";
	// }
	//
	// contador++;
	// }
	// System.out.println("ESTO MUESTRA: " + mostrar + " ---");
	// areaObservaciones.setText(mostrar);
	//
	// }

	public void colocarGarantiasObservaciones() {
		ArrayList<ItemFactura> listaItemsTempo = modeloTabla
				.getProductosFactura();
		HashMap<Integer, String> listaGarantias = new HashMap<Integer, String>();
		String mostrar = " ";
		int contador = 1;
		ArrayList<String> tempo;
		for (int i = 0; i < listaItemsTempo.size(); i++) {
			// if (listaItemsTempo.get(i).getProducto().getTieneSerial() == 1) {
			// mostrar = mostrar + "   Item " + contador + ": ";
			// mostrar = mostrar
			// + listaItemsTempo.get(i).getArticulo().getNumeroSerie()
			// + ",";
			// }
			if (listaGarantias.get(listaItemsTempo.get(i).getProducto()
					.getGarantiaMeses()) != null) {
				listaGarantias
						.put(listaItemsTempo.get(i).getProducto()
								.getGarantiaMeses(),
								listaGarantias.get(listaItemsTempo.get(i)
										.getProducto().getGarantiaMeses())
										+ "," + contador);
			} else {
				listaGarantias.put(listaItemsTempo.get(i).getProducto()
						.getGarantiaMeses(), contador + "");
			}

			contador++;
		}

		Iterator itu = listaGarantias.entrySet().iterator();
		while (itu.hasNext()) {
			Map.Entry e = (Map.Entry) itu.next();
			mostrar += "Items:" + e.getValue() + " tienen " + e.getKey()
					+ " meses -- ";
		}

		areaObservaciones.setText("Garantia: " + mostrar);
	}

	public void cerrarFactura() {
		ventanaInterna.dispose();
	}

	public void eliminarArticulo(int row) {
		productosFactura.remove(row);
//		tablaProductos.removeAll();
		while (modeloTabla.getRowCount() != 0) {
			modeloTabla.removeRow(0);
		}
		modeloTabla.getProductosFactura().remove(row);

		actualizarTabla();
		calcularValorFactura();
		colocarGarantiasObservaciones();

	}

	private void tablaMenuSecundario(final MouseEvent evt) {
		if (evt.getButton() == evt.BUTTON3) {
			menus = new JMenuItem[3];
			menus[0] = new JMenuItem("Eliminar");
			menus[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt1) {
					int seleccion = JOptionPane.showConfirmDialog(null,
							"¿Esta seguro de Eliminar el articulo");
					if (seleccion == 0) {
						eliminarArticulo(tablaProductos.rowAtPoint(evt
								.getPoint()));
					}
				}
			});

			menus[1] = new JMenuItem("Descuento");
			menus[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt1) {
					String descuentoEscogido = JOptionPane
							.showInputDialog("¿Porcentaje de descuento que desea hacer");
					System.out
							.println("++++++++++++++++++++++++++++++++++++++++++++++++++ descuento "
									+ descuentoEscogido);

					if (descuentoEscogido != null
							&& !descuentoEscogido.equals("")) {
						int descuento = Integer.parseInt(descuentoEscogido);
						if (descuento > 0) {
							System.out.println("EL DESCUENTO es del "
									+ descuento);
							calcularDescuento(
									tablaProductos.rowAtPoint(evt.getPoint()),
									descuento);
						}
					}
				}
			});

			menus[2] = new JMenuItem("Restaurar");
			menus[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt1) {
					restaurarProducto(tablaProductos.rowAtPoint(evt.getPoint()));

				}
			});

			emergente = new JPopupMenu();
			for (byte x = 0; x < menus.length; x++) {
				emergente.add(menus[x]);
			}
			emergente.show(tablaProductos, evt.getX(), evt.getY());
		}
	}

	public void restaurarProducto(int row) {
		Producto producto = daoProducto.buscarPorCodigo(Integer
				.parseInt(modeloTabla.getValueAt(row, 7) + ""));

		int valorUnitarioSinIva = (int) (producto.getCostoProducto() * (1 + (producto
				.getMargenProducto() / 100)));

		int valorUnitarioConIva = (int) (producto.getCostoProducto()
				* (1 + (producto.getMargenProducto() / 100)) * (1 + (producto
				.getIvaProducto() / 100)));

		modeloTabla.setValueAt("1", row, 2);
		modeloTabla.setValueAt(valorUnitarioSinIva + "", row, 3);
		modeloTabla.setValueAt(valorUnitarioSinIva + "", row, 4);
		modeloTabla.setValueAt(valorUnitarioConIva + "", row, 5);
		modeloTabla.setValueAt(valorUnitarioConIva + "", row, 6);
		calcularValorFactura();
	}

	public void calcularDescuento(int row, float descuento) {
		int precioConDescuento = (int) (Integer.parseInt(modeloTabla
				.getValueAt(row, 5) + ""));
		precioConDescuento = (int) (precioConDescuento * (1 - (descuento / 100)));
		modeloTabla.setValueAt(precioConDescuento, row, 5);
		calcularCambiosProducto(row);
	}

	public void cargarCopiaFactura(int idFactura) {
		FacturaVenta facturaVentaTempo = daoFacturaVenta.consultar(idFactura);
		daoFacturaVenta.anular(facturaVentaTempo.getIdFactura());
		ArrayList<ItemFactura> listaItems = daoItemFactura
				.retornarListaItems(facturaVentaTempo.getIdFactura());
		cargarProductoArticuloLista(listaItems);
		idClienteCargado = facturaVentaTempo.getIdCliente();
		System.out.println("ID DEL CLIENTE " + idClienteCargado);
		cargarCliente();
		modeloTabla.actualizarDatos();
		calcularValorFactura();
		colocarGarantiasObservaciones();
	}

	public void cargarProductoArticuloLista(ArrayList<ItemFactura> lista) {
		DAOArticulo daoArticulo = new DAOArticulo();
		DAOProducto daoProducto = new DAOProducto();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdArticulo() != 0) {
				lista.get(i).setArticulo(
						daoArticulo
								.consultar(lista.get(i).getIdArticulo() + ""));
				daoArticulo.cambiarEstadoDevuelto(lista.get(i).getIdArticulo());
			}
			Producto tempo = daoProducto
					.consultar(lista.get(i).getIdProducto());
			lista.get(i).setProducto(tempo);
			productosFactura.add(tempo);
			modeloTabla.getProductosFactura().add(lista.get(i));
			daoProducto.cambiarExistencias(lista.get(i).getIdProducto(), -1
					* lista.get(i).getCantidad());
		}
	}

	public ArrayList<String> cargarInfoCliente() {
		ArrayList<String> listaInfo = new ArrayList<String>();

		ArrayList<Detalle> listaDetalles = daoDetalle
				.consultarDetalleCompleto(idClienteCargado);
		Persona persona = daoPersona.buscarPorCodigo(idClienteCargado);
		listaInfo.add("NO REGISTRA");
		listaInfo.add("NO REGISTRA");
		listaInfo.add("NO REGISTRA");
		listaInfo.add("NO REGISTRA");
		for (int i = 0; i < listaDetalles.size(); i++) {
			Detalle detalleTempo = listaDetalles.get(i);
			System.out.println("DETALLE  " + detalleTempo.getTipo() + " desc "
					+ detalleTempo.getDescripcion());
			if (detalleTempo.getTipo().equals("Direccion")) {
				listaInfo.add(0, detalleTempo.getDescripcion());
				listaInfo.add(1, detalleTempo.getNombreUbicacion());
			} else if (detalleTempo.getTipo().equals("Telefono")) {
				listaInfo.add(2, detalleTempo.getDescripcion());
			}
		}
		if (persona.getEstado() == 0) {
			listaInfo.add(3, "ESTADO VACIO");
		}

		return listaInfo;
	}

	public TitledBorder getRotulo() {
		return rotulo;
	}

	public void setRotulo(TitledBorder rotulo) {
		this.rotulo = rotulo;
	}

	public String getInfoProduct() {
		return infoProduct;
	}

	public void setInfoProduct(String infoProduct) {
		this.infoProduct = infoProduct;
	}

	public JLabel getLabelCliente() {
		return labelCliente;
	}

	public void setLabelCliente(JLabel labelCliente) {
		this.labelCliente = labelCliente;
	}

	public JLabel getLabelProducto() {
		return labelProducto;
	}

	public void setLabelProducto(JLabel labelProducto) {
		this.labelProducto = labelProducto;
	}

	public JLabel getLabelNumeroFactura() {
		return labelNumeroFactura;
	}

	public void setLabelNumeroFactura(JLabel labelNumeroFactura) {
		this.labelNumeroFactura = labelNumeroFactura;
	}

	public JLabel getLabelFechaFactura() {
		return labelFechaFactura;
	}

	public void setLabelFechaFactura(JLabel labelFechaFactura) {
		this.labelFechaFactura = labelFechaFactura;
	}

	public JLabel getLabelVenceFactura() {
		return labelVenceFactura;
	}

	public void setLabelVenceFactura(JLabel labelVenceFactura) {
		this.labelVenceFactura = labelVenceFactura;
	}

	public JTextField getCampoBusquedaCliente() {
		return campoBusquedaCliente;
	}

	public void setCampoBusquedaCliente(JTextField campoBusquedaCliente) {
		this.campoBusquedaCliente = campoBusquedaCliente;
	}

	public JTextField getCampoBusquedaProducto() {
		return campoBusquedaProducto;
	}

	public void setCampoBusquedaProducto(JTextField campoBusquedaProducto) {
		this.campoBusquedaProducto = campoBusquedaProducto;
	}

	public JTextField getCampoPrefijoFactura() {
		return campoPrefijoFactura;
	}

	public void setCampoPrefijoFactura(JTextField campoPrefijoFactura) {
		this.campoPrefijoFactura = campoPrefijoFactura;
	}

	public JTextField getCampoNumeroFactura() {
		return campoNumeroFactura;
	}

	public void setCampoNumeroFactura(JTextField campoNumeroFactura) {
		this.campoNumeroFactura = campoNumeroFactura;
	}

	public JTextField getCampoFechaFactura() {
		return campoFechaFactura;
	}

	public void setCampoFechaFactura(JTextField campoFechaFactura) {
		this.campoFechaFactura = campoFechaFactura;
	}

	public JTextField getCampoVenceFactura() {
		return campoVenceFactura;
	}

	public void setCampoVenceFactura(JTextField campoVenceFactura) {
		this.campoVenceFactura = campoVenceFactura;
	}

	public JButton getButtonSearchCliente() {
		return buttonSearchCliente;
	}

	public void setButtonSearchCliente(JButton buttonSearchCliente) {
		this.buttonSearchCliente = buttonSearchCliente;
	}

	public JButton getButtonCrearCliente() {
		return buttonCrearCliente;
	}

	public void setButtonCrearCliente(JButton buttonCrearCliente) {
		this.buttonCrearCliente = buttonCrearCliente;
	}

	public JButton getButtonSearchProducto() {
		return buttonSearchProducto;
	}

	public void setButtonSearchProducto(JButton buttonSearchProducto) {
		this.buttonSearchProducto = buttonSearchProducto;
	}

	public DefaultListModel getModelListResultCliente() {
		return modelListResultCliente;
	}

	public void setModelListResultCliente(
			DefaultListModel modelListResultCliente) {
		this.modelListResultCliente = modelListResultCliente;
	}

	public DefaultListModel getModelListResultProducto() {
		return modelListResultProducto;
	}

	public void setModelListResultProducto(
			DefaultListModel modelListResultProducto) {
		this.modelListResultProducto = modelListResultProducto;
	}

	public JList getListResultCliente() {
		return listResultCliente;
	}

	public void setListResultCliente(JList listResultCliente) {
		this.listResultCliente = listResultCliente;
	}

	public JList getListResultProducto() {
		return listResultProducto;
	}

	public void setListResultProducto(JList listResultProducto) {
		this.listResultProducto = listResultProducto;
	}

	public JScrollPane getScrollPaneResultCliente() {
		return scrollPaneResultCliente;
	}

	public void setScrollPaneResultCliente(JScrollPane scrollPaneResultCliente) {
		this.scrollPaneResultCliente = scrollPaneResultCliente;
	}

	public JScrollPane getScrollPaneResultProducto() {
		return scrollPaneResultProducto;
	}

	public void setScrollPaneResultProducto(JScrollPane scrollPaneResultProducto) {
		this.scrollPaneResultProducto = scrollPaneResultProducto;
	}

	public JPanel getPanelInfoFactura() {
		return panelInfoFactura;
	}

	public void setPanelInfoFactura(JPanel panelInfoFactura) {
		this.panelInfoFactura = panelInfoFactura;
	}

	public JPanel getPanelClienteInfo() {
		return panelClienteInfo;
	}

	public void setPanelClienteInfo(JPanel panelClienteInfo) {
		this.panelClienteInfo = panelClienteInfo;
	}

	public JPanel getPanelListaProductos() {
		return panelListaProductos;
	}

	public void setPanelListaProductos(JPanel panelListaProductos) {
		this.panelListaProductos = panelListaProductos;
	}

	public JPanel getPanelObservaciones() {
		return panelObservaciones;
	}

	public void setPanelObservaciones(JPanel panelObservaciones) {
		this.panelObservaciones = panelObservaciones;
	}

	public JPanel getPanelCostoFactura() {
		return panelCostoFactura;
	}

	public void setPanelCostoFactura(JPanel panelCostoFactura) {
		this.panelCostoFactura = panelCostoFactura;
	}

	public JTextArea getAreaObservaciones() {
		return areaObservaciones;
	}

	public void setAreaObservaciones(JTextArea areaObservaciones) {
		this.areaObservaciones = areaObservaciones;
	}

	public JScrollPane getScrollPaneObservaciones() {
		return scrollPaneObservaciones;
	}

	public void setScrollPaneObservaciones(JScrollPane scrollPaneObservaciones) {
		this.scrollPaneObservaciones = scrollPaneObservaciones;
	}

	public JButton getBotonImprimir() {
		return botonImprimir;
	}

	public void setBotonImprimir(JButton botonImprimir) {
		this.botonImprimir = botonImprimir;
	}

	public JLabel getLabelSubtotal() {
		return labelSubtotal;
	}

	public void setLabelSubtotal(JLabel labelSubtotal) {
		this.labelSubtotal = labelSubtotal;
	}

	public JTextField getCampoSubtotal() {
		return campoSubtotal;
	}

	public void setCampoSubtotal(JTextField campoSubtotal) {
		this.campoSubtotal = campoSubtotal;
	}

	public JLabel getLabelBase() {
		return labelBase;
	}

	public void setLabelBase(JLabel labelBase) {
		this.labelBase = labelBase;
	}

	public JLabel getLabelIva() {
		return labelIva;
	}

	public void setLabelIva(JLabel labelIva) {
		this.labelIva = labelIva;
	}

	public JTextField getCampoIva() {
		return campoIva;
	}

	public void setCampoIva(JTextField campoIva) {
		this.campoIva = campoIva;
	}

	public JLabel getLabelTotal() {
		return labelTotal;
	}

	public void setLabelTotal(JLabel labelTotal) {
		this.labelTotal = labelTotal;
	}

	public JTextField getCampoTotal() {
		return campoTotal;
	}

	public void setCampoTotal(JTextField campoTotal) {
		this.campoTotal = campoTotal;
	}

	public JScrollPane getScrollListaProductos() {
		return scrollListaProductos;
	}

	public void setScrollListaProductos(JScrollPane scrollListaProductos) {
		this.scrollListaProductos = scrollListaProductos;
	}

	public ModeloTabla getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(ModeloTabla modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public void setTablaProductos(JTable tablaProductos) {
		this.tablaProductos = tablaProductos;
	}

	public int getIdClienteCargado() {
		return idClienteCargado;
	}

	public void setIdClienteCargado(int idClienteCargado) {
		this.idClienteCargado = idClienteCargado;
	}

	public JLabel getLabelClienteSeleccionado() {
		return labelClienteSeleccionado;
	}

	public void setLabelClienteSeleccionado(JLabel labelClienteSeleccionado) {
		this.labelClienteSeleccionado = labelClienteSeleccionado;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		tablaMenuSecundario(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		tablaMenuSecundario(e);

	}

	public JLabel getLabelInfoCliente() {
		return labelInfoCliente;
	}

	public void setLabelInfoCliente(JLabel labelInfoCliente) {
		this.labelInfoCliente = labelInfoCliente;
	}

}
