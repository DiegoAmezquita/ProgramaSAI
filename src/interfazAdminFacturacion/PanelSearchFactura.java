package interfazAdminFacturacion;

import Codigo.Datos;
import Codigo.FacturaVenta;
import Codigo.Persona;
import DAO.DAOFacturaVenta;
import DAO.DAOPersona;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelSearchFactura extends JPanel {

	private JTextField fieldSearch;
	private JButton buttonSearch;
	private JList listResult;
	private DefaultListModel modelListResult;
	private JScrollPane scrollPaneResult;
	private TitledBorder rotulo;
	private DAOPersona daoPersona;
	private DAOFacturaVenta daoFacturaVenta;
	private DAOvarios daoVarios;
	private FrameMain frameMain;
	private int idFacturaSeleccionada;

	private JLabel labelCliente;
	private JLabel labelDocumento;
	private JLabel labelFecha;
	private JLabel labelFechaVencimiento;
	private JLabel labelNumeroItems;
	private JLabel labelEstadoFactura;

	public PanelSearchFactura(final FrameMain frameMain) {
		this.frameMain = frameMain;
		setSize(1000, 700);

		daoFacturaVenta = new DAOFacturaVenta();
		daoPersona = new DAOPersona();
		daoVarios = new DAOvarios();

		rotulo = BorderFactory.createTitledBorder("Busqueda Facturas");
		rotulo.setTitleColor(new Color(0, 0, 128));
		setBorder(rotulo);
		setLayout(null);
		fieldSearch = new JTextField();
		fieldSearch.setBounds(20, 20, 500, 30);
		add(fieldSearch);

		buttonSearch = new JButton("BUSCAR");
		buttonSearch.setBounds(530, 20, 100, 30);
		buttonSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				busquedaFactura();

			}
		});

		ActionMap mapaAccion = this.getActionMap();
		InputMap map = this
				.getInputMap(buttonSearch.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke key_Enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		map.put(key_Enter, "buscar");
		mapaAccion.put("buscar", buscarFactura());

		add(buttonSearch);

		labelCliente = new JLabel("Cliente:");
		labelCliente.setBounds(100, 300, 200, 35);
		add(labelCliente);

		labelDocumento = new JLabel("Documento:");
		labelDocumento.setBounds(100, 330, 200, 35);
		add(labelDocumento);

		labelFecha = new JLabel("Fecha Facturacion:");
		labelFecha.setBounds(100, 360, 200, 35);
		add(labelFecha);

		labelFechaVencimiento = new JLabel("Fecha Vencimiento:");
		labelFechaVencimiento.setBounds(100, 390, 200, 35);
		add(labelFechaVencimiento);

		labelNumeroItems = new JLabel("Numero Items:");
		labelNumeroItems.setBounds(100, 420, 200, 35);
		add(labelNumeroItems);

		labelEstadoFactura = new JLabel("Estado:");
		labelEstadoFactura.setBounds(100, 450, 200, 35);
		add(labelEstadoFactura);

		modelListResult = new DefaultListModel();
		listResult = new JList(modelListResult);
		listResult.setCellRenderer(new DatosCellRenderer());
		scrollPaneResult = new JScrollPane(listResult);
		scrollPaneResult.setBounds(20, 60, 960, 200);
		listResult.setName("listResult");
		listResult.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				cargarInfoFactura();
			}
		});
		add(scrollPaneResult);

		JButton botonAnular = new JButton("ANULAR");
		botonAnular.setBounds(100, 500, 150, 35);
		botonAnular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				anularFacturaSeleccionada();

			}
		});
		add(botonAnular);

		JButton botonCopiarAnular = new JButton("COPIAR/ANULAR");
		botonCopiarAnular.setBounds(300, 500, 150, 35);
		botonCopiarAnular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try {
					System.out.println("CODIGO FACTURA "+((Datos) listResult.getSelectedValue()).getCodigo());
					if (daoFacturaVenta
							.consultar(
									((Datos) listResult.getSelectedValue())
											.getCodigo()).getEstado() == 0) {
						frameMain.crearFrameFacturacion(1, ((Datos) listResult
								.getSelectedValue()).getCodigo());
					} else {
						JOptionPane.showMessageDialog(null,
								"LA FACTURA YA ESTA ANULADA");
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"NO HA SELECCIONADO NINGUNA FACTURA");
				}

			}
		});
		add(botonCopiarAnular);

		// JButton botonImprimir = new JButton("IMPRIMIR");
		// botonImprimir.setBounds(500, 300, 150, 35);
		// add(botonImprimir);

	}

	public void cargarInfoFactura() {
		if (listResult.getSelectedIndex() >= 0) {
			FacturaVenta facturaTempo = daoFacturaVenta
					.consultar(((Datos) listResult.getSelectedValue())
							.getCodigo());
			Persona personaTempo = daoPersona.consultar(facturaTempo
					.getIdCliente());

			if (personaTempo == null) {
				JOptionPane.showMessageDialog(null, "PERSONA LLEGA NULL");
			}
			labelCliente.setText("Cliente: " + personaTempo.getNombre() + " "
					+ personaTempo.getApellido());
			labelDocumento.setText("Documento: "
					+ daoVarios.darFormatoNumeros(personaTempo
							.getNumeroDocumento() + ""));
			labelFecha.setText("Fecha Facturacion: "
					+ facturaTempo.getFechaAceptada());
			labelFechaVencimiento.setText("Fecha Vencimiento: "
					+ facturaTempo.getFechaVence());
			labelNumeroItems.setText("Numero Items: "
					+ daoFacturaVenta.consultarNumeroItems(facturaTempo
							.getIdFactura()));

			if (facturaTempo.getEstado() == 1) {
				labelEstadoFactura
						.setText("<html>Estado: <font color='red'>ANULADO</font></html>");
			} else {
				labelEstadoFactura.setText("Estado: Normal");
			}
		}

	}

	public void anularFacturaSeleccionada() {
		try {
			
			if (daoFacturaVenta.consultar(
					((Datos) listResult.getSelectedValue()).getCodigo())
					.getEstado() == 0) {
				int i = JOptionPane.showConfirmDialog(null,
						"ESTA SEGURO DE ANULAR LA FACTURA");
				if (i == 0) {
					daoFacturaVenta.anular(((Datos) listResult
							.getSelectedValue()).getCodigo());
					busquedaFactura();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"LA FACTURA YA ESTA ANULADA");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"NO HA SELECCIONADO NINGUNA FACTURA");
		}
	}

	public void busquedaFactura() {
		cargarResultados();
	}

	public AbstractAction buscarFactura() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				busquedaFactura();

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
		ArrayList<FacturaVenta> listaFacturas = daoFacturaVenta
				.buscarFacturas(getFieldSearch().getText());
		listResult.clearSelection();
		getModelListResult().clear();
		String mostrar = "";
		for (int i = 0; i < listaFacturas.size(); i++) {
			mostrar = listaFacturas.get(i).getPrefijoFactura() + " "
					+ listaFacturas.get(i).getNumeroFactura() + " "
					+ listaFacturas.get(i).getFechaFactura();

			if (listaFacturas.get(i).getEstado() == 1) {
				mostrar += " <b>ANULADA</b>";
			}
			Persona personaTempo = daoPersona.consultar(listaFacturas.get(i)
					.getIdCliente());
			mostrar += " "
					+ personaTempo.getNombre()
					+ " "
					+ personaTempo.getApellido()
					+ " "
					+ daoVarios.darFormatoNumeros(personaTempo
							.getNumeroDocumento() + "");

			StringTokenizer tk = new StringTokenizer(
					getFieldSearch().getText(), " "); // Cambia aquí el
														// separador
			mostrar = "<html>" + mostrar.toUpperCase() + "</html>";
			String nuevo = "";
			String palabra = "";
			while (tk.hasMoreTokens()) {
				palabra = tk.nextToken().toUpperCase();
				nuevo = "<b>" + palabra + "</b>";
				mostrar = mostrar.replaceAll(palabra, nuevo);
			}
			getModelListResult().addElement(
					new Datos(listaFacturas.get(i).getIdFactura(), mostrar));
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
