package pages.products;

import db.ProductDAO;
import db.UserDAO;
import dnl.utils.text.table.TextTable;
import entity.Product;
import entity.User;
import pages.ActionPage;
import pages.helper.Common;
import ui.Constant;

import java.sql.SQLException;
import java.util.List;

public class ShowProductsPage  extends ActionPage {
    public ShowProductsPage(){
        super();
        setPageName("Show Products");
    }

    @Override
    protected boolean runPage() {
        try {
            ProductDAO pd = new ProductDAO();
            List<Product> products = pd.readAll();
            listProducts(products);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    private void listProducts(List<Product> products) {
        String[] column = new String[]{"Id", "Name", "Unit", "Price","FirstMonthPrice","Inventory"};
        Object[][] data = new Object[products.size()][column.length];
        int i = 0;
        for (Product p : products) {
            data[i][0] = p.getId();
            data[i][1] = p.getName();
            data[i][2] = p.getUnit();
            data[i][3] = p.getPrice();
            data[i][4] = p.getFirstMonthPrice();
            data[i][5] = p.getInventory();
            i++;
        }
        TextTable tt = new TextTable(column, data);
        Common.PrintCurrentDateTime();
        tt.printTable();
        System.out.println(Constant.SingleLine);
    }
}
