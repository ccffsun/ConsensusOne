package pages.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Common {
    public static boolean Confirm(Scanner scanner){
        System.out.println("Confirm? y/n");
        String input = scanner.nextLine();
        return input.toLowerCase().equals("y") || input.toLowerCase().equals("yes");
    }

    public static void PrintCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }
}
