package ui;

public class Util {
    public static void PressEnterKeyToContinue(){
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
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
