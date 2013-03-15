package interfazFacturacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Codigo.Persona;
import DAO.DAOvarios;

public class FrameImpresion extends JFrame {

	private static final long serialVersionUID = 1L;
	PanelImpresion panel;

	public FrameImpresion(ArrayList<ItemFacturaTabla> listaItems,
			Persona cliente, String direccion, String telefono,
			String observaciones, Date fecha, Date fechaVence, String subtotal,
			String subtotalProductosIva, String iva, String total,
			String aproximacionPeso) {

		setSize(900, 2000);
		setLayout(null);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);

		JScrollPane scroll = new JScrollPane();
		panel = new PanelImpresion(listaItems, cliente,direccion,telefono, observaciones, fecha,
				fechaVence, subtotal, subtotalProductosIva, iva, total,
				aproximacionPeso);
		scroll.setBounds(0, 0, 900, 500);
		// scroll.add(panel);
		scroll.setViewportView(panel);
		add(scroll);

	}

	public void imprimir() {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			PageFormat pf = new PageFormat();
			Paper p = new Paper();
			System.out.println("ANTES " + p.getWidth() + " " + p.getHeight());
			p.setSize(1000, 1500);
			System.out.println("DESPUES " + p.getWidth() + " " + p.getHeight());
			p.setImageableArea(0, 0, 700, 1500);
			pf.setPaper(p);
			job.setPrintable(panel, pf);

			boolean aceptar_impresion = job.printDialog();
			if (aceptar_impresion)
				job.print();

		} catch (PrinterException ex) {
			Logger.getLogger(PanelFacturacion.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

}
