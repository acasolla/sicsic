package it.abc.sicsic.view.backing.barche;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;

import it.abc.sicsic.model.Barche;

import it.abc.sicsic.view.backing.clienti.GestioneClienti;

import it.soulsoftware.utils.Utils;

import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;

@ManagedBean(name = "backing_gestioneBarche")
@RequestScoped

public class GestioneBarche {

    private RichInputText itNomeBarca;
    private RichInputText itModello;
    private RichTable tBarche;
    private List<Barche> listTableBarche;
    private RichCommandButton cerca;
    private String msg;
    private String msgPopup;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private static Logger logger = Logger.getLogger(GestioneBarche.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private RichOutputText otMsg;
    private List<Object> components;
    private RichPopup popupRemove;
    private RichDialog dialogRemove;
    private RichPopup noteWindow;

    public GestioneBarche(){
        logger.info(Utils.logStartMethod("GestioneBarche"));    
        try{
            initializeComponents();
            ViewUtils.fillComponentsByParamsInSession(Constants.Place.GESTIONE_BARCHE,
                                                     tBarche,
                                                     components);
            listTableBarche = serviceFacade.getBarche(getNomeBarca(), getModello());
        }catch( Exception e){
            logger.info("Error initiating gestionebarche");    
        }
    }
        
    public List<Barche> getListTableBarche(){
        return listTableBarche;
    }
    
    public void setItNomeBarca(RichInputText itNomeBarca) {
        this.itNomeBarca = itNomeBarca;
    }

    public RichInputText getItNomeBarca() {
        return itNomeBarca;
    }

    public void setItModello(RichInputText itModello) {
        this.itModello = itModello;
    }

    public RichInputText getItModello() {
        return itModello;
    }

    public void cerca(ActionEvent actionEvent) {
    //    JavaServiceFacade serviceFacade = new JavaServiceFacade();
        List<Barche> list = null;
        list = serviceFacade.getBarche(getNomeBarca(),getModello());        
        logger.info("listResult=" + list);
        tBarche.setValue(list);
        System.out.println("cerca GestioneBarche start");
        AdfFacesContext.getCurrentInstance().addPartialTarget(tBarche);
    }
    
    private String getNomeBarca(){
        
        return ViewUtils.getStringValue(getItNomeBarca());
    }
    private String getModello(){
        return ViewUtils.getStringValue(getItModello());
    }

    public void setTBarche(RichTable tableBarche) {
        this.tBarche = tableBarche;
    }

    public RichTable getTBarche() {
        return tBarche;
    }
    
    private void initializeComponents(){
        components = new ArrayList<Object>();
        itNomeBarca = new RichInputText();
        itModello = new RichInputText();
        tBarche = new RichTable();
        
        itNomeBarca.setId("itNomeBarca");
        itModello.setId("itModello");
        tBarche.setId("tBarche");
        
        populateComponents();
    }
    
    private void populateComponents(){
        if(components == null)
            throw new IllegalArgumentException("components can't be null");
        components.clear();
        components.add(itNomeBarca);
        components.add(itModello);
        components.add(tBarche);
    }


    public String actionDetail() {
        Barche barca = (Barche)tBarche.getSelectedRowData();
        if (barca == null)
            return null;
        logger.info("barca selected=" + barca);
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_BARCA, barca);
        //populateComponents();
        ViewUtils.setParamsInSession(Constants.Place.GESTIONE_BARCHE, 
                                    Constants.Place.GESTIONE_BARCHE, 
                                    tBarche, components);
        return Constants.Place.INSERIMENTO_BARCA;    
    }

    public void setPopupRemove(RichPopup popupRemove) {
        this.popupRemove = popupRemove;
    }

    public RichPopup getPopupRemove() {
        return popupRemove;
    }

    public void dialogRemoveListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        Barche barca = (Barche)tBarche.getSelectedRowData();
        if (barca==null)return;
        try{
            serviceFacade.removeBarche(barca);
            List<Barche>list = null;
            list = serviceFacade.getBarche(getNomeBarca(), getModello());
            tBarche.setValue(list);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tBarche);
        }catch (Exception e){
            logger.info("error removing barca" + e);
            setMsg("Salvataggio fallito, error=" + e.getMessage());
            noteWindow.show(ph);
            return;
        }
        setMsg("La barca " +barca.getNomeBarca()+" e stata rimossa con successo");
        ViewUtils.showMessageNoBundle("La barca "+barca.getNomeBarca()+" è stata rimossa con successo", 
                                     "cbDelete", 
                                     noteWindow, otMsg);
        }
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.no)) {
            return;
        }    
    }

    public void setDialogRemove(RichDialog dialogRemove) {
        this.dialogRemove = dialogRemove;
    }

    public RichDialog getDialogRemove() {
        return dialogRemove;
    }

    public void setMsgPopup(String msgPopup) {
        this.msgPopup = msgPopup;
    }

    public String getMsgPopup() {
        return msgPopup;
    }

    public void setNoteWindow(RichPopup noteWindow) {
        this.noteWindow = noteWindow;
    }

    public RichPopup getNoteWindow() {
        return noteWindow;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setOtMsg(RichOutputText otMsg) {
        this.otMsg = otMsg;
    }

    public RichOutputText getOtMsg() {
        return otMsg;
    }

    public void openPopup(ActionEvent actionEvent) {
        Barche barca = (Barche)tBarche.getSelectedRowData();
        logger.info("barca=" + barca);
        if( barca == null ) return;
        setMsgPopup("Vuoi eliminare la barca: " + (barca.getNomeBarca()));
        popupRemove.show(ph);
    }

    public void setCerca(RichCommandButton cerca) {
        this.cerca = cerca;
    }

    public RichCommandButton getCerca() {
        return cerca;
    }

    public void tableSelection(SelectionEvent selectionEvent) {
        // Add event code here...
    }
}
