package Interfaz;

import interfazFacturacion.PanelFacturacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Codigo.ItemFactura;
import Codigo.Persona;
import DAO.DAOvarios;

public class FrameImpresion extends JFrame implements Printable {

	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial", Font.PLAIN, 12);

	public FrameImpresion(ArrayList<ItemFactura> listaItems, Persona cliente,
			String observaciones, Date fecha, Date fechaVence, String subtotal,
			String iva, String total) {

		setSize(900, 800);
		setLayout(null);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);

		crearInfoCliente(cliente);
		crearInfoFactura(fecha, fechaVence);
		crearPanelProductos(listaItems);
		crearPanelObservaciones(observaciones);
		crearPanelValorFactura(subtotal, iva, total);
		crearPanelPrecioTexto();

	}

	public void crearInfoFactura(Date fecha, Date fechaVence) {
		JPanel panelInfoFactura = new JPanel();
		panelInfoFactura.setBackground(Color.white);
		panelInfoFactura.setBounds(600, 40, 300, 50);
		panelInfoFactura.setLayout(null);
		JLabel fechaFactura = new JLabel(fecha.getDay() + "       "
				+ fecha.getMonth() + "       " + (fecha.getYear() + 1900));
		fechaFactura.setBounds(5, 5, 100, 35);
		panelInfoFactura.add(fechaFactura);

		JLabel fechaVencimiento = new JLabel( fechaVence.getDay()+ "       "
				+ fechaVence.getMonth() + "       " + (fechaVence.getYear() + 1900));
		fechaVencimiento.setBounds(130, 5, 100, 35);
		panelInfoFactura.add(fechaVencimiento);

		add(panelInfoFactura);
	}

	public void crearInfoCliente(Persona cliente) {

		JPanel panelInfoCliente = new JPanel();
		panelInfoCliente.setLayout(null);
		panelInfoCliente.setBounds(10, 70, 700, 100);
		panelInfoCliente.setBackground(Color.white);
		JLabel labelNombreCliente = new JLabel(cliente.getNombre() + "  "
				+ cliente.getApellido());
		labelNombreCliente.setBounds(90, 0, 400, 50);
		panelInfoCliente.add(labelNombreCliente);

		JLabel labelNitCliente = new JLabel(new DAOvarios().darFormatoNumeros(cliente.getNumeroDocumento() + ""));
		labelNitCliente.setBounds(630, 0, 200, 50);
		panelInfoCliente.add(labelNitCliente);

		JLabel labelDireccionCliente = new JLabel("");
		labelDireccionCliente.setBounds(30, 40, 400, 50);
		panelInfoCliente.add(labelDireccionCliente);

		JLabel labelTelefonoCliente = new JLabel("");
		labelTelefonoCliente.setBounds(520, 40, 100, 50);
		panelInfoCliente.add(labelTelefonoCliente);

		JLabel labelCiudadCliente = new JLabel("");
		labelCiudadCliente.setBounds(640, 40, 100, 50);
		panelInfoCliente.add(labelCiudadCliente);

		add(panelInfoCliente);

		// JButton buttonSearchCliente = new JButton("IMPRIMIR");
		// buttonSearchCliente.setBounds(50, 150, 160, 30);
		// buttonSearchCliente.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// imprimir();
		// }
		// });
		// add(buttonSearchCliente);
	}

	// capacidad maxima 12 renglones

	public void crearPanelProductos(ArrayList<ItemFactura> listaItems) {
		JPanel panelProductos = new JPanel();
		panelProductos.setBounds(30, 131, 780, 200);
		panelProductos.setLayout(null);
		add(panelProductos);
		panelProductos.setBackground(Color.white);

//		TitledBorder rotuloInfoFactura = BorderFactory.createTitledBorder("");
//		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
//		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
//		panelProductos.setBorder(rotuloInfoFactura);

		int posYRenglon = -1;
		int tamanoRenglon = 19;
		for (int i = 0; i < listaItems.size(); i++) {
			ItemFactura itemTempo = listaItems.get(i);
			String descripcion = itemTempo.getProducto()
					.getDescripcionProducto();
			if (itemTempo.getProducto().getTieneSerial() == 1) {
				descripcion += " N.Serie "
						+ itemTempo.getArticulo().getNumeroSerie();
			}
//			 descripcion="5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555";
			int renglones = calcularRenglon(descripcion);
			int posiconPartirDescripcion=descripcion.length()/renglones;
			for (int j = 0; j < renglones; j++) {
				posYRenglon++;				
				JLabel labelDescripcion = new JLabel(descripcion.substring(j*posiconPartirDescripcion,(j+1)*posiconPartirDescripcion));
				labelDescripcion.setFont(font);
				labelDescripcion.setBounds(5, tamanoRenglon * posYRenglon, 550, 25);
				panelProductos.add(labelDescripcion);
			}

			JLabel labelCantidad = new JLabel(itemTempo.getCantidad() + "");
			labelCantidad.setBounds(550, tamanoRenglon * posYRenglon, 50, 25);
			panelProductos.add(labelCantidad);

			JLabel labelValorUnitario = new JLabel(itemTempo.getPrecio() + "");
			labelValorUnitario.setBounds(600, tamanoRenglon * posYRenglon, 80, 25);
			panelProductos.add(labelValorUnitario);

			JLabel labelValorTotal = new JLabel(itemTempo.getSubTotal() + "");
			labelValorTotal.setBounds(700, tamanoRenglon * posYRenglon, 100, 25);
			panelProductos.add(labelValorTotal);
		}

	}

	public int calcularRenglon(String texto) {
		@SuppressWarnings("serial")
		FontMetrics metrics = new FontMetrics(font) {
		};
		Rectangle2D bounds = metrics.getStringBounds(texto, null);
		int widthInPixels = (int) bounds.getWidth();
		int renglones = widthInPixels / 450;
		return renglones + 1;
	}
	
	

	// public static void main(String[] args) {
	// String texto = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	// Font font = new Font("Arial", Font.PLAIN, 12);
	// FontMetrics metrics = new FontMetrics(font){};
	// Rectangle2D bounds = metrics.getStringBounds(texto, null);
	// int widthInPixels = (int) bounds.getWidth();
	// int renglones1 = widthInPixels/450;
	// System.out.println(widthInPixels);
	//
	// }

	public void crearPanelObservaciones(String observaciones) {
		JPanel panelObservaciones = new JPanel();
		panelObservaciones.setBounds(10, 400, 580, 50);
		panelObservaciones.setLayout(null);
		add(panelObservaciones);
		panelObservaciones.setBackground(Color.white);

//		TitledBorder rotuloInfoFactura = BorderFactory.createTitledBorder("");
//		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
//		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
//		panelObservaciones.setBorder(rotuloInfoFactura);

		JLabel labelObservaciones = new JLabel(observaciones);
		labelObservaciones.setBounds(100, 0, 580, 25);
		panelObservaciones.add(labelObservaciones);
	}

	public void crearPanelPrecioTexto() {
		JPanel panelPrecioTexto = new JPanel();
		panelPrecioTexto.setBounds(10, 450, 580, 50);
		panelPrecioTexto.setLayout(null);
		add(panelPrecioTexto);
		panelPrecioTexto.setBackground(Color.white);

//		TitledBorder rotuloInfoFactura = BorderFactory.createTitledBorder("");
//		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
//		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
//		panelPrecioTexto.setBorder(rotuloInfoFactura);

		JLabel labelObservaciones = new JLabel(
				"Un millon docientos cincuenta mil pessos m/cte");
		labelObservaciones.setBounds(50, 0, 580, 25);
		panelPrecioTexto.add(labelObservaciones);
	}

	public void crearPanelValorFactura(String subtotal, String iva, String total) {
		JPanel panelValorFactura = new JPanel();
		panelValorFactura.setBounds(600, 400, 190, 100);
		panelValorFactura.setLayout(null);
		add(panelValorFactura);
		panelValorFactura.setBackground(Color.white);

//		TitledBorder rotuloInfoFactura = BorderFactory.createTitledBorder("");
//		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
//		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
//		panelValorFactura.setBorder(rotuloInfoFactura);

		JLabel labelSubTotal = new JLabel(subtotal);
		labelSubTotal.setBounds(100, 0, 200, 25);
		panelValorFactura.add(labelSubTotal);

		JLabel labelIVA = new JLabel(iva);
		labelIVA.setBounds(100, 35, 200, 25);
		panelValorFactura.add(labelIVA);

		JLabel labelTotal = new JLabel(total);
		labelTotal.setBounds(100, 52, 200, 25);
		panelValorFactura.add(labelTotal);

	}

	// public static void main(String[] args) {
	// FrameImpresion frame = new FrameImpresion();
	// frame.setVisible(true);
	// }

	public void imprimir() {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);

			boolean aceptar_impresion = job.printDialog();
			if (aceptar_impresion)
				job.print();

		} catch (PrinterException ex) {
			Logger.getLogger(PanelFacturacion.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0)
			return NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		// -------------------------ESCALAR LA
		// IMPRESION-------------------------------//
		g2d.scale(0.7f, 0.7f);
		//
		// this.printAll(graphics);
		this.print(graphics);

		return PAGE_EXISTS;
	}
}
