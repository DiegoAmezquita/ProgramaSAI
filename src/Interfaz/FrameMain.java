package Interfaz;

import Codigo.Contacto;
import Codigo.Detalle;
import Codigo.Datos;
import Codigo.Login;
import Codigo.OrdenServicio;
import Codigo.Persona;
import Codigo.Producto;
import DAO.DAOContacto;
import DAO.DAODetalle;
import DAO.DAOLogin;
import DAO.DAOPersona;
import DAO.DAOProducto;
import DAO.DAOSitio;
import DAO.DAOvarios;
import interfazAdminFacturacion.PanelSearchFactura;
import interfazBuzon.PanelBuzon;
import interfazFacturacion.PanelFacturacion;
import interfazInventario.PanelCreacionPersona;
import interfazInventario.PanelCreacionProducto;
import interfazInventario.PanelInventario;
import interfazInventario.PanelNumeroSerie;
import interfazPersona.PanelPersona;
import interfazPersona.PanelResult;
import interfazPersona.PanelSearch;
import interfazProductos.PanelProduct;
import interfazProductos.PanelResultProduct;
import interfazProductos.PanelSearchProduct;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JComboBox;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FrameMain extends JFrame implements ActionListener,
		ListSelectionListener, ItemListener {

	JDesktopPane escritorio;
	private JMenuBar menuBar;
	private JMenu menu;
	private PanelSearch panelSearch;
	private PanelSearchFactura panelSearchFactura;
	private PanelSearchProduct panelSearchProduct;
	private PanelResult panelResult;
	private PanelResultProduct panelResultProduct;
	private PanelFacturacion panelFacturacion;
	private PanelInventario panelInventario;
	private PanelBuzon panelBuzon;
	private DAOPersona daoPersona;
	private DAOProducto daoProduct;
	private DAODetalle daoDetalle;
	private DAOLogin daoLogin;
	private DAOContacto daoContacto;
	private DAOvarios daoVarios;
	private DAOSitio daoSitio;
	private String nombreUsuarioOnline;
	private PanelPersona panelPersona;
	private PanelProduct panelProduct;
	private DialogAsignacion dialogAsignacion;
	private JMenu menu1;
	private HiloRevizarCorreo hiloRevizarCorreo;
	private int codLog;
	private PanelCreacionProducto panelCreacionProducto;
	private PanelCreacionPersona panelCreacionPersona;
	private PanelNumeroSerie panelNumeroSerie;

	public FrameMain(String usuario) {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width, 950);
		// setSize(1100, 800);
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		escritorio = new JDesktopPane();
		escritorio.setLayout(null);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(escritorio,
				GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(escritorio,
				GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE));
		setLocationRelativeTo(null);
		daoPersona = new DAOPersona();
		daoProduct = new DAOProducto();
		daoDetalle = new DAODetalle();
		daoLogin = new DAOLogin();
		daoContacto = new DAOContacto();
		daoVarios = new DAOvarios();
		daoSitio = new DAOSitio();

		codLog = daoLogin.buscarCodigoLogin(usuario);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int eleccion = JOptionPane.showConfirmDialog(null,
						"ÀEsta seguro que desea salir?");
				if (eleccion == 0) {
					System.exit(0);
				}
			}
		});

		crearMenu(usuario);
		hiloRevizarCorreo = new HiloRevizarCorreo(menu1, codLog);
		hiloRevizarCorreo.start();
	}

	public void crearFramePersona() {
		JInternalFrame ventanaInterna = new JInternalFrame("Persona", true,
				true, true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelSearch = new PanelSearch(this);
		panelResult = new PanelResult(this);
		panelPersona = new PanelPersona(panelSearch, panelResult);
		panelPersona.setBounds(0, 0, 1024, 768);
		ventanaInterna.add(panelPersona);
	}

	public void crearFrameProducto() {
		JInternalFrame ventanaInterna = new JInternalFrame("Producto", true,
				true, true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelSearchProduct = new PanelSearchProduct(this);
		panelResultProduct = new PanelResultProduct(this);
		panelProduct = new PanelProduct(panelSearchProduct, panelResultProduct);
		panelProduct.setBounds(0, 0, 1024, 768);
		ventanaInterna.add(panelProduct);
	}

	/*
	 * si operacion igual a --- 0 es para creacion de nueva factura --- 1 para
	 * anulacion y copia de una factura ---
	 */
	public void crearFrameFacturacion(int operacion, int idFactura) {
		JInternalFrame ventanaInterna = new JInternalFrame("Facturacion", true,
				true, true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelSearch = new PanelSearch(this);
		panelFacturacion = new PanelFacturacion(this, ventanaInterna);
		panelFacturacion.setBounds(0, 0, 1000, 700);
		ventanaInterna.add(panelFacturacion);

		if (operacion == 1) {
			panelFacturacion.cargarCopiaFactura(idFactura);
		}
	}

	public void crearFrameAdminFacturacion() {
		JInternalFrame ventanaInterna = new JInternalFrame(
				"Administracion Facturas de Venta", true, true, true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelSearchFactura = new PanelSearchFactura(this);
		ventanaInterna.add(panelSearchFactura);
	}

	public void crearFrameInventario() {
		JInternalFrame ventanaInterna = new JInternalFrame("Inventario", true,
				true, true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelInventario = new PanelInventario(this, ventanaInterna);
		panelInventario.setBounds(0, 0, 1000, 700);
		ventanaInterna.add(panelInventario);
	}

	public void crearFrameBuzon() {
		JInternalFrame ventanaInterna = new JInternalFrame("Buzon", true, true,
				true, true);
		ventanaInterna.setBounds(0, 0, 1024, 768);
		escritorio.add(ventanaInterna, JLayeredPane.DEFAULT_LAYER);
		ventanaInterna.setVisible(true);
		ventanaInterna.setLayout(null);
		panelBuzon = new PanelBuzon(this, codLog);
		panelBuzon.setBounds(0, 0, 1000, 600);
		ventanaInterna.add(panelBuzon);
	}

	public void crearMenu(String usuario) {
		menuBar = new JMenuBar();
		menu = new JMenu("Opciones");
		JMenuItem itemPerson = new JMenuItem("Persona");
		itemPerson.addActionListener(this);
		itemPerson.setActionCommand("Persona");
		JMenuItem itemProduct = new JMenuItem("Producto");
		itemProduct.addActionListener(this);
		itemProduct.setActionCommand("Producto");
		JMenuItem itemFacturacion = new JMenuItem("Facturacion");
		itemFacturacion.addActionListener(this);
		itemFacturacion.setActionCommand("Facturacion");
		JMenuItem itemAdminFacturacion = new JMenuItem("Admin Facturacion");
		itemAdminFacturacion.addActionListener(this);
		itemAdminFacturacion.setActionCommand("Admin Facturacion");
		JMenuItem itemInventario = new JMenuItem("Inventario");
		itemInventario.addActionListener(this);
		itemInventario.setActionCommand("Inventario");
		JMenuItem itemBuzon = new JMenuItem("Buzon");
		itemBuzon.addActionListener(this);
		itemBuzon.setActionCommand("Buzon");
		menu.add(itemPerson);
		menu.add(itemProduct);
		menu.add(itemFacturacion);
		menu.add(itemAdminFacturacion);
		menu.add(itemInventario);
		menu.add(itemBuzon);
		menu.addSeparator();
		JMenuItem itemSalir = new JMenuItem("Salir");
		itemSalir.addActionListener(this);
		itemSalir.setActionCommand("Salir");
		menu.add(itemSalir);
		nombreUsuarioOnline = usuario;
		menu1 = new JMenu("Conectado como: " + usuario);
		menu1.setEnabled(false);
		menuBar.add(menu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menu1);
		setJMenuBar(menuBar);
	}

	public static void main(String[] args) {
		FrameMain a = new FrameMain("Diego");
		a.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e0) {
		String command = e0.getActionCommand();
		if (command.equals("Persona")) {
			crearFramePersona();
		} else if (command.equals("Producto")) {
			crearFrameProducto();
		} else if (command.equals("Facturacion")) {
			crearFrameFacturacion(0, 0);
		} else if (command.equals("Admin Facturacion")) {
			crearFrameAdminFacturacion();
		} else if (command.equals("Inventario")) {
			crearFrameInventario();
		} else if (command.equals("Buzon")) {
			crearFrameBuzon();
		} else if (command.equals("Salir")) {
			if (JOptionPane.showConfirmDialog(null,
					"¿Esta seguro que desea salir?") == 0) {
				System.exit(0);
			}
		}
		if (command.equals("BUSCAR")) {
			buscarPersona();
		}

		if (command.equals("BUSCARPRODUCTO")) {
			if (!panelSearchProduct.getFieldSearch().getText().equals("")) {
				panelSearchProduct.cargarResultados();
				// panelResult.getPanelDetails().bloquearElementos();
				// panelResult.getPanelInfoPersona().bloquearElementos();
			}
		}

		if (command.equals("BUSCARCLIENTEFACTURACION")) {
			panelCreacionPersona = PanelCreacionPersona.getInstancia(this,
					"FACTURACION");
			panelCreacionPersona.setPadre("FACTURACION");
			panelCreacionPersona.setVisible(true);
			panelCreacionPersona.cambiarLabels();
		}

		if (command.equals("BUSCARPRODUCTOFACTURACION")) {
			if (!panelFacturacion.getCampoBusquedaProducto().getText()
					.equals("")) {
				panelFacturacion.cargarResultadosProducto();
			}
		}

		if (command.equals("CALCULARPRECIO")) {
			panelResultProduct.getPanelInfoProduct().calcularPrecio();
		}

		if (command.equals("BUSCARPROVEEDORINVENTARIO")) {
			System.out.println("AGREGA ESTE PARA INTENTARIO");
			panelCreacionPersona = PanelCreacionPersona.getInstancia(this,
					"INVENTARIO");
			panelCreacionPersona.setPadre("INVENTARIO");
			panelCreacionPersona.setVisible(true);
			panelCreacionPersona.cambiarLabels();
		}

		if (command.equals("BUSCARPROVEEDOR")) {
			panelCreacionPersona.buscarProveedor();
		}

		if (command.equals("CREARPROVEEDORINVENTARIO")) {
			panelCreacionPersona = PanelCreacionPersona.getInstancia(this,
					"INVENTARIO");
			panelCreacionPersona.setVisible(true);
		}

		if (command.equals("CANCELARPERSONAPANELTEMPORAL")) {
			panelCreacionPersona.setVisible(false);
			panelCreacionPersona = null;
		}

		if (command.equals("CANCELARPRODUCTOPANELTEMPORAL")) {
			panelCreacionProducto.setVisible(false);
			panelCreacionProducto = null;
		}

		if (command.equals("AGREGARARTICULOS")) {
			if (panelInventario.getIdProductoCargado() != 0
					&& panelInventario.getIdProveedorCargado() != 0) {
				panelInventario.setIdSitioCargado(daoSitio.consultar(
						panelInventario.getComboBoxSitios().getSelectedItem()
								+ "").getIdSitio());
				Producto producto = daoProduct.buscarPorCodigo(panelInventario
						.getIdProductoCargado());
				if (producto.getTieneSerial() == 0) {
					// if(!panelInventario.getCampoProductoCantidad().getText().equals("")){
					// int contador =
					// Integer.parseInt(panelInventario.getCampoProductoCantidad().getText());
					// panelInventario.agregarArticuloFactura(producto,"",1);
					// }else{
					// JOptionPane.showMessageDialog(null,
					// "EL CAMPO CANTIDAD NO PUEDE ESTAR VACIO SI EL PRODUCTO NO TIENE NUMERO DE SERIE");
					// }
				} else {
					// panelNumeroSerie = new PanelNumeroSerie(this);
					// panelNumeroSerie.setVisible(true);
				}
			}
		}

		if (command.equals("GUARDARINVENTARIONUMERODESERIE")) {
			ArrayList<String> tempoNumerosSerie = panelNumeroSerie
					.devolverNumerosSerie();
			Producto producto = daoProduct.buscarPorCodigo(panelInventario
					.getIdProductoCargado());
			for (int i = 0; i < tempoNumerosSerie.size(); i++) {
				panelInventario.agregarArticuloFactura(producto,
						tempoNumerosSerie.get(i), tempoNumerosSerie.size());
			}
			// panelNumeroSerie.setVisible(false);
			// panelNumeroSerie = null;
		}

		if (command.equals("CANCELARNUMERODESERIE")) {
			panelNumeroSerie.setVisible(false);
			panelNumeroSerie = null;
		}

		if (command.equals("IMPRIMIRFACTURAVENTA")) {
			if (panelFacturacion.getIdClienteCargado() != 0) {
				panelFacturacion.crearFacturaVenta();
			} else {
				JOptionPane.showMessageDialog(null,
						"DEBE SELECCIONAR EL CLIENTE PRIMERO");
			}
		}

		if (command.equals("SELECCIONARNUMERODESERIE")) {
			panelFacturacion.cargarProductoNumeroSerie();
		}

		if (command.equals("CANCELARSELECCIONNUMERODESERIE")) {
			panelFacturacion.ocultarSeleccionNumeroSerie();
		}

		if (command.equals("NUEVAPERSONA")) {
			System.out.println("NUEVA PERSONA");
			panelResult.getPanelInfoPersona().reiniciarCampos();
			panelResult.getPanelInfoPersona().setNewPerson(true);
			panelResult.getPanelInfoPersona().setPersonLoad(0);
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();
			panelResult.getPanelInfoPersona().desbloquearElementos();
			panelResult.getPanelInfoPersona().bloquearEnUso();
		}
		if (command.equals("EDITARPERSONA")) {
			System.out.println("EDITAR PERSONA ");
			panelResult.getPanelInfoPersona().setNewPerson(false);
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();
			panelResult.getPanelInfoPersona().desbloquearElementos();
			panelResult.getPanelInfoPersona().bloquearEnUso();
		}

		if (command.equals("GUARDARPERSONA")) {
			System.out.println("GUARDAR PERSONA");
			if (panelResult.getPanelInfoPersona().validarCampos()) {
				if (panelResult.getPanelInfoPersona()
						.validarDigitoVerificacion()) {
					if (!daoPersona.verificarDocumentoExiste(panelResult
							.getPanelInfoPersona().getPersonLoad(), panelResult
							.getPanelInfoPersona().getComboBoxTypeDocument()
							.getSelectedIndex() + 1, panelResult
							.getPanelInfoPersona().getFieldNumberDocument()
							.getText())) {
						if (panelResult.getPanelInfoPersona().isNewPerson()) {
							Persona persona = panelResult.getPanelInfoPersona()
									.crearPersona();
							if (panelResult.getPanelInfoPersona()
									.isCreacionPersonaCorrecta()) {
								panelResult.getPanelInfoPersona().setNewPerson(
										false);
								if (persona != null) {
									daoPersona.insert(persona);
									JOptionPane.showMessageDialog(this,
											"PERSONA AGREGADA");
									panelSearch.desbloquearElementos();
									panelResult.desbloquearPestanias();
									panelResult.getPanelInfoPersona()
											.bloquearElementos();
									panelResult.getPanelInfoPersona()
											.desbloquearEdicion();
									panelResult.getPanelInfoPersona()
											.desbloquearEnUso();
									if (panelResult.getPanelInfoPersona()
											.isVieneFactura()) {
										panelBuzon.setVisible(false);
										panelPersona.setVisible(false);
										panelProduct.setVisible(false);
										panelFacturacion.setVisible(true);
									}
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"DOCUMENTO DEMASIADO LARGO");
							}
						} else {
							Persona persona = panelResult.getPanelInfoPersona()
									.crearPersona();
							if (persona != null) {
								persona.setCodigo(panelResult
										.getPanelInfoPersona().getPersonLoad());
								daoPersona.editar(persona);
								JOptionPane.showMessageDialog(this,
										"PERSONA GUARDADA");
								panelSearch.desbloquearElementos();
								panelResult.desbloquearPestanias();
								panelResult.getPanelInfoPersona()
										.bloquearElementos();
								panelResult.getPanelInfoPersona()
										.desbloquearEdicion();
								panelResult.getPanelInfoPersona()
										.desbloquearEnUso();
								panelSearch.cargarResultados();
							}
						}
					} else {
						JOptionPane.showMessageDialog(this,
								"Ese documento ya se encuentra guardado");
					}

				} else {
					JOptionPane.showMessageDialog(this,
							"Codigo de verificacion errado");
				}

			} else {
				JOptionPane.showMessageDialog(null, "ERROR EN LOS DATOS");
			}

		}
		if (command.equals("CANCELARPERSONA")) {
			panelResult.getPanelInfoPersona().setNewPerson(false);
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
			panelResult.getPanelInfoPersona().desbloquearEdicion();
			panelResult.getPanelInfoPersona().bloquearElementos();
			panelResult.getPanelInfoPersona().desbloquearEnUso();
			if (panelResult.getPanelInfoPersona().isVieneFactura()) {
				panelBuzon.setVisible(false);
				panelPersona.setVisible(false);
				panelProduct.setVisible(false);
				panelFacturacion.setVisible(true);
				panelResult.getPanelInfoPersona().setVieneFactura(false);
			}
		}
		if (command.equals("NUEVODETALLE")) {
			System.out.println("NUEVO DETALLE");
			panelResult.getPanelDetails().reiniciarCampos();
			panelResult.getPanelDetails().setNewDetail(true);
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();
			panelResult.getPanelDetails().desbloquearElementos();
			panelResult.getPanelDetails().bloquearEnUso();
		}
		if (command.equals("EDITARDETALLE")) {
			System.out.println("EDITAR DETALLE");
			panelResult.getPanelDetails().setNewDetail(false);
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();
			panelResult.getPanelDetails().desbloquearElementos();
			panelResult.getPanelDetails().bloquearEnUso();
		}

		if (command.equals("GUARDARDETALLE")) {
			System.out.println("GUARDAR DETALLE");
			if (panelResult.getPanelDetails().validarDetalle()) {
				if (panelResult.getPanelDetails().isNewDetail()) {
					Detalle detalle = crearDetalle();
					daoDetalle.insert(detalle);
					JOptionPane.showMessageDialog(this, "DETALLE AGREGADO");
					panelSearch.desbloquearElementos();
					panelResult.desbloquearPestanias();
					panelResult.getPanelDetails().cargarDetalles(
							panelResult.getPanelInfoPersona().getPersonLoad());
				} else {
					Detalle detalle = crearDetalle();
					panelResult.getPanelInfoPersona().setNewPerson(false);
					detalle.setCodigo(panelResult.getPanelDetails()
							.getDetailLoad());
					daoDetalle.editar(detalle);
					JOptionPane.showMessageDialog(this, "DETALLE GUARDADO");
					panelSearch.desbloquearElementos();
					panelResult.desbloquearPestanias();
					panelResult.getPanelDetails().cargarDetalles(
							panelResult.getPanelInfoPersona().getPersonLoad());
				}
				panelResult.getPanelDetails().bloquearElementos();
				panelResult.getPanelDetails().desbloquearEnUso();

			} else {
				JOptionPane.showMessageDialog(this, "ERROR EN LOS DATOS");
			}
		}
		if (command.equals("BORRARDETALLE")) {
			System.out.println("BORRAR DETALLE");
			int confirmacionBorrar = JOptionPane.showConfirmDialog(this,
					"¿ESTA SEGURO DE BORRAR EL DETALLE?");
			if (confirmacionBorrar == 0) {
				int codigoDetalle = panelResult.getPanelDetails()
						.getDetailLoad();
				daoDetalle.borrar(codigoDetalle);
				panelResult.getPanelDetails().cargarDetalles(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelDetails().reiniciarCampos();
				panelResult.getPanelDetails().bloquearEdicion();
			}
		}

		if (command.equals("CANCELARDETALLE")) {
			panelResult.getPanelDetails().setNewDetail(false);
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
			panelResult.getPanelDetails().desbloquearEdicion();
			panelResult.getPanelDetails().bloquearElementos();
			panelResult.getPanelDetails().desbloquearEnUso();
		}

		if (command.equals("GUARDARUSUARIO")) {
			System.out.println("GUARDAR USUARIO");
			if (panelResult.getPanelUser().isSinUsuario()) {
				Login login = crearLogin();
				daoLogin.insert(login);
				JOptionPane.showMessageDialog(this, "USUARIO AGREGADO");
				panelSearch.desbloquearElementos();
				panelResult.desbloquearPestanias();
				panelResult.getPanelUser().cargarUsuario(
						panelResult.getPanelInfoPersona().getPersonLoad());

			} else {
				Login login = crearLogin();
				daoLogin.editar(login);
				JOptionPane.showMessageDialog(this, "USUARIO GUARDADO");
				panelSearch.desbloquearElementos();
				panelResult.desbloquearPestanias();
			}

		}
		if (command.equals("BORRARUSUARIO")) {
			System.out.println("BORRAR USUARIO");
			int confirmacionBorrar = JOptionPane.showConfirmDialog(this,
					"¿ESTA SEGURO DE BORRAR EL USUARIO?");
			if (confirmacionBorrar == 0) {
				int codigoUsuario = panelResult.getPanelUser().getCodigoLogin();
				daoLogin.borrar(codigoUsuario);
				panelResult.getPanelUser().reiniciarCampos();
				panelResult.getPanelUser().cargarUsuario(
						panelResult.getPanelInfoPersona().getPersonLoad());
			}
		}
		if (command.equals("EDITARORDENSERVICIO")) {
			System.out.println("EDITAR ORDEN SERVICIO");
			panelResult.getPanelNewOrder().setEdicion(true);
			panelResult.getPanelNewOrder().reiniciarCampos();
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setComponentAt(i,
							panelResult.getPanelNewOrder());
				}
			}
			panelResult.getTabbedPane().setSelectedIndex(-1);
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setSelectedIndex(i);
				}
			}

			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();
			panelResult.getPanelNewOrder().cargarOrdenEdicion();
			panelResult.getPanelNewOrder().desbloquearEdicion();
			panelResult.getPanelNewOrder().edicionOrdenDesbloquear();

		}
		if (command.equals("NUEVAORDENSERVICIO")) {
			System.out.println("NUEVA ORDEN SERVICIO");

			panelResult.getPanelNewOrder().setEdicion(false);
			panelResult.getPanelNewOrder().reiniciarCampos();
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setComponentAt(i,
							panelResult.getPanelNewOrder());
				}
			}

			panelResult.getTabbedPane().setSelectedIndex(-1);
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setSelectedIndex(i);
				}
			}
			panelResult.getPanelNewOrder().bloquearEdicion();
			panelResult.getPanelNewOrder().nuevaOrdenDesbloquear();
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();

		}
		if (command.equals("CANCELARORDENSERVICIO")) {
			System.out.println("CANCELAR ORDEN SERVICIO");
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setComponentAt(i,
							panelResult.getPanelOrders());
				}
			}
			panelResult.getTabbedPane().setSelectedIndex(-1);
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setSelectedIndex(i);
				}
			}
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
			panelResult.getPanelOrders().cargarOrdenes();
			panelResult.getPanelNewOrder().setEdicion(false);
			panelResult.getPanelOrders().ordenNoSeleccionada();
			panelResult.getPanelNewOrder().reiniciarListaElementos();

		}
		if (command.equals("REPORTEORDENSERVICIO")) {
			System.out.println("REPORTE ORDEN SERVICIO");
			panelResult.getPanelOrders().crearReporte();

		}

		if (command.equals("BORRARELEMENTO")) {
			System.out.println("BORRAR ELEMENTO");
			if (!panelResult.getPanelNewOrder().isEdicion()) {
				panelResult.getPanelNewOrder().borrarElemento();
			}

		}

		if (command.equals("BUSCARCONTACTO")) {
			System.out.println("BUSCAR CONTACTO");
			String buscarPersona = JOptionPane
					.showInputDialog("Persona a Buscar:");
			System.out.println(buscarPersona);
			if (buscarPersona != null && !buscarPersona.equals("")) {
				panelSearch.bloquearElementos();
				panelResult.bloquearPestanias();
				panelResult.getPanelContacts().cargarListaPersonas(
						buscarPersona);
			}
		}
		if (command.equals("EDITARCONTACTO")) {
			System.out.println("EDITAR CONTACTO");
			panelResult.getPanelContacts().setEdicion(true);
			panelResult.getPanelContacts().bloquearUso();
			panelSearch.bloquearElementos();
			panelResult.bloquearPestanias();

		}

		if (command.equals("GUARDARCONTACTO")) {
			System.out.println("GUARDAR CONTACTO");
			if (!panelResult.getPanelContacts().isEdicion()) {
				Contacto contacto = panelResult.getPanelContacts()
						.crearContacto();
				daoContacto.insert(contacto);
				panelResult.getPanelContacts().cargarContactos();
				panelResult.getPanelContacts().reiniciarInfo();
				JOptionPane.showMessageDialog(this, "CONTACTO AGREGADO");
			} else {
				Contacto contacto = panelResult.getPanelContacts()
						.crearContacto();
				daoContacto.editar(contacto);
				panelResult.getPanelContacts().cargarContactos();
				panelResult.getPanelContacts().reiniciarInfo();
				JOptionPane.showMessageDialog(this, "CONTACTO GUARDADO");
			}
			panelResult.getPanelContacts().desbloquearUso();
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
			panelResult.getPanelContacts().bloquearEdicion();
			panelResult.getPanelContacts().reiniciarListaBusqueda();

		}
		if (command.equals("BORRARCONTACTO")) {
			System.out.println("BORRAR CONTACTO");
			int codigoContacto = panelResult.getPanelContacts()
					.getCodContactLoad();
			if (panelResult.getPanelContacts().isEdicion()) {
				if (codigoContacto != 0) {
					int confirmacionBorrar = JOptionPane.showConfirmDialog(
							this, "Â¿ESTA SEGURO DE BORRAR EL CONTACTO?");
					if (confirmacionBorrar == 0) {
						daoContacto.borrar(codigoContacto);
						panelResult.getPanelContacts().reiniciarInfo();
						panelResult.getPanelContacts().cargarContactos();
						panelResult.getPanelContacts().bloquearEdicion();
					} else {
						panelResult.getPanelContacts().desbloquearEdicion();
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"Ningun Contacto Seleccionado");
				}
			}

		}

		if (command.equals("CANCELARCONTACTO")) {
			panelResult.getPanelContacts().desbloquearUso();
			panelResult.getPanelContacts().bloquearEdicion();
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
			panelResult.getPanelContacts().desbloquearEdicion();
			panelResult.getPanelContacts().reiniciarInfo();
		}

		if (command.equals("AGREGARELEMENTO")) {
			System.out.println("AGREGAR ELEMENTO1111");
			if (!panelResult.getPanelNewOrder().isEdicion()) {
				if (panelResult.getPanelNewOrder().getIndexEdicion() >= 0) {
					panelResult
							.getPanelNewOrder()
							.getArrayItemsTemp()
							.remove(panelResult.getPanelNewOrder()
									.getIndexEdicion());
					panelResult
							.getPanelNewOrder()
							.getArrayItemsTemp()
							.add(panelResult.getPanelNewOrder()
									.getIndexEdicion(),
									panelResult.getPanelNewOrder()
											.crearElemento());

				} else {
					panelResult
							.getPanelNewOrder()
							.getArrayItemsTemp()
							.add(panelResult.getPanelNewOrder().crearElemento());

				}
				panelResult.getPanelNewOrder().setIndexEdicion(-1);
				panelResult.getPanelNewOrder().reiniciarInfoElemento();
				panelResult.getPanelNewOrder().actualizarListaElementos();
			} else {

				panelResult.getPanelNewOrder().guardarElementoEdicion(
						panelResult.getPanelNewOrder().crearElemento());
			}

		}
		if (command.equals("GUARDARORDENSERVICIO")) {
			System.out.println("GUARDAR ORDEN SERVICIO");
			if (!panelResult.getPanelNewOrder().isEdicion()) {
				JOptionPane.showMessageDialog(this, "ORDEN GUARDADA");
				panelResult.getPanelNewOrder().setCodPerson(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelNewOrder().setNombreUser(
						nombreUsuarioOnline);
				OrdenServicio ordenServicio = panelResult.getPanelNewOrder()
						.crearOrdenServicio();
				panelResult.getPanelNewOrder().getDaoOrdenServicio()
						.insert(ordenServicio);
				int codigoOrden = panelResult.getPanelNewOrder()
						.getDaoOrdenServicio()
						.buscarOrdenServicioParaCodigo(ordenServicio);
				panelResult.getPanelNewOrder().guardarElementosEnOrdenServicio(
						codigoOrden);
				panelResult.getPanelNewOrder().crearEventosPorOS(codigoOrden);
			} else {
				JOptionPane.showMessageDialog(this, "ORDEN GUARDADA");
				panelResult.getPanelNewOrder().setCodPerson(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelNewOrder().setCodUser(8);
				OrdenServicio ordenServicio = panelResult.getPanelNewOrder()
						.crearOrdenServicio();
				panelResult.getPanelNewOrder().getDaoOrdenServicio()
						.editar(ordenServicio);

			}
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setComponentAt(i,
							panelResult.getPanelOrders());
				}
			}

			panelResult.getTabbedPane().setSelectedIndex(-1);
			for (int i = 0; i < panelResult.getTabbedPane().getTabCount(); i++) {
				if (panelResult.getTabbedPane().getTitleAt(i).equals("Ordenes")) {
					panelResult.getTabbedPane().setSelectedIndex(i);
				}
			}
			panelResult.getPanelOrders().cargarOrdenes();
			panelSearch.desbloquearElementos();
			panelResult.desbloquearPestanias();
		}

		if (command.equals("CANCELAREVENTOACTIVIDAD")) {
			System.out.println("CANCELAR EVENTO ACTIVIDAD");
			// panelBuzon.ocultarElementos();
			// panelBuzon.setTipoEleccion(0);
		}

		if (command.equals("GUARDAREVENTOBUZON")) {
			panelBuzon.crearEvento();
			JOptionPane.showMessageDialog(this, "EVENTO GUARDADO");
			panelBuzon.getPanelEvent().limpiarCampos();
			panelBuzon.cargarMensajes();
		}
		if (command.equals("GUARDAREVENTO")) {
			if (dialogAsignacion != null) {
				dialogAsignacion.crearEvento();
				dialogAsignacion.limpiarCampos();
			}

		}
		if (command.equals("NUEVAACTIVIDAD")) {
			System.out.println("NUEVA ACTIVIDAD");
			panelBuzon.getPanelActivity().habilitarCampos();
		}
		if (command.equals("GUARDARACTIVIDAD")) {
			if (!panelBuzon.getPanelActivity().getAreaDescriptionActivity()
					.getText().equals("")) {
				panelBuzon.crearActividad();
				panelBuzon.cargarActividades();
				JOptionPane.showMessageDialog(this, "ACTIVIDAD GUARDADA");
				panelBuzon.getPanelActivity().limpiarCampo();
				panelBuzon.cargarMensajes();
				panelBuzon.getPanelActivity().deshabilitarCampos();
			} else {
				JOptionPane.showMessageDialog(this,
						"La descripcion no puede estar vacia");
			}
		}
		if (command.equals("CANCELARACTIVIDAD")) {
			System.out.println("CANCELAR ACTIVIDAD");
			panelBuzon.getPanelActivity().deshabilitarCampos();
		}

		if (command.equals("CANCELAREVENTO")) {
			System.out.println("CANCELAR EVENTO");
			panelBuzon.getPanelEvent().limpiarCampos();

		}
		if (command.equals("ASIGNARITEM")) {
			System.out.println("ASIGNAR ITEM");
			if (panelResult.getPanelNewOrder().isEdicion()) {
				dialogAsignacion = new DialogAsignacion(this);
				int elemento = panelResult.getPanelNewOrder().getCodElemSel();
				System.out.println("CODIGO QUE VOY A ASIGNAR " + elemento);
				dialogAsignacion.setCodItemSelected(elemento);
				dialogAsignacion.setVisible(true);
			}
		}
		if (command.equals("QUITARTODOSPERMISOS")) {
			panelResult.getPanelUser().quitarTodosPermisos();
		}
		if (command.equals("QUITARUNOSPERMISOS")) {
			panelResult.getPanelUser().quitarPermiso();
		}
		if (command.equals("PASARTODOSPERMISOS")) {
			panelResult.getPanelUser().agregarTodosPermisos();
		}
		if (command.equals("PASARUNOSPERMISOS")) {
			panelResult.getPanelUser().agregarPermiso();
		}
		if (command.equals("IMPRIMIRORDENSERVICIO")) {
			panelResult.getPanelOrders().mostrarImpresionReporte();
		}

		if (command.equals("NUEVOPRODUCTO")) {
			System.out.println("NUEVO PRODUCTO");
			panelResultProduct.getPanelInfoProduct().reiniciarCampos();
			panelResultProduct.getPanelInfoProduct().setNewProduct(true);
			panelResultProduct.getPanelInfoProduct().setProductLoad(0);
			panelResultProduct.setInfoProduct("");
			panelResultProduct.actualizarInfoProduct();
			panelSearchProduct.bloquearElementos();
			panelResultProduct.bloquearPestanias();
			panelResultProduct.getPanelInfoProduct().desbloquearElementos();
			panelResultProduct.getPanelInfoProduct().bloquearEnUso();
		}
		if (command.equals("EDITARPRODUCTO")) {
			System.out.println("EDITAR PRODUCTO");
			panelResultProduct.getPanelInfoProduct().setNewProduct(false);
			panelSearchProduct.bloquearElementos();
			panelResultProduct.bloquearPestanias();
			panelResultProduct.getPanelInfoProduct().desbloquearElementos();
			panelResultProduct.getPanelInfoProduct().bloquearEnUso();
		}

		if (command.equals("CLONARPRODUCTO")) {
			System.out.println("CLONAR PRODUCTO");
			panelResultProduct.getPanelInfoProduct().setNewProduct(false);
			panelSearchProduct.desbloquearElementos();
			panelResultProduct.desbloquearPestanias();
			if (panelResultProduct.getPanelInfoProduct().getProductLoad() > 0) {
				panelResultProduct.getPanelInfoProduct().desbloquearEdicion();
			}
			panelResultProduct.getPanelInfoProduct().bloquearElementos();
			panelResultProduct.getPanelInfoProduct().desbloquearEnUso();

			// creando el evento de clonacion

			panelResultProduct.getPanelInfoProduct().clonarCampos();
			panelResultProduct.getPanelInfoProduct().setNewProduct(true);
			panelResultProduct.getPanelInfoProduct().setProductLoad(0);
			panelResultProduct.setInfoProduct("");
			panelResultProduct.actualizarInfoProduct();
			panelSearchProduct.bloquearElementos();
			panelResultProduct.bloquearPestanias();
			panelResultProduct.getPanelInfoProduct().desbloquearElementos();
			panelResultProduct.getPanelInfoProduct().bloquearEnUso();

		}

		if (command.equals("GUARDARPRODUCTO")) {
			System.out.println("GUARDAR PRODUCTO");
			if (panelResultProduct.getPanelInfoProduct().validarCampos(
					panelResultProduct.getPanelInfoProduct().isNewProduct())) {
				if (panelResultProduct.getPanelInfoProduct().isNewProduct()) {
					Producto product = panelResultProduct.getPanelInfoProduct()
							.crearProducto();
					if (panelResultProduct.getPanelInfoProduct()
							.isCreacionProductoCorrecta()) {
						panelResultProduct.getPanelInfoProduct().setNewProduct(
								false);
						if (product != null) {
							daoProduct.insert(product);
							JOptionPane.showMessageDialog(this,
									"PRODUCTO CREADO");
							panelSearchProduct.desbloquearElementos();
							panelResultProduct.desbloquearPestanias();
							panelResultProduct.getPanelInfoProduct()
									.bloquearElementos();
							panelResultProduct.getPanelInfoProduct()
									.desbloquearEdicion();
							panelResultProduct.getPanelInfoProduct()
									.desbloquearEnUso();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"PROBLEMAS CON LA CREACION INFORMAR");
					}
				} else {
					Producto product = panelResultProduct.getPanelInfoProduct()
							.crearProducto();
					if (product != null) {
						product.setIdProducto(panelResultProduct
								.getPanelInfoProduct().getProductLoad());
						if (JOptionPane.showConfirmDialog(null,
								"¿Esta seguro de guardar los cambios?") == 0) {
							daoProduct.editar(product);
							JOptionPane.showMessageDialog(this,
									"PRODUCTO GUARDADO");
							panelSearchProduct.desbloquearElementos();
							panelResultProduct.desbloquearPestanias();
							panelResultProduct.getPanelInfoProduct()
									.bloquearElementos();
							panelResultProduct.getPanelInfoProduct()
									.desbloquearEdicion();
							panelResultProduct.getPanelInfoProduct()
									.desbloquearEnUso();
							panelSearchProduct.cargarResultados();
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "ERROR EN LOS DATOS");
			}

		}
		if (command.equals("CANCELARPRODUCTO")) {
			panelResultProduct.getPanelInfoProduct().setNewProduct(false);
			panelSearchProduct.desbloquearElementos();
			panelResultProduct.desbloquearPestanias();
			if (panelResultProduct.getPanelInfoProduct().getProductLoad() > 0) {
				panelResultProduct.getPanelInfoProduct().desbloquearEdicion();
			}
			panelResultProduct.getPanelInfoProduct().bloquearElementos();
			panelResultProduct.getPanelInfoProduct().desbloquearEnUso();
		}

	}

	public Login crearLogin() {
		Login login = new Login();
		login.setNombreLog(panelResult.getPanelUser().getFieldNameUser()
				.getText());
		login.setClaveLog(panelResult.getPanelUser().getFieldPassword()
				.getText());
		login.setCodigoPers(panelResult.getPanelInfoPersona().getPersonLoad());

		if (panelResult.getPanelUser().getComboBoxTypeUser().getSelectedIndex() < 0) {
			login.setTipoUsuario(3);

		} else {
			login.setTipoUsuario(panelResult.getPanelUser()
					.getComboBoxTypeUser().getSelectedIndex() + 1);

		}
		System.out.println("TIPO SSUSUUSUSUS " + login.getTipoUsuario());

		return login;

	}

	public Detalle crearDetalle() {
		Detalle detalle = new Detalle();
		detalle.setNombre(panelResult.getPanelDetails().getFieldName()
				.getText());
		detalle.setNombreUbicacion(panelResult.getPanelDetails()
				.getComboBoxUbication().getSelectedItem()
				+ "");
		detalle.setTipo(panelResult.getPanelDetails().getComboBoxType()
				.getSelectedItem()
				+ "");
		detalle.setDescripcion(panelResult.getPanelDetails()
				.getFieldDescripcion().getText());
		detalle.setCodigoPersona(panelResult.getPanelInfoPersona()
				.getPersonLoad());
		return detalle;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}
		JList theList = (JList) e.getSource();
		Datos d = (Datos) theList.getSelectedValue();

		// System.out.println("CODIGO ESCOJIDO " + d.getCodigo());
		// System.out.println("nombre lista: " + theList.getName());

		if (d != null) {
			if (d.getCodigo() != 0 && theList.getName().equals("listResult")) {
				System.out.println("1");
				panelResult.getPanelInfoPersona().setPersonLoad(
						Integer.parseInt(d.getCodigo() + ""));

				Persona personaResultado = daoPersona.consultar(Integer
						.parseInt(d.getCodigo() + ""));
				panelResult.setInfoPersona(personaResultado.getNombre()
						+ " "
						+ personaResultado.getApellido()
						+ " "
						+ daoVarios.darFormatoNumeros(personaResultado
								.getNumeroDocumento() + ""));
				panelResult.actualizarInfoPersona();
				panelResult.getPanelInfoPersona().cargarPersona(
						personaResultado);
				System.out.println("2");
				System.out.println("CODIGO PRUEBA " + d.getCodigo());
				panelResult.getPanelDetails().cargarDetalles(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelDetails().reiniciarCampos();
				System.out.println("3");
				panelResult.getPanelUser().cargarUsuario(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelContacts().setCodPersonLoad(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelContacts().cargarContactos();
				panelResult.getPanelContacts().personaSeleccionada();
				panelResult.getPanelOrders().setCodPerson(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelOrders().cargarOrdenes();
				panelResult.getPanelOrders().personaSeleccionada();
				panelResult.getPanelDetails().desbloquearPersonaSeleccionada();
				panelResult.getPanelUser().desbloquearElementos();
				panelResult.getPanelOrders().ordenNoSeleccionada();
				System.out.println("4");

			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listDetails")) {
				Detalle detalleResultado = daoDetalle.consultarDetalle(Integer
						.parseInt(d.getCodigo() + ""));
				panelResult.getPanelDetails().getComboBoxType()
						.setSelectedItem(detalleResultado.getTipo());
				panelResult.getPanelDetails().getFieldName()
						.setText(detalleResultado.getNombre());
				panelResult.getPanelDetails().getFieldDescripcion()
						.setText(detalleResultado.getDescripcion());
				panelResult.getPanelDetails().getComboBoxUbication()
						.setSelectedItem(detalleResultado.getNombreUbicacion());
				panelResult.getPanelDetails().setDetailLoad(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelDetails().desbloquearEnUso();
				panelResult.getPanelDetails().desbloquearEdicion();
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listResultContact")) {
				panelResult.getPanelContacts().setCodPersonLoad(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelContacts().setCodContactLoad(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelContacts().cargarContactoNuevo();
				panelResult.getPanelContacts().setEdicion(false);
				panelResult.getPanelContacts().contactoSeleccionado();
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listContact")) {
				panelResult.getPanelContacts().setCodContactLoad(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelContacts().setCodPersonLoad(
						panelResult.getPanelInfoPersona().getPersonLoad());
				panelResult.getPanelContacts().cargarContactoEdicion();
				panelResult.getPanelContacts().desbloquearEdicion();
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listOrders")) {
				panelResult.getPanelOrders().setCodOrdenSeleccionada(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelNewOrder().setCodOrdenEdicion(
						Integer.parseInt(d.getCodigo() + ""));
				panelResult.getPanelOrders().ordenSeleccionada();

			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listItems")) {
				panelResult.getPanelNewOrder().setCodElemSel(
						Integer.parseInt(d.getCodigo() + ""));
				System.out.println("ELEMENTO " + theList.getSelectedIndex());
				panelResult.getPanelNewOrder().cargarElementoSeleccionado();
				panelResult.getPanelNewOrder().setIndexEdicion(
						theList.getSelectedIndex());
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listMail")) {
				panelBuzon.setCodEventSelected(d.getCodigo());
				panelBuzon.cargarEvento();
				panelBuzon.cargarActividades();
				panelBuzon.getPanelEvent().desbloquearBotones();
				panelBuzon.getPanelActivity().desbloquearBotones();
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listActivities")) {
				panelBuzon.cargarInfoActividad(d.getCodigo());

			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listResultProduct")) {
				panelResultProduct.getPanelInfoProduct().setProductLoad(
						Integer.parseInt(d.getCodigo() + ""));
				Producto productResultado = daoProduct.buscarPorCodigo(d
						.getCodigo());
				panelResultProduct.getPanelInfoProduct().cargarProducto(
						productResultado);
				panelResultProduct.getPanelImagenProducto().setIdProductoLoad(
						d.getCodigo());
				panelResultProduct.getPanelImagenProducto()
						.desbloquearElementos();
				panelResultProduct.getPanelImagenProducto().cargarImagenes();
				String mostrar = daoVarios.consultarVariosPorCategoriaNivel2(
						"Tipo de Elemento",
						productResultado.getIdCategoriaProducto())
						+ " "
						+ daoVarios.consultarVariosPorCategoriaNivel2(
								"Marca Elemento",
								productResultado.getIdMarcaProducto())
						+ " "
						+ productResultado.getDescripcionProducto();
				panelResultProduct.setInfoProduct(mostrar);
				panelResultProduct.actualizarInfoProduct();
			} else if (d.getCodigo() != 0
					&& theList.getName().equals("listResultCliente")) {
				panelFacturacion.setIdClienteCargado(d.getCodigo());
				panelFacturacion.cargarCliente();
			}

		}
	}

	public int getCodLog() {
		return codLog;
	}

	public void setCodLog(int codLog) {
		this.codLog = codLog;
	}

	public void itemStateChanged(ItemEvent e) {
		if (panelResult != null && e.getStateChange() == 1) {
			if (e != null && ((JComboBox) e.getSource()).getName() != null) {
				if (((JComboBox) e.getSource()).getName().equals(
						"comboBoxTypePersona")) {
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
					panelResult.getPanelInfoPersona().mostrarInfoTipoPersona();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboTypeContact")) {
					panelResult.getPanelContacts().crearNuevoTipoContacto();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboBoxType")) {
					panelResult.getPanelDetails().ocultarPorSeleccion();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboBoxTypeDocument")) {
					panelResult.getPanelInfoPersona().intercambioDocumentos();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboBrandNewOrder")) {
					panelResult.getPanelNewOrder().crearNuevoVario();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboTypeNewOrder")) {
					panelResult.getPanelNewOrder().crearNuevoVario();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				} else if (((JComboBox) e.getSource()).getName().equals(
						"comboStateNewOrder")) {
					panelResult.getPanelNewOrder().crearNuevoVario();
					// panelResult.getPanelContacts().crearNuevoTipoContacto();
				}

			}
		}

	}

	public void buscarPersona() {
		if (!panelSearch.getFieldSearch().getText().equals("")) {
			panelSearch.cargarResultados();
			panelResult.getPanelDetails().bloquearElementos();
			panelResult.getPanelInfoPersona().bloquearElementos();
		}
	}

	public PanelFacturacion getPanelFacturacion() {
		return panelFacturacion;
	}

	public void setPanelFacturacion(PanelFacturacion panelFacturacion) {
		this.panelFacturacion = panelFacturacion;
	}

	public PanelInventario getPanelInventario() {
		return panelInventario;
	}

	public void setPanelInventario(PanelInventario panelInventario) {
		this.panelInventario = panelInventario;
	}

}