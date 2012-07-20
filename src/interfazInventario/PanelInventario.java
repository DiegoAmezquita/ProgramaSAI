package interfazInventario;

import Codigo.Articulo;
import Codigo.FacturaCompra;
import Codigo.Producto;
import Codigo.Sitio;
import DAO.DAOArticulo;
import DAO.DAOFacturaCompra;
import DAO.DAOPersona;
import DAO.DAOProducto;
import DAO.DAOSitio;
import DAO.DAOvarios;
import Interfaz.FrameMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

public class PanelInventario extends JPanel implements MouseListener {

	private TitledBorder rotulo;
	private String infoProduct = "PRUEBA FACTURACION";
	private JLabel labelProveedor;
	private JLabel labelInfoProveedor;
	private JLabel labelNumeroFactura;
	private JLabel labelFechaFactura;
	private JLabel labelSitio;
	private JLabel labelProductoCantidad;
	private JLabel labelProducto;
	private JTextField campoBusquedaProducto;
	private JTextField campoNumeroFactura;
	private JTextField campoFechaFactura;
	private JComboBox comboBoxSitios;
	private JLabel labelFechaIngreso;
	private JTextField campoFechaIngreso;
	private JButton buttonSearchProveedor;
	private JButton buttonSearchProducto;
	private JButton buttonBuscarProducto;
	private DefaultListModel modelListResultProducto;
	private JList listResultProducto;
	private JScrollPane scrollPaneResultProveedor;
	private JScrollPane scrollPaneResultProducto;
	private JPanel panelInfoFactura;
	private JPanel panelArticuloInfo;
	private JPanel panelListaArticulos;
	private JPanel panelObservaciones;
	private JPanel panelCostoFactura;
	private JTextArea areaObservaciones;
	private JScrollPane scrollPaneObservaciones;
	private JButton botonGuardar;
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
	private JScrollPane scrollListaArticulos;
	private ModeloTablaArticulos modeloTablaArticulos;
	private JTable tablaArticulos;
	private DAOPersona daoPersona;
	private DAOProducto daoProducto;
	private DAOvarios daoVarios;
	private DAOSitio daoSitio;
	private DAOArticulo daoArticulo;
	private DAOFacturaCompra daoFacturaCompra;
	private ArrayList<Articulo> articulosFactura;
	private JButton botonAgregarArticulo;
	private FrameMain frameMain;
	private JButton botonLimpiar;
	private JButton botonCancelar;
	private int idProductoCargado = 0;
	private int idProveedorCargado = 0;
	private int idSitioCargado = 0;
	private int idFacturaGuarda = 0;
	private Map listaArticulosMostrar;

	private ArrayList<ItemInventario> listaItemsInventario;

	private JLabel labelProveedorSeleccionado;
	private PanelNumeroSerie panelNumeroSerie;
	private Articulo articuloEdicion;
	private JPopupMenu emergente;// es el "marco" donde se pone el menu
	private JMenuItem menus[];// lleva la lista de las opciones

	private JInternalFrame ventanaInterna;
	private JDialog dialogoInformacionProveedor;

	public PanelInventario(FrameMain frameMain, JInternalFrame ventanaInterna) {
		// rotulo = BorderFactory.createTitledBorder(infoProduct);
		// rotulo.setTitleColor(new Color(0, 0, 128));
		// setBorder(rotulo);
		setLayout(null);
		this.frameMain = frameMain;
		this.ventanaInterna = ventanaInterna;

		articulosFactura = new ArrayList<Articulo>();

		labelProveedor = new JLabel("Proveedor");
		labelProveedor.setBounds(10, 20, 80, 30);
		add(labelProveedor);

		labelProveedorSeleccionado = new JLabel(
				"NO HA SELECCIONADO UN PROVEEDOR");
		labelProveedorSeleccionado.setBounds(90, 20, 720, 30);
		add(labelProveedorSeleccionado);

		labelInfoProveedor = new JLabel(
				"<html><font color='red'>info</font> </html>");
		labelInfoProveedor.setBounds(350, 20, 50, 30);
		labelInfoProveedor.addMouseListener(new MouseListener() {

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
		labelInfoProveedor.setVisible(false);
		add(labelInfoProveedor);

		buttonSearchProveedor = new JButton("BUSCAR PROVEEDOR");
		buttonSearchProveedor.setBounds(470, 20, 160, 30);
		buttonSearchProveedor.setActionCommand("BUSCARPROVEEDORINVENTARIO");
		buttonSearchProveedor.addActionListener(frameMain);
		add(buttonSearchProveedor);

		labelFechaIngreso = new JLabel("Fecha Ing");
		labelFechaIngreso.setBounds(10, 60, 80, 30);
		add(labelFechaIngreso);

		campoFechaIngreso = new JTextField();
		campoFechaIngreso.setBounds(90, 60, 150, 30);
		// campoFechaFactura.setEditable(false);
		campoFechaIngreso.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!campoFechaFactura.getText().equals("")) {
					cargarFecha(campoFechaIngreso);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (!campoFechaIngreso.getText().equals("")) {
					SimpleDateFormat formateador = new SimpleDateFormat(
							"yyyy/MM/dd kk:mm:ss");
					formateador.setLenient(false);
					try {
						Date fecha = formateador.parse(campoFechaIngreso
								.getText());
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null,
								"La fecha tiene un formato incorrecto");

						campoFechaIngreso.requestFocus();
					}
				}

			}

		});
		add(campoFechaIngreso);

		labelSitio = new JLabel("Sitio");
		labelSitio.setBounds(250, 60, 80, 30);
		add(labelSitio);

		comboBoxSitios = new JComboBox();
		comboBoxSitios.setBounds(310, 60, 150, 30);
		add(comboBoxSitios);

		buttonSearchProducto = new JButton("BUSCAR PRODUCTO");
		buttonSearchProducto.setBounds(470, 60, 160, 30);
		buttonSearchProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				crearFrameBuscarProducto();

			}
		});
		add(buttonSearchProducto);

		listaItemsInventario = new ArrayList<ItemInventario>();

		crearPanelInfoFactura();
		// crearPanelInfoProducto();
		crearPanelListaArticulos();
		crearPanelObservaciones();
		crearPanelCostoFactura();
		crearBotonesAcciones();

		daoPersona = new DAOPersona();
		daoVarios = new DAOvarios();
		daoProducto = new DAOProducto();
		daoSitio = new DAOSitio();
		daoFacturaCompra = new DAOFacturaCompra();
		daoArticulo = new DAOArticulo();
		cargarSitios();
		cargarFecha(campoFechaIngreso);
		cargarFecha(campoFechaFactura);
		listaArticulosMostrar = new HashMap();

	}

	public void cargarSitios() {
		ArrayList<Sitio> temporal = daoSitio.consultar();
		for (int i = 0; i < temporal.size(); i++) {
			comboBoxSitios.addItem(temporal.get(i).getNombreSitio());
		}
	}

	public void crearFrameBuscarProducto() {
		PanelCreacionProducto.getInstancia(frameMain, "INVENTARIO").setVisible(
				true);

	}

	public void agregarConNumeroSerie(ArrayList<String> tempoNumerosSerie) {
		modeloTablaArticulos.permitir = true;
		Producto producto = daoProducto.buscarPorCodigo(getIdProductoCargado());
		for (int i = 0; i < tempoNumerosSerie.size(); i++) {
			agregarArticuloFactura(producto, tempoNumerosSerie.get(i),
					tempoNumerosSerie.size());
		}
		modeloTablaArticulos.permitir = false;
	}

	public void cargarFecha(JTextField campoFecha) {
		String[] fecha = daoVarios.retornarFechaBD();
		campoFecha.setText(fecha[2] + "/" + fecha[1] + "/" + fecha[0] + "  "
				+ fecha[3]);
	}

	public void crearPanelInfoFactura() {
		panelInfoFactura = new JPanel();
		panelInfoFactura.setBounds(740, 15, 250, 90);
		TitledBorder rotuloInfoFactura = BorderFactory
				.createTitledBorder("Factura de Compra");
		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
		panelInfoFactura.setBorder(rotuloInfoFactura);
		panelInfoFactura.setLayout(null);
		add(panelInfoFactura);

		labelNumeroFactura = new JLabel("Numero");
		labelNumeroFactura.setBounds(20, 20, 80, 30);
		panelInfoFactura.add(labelNumeroFactura);

		campoNumeroFactura = new JTextField();
		campoNumeroFactura.setBounds(100, 20, 130, 30);
		panelInfoFactura.add(campoNumeroFactura);

		labelFechaFactura = new JLabel("Fecha");
		labelFechaFactura.setBounds(20, 55, 80, 30);
		panelInfoFactura.add(labelFechaFactura);

		campoFechaFactura = new JTextField();
		campoFechaFactura.setBounds(100, 55, 130, 30);
		// campoFechaFactura.setEditable(false);
		campoFechaFactura.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (!campoFechaFactura.getText().equals("")) {
					// cargarFecha(campoFechaFactura);
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
		panelInfoFactura.add(campoFechaFactura);

	}

	public void crearPanelListaArticulos() {
		panelListaArticulos = new JPanel();
		panelListaArticulos.setBounds(10, 100, 980, 410);
		TitledBorder rotuloTitulo = BorderFactory
				.createTitledBorder("Articulos");
		rotuloTitulo.setTitleColor(new Color(0, 0, 128));
		panelListaArticulos.setBorder(rotuloTitulo);
		panelListaArticulos.setLayout(null);
		add(panelListaArticulos);

		final String[] columnNames1 = { "ITEM", "ARTICULO", "CANTIDAD",
				"COSTO U.", "EXISTENCIAS", "PRECIO ACTUAL", "PRECIO SUGERIDO",
				"PRECIO", "idproducto" };

		modeloTablaArticulos = new ModeloTablaArticulos(columnNames1, 0, this);
		tablaArticulos = new JTable(modeloTablaArticulos);
		tablaArticulos.getColumnModel().getColumn(8).setMaxWidth(0);

		tablaArticulos.getColumnModel().getColumn(8).setMinWidth(0);

		tablaArticulos.getColumnModel().getColumn(8).setPreferredWidth(0);

		scrollListaArticulos = new JScrollPane(tablaArticulos);
		scrollListaArticulos.setBounds(10, 20, 960, 380);
		panelListaArticulos.add(scrollListaArticulos);
		tablaArticulos.addMouseListener(this);

		TableColumn columna = tablaArticulos.getColumn("ARTICULO");
		columna.setPreferredWidth(300);
		columna = tablaArticulos.getColumn("ITEM");
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
		areaObservaciones.setEditable(false);
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

	public void crearDialogoInformacionCliente() {
		dialogoInformacionProveedor = new JDialog(frameMain);
		dialogoInformacionProveedor.setLocationRelativeTo(null);
		dialogoInformacionProveedor.setSize(300, 300);
		dialogoInformacionProveedor.setVisible(true);
	}

	public void crearBotonesAcciones() {
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(150, 650, 150, 40);
		botonGuardar.setActionCommand("GUARDARFACTURACOMPRA");
		botonGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				crearFacturaCompra();
			}
		});
		botonGuardar.addActionListener(frameMain);
		add(botonGuardar);

		botonLimpiar = new JButton("LIMPIAR");
		botonLimpiar.setBounds(320, 650, 150, 40);
		add(botonLimpiar);

		botonCancelar = new JButton("CANCELAR");
		botonCancelar.setBounds(490, 650, 150, 40);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarListaFactura();

			}
		});
		add(botonCancelar);

	}

	public void mostrarListaFactura() {
		System.out.println("INICIA REVISION");
		Iterator it = listaArticulosMostrar.entrySet().iterator();
		ArrayList<String> tempo;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println(e.getKey() + " " + e.getValue());
		}
		System.out.println("TERMINA REVISION");
	}

	public ItemInventario buscarItemInventario(int idProducto) {
		for (int i = 0; i < listaItemsInventario.size(); i++) {
			if (listaItemsInventario.get(i).getProducto().getIdProducto() == idProducto) {
				return listaItemsInventario.get(i);
			}
		}
		return null;
	}

	public void agregarArticuloFactura(Producto producto, String numeroSerie,
			int cantidad) {

		ItemInventario

		itemTemporal = buscarItemInventario(producto.getIdProducto());

		if (itemTemporal == null) {
			itemTemporal = new ItemInventario();

			if (producto.getTieneSerial() == 1) {
				itemTemporal.getSeriales().add(numeroSerie);
			}
			itemTemporal.setNumero(listaItemsInventario.size() + 1);
			itemTemporal.setCantidad(1);
			itemTemporal.setProducto(producto);

			String mostrar = daoVarios.consultarVariosPorCategoriaNivel2(
					"Tipo de Elemento", producto.getIdCategoriaProducto())
					+ " "
					+ daoVarios.consultarVariosPorCategoriaNivel2(
							"Marca Elemento", producto.getIdMarcaProducto())
					+ " " + producto.getDescripcionProducto();

			itemTemporal.setDescripcion(mostrar);
			itemTemporal.setCostoUnitario(producto.getCostoProducto());

			int costo = producto.getCostoProducto() * cantidad;

			modeloTablaArticulos.addRow(new String[] {
					itemTemporal.getNumero() + "",
					mostrar,
					cantidad + "",
					costo + "",
					producto.getExistenciasProducto() + "",
					producto.getPrecioProducto() + "",
					calcularPrecioSugerido(Integer.parseInt(costo + ""),
							producto) + "", itemTemporal.getPrecio() + "",
					producto.getIdProducto() + "" });

			listaItemsInventario.add(itemTemporal);

		} else {
			listaItemsInventario.remove(itemTemporal.getNumero() - 1);
			if (producto.getTieneSerial() == 1) {
				itemTemporal.getSeriales().add(numeroSerie);
			}
			itemTemporal.setCantidad(itemTemporal.getCantidad() + 1);
			listaItemsInventario
					.add(itemTemporal.getNumero() - 1, itemTemporal);

			System.out.println("A COLOCAR LA CANTIDAD DE "
					+ itemTemporal.getCantidad());
			modeloTablaArticulos.cambiarCantidadPermitido(
					itemTemporal.getCantidad(), itemTemporal.getNumero() - 1);
		}
		colocarNumeroSerieObservaciones();
		calcularTotalFacturaInventario();
	}

	public ArrayList<String> retornarSeriales(int idProducto) {
		ArrayList<String> temporal = new ArrayList<String>();
		for (int i = 0; i < articulosFactura.size(); i++) {
			if (articulosFactura.get(i).getIdProductoArticulo() == (idProducto)) {
				temporal.add(articulosFactura.get(i).getNumeroSerie());
			}
		}

		return temporal;
	}

	public void colocarNumeroSerieObservaciones() {
		String mostrar = "";
		for (int i = 0; i < listaItemsInventario.size(); i++) {
			if (listaItemsInventario.get(i).getSeriales().size() > 0) {
				mostrar += " Item " + (i + 1) + ": ";
				for (int j = 0; j < listaItemsInventario.get(i).getSeriales()
						.size(); j++) {
					mostrar += listaItemsInventario.get(i).getSeriales().get(j)
							+ ", ";
				}
			}
		}
		areaObservaciones.setText(mostrar);

	}

	public void cambiarCantidadEdicion(int idProducto, int cantidad) {
		for (int i = 0; i < modeloTablaArticulos.getRowCount(); i++) {
			if (idProducto == Integer.parseInt(modeloTablaArticulos.getValueAt(
					i, 8) + "")) {
				modeloTablaArticulos.cambiarCantidadPermitido(cantidad + "", i);
				break;
			}
		}
	}

	public void actualizarTabla1() {

		Iterator it = listaArticulosMostrar.entrySet().iterator();
		String mostrar = "";
		int contador = 1;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("BUSCANDO ESTO " + e.getKey());
			Producto product = daoProducto.buscarPorCodigo(Integer.parseInt(e
					.getKey() + ""));
			mostrar = daoVarios.consultarVariosPorCategoriaNivel2(
					"Tipo de Elemento", product.getIdCategoriaProducto())
					+ " "
					+ daoVarios.consultarVariosPorCategoriaNivel2(
							"Marca Elemento", product.getIdMarcaProducto())
					+ " " + product.getDescripcionProducto();
			String costo = (product.getCostoProducto()) + "";
			for (int i = 0; i < articulosFactura.size(); i++) {
				if (product.getIdProducto() == (articulosFactura.get(i)
						.getIdProductoArticulo())) {
					costo = articulosFactura.get(i).getCostoArticulo()
							* (Integer.parseInt(e.getValue() + "")) + "";
					break;
				}
			}
			if (!existeProductoFactura(mostrar)) {
				modeloTablaArticulos
						.addRow(new String[] {
								contador + "",
								mostrar,
								e.getValue() + "",
								costo,
								product.getExistenciasProducto() + "",
								product.getPrecioProducto() + "",
								calcularPrecioSugerido(
										Integer.parseInt(costo + ""), product)
										+ "", "", product.getIdProducto() + "" });
			}
			contador++;
		}
		calcularTotalFacturaInventario();

	}

	public void calcularPrecioSugerido(int row) {
		Producto producto = daoProducto.buscarPorCodigo(Integer
				.parseInt(modeloTablaArticulos.getValueAt(row, 8) + ""));
		int precioSugerido = Integer.parseInt(modeloTablaArticulos.getValueAt(
				row, 3) + "");
		precioSugerido = (int) (precioSugerido
				* (1 + (producto.getIvaProducto() / 100)) * (1 + (producto
				.getMargenProducto() / 100)));
		modeloTablaArticulos.setValueAt(precioSugerido, row, 6);

	}

	public int calcularPrecioSugerido(int costo, Producto producto) {
		int precioSugerido = (int) (costo
				* (1 + (producto.getIvaProducto() / 100)) * (1 + (producto
				.getMargenProducto() / 100)));
		return precioSugerido;
	}

	public boolean existeProductoFactura(String mostrar) {
		for (int i = 0; i < modeloTablaArticulos.getRowCount(); i++) {
			String tempo = (String) modeloTablaArticulos.getValueAt(i, 1);
			if (tempo.equals(mostrar)) {
				return true;
			}
		}

		return false;
	}

	public boolean cambiarExistenciasTabla(int row, int valor, boolean interno) {

		ItemInventario itemTempo = listaItemsInventario.get(row);
		listaItemsInventario.remove(row);
		itemTempo.setCantidad(valor);
		listaItemsInventario.add(row, itemTempo);

		if (itemTempo.getProducto().getTieneSerial() == 1) {
			if (!interno) {
				JOptionPane
						.showMessageDialog(null,
								"No se puede cambiar la cantidad a un producto con serial");
				return false;
			}
		}

		return true;
	}

	public void crearFacturaCompra() {
		FacturaCompra facturaTemporal = new FacturaCompra();
		boolean correcto = true;
		if (!campoNumeroFactura.getText().equals("")) {
			facturaTemporal.setNumero(campoNumeroFactura.getText());
		} else {
			JOptionPane.showMessageDialog(null,
					"EL NUMERO DE LA FACTURA NO PUEDE ESTAR VACIO");
			correcto = false;
		}

		if (idProveedorCargado != 0) {
			facturaTemporal.setIdProveedor(idProveedorCargado);
		} else {
			JOptionPane.showMessageDialog(null,
					"AUN NO HA SELECCIONADO EL PROVEEDOR DEL PEDIDO");
			correcto = false;
		}

		facturaTemporal.setFecha(campoFechaFactura.getText());
		facturaTemporal.setFechaIngreso(campoFechaIngreso.getText());

		if (correcto) {
			idFacturaGuarda = daoFacturaCompra.insert(facturaTemporal);
			agregarArticulos();
			cerrarFactura();
		}
	}

	public void cerrarFactura() {
		ventanaInterna.dispose();
	}

	public void agregarArticulos() {
		Articulo articuloTemporal;
		String idSitio = daoSitio.consultar(
				(comboBoxSitios.getSelectedItem() + "")).getIdSitio()
				+ "";

		for (int i = 0; i < listaItemsInventario.size(); i++) {
			ItemInventario itemTempo = listaItemsInventario.get(i);
			articuloTemporal = new Articulo();
			articuloTemporal.setIdDocumentoSoporte(idFacturaGuarda + "");
			articuloTemporal.setCostoArticulo(itemTempo.getCostoUnitario());
			articuloTemporal.setFechaIngreso(campoFechaIngreso.getText());
			articuloTemporal.setIdProductoArticulo(itemTempo.getProducto()
					.getIdProducto());
			articuloTemporal.setIdProveedor(idProveedorCargado + "");
			articuloTemporal.setIdSitio(idSitio);
			daoProducto.cambiarCosto(itemTempo.getProducto().getIdProducto()
					+ "", itemTempo.getCostoUnitario() + "");
			if (itemTempo.getPrecio() != 0) {
				daoProducto.cambiarPrecio(itemTempo.getProducto()
						.getIdProducto() + "", itemTempo.getPrecio() + "");
			} else {
				daoProducto.cambiarPrecio(itemTempo.getProducto()
						.getIdProducto() + "", itemTempo.getPrecioSugerido()
						+ "");
			}
			for (int j = 0; j < itemTempo.getSeriales().size(); j++) {
				articuloTemporal.setNumeroSerie(itemTempo.getSeriales().get(j));
				daoArticulo.insert(articuloTemporal);
			}
			daoProducto.cambiarExistencias(itemTempo.getProducto()
					.getIdProducto(), itemTempo.getCantidad());
		}

		// FacturaCompra facturaTemporal = daoFacturaCompra.consultar(idFactura)

		Iterator it = listaArticulosMostrar.entrySet().iterator();
		int contador = 0;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			daoProducto.cambiarCosto(e.getKey() + "",
					modeloTablaArticulos.getValueAt(contador, 3) + "");
			int nuevoPrecio = 0;
			if ((modeloTablaArticulos.getValueAt(contador, 7) + "").equals("")) {
				nuevoPrecio = Integer.parseInt(modeloTablaArticulos.getValueAt(
						contador, 6) + "");
			} else {
				nuevoPrecio = Integer.parseInt(modeloTablaArticulos.getValueAt(
						contador, 7) + "");
			}

			daoProducto.cambiarPrecio(e.getKey() + "", nuevoPrecio + "");
			daoProducto.cambiarExistencias(Integer.parseInt(e.getKey() + ""),
					Integer.parseInt(e.getValue() + ""));
			contador++;
		}

		JOptionPane.showMessageDialog(null, "FACTURA DE COMPRA GUARDADA");

	}

	public void calcularTotalFacturaInventario() {
		int precio = 0;
		HashMap<Float, Integer> listaBases = new HashMap<Float, Integer>();
		for (int i = 0; i < listaItemsInventario.size(); i++) {
			ItemInventario itemTempo = listaItemsInventario.get(i);
			precio += itemTempo.cantidad * itemTempo.getCostoUnitario();
			int costoBase = (int) (((itemTempo.getCostoUnitario() * (itemTempo
					.getProducto().getIvaProducto() / 100)) * itemTempo
					.getCantidad()));
			if (listaBases.get(itemTempo.getProducto().getIvaProducto()) != null) {
				listaBases.put(
						itemTempo.getProducto().getIvaProducto(),
						listaBases
								.get(itemTempo.getProducto().getIvaProducto())
								+ costoBase);
			} else {
				listaBases.put(itemTempo.getProducto().getIvaProducto(),
						costoBase);
			}
		}
		campoSubtotal.setText(precio + "");
		precio = 0;
		defaultlistaBase.removeAllElements();
		Iterator itu = listaBases.entrySet().iterator();
		while (itu.hasNext()) {
			System.out.println("ENTRA PARA AGREGAR " + defaultlistaBase.size());
			Map.Entry e = (Map.Entry) itu.next();
			defaultlistaBase.addElement(e.getKey() + "% " + e.getValue());
			precio += Integer.parseInt(e.getValue() + "");
		}
		campoIva.setText(precio + "");

		campoTotal.setText((Integer.parseInt(campoSubtotal.getText()) + Integer
				.parseInt(campoIva.getText())) + "");

	}

	public void cambiarDatoItemInventario(int dato, int row, int column) {
		ItemInventario tempo = listaItemsInventario.get(row);
		listaItemsInventario.remove(row);
		switch (column) {
		case 2:
			tempo.setCantidad(dato);
			break;
		case 3:
			tempo.setCostoUnitario(dato);
			break;
		case 6:
			tempo.setPrecioSugerido(dato);
			break;
		case 7:
			tempo.setPrecio(dato);
			break;

		}
		listaItemsInventario.add(row, tempo);

	}

	public void actualizarNumeroItem() {
		for (int i = 0; i < listaItemsInventario.size(); i++) {
			ItemInventario itemTempo = listaItemsInventario.get(i);
			listaItemsInventario.remove(i);
			itemTempo.setNumero(i + 1);
			listaItemsInventario.add(itemTempo);
			modeloTablaArticulos.setValueAt(i + 1, i, 0);
		}
	}

	public void eliminarArticulo(int row) {
		modeloTablaArticulos.removeRow(row);
		listaItemsInventario.remove(row);
		colocarNumeroSerieObservaciones();
		actualizarNumeroItem();
		calcularTotalFacturaInventario();

	}

	public void editarArticulo(int row) {
		ItemInventario itemTempo = listaItemsInventario.get(row);
		if (itemTempo.getProducto().getTieneSerial() == 1) {
			setIdProductoCargado(itemTempo.getProducto().getIdProducto());
			panelNumeroSerie = new PanelNumeroSerie(this,itemTempo.getSeriales());
			panelNumeroSerie.setVisible(true);
			panelNumeroSerie.setEstadoEdicion(true);
//			panelNumeroSerie.cargarNumerosSerie(itemTempo.getSeriales());
		} else {
			JOptionPane.showMessageDialog(null, "NO HAY NADA QUE EDITAR");
		}

	}

	public void actualizarNumerosSerie() {
		for (int i = 0; i < articulosFactura.size(); i++) {
			if (articulosFactura.get(i).getIdProductoArticulo() == getIdProductoCargado()) {
				articulosFactura.remove(i);
				i = -1;
			}
		}
		listaArticulosMostrar.remove(getIdProductoCargado());
	}

	private void tablaMenuSecundario(final MouseEvent evt) {
		// System.out.println("BOTON DEL MOUSE "+evt.getButton());
		if (evt.getButton() == evt.BUTTON3) {
			menus = new JMenuItem[2];
			menus[0] = new JMenuItem("Eliminar");
			menus[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt1) {
					int seleccion = JOptionPane.showConfirmDialog(null,
							"¿Esta seguro de Eliminar el articulo");
					if (seleccion == 0) {
						eliminarArticulo(tablaArticulos.rowAtPoint(evt
								.getPoint()));
						// tablaProductos.re

						// tablaProductos.remove
						// actualizarTabla();
					}
				}
			});

			menus[1] = new JMenuItem("Editar");
			menus[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt1) {
					editarArticulo(tablaArticulos.rowAtPoint(evt.getPoint()));
				}

			});

			emergente = new JPopupMenu();
			for (byte x = 0; x < menus.length; x++) {
				emergente.add(menus[x]);
			}
			/*
			 * para la linea de arriba seria lo mismo que:
			 * emergente.add(menus[0]); emergente.add(menus[1]);
			 */
			emergente.show(tablaArticulos, evt.getX(), evt.getY());// donde
																	// $tabla es
																	// la tabla
																	// o el
																	// objeto
																	// sobre el
																	// que
																	// mostraras
																	// el menu
		}
	}

	public JLabel getLabelInfoProveedor() {
		return labelInfoProveedor;
	}

	public void setLabelInfoProveedor(JLabel labelInfoProveedor) {
		this.labelInfoProveedor = labelInfoProveedor;
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

	public JLabel getLabelProveedor() {
		return labelProveedor;
	}

	public void setLabelProveedor(JLabel labelProveedor) {
		this.labelProveedor = labelProveedor;
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

	public JLabel getLabelSitio() {
		return labelSitio;
	}

	public void setLabelSitio(JLabel labelSitio) {
		this.labelSitio = labelSitio;
	}

	public JLabel getLabelProductoCantidad() {
		return labelProductoCantidad;
	}

	public void setLabelProductoCantidad(JLabel labelProductoCantidad) {
		this.labelProductoCantidad = labelProductoCantidad;
	}

	public JLabel getLabelProducto() {
		return labelProducto;
	}

	public void setLabelProducto(JLabel labelProducto) {
		this.labelProducto = labelProducto;
	}

	public JTextField getCampoBusquedaProducto() {
		return campoBusquedaProducto;
	}

	public void setCampoBusquedaProducto(JTextField campoBusquedaProducto) {
		this.campoBusquedaProducto = campoBusquedaProducto;
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

	public JComboBox getComboBoxSitios() {
		return comboBoxSitios;
	}

	public void setComboBoxSitios(JComboBox comboBoxSitios) {
		this.comboBoxSitios = comboBoxSitios;
	}

	public JButton getButtonSearchCliente() {
		return buttonSearchProveedor;
	}

	public void setButtonSearchCliente(JButton buttonSearchCliente) {
		this.buttonSearchProveedor = buttonSearchCliente;
	}

	public JButton getButtonSearchProducto() {
		return buttonSearchProducto;
	}

	public void setButtonSearchProducto(JButton buttonSearchProducto) {
		this.buttonSearchProducto = buttonSearchProducto;
	}

	public DefaultListModel getModelListResultProducto() {
		return modelListResultProducto;
	}

	public void setModelListResultProducto(
			DefaultListModel modelListResultProducto) {
		this.modelListResultProducto = modelListResultProducto;
	}

	public JList getListResultProducto() {
		return listResultProducto;
	}

	public void setListResultProducto(JList listResultProducto) {
		this.listResultProducto = listResultProducto;
	}

	public JScrollPane getScrollPaneResultCliente() {
		return scrollPaneResultProveedor;
	}

	public void setScrollPaneResultCliente(JScrollPane scrollPaneResultCliente) {
		this.scrollPaneResultProveedor = scrollPaneResultCliente;
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

	public JPanel getPanelArticuloInfo() {
		return panelArticuloInfo;
	}

	public void setPanelArticuloInfo(JPanel panelArticuloInfo) {
		this.panelArticuloInfo = panelArticuloInfo;
	}

	public JPanel getPanelListaArticulos() {
		return panelListaArticulos;
	}

	public void setPanelListaArticulos(JPanel panelListaArticulos) {
		this.panelListaArticulos = panelListaArticulos;
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
		return botonGuardar;
	}

	public void setBotonImprimir(JButton botonImprimir) {
		this.botonGuardar = botonImprimir;
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

	// public JTextField getCampoBase() {
	// return campoBase;
	// }
	//
	// public void setCampoBase(JTextField campoBase) {
	// this.campoBase = campoBase;
	// }

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

	public JScrollPane getScrollListaArticulos() {
		return scrollListaArticulos;
	}

	public void setScrollListaArticulos(JScrollPane scrollListaArticulos) {
		this.scrollListaArticulos = scrollListaArticulos;
	}

	public ModeloTablaArticulos getModeloTablaArticulos() {
		return modeloTablaArticulos;
	}

	public void setModeloTablaArticulos(
			ModeloTablaArticulos modeloTablaArticulos) {
		this.modeloTablaArticulos = modeloTablaArticulos;
	}

	public JTable getTablaArticulos() {
		return tablaArticulos;
	}

	public void setTablaArticulos(JTable tablaArticulos) {
		this.tablaArticulos = tablaArticulos;
	}

	public DAOPersona getDaoPersona() {
		return daoPersona;
	}

	public void setDaoPersona(DAOPersona daoPersona) {
		this.daoPersona = daoPersona;
	}

	public DAOProducto getDaoProducto() {
		return daoProducto;
	}

	public void setDaoProducto(DAOProducto daoProducto) {
		this.daoProducto = daoProducto;
	}

	public DAOvarios getDaoVarios() {
		return daoVarios;
	}

	public void setDaoVarios(DAOvarios daoVarios) {
		this.daoVarios = daoVarios;
	}

	public DAOSitio getDaoSitio() {
		return daoSitio;
	}

	public void setDaoSitio(DAOSitio daoSitio) {
		this.daoSitio = daoSitio;
	}

	public JButton getBotonAgregarArticulo() {
		return botonAgregarArticulo;
	}

	public void setBotonAgregarArticulo(JButton botonAgregarArticulo) {
		this.botonAgregarArticulo = botonAgregarArticulo;
	}

	public FrameMain getFrameMain() {
		return frameMain;
	}

	public void setFrameMain(FrameMain frameMain) {
		this.frameMain = frameMain;
	}

	public int getIdProductoCargado() {
		return idProductoCargado;
	}

	public void setIdProductoCargado(int idProductoCargado) {
		this.idProductoCargado = idProductoCargado;
	}

	public int getIdProveedorCargado() {
		return idProveedorCargado;
	}

	public void setIdProveedorCargado(int idProveedorCargado) {
		this.idProveedorCargado = idProveedorCargado;
	}

	public int getIdSitioCargado() {
		return idSitioCargado;
	}

	public void setIdSitioCargado(int idSitioCargado) {
		this.idSitioCargado = idSitioCargado;
	}

	public JLabel getLabelProveedorSeleccionado() {
		return labelProveedorSeleccionado;
	}

	public void setLabelProveedorSeleccionado(JLabel labelProveedorSeleccionado) {
		this.labelProveedorSeleccionado = labelProveedorSeleccionado;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		tablaMenuSecundario(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		tablaMenuSecundario(arg0);
	}

}
