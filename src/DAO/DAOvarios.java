package DAO;

import Codigo.Varios;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.NumberFormat;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOvarios {

	private DAOConnectionLogin m_DAOConnectionLogin;

	public DAOvarios() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "",
				"", "");
	}

	/**
	 * 
	 * @param vario
	 *            vario
	 */
	public ArrayList<Varios> consultar(Varios vario) {
		String sql = "";
		if (!vario.getnombreVario().equals("")) {
			if (sql.equals("")) {
				sql = sql + " where ";
			} else {
				sql = sql + " and ";
			}
			sql = sql + " nombrevar like '%" + vario.getnombreVario() + "%'";
		}
		if (!vario.getabreviadoVario().equals("")) {
			if (sql.equals("")) {
				sql = sql + " where ";
			} else {
				sql = sql + " and ";
			}
			sql = sql + " abreviadovar like '%" + vario.getabreviadoVario()
					+ "%'";
		}
		if (vario.getcodVar() != 0) {
			if (sql.equals("")) {
				sql = sql + " where ";
			} else {
				sql = sql + " and ";
			}
			sql = sql + " codvar like '%" + vario.getcodVar() + "%'";
		}

		return null;
	}

	public Varios consultar(String codigo) {
		Varios variosTempo = new Varios();
		try {
			String sql = "select a.* from varios a where a.CODVAR='" + codigo
					+ "'";
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				variosTempo.setnombreVario(rs.getString("NOMBREVAR"));
				variosTempo.setabreviadoVario(rs.getString("ABREVIADOVAR"));
				variosTempo.setcodVar(Integer.parseInt(rs.getString("CODVAR")));
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		return variosTempo;
	}

	public ArrayList<Varios> consultarVariosPorCategoria(String categoria) {
		ArrayList<Varios> listaVarios = new ArrayList<Varios>();
		try {
			String sql = "select a.* from varios a, varios b where b.NOMBREVAR='"
					+ categoria + "' and a.TIPONIVEL3VAR=b.codvar";
			if (categoria.equals("Marca Elemento")) {
				sql = sql + " order by a.NOMBREVAR,a.TIPONIVEL2VAR";
			} else {
				sql = sql + " order by a.TIPONIVEL2VAR";
			}
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				Varios vario = new Varios();
				vario.setnombreVario(rs.getString("NOMBREVAR"));
				vario.settipoNivel2Var(Integer.parseInt(rs
						.getString("TIPONIVEL2VAR")));
				vario.settipoNivel3Var(Integer.parseInt(rs
						.getString("TIPONIVEL3VAR")));
				listaVarios.add(vario);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return listaVarios;
	}

	public String consultarVariosPorCategoriaNivel2(String categoria, int nivel2) {
		String nombreVar = "";
		try {
			String sql = "select a.* from varios a, varios b where b.NOMBREVAR='"
					+ categoria
					+ "' and a.TIPONIVEL3VAR=b.codvar and a.tiponivel2var = "
					+ nivel2;
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				nombreVar = rs.getString("NOMBREVAR");
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return nombreVar;
	}

	public int buscarCodigoVario(String nombre) {
		int codigoVario = 0;
		try {

			String sql = "select codvar from varios where nombrevar = '"
					+ nombre + "'";
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				codigoVario = Integer.parseInt(rs.getString("CODVAR"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return codigoVario;
	}

	public String[] retornarFechaBD() {
		String[] fecha = new String[4];
		try {
			String sql = "SELECT DATE_FORMAT(CURDATE(),'%d') as dia,"
					+ "DATE_FORMAT(CURDATE(),'%m') as mes,"
					+ "DATE_FORMAT(CURDATE(),'%Y') as anio,"
					+ "curTime() as hora;";
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				fecha[0] = rs.getString("dia");
				fecha[1] = rs.getString("mes");
				fecha[2] = rs.getString("anio");
				fecha[3] = rs.getString("hora");
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return fecha;
	}

	public static String darFormatoNumeros(String numero) {
		String nuevoNumero = "";
		NumberFormat numberFormatter;

		numberFormatter = NumberFormat.getNumberInstance();
		nuevoNumero = numberFormatter.format(new BigInteger(numero));

		return nuevoNumero;
	}

	public int editar(Varios vario) {
		int tuplasAfectadas = 0;
		String sql = "Update varios Set nombrevar='" + vario.getnombreVario()
				+ "'";
		sql = sql + " Where codvar ='" + vario.getcodVar() + "'";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;
	}

	public int[] buscarDatosCategoria(String categoria) {
		int[] tempo = new int[2];
		try {
		String sql = "SELECT a.tiponivel1var as '1', max(a.TIPONIVEL2VAR) as '2' FROM varios a, varios b WHERE b.NOMBREVAR = '"
				+ categoria
				+ "' and a.TIPONIVEL3VAR = b.CODVAR group by a.TIPONIVEL1VAR";
		ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
		
			while (rs.next()) {
				tempo[0] = Integer.parseInt(rs.getString("1"));
				tempo[1] = Integer.parseInt(rs.getString("2"));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempo;
	}

	/**
	 * 
	 * @param vario
	 *            vario
	 */
	public int insert(String nombreVar, String categoria) {
		int tuplasAfectadas = 0;
		int codVar = buscarCodigoVario(categoria);
		int[] tempo = buscarDatosCategoria(categoria);

		String sql = "INSERT INTO VARIOS(TIPONIVEL1VAR,TIPONIVEL2VAR,TIPONIVEL3VAR,NOMBREVAR,ABREVIADOVAR) "
				+ "VALUES  ("
				+ tempo[0]
				+ ","
				+ tempo[1]
				+ ","
				+ codVar
				+ ",'"
				+ nombreVar
				+ "','" + nombreVar + "')";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;

	}
}
