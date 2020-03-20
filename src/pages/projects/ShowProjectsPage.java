package pages.projects;

import db.ProjectDAO;
import dnl.utils.text.table.TextTable;
import entity.Project;
import pages.ActionPage;
import ui.Constant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShowProjectsPage extends ActionPage {
    public ShowProjectsPage() {
        super();
        setPageName("Show Projects");
    }

    @Override
    protected boolean runPage() {
        System.out.println("1. All Projects\n2. Active Projects\n3. Closed Projects");

        try {
            String input = scanner.nextLine();
            ProjectDAO pd = new ProjectDAO();
            List<Project> projects = pd.readAll();
            switch (input) {
                case "1":
                    listProjects(projects);
                    break;
                case "2":
                    listActiveProjects(projects);
                    break;
                case "3":
                    listClosedProjects(projects);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    private void listActiveProjects(List<Project> projects) {
        List<Project> activeProject = projects.stream().filter(p -> (p.getStatus().equals("active"))).collect(Collectors.toList());
        listProjects(activeProject);
    }

    private void listClosedProjects(List<Project> projects) {
        List<Project> activeProject = projects.stream().filter(p -> (p.getStatus().equals("closed"))).collect(Collectors.toList());
        listProjects(activeProject);
    }

    private void listProjects(List<Project> projects) {
            String[] column = new String[]{"Id", "Project Name", "User Id", "Status", "Start Date", "End Date"};
            Object[][] data = new Object[projects.size()][column.length];
            int i = 0;
            for (Project p : projects) {
                data[i][0] = p.getId();
                data[i][1] = p.getProjectName();
                data[i][2] = p.getUserId();
                data[i][3] = p.getStatus();
                data[i][4] = p.getStartDate();
                data[i][5] = p.getEndDate();
                i++;
            }
        TextTable tt = new TextTable(column, data);
        tt.printTable();
        System.out.println(Constant.SingleLine);
    }
}
