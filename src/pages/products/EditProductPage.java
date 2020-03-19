package pages.products;

import pages.ActionPage;

public class EditProductPage extends ActionPage {
    public EditProductPage(){
        super();
        setPageName("Edit Product");
    }

    @Override
    protected boolean runPage() {
        return true;
    }
}
