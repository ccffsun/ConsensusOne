package ui;

import db.UserDAO;
import entity.Project;
import entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Page {
    Scanner scanner;

    public Page() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            Util.PageHeader(Constant.Welcome);
            System.out.println(Constant.MainOptions);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    manageProjects();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    return;
            }
        }
    }

    private void manageProjects() {
        while (true) {
            Util.PageHeader(Constant.ManageProjects);
            System.out.println(Constant.ProjectOptions);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    newProject();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    return;
            }
        }
    }

    private void newProject() {
        Project p = new Project();
        Util.PageHeader(Constant.NewProject);

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
        Util.PressEnterKeyToContinue();
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
