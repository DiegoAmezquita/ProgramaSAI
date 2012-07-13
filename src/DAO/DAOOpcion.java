package DAO;

import Codigo.Opcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOOpcion {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOOpcion() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    /**
     *
     * @param vario    vario
     */
    public ArrayList<Opcion> consultar(Opcion opcion) {
        return null;
    }

    public ArrayList<Opcion> retornarOpcionesUsuario(int codigoUsuario) {
        ArrayList<Opcion> listaOpciones = new ArrayList<Opcion>();
        try {
            String sql = "select opcion.* from opcion,permisos where permisos.codopc = opcion.codopc and permisos.codlog =" + codigoUsuario + " order by opcion.opc_codopc,opcion.codopc";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Opcion opc = new Opcion();
                opc.setcodigo(Integer.parseInt(rs.getString("CODOPC")));
                opc.setdescripcion(rs.getString("DESCRIPOPC"));
                opc.setnivel(Integer.parseInt(rs.getString("NIVELOPC")));
                opc.setnombre(rs.getString("NOMBREOPC"));
                opc.setpagina(rs.getString("PAGINAOPC"));
                listaOpciones.add(opc);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOpciones;
    }

    public ArrayList<Opcion> retornarOpciones() {
        ArrayList<Opcion> listaOpciones = new ArrayList<Opcion>();
        try {
            String sql = "select * from opcion order by opcion.opc_codopc,opcion.codopc";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Opcion opc = new Opcion();
                opc.setcodigo(Integer.parseInt(rs.getString("CODOPC")));
                opc.setdescripcion(rs.getString("DESCRIPOPC"));
                opc.setnivel(Integer.parseInt(rs.getString("NIVELOPC")));
                opc.setnombre(rs.getString("NOMBREOPC"));
                opc.setpagina(rs.getString("PAGINAOPC"));
                listaOpciones.add(opc);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOpciones;
    }

    public int buscarCodigoOpcion(String nombreOpc) {
        int codigoOpc = 0;
        try {
            String sql = "select * from opcion where NOMBREOPC ='" + nombreOpc + "'";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                codigoOpc = Integer.parseInt(rs.getString("CODOPC"));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigoOpc;
    }

    public int agregarPermiso(int codigoPermiso, int codigoLogin) {
        int tuplasAfectadas = 0;
        boolean existente = false;
        try {
            String sql = "select * from permisos where codopc =" + codigoPermiso + " and codlog=" + codigoLogin;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                existente = true;
            }
            if (!existente) {
                sql = "INSERT INTO permisos(CODLOG,CODOPC) "
                        + "VALUES  (" + codigoLogin + ","
                        + codigoPermiso + ")";
                tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tuplasAfectadas;
    }

    public int borrarPermiso(int codigoPermiso, int codigoLogin) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM permisos WHERE codlog=" + codigoLogin + " and codopc = " + codigoPermiso + ";";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int borrarTodosPermisosUsuario(int codigoLogin) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM permisos WHERE CODLOG='" + codigoLogin + "';";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    /**
     *
     * @param vario    vario
     */
    public int editar(Opcion opcion) {
        return 0;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Opcion opcion) {
        return 0;
    }
}
