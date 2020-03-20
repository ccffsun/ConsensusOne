package pages.products;

import db.ProductDAO;
import db.ProjectDAO;
import db.UserDAO;
import entity.Product;
import entity.Project;
import entity.User;
import pages.ActionPage;
import pages.helper.Common;

import java.sql.Date;

public class NewProductPage extends ActionPage {
    public NewProductPage(){
        super();
        setPageName("New Product");
    }

    @Override
    protected boolean runPage() {
        Product p = new Product();

        try {
            System.out.println("Product Name:");
            String input = scanner.nextLine();
            p.setName(input);

            System.out.println("Product Unit:");
            input = scanner.nextLine();
            p.setUnit(input);

            System.out.println("Product Price:");
            input = scanner.nextLine();
            p.setPrice(Double.valueOf(input));

            System.out.println("First Month Price");
            input = scanner.nextLine();
            p.setFirstMonthPrice(Double.valueOf(input));

            System.out.println("Inventory");
            input = scanner.nextLine();
            p.setInventory(Integer.valueOf(input));



            if (Common.Confirm()) {
                ProductDAO pd = new ProductDAO();
                pd.create(p);
                System.out.println("Successfully Added new product!");
            }
        }catch (Exception ex){
            System.out.println("Failed!!!!!!!");
            ex.printStackTrace();
        }

        return true;
    }
}
