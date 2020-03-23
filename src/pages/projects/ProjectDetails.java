package pages.projects;

import db.OrderDAO;
import dnl.utils.text.table.TextTable;
import entity.Order;
import pages.ActionPage;
import pages.helper.Common;
import ui.Constant;

import java.util.List;

public class ProjectDetails extends ActionPage {
    public ProjectDetails(){
        super();
        setPageName(Constant.ProjectDetails);
    }

    @Override
    protected boolean runPage() {
        try {
            System.out.println(Constant.ProjectId);
            int projectId = Integer.valueOf(scanner.nextLine());
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getOrdersByProjectId(projectId);
            String[] column = new String[]{"Date", "Product Id", "Type", "Quantity"};
            Object[][] data = new Object[orders.size()][column.length];
            int i = 0;
            for(Order o : orders){
                data[i][0] = o.getDate();
                data[i][1] = o.getProductId();
                data[i][2] = o.getType() == 1? "Lease":"Return";
                data[i][3] = o.getQuantity();
                i++;
            }
            TextTable tt = new TextTable(column, data);
            Common.PrintCurrentDateTime();
            tt.printTable();
            System.out.println(Constant.SingleLine);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return true;
    }
}
