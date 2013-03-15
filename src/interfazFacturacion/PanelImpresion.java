package interfazFacturacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import Codigo.Persona;
import DAO.DAOvarios;

public class PanelImpresion extends JPanel implements Printable {

	Font font = new Font("Arial", Font.PLAIN, 13);

	DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
	DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
	char sep = symbols.getDecimalSeparator();
	char mill = symbols.getGroupingSeparator();
	String symbol = symbols.getCurrencySymbol();

	DecimalFormat df = new DecimalFormat("#" + sep + "###" + sep + "###"
			+ sep + "###" + mill + "##");

	int margenIzquierdo = 0;
	int margenSuperior = -10;

	public PanelImpresion(ArrayList<ItemFacturaTabla> listaItems,
			Persona cliente, String direccion, String telefono,
			String observaciones, Date fecha, Date fechaVence, String subtotal,
			String subtotalProductosIva, String iva, String total,
			String aproximacionPeso) {

		setLayout(null);
		setSize(900, 2000);
		setBackground(Color.WHITE);
		crearInfoFactura(0, fecha, fechaVence);
		crearInfoCliente(0, cliente, direccion, telefono);
		crearPanelProductos(0, listaItems, aproximacionPeso);
		crearPanelObservaciones(0, observaciones);
		crearPanelValorFactura(0, subtotal, subtotalProductosIva, iva, total);
		crearPanelPrecioTexto(0, total);

		crearInfoFactura(1, fecha, fechaVence);
		crearInfoCliente(1, cliente, direccion, telefono);
		crearPanelProductos(1, listaItems, aproximacionPeso);
		crearPanelObservaciones(1, observaciones);
		crearPanelValorFactura(1, subtotal, subtotalProductosIva, iva, total);
		crearPanelPrecioTexto(1, total);
	}

	public void crearInfoFactura(int factura, Date fecha, Date fechaVence) {
		JPanel panelInfoFactura = new JPanel();
		panelInfoFactura.setBackground(Color.white);
		panelInfoFactura.setBounds(595 + margenIzquierdo, 50 + margenSuperior,
				300, 35);
		if (factura != 0) {
			panelInfoFactura.setBounds(595 + margenIzquierdo,
					645 + 50 + margenSuperior, 300, 35);
		}
		panelInfoFactura.setLayout(null);
		String anio = (fecha.getYear() + 1900) + "";
		anio = anio.substring(2, 4);

		String fechaEscribir = fecha.getDate() + "        "
				+ (fecha.getMonth() + 1) + "       " + anio;
		JLabel fechaFactura = new JLabel(fechaEscribir);
		fechaFactura.setFont(font);
		fechaFactura.setBounds(0, 10, 120, 35);
		panelInfoFactura.add(fechaFactura);

		String anio1 = (fechaVence.getYear() + 1900) + "";
		anio1 = anio1.substring(2, 4);

		JLabel fechaVencimiento = new JLabel(fechaVence.getDate() + "        "
				+ (fechaVence.getMonth() + 1) + "       " + anio1);
		fechaVencimiento.setBounds(120, 10, 120, 35);
		fechaVencimiento.setFont(font);
		panelInfoFactura.add(fechaVencimiento);

		add(panelInfoFactura);
	}

	public void crearInfoCliente(int factura, Persona cliente,
			String direccion, String telefono) {

		JPanel panelInfoCliente = new JPanel();
		panelInfoCliente.setLayout(null);
		panelInfoCliente.setBounds(10 + margenIzquierdo, 82 + margenSuperior,
				800, 55);
		if (factura != 0) {
			panelInfoCliente.setBounds(10 + margenIzquierdo,
					646 + 82 + margenSuperior, 800, 55);
		}
		panelInfoCliente.setBackground(Color.white);

//		TitledBorder rotuloInfoFactura = BorderFactory.createTitledBorder("");
//		rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
//		rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
//		panelInfoCliente.setBorder(rotuloInfoFactura);

		JLabel labelNombreCliente = new JLabel(cliente.getNombre() + "  "
				+ cliente.getApellido());
		labelNombreCliente.setBounds(90, 0, 400, 30);
		labelNombreCliente.setFont(font);
		labelNombreCliente.setAlignmentY(CENTER_ALIGNMENT);
		panelInfoCliente.add(labelNombreCliente);

		JLabel labelNitCliente = new JLabel(
				new DAOvarios().darFormatoNumeros(cliente.getNumeroDocumento()
						+ ""));
		labelNitCliente.setBounds(630, 0, 200, 30);
		labelNitCliente.setFont(font);
		labelNitCliente.setAlignmentY(CENTER_ALIGNMENT);
		panelInfoCliente.add(labelNitCliente);

		String ciudad = "";

		System.out.println("DIRECCION " + direccion);
		System.out.println("TELEFONO " + telefono);
		System.out.println("CIUDAD  " + ciudad);

		if (direccion != null && direccion.length() > 5) {
			ciudad = direccion.substring(direccion.lastIndexOf("Ciudad:") + 8);
			direccion = direccion
					.substring(0, direccion.lastIndexOf("Ciudad:"));
		}

		JLabel labelDireccionCliente = new JLabel(direccion);
		labelDireccionCliente.setBounds(90, 15, 400, 50);
		panelInfoCliente.add(labelDireccionCliente);

		JLabel labelTelefonoCliente = new JLabel(telefono);
		labelTelefonoCliente.setBounds(520, 17, 100, 50);
		panelInfoCliente.add(labelTelefonoCliente);

		JLabel labelCiudadCliente = new JLabel(ciudad);
		labelCiudadCliente.setBounds(700, 17, 100, 50);
		panelInfoCliente.add(labelCiudadCliente);

		add(panelInfoCliente);

		add(panelInfoCliente);

	}

	// capacidad maxima 12 renglones

	public void crearPanelProductos(int factura,
			ArrayList<ItemFacturaTabla> listaItems, String aproximacionPeso) {
		JPanel panelProductos = new JPanel();
		panelProductos.setBounds(30 + margenIzquierdo, 141 + margenSuperior,
				800, 265);
		if (factura != 0) {
			panelProductos.setBounds(30 + margenIzquierdo,
					645 + 141 + margenSuperior, 800, 265);
		}
		panelProductos.setLayout(null);
		add(panelProductos);
		panelProductos.setBackground(Color.white);

		int posYRenglon = 0;
		int tamanoRenglon = 19;
		for (int i = 0; i < listaItems.size(); i++) {
			ItemFacturaTabla itemTempo = listaItems.get(i);
			String descripcion = itemTempo.getProducto().getDescripcionProducto();
			if (itemTempo.getProducto().getTieneSerial() == 1) {
				descripcion += " S/N: ";
				for (int j = 0; j < itemTempo.getArticulos().size(); j++) {
					descripcion += itemTempo.getArticulos().get(j)
							.getNumeroSerie()
							+ ", ";

				}
			}
			int renglones = calcularRenglon(descripcion);
			int posiconPartirDescripcion = descripcion.length() / renglones;

			for (int j = 0; j < renglones; j++) {
				posYRenglon++;
				JLabel labelDescripcion = new JLabel(descripcion.substring(j
						* posiconPartirDescripcion, (j + 1)
						* posiconPartirDescripcion));
				labelDescripcion.setFont(font);
				int posY = tamanoRenglon * posYRenglon;
				labelDescripcion.setBounds(5, posY, 530, 25);
				panelProductos.add(labelDescripcion);
			}

			JLabel labelCantidad = new JLabel(itemTempo.getCantidad() + "",
					JLabel.RIGHT);
			labelCantidad.setBounds(520, tamanoRenglon * posYRenglon, 30, 25);
			labelCantidad.setFont(font);
			panelProductos.add(labelCantidad);

			JLabel labelValorUnitario = new JLabel(symbol
					+ df.format(itemTempo.getValorUnitarioSinIva()) + " ",
					JLabel.RIGHT);
			labelValorUnitario.setBounds(558, tamanoRenglon * posYRenglon, 105,
					25);
			labelValorUnitario.setFont(font);
			panelProductos.add(labelValorUnitario);

			JLabel labelValorTotal = new JLabel(symbol
					+ df.format(itemTempo.getSubtotalSinIva()) + " ",
					JLabel.RIGHT);
			labelValorTotal
					.setBounds(675, tamanoRenglon * posYRenglon, 118, 25);
			labelValorTotal.setFont(font);
			panelProductos.add(labelValorTotal);
		}
		posYRenglon++;

		JLabel labelDescripcionAproximacionPeso = new JLabel(
				"APROXIMACION AL PESO");
		labelDescripcionAproximacionPeso.setFont(font);
		labelDescripcionAproximacionPeso.setBounds(5, tamanoRenglon
				* posYRenglon, 530, 25);
		panelProductos.add(labelDescripcionAproximacionPeso);

		JLabel labelAproximacionPeso = new JLabel(symbol + aproximacionPeso
				+ " ", JLabel.RIGHT);
		labelAproximacionPeso.setBounds(675, tamanoRenglon * posYRenglon, 118,
				25);
		labelAproximacionPeso.setFont(font);
		panelProductos.add(labelAproximacionPeso);

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

	public void crearPanelObservaciones(int factura, String observaciones) {
		JPanel panelObservaciones = new JPanel();
		panelObservaciones.setBounds(27 + margenIzquierdo, 398, 580, 25);
		if (factura != 0) {
			panelObservaciones.setBounds(27 + margenIzquierdo,
					655 + 398 + margenSuperior, 580, 25);
		}
		panelObservaciones.setLayout(null);
		add(panelObservaciones);
		panelObservaciones.setBackground(Color.white);

		JLabel labelObservaciones = new JLabel(observaciones);
		labelObservaciones.setBounds(100, 0, 580, 25);
		labelObservaciones.setFont(font);
		panelObservaciones.add(labelObservaciones);
	}

	public void crearPanelPrecioTexto(int factura, String total) {
		JPanel panelPrecioTexto = new JPanel();
		panelPrecioTexto.setBounds(10 + margenIzquierdo, 435, 580, 25);
		if (factura != 0) {
			panelPrecioTexto.setBounds(10 + margenIzquierdo,
					655 + 435 + margenSuperior, 580, 25);
		}
		panelPrecioTexto.setLayout(null);
		add(panelPrecioTexto);
		panelPrecioTexto.setBackground(Color.white);

		String valorLetras = Numero_a_Letra.Convertir(total, true)+" m/cte";
				
		// valorLetras = "PRUEBA MENSAJE NO SE PEGA";
		System.out.println(valorLetras);
		JLabel labelObservaciones = new JLabel(valorLetras);
		labelObservaciones.setBounds(50, 0, 600, 25);
		labelObservaciones.setFont(font);
		panelPrecioTexto.add(labelObservaciones);
	}

	public void crearPanelValorFactura(int factura, String subtotal,
			String subtotalProductosIva, String iva, String total) {
		JPanel panelValorFactura = new JPanel();
		panelValorFactura.setBounds(590 + margenIzquierdo,
				406 + margenSuperior, 240, 100);
		if (factura != 0) {
			panelValorFactura.setBounds(590 + margenIzquierdo,
					645 + 406 + margenSuperior, 240, 100);
		}
		panelValorFactura.setLayout(null);
		add(panelValorFactura);
		panelValorFactura.setBackground(Color.white);

		// TitledBorder rotuloInfoFactura =
		// BorderFactory.createTitledBorder("");
		// rotuloInfoFactura.setTitleFont(new Font("Arial", 1, 17));
		// rotuloInfoFactura.setTitleColor(new Color(0, 0, 128));
		// panelValorFactura.setBorder(rotuloInfoFactura);

		JLabel labelSubTotal = new JLabel(symbol
				+ DAOvarios.darFormatoNumeros(subtotal) + " ", JLabel.RIGHT);
		labelSubTotal.setBounds(100, 3, 135, 25);
		labelSubTotal.setFont(font);
		panelValorFactura.add(labelSubTotal);

		if (!subtotalProductosIva.equals(subtotal)) {

			JLabel labelBase = new JLabel("BASE", JLabel.LEFT);
			labelBase.setBounds(25, 21, 70, 25);
			labelBase.setFont(font);
			panelValorFactura.add(labelBase);

			JLabel labelSubtotalProductosConIva = new JLabel(symbol
					+ DAOvarios.darFormatoNumeros(subtotalProductosIva) + " ",
					JLabel.RIGHT);
			labelSubtotalProductosConIva.setBounds(100, 21, 135, 25);
			labelSubtotalProductosConIva.setFont(font);
			panelValorFactura.add(labelSubtotalProductosConIva);
		}
		
		
		JLabel labelPorcentaje = new JLabel("16");
		labelPorcentaje.setBounds(55, 39, 50, 25);
		panelValorFactura.add(labelPorcentaje);

		JLabel labelIVA = new JLabel(symbol + DAOvarios.darFormatoNumeros(iva)
				+ " ", JLabel.RIGHT);
		labelIVA.setBounds(100, 41, 135, 25);
		labelIVA.setFont(font);
		panelValorFactura.add(labelIVA);

		JLabel labelTotal = new JLabel(symbol
				+ DAOvarios.darFormatoNumeros(total) + " ", JLabel.RIGHT);
		labelTotal.setBounds(100, 59, 135, 25);
		labelTotal.setFont(font);
		panelValorFactura.add(labelTotal);

	}

	// public static void main(String[] args) {
	// FrameImpresion frame = new FrameImpresion();
	// frame.setVisible(true);
	// }

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
