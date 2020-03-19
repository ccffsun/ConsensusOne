package pages.projects;

import pages.ActionPage;

public class CloseProjectPage  extends ActionPage {
    public CloseProjectPage(){
        super();
        setPageName("Close Project");
    }
    @Override
    protected boolean runPage() {
        return true;
    }
}
