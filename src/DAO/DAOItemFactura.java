package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Codigo.ItemFactura;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOItemFactura {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOItemFactura() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
		daoVarios = new DAOvarios();

	}
	
	public ArrayList<ItemFactura> retornarListaItems(int idFactura){
		ArrayList<ItemFactura> listaItems = new ArrayList<ItemFactura>();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from itemFacturaVenta where 	idfactura = '" + idFactura + "'");
			while (rs.next()) {
				ItemFactura itemFacturaResultado = new ItemFactura();
				itemFacturaResultado.setIdItemFactura(Integer.parseInt(rs.getString("idItem")));
				itemFacturaResultado.setIdFactura(Integer.parseInt(rs.getString("IdFactura")));
				itemFacturaResultado.setIdArticulo(Integer.parseInt(rs.getString("idArticulo")));
				itemFacturaResultado.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
				itemFacturaResultado.setCantidad(Integer.parseInt(rs.getString("Cantidad")));
				itemFacturaResultado.setPrecio(Double.parseDouble(rs.getString("Unitario")));
				itemFacturaResultado.setSubTotal(Double.parseDouble(rs.getString("Subtotal")));
				itemFacturaResultado.setGarantia(Integer.parseInt(rs.getString("Garantia")));
				itemFacturaResultado.setIva(Double.parseDouble(rs.getString("Iva")));				
				listaItems.add(itemFacturaResultado);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOItemFactura.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaItems;
	}
	

	public ItemFactura consultar(String idItemFactura) {
		ItemFactura itemFacturaResultado = new ItemFactura();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from itemFacturaVenta where 	idItem = '" + idItemFactura + "'");
			while (rs.next()) {
				itemFacturaResultado.setIdItemFactura(Integer.parseInt(rs.getString("idItem")));
				itemFacturaResultado.setIdFactura(Integer.parseInt(rs.getString("IdFactura")));
				itemFacturaResultado.setIdArticulo(Integer.parseInt(rs.getString("idArticulo")));
				itemFacturaResultado.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
				itemFacturaResultado.setCantidad(Integer.parseInt(rs.getString("Cantidad")));
				itemFacturaResultado.setPrecio(Double.parseDouble(rs.getString("Unitario")));
				itemFacturaResultado.setSubTotal(Double.parseDouble(rs.getString("Subtotal")));
				itemFacturaResultado.setGarantia(Integer.parseInt(rs.getString("Garantia")));
				itemFacturaResultado.setIva(Double.parseDouble(rs.getString("Iva")));
				

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOItemFactura.class.getName()).log(Level.SEVERE, null, ex);
		}
		return itemFacturaResultado;
	}


	public int editar(ItemFactura itemFactura) {
		int tuplasAfectadas = 0;
//		String sql = "Update itemFacturaVenta Set "		
//				+ "idItem='" + factura.getPrefijoFactura() + "',"
//				+ "IdFactura=" + factura.getNumeroFactura() + ","
//				+ "idArticulo='" + factura.getIdCliente() + "',"
//				+ "idProducto='" + factura.getNitCliente()+ "',"
//				+ "Cantidad=" + factura.getDigitoCliente()+ ","
//				+ "direccionCliente='" + factura.getDireccionCliente() + "',"
//				+ "ciudadCliente=" + factura.getCiudadClient() + ","
//				+ "telefonoCliente='" + factura.getTelefonoCliente() + "',"
//				+ "baseIva=" + factura.getBaseIva() + ","
//				+ "iva='" + factura.getIva() + "',"
//				+ "totalFactura=" + factura.getTotalFactura() + ","
//				+ "fechaVence='" + factura.getFechaVence() + "',"
////				+ "fechaAceptada=" + factura.getFechaAceptada() + ","
//				+ "estado='" + factura.getEstado() + "'";
//
//
//		sql = sql + " Where idFactura =" + factura.getIdFactura() + "";
//		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}


	public int insert(ItemFactura itemFactura) {
		String sql = "";	
		sql = "INSERT INTO itemfacturaventa (" +
				"IdFactura ," +
				"idArticulo ," +
				"idProducto ," +
				"Cantidad ," +
				"Unitario ," +
				"Subtotal ," +
				"Garantia ," +			
				"Iva)" +
				"values (" +
				"'"+itemFactura.getIdFactura()+"'," +
				"'"+itemFactura.getIdArticulo()+"'," +
				"'"+itemFactura.getIdProducto()+"'," +
				"'"+itemFactura.getCantidad()+"'," +
				"'"+itemFactura.getPrecio()+"'," +
				"'"+itemFactura.getSubTotal()+"'," +
				"'"+itemFactura.getGarantia()+"'," +
				"'"+itemFactura.getIva()+"')";


		System.out.println("SQL INSERCION: "+sql);
		int tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}
}
