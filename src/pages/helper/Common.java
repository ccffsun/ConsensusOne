package pages.helper;

import ui.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        System.out.println(Constant.ANSI_GREEN+dateFormat.format(date)+Constant.ANSI_RESET);
    }

    public static final int LEASE = 1;
    public static final int RETURN =2;

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static int DaysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
