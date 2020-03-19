package pages.users;

import pages.ActionPage;

public class ShowUsersPage  extends ActionPage {
    public ShowUsersPage(){
        super();
        setPageName("Show Users");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
