package DAO;

import Codigo.Detalle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAODetalle {

    public DAOConnectionLogin m_DAOConnectionLogin;
    private DAOUbicacion daoUbicacion;
    private DAOvarios daoVarios;

    public DAODetalle() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
        daoUbicacion = new DAOUbicacion();
        daoVarios = new DAOvarios();
    }

    /**
     *
     * @param vario    vario
     */
    public ArrayList<Detalle> consultar(int codigoPersona) {
        ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();
        
        try {
            String sql = "select detalle.FECHADETALLE,detalle.NOMBREDETALLE,detalle.DESCRIPDETALLE,varios.NOMBREVAR, ubicacion.NOMBREUBI,detalle.CODDETALLE from detalle,varios,ubicacion  where detalle.CODPERS = '" + codigoPersona + "' and varios.codvar =detalle.tipoubidetalle and ubicacion.CODIGOUBI = detalle.CODIGOUBI  order by detalle.tipoubidetalle";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Detalle det = new Detalle();
                det.setNombre(rs.getString("NOMBREDETALLE"));
                det.setDescripcion(rs.getString("DESCRIPDETALLE"));
                det.setTipo(rs.getString("NOMBREVAR"));
                det.setNombreUbicacion(rs.getString("NOMBREUBI"));
                det.setCodigo(Integer.parseInt(rs.getString("CODDETALLE")));
                det.setFechaCreacion(rs.getString("FECHADETALLE"));
                listaDetalles.add(det);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return listaDetalles;
    }
    
    
    public ArrayList<Detalle> consultarPorTipo(int codigoPersona,String tipo) {
        ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();
        
        try {
            String sql = "select detalle.FECHADETALLE,detalle.NOMBREDETALLE,detalle.DESCRIPDETALLE,varios.NOMBREVAR, ubicacion.NOMBREUBI,detalle.CODDETALLE from detalle,varios,ubicacion  where detalle.CODPERS = '" + codigoPersona + "' and varios.codvar =detalle.tipoubidetalle and ubicacion.CODIGOUBI = detalle.CODIGOUBI and varios.NOMBREVAR ='"+tipo +"'  order by detalle.tipoubidetalle";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Detalle det = new Detalle();
                det.setNombre(rs.getString("NOMBREDETALLE"));
                det.setDescripcion(rs.getString("DESCRIPDETALLE"));
                det.setTipo(rs.getString("NOMBREVAR"));
                det.setNombreUbicacion(rs.getString("NOMBREUBI"));
                det.setCodigo(Integer.parseInt(rs.getString("CODDETALLE")));
                det.setFechaCreacion(rs.getString("FECHADETALLE"));
                listaDetalles.add(det);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return listaDetalles;
    }
    
    
    
    
    

    public Detalle consultarDetalle(int codigo) {
        Detalle detalleResultado = new Detalle();
        try {
            ResultSet rs = m_DAOConnectionLogin.executeQuery("select detalle.*,ubicacion.NOMBREUBI,varios.NOMBREVAR from detalle,ubicacion,varios where CODDETALLE = '" + codigo + "' and ubicacion.CODIGOUBI = detalle.CODIGOUBI and varios.codvar =detalle.tipoubidetalle");
            while (rs.next()) {
                detalleResultado.setNombre(rs.getString("NOMBREDETALLE"));
                detalleResultado.setCodigo(Integer.parseInt(rs.getString("CODDETALLE")));
                detalleResultado.setDescripcion(rs.getString("DESCRIPDETALLE"));
                detalleResultado.setNombreUbicacion(rs.getString("NOMBREUBI"));
                detalleResultado.setTipo(rs.getString("NOMBREVAR"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalleResultado;

    }

    public ArrayList<Detalle> consultarDetalleCompleto(int codigoPersona) {
        ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();

        try {
            String sql = "select detalle.NOMBREDETALLE,detalle.DESCRIPDETALLE,varios.NOMBREVAR, ubicacion.NOMBREUBI,detalle.CODDETALLE "
                    + "from detalle,varios,ubicacion  "
                    + "where detalle.CODPERS = '" + codigoPersona + "' "
                    + "and varios.codvar =detalle.tipoubidetalle "
                    + "and ubicacion.CODIGOUBI = detalle.CODIGOUBI  "
                    + "order by detalle.tipoubidetalle";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);

            while (rs.next()) {
                Detalle det = new Detalle();
                det.setNombre(rs.getString("NOMBREDETALLE"));
                det.setDescripcion(rs.getString("DESCRIPDETALLE"));
                det.setTipo(rs.getString("NOMBREVAR"));
                det.setNombreUbicacion(rs.getString("NOMBREUBI"));
                det.setCodigo(Integer.parseInt(rs.getString("CODDETALLE")));
                listaDetalles.add(det);
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
            Logger.getLogger(DAODetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaDetalles;
    }


    /**
     *
     * @param vario    vario
     */
    public int editar(Detalle detalle) {

        int tuplasAfectadas = 0;
        int codigoUbicacion = daoUbicacion.consultarUbicacion(detalle.getNombreUbicacion()).getcodigo();
        String sql = "Update detalle Set nombredetalle='" + detalle.getNombre() + "',"
                + "descripdetalle='" + detalle.getDescripcion() + "',"
                + "tipoubidetalle='" + detalle.getTipo() + "',"
                + "codigoubi='" + codigoUbicacion + "'"
                + " Where CODDETALLE='" + detalle.getCodigo() + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Detalle detalle) {
        int tuplasAfectadas = 0;       
        int codigoUbicacion = daoUbicacion.consultarUbicacion(detalle.getNombreUbicacion()).getcodigo();
        int tipo = daoVarios.buscarCodigoVario(detalle.getTipo());
        String sql = "INSERT INTO detalle(NOMBREDETALLE,DESCRIPDETALLE,CODPERS,CODIGOUBI,TIPOUBIDETALLE) "
                + "VALUES  ('" + detalle.getNombre() + "','" + detalle.getDescripcion() + "'," + detalle.getCodigoPersona() + "," + codigoUbicacion + "," + tipo + ")";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int borrar(int codigoDetalle) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM detalle WHERE CODDETALLE='" + codigoDetalle + "';";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
