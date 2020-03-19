package pages;

import ui.Util;

public abstract class ActionPage extends Page{
    public ActionPage() {
        super();
    }

    @Override
    public void run() {
        super.run();
        Util.PressEnterKeyToContinue();
    }
}
