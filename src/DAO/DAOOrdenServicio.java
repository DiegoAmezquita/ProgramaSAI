package DAO;

import Codigo.OrdenServicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOOrdenServicio {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOOrdenServicio() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    /**
     *
     * @param vario    vario
     */
    public OrdenServicio consultar(int codOrdSer) {
           OrdenServicio ordenTempo = new OrdenServicio();
        try {

            String sql = "Select * from ordenservicio where codordser=" + codOrdSer;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                ordenTempo.setCodigo(Integer.parseInt(rs.getString("CODORDSER")));
                ordenTempo.setCodPersDuenio(Integer.parseInt(rs.getString("CODPERS")));
                ordenTempo.setFechaCreacion(rs.getString("FECHAORDSER"));
                ordenTempo.setMotivo(rs.getString("MOTIVOORDSER"));
                ordenTempo.setNumero(Integer.parseInt(rs.getString("NUMEROORDSER")));
                ordenTempo.setUsuarioRecibe(Integer.parseInt(rs.getString("USUARIOORDSER")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ordenTempo;
    }

  

    /**
     *
     * @param vario    vario
     */
    public int insert(OrdenServicio ordenServicio) {
        int tuplasAfectadas = 0;
        String sql = "INSERT INTO ordenservicio(CODPERS,NUMEROORDSER,FECHAORDSER,USUARIOORDSER,MOTIVOORDSER) "
                + "VALUES  (" + ordenServicio.getCodPersDuenio() + "," + ordenServicio.getNumero() + ",'"
                + ordenServicio.getFechaCreacion() + "','"
                + ordenServicio.getUsuarioRecibe() + "','"
                + ordenServicio.getMotivo() + "')";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int buscarOrdenServicioParaCodigo(OrdenServicio ordenServicio) {
        int codOrdenServicio = 0;
        try {

            String sql = "select * from ordenservicio where codpers=" + ordenServicio.getCodPersDuenio()
                    + " and NUMEROORDSER=" + ordenServicio.getNumero()
                    + " and FECHAORDSER='" + ordenServicio.getFechaCreacion()
                    + "' and USUARIOORDSER='" + ordenServicio.getUsuarioRecibe()
                    + "' and MOTIVOORDSER='" + ordenServicio.getMotivo() + "'";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                codOrdenServicio = Integer.parseInt(rs.getString("CODORDSER"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codOrdenServicio;
    }

    public int aumentarNumeroOS() {
        int numero = 0;
        try {
            String sql = "select * from varios where codvar= 435;";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                numero = Integer.parseInt(rs.getString("tiponivel2var"));
            }
            int nuevoNumero = (numero + 1);
            m_DAOConnectionLogin.executeUpdate("Update varios Set tiponivel2var='" + nuevoNumero + "'"
                    + " Where CODVAR=435");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public ArrayList<OrdenServicio> listarOrdenesPersona(int codPers) {
        ArrayList<OrdenServicio> arrayOrdenesTempo = new ArrayList<OrdenServicio>();
        try {
           
            String sql = "select * from ordenservicio where codpers=" + codPers;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                OrdenServicio ordenTempo = new OrdenServicio();
                ordenTempo.setCodPersDuenio(Integer.parseInt(rs.getString("CODPERS")));
                ordenTempo.setCodigo(Integer.parseInt(rs.getString("CODORDSER")));
                ordenTempo.setFechaCreacion(rs.getString("FECHAORDSER"));
                ordenTempo.setMotivo(rs.getString("MOTIVOORDSER"));
                ordenTempo.setNumero(Integer.parseInt(rs.getString("NUMEROORDSER")));
                ordenTempo.setUsuarioRecibe(Integer.parseInt(rs.getString("USUARIOORDSER")));
                arrayOrdenesTempo.add(ordenTempo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayOrdenesTempo;

    }


     public int editar(OrdenServicio ordenServicio) {
        int tuplasAfectadas = 0;
        String sql = "Update ordenservicio Set motivoordser='" + ordenServicio.getMotivo()+ "'"
                + " Where CODORDSER='" + ordenServicio.getCodigo() + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
