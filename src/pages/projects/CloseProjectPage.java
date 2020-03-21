package pages.projects;

import db.ProjectBookkeepingDAO;
import db.ProjectDAO;
import dnl.utils.text.table.TextTable;
import entity.Order;
import entity.Project;
import entity.ProjectBookkeeping;
import pages.ActionPage;
import pages.helper.Common;
import ui.Constant;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CloseProjectPage  extends ActionPage {
    public CloseProjectPage(){
        super();
        setPageName("Close Project");
    }
    @Override
    protected boolean runPage() {
        try {
            System.out.println("Project Id:");
            String input = scanner.nextLine();
            int pid = Integer.valueOf(input);
            ProjectDAO pd = new ProjectDAO();
            Project project = pd.getProject(pid);
            if(project == null){
                System.out.println("Project not exist!!!!!!");
                return true;
            }
            System.out.println("Close Date:");
            input = scanner.nextLine();
            project.setEndDate(Date.valueOf(input));

            showBookKeeping(pid);

            if(Common.Confirm(scanner)){
                project.setStatus("closed");
                pd.update(project);
            }else {
                //new ProjectLeaseReturnPage().run();
            }

        }catch (Exception ex){
            System.out.println("Failed!!!!!");
            ex.printStackTrace();
        }
        return true;
    }

    private void showBookKeeping(int projectId) throws SQLException {
        ProjectBookkeepingDAO bookkeepingDAO = new ProjectBookkeepingDAO();
        List<ProjectBookkeeping> pbList = bookkeepingDAO.getProjectBookkeepings(projectId);
        String[] column = new String[]{"Product Id", "Amount Outstanding"};
        Object[][] data = new Object[pbList.size()][column.length];
        int i = 0;
        for(ProjectBookkeeping pb: pbList){
            data[i][0] = pb.getProductId();
            data[i][1] = pb.getQuantity();
            i++;
        }
        TextTable tt = new TextTable(column, data);
        tt.printTable();
        System.out.println(Constant.SingleLine);
    }
}
