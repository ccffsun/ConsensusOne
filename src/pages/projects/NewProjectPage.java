package pages.projects;

import com.mysql.cj.result.SqlDateValueFactory;
import db.ProjectDAO;
import db.UserDAO;
import entity.Project;
import entity.User;
import pages.ActionPage;
import pages.helper.Common;
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

        try {
            System.out.println("Project Name:");
            String input = scanner.nextLine();
            p.setProjectName(input);

            System.out.println("User ID:");
            showUserData();
            input = scanner.nextLine();
            p.setUserId(Integer.valueOf(input));

            UserDAO ud = new UserDAO();
            User u = ud.getUser(p.getUserId());
            if (u == null) {
                System.out.println("User not exist!!!!!!!");
                return true;
            }

            System.out.println("Create Date:");
            input = scanner.nextLine();
            p.setStartDate(Date.valueOf(input));

            p.setStatus("active");

            if (Common.Confirm()) {
                ProjectDAO pd = new ProjectDAO();
                pd.create(p);
                System.out.println("Successfully Added new project!");
            }
        }catch (Exception ex){
            System.out.println("Failed!!!!!!!");
            ex.printStackTrace();
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
