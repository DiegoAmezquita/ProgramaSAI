package DAO;

import Codigo.Actividad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOActividad {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOActividad() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    /**
     *
     * @param vario    vario
     */
    public ArrayList<Actividad> consultarPorElemento(int codEle) {
        ArrayList<Actividad> listActivities = new ArrayList<Actividad>();
        try {
            String sql = "select * from actividad a, login l, persona p where codele = " + codEle + " "
                    + "and a.codlog = l.codlog "
                    + "and l.codpers = p.codpers";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setCodAct(Integer.parseInt(rs.getString("a.CODACT")));
                actividad.setCodEle(Integer.parseInt(rs.getString("a.CODELE")));
                actividad.setCodLog(rs.getString("p.nombrepers"));
                actividad.setFechaCreacion(rs.getString("a.FECHAACT"));
                actividad.setDescripcion(rs.getString("a.DESCRIPCIONACT"));
                listActivities.add(actividad);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listActivities;
    }

    public Actividad consultarPorCodigo(int codAct) {
        Actividad actividad = new Actividad();
        try {
            String sql = "select * from actividad a, login l, persona p where codact = " + codAct + " "
                    + "and a.codlog = l.codlog "
                    + "and l.codpers = p.codpers";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {

                actividad.setCodAct(Integer.parseInt(rs.getString("a.CODACT")));
                actividad.setCodEle(Integer.parseInt(rs.getString("a.CODELE")));
                actividad.setCodLog(rs.getString("p.nombrepers"));
                actividad.setFechaCreacion(rs.getString("a.FECHAACT"));
                actividad.setDescripcion(rs.getString("a.DESCRIPCIONACT"));

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actividad;
    }

    public ArrayList<Actividad> consultarPorElementoYUsuario(int codEle, int codLog) {
        ArrayList<Actividad> listActivities = new ArrayList<Actividad>();
        try {
            String sql = "select * from actividad where codele = " + codEle + " and codlog =" + codLog;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setCodAct(Integer.parseInt(rs.getString("CODACT")));
                actividad.setCodEle(Integer.parseInt(rs.getString("CODELE")));
                actividad.setCodLog(rs.getString("CODLOG"));
                actividad.setFechaCreacion(rs.getString("FECHAACT"));
                actividad.setDescripcion(rs.getString("DESCRIPCIONACT"));
                listActivities.add(actividad);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listActivities;
    }

    /**
     *
     * @param vario    vario
     */
    public int editar(Actividad actividad) {
        return 0;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Actividad actividad) {
        int tuplasAfectadas = 0;
        String sql = "INSERT INTO actividad(CODELE,CODLOG,DESCRIPCIONACT,FECHAACT) "
                + "VALUES  (" + actividad.getCodEle() + "," + actividad.getCodLog() + ",'" + actividad.getDescripcion() + "','" + actividad.getFechaCreacion() + "')";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
