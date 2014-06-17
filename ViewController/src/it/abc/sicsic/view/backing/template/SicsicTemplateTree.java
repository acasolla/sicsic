package it.abc.sicsic.view.backing.template;

import it.abc.sicsic.Constants;
import it.abc.sicsic.security.User;
import it.abc.sicsic.view.backing.tree.TreeCore;
import it.abc.sicsic.view.backing.tree.TreeItem;
import it.abc.sicsic.view.backing.tree.TreeNode;


import it.soulsoftware.utils.FacesUtils;

import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.data.RichTree;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.RowDisclosureEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;

@ManagedBean(name = "backing_sicsicTemplateTree")
@SessionScoped

public class SicsicTemplateTree {
    private Object instance = null;
    private TreeModel model = null;
    private RichTree tree;
    private static final Logger logger = Logger.getLogger(SicsicTemplateTree.class);
    public SicsicTemplateTree() {
        super();
        logger.info("SicsicTemplateTree start");
         List<TreeCore> root = (List<TreeCore>)FacesUtils.getSessionMapValue(Constants.Key.TREE);
        FacesUtils.setSessionMapValue(Constants.Key.TREE,null);
        setListInstance(root);
        if (tree==null) tree=new RichTree();
        RowKeySet rks = tree.getDisclosedRowKeys();
        if( rks == null) rks = new RowKeySetImpl(); 
        rks.clear();
        for ( int i= 0; i<root.size(); i++){
            List<Integer> rows = new ArrayList<Integer>();
            rows.add(i);
            rks.add(rows);
        }
        
        tree.setDisclosedRowKeys(rks);
        tree.saveState(FacesContext.getCurrentInstance());
                
    }
    
    public TreeModel getModel() {
        if (model == null)
            model = new ChildPropertyTreeModel(instance, "children");
        return model;
    }

    public void setListInstance(List instance) {
        this.instance = instance;
        //ViewUtils.refreshComponents(tree);

        model = null;
    }

    public void setTree(RichTree tree) {
        this.tree = tree;
    }

    public RichTree getTree() {
        return tree;
    }

    public void treeSelectionListener(SelectionEvent selectionEvent) {
        logger.info("tree clicked");
    }

    public void rowDisclosureTree(RowDisclosureEvent rowDisclosureEvent) {
        logger.info("tree clicked, event=" + rowDisclosureEvent);
        RowKeySet rks = tree.getDisclosedRowKeys();
        logger.info("rks size=" + rks.size());
        Iterator it = rks.iterator();
        while (it.hasNext()){
           Object o = it.next();
            if ( o instanceof Integer)
            logger.info("integer=" + o);
            else if ( o instanceof String)
                logger.info("String=" + o);
            else if ( o instanceof ArrayList )
                logger.info("arrayList=" + o);
            else
                logger.info("object=" + o);
        }
        
    }
    public String actionClienti(){
        return Constants.Place.INSERIMENTO_CLIENTE;
    }
    public String actionContratti(){
        return Constants.Place.CONTRATTO;
    }
    public String actionLogin(){
    //    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesUtils.setSessionMapValue(Constants.Key.USER,null);
            
        return Constants.Place.LOGIN;
    }
}
