package pages;

import pages.helper.Common;
import ui.Constant;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class Page {
    String pageName;
    public Scanner scanner;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Page(){
        scanner = new Scanner(System.in);
    }
    public Page(String name){
        pageName = name;
        scanner = new Scanner(System.in);
    }

    private void showHeader(){
        System.out.println(Constant.ANSI_CYAN+Constant.DoubleLine+Constant.ANSI_RESET);
        System.out.println(Constant.ANSI_BLUE+pageName+Constant.ANSI_RESET);
        System.out.println(Constant.ANSI_CYAN+Constant.DoubleLine+Constant.ANSI_RESET);
    }

    public void run() {
        while (true){
            showHeader();
            if(runPage()){
                return;
            }
        }
    }


    protected abstract boolean runPage();
}
