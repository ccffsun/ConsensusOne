package ui;

import java.util.Scanner;

public class Util {
    public static void PressEnterKeyToContinue(){
        System.out.println("Press Enter key to continue...");
        try
        {
            new Scanner(System.in).nextLine();
        }
        catch(Exception e)
        {}
    }

    public static void PageHeader(String header){
        System.out.println(Constant.DoubleLine);
        System.out.println(header);
        System.out.println(Constant.DoubleLine);
    }
}
