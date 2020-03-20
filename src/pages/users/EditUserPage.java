package pages.users;

import db.UserDAO;
import entity.User;
import pages.ActionPage;

import java.awt.*;
import java.sql.SQLException;

public class EditUserPage extends ActionPage {
    public EditUserPage() {
        super();
        setPageName("Edit User");
    }

    UserDAO ud = new UserDAO();
    @Override
    protected boolean runPage() {

        try {
            ShowUsersPage sp = new ShowUsersPage();
            sp.runPage();
            System.out.println("User Id:");
            String input = scanner.nextLine();
            User user = ud.getUser(Integer.valueOf(input));
            System.out.println("Edit:\n1. Name\n2. Email\n3. Tel\n4. Back");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Edit Name:");
                    input = scanner.nextLine();
                    user.setName(input);
                    ud.update(user);
                    break;
                case "2":
                    System.out.println("Edit Email:");
                    input = scanner.nextLine();
                    user.setEmail(input);
                    ud.update(user);
                    break;
                case "3":
                    System.out.println("Edit Tel:");
                    input = scanner.nextLine();
                    user.setTel(input);
                    ud.update(user);
                    break;
                case "4":
                    break;

            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }
}
