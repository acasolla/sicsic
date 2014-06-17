package it.abc.sicsic.view.backing.skipper;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;

import it.abc.sicsic.model.Skipper;

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
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_gestioneSkipper")
@RequestScoped

public class GestioneSkipper {
    private RichInputText itNome;
    private RichInputText itNatoIl;
    private RichInputText itNatoA;
    private RichInputText itResidenza;
    private RichInputText itPatenteNautica;
    private RichInputText itRilasciataDa;
    private RichTable tSkipp;
    private List<Skipper> skipperTable;
    private RichPopup popupRemove;
    private RichDialog dialogRemove;
    private RichPopup noteWindow;
    private String msg;
    private RichOutputText otMsg;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private static Logger logger = Logger.getLogger(GestioneSkipper.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private List<Object> components;
    private String msgPopup;
    
    //  private static Logger logger = Logger.getLogger(GestioneSkipper.class);
    
    public GestioneSkipper(){
        logger.info(Utils.logStartMethod("GestioneSkipper"));
        try{
            initializeComponents();
            ViewUtils.fillComponentsByParamsInSession(Constants.Place.GESTIONE_SKIPPER, tSkipp, components);
            skipperTable = serviceFacade.getSkipper(getNome(), getResidenza());
        }catch (Exception e){
            logger.info("Error initiating gestione Skipper");    
        }
    }
    
    public List<Skipper>getSkipperTable(){
        return skipperTable;
    }
    
    public void setItNome(RichInputText itNome) {
        this.itNome = itNome;
    }

    public RichInputText getItNome() {
        return itNome;
    }

    public void setItNatoIl(RichInputText itNatoIl) {
        this.itNatoIl = itNatoIl;
    }

    public RichInputText getItNatoIl() {
        return itNatoIl;
    }

    public void setItNatoA(RichInputText itNatoA) {
        this.itNatoA = itNatoA;
    }

    public RichInputText getItNatoA() {
        return itNatoA;
    }

    public void setItResidenza(RichInputText itResidenza) {
        this.itResidenza = itResidenza;
    }

    public RichInputText getItResidenza() {
        return itResidenza;
    }

    public void setItPatenteNautica(RichInputText itPatenteNautica) {
        this.itPatenteNautica = itPatenteNautica;
    }

    public RichInputText getItPatenteNautica() {
        return itPatenteNautica;
    }

    public void setItRilasciataDa(RichInputText itRilasciataDa) {
        this.itRilasciataDa = itRilasciataDa;
    }

    public RichInputText getItRilasciataDa() {
        return itRilasciataDa;
    }
    
    private String getNome(){
        return ViewUtils.getStringValue(getItNome());
    }
    private String getNatoIl(){
        return ViewUtils.getStringValue(getItNatoIl());
    }
    private String getNatoA(){
        return ViewUtils.getStringValue(getItNatoA());   
    }
    private String getResidenza(){
        return ViewUtils.getStringValue(getItResidenza());
    }
    private String getPatenteNautica(){
        return ViewUtils.getStringValue(getItPatenteNautica());
    }
    private String getRilasciataDa(){
        return ViewUtils.getStringValue(getItRilasciataDa());
    }

    public void cerca(ActionEvent actionEvent) {
        List<Skipper> list = null;
        list = serviceFacade.getSkipper(getNome(), getResidenza());
        logger.info("listResult=" + list);
        tSkipp.setValue(list);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tSkipp);
    }

    public void setTSkipp(RichTable tableSkipper) {
        this.tSkipp = tableSkipper;
    }

    public RichTable getTSkipp() {
        return tSkipp;
    }

    public void openPopup(ActionEvent actionEvent) {
        Skipper skipper = (Skipper)tSkipp.getSelectedRowData();
        logger.info("skipper=" + skipper);
        if(skipper == null) return;
        setMsgPopup("Vuoi eliminare lo skipper: " + skipper.getNome());
        popupRemove.show(ph);
    }

    public String actionDetail() {
        Skipper skipper = (Skipper)tSkipp.getSelectedRowData();
        if(skipper == null)return null;
        logger.info("skipper selected=" + skipper);
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_SKIPPER, skipper);
        ViewUtils.setParamsInSession(Constants.Place.GESTIONE_SKIPPER, 
                                    Constants.Place.GESTIONE_SKIPPER, 
                                    tSkipp, components);
        return Constants.Place.INSERIMENTO_SKIPPER;
    }

    public void setPopupRemove(RichPopup popupRemove) {
        this.popupRemove = popupRemove;
    }

    public RichPopup getPopupRemove() {
        return popupRemove;
    }

    public void dialogRemoveListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        Skipper skipper = (Skipper)tSkipp.getSelectedRowData();
        if(skipper == null)return;
        try{
            serviceFacade.removeSkipper(skipper);
            List<Skipper> list = null;
            list = serviceFacade.getSkipper(getNome(), getResidenza());
            tSkipp.setValue(list);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tSkipp);
        }catch(Exception e){
                logger.info("error removing skipper" + e);
                setMsg("Salvataggio fallito, error=" + e.getMessage());
                    noteWindow.show(ph);
                    return;
            }
        
        setMsg("Lo skipper "+skipper.getNome()+" è stato rimosso con successo");
        ViewUtils.showMessageNoBundle("Lo skipper "+skipper.getNome()+" è stato rimosso con successo",
               "cbDelete",
               noteWindow,
               otMsg);
        }
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
            return;
        }
    }

    public void setDialogRemove(RichDialog dialogRemove) {
        this.dialogRemove = dialogRemove;
    }

    public RichDialog getDialogRemove() {
        return dialogRemove;
    }

    public void setNoteWindow(RichPopup noteWindow) {
        this.noteWindow = noteWindow;
    }

    public RichPopup getNoteWindow() {
        return noteWindow;
    }

    public void setOtMsg(RichOutputText otMsg) {
        this.otMsg = otMsg;
    }

    public RichOutputText getOtMsg() {
        return otMsg;
    }

    public void setMsgPopup(String msgPopup) {
        this.msgPopup = msgPopup;
    }

    public String getMsgPopup() {
        return msgPopup;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    
    private void initializeComponents(){
        components = new ArrayList<Object>();
        itNome = new RichInputText();
        itResidenza = new RichInputText();
        
        itNome.setId("itNome");
        itResidenza.setId("itResidenza");
        
        populateComponents();
    }
    
    private void populateComponents(){
        if(components ==  null)
            throw new IllegalArgumentException("components can't be null");
        components.clear();
        components.add(itNome);
        components.add(itResidenza);
        components.add(tSkipp);
    }
}
