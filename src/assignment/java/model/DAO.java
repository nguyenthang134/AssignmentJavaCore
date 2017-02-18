/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thang
 */
public class DAO {

    private static final String CONNECTION_URL_PREFIX = "jdbc:mysql://";
    private static final String HOST = "localhost:3306/";
    private static final String DATABASE = "Users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static String getCONNECTION_URL_PREFIX() {
        return CONNECTION_URL_PREFIX;
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    //Connection to mysql
    public static Connection connect() throws SQLException {
        String url = CONNECTION_URL_PREFIX + HOST + DATABASE;
        Connection conn = DriverManager.getConnection(url, USERNAME, PASSWORD);

        return conn;
    }

    //Create mysql statement
    public static Statement createStm() throws SQLException {
        Statement statement = connect().createStatement();
        return statement;
    }
}
