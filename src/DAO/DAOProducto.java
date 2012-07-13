package DAO;

import Codigo.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOProducto {

	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOProducto() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "",
				"", "");
		daoVarios = new DAOvarios();

	}

	public Producto consultar(int idProduct) {
		Producto productResultado = new Producto();
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from producto where idproducto = '"
							+ idProduct + "'");
			while (rs.next()) {
				productResultado.setIdProducto(Integer.parseInt(rs
						.getString("idProducto")));
				productResultado.setIdCategoriaProducto(Integer.parseInt(rs
						.getString("idCategoriaProducto")));
				productResultado.setIdMarcaProducto(Integer.parseInt(rs
						.getString("idMarcaProducto")));
				productResultado.setReferenciaProducto(rs
						.getString("referenciaProducto"));
				productResultado.setDescripcionProducto(rs
						.getString("descripcionProducto"));
				productResultado.setEANCodeProducto(rs
						.getString("EANCodeProducto"));
				productResultado.setNumeroParteProducto(rs
						.getString("numeroParteProducto"));
				productResultado.setUPCCodeProducto(rs
						.getString("CPUCodeProducto"));
				productResultado.setPluProducto(rs.getString("pluProducto"));
				productResultado.setMargenProducto(Float.parseFloat(rs
						.getString("margenProducto")));
				productResultado.setFechaActualizacionProducto(rs
						.getString("fechaActualizacionProducto"));
				productResultado.setExistenciasProducto(Integer.parseInt(rs
						.getString("existenciasProducto")));
				productResultado.setPrecioProducto(Integer.parseInt(rs
						.getString("precioProducto")));
				productResultado.setCostoProducto(Integer.parseInt(rs
						.getString("costoProducto")));
				productResultado.setAbreviadoProducto(rs
						.getString("abreviadoProducto"));
				productResultado.setMargenMinimo(Float.parseFloat(rs
						.getString("margenMinimo")));
				productResultado.setIdTipoProducto(Integer.parseInt(rs
						.getString("idTipoProducto")));
				productResultado.setTieneSerial(Integer.parseInt(rs
						.getString("tieneSerial")));
				productResultado.setIvaProducto(Float.parseFloat(rs
						.getString("iva")));
				productResultado.setGarantiaMeses(Integer.parseInt(rs
						.getString("garantiaMeses")));

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return productResultado;
	}

	public String armarBusqueda(String linea, String atributos) {

		StringTokenizer tokens = new StringTokenizer(linea);
		StringTokenizer tokenAtrib;
		String res = "";
		int conta = 0;
		while (tokens.hasMoreTokens()) {
			String aux = tokens.nextToken();
			if (conta > 0) {
				res = res + " or ";
			} else {
				res = "where ";
			}
			int cc = 0;
			tokenAtrib = new StringTokenizer(atributos);
			while (tokenAtrib.hasMoreTokens()) {
				if (cc > 0) {
					res = res + " or ";
				}
				res = res + tokenAtrib.nextToken() + " like '%" + aux + "%' ";
				cc++;
			}
			conta++;
		}
		return res;
	}

	public ArrayList<Producto> pruebaConsultaOptimizadoMAL(String search) {
		int x = -1;
		String query = "Select * from producto where ";
		String[] palabras = search.split(" ");
		for (int i = 0; i < palabras.length; i++) {
			if (i == 0)
				query = query + "( ";
			else
				query = query + " OR ( ";
			try {
				x = Integer.parseInt(palabras[i]);
			} catch (NumberFormatException e) {
				x = -1;
			}
			if (x >= 0) {
				if (palabras[i].length() == 13)
					query = query + "EANCODEPRODUCTO=" + x + " OR ";
				else if (palabras[i].length() < 6)
					query = query + "PLUPRODUCTO=" + x + " OR ";
			}
			query = query + "ABREVIADOPRODUCTO like '%" + palabras[i]
					+ "%' OR " + "DESCRIPCIONPRODUCTO like '%" + palabras[i]
					+ "%' OR " + "NUMEROPARTEPRODUCTO like '%" + palabras[i]
					+ "%' )";
		}
		query = query + " Order by ABREVIADOPRODUCTO";
		ArrayList<Producto> listProduct = new ArrayList<Producto>();

		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery(query);
			while (rs.next()) {
				Producto productResultado = new Producto();
				productResultado.setIdProducto(Integer.parseInt(rs
						.getString("idProducto")));
				productResultado.setIdCategoriaProducto(Integer
						.parseInt(daoVarios.consultarVariosPorCategoriaNivel2(
								"Tipo de Elemento", Integer.parseInt(rs
										.getString("idCategoriaProducto")))));
				productResultado.setIdMarcaProducto(Integer.parseInt(daoVarios
						.consultarVariosPorCategoriaNivel2("Marca Elemento",
								Integer.parseInt(rs
										.getString("idMarcaProducto")))));
				productResultado.setReferenciaProducto(rs
						.getString("referenciaProducto"));
				productResultado.setDescripcionProducto(rs
						.getString("descripcionProducto"));
				productResultado.setEANCodeProducto(rs
						.getString("EANCodeProducto"));
				productResultado.setNumeroParteProducto(rs
						.getString("numeroParteProducto"));
				productResultado.setUPCCodeProducto(rs
						.getString("CPUCodeProducto"));
				productResultado.setPluProducto(rs.getString("pluProducto"));
				productResultado.setMargenProducto(Float.parseFloat(rs
						.getString("margenProducto")));
				productResultado.setFechaActualizacionProducto(rs
						.getString("fechaActualizacionProducto"));
				productResultado.setExistenciasProducto(Integer.parseInt(rs
						.getString("existenciasProducto")));
				productResultado.setPrecioProducto(Integer.parseInt(rs
						.getString("precioProducto")));
				productResultado.setCostoProducto(Integer.parseInt(rs
						.getString("costoProducto")));
				productResultado.setAbreviadoProducto(rs
						.getString("abreviadoProducto"));
				productResultado.setMargenMinimo(Float.parseFloat(rs
						.getString("margenMinimo")));
				productResultado.setIdTipoProducto(Integer.parseInt(rs
						.getString("idTipoProducto")));
				productResultado.setTieneSerial(Integer.parseInt(rs
						.getString("tieneSerial")));
				productResultado.setIvaProducto(Float.parseFloat(rs
						.getString("iva")));
				productResultado.setGarantiaMeses(Integer.parseInt(rs
						.getString("garantiaMeses")));
				listProduct.add(productResultado);
			}
		} catch (SQLException ex) {
		}

		return listProduct;
	}

	public ArrayList<Producto> consultarOptimizado(String cad) {

		// TODO modificar la consulta para que no sea con el codigo sino con el
		// nombre del padre

		// String cadena = "select producto.* from producto, varios a where " +
		// "(producto.idmarcaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 127 and a.nombrevar like'%"+cad+"%') or "
		// +
		// "(producto.idcategoriaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 124 and a.nombrevar like'%"+cad+"%') or "
		// +
		// "(producto.EANCodeProducto like '%"+cad+"%') or "+
		// "(producto.CPUCodeProducto like '%"+cad+"%') or "+
		// "(producto.pluProducto like '%"+cad+"%') or "+
		// "(producto.descripcionProducto like '%"+cad+"%') or "+
		// "(producto.abreviadoProducto like '%"+cad+"%')";

		String cadena = "(select producto.* from producto, varios a where "
				+ "(producto.idcategoriaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 124 and a.nombrevar like'%"
				+ cad
				+ "%') or "
				+ "(producto.idmarcaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 127 and a.nombrevar like'%"
				+ cad
				+ "%')) "
				+ "union"
				+ "(select producto.* from producto where "
				+ "(producto.EANCodeProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.CPUCodeProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.pluProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.referenciaProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.numeroParteProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.descripcionProducto like '%"
				+ cad
				+ "%') or "
				+ "(producto.abreviadoProducto like '%" + cad + "%'))";

		System.out.println("CONSULTA PRODUCTO " + cadena);

		// "
		// String cadena = "select producto.idproducto" +
		// ",producto.idCategoriaProducto" +
		// ",producto.idMarcaProducto" +
		// ",producto.referenciaProducto" +
		// ",producto.descripcionProducto" +
		// ",producto.EANCodeProducto" +
		// ",producto.numeroParteProducto" +
		// ",producto.CPUCodeProducto" +
		// ",producto.pluProducto" +
		// ",producto.margenProducto" +
		// ",producto.fechaActualizacionProducto" +
		// ",producto.existenciasProducto" +
		// ",producto.precioProducto" +
		// ",producto.existenciasProducto" +
		// ",producto.costoProducto" +
		// ",producto.abreviadoProducto" +
		// ",producto.margenMinimo" +
		// ",producto.idTipoProducto from producto, varios a,varios b where (producto.idmarcaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 127 and a.nombrevar like'%"+cad+"%') or "
		// +
		// "(producto.idcategoriaproducto = a.TIPONIVEL2VAR and a.TIPONIVEL3VAR = 124 and a.nombrevar like'%"+cad+"%')";

		ArrayList<Producto> listProduct = new ArrayList<Producto>();
		try {
			ResultSet rs = m_DAOConnectionLogin.executeQuery(cadena);
			while (rs.next()) {
				Producto productResultado = new Producto();
				productResultado.setIdProducto(Integer.parseInt(rs
						.getString("idProducto")));
				productResultado.setIdCategoriaProducto(Integer.parseInt(rs
						.getString("idCategoriaProducto")));
				productResultado.setIdMarcaProducto(Integer.parseInt(rs
						.getString("idMarcaProducto")));
				productResultado.setReferenciaProducto(rs
						.getString("referenciaProducto"));
				productResultado.setDescripcionProducto(rs
						.getString("descripcionProducto"));
				productResultado.setEANCodeProducto(rs
						.getString("EANCodeProducto"));
				productResultado.setNumeroParteProducto(rs
						.getString("numeroParteProducto"));
				productResultado.setUPCCodeProducto(rs
						.getString("CPUCodeProducto"));
				productResultado.setPluProducto(rs.getString("pluProducto"));
				productResultado.setMargenProducto(Float.parseFloat(rs
						.getString("margenProducto")));
				productResultado.setFechaActualizacionProducto(rs
						.getString("fechaActualizacionProducto"));
				productResultado.setExistenciasProducto(Integer.parseInt(rs
						.getString("existenciasProducto")));
				productResultado.setPrecioProducto(Integer.parseInt(rs
						.getString("precioProducto")));
				productResultado.setCostoProducto(Integer.parseInt(rs
						.getString("costoProducto")));
				productResultado.setAbreviadoProducto(rs
						.getString("abreviadoProducto"));
				productResultado.setMargenMinimo(Float.parseFloat(rs
						.getString("margenMinimo")));
				productResultado.setIdTipoProducto(Integer.parseInt(rs
						.getString("idTipoProducto")));
				productResultado.setTieneSerial(Integer.parseInt(rs
						.getString("tieneSerial")));
				productResultado.setIvaProducto(Float.parseFloat(rs
						.getString("iva")));
				productResultado.setGarantiaMeses(Integer.parseInt(rs
						.getString("garantiaMeses")));
				listProduct.add(productResultado);
			}
		} catch (SQLException ex) {
		}
		return listProduct;
	}

	public int contarPalabras(String origen, String busqueda) {
		StringTokenizer token;
		token = new StringTokenizer(busqueda);
		int conta = 0;
		while (token.hasMoreTokens()) {
			String aux = token.nextToken();
			if (origen.contains(aux)) {
				conta++;
			}
		}

		return conta;
	}

	public Producto buscarPorCodigo(int idProduct) {
		Producto productResultado = new Producto();
		try {
			ResultSet rs = m_DAOConnectionLogin
					.executeQuery("select * from producto where idproducto = '"
							+ idProduct + "'");
			while (rs.next()) {
				productResultado.setIdProducto(Integer.parseInt(rs
						.getString("idProducto")));
				productResultado.setIdCategoriaProducto(Integer.parseInt(rs
						.getString("idCategoriaProducto")));
				productResultado.setIdMarcaProducto(Integer.parseInt(rs
						.getString("idMarcaProducto")));
				productResultado.setReferenciaProducto(rs
						.getString("referenciaProducto"));
				productResultado.setDescripcionProducto(rs
						.getString("descripcionProducto"));
				productResultado.setEANCodeProducto(rs
						.getString("EANCodeProducto"));
				productResultado.setNumeroParteProducto(rs
						.getString("numeroParteProducto"));
				productResultado.setUPCCodeProducto(rs
						.getString("CPUCodeProducto"));
				productResultado.setPluProducto(rs.getString("pluProducto"));
				productResultado.setMargenProducto(Float.parseFloat(rs
						.getString("margenProducto")));
				productResultado.setFechaActualizacionProducto(rs
						.getString("fechaActualizacionProducto"));
				productResultado.setExistenciasProducto(Integer.parseInt(rs
						.getString("existenciasProducto")));
				productResultado.setPrecioProducto(Integer.parseInt(rs
						.getString("precioProducto")));
				productResultado.setCostoProducto(Integer.parseInt(rs
						.getString("costoProducto")));
				productResultado.setAbreviadoProducto(rs
						.getString("abreviadoProducto"));
				productResultado.setMargenMinimo(Float.parseFloat(rs
						.getString("margenMinimo")));
				productResultado.setIdTipoProducto(Integer.parseInt(rs
						.getString("idTipoProducto")));
				productResultado.setTieneSerial(Integer.parseInt(rs
						.getString("tieneSerial")));
				productResultado.setIvaProducto(Float.parseFloat(rs
						.getString("iva")));
				productResultado.setGarantiaMeses(Integer.parseInt(rs
						.getString("garantiaMeses")));

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOProducto.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return productResultado;
	}

	public int editar(Producto product) {
		int tuplasAfectadas = 0;
		String sql = "Update producto Set EANCodeProducto='"
				+ product.getEANCodeProducto() + "'," + "CPUCodeProducto='"
				+ product.getUPCCodeProducto() + "'," + "pluProducto="
				+ product.getPluProducto() + "," + "referenciaProducto='"
				+ product.getReferenciaProducto() + "',"
				+ "idCategoriaProducto=" + product.getIdCategoriaProducto()
				+ "," + "idMarcaProducto=" + product.getIdMarcaProducto() + ","
				+ "idTipoProducto=" + product.getIdTipoProducto() + ","
				+ "numeroParteProducto='" + product.getNumeroParteProducto()
				+ "'," + "costoProducto='" + product.getCostoProducto() + "',"
				+ "margenProducto='" + product.getMargenProducto() + "',"
				+ "margenMinimo='" + product.getMargenMinimo() + "',"
				+ "fechaActualizacionProducto='"
				+ product.getFechaActualizacionProducto() + "',"
				+ "precioProducto='" + product.getPrecioProducto() + "',"
				+ "abreviadoProducto='" + product.getAbreviadoProducto() + "',"
				+ "tieneSerial='" + product.getTieneSerial() + "'," + "iva='"
				+ product.getIvaProducto() + "'," + "descripcionProducto='"
				+ product.getDescripcionProducto() + "'," + " garantiameses='"
				+ product.getGarantiaMeses() + "'";

		sql = sql + " Where idproducto=" + product.getIdProducto() + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);

		return tuplasAfectadas;
	}

	public int insert(Producto producto) {
		int tuplasAfectadas = 0;
		String sql = "";
		sql = "insert into producto(EANCodeProducto,CPUCodeProducto,pluProducto,referenciaProducto,"
				+ "idCategoriaProducto,idMarcaProducto,idTipoProducto,numeroParteProducto,descripcionProducto,"
				+ "costoProducto,margenProducto,margenMinimo,fechaActualizacionProducto,precioProducto,abreviadoProducto,tieneSerial,iva,garantiameses) "
				+ "VALUES  ('"
				+ producto.getEANCodeProducto()
				+ "','"
				+ producto.getUPCCodeProducto()
				+ "',"
				+ producto.getPluProducto()
				+ ",'"
				+ producto.getReferenciaProducto()
				+ "',"
				+ producto.getIdCategoriaProducto()
				+ ","
				+ ""
				+ producto.getIdMarcaProducto()
				+ ","
				+ producto.getIdTipoProducto()
				+ ", '"
				+ producto.getNumeroParteProducto()
				+ "'"
				+ ",'"
				+ producto.getDescripcionProducto()
				+ "'"
				+ ",'"
				+ producto.getCostoProducto()
				+ "'"
				+ ",'"
				+ producto.getMargenProducto()
				+ "'"
				+ ",'"
				+ producto.getMargenMinimo()
				+ "'"
				+ ",'"
				+ producto.getFechaActualizacionProducto()
				+ "'"
				+ ",'"
				+ producto.getPrecioProducto()
				+ "'"
				+ ",'"
				+ producto.getAbreviadoProducto()
				+ "'"
				+ ",'"
				+ producto.getTieneSerial()
				+ "'"
				+ ",'"
				+ producto.getIvaProducto()
				+ "'"
				+ ",'"
				+ producto.getGarantiaMeses() + "'" + ") ";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int getMaximoPlu(int categoria) {
		int tempo = 0;
		int categoria1 = (categoria + 1) * 10000;
		int categoria2 = (categoria + 2) * 10000;

		try {
			String sql = "SELECT max( `pluProducto` ) as plu FROM `producto` WHERE `pluProducto` >'"
					+ categoria1 + "' AND `pluProducto` <'" + categoria2 + "'";

			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

			System.out.println("DEVUELVE ESTE PLU 1 " + tempo);
			while (rs.next()) {
				if (rs.getString("plu") == null) {
					tempo = categoria1 + 1;
					break;
				}
				tempo = Integer.parseInt(rs.getString("plu")) + 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return tempo;
	}

	public boolean isExistePlu(String plu) {
		try {
			String sql = "SELECT pluproducto FROM `producto` WHERE `pluProducto` ='"
					+ plu + "'";

			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString("pluproducto") == null) {
					return false;
				} else {
					return true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public int cambiarExistencias(int idProducto, int existencias) {
		int nuevasExistencias = buscarPorCodigo(idProducto)
				.getExistenciasProducto();
		nuevasExistencias = nuevasExistencias + existencias;
		System.out.println("''''''''''''''''''''''''''''''''ID PRODUCTO " + idProducto
				+ " existencias a agregar " + existencias + " total "
				+ nuevasExistencias);

		int tuplasAfectadas = 0;
		String sql = "Update producto Set existenciasProducto='"
				+ nuevasExistencias + "'";
		sql = sql + " Where idproducto=" + idProducto + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int cambiarCosto(String idProducto, String costo) {
		int tuplasAfectadas = 0;
		String sql = "Update producto Set costoproducto='" + costo + "'";
		sql = sql + " Where idproducto=" + idProducto + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int cambiarPrecio(String idProducto, String precio) {
		int tuplasAfectadas = 0;
		String sql = "Update producto Set precioproducto='" + precio + "'";
		sql = sql + " Where idproducto=" + idProducto + "";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

}
