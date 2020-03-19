package pages.settlement;

import pages.ActionPage;

public class SettlementPage  extends ActionPage {
    public SettlementPage (){
        super();
        setPageName("Settlement");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
