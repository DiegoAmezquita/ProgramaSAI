package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Codigo.FacturaVenta;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOFacturaVenta {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOFacturaVenta() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "",
				"", "");
		daoVarios = new DAOvarios();

	}

	public ArrayList<FacturaVenta> buscarFacturas(String texto) {
		ArrayList<FacturaVenta> listaFacturas = new ArrayList<FacturaVenta>();
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from FacturaVenta where numerofactura = '"
							+ texto + "'");
			while (rs.next()) {
				FacturaVenta facturaResultado = new FacturaVenta();
				facturaResultado.setIdFactura(Integer.parseInt(rs
						.getString("idFactura")));
				facturaResultado.setPrefijoFactura(rs
						.getString("prefijoFactura"));
				facturaResultado
						.setNumeroFactura(rs.getString("numeroFactura"));
				facturaResultado.setIdCliente(Integer.parseInt(rs
						.getString("idCliente")));
				facturaResultado.setNitCliente(rs.getString("nitCliente"));
				facturaResultado
						.setDigitoCliente(rs.getString("digitoCliente"));
				facturaResultado.setDireccionCliente(rs
						.getString("direccionCliente"));
				facturaResultado.setCiudadClient(rs.getString("ciudadCliente"));
				facturaResultado.setTelefonoCliente(rs
						.getString("telefonoCliente"));
				facturaResultado.setBaseIva(Double.parseDouble(rs
						.getString("baseIva")));
				facturaResultado
						.setIva(Double.parseDouble(rs.getString("iva")));
				facturaResultado.setTotalFactura(Double.parseDouble(rs
						.getString("totalFactura")));
				facturaResultado.setFechaVence(rs.getString("fechaVence"));
				facturaResultado
						.setFechaAceptada(rs.getString("fechaAceptada"));
				facturaResultado.setEstado(Integer.parseInt(rs
						.getString("estado")));
				facturaResultado.setFechaFactura(rs.getString("fechafactura"));
				listaFacturas.add(facturaResultado);

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOFacturaVenta.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return listaFacturas;
	}

	public FacturaVenta consultar(int idFactura) {
		FacturaVenta facturaResultado = new FacturaVenta();
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from FacturaVenta where idFactura = '"
							+ idFactura + "'");
			while (rs.next()) {
				facturaResultado.setIdFactura(Integer.parseInt(rs
						.getString("idFactura")));
				facturaResultado.setPrefijoFactura(rs
						.getString("prefijoFactura"));
				facturaResultado
						.setNumeroFactura(rs.getString("numeroFactura"));
				facturaResultado.setIdCliente(Integer.parseInt(rs
						.getString("idCliente")));
				facturaResultado.setNitCliente(rs.getString("nitCliente"));
				facturaResultado
						.setDigitoCliente(rs.getString("digitoCliente"));
				facturaResultado.setDireccionCliente(rs
						.getString("direccionCliente"));
				facturaResultado.setCiudadClient(rs.getString("ciudadCliente"));
				facturaResultado.setTelefonoCliente(rs
						.getString("telefonoCliente"));
				facturaResultado.setBaseIva(Double.parseDouble(rs
						.getString("baseIva")));
				facturaResultado
						.setIva(Double.parseDouble(rs.getString("iva")));
				facturaResultado.setTotalFactura(Double.parseDouble(rs
						.getString("totalFactura")));
				facturaResultado.setFechaVence(rs.getString("fechaVence"));
				facturaResultado
						.setFechaAceptada(rs.getString("fechaAceptada"));
				facturaResultado.setEstado(Integer.parseInt(rs
						.getString("estado")));

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOFacturaVenta.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return facturaResultado;
	}

	public int editar(FacturaVenta factura) {
		int tuplasAfectadas = 0;
		String sql = "Update FacturaVenta Set " + "prefijoFactura='"
				+ factura.getPrefijoFactura() + "'," + "numeroFactura="
				+ factura.getNumeroFactura() + "," + "idCliente='"
				+ factura.getIdCliente() + "'," + "nitCliente='"
				+ factura.getNitCliente() + "'," + "digitoCliente="
				+ factura.getDigitoCliente() + "," + "direccionCliente='"
				+ factura.getDireccionCliente() + "'," + "ciudadCliente="
				+ factura.getCiudadClient() + "," + "telefonoCliente='"
				+ factura.getTelefonoCliente() + "'," + "baseIva="
				+ factura.getBaseIva() + "," + "iva='" + factura.getIva()
				+ "'," + "totalFactura=" + factura.getTotalFactura() + ","
				+ "fechaVence='" + factura.getFechaVence() + "',"
				// + "fechaAceptada=" + factura.getFechaAceptada() + ","
				+ "estado='" + factura.getEstado() + "'";

		sql = sql + " Where idFactura =" + factura.getIdFactura() + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int anular(int idFactura) {
		int tuplasAfectadas = 0;
		String sql = "Update FacturaVenta Set " + "estado='" + 1 + "'";
		sql = sql + " Where idFactura =" + idFactura + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int insert(FacturaVenta factura) {
		int idGenerado = 0;
		String sql = "";
		sql = "INSERT INTO facturaventa (" + "prefijoFactura ,"
				+ "numeroFactura ," + "idCliente ," + "nitCliente ,"
				+ "digitoCliente ," + "direccionCliente ," + "ciudadCliente ,"
				+ "telefonoCliente ," + "baseIva ," + "iva ,"
				+ "totalFactura ," + "fechaVence ," + "fechaAceptada ," +
				// "fechafactura," +
				"estado)" + "values (" + "'"
				+ factura.getPrefijoFactura()
				+ "',"
				+ "'"
				+ factura.getNumeroFactura()
				+ "',"
				+ "'"
				+ factura.getIdCliente()
				+ "',"
				+ "'"
				+ factura.getNitCliente()
				+ "',"
				+ "'"
				+ factura.getDigitoCliente()
				+ "',"
				+ "'"
				+ factura.getDireccionCliente()
				+ "',"
				+ "'"
				+ factura.getCiudadClient()
				+ "',"
				+ "'"
				+ factura.getTelefonoCliente()
				+ "',"
				+ "'"
				+ factura.getBaseIva()
				+ "',"
				+ "'"
				+ factura.getIva()
				+ "',"
				+ "'"
				+ factura.getTotalFactura()
				+ "',"
				+ "'"
				+ factura.getFechaVence()
				+ "',"
				+ "'"
				+ factura.getFechaAceptada() + "'," +
				// "'"+factura.getFechaFactura()+"'," +
				"'" + factura.getEstado() + "')";

		System.out.println("SQL INSERCION: " + sql);
		m_DAOConnectionLogin.executeUpdate(sql);
		idGenerado = m_DAOConnectionLogin.devolverUltimoID();
		return idGenerado;
	}

	public int consultarNumeroItems(int idFactura) {
		int cantidadItems = 0;
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from itemfacturaventa where idFactura = '"
							+ idFactura + "'");
			while (rs.next()) {
				cantidadItems += rs.getInt("Cantidad");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cantidadItems;
	}
}
