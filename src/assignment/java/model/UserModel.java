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

    //Create new user
    public static void insert(User user) {
        sql = "INSERT INTO users (Name,Username,Password) VALUES ('" + user.getName() + "','" + user.getUserName() + "','" + user.getPassword() + "')";
        try {
            Statement stm = DAO.createStm();

            //Validate username and password
            ResultSet validate = stm.executeQuery("SELECT * FROM users WHERE Username = '" + user.getUserName() + "'");

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

    public static void updateInfo(User user) {
        try {
            String name = "";
            String userName = "";
            String password = "";
            while (true) {
                Statement statement = DAO.createStm();
                String sql = "SELECT Name,Username,Password FROM users Where ID = " + user.getID();

                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    name = rs.getString("Name");
                    userName = rs.getString("Username");
                    password = rs.getString("Password");
                }

                //Update user info
                if (user.getName().isEmpty()) {
                    user.setName(name);
                }
                if (user.getUserName().isEmpty()) {
                    user.setUserName(userName);
                }
                if (user.getPassword().isEmpty()) {
                    user.setPassword(password);
                }

                String update = "UPDATE users SET Name = '" + user.getName() + "',Username = '" + user.getUserName() + "',Password = '" + user.getPassword() + "' WHERE ID = " + user.getID();
                statement.execute(update);
                System.out.println("\nUpdate successful !!!");
                break;
            }
        } catch (Exception e) {
            System.err.println("\nCannot update !!!");
        }
    }

    //Delete user
    public static void deleteUser(User user) {
        try {
            int confirmDelete;
            Statement stm = DAO.createStm();
            while (true) {
                String delete = "DELETE FROM users WHERE ID = " + user.getID();
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

    //Search user
    //search by ID
    public static void searchByID(User user) {
        while (true) {
            try {
                sql = "SELECT * FROM users WHERE ID LIKE '%" + user.getID() + "%'";
                Statement stm = DAO.createStm();

                ResultSet checkID = stm.executeQuery("SELECT * FROM users WHERE ID LIKE '%" + user.getID() + "%'");
                if (checkID.next() == false) {
                    System.err.println("Sorry ,there's is no user with that ID !!!");
                    break;
                }

                String leftAlignFormat = "| %-15s | %-35s | %-35s | %-25s |%n";
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
                System.out.format(leftAlignFormat, "ID", "Name", "Username", "Password");
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");

                ResultSet viewByID = stm.executeQuery(sql);
                while (viewByID.next()) {
                    String id = viewByID.getString("ID");
                    String name = viewByID.getString("Name");
                    String userName = viewByID.getString("Username");
                    String password = viewByID.getString("Password");
                    System.out.format(leftAlignFormat, id, name, userName, password);
                }
            } catch (Exception e) {
                System.err.println("Cannot search !!!");
            }
            System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
            break;
        }
    }

    //search by name
    public static void searchByName(User user) {
        while (true) {
            try {
                sql = "SELECT * FROM users WHERE Name LIKE '%" + user.getName() + "%'";
                Statement stm = DAO.createStm();

                ResultSet checkName = stm.executeQuery("SELECT * FROM users WHERE Name LIKE '%" + user.getName() + "%'");
                if (checkName.next() == false) {
                    System.err.println("Sorry ,there's is no user with that name !!!");
                    break;
                }

                String leftAlignFormat = "| %-15s | %-35s | %-35s | %-25s |%n";
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
                System.out.format(leftAlignFormat, "ID", "Name", "Username", "Password");
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");

                ResultSet viewByName = stm.executeQuery(sql);
                while (viewByName.next()) {
                    String id = viewByName.getString("ID");
                    String name = viewByName.getString("Name");
                    String userName = viewByName.getString("Username");
                    String password = viewByName.getString("Password");
                    System.out.format(leftAlignFormat, id, name, userName, password);
                }
            } catch (Exception e) {
                System.err.println("Cannot search !!!");
            }
            System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
            break;
        }
    }

    //search by username
    public static void searchByUserName(User user) {
        while (true) {
            try {
                sql = "SELECT * FROM users WHERE Username LIKE '%" + user.getUserName() + "%'";
                Statement stm = DAO.createStm();

                ResultSet checkUserName = stm.executeQuery("SELECT * FROM users WHERE Username LIKE '%" + user.getUserName() + "%'");
                if (checkUserName.next() == false) {
                    System.err.println("Sorry ,there's is no user with that username !!!");
                    break;
                }

                String leftAlignFormat = "| %-15s | %-35s | %-35s | %-25s |%n";
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
                System.out.format(leftAlignFormat, "ID", "Name", "Username", "Password");
                System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");

                ResultSet viewByUserName = stm.executeQuery(sql);
                while (viewByUserName.next()) {
                    String id = viewByUserName.getString("ID");
                    String name = viewByUserName.getString("Name");
                    String userName = viewByUserName.getString("Username");
                    String password = viewByUserName.getString("Password");
                    System.out.format(leftAlignFormat, id, name, userName, password);
                }
            } catch (Exception e) {
                System.err.println("Cannot search !!!");
            }
            System.out.format("+-----------------+-------------------------------------+-------------------------------------+---------------------------+%n");
            break;
        }
    }
}
