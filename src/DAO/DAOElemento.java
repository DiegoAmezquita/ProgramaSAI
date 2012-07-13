package DAO;

import Codigo.Elemento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOElemento {

    public DAOConnectionLogin m_DAOConnectionLogin;
    DAOvarios daoVarios;

    public DAOElemento() {
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

    public Elemento consultar(int codEle) {
        Elemento eleTempo = new Elemento();
        try {

            String sql = "select * from elemento where codele= " + codEle;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                eleTempo.setCodigoElemento(Integer.parseInt(rs.getString("CODELE")));
                eleTempo.setCodigoOrdenServicio(Integer.parseInt(rs.getString("CODORDSER")));
                eleTempo.setDescripcion(rs.getString("DESCRIPCIONELE"));
                eleTempo.setEstado(rs.getString("ESTADOELE"));
                eleTempo.setMarca(rs.getString("MARCAELE"));
                eleTempo.setModelo(rs.getString("MODELOELE"));
                eleTempo.setSerial(rs.getString("SERIALELE"));
                eleTempo.setTipo(rs.getString("TIPOELE"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrdenServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eleTempo;
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

    /**
     *
     * @param vario    vario
     */
    public int insert(Elemento elemento) {
        int tuplasAfectadas = 0;
        int marca = daoVarios.buscarCodigoVario(elemento.getMarca());
        int estado = daoVarios.buscarCodigoVario(elemento.getEstado());
        int tipo = daoVarios.buscarCodigoVario(elemento.getTipo());
        String sql = "INSERT INTO elemento(CODORDSER,TIPOELE,MARCAELE,MODELOELE,SERIALELE,ESTADOELE,DESCRIPCIONELE) "
                + "VALUES  (" + elemento.getCodigoOrdenServicio() + ","
                + tipo + ","
                + marca + ",'"
                + elemento.getModelo() + "','"
                + elemento.getSerial() + "',"
                + estado+ ",'"
                + elemento.getDescripcion() + "')";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
