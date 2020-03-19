package pages.projects;

import pages.ActionPage;

public class ShowProjectsPage  extends ActionPage {
    public ShowProjectsPage(){
        super();
        setPageName("Show Projects");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
