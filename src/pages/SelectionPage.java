package pages;

import java.util.ArrayList;
import java.util.List;

public class SelectionPage extends Page {
    public List<Page> subPages;

    public SelectionPage(String name) {
        super(name);
        subPages = new ArrayList<>();
    }

    public void addPage(Page p){
        subPages.add(p);
    }

    @Override
    protected boolean runPage() {
        int i = 1;
        for(Page p : subPages){
            System.out.println(i+". "+p.getPageName());
            i++;
        }
        System.out.println(i+". Back");
        String input = scanner.nextLine();
        int id = 0;
        try {
            id = Integer.valueOf(input);
            subPages.get(id-1).run();
        }catch (Exception e){
            if(id == subPages.size()+1){
                return true;
            }
        }
        return false;
    }
}
