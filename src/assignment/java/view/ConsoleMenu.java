/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.java.view;

import assignment.java.controller.UserController;
import assignment.java.model.UserModel;
import assignment.java.utility.ScannerUtil;
import java.sql.SQLException;

/**
 *
 * @author Thang
 */
public class ConsoleMenu {

    private static int choice;

    public static int getChoice() {
        return choice;
    }

    public static void setChoice(int choice) {
        ConsoleMenu.choice = choice;
    }

    //Menu
    public static void menu() throws SQLException{
        while (true) {
            System.out.println("===========================");
            System.out.println("|USERS MANAGERMENT PROGRAM|");
            System.out.println("===========================");
            System.out.println("|1.Create new user.       |");
            System.out.println("|2.Users list.            |");
            System.out.println("|3.Update user info.      |");
            System.out.println("|4.Delete user            |");
            System.out.println("|5.Quit                   |");
            System.out.println("===========================");
            System.out.println("Select your choice :");
            choice = ScannerUtil.getInt();
            switch (choice) {
                case 1:
                    UserController.processInsert();
                    UserController.conti();
                    break;
                case 2:
                    UserModel.viewList();
                    UserController.conti();
                    break;
                case 3:
                    UserController.processUpdate();
                    UserController.conti();
                    break;
                case 4:
                    UserController.processDelete();
                    UserController.conti();
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Invalid choice !!!");
                    break;
            }
            if (choice == 5) {
                break;
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        menu();
    }
}
