package DAO;

import Codigo.Evento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOEvento {

    public DAOConnectionLogin m_DAOConnectionLogin;
    private DAOvarios daoVarios;

    public DAOEvento() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
        daoVarios = new DAOvarios();
    }

    /**
     *
     * @param vario    vario
     */
    public ArrayList<Evento> consultarPorUsuario(int codLog) {
        ArrayList<Evento> listaEventos = new ArrayList<Evento>();

        try {
            String sql = "select * from evento where responsableEven = " + codLog + " and siguienteeven != 1 order by leidoeven";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Evento eventoTempo = new Evento();
                eventoTempo.setCodigo(Integer.parseInt(rs.getString("CODEVEN")));
                eventoTempo.setCodigoElemento(Integer.parseInt(rs.getString("CODELE")));
                eventoTempo.setDescripcion(rs.getString("DESCRIPCIONEVEN"));
                eventoTempo.setFechaCreacion(rs.getString("FECHAASIGSUPERVI"));
                eventoTempo.setResponsableEven(Integer.parseInt(rs.getString("RESPONSABLEEVEN")));
                eventoTempo.setSiguienteEven(Integer.parseInt(rs.getString("SIGUIENTEEVEN")));
                eventoTempo.setTipo(rs.getString("NOMBRETIPOEVEN"));
                if (rs.getString("LEIDOEVEN").equals("0")) {
                    eventoTempo.setLeido(false);
                } else {
                    eventoTempo.setLeido(true);
                }
                listaEventos.add(eventoTempo);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEventos;

    }

    public Evento consultarPorCodigo(int codEven) {
        Evento eventoTempo = new Evento();
        try {
            String sql = "select * from evento where codeven = " + codEven;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                eventoTempo.setCodigo(Integer.parseInt(rs.getString("CODEVEN")));
                eventoTempo.setCodigoElemento(Integer.parseInt(rs.getString("CODELE")));
                eventoTempo.setDescripcion(rs.getString("DESCRIPCIONEVEN"));
                eventoTempo.setFechaCreacion(rs.getString("FECHAASIGSUPERVI"));
                eventoTempo.setResponsableEven(Integer.parseInt(rs.getString("RESPONSABLEEVEN")));
                eventoTempo.setSiguienteEven(Integer.parseInt(rs.getString("SIGUIENTEEVEN")));
                eventoTempo.setTipo(rs.getString("NOMBRETIPOEVEN"));
                if (rs.getString("LEIDOEVEN").equals("0")) {
                    eventoTempo.setLeido(false);
                } else {
                    eventoTempo.setLeido(true);
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventoTempo;
    }

    public int cambiarALeido(int codEven) {
        int tuplasAfectadas = 0;
        String sql = "Update evento Set leidoEven=" + true + " "
                + " Where codeven='" + codEven + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int agregarEventoSiguiente(int codEven) {
        int tuplasAfectadas = 0;
        String sql = "Update evento Set siguienteeven=" + 1 + " "
                + " Where codeven='" + codEven + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    /**
     *
     * @param vario    vario
     */
    public int editar(Evento evento) {
        return 0;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Evento evento) {
        int tuplasAfectadas = 0;
        String sql = "INSERT INTO evento(CODELE,DESCRIPCIONEVEN,FECHAASIGSUPERVI,RESPONSABLEEVEN,NOMBRETIPOEVEN,SIGUIENTEEVEN,LEIDOEVEN) "
                + "VALUES  (" + evento.getCodigoElemento() + ","
                + "'" + evento.getDescripcion() + "',"
                + "'" + evento.getFechaCreacion() + "',"
                + "" + evento.getResponsableEven() + ","
                + "" + daoVarios.buscarCodigoVario(evento.getTipo()) + ","
                + "" + evento.getSiguienteEven() + ","
                + "" + evento.isLeido() + ")";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int invalidarEventosAnteriores(int codElemento) {
        int tuplasAfectadas = 0;
         String sql = "Update evento Set siguienteeven=" + 1 + " "
                + " Where codele='" + codElemento + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;

    }
}
