package interfazInventario;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Codigo.Producto;

public class ModeloTablaArticulos extends DefaultTableModel {

	PanelInventario panelInventario;
	boolean permitir = false;

	public ModeloTablaArticulos(Object[] titulos, int numFilas,
			PanelInventario panelInventario) {
		super(titulos, numFilas);
		
		this.panelInventario = panelInventario;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column >= 2) {
			return true;
		}
		return false;
	}

	public void cambiarCantidadPermitido(Object aValue, int row){
		permitir=true;
		setValueAt(aValue, row, 2);
		permitir=false;
	}

	@Override
	public void addRow(Vector rowData) {
		super.addRow(rowData);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (column == 3) {
			try {
				super.setValueAt(Integer.parseInt(aValue + ""), row, column);
				panelInventario.calcularTotalFacturaInventario();
				
				panelInventario.calcularPrecioSugerido(row);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"EL COSTO DEBE SER UN NUMERO");
			}
		} else if (column == 2) {
			try {
				boolean test = panelInventario.cambiarExistenciasTabla(row,
						Integer.parseInt(aValue + ""),permitir);
				if(test){
				super.setValueAt(Integer.parseInt(aValue + ""), row, column);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"LA CANTIDAD DEBE SER UN NUMERO");
			}
		} else if (column == 7) {
			try {
				super.setValueAt(Integer.parseInt(aValue + ""), row, column);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"EL PRECIO DEBE SER UN NUMERO");
			}
		}else if(column==6){
			try {
				super.setValueAt(Integer.parseInt(aValue + ""), row, column);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"ERROR CON EL PRECIO SUGERIDO");
			}
		}
	}

}
