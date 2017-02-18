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
                System.err.println("Invalid choice !!!");
            }
        }

        return input;
    }
    
    public static String getStr(){
        String input = "";
        while(true){
            input = new Scanner(System.in).nextLine();
            if(input.isEmpty()){
                System.err.println("This section cannot be blank !!!");
            } else {
                break;
            }
        }
        return input;
    }
}
