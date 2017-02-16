/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjava1;



import assignment.java.utility.ScannerUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class AssignmentJavaCore {

    private static int conti;
    private static int choice = 6;

    //Ask do you want to continue or not 
    public static void conti() {
        System.out.println("Do you want to continue ?  1.YES|2.NO");
        conti = new Scanner(System.in).nextInt();
        if (conti == 2) {
            choice = 5;
        } else {
            choice = 6;
        }
    }

    //Menu
    public static void menu() throws SQLException {
        System.out.println("USERS MANAGERMENT PROGRAM");
        while (choice != 5) {
            System.out.println("-----------MENU----------");
            System.out.println("1.Create new user.");
            System.out.println("2.Users list.");
            System.out.println("3.Update user info.");
            System.out.println("4.Delete user");
            System.out.println("5.Quit");
            System.out.println("Select your choice :");
            choice = ScannerUtil.getInt();
            switch (choice) {
                case 1:
                    new CreateNewUser();
                    conti();
                    break;
                case 2:
                    new ViewUsersList();
                    conti();
                    break;
                case 3:
                    System.out.println("3");
                    conti();
                    break;
                case 4:
                    System.out.println("4");
                    conti();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice !");
                    break;
            }
        }
    }
    //Connection to mysql
    public static Connection connect() throws SQLException {
        String url = Config.getCONNECTION_URL_PREFIX() + Config.getHOST() + Config.getDATABASE();
        Connection conn = DriverManager.getConnection(url, Config.getUSERNAME(), Config.getPASSWORD());
        
        return conn;
    }
    
    //Create mysql statement
    public static Statement createStm() throws SQLException{
        Statement statement = connect().createStatement();
        return statement;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        try {
            menu();
        } catch (SQLException e) {
            System.out.println("Something went wrong ,");
            System.out.println("can't connect to the Database !");
        }
    }
}
