package pages.users;

import pages.ActionPage;

public class NewUserPage  extends ActionPage {
    public NewUserPage(){
        super();
        setPageName("New User");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
