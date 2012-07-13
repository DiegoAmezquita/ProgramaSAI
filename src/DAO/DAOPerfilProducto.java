package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Codigo.ItemFactura;
import Codigo.PerfilProducto;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOPerfilProducto {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOPerfilProducto() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
		daoVarios = new DAOvarios();

	}
	

	public PerfilProducto consultar(String id) {
		PerfilProducto perfilProducto = new PerfilProducto();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from perfilproducto where id = '" + id+ "'");
			while (rs.next()) {
				perfilProducto.setId(Integer.parseInt(rs.getString("id")));
				perfilProducto.setNombre(rs.getString("nombre"));
				perfilProducto.setMargenMinimo(Float.parseFloat(rs.getString("margenminimo")));
				perfilProducto.setMargenSugerido(Float.parseFloat(rs.getString("margensugerido")));
				perfilProducto.setMargenMaximo(Float.parseFloat(rs.getString("margenmaximo")));
				perfilProducto.setIva(Float.parseFloat(rs.getString("iva")));
				perfilProducto.setTieneSerial(Integer.parseInt(rs.getString("tieneserial")));
				perfilProducto.setGarantiaMeses(Integer.parseInt(rs.getString("garantiameses")));
				
				

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOPerfilProducto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return perfilProducto;
	}
	
	public PerfilProducto consultarNombre(String nombre) {
		PerfilProducto perfilProducto = new PerfilProducto();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from perfilproducto where nombre = '" + nombre+ "'");
			while (rs.next()) {
				perfilProducto.setId(Integer.parseInt(rs.getString("id")));
				perfilProducto.setNombre(rs.getString("nombre"));
				perfilProducto.setMargenMinimo(Float.parseFloat(rs.getString("margenminimo")));
				perfilProducto.setMargenSugerido(Float.parseFloat(rs.getString("margensugerido")));
				perfilProducto.setMargenMaximo(Float.parseFloat(rs.getString("margenmaximo")));
				perfilProducto.setIva(Float.parseFloat(rs.getString("iva")));
				perfilProducto.setTieneSerial(Integer.parseInt(rs.getString("tieneserial")));
				perfilProducto.setGarantiaMeses(Integer.parseInt(rs.getString("garantiameses")));
				

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOPerfilProducto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return perfilProducto;
	}
	
	public ArrayList<PerfilProducto> getListaPerfiles() {
		ArrayList<PerfilProducto> listaTempo = new ArrayList<PerfilProducto>();
		try {			
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from perfilproducto");
			while (rs.next()) {
				PerfilProducto perfilProducto = new PerfilProducto();
				perfilProducto.setId(Integer.parseInt(rs.getString("id")));
				perfilProducto.setNombre(rs.getString("nombre"));
				perfilProducto.setMargenMinimo(Float.parseFloat(rs.getString("margenminimo")));
				perfilProducto.setMargenSugerido(Float.parseFloat(rs.getString("margensugerido")));
				perfilProducto.setMargenMaximo(Float.parseFloat(rs.getString("margenmaximo")));
				perfilProducto.setIva(Float.parseFloat(rs.getString("iva")));
				perfilProducto.setTieneSerial(Integer.parseInt(rs.getString("tieneserial")));
				perfilProducto.setGarantiaMeses(Integer.parseInt(rs.getString("garantiameses")));
				listaTempo.add(perfilProducto);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOPerfilProducto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaTempo;
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


//	public int insert(ItemFactura itemFactura) {
//		String sql = "";	
//		sql = "INSERT INTO itemfacturaventa (" +
//				"IdFactura ," +
//				"idArticulo ," +
//				"idProducto ," +
//				"Cantidad ," +
//				"Unitario ," +
//				"Subtotal ," +
//				"Garantia ," +			
//				"Iva)" +
//				"values (" +
//				"'"+itemFactura.getIdFactura()+"'," +
//				"'"+itemFactura.getIdArticulo()+"'," +
//				"'"+itemFactura.getIdProducto()+"'," +
//				"'"+itemFactura.getCantidad()+"'," +
//				"'"+itemFactura.getPrecio()+"'," +
//				"'"+itemFactura.getSubTotal()+"'," +
//				"'"+itemFactura.getGarantia()+"'," +
//				"'"+itemFactura.getIva()+"')";
//
//
//		System.out.println("SQL INSERCION: "+sql);
//		int tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
//		return tuplasAfectadas;
//	}
}
