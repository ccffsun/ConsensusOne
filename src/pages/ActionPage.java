package pages;

import ui.Constant;

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
        System.out.println(Constant.ANSI_YELLOW+"Press Enter key to continue..."+Constant.ANSI_RESET);
        try
        {
            scanner.nextLine();
        }
        catch(Exception e)
        {}
    }
}
