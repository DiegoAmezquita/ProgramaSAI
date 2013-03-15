package interfazFacturacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Codigo.Articulo;
import Codigo.Datos;
import Codigo.Producto;
import Interfaz.DatosCellRenderer;

public class PanelSeleccionArticulo extends JFrame {

	private JLabel labelNumeroSerie;
	private JTextField campoNumeroSerie;
	private JScrollPane scrollNumerosCargados;
	private JList listaNumerosSerie;
	private DefaultListModel modeloListaNumerosSerie;
	private PanelFacturacion panelFacturacion;
	private JButton botonAgregar;
	private JButton botonSeleccionar;
	private JButton botonCancelar;
	private int maximoNumero;
	private Producto producto;

	public PanelSeleccionArticulo(PanelFacturacion panelFactura,
			Producto productoTempo) {
		this.panelFacturacion = panelFactura;
		this.producto = productoTempo;
		setLayout(null);
		setSize(500, 500);
		setLocationRelativeTo(null);

		JLabel labelTitulo = new JLabel("Seleccion Articulo", JLabel.CENTER);
		labelTitulo.setBounds(20, 20, 460, 25);
		labelTitulo.setFont(new Font("Arial", 1, 15));
		add(labelTitulo);

		modeloListaNumerosSerie = new DefaultListModel();
		listaNumerosSerie = new JList(modeloListaNumerosSerie);
		listaNumerosSerie.setCellRenderer(new DatosCellRenderer());
		scrollNumerosCargados = new JScrollPane(listaNumerosSerie);
		scrollNumerosCargados.setBounds(20, 90, 450, 300);
		add(scrollNumerosCargados);

		botonSeleccionar = new JButton("SELECCIONAR");
		botonSeleccionar.setBounds(100, 400, 120, 30);
		botonSeleccionar.setActionCommand("SELECCIONARNUMERODESERIE");
		botonSeleccionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelFacturacion.agregarProductoFacturaNumeroSerie(producto);
			}
		});
		add(botonSeleccionar);

		botonCancelar = new JButton("CANCELAR");
		botonCancelar.setBounds(270, 400, 120, 30);
		botonCancelar.setActionCommand("CANCELARSELECCIONNUMERODESERIE");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelFacturacion.ocultarSeleccionNumeroSerie();

			}
		});
		add(botonCancelar);

	}

	public void cargarNumerosSerie(ArrayList<Articulo> numerosSerie) {
		for (int i = 0; i < numerosSerie.size(); i++) {
			modeloListaNumerosSerie.addElement(new Datos(numerosSerie.get(i)
					.getIdArticulo(), numerosSerie.get(i).getNumeroSerie()));
		}
		modeloListaNumerosSerie.addElement(new Datos(0, "NUEVO"));
	}

	public int numeroSerieSeleccionado() {
		if (listaNumerosSerie.getSelectedIndex() != -1) {
			int tempo = ((Datos) modeloListaNumerosSerie.get(listaNumerosSerie
					.getSelectedIndex())).getCodigo();
			System.out.println("NUMERO DE SERIE SELECCIONADO PARA AGREGAR A LA FACTURA "+tempo);
			return tempo;
		}
		return 0;
	}

	public String devolverNumeroSerieSeleccionado() {
		String tempo = ((Datos) modeloListaNumerosSerie.get(listaNumerosSerie
				.getSelectedIndex())).getNombre();
		return tempo;
	}

	public void agregarNumeroSerie() {
		if (!campoNumeroSerie.getText().equals("")) {
			modeloListaNumerosSerie.addElement(campoNumeroSerie.getText());
		} else {
			JOptionPane.showMessageDialog(this,
					"El Numero de serie no puede estar vacio");
		}
		devolverNumerosSerie();
		campoNumeroSerie.setText("");
	}

	public ArrayList<String> devolverNumerosSerie() {
		ArrayList<String> temporal = new ArrayList<String>();
		for (int i = 0; i < modeloListaNumerosSerie.getSize(); i++) {
			temporal.add(modeloListaNumerosSerie.getElementAt(i) + "");
		}
		for (int i = 0; i < temporal.size(); i++) {
			System.out.println(temporal.get(i));
		}
		return temporal;
	}

}
