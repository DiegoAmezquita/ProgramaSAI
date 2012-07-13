package DAO;

import Codigo.Perfil;
import Codigo.Sitio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOSitio {

	public DAOConnectionLogin m_DAOConnectionLogin;


	public DAOSitio(){
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
	}

	/**
	 * 
	 * @param vario    vario
	 */
	public ArrayList<Sitio> consultar(){
		ArrayList<Sitio> temporal = new ArrayList<Sitio>();
	        try {
	            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from sitio");
	            while (rs.next()) {
	            	Sitio temp = new Sitio();
	            	temp.setIdSitio(Integer.parseInt(rs.getString("idsitio")));
	            	if(rs.getString("idpadre")!=null){
	            	temp.setIdPadreSitio(Integer.parseInt(rs.getString("idpadre")));
	            	}else{
	            		temp.setIdPadreSitio(0);
	            	}
	            	temp.setNombreSitio(rs.getString("nombre"));
	            	temp.setUbicacionSitio(rs.getString("ubicacion"));
	            	temporal.add(temp);
	            }
	        } catch (SQLException ex) {
	            ex.getStackTrace();
	        }
	        return temporal;
	}
	
	public Sitio consultar(int idSitio){
		Sitio temp = new Sitio();
	        try {
	            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from sitio where idsitio="+idSitio);
	            while (rs.next()) {
	            	temp.setIdSitio(Integer.parseInt(rs.getString("idsitio")));
	            	temp.setIdPadreSitio(Integer.parseInt(rs.getString("idpadre")));
	            	temp.setNombreSitio(rs.getString("nombre"));
	            	temp.setUbicacionSitio(rs.getString("ubicacion"));
	            }
	        } catch (SQLException ex) {
	            ex.getStackTrace();
	        }
	        return temp;
	}
	
	public Sitio consultar(String nombre){
		Sitio temp = new Sitio();
	        try {
	            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from sitio where nombre='"+nombre+"'");
	            while (rs.next()) {
	            	temp.setIdSitio(Integer.parseInt(rs.getString("idsitio")));
	            	if(rs.getString("idpadre")==null){
	            		temp.setIdPadreSitio(0);
	            	}else{
	            		temp.setIdPadreSitio(Integer.parseInt(rs.getString("idpadre")));
	            	}
	            	temp.setNombreSitio(rs.getString("nombre"));
	            	temp.setUbicacionSitio(rs.getString("ubicacion"));
	            }
	        } catch (SQLException ex) {
	            ex.getStackTrace();
	        }
	        return temp;
	}
	

	/**
	 * 
	 * @param vario    vario
	 */
	public int editar(Perfil perfil){
		return 0;
	}

	/**
	 * 
	 * @param vario    vario
	 */
	public int insert(Perfil perfil){
		return 0;
	}


}