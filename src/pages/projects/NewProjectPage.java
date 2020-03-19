package pages.projects;

import db.UserDAO;
import entity.Project;
import entity.User;
import pages.ActionPage;
import ui.Constant;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class NewProjectPage extends ActionPage {
    public NewProjectPage() {
        super();
        setPageName("New Project");
    }

    @Override
    protected boolean runPage() {
        Project p = new Project();

        System.out.println("Project Name:");
        String input = scanner.nextLine();
        p.setProjectName(input);

        System.out.println("User ID:");
        showUserData();
        input = scanner.nextLine();
        p.setUserId(Integer.valueOf(input));

        System.out.println("Create Date:");
        input = scanner.nextLine();
        p.setStartDate(Date.valueOf(input));

        System.out.println("Confirm? y/n");
        input = scanner.nextLine();
        if (input.toLowerCase().equals("y")) {
            System.out.println("Successfully Added new user!");
        }

        return true;
    }

    private void showUserData () {
        try {
            System.out.println(Constant.SingleLine);
            UserDAO ud = new UserDAO();
            List<User> users = ud.readAll();
            for (User u : users) {
                System.out.println(u.getId() + ". " + u.getName());
            }
            System.out.println(Constant.SingleLine);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
