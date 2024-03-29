/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author vhsf0
 */
public class JDBCUtil {
    private static String url;
    private static String username;
    private static String password;
    
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/properties/configuracaobd.properties");
    
    protected Connection connection = null;
    protected PreparedStatement pstdata = null;
    protected ResultSet rsdata = null;
    

    /**
     * Initializes the data source.
     *
     * @param fileName the name of the property file that contains the database
     * driver, URL, username, and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(File fileName)
            throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        
        if (driver != null) {
            Class.forName(driver);
        }
    }
    
    public boolean CriaConexao() {
        try {
            init(config_file);
            connection = getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("DB Name: " + dbmt.getDatabaseProductName());
            System.out.println("DB Version: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Driver Version: " + dbmt.getDriverVersion());
            System.out.println("User: " + dbmt.getUserName());

            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("JDBC driver loading failed." + erro);
        } catch (IOException erro) {
            System.out.println("Configuration file loading failed." + erro);
        } catch (SQLException erro) {
            System.out.println("SQL command failed = " + erro);
        }
        return false;
    }

    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Error trying to close the connection = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     *
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
