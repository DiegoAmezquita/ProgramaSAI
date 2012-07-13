package interfazInventario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

public class PanelNumeroSerie extends JDialog implements ActionListener {

	private JLabel labelNumeroSerie;
	private JTextField campoNumeroSerie;
	private JScrollPane scrollNumerosCargados;
	private JList listaNumerosSerie;
	private DefaultListModel modeloListaNumerosSerie;
	private JButton botonAgregar;
	private JButton botonGuardar;
	private JButton botonCancelar;
	private int maximoNumero;
	private boolean edicion = false;
	private boolean estadoEdicion = false;

	public PanelNumeroSerie(final PanelInventario panelInventario) {
		super(panelInventario.getFrameMain(),true);
		setLayout(null);
		setSize(500, 500);
		setLocationRelativeTo(null);

		JLabel labelTitulo = new JLabel("ARTICULOS PARA REGISTRAR",
				JLabel.CENTER);
		labelTitulo.setBounds(20, 20, 460, 25);
		labelTitulo.setFont(new Font("Arial", 1, 15));
		add(labelTitulo);

		labelNumeroSerie = new JLabel("Numero de Serie:");
		labelNumeroSerie.setBounds(20, 50, 200, 25);
		add(labelNumeroSerie);

		campoNumeroSerie = new JTextField();
		campoNumeroSerie.setBounds(150, 50, 210, 25);
		add(campoNumeroSerie);

		botonAgregar = new JButton("AGREGAR");
		botonAgregar.setBounds(370, 50, 100, 25);
		botonAgregar.setActionCommand("AGREGAR");
		botonAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarNumeroSerie();

			}
		});
		add(botonAgregar);

		modeloListaNumerosSerie = new DefaultListModel();
		listaNumerosSerie = new JList(modeloListaNumerosSerie);
		// listaNumerosSerie.setCellRenderer(new DatosCellRenderer());
		scrollNumerosCargados = new JScrollPane(listaNumerosSerie);
		scrollNumerosCargados.setBounds(20, 90, 450, 300);
		listaNumerosSerie.setName("listResultProveedor");
		listaNumerosSerie.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				editarNumeroSerie();
			}
		});
		add(scrollNumerosCargados);

		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(120, 400, 100, 30);
		botonGuardar.setActionCommand("GUARDARINVENTARIONUMERODESERIE");
		botonGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(estadoEdicion){
					panelInventario.actualizarNumerosSerie();
				}
				
				panelInventario.agregarConNumeroSerie(devolverNumerosSerie());
				setVisible(false);

			}
		});
		add(botonGuardar);

		botonCancelar = new JButton("CANCELAR");
		botonCancelar.setBounds(270, 400, 100, 30);
		botonCancelar.setActionCommand("CANCELARNUMERODESERIE");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		add(botonCancelar);

	}

	public void editarNumeroSerie() {
		if (listaNumerosSerie.getSelectedIndex() != -1) {
			System.out.println("NUMERO SERIE SELECCIONADO "
					+ listaNumerosSerie.getSelectedValue() + "");
			campoNumeroSerie.setText(listaNumerosSerie.getSelectedValue() + "");

			edicion = true;
		}
	}

	public void agregarNumeroSerie() {
		if (!campoNumeroSerie.getText().equals("")) {
			if (edicion) {
				int posicion = listaNumerosSerie.getSelectedIndex();
				modeloListaNumerosSerie.remove(posicion);
				modeloListaNumerosSerie.add(posicion,
						campoNumeroSerie.getText());
				edicion = false;
			} else if (!edicion) {
				modeloListaNumerosSerie.addElement(campoNumeroSerie.getText());
			} else {
				JOptionPane.showMessageDialog(this,
						"El Numero de serie no puede estar vacio");
			}
			campoNumeroSerie.setText("");
		}
	}

	public ArrayList<String> devolverNumerosSerie() {
		ArrayList<String> temporal = new ArrayList<String>();
		for (int i = 0; i < modeloListaNumerosSerie.getSize(); i++) {
			temporal.add(modeloListaNumerosSerie.getElementAt(i) + "");
		}
		System.out.println("ELEMENTOS QUE SE GUARDAN");
		for (int i = 0; i < temporal.size(); i++) {
			System.out.println(temporal.get(i));
		}
		return temporal;
	}
	
	public void cargarNumerosSerie(ArrayList<String> numerosSerie){
		for (int i = 0; i < numerosSerie.size(); i++) {
			modeloListaNumerosSerie.addElement(numerosSerie.get(i));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("AGREGAR")) {
			agregarNumeroSerie();
		}

	}

	public boolean isEstadoEdicion() {
		return estadoEdicion;
	}

	public void setEstadoEdicion(boolean estadoEdicion) {
		this.estadoEdicion = estadoEdicion;
	}
	
	

}
