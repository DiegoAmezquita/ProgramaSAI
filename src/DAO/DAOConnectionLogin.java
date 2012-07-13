package DAO;

import Codigo.ConnectionMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:27
 */
public class DAOConnectionLogin extends ConnectionMysql {

    private String password;
    private boolean stateLogin;
    private String userName;
    private static DAOConnectionLogin instancia;
    private static boolean resetInstance = false;
    private static boolean conectadoDB = false;

    /**
     *
     * @param user
     * @param password    password
     */
    private DAOConnectionLogin(String host, String nombreBase, String usuario, String clave, String user, String password) {
        //super("localhost", "base", "root", "");
        super(host, nombreBase, usuario, clave);
        this.userName = user;
        this.password = password;
        if (super.connection()) {
            conectadoDB=true;
            if (connectionLogin()) {
                stateLogin = true;
                resetInstance = false;
            } else {
                stateLogin = false;
                resetInstance = true;

            }
        }else{
            conectadoDB = false;
        }


    }

    public boolean close() {
        return false;
    }

    public boolean connectionLogin() {
        try {
            System.out.println("USUARIO: " + userName);
            System.out.println("CLAVE: " + password);
            String sql = "select * from login where NOMBRELOG ='" + userName + "' and CLAVELOG = '" + password + "'";
            ResultSet rs = executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DAOConnectionLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getpassword() {
        return password;
    }

    public String getuserName() {
        return userName;
    }

    public static DAOConnectionLogin getInstancia(String host, String nombreBase, String usuario, String clave, String userName, String password) {
        if (instancia == null) {
            instancia = new DAOConnectionLogin(host, nombreBase, usuario, clave, userName, password);
            if (resetInstance||!conectadoDB) {
                instancia = null;
            }
        }
        return instancia;
    }

    public boolean isStateLogin() {
        return stateLogin;
    }

    public boolean isConectadoDB() {
        return conectadoDB;
    }

    public void setConectadoDB(boolean conectadoDB) {
        this.conectadoDB = conectadoDB;
    }


}
