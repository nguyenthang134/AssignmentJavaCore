/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.java.controller;

import assignment.java.model.DAO;
import assignment.java.model.User;
import assignment.java.model.UserModel;
import assignment.java.utility.ScannerUtil;
import assignment.java.view.ConsoleMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class UserController {

    private static int conti;

    //Ask do you want to continue or not 
    public static void conti() {
        System.out.println("\nDo you want to continue ?  1.YES|2.NO");
        while (true) {
            conti = ScannerUtil.getInt();
            if (conti == 2) {
                ConsoleMenu.setChoice(6);
                break;
            }
            if (conti == 1) {
                ConsoleMenu.setChoice(7);
                break;
            } else {
                System.err.println("Invalid choice !!!");
            }
        }
    }

    //Create new user
    public static void processInsert() {
        System.out.println("Enter name : ");
        String name = ScannerUtil.getStr();

        System.out.println("Enter username : ");
        String userName = ScannerUtil.getStr();

        System.out.println("Enter password : ");
        String password = ScannerUtil.getStr();

        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(password);
        UserModel.insert(user);
    }

    //Update user info
    public static void processUpdate() throws SQLException {
        while (true) {
            Statement statement = DAO.createStm();

            //Enter user ID then display 
            System.out.println("Please enter the user ID");
            int ID = new Scanner(System.in).nextInt();
            String sql = "SELECT Name,Username,Password FROM users Where ID = " + ID;

            ResultSet checkID = statement.executeQuery("SELECT * FROM users WHERE ID = " + ID);

            if (checkID.next() == false) {
                System.err.println("Sorry ,there's is no user with that ID !!!");
                break;
            }

            ResultSet rs = statement.executeQuery(sql);
            System.out.println("");
            while (rs.next()) {
                String name = rs.getString("Name");
                String userName = rs.getString("Username");
                String password = rs.getString("Password");
                System.out.printf("%-10s %s\n", "Name     :", name);
                System.out.printf("%-10s %s\n", "Username :", userName);
                System.out.printf("%-10s %s\n", "Password :", password);
            }

            System.out.println("\nIf you don't want to change ,\njust keep it blank and press enter !!!");
            System.out.println("\nEnter new Name : ");
            String newName = new Scanner(System.in).nextLine();
            System.out.println("Enter new Username : ");
            String newUserName = new Scanner(System.in).nextLine();
            System.out.println("Enter new Password : ");
            String newPassword = new Scanner(System.in).nextLine();

            User user = new User();
            user.setID(ID);
            user.setName(newName);
            user.setUserName(newUserName);
            user.setPassword(newPassword);

            UserModel.updateInfo(user);

            break;
        }
    }

    //Delete user
    public static void processDelete() {
        try {
            while (true) {
                Statement statement = DAO.createStm();

                System.out.println("Please enter the user ID");
                int ID = new Scanner(System.in).nextInt();
                String sql = "SELECT Name,Username,Password FROM users Where ID = " + ID;

                ResultSet checkID = statement.executeQuery("SELECT * FROM users WHERE ID = " + ID);
                if (checkID.next() == false) {
                    System.err.println("Sorry ,there's is no user with that ID !!!");
                    break;
                }

                System.out.println("");
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    String name = rs.getString("Name");
                    String userName = rs.getString("Username");
                    String password = rs.getString("Password");
                    System.out.printf("%-10s %s\n", "Name     :", name);
                    System.out.printf("%-10s %s\n", "Username :", userName);
                    System.out.printf("%-10s %s\n", "Password :", password);
                }

                User user = new User();
                user.setID(ID);
                UserModel.deleteUser(user);
                break;
            }
        } catch (Exception e) {
            System.err.println("Cannot delete !!!");
        }
    }

    //Search user
    public static void processSearch() {
        while (true) {
            User user = new User();
            try {
                System.out.println("What do you want to search :");
                System.out.println("1.ID");
                System.out.println("2.Name");
                System.out.println("3.Username");
                System.out.println("4.Back");

                int choice = ScannerUtil.getInt();

                if (choice == 1) {
                    System.out.println("Enter ID :");
                    int ID = ScannerUtil.getInt();
                    user.setID(ID);
                    UserModel.searchByID(user);
                } else if (choice == 2) {
                    System.out.println("Enter name :");
                    String name = ScannerUtil.getStr();
                    user.setName(name);
                    UserModel.searchByName(user);
                } else if (choice == 3) {
                    System.out.println("Enter Username :");
                    String userName = ScannerUtil.getStr();
                    user.setUserName(userName);
                    UserModel.searchByUserName(user);
                } else if (choice == 4) {
                    break;
                } else {
                    System.err.println("Invalid choice !!!");
                }
            } catch (Exception e) {
                System.err.println("Cannot search !!!");
            }
        }
    }
}
