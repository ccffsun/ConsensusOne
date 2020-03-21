package pages.projects;

import db.OrderDAO;
import db.ProductDAO;
import db.ProjectBookkeepingDAO;
import db.ProjectDAO;
import entity.Order;
import entity.Product;
import entity.Project;
import entity.ProjectBookkeeping;
import pages.ActionPage;
import pages.helper.Common;

import java.sql.Date;
import java.sql.SQLException;

public class ProjectLeaseReturnPage extends ActionPage {
    ProjectDAO projectDAO;
    ProductDAO productDAO;
    ProjectBookkeepingDAO projectBookkeepingDAO;
    OrderDAO orderDAO;

    public ProjectLeaseReturnPage() {
        super();
        setPageName("Project Lease/Return");
    }

    @Override
    protected boolean runPage() {
        Order order = new Order();
        int projectId;
        int productId;
        projectDAO = new ProjectDAO();
        productDAO = new ProductDAO();
        projectBookkeepingDAO = new ProjectBookkeepingDAO();
        orderDAO = new OrderDAO();

        int quantity;

        try {
            System.out.println("Project Id:");
            String input = scanner.nextLine();
            projectId = Integer.valueOf(input);
            Project project = projectDAO.getProject(projectId);
            if (project == null) {
                System.out.println("Project does not exist!!!");
                return true;
            }
            order.setProjectId(projectId);


            System.out.println("Product Id:");
            input = scanner.nextLine();
            productId = Integer.valueOf(input);
            Product product = productDAO.getProduct(productId);
            if (product == null) {
                System.out.println("Product does not exist!!!");
                return true;
            }
            order.setProductId(productId);

            System.out.println("Lease/Return Date:");
            input = scanner.nextLine();
            order.setDate(Date.valueOf(input));

            System.out.println("Option:\n1. Lease\n2. Return");
            input = scanner.nextLine();
            order.setType(Integer.valueOf(input));

            System.out.println("Quantity:");
            input = scanner.nextLine();
            quantity = Integer.valueOf(input);
            order.setQuantity(quantity);

            if(updateTables(order, project, product)){
                System.out.println("Successfully Added new Leased/Return!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Will update Order, Product and ProjectBookkeeping tables
    //return true if update successfully, otherwise return false
    private boolean updateTables(Order order, Project project, Product product) {
        try {
            ProjectBookkeeping projectBookkeeping = projectBookkeepingDAO.getProjectBookkeeping(project.getId(), product.getId());
            if (projectBookkeeping == null) {
                projectBookkeeping = new ProjectBookkeeping();
                projectBookkeeping.setProjectId(project.getId());
                projectBookkeeping.setProductId(product.getId());
                projectBookkeeping.setQuantity(0);
            }

            if (order.getType() == 1 && order.getQuantity() > product.getInventory()) {
                System.out.println("Inventory ran out!!!!");
                return false;
            } else if (order.getType() == 2 && order.getQuantity() > projectBookkeeping.getQuantity()) {
                System.out.println("Return Number More Than Lease Number!!!!");
                return false;
            }

            int changedQuantity = order.getQuantity();
            int currentQuantity = projectBookkeeping.getQuantity();
            int inventory = product.getInventory();
            if (order.getType() == 1) {
                projectBookkeeping.setQuantity(currentQuantity + changedQuantity);
                product.setInventory(inventory - changedQuantity);
            } else {
                projectBookkeeping.setQuantity(currentQuantity - changedQuantity);
                product.setInventory(inventory + changedQuantity);
            }

            if(Common.Confirm(scanner)){
                productDAO.update(product);
                if(projectBookkeeping.getId() == 0) {
                    projectBookkeepingDAO.create(projectBookkeeping);
                }else {
                    projectBookkeepingDAO.update(projectBookkeeping);
                }
                orderDAO.create(order);
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
