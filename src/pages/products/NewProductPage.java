package pages.products;

import db.ProductDAO;
import entity.Product;
import pages.ActionPage;
import pages.helper.Common;
import ui.Constant;


public class NewProductPage extends ActionPage {
    public NewProductPage(){
        super();
        setPageName(Constant.NewProduct);
    }

    @Override
    protected boolean runPage() {
        Product p = new Product();

        try {
            System.out.println(Constant.ProductName);
            String input = scanner.nextLine();
            p.setName(input);

            System.out.println(Constant.ProductUnit);
            input = scanner.nextLine();
            p.setUnit(input);

            System.out.println(Constant.ProductPrice);
            input = scanner.nextLine();
            p.setPrice(Double.valueOf(input));

            System.out.println(Constant.FirstMonthPrice);
            input = scanner.nextLine();
            p.setFirstMonthPrice(Double.valueOf(input));

            System.out.println(Constant.Inventory);
            input = scanner.nextLine();
            p.setInventory(Integer.valueOf(input));



            if (Common.Confirm(scanner)) {
                ProductDAO pd = new ProductDAO();
                pd.create(p);
                System.out.println(Constant.Succeeded);
            }
        }catch (Exception ex){
            System.out.println(Constant.Failed);
            ex.printStackTrace();
        }

        return true;
    }
}
