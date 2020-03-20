package pages.helper;

import java.util.Scanner;

public class Common {
    public static boolean Confirm(){
        System.out.println("Confirm? y/n");
        String input = new Scanner(System.in).nextLine();
        return input.toLowerCase().equals("y") || input.toLowerCase().equals("yes");
    }
}
