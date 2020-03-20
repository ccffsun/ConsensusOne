import pages.ActionPage;
import pages.SelectionPage;
import pages.products.EditProductPage;
import pages.products.NewProductPage;
import pages.products.ShowProductsPage;
import pages.projects.CloseProjectPage;
import pages.projects.NewProjectPage;
import pages.projects.ProjectLeaseReturnPage;
import pages.projects.ShowProjectsPage;
import pages.settlement.SettlementPage;
import pages.users.EditUserPage;
import pages.users.NewUserPage;
import pages.users.ShowUsersPage;
import ui.Constant;

public class Main {
    public static void main(String[] args){
        SelectionPage mainPage = new SelectionPage(Constant.Welcome);

        SelectionPage manageProjects = new SelectionPage(Constant.ManageProjects);
        SelectionPage manageUsers = new SelectionPage("Manage Users");
        SelectionPage manageProducts = new SelectionPage("Manage Products");

        manageProjects.addPage(new NewProjectPage());
        manageProjects.addPage(new ShowProjectsPage());
        manageProjects.addPage(new ProjectLeaseReturnPage());
        manageProjects.addPage(new CloseProjectPage());

        manageUsers.addPage(new NewUserPage());
        manageUsers.addPage(new ShowUsersPage());
        manageUsers.addPage(new EditUserPage());

        manageProducts.addPage(new NewProductPage());
        manageProducts.addPage(new ShowProductsPage());
        manageProducts.addPage(new EditProductPage());

        mainPage.addPage(manageProjects);
        mainPage.addPage(manageUsers);
        mainPage.addPage(manageProducts);
        mainPage.addPage(new SettlementPage());

        mainPage.run();

        /*
        Page p = new Page();
        p.run();

         */
    }
}