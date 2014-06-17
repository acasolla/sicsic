package it.abc.sicsic.view.backing.tree;


import it.abc.sicsic.Constants;

import java.util.ArrayList;
import java.util.List;

public class TreeNode extends TreeCore {
    private final List<TreeItem> children;
    public TreeNode(String text) {
      super(text, true);
      children = new ArrayList<TreeItem>();
    }
    public TreeNode addChildren(TreeItem children){
        this.children.add(children);
        return this;
    }
    
    public List<TreeItem> getChildren() {
        return children;
    }

    @Override
    public String getAction() {
        return null;
    }

    @Override
    public String getIcon() {
        return Constants.Icon.FOLDER;
    }
}
