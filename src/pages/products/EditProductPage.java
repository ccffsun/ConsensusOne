package pages.products;

import db.ProductDAO;
import entity.Product;
import pages.ActionPage;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;

public class EditProductPage extends ActionPage {
    public EditProductPage(){
        super();
        setPageName("Edit Product");
        pd = new ProductDAO();
    }

    ProductDAO pd;
    Product product;

    @Override
    protected boolean runPage() {
        System.out.println("1. Addition Product\n2. Deduction Product\n3. back");

        try {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Product Id:");
                    input = scanner.nextLine();
                    product = pd.getProduct(Integer.valueOf(input));
                    if(product==null){
                        System.out.println("Product does not exist!!!");
                        return true;
                    }
                    System.out.println("Addition Quantity:");
                    input = scanner.nextLine();
                    additionInventory(Integer.valueOf(input));
                    break;
                case "2":
                    System.out.println("Product Id:");
                    input = scanner.nextLine();
                    product = pd.getProduct(Integer.valueOf(input));
                    if(product==null){
                        System.out.println("Product does not exist!!!");
                        return true;
                    }
                    System.out.println("Deduction Quantity:");
                    input = scanner.nextLine();
                    deductionInventory(Integer.valueOf(input));
                    break;
                case "3":
                    break;
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void additionInventory(int additionNum) {
        try {
            product.setInventory(product.getInventory() + additionNum);
            pd.update(product);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deductionInventory(int deductionNum) {
        try {
            product.setInventory(product.getInventory() - deductionNum);
            pd.update(product);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
