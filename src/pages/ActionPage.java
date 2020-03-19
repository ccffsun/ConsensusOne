package pages;

import java.util.Scanner;

public abstract class ActionPage extends Page{
    public ActionPage() {
        super();
    }

    @Override
    public void run() {
        super.run();
        pressEnterKeyToContinue();
    }

    private void pressEnterKeyToContinue(){
        System.out.println("Press Enter key to continue...");
        try
        {
            new Scanner(System.in).nextLine();
        }
        catch(Exception e)
        {}
    }
}
