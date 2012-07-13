package DAO;

import Codigo.Login;
import Codigo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOLogin {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOLogin() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    /**
     *
     * @param vario    vario
     */
    public Login consultar(int codigoPersona) {
        Login login = new Login();
        try {
            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from login where codpers = " + codigoPersona);
            while (rs.next()) {
                login.setNombreLog(rs.getString("NOMBRELOG"));
                login.setClaveLog(rs.getString("CLAVELOG"));
                login.setCodigoPers(Integer.parseInt(rs.getString("CODPERS")));
                login.setCodigoLog(Integer.parseInt(rs.getString("CODLOG")));
                login.setTipoUsuario(Integer.parseInt(rs.getString("TIPOUSUARIO")));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return login;

    }

    /**
     *
     * @param vario    vario
     */
    public int editar(Login login) {
        int tuplasAfectadas = 0;
        String sql = "Update login Set nombrelog='" + login.getNombreLog() + "',"
                + "clavelog='" + login.getClaveLog() + "',"
                + "tipousuario='" + login.getTipoUsuario() + "'"
                + " Where CODPERS='" + login.getCodigoPers() + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    /**
     *
     * @param vario    vario
     */
    public int insert(Login login) {
        int tuplasAfectadas = 0;       
        String sql = "INSERT INTO login(CODPERS,NOMBRELOG,CLAVELOG,TIPOUSUARIO) "
                + "VALUES  (" + login.getCodigoPers() + ",'" + login.getNombreLog() + "','" + login.getClaveLog() + "'," + login.getTipoUsuario() + ")";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int borrar(int codigoUsuario) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM permisos WHERE CODLOG='" + codigoUsuario + "';";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        sql = "DELETE FROM login WHERE CODLOG='" + codigoUsuario + "';";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int buscarCodigoLogin(String nombreLogin) {
        int codLog = 0;
        try {
            String sql = "select * from login where nombrelog = '" + nombreLogin + "'";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                codLog = (Integer.parseInt(rs.getString("CODLOG")));

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return codLog;

    }

    public ArrayList<Persona> retornarTodosUsuarios() {
        ArrayList<Persona> listUsuarios = new ArrayList<Persona>();
        try {
            String sql = "select p.nombrepers,p.apellidopers,l.codlog from login l,persona p "
                    + "where l.codpers = p.codpers";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setNombre(rs.getString("nombrepers"));
                persona.setApellido(rs.getString("apellidopers"));
                listUsuarios.add(persona);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return listUsuarios;

    }

    public int buscarLoginPorPersona(String nombre){
        int codLog = 0;
        try {
            String sql = "select l.codlog from login l, persona p "
                    + "where l.codpers = p.codpers "
                    + "and p.nombrepers = '" + nombre + "'";
           
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                codLog = (Integer.parseInt(rs.getString("codlog")));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return codLog;

    }
}
