package interfazFacturacion;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Codigo.ItemFactura;
import Codigo.Producto;
import DAO.DAOArticulo;
import DAO.DAOProducto;

public class ModeloTabla extends DefaultTableModel {

	private ArrayList<ItemFactura> productosFactura;

	PanelFacturacion panelFactura;
	boolean permitir = false;
	DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
	DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
	char sep = symbols.getDecimalSeparator();
	char mill = symbols.getGroupingSeparator();

	public ModeloTabla(Object[] titulos, int numFilas, PanelFacturacion panelFactura) {
		super(titulos, numFilas);
		productosFactura = new ArrayList<ItemFactura>();
		this.panelFactura = panelFactura;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 2 || column == 5)
			return true;
		return false;
	}

	@Override
	public void addRow(Vector rowData) {
		super.addRow(rowData);
	}

	// public void setValueAt(String valor, int arg1, int arg2) {
	// super.setValueAt(valor, arg1, arg2);
	// }

	public void cambiarCantidadPermitido(Object aValue, int row) {
		permitir = true;
		setValueAt(aValue, row, 2);
		permitir = false;
	}

	@Override
	public void setValueAt(Object arg0, int row, int column) {
		System.out.println("arg0 " + arg0);
		System.out.println("row " + row);
		System.out.println("column " + column);
		DecimalFormat df = new DecimalFormat("#" + sep + "###" + sep + "###" + sep + "###" + mill + "##");
		if (column == 5) {
			if (validarEntrada(arg0 + "")) {
				if (arg0.equals("")) {
					arg0 = getValueAt(row, column);
				}
				String newArg0 = arg0 + "";
				System.out.println("STRING " + newArg0);

				newArg0 = newArg0.replaceAll("[.]", "");
				System.out.println("OBJETO " + newArg0);

				super.setValueAt(df.format(Float.parseFloat(newArg0)), row, column);
				panelFactura.cambiarDatoItemFacturaTabla(Float.parseFloat(newArg0), row, column);
			}
		} else if (column == 2) {
			if (validarEntrada(arg0 + "")) {
				try {
					boolean test = panelFactura.cambiarExistenciasTabla(row, Integer.parseInt(arg0 + ""), permitir);
					if (test) {

						super.setValueAt(Integer.parseInt(arg0 + ""), row, column);
						System.out.println("---------------------------**-*/-*/-/-/*/-/");
						for (ItemFacturaTabla itemTempo:panelFactura.listaItemsFactura) {
							System.out.println(itemTempo.descripcion);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "LA CANTIDAD DEBE SER UN NUMERO");
					e.printStackTrace();
				}
			}
		} else {
			super.setValueAt(arg0, row, column);
		}

		// panelFactura.calcularCambiosProducto(row);
		// actualizarDatos();
		// panelFactura.calcularValorFactura();

	}

	public void actualizarDatos() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
		for (int i = 0; i < productosFactura.size(); i++) {

			String descripcion = productosFactura.get(i).getProducto().getDescripcionProducto();
			if (productosFactura.get(i).getProducto().getTieneSerial() == 1) {
				descripcion += " N.Serie " + productosFactura.get(i).getArticulo().getNumeroSerie();
			}

			float valorUnitarioSinIva = productosFactura.get(i).getProducto().getCostoProducto();
			float margen = productosFactura.get(i).getProducto().getMargenProducto();
			margen = (1 + (margen / 100));
			valorUnitarioSinIva *= margen;

			int subSinIva = (int) (valorUnitarioSinIva * productosFactura.get(i).getCantidad());

			int valorUnitarioConIva = (int) (valorUnitarioSinIva * (1 + (productosFactura.get(i).getProducto().getIvaProducto() / 100)));

			int subConIva = valorUnitarioConIva * productosFactura.get(i).getCantidad();

			addRow(new String[] { (i + 1) + "", descripcion, productosFactura.get(i).getCantidad() + "", (int) valorUnitarioSinIva + "", subSinIva + "", (int) valorUnitarioConIva + "",
					subConIva + "", productosFactura.get(i).getProducto().getIdProducto() + "" });
		}
	}

	public ArrayList<ItemFactura> getProductosFactura() {
		return productosFactura;
	}

	public void setProductosFactura(ArrayList<ItemFactura> productosFactura) {
		this.productosFactura = productosFactura;
	}

	public boolean validarEntrada(String entrada) {
		try {
			int valido = Integer.parseInt(entrada.replaceAll("[.,]", ""));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
