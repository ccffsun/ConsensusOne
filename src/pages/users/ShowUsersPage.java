package pages.users;

import db.ProjectDAO;
import db.UserDAO;
import dnl.utils.text.table.TextTable;
import entity.Project;
import entity.User;
import pages.ActionPage;
import ui.Constant;

import java.sql.SQLException;
import java.util.List;

public class ShowUsersPage  extends ActionPage {
    public ShowUsersPage(){
        super();
        setPageName("Show Users");
    }

    @Override
    protected boolean runPage()  {
        try {
            UserDAO ud = new UserDAO();
            List<User> users = ud.readAll();
            listUsers(users);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    private void listUsers(List<User> users) {
        String[] column = new String[]{"Id", "Name", "Email", "Tel"};
        Object[][] data = new Object[users.size()][column.length];
        int i = 0;
        for (User u : users) {
            data[i][0] = u.getId();
            data[i][1] = u.getName();
            data[i][2] = u.getEmail();
            data[i][3] = u.getTel();
            i++;
        }
        TextTable tt = new TextTable(column, data);
        tt.printTable();
        System.out.println(Constant.SingleLine);
    }
}
