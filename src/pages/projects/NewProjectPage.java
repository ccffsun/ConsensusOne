package pages.projects;

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
        setPageName(Constant.NewProject);
    }

    @Override
    protected boolean runPage() {
        Project p = new Project();

        try {
            System.out.println(Constant.ProjectName);
            String input = scanner.nextLine();
            p.setProjectName(input);

            System.out.println(Constant.UserId);
            showUserData();
            input = scanner.nextLine();
            p.setUserId(Integer.valueOf(input));

            UserDAO ud = new UserDAO();
            User u = ud.getUser(p.getUserId());
            if (u == null) {
                System.out.println(Constant.UserNotExist);
                return true;
            }

            System.out.println("Create Date:");
            input = scanner.nextLine();
            p.setStartDate(Date.valueOf(input));

            p.setStatus(Constant.ACTIVE);

            if (Common.Confirm(scanner)) {
                ProjectDAO pd = new ProjectDAO();
                pd.create(p);
                System.out.println(Constant.Succeeded);
            }
        }catch (Exception ex){
            System.out.println(Constant.Failed);
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
