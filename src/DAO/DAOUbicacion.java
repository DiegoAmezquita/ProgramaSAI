package DAO;

import Codigo.Ubicacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOUbicacion {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOUbicacion() {

        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    /**
     *
     * @param vario    vario
     */
    public ArrayList<String> retornarUbicaciones() {
        ArrayList<String> listaUbicaciones = new ArrayList<String>();
        try {
            String sql = "select * from ubicacion";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                listaUbicaciones.add(rs.getString("NOMBREUBI"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return listaUbicaciones;
    }

    public Ubicacion consultarUbicacion(String nombre) {
        Ubicacion ubicacion= new Ubicacion();
        try {
            String sql = "select * from ubicacion where nombreubi = '"+nombre+"'";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                ubicacion.setnombre(rs.getString("NOMBREUBI"));
                ubicacion.setcodigo(Integer.parseInt(rs.getString("CODIGOUBI")));                
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return ubicacion;
    }

    /**
     *
     * @param vario    vario
     */
    public int editar(Ubicacion ubicacion) {
        return 0;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Ubicacion ubicacion) {
        return 0;
    }
}
