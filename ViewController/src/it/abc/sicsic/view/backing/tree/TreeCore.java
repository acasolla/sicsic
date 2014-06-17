package it.abc.sicsic.view.backing.tree;

import java.util.List;

public abstract class TreeCore {
    private String text;
    private final boolean disabled;
        
    public TreeCore(String text,boolean disabled) {
      this.text = text;
      this.disabled = disabled;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public boolean isDisabled(){
        return disabled;    
    }
    public abstract List<TreeItem> getChildren();
    public abstract String getAction();
    public abstract String getIcon();
}
