package pages.products;

import pages.ActionPage;

public class NewProductPage extends ActionPage {
    public NewProductPage(){
        super();
        setPageName("New Product");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
