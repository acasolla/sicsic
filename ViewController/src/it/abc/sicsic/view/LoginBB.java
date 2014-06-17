package it.abc.sicsic.view;


import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;
import it.abc.sicsic.security.SecurityService;

import it.abc.sicsic.security.User;

import it.abc.sicsic.view.backing.template.SicsicTemplateTree;
import it.abc.sicsic.view.backing.tree.TreeCore;
import it.abc.sicsic.view.backing.tree.TreeItem;
import it.abc.sicsic.view.backing.tree.TreeNode;

import it.soulsoftware.utils.FacesUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.log4j.Logger;


@ManagedBean(name = "backing_Login")
@RequestScoped
public class LoginBB {

    private static Logger log = Logger.getLogger(LoginBB.class);

    private RichInputText itUser;
    private RichInputText itPsw;
    private RichOutputText otError;

    public void setItUser(RichInputText itUser) {
        this.itUser = itUser;
    }

    public RichInputText getItUser() {
        return itUser;
    }

    public void setItPsw(RichInputText itPsw) {
        this.itPsw = itPsw;
    }

    public RichInputText getItPsw() {
        return itPsw;
    }

    public void setOtError(RichOutputText otError) {
        this.otError = otError;
    }

    public RichOutputText getOtError() {
        return otError;
    }

    public String submit() {
        log.debug("starting submit...");
        boolean logged = false;
        String user = null;
        String psw = null;
        try {
            user = itUser.getValue().toString();
            psw = itPsw.getValue().toString();
            log.debug("user=" + user + " psw=" + psw);
            SecurityService ss = new SecurityService();
            logged = ss.login(user, psw);
        } catch (Exception e) {
            log.error("error", e);
        }

        if (!logged) {
            log.debug("logged=" + logged);
            otError.setValue("Error authenticating !");
            otError.setVisible(true);
            return null;
        } else {
            
            User us = new User();
            us.setUsername(user);
            us.setPsw(psw);
            
            boolean isAdmin = user.equals(Constants.Key.ADMIN);

            if ( !isAdmin && psw.equals(Constants.Key.CANCELLA)){
                JavaServiceFacade facade = new JavaServiceFacade();
                facade.deleteAllNoleggio();
                facade.deleteAllSpese();
            }
    
            List<TreeCore> root = createTree(isAdmin);
            FacesUtils.setSessionMapValue(Constants.Key.USER, us);
            FacesUtils.setSessionMapValue(Constants.Key.TREE, root);
            SicsicTemplateTree tempTree = (SicsicTemplateTree)FacesUtils.getSessionMapValue("backing_sicsicTemplateTree");
            if ( tempTree != null )
                tempTree.setListInstance(root);
            log.debug("user setted in session=" + us);
            return "Contratto";

        }
    }

    private List<TreeCore> createTree(boolean isAdmin){
        log.debug("creating tree...,isAdmin=" + isAdmin);
        List<TreeCore> root = new ArrayList<TreeCore>();
        TreeNode clientiNode = new TreeNode("Clienti");
        clientiNode.addChildren(new TreeItem("Gestione Clienti",
                                             Constants.Place.GESTIONE_CLIENTI,
                                             Constants.Icon.CUSTOMERS))
                   .addChildren(new TreeItem("Inserimento Cliente",
                                             Constants.Place.INSERIMENTO_CLIENTE,
                                             Constants.Icon.INSERT));
        TreeNode barcheNode = new TreeNode("Barche");
        barcheNode.addChildren(new TreeItem("Gestione Barche",
                                            Constants.Place.GESTIONE_BARCHE,
                                            Constants.Icon.BOAT))
                  .addChildren(new TreeItem("Inserimento Barca",
                                            Constants.Place.INSERIMENTO_BARCA,
                                            Constants.Icon.INSERT));
        TreeNode skipperNode = new TreeNode("Skipper");
        skipperNode.addChildren(new TreeItem("Gestione Skipper",
                                             Constants.Place.GESTIONE_SKIPPER,
                                             Constants.Icon.ANCHOR))
                  .addChildren(new TreeItem("Inserimento Skipper",
                                            Constants.Place.INSERIMENTO_SKIPPER,
                                            Constants.Icon.INSERT));
        TreeNode nauticaNode = new TreeNode("Nautica");        
        nauticaNode.addChildren(new TreeItem("Contratto",
                                              Constants.Place.CONTRATTO,
                                              Constants.Icon.CONTRACT));
      //  TreeNode speseNode = new TreeNode("Spese");       
        if ( isAdmin ){
        nauticaNode.addChildren(new TreeItem("Gestione Noleggi",
                                  Constants.Place.GESTIONE_NOLEGGI,
                                  Constants.Icon.RENT))
                   .addChildren(new TreeItem("Inserimento Noleggio",
                                  Constants.Place.INSERIMENTO_NOLEGGIO,
                                  Constants.Icon.INSERT))
            
            .addChildren(new TreeItem("Gestione Spese",
                                               Constants.Place.GESTIONE_SPESE,
                                               Constants.Icon.COST))
                     .addChildren(new TreeItem("Inserimento Spesa",
                                               Constants.Place.INSERIMENTO_SPESA,
                                               Constants.Icon.INSERT));
            
        }

        root.add(clientiNode);
        root.add(barcheNode);
        root.add(skipperNode);
        root.add(nauticaNode);
        //root.add(speseNode);
    
        return root;
    }

}
