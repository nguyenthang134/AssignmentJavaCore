/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjava1;

import assignment.java.utility.ScannerUtil;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

/**
 *
 * @author Thang
 */
public class DeleteUser {

    public DeleteUser() throws SQLException {
        int confirmDelete;
        while (true) {
            Statement statement = AssignmentJavaCore.createStm();

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
                String name = rs.getString("Name");
                String userName = rs.getString("Username");
                String password = rs.getString("Password");
                System.out.printf("%-10s %s\n", "Name     :", name);
                System.out.printf("%-10s %s\n", "Username :", userName);
                System.out.printf("%-10s %s\n", "Password :", password);
            }

            String delete = "DELETE FROM users WHERE ID = " + ID;
            System.out.println("Are you sure ? 1.YES|2.NO");
            confirmDelete = ScannerUtil.getInt();
            try {
                if (confirmDelete == 1) {
                    statement.execute(delete);
                    System.out.println("Delete successful !!!");
                } else if (confirmDelete == 2) {
                    break;
                } else {
                    System.err.println("Invalid choice !!!");
                }
            } catch (Exception e) {
                    System.err.println("Cannot delete !!!");
            }
            break;
        }
    }
}
