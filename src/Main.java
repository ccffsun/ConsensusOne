import pages.ActionPage;
import pages.SelectionPage;
import pages.projects.NewProjectPage;

public class Main {
    public static void main(String[] args){
        SelectionPage mainPage = new SelectionPage("Welcome");

        SelectionPage manageProjects = new SelectionPage("Manage Projects");
        SelectionPage manageUsers = new SelectionPage("Manage Users");
        SelectionPage manageProducts = new SelectionPage("Manage Products");

        ActionPage newProject = new NewProjectPage();

        manageProjects.addPage(newProject);

        mainPage.addPage(manageProjects);
        mainPage.addPage(manageUsers);
        mainPage.addPage(manageProducts);

        mainPage.run();

        /*
        Page p = new Page();
        p.run();

         */
    }
}