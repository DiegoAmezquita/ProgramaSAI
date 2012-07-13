package Codigo;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @version 1.0
 * @created 13-Mar-2011 18:48:27
 */
public class ConnectionMysql {

    private String nameDatabase;
    private String passwordDatabase;
    private String pathDatabase;
    private boolean state;
    private String userDatabase;
    private Connection conexion;
    private Statement st;

    public ConnectionMysql() {
    }

    /**
     *
     * @param path
     * @param nameDB
     * @param user
     * @param password    password
     */
    public ConnectionMysql(String path, String nameDB, String user, String password) {
        userDatabase = user;
        passwordDatabase = password;
        pathDatabase = path;
        nameDatabase = nameDB;
    }

    public boolean closeConection() {
        return false;
    }

    public boolean connection() {
        try {         

            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(userDatabase);
            dataSource.setPassword(passwordDatabase);
            dataSource.setDatabaseName(nameDatabase);
            dataSource.setServerName(pathDatabase);
         //   dataSource.setEncoding("ISO-8859-1");
          //  dataSource.setConnectionCollation("latin1_spanish_ci");
            conexion = dataSource.getConnection();
            st = conexion.createStatement();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DATOS ERRONEOS, REVIZAR LA CONFIGURACIÓN DE LA CONEXION CON LA BASE DE DATOS");
            return false;
        }

    }

    /**
     *
     * @param sql    sql
     */
    public ResultSet executeQuery(String sql) {
        try {
        	System.out.println("SQL "+sql);
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sql    sql
     */
    public int executeUpdate(String sql) {
        int tuplasAfectadas = 0;
        try {
        	System.out.println("SQL "+sql);
            PreparedStatement stmt = conexion.prepareStatement(sql);
            tuplasAfectadas = stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tuplasAfectadas;
    }
    
    public int devolverUltimoID(){
    	int ultimoID=0;
    	 try {
             ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
             while (rs.next()) {                
                ultimoID = (Integer.parseInt(rs.getString("last_insert_id()")));                
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
             Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
         }
    	return ultimoID;
    }
    
    
    public int executeUpdateImage(String path, int idProducto) {
        int tuplasAfectadas = 0;
        try {
        	File file = new File(path);
        	FileInputStream is = new FileInputStream(file);

        	PreparedStatement st = conexion.prepareStatement("insert into imagenproducto(idproducto,imagen) values(?,?)");

//        	st.setString(1, "1");
        	st.setString(1, idProducto+"");
        	st.setBinaryStream(2, is,(int) file.length());
        	st.execute();
        	is.close();
        	st.close();       
         

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tuplasAfectadas;
    }
    
    

    public String getnameDatabase() {
        return nameDatabase;
    }

    public String getpasswordDatabase() {
        return passwordDatabase;
    }

    public String getpathDatabase() {
        return pathDatabase;
    }

    public String getuserDatabase() {
        return userDatabase;
    }

    public boolean isstate() {
        return state;
    }
}
