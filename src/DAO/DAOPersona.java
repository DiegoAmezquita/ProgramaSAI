package DAO;

import Codigo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:28
 */
public class DAOPersona {

    public DAOConnectionLogin m_DAOConnectionLogin;

    public DAOPersona() {
        m_DAOConnectionLogin = DAOConnectionLogin.getInstancia("", "", "", "", "", "");

    }

    public Persona consultar(int idPersona) {
        Persona personaResultado = new Persona();
        try {
            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from persona where CODPERS = '" + idPersona + "'");
            while (rs.next()) {
                personaResultado.setNombre(rs.getString("NOMBREPERS"));
                personaResultado.setApellido(rs.getString("APELLIDOPERS"));
                personaResultado.setNumeroDocumento(rs.getString("NUMDOCPERS"));
                personaResultado.setCodigo(Integer.parseInt(rs.getString("CODPERS")));
                personaResultado.setTipo(Integer.parseInt(rs.getString("TIPOPERSO")));
                personaResultado.setTipoDocumento(Integer.parseInt(rs.getString("TIPODOCPERS")));
                personaResultado.setFechaNacimiento(rs.getString("FECHANACIPERS"));

                if (rs.getString("GENEROPERS") != null) {
                    personaResultado.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));
                } else {
                    personaResultado.setGenero(1);
                }
//                personaResultado.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personaResultado;
    }

    public static String armarBusqueda(String linea, String atributos) {

        StringTokenizer tokens = new StringTokenizer(linea);
        StringTokenizer tokenAtrib;
        String res = "";
        int conta = 0;
        while (tokens.hasMoreTokens()) {
            String aux = tokens.nextToken();
            if (conta > 0) {
                res = res + " or ";
            } else {
                res = "where ";
            }
            int cc = 0;
            tokenAtrib = new StringTokenizer(atributos);
            while (tokenAtrib.hasMoreTokens()) {
                if (cc > 0) {
                    res = res + " or ";
                }
                res = res + tokenAtrib.nextToken() + " like '%" + aux + "%' ";
                cc++;
            }
            conta++;
        }
        return res;
    }

    public ArrayList<Persona> consultarOptimizado(String cad) {
        String cadena1 = "Select * from persona " + armarBusqueda(cad, "NOMBREPERS APELLIDOPERS NUMDOCPERS");
        cadena1= cadena1 +" order by APELLIDOPERS desc ,NOMBREPERS desc";
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
        int mayor = 0;
        try {
           // System.out.println("CONSULTA "+cadena1);
            ResultSet rs = m_DAOConnectionLogin.executeQuery(cadena1);
            while (rs.next()) {
                Persona personaResultado = new Persona();
                personaResultado.setNombre(rs.getString("NOMBREPERS"));
                personaResultado.setApellido(rs.getString("APELLIDOPERS"));
                personaResultado.setNumeroDocumento(rs.getString("NUMDOCPERS"));
                personaResultado.setCodigo(Integer.parseInt(rs.getString("CODPERS")));
                personaResultado.setTipo(Integer.parseInt(rs.getString("TIPOPERSO")));
                personaResultado.setTipoDocumento(Integer.parseInt(rs.getString("TIPODOCPERS")));
                if (rs.getString("FECHANACIPERS") == null || rs.getString("FECHANACIPERS").equals("0000-00-00")) {
                    personaResultado.setFechaNacimiento("0000-00-00");
                } else {
                    personaResultado.setFechaNacimiento(rs.getString("FECHANACIPERS"));
                }
                if (rs.getString("GENEROPERS") != null) {
                    personaResultado.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));
                } else {
                    personaResultado.setGenero(1);
                }
                String res = rs.getString("NOMBREPERS") + "  " + rs.getString("APELLIDOPERS");
                String cad1 = cad.toUpperCase();
                String res1 = res.toUpperCase();
                int numeroCoincidencias = contarPalabras(res1, cad1);
                if (numeroCoincidencias == mayor) {
                    listaPersonas.add(0, personaResultado);
                } else if (numeroCoincidencias > mayor) {
                    listaPersonas.add(0, personaResultado);
                    mayor = numeroCoincidencias;
                } else {
                    listaPersonas.add(personaResultado);
                }

            }
        } catch (SQLException ex) {
        }
        return listaPersonas;
    }

    public int contarPalabras(String origen, String busqueda) {
        StringTokenizer token;
        token = new StringTokenizer(busqueda);
        int conta = 0;
        while (token.hasMoreTokens()) {
            String aux = token.nextToken();
            if (origen.contains(aux)) {
                conta++;
            }
        }

        return conta;
    }

    public Persona buscarPorCodigo(int codPers) {
        Persona personaResultado = new Persona();
        try {
            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from persona where CODPERS = '" + codPers + "'");
            while (rs.next()) {
                personaResultado.setNombre(rs.getString("NOMBREPERS"));
                personaResultado.setApellido(rs.getString("APELLIDOPERS"));
                personaResultado.setNumeroDocumento(rs.getString("NUMDOCPERS"));
                personaResultado.setCodigo(Integer.parseInt(rs.getString("CODPERS")));
                personaResultado.setTipo(Integer.parseInt(rs.getString("TIPOPERSO")));
                personaResultado.setTipoDocumento(Integer.parseInt(rs.getString("TIPODOCPERS")));
                personaResultado.setFechaNacimiento(rs.getString("FECHANACIPERS"));
                if (rs.getString("GENEROPERS") != null) {
                    personaResultado.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));
                } else {
                    personaResultado.setGenero(1);
                }
//                personaResultado.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personaResultado;
    }

    public ArrayList<Persona> buscarPersona(String busqueda) {
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
        try {
            //for (int i = 0; i < listaPalabras.size(); i++) {
            ResultSet rs = m_DAOConnectionLogin.executeQuery("select * from persona where NOMBREPERS like '%" + busqueda + "%' order by APELLIDOPERS");
            while (rs.next()) {
                Persona per = new Persona();
                per.setNombre(rs.getString("NOMBREPERS"));
                per.setApellido(rs.getString("APELLIDOPERS"));
                per.setNumeroDocumento(rs.getString("NUMDOCPERS"));
                per.setCodigo(Integer.parseInt(rs.getString("CODPERS")));
                per.setTipo(Integer.parseInt(rs.getString("TIPOPERSO")));
                per.setTipoDocumento(Integer.parseInt(rs.getString("TIPODOCPERS")));
                per.setFechaNacimiento(rs.getString("FECHANACIPERS"));
                if (rs.getString("GENEROPERS") != null) {
                    per.setGenero(Integer.parseInt(rs.getString("GENEROPERS")));
                } else {
                    per.setGenero(1);
                }

                listaPersonas.add(per);
            }
            //}
            //}

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonas;
    }

    public ArrayList<String> separarCadena(String cadena) {
        ArrayList<String> listaPalabras = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(cadena);
        while (tokens.hasMoreElements()) {
            listaPalabras.add(tokens.nextToken());
        }

        return listaPalabras;
    }

    public int editar(Persona persona) {
        int tuplasAfectadas = 0;
        String sql = "Update persona Set nombrepers='" + persona.getNombre() + "',"
                + "apellidopers='" + persona.getApellido() + "',"
                + "tipodocpers='" + persona.getTipoDocumento() + "',"
                + "numdocpers='" + persona.getNumeroDocumento() + "',"
                + "tipoperso='" + persona.getTipo() + "',"
                + "generopers='" + persona.getGenero() + "',";
        if (persona.getFechaNacimiento() != null) {
            sql = sql + "fechanacipers='" + persona.getFechaNacimiento() + "', ";
        } else {
            sql = sql + "fechanacipers=" + null + ", ";
        }

        sql = sql + "nitpers=" + persona.getDigitoChequeo() + ""
                + " Where CODPERS='" + persona.getCodigo() + "'";
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }

    public boolean verificarDocumentoExiste(int codPers, int tipoDocumento, String numeroDoc) {
        try {
            String sql = "Select * from persona where TIPODOCPERS ="+tipoDocumento
                    +" and NUMDOCPERS = "+numeroDoc
                    +" and codpers != "+codPers;
            ResultSet rs = m_DAOConnectionLogin.executeQuery(sql);
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int generarDocumentoTemporal(){
        int docTempo = 0;
        boolean creado= false;
        while(!creado){
            docTempo=(new Random().nextInt());
            if(docTempo<0)
                docTempo=docTempo*-1;

            if(!verificarDocumentoExiste(0, 5,docTempo+"")){
                creado=true;
            }
        }
        return docTempo;
    }

    public int insert(Persona persona) {
        int tuplasAfectadas = 0;
        if (persona.getTipo() == 1) {
            persona.setDigitoChequeo(0);
        } else {
            persona.setGenero(1);
            persona.setFechaNacimiento("1990/12/12");
        }
        String sql = "";
        if (persona.getFechaNacimiento() != null) {
            sql = "insert into persona(NOMBREPERS,APELLIDOPERS,TIPOPERSO,TIPODOCPERS,NUMDOCPERS,GENEROPERS,FECHANACIPERS,NITPERS) "
                    + "VALUES  ('" + persona.getNombre() + "','" + persona.getApellido() + "'," + persona.getTipo() + "," + persona.getTipoDocumento() + "," + persona.getNumeroDocumento() + ","
                    + "" + persona.getGenero() + ",'" + persona.getFechaNacimiento() + "'," + persona.getDigitoChequeo() + ") ";
        } else {
            sql = "insert into persona(NOMBREPERS,APELLIDOPERS,TIPOPERSO,TIPODOCPERS,NUMDOCPERS,GENEROPERS,NITPERS) "
                    + "VALUES  ('" + persona.getNombre() + "','" + persona.getApellido() + "'," + persona.getTipo() + "," + persona.getTipoDocumento() + "," + persona.getNumeroDocumento() + ","
                    + "" + persona.getGenero() + "," + persona.getDigitoChequeo() + ") ";

        }
        //String sql = "insert into persona(NOMBREPERS,APELLIDOPERS,TIPOPERSO,TIPODOCPERS,NUMDOCPERS,GENEROPERS,FECHANACIPERS,NITPERS) "
        //            + "VALUES  ('vivis','prueba',1,1,2421421,"
        //            + "2,12/02/1992,1)";
        System.out.println("SQL INSERCION: "+sql);
        tuplasAfectadas = m_DAOConnectionLogin.executeUpdate(sql);
        return tuplasAfectadas;
    }
}
