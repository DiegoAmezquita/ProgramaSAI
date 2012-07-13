package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Codigo.Elemento;
import Codigo.Imagen;

public class DAOImagen {
	public DAOConnectionLogin m_DAOConnectionLogin;
	DAOvarios daoVarios;

	public DAOImagen() {
		m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
		daoVarios = new DAOvarios();
	}

	/**
	 *
	 * @param vario    vario
	 */
	public ArrayList<Elemento> retornarElementosOrden(int codOrdSer) {
		ArrayList<Elemento> listaElementos = new ArrayList<Elemento>();
		try {
			String sql = "Select e.codele codigo, v1.nombrevar tipo, v2.nombrevar marca,e.modeloele modelo,e.serialele serial, v3.nombrevar estado, e.descripcionele descripcion"
					+ " from elemento e,varios v1,varios v2,varios v3 "
					+ " where codordser= " + codOrdSer + " "
					+ " and v1.codvar=e.tipoele"
					+ " and v2.codvar = e.marcaele"
					+ " and v3.codvar = e.estadoele;";           
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				Elemento elemento = new Elemento();
				elemento.setCodigoElemento(Integer.parseInt(rs.getString("codigo")));
				elemento.setDescripcion(rs.getString("descripcion"));
				elemento.setEstado(rs.getString("estado"));
				elemento.setMarca(rs.getString("marca"));
				elemento.setModelo(rs.getString("modelo"));
				elemento.setSerial(rs.getString("serial"));
				elemento.setTipo(rs.getString("tipo"));
				listaElementos.add(elemento);

			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaElementos;
	}

	public Elemento consultarCompleto(int codEle) {
		Elemento eleTempo = new Elemento();
		try {

			String sql = "Select e.codele codigo, v1.nombrevar tipo, v2.nombrevar marca,e.modeloele modelo,e.serialele serial, v3.nombrevar estado, e.descripcionele descripcion"
					+ " from elemento e,varios v1,varios v2,varios v3 "
					+ " where codele= " + codEle + " "
					+ " and v1.codvar=e.tipoele"
					+ " and v2.codvar = e.marcaele"
					+ " and v3.codvar = e.estadoele;";
			ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
			while (rs.next()) {
				eleTempo.setCodigoElemento(Integer.parseInt(rs.getString("codigo")));
				eleTempo.setDescripcion(rs.getString("DESCRIPCION"));
				eleTempo.setEstado(rs.getString("ESTADO"));
				eleTempo.setMarca(rs.getString("MARCA"));
				eleTempo.setModelo(rs.getString("MODELO"));
				eleTempo.setSerial(rs.getString("SERIAL"));
				eleTempo.setTipo(rs.getString("TIPO"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return eleTempo;
	}

	public ArrayList<Imagen> consultar(int idProducto) {
		String sql = "select * from imagenproducto where idproducto ="+idProducto;
		ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
		ArrayList<Imagen> temporal = new ArrayList<Imagen>();
		try {
			while (rs.next()) {
				Imagen imgTemp = new Imagen();
				String nombre = rs.getString("idimagen");
				imgTemp.setIdImagen(Integer.parseInt(nombre));
				byte[]  bytesImagen = rs.getBytes("imagen");
				imgTemp.setImageFile(bytesImagen);
				
				temporal.add(imgTemp);
			}
			// TODO Auto-generated catch block
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temporal;
	}




	/**
	 *
	 * @param vario    vario
	 */
	public int editar(Elemento elemento) {
		int tuplasAfectadas = 0;
		int marca = daoVarios.buscarCodigoVario(elemento.getMarca());
		int estado = daoVarios.buscarCodigoVario(elemento.getEstado());
		int tipo = daoVarios.buscarCodigoVario(elemento.getTipo());
		String sql = "Update elemento Set tipoele='" + tipo+ "',"
				+ "marcaele='" + marca + "',"
				+ "modeloele='" + elemento.getModelo() + "',"
				+ "estadoele='" + estado + "',"
				+ "descripcionele='" + elemento.getDescripcion() + "',"
				+ "serialele='" + elemento.getSerial() + "'"
				+ " Where CODELE='" + elemento.getCodigoElemento() + "'";
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
		return tuplasAfectadas;

	}
	
	
	public int borrar(int idImagen) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM imagenproducto WHERE idImagen='" + idImagen + "';";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

	public int insert(String path, int idProducto) {	
		int tuplasAfectadas = 0;        
		tuplasAfectadas = m_DAOConnectionLogin.executeUpdateImage(path,idProducto);

		return tuplasAfectadas;
	}
}
