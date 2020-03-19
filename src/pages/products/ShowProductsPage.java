package pages.products;

import pages.ActionPage;

public class ShowProductsPage  extends ActionPage {
    public ShowProductsPage(){
        super();
        setPageName("Show Products");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
