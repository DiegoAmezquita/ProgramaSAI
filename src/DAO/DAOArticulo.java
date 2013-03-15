package DAO;

import Codigo.Articulo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOArticulo {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOArticulo() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "",
				"", "");
		daoVarios = new DAOvarios();

	}

	public Articulo consultar(String idArticulo) {
		Articulo articuloResultado = new Articulo();
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from articulo where idarticulo = '"
							+ idArticulo + "'");
			while (rs.next()) {
				articuloResultado.setIdArticulo(Integer.parseInt(rs
						.getString("idarticulo")));
				articuloResultado.setIdProductoArticulo(Integer.parseInt(rs
						.getString("idproductoarticulo")));
				articuloResultado.setNumeroSerie(rs.getString("numeroserie"));
				articuloResultado.setEstado(Integer.parseInt(rs
						.getString("estado")));
				articuloResultado.setIdProveedor(rs.getString("idproveedor"));
				articuloResultado.setCostoArticulo(Integer.parseInt(rs
						.getString("costoarticulo")));
				articuloResultado.setFechaIngreso(rs.getString("fechaingreso"));
				articuloResultado.setIdDocumentoSoporte(rs
						.getString("iddocumentosoporte"));
				articuloResultado.setIdSitio(rs.getString("idsitio"));
				articuloResultado.setCantidad(Integer.parseInt(rs
						.getString("cantidad")));

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOArticulo.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return articuloResultado;
	}

	public boolean verificarProductoRegistrado(String idProductoArticulo) {
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from articulo where idProductoArticulo = '"
							+ idProductoArticulo + "'");
			while (rs.next()) {
				return true;

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOArticulo.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return false;
	}

	public ArrayList<Articulo> buscarArticulosPorProducto(
			String idProductoArticulo) {
		ArrayList<Articulo> temporal = new ArrayList<Articulo>();

		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from articulo where idProductoArticulo = '"
							+ idProductoArticulo + "'");
			while (rs.next()) {
				Articulo articuloResultado = new Articulo();
				articuloResultado.setIdArticulo(Integer.parseInt(rs
						.getString("idarticulo")));
				articuloResultado.setIdProductoArticulo(Integer.parseInt(rs
						.getString("idproductoarticulo")));
				articuloResultado.setNumeroSerie(rs.getString("numeroserie"));
				articuloResultado.setEstado(Integer.parseInt(rs
						.getString("estado")));
				articuloResultado.setIdProveedor(rs.getString("idproveedor"));
				articuloResultado.setCostoArticulo(Integer.parseInt(rs
						.getString("costoarticulo")));
				articuloResultado.setFechaIngreso(rs.getString("fechaingreso"));
				articuloResultado.setIdDocumentoSoporte(rs
						.getString("iddocumentosoporte"));
				articuloResultado.setIdSitio(rs.getString("idsitio"));
				articuloResultado.setCantidad(Integer.parseInt(rs
						.getString("cantidad")));
				temporal.add(articuloResultado);

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOArticulo.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return temporal;
	}

	public ArrayList<Articulo> buscarArticulosPorProductoDisponibles(
			String idProductoArticulo) {
		ArrayList<Articulo> temporal = new ArrayList<Articulo>();

		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from articulo where idProductoArticulo = '"
							+ idProductoArticulo + "' and estado='0'");
			while (rs.next()) {
				Articulo articuloResultado = new Articulo();
				articuloResultado.setIdArticulo(Integer.parseInt(rs
						.getString("idarticulo")));
				articuloResultado.setIdProductoArticulo(Integer.parseInt(rs
						.getString("idproductoarticulo")));
				articuloResultado.setNumeroSerie(rs.getString("numeroserie"));
				articuloResultado.setEstado(Integer.parseInt(rs
						.getString("estado")));
				articuloResultado.setIdProveedor(rs.getString("idproveedor"));
				articuloResultado.setCostoArticulo(Integer.parseInt(rs
						.getString("costoarticulo")));
				articuloResultado.setFechaIngreso(rs.getString("fechaingreso"));
				articuloResultado.setIdDocumentoSoporte(rs
						.getString("iddocumentosoporte"));
				articuloResultado.setIdSitio(rs.getString("idsitio"));
				articuloResultado.setCantidad(Integer.parseInt(rs
						.getString("cantidad")));
				temporal.add(articuloResultado);

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOArticulo.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return temporal;
	}

	public int editar(Articulo articulo) {
		int tuplasAfectadas = 0;
		String sql = "Update articulo Set idProductoArticulo='"
				+ articulo.getIdProductoArticulo() + "'," + "numeroSerie='"
				+ articulo.getNumeroSerie() + "'," + "estado="
				+ articulo.getEstado() + "," + "idProveedor='"
				+ articulo.getIdProveedor() + "'," + "costoArticulo="
				+ articulo.getCostoArticulo() + "," + "fechaIngreso="
				+ articulo.getFechaIngreso() + "," + "idsitio="
				+ articulo.getIdSitio() + "," + "idDocumentoSoporte='"
				+ articulo.getIdDocumentoSoporte() + "'," + "cantidad='"
				+ articulo.getCantidad() + "',";

		sql = sql + " Where idArticulo =" + articulo.getIdArticulo() + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int cambiarEstadoVendido(int idArticulo) {
		int tuplasAfectadas = 0;
		String sql = "Update articulo Set estado='1'";
		sql = sql + " Where idArticulo =" + idArticulo + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int cambiarEstadoDevuelto(int idArticulo) {
		int tuplasAfectadas = 0;
		String sql = "Update articulo Set estado='0'";
		sql = sql + " Where idArticulo =" + idArticulo + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int insert(Articulo articulo) {
		int tuplasAfectadas = 0;
		String sql = "";
		sql = "INSERT INTO articulo (idProductoArticulo ,"
				+ "numeroSerie ,estado ,idProveedor ,costoArticulo ,"
				+ "idDocumentoSoporte,idsitio,cantidad)" + "values ('"
				+ articulo.getIdProductoArticulo() + "', " + "'"
				+ articulo.getNumeroSerie() + "', " + "'"
				+ articulo.getEstado() + "', '" + articulo.getIdProveedor()
				+ "', " + "'" + articulo.getCostoArticulo() + "', " + " '"
				+ articulo.getIdDocumentoSoporte() + "','"
				+ articulo.getIdSitio() + "','" + articulo.getCantidad() + "')";

		System.out.println("SQL INSERCION: " + sql);
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}
}
