package pages.projects;

import db.OrderDAO;
import db.ProjectDAO;
import db.UserDAO;
import entity.Order;
import entity.Project;
import pages.ActionPage;
import pages.helper.Common;

import java.sql.Date;
import java.sql.SQLException;

public class ProjectLeaseReturnPage  extends ActionPage {
    public ProjectLeaseReturnPage(){
        super();
        setPageName("Project Lease/Return");
    }

    @Override
    protected boolean runPage() {
        Order order = new Order();
        try {
            System.out.println("Project Id:");
            String input = scanner.nextLine();
            order.setProjectId(Integer.valueOf(input));
            System.out.println("Product Id:");
            input = scanner.nextLine();
            order.setProductId(Integer.valueOf(input));
            System.out.println("Lease/Return Date:");
            input = scanner.nextLine();
            order.setDate(Date.valueOf(input));
            System.out.println("Option:\n1. Lease\n2. Return");
            input = scanner.nextLine();
            order.setType(Integer.valueOf(input));
            System.out.println("Quantity:");
            input = scanner.nextLine();
            order.setQuantity(Integer.valueOf(input));

            if (Common.Confirm(scanner)) {
                OrderDAO od = new OrderDAO();
                od.create(order);
                System.out.println("Successfully Added new Lease/Return!");
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
