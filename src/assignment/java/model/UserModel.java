/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.java.model;

import assignment.java.utility.ScannerUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class UserModel {

    private static String sql;

    //Get primitive values in SQL table
    private static String name = "";
    private static String userName = "";
    private static String password = "";

    public static String getName() {
        return name;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    //Create new user
    public static void insert(String name, String userName, String password) {
        sql = "INSERT INTO users (Name,Username,Password) VALUES ('" + name + "','" + userName + "','" + password + "')";
        try {
            Statement stm = DAO.createStm();

            //Validate username and password
            ResultSet validate = stm.executeQuery("SELECT * FROM users WHERE Username = '" + userName + "'");

            if (validate.next() == true) {
                System.err.println("Sorry ,usename already exists !!!");
            } else {
                stm.execute(sql);
                System.out.println("\nCreate successful !!!");
            }
        } catch (SQLException e) {
            System.err.println("\nError while creating !!!");
        }
    }

    //View user list
    public static void viewList() {
        sql = "SELECT * FROM users";
        String leftAlignFormat = "| %-15s | %-35s | %-35s | %-25s |%n";
        System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
        System.out.format(leftAlignFormat, "ID", "Name", "Username", "Password");
        System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
        try {
            Statement stm = DAO.createStm();

            ResultSet viewList = stm.executeQuery(sql);
            while (viewList.next()) {
                String id = viewList.getString("ID");
                String name = viewList.getString("Name");
                String userName = viewList.getString("Username");
                String password = viewList.getString("Password");
                System.out.format(leftAlignFormat, id, name, userName, password);
            }
        } catch (SQLException ex) {
            System.err.println("\nCannot view list !!!");
        }
        System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
    }

    //Update user info
    public static void updateInfo(int ID, String newName, String newUserName, String newPassword) {
        try {
            String name = "";
            String userName = "";
            String password = "";
            while (true) {
                Statement statement = DAO.createStm();
                String sql = "SELECT Name,Username,Password FROM users Where ID = " + ID;

                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    name = rs.getString("Name");
                    userName = rs.getString("Username");
                    password = rs.getString("Password");
                }

                //Update user info
                if (newName.isEmpty()) {
                    newName = name;
                }
                if (newUserName.isEmpty()) {
                    newUserName = userName;
                }
                if (newPassword.isEmpty()) {
                    newPassword = password;
                }

                String update = "UPDATE users SET Name = '" + newName + "',Username = '" + newUserName + "',Password = '" + newPassword + "' WHERE ID = " + ID;
                statement.execute(update);
                System.out.println("\nUpdate successful !!!");
                break;
            }
        } catch (Exception e) {
            System.err.println("\nCannot update !!!");
        }
    }

    public static void deleteUser(int ID) {
        try {
            int confirmDelete;
            Statement stm = DAO.createStm();
            while (true) {
                String delete = "DELETE FROM users WHERE ID = " + ID;
                System.out.println("\nAre you sure ? 1.YES|2.NO");
                confirmDelete = ScannerUtil.getInt();
                    if (confirmDelete == 1) {
                        stm.execute(delete);
                        System.out.println("\nDelete successful !!!");
                    } else if (confirmDelete == 2) {
                        break;
                    } else {
                        System.err.println("\nInvalid choice !!!");
                    }
                break;
            }
        } catch (Exception e) {
            System.err.println("Cannot delete !!!");
        }
    }
}
