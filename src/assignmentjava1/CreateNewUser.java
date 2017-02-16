/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjava1;

import assignment.java.utility.ScannerUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class CreateNewUser {

    private static String sql = ""; //SQL query

    //First choice 
    public CreateNewUser() throws SQLException {

        Statement stm = AssignmentJavaCore.createStm();

        System.out.println("Enter name : ");
        String name = ScannerUtil.getString();
        System.out.println("Enter username : ");
        String userName = ScannerUtil.getString();
        System.out.println("Enter password : ");
        String password = ScannerUtil.getString();

        sql = "INSERT INTO users (Name,Username,Password) VALUES ('" + name + "','" + userName + "','" + password + "')";

        //Validate username and password
        ResultSet validate = stm.executeQuery("SELECT * FROM users WHERE Username = '" + userName + "'");

        if (validate.next() == true) {
            System.out.println("Sorry ,usename already exists !!!");
        } else {
            stm.execute(sql);
            System.out.println("Create successful !");
        }
    }

}
