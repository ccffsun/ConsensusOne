package pages.users;

import db.ProjectDAO;
import db.UserDAO;
import entity.Project;
import entity.User;
import pages.ActionPage;
import pages.helper.Common;

import java.sql.Date;

public class NewUserPage  extends ActionPage {
    public NewUserPage(){
        super();
        setPageName("New User");
    }

    @Override
    protected boolean runPage() {
        User u = new User();

        try {
            System.out.println("User Name:");
            String input = scanner.nextLine();
            u.setName(input);

            System.out.println("User email:");
            input = scanner.nextLine();
            u.setEmail(input);

            System.out.println("User tel:");
            input = scanner.nextLine();
            u.setTel(input);

            if (Common.Confirm()) {
                UserDAO ud = new UserDAO();
                ud.create(u);
                System.out.println("Successfully Added new user!");
            }
        }catch (Exception ex){
            System.out.println("Failed!!!!!!!");
            ex.printStackTrace();
        }

        return true;
    }
}
