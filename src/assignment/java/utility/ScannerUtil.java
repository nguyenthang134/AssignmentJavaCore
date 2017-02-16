/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.java.utility;

import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class ScannerUtil {

    public static int getInt() {
        int input = 0;
        while (true) {
            try {
                input = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                System.err.println("Please enter number type !");
            }
        }

        return input;
    }

    public static String getString() {
        String inputString = "";
        while (true) {
            try {
                inputString = new Scanner(System.in).nextLine();
                if (inputString.trim().length() > 0) {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Please enter string type !");
            }
        }

        return inputString;
    }

}
