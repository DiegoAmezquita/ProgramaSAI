/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Codigo.Contacto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class DAOContacto {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOContacto() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");
    }

    public ArrayList<Contacto> retornarContactos(int codPerson) {
        ArrayList<Contacto> listaContactos = new ArrayList<Contacto>();
        try {
            String sql = "select con.*,v.nombrevar from contacto con,varios v where codpers=" + codPerson
                    + " and con.NOMBRECONTACTO=v.CODVAR";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                Contacto contacto = new Contacto();
                contacto.setTipoContacto(rs.getString("nombrevar"));
                contacto.setCodContact(Integer.parseInt(rs.getString("OPC_CODPERS")));
                contacto.setCodPerson(Integer.parseInt(rs.getString("CODPERS")));
                listaContactos.add(contacto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaContactos;

    }

    public String retornarTipoContacto(int codigoContacto, int codigoPersona) {
        String tipoContacto = "";
        try {
            String sql = "select con.*,v.nombrevar from contacto con,varios v where con.codpers=" + codigoPersona
                    + " and con.opc_codpers=" + codigoContacto
                    + " and con.NOMBRECONTACTO=v.CODVAR";
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                tipoContacto = rs.getString("NOMBREVAR");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOvarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tipoContacto;
    }

    public int insert(Contacto contacto) {
        int tuplasAfectadas = 0;
        String sql = "INSERT INTO contacto(NOMBRECONTACTO,CODPERS,OPC_CODPERS) "
                + "VALUES  (" + contacto.getTipoContacto() + "," + contacto.getCodPerson() + "," + contacto.getCodContact() + ")";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int editar(Contacto contacto) {
        int tuplasAfectadas = 0;
        String sql = "Update contacto Set NOMBRECONTACTO=" + contacto.getTipoContacto() + ""
                + " Where OPC_CODPERS=" + contacto.getCodContact() + ""
                + " and CODPERS=" + contacto.getCodPerson() + "";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public int borrar(int codigoContacto) {
        int tuplasAfectadas = 0;
        String sql = "DELETE FROM contacto WHERE OPC_CODPERS=" + codigoContacto + ";";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
