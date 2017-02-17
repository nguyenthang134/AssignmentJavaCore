/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjava1;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

/**
 *
 * @author Thang
 */
public class UpdateUserInfo {

    public UpdateUserInfo() throws SQLException {
        String name = "";
        String userName = "";
        String password = "";
        while (true) {
            Statement statement = AssignmentJavaCore.createStm();

            //Enter user ID then display 
            System.out.println("Please enter the user ID");
            int ID = new Scanner(System.in).nextInt();
            String sql = "SELECT Name,Username,Password FROM users Where ID = " + ID;

            ResultSet checkID = statement.executeQuery("SELECT * FROM users WHERE ID = " + ID);

            if (checkID.next() == false) {
                System.out.println("Sorry ,there's is no user with that ID !!!");
                break;
            }

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("Name");
                userName = rs.getString("Username");
                password = rs.getString("Password");
                System.out.printf("%-10s %s\n", "Name     :", name);
                System.out.printf("%-10s %s\n", "Username :", userName);
                System.out.printf("%-10s %s\n", "Password :", password);
            }

            //Update user info
            System.out.println("\nIf you don't want to change ,\njust keep it blank and press enter !!!");
            System.out.println("Enter new Name : ");
            String newName = new Scanner(System.in).nextLine();
            if (newName.isEmpty()) {
                newName = name;
            }
            System.out.println("Enter new Username : ");
            String newUserName = new Scanner(System.in).nextLine();
            if (newUserName.isEmpty()) {
                newUserName = userName;
            }
            System.out.println("Enter new Password : ");
            String newPassword = new Scanner(System.in).nextLine();
            if (newPassword.isEmpty()) {
                newPassword = password;
            }

            try {
                String update = "UPDATE users SET Name = '" + newName + "',Username = '" + newUserName + "',Password = '" + newPassword + "' WHERE ID = " + ID;
                statement.execute(update);
                System.out.println("Update successful !!!");
            } catch (Exception e) {
                System.err.println("Cannot update !!!");
            }
            break;
        }
    }
}
