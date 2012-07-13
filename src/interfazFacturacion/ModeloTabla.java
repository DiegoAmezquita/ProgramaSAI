package interfazFacturacion;

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

	public ModeloTabla(Object[] titulos, int numFilas,
			PanelFacturacion panelFactura) {
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

	public void setValueAt(String valor, int arg1, int arg2) {
		super.setValueAt(valor, arg1, arg2);
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		if (arg2 == 5) {
			System.out.println("ENTRA ---------" + arg0);
			if (arg0.equals("")) {
				arg0 = getValueAt(arg1, arg2);
			}
			super.setValueAt(arg0, arg1, arg2);
			// System.out.println("SALE ----------" + arg0);
			// ItemFactura tempo = productosFactura.get(arg1);
			// tempo.setPrecio(Double.parseDouble(arg0 + ""));
			// productosFactura.remove(arg1);
			// productosFactura.add(arg1, tempo);
		} else if (arg2 == 2) {
			if (arg0.equals("")) {
				arg0 = getValueAt(arg1, arg2);
			}
			try {
				ItemFactura tempo = productosFactura.get(arg1);
				if (tempo.getProducto().getTieneSerial() == 0) {
					tempo.setCantidad(Integer.parseInt(arg0 + ""));
					productosFactura.remove(arg1);
					productosFactura.add(arg1, tempo);
					super.setValueAt(arg0, arg1, arg2);
				} else {
					JOptionPane
							.showMessageDialog(null,
									"NO SE PUEDE CAMBIAR LA CANTIDAD DE UN PRODUCTO CON NUMERO DE SERIE");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"PROBLEMAS CON EL NUMERO QUE INGRESO");

			}
		}
		
		panelFactura.calcularCambiosProducto(arg1);
		// actualizarDatos();
		panelFactura.calcularValorFactura();

	}

	public void actualizarDatos() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
		for (int i = 0; i < productosFactura.size(); i++) {

			String descripcion = productosFactura.get(i).getProducto()
					.getDescripcionProducto();
			if (productosFactura.get(i).getProducto().getTieneSerial() == 1) {
				descripcion += " N.Serie "
						+ productosFactura.get(i).getArticulo()
								.getNumeroSerie();
			}

			float valorUnitarioSinIva = productosFactura.get(i).getProducto()
					.getCostoProducto();
			float margen = productosFactura.get(i)
					.getProducto().getMargenProducto();
			margen = (1+(margen/100));
			valorUnitarioSinIva *= margen;
			
			int subSinIva = (int)(valorUnitarioSinIva
					* productosFactura.get(i).getCantidad());

			int valorUnitarioConIva = (int) (valorUnitarioSinIva * (1 + (productosFactura
					.get(i).getProducto().getIvaProducto() / 100)));

			int subConIva = valorUnitarioConIva
					* productosFactura.get(i).getCantidad();

			addRow(new String[] { (i + 1) + "", descripcion,
					productosFactura.get(i).getCantidad() + "",
					(int)valorUnitarioSinIva + "", subSinIva + "",
					(int)valorUnitarioConIva + "", subConIva + "",
					productosFactura.get(i).getProducto().getIdProducto() + "" });
		}
	}

	public void cargarProductoArticuloLista(ArrayList<ItemFactura> lista) {
		DAOArticulo daoArticulo = new DAOArticulo();
		DAOProducto daoProducto = new DAOProducto();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdArticulo() != 0) {
				lista.get(i).setArticulo(
						daoArticulo
								.consultar(lista.get(i).getIdArticulo() + ""));
			}
			lista.get(i).setProducto(
					daoProducto.consultar(lista.get(i).getIdProducto()));
			productosFactura.add(lista.get(i));
		}
	}

	public ArrayList<ItemFactura> getProductosFactura() {
		return productosFactura;
	}

	public void setProductosFactura(ArrayList<ItemFactura> productosFactura) {
		this.productosFactura = productosFactura;
	}
}
