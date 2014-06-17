package it.abc.sicsic.view.backing.tree;

import java.util.Collections;
import java.util.List;

public class TreeItem extends TreeCore {
    private final String action;
    private String icon;
    
     public TreeItem(String text, String action){
        super(text,false);
        this.action = action;        
    }
     
    public TreeItem(String text, String action,String icon){
       super(text,false);
       this.action = action;
       this.icon = icon;
    }
     
    @Override
     public String getAction(){
        return action;    
    }

    @Override
    public List<TreeItem> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
