import pages.ActionPage;
import pages.SelectionPage;
import pages.products.EditProductPage;
import pages.products.NewProductPage;
import pages.products.ShowProductsPage;
import pages.projects.*;
import pages.settlement.SettlementPage;
import pages.users.EditUserPage;
import pages.users.NewUserPage;
import pages.users.ShowUsersPage;
import ui.Constant;

public class Main {
    public static void main(String[] args){
        SelectionPage mainPage = new SelectionPage(Constant.Welcome);

        SelectionPage manageProjects = new SelectionPage(Constant.ManageProjects);
        SelectionPage manageUsers = new SelectionPage(Constant.ManageUsers);
        SelectionPage manageProducts = new SelectionPage(Constant.ManageProducts);

        manageProjects.addPage(new NewProjectPage());
        manageProjects.addPage(new ShowProjectsPage());
        manageProjects.addPage(new ProjectLeaseReturnPage());
        manageProjects.addPage(new CloseProjectPage());
        manageProjects.addPage(new ProjectDetails());

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
    }
}