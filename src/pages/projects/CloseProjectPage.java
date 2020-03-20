package pages.projects;

import db.ProjectDAO;
import entity.Project;
import pages.ActionPage;
import pages.helper.Common;

import java.sql.Date;

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
            if(Common.Confirm()){
                project.setStatus("closed");
                pd.update(project);
            }

        }catch (Exception ex){
            System.out.println("Failed!!!!!");
            ex.printStackTrace();
        }


        return true;
    }
}
