package DAO;

import Codigo.FacturaCompra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOFacturaCompra {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOFacturaCompra() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
		daoVarios = new DAOvarios();

	}

	public FacturaCompra consultar(String idFactura) {
		FacturaCompra facturaResultado = new FacturaCompra();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from facturacompra where idFacturaCompra = '" + idFactura + "'");
			while (rs.next()) {
				facturaResultado.setIdFacturaCompra(Integer.parseInt(rs.getString("idFacturaCompra")));
				facturaResultado.setFecha(rs.getString("fecha"));
				facturaResultado.setIdProveedor(Integer.parseInt(rs.getString("idproveedor")));
				facturaResultado.setNumero(rs.getString("numero"));
				facturaResultado.setFechaIngreso(rs.getString("fechaIngreso"));

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOFacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
		}
		return facturaResultado;
	}


	public int editar(FacturaCompra factura) {
		int tuplasAfectadas = 0;
		String sql = "Update facturacompra Set "
				+ "fecha='" + factura.getFecha() + "',"
				+ "idproveedor=" + factura.getIdProveedor() + ","
				+ "fechaIngreso=" + factura.getFechaIngreso() + ","
				+ "numero='" + factura.getNumero()+ "'";


		sql = sql + " Where idFacturaCompra =" + factura.getIdFacturaCompra() + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}


	public int insert(FacturaCompra factura) {
		int idGenerado= 0;		
		String sql = "";	
		sql = "INSERT INTO facturacompra (idproveedor ," +
				"numero,fecha,fechaIngreso)" +
				"values ('"+factura.getIdProveedor()+"', '" +
				factura.getNumero()+"', '"+factura.getFecha()+"', '"+factura.getFechaIngreso()+"')";


		m_DAOConnectionLogin.executeUpdate(sql);
		idGenerado = m_DAOConnectionLogin.devolverUltimoID(); 
		return idGenerado;
	}
}
