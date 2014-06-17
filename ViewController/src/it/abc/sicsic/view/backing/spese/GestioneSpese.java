package it.abc.sicsic.view.backing.spese;


import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;


import it.abc.sicsic.model.SpeseDTO;

import it.soulsoftware.utils.Utils;

import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_gestioneSpese")
@SessionScoped

public class GestioneSpese {

    private RichInputText itImportoSpese;
    private RichTable tSpese;
    private List<SpeseDTO> listTableSpese;
    private RichPopup popupRemove;
    private RichDialog dialogRemove;
    private RichPopup noteWindow;
    private RichOutputText otMsg;
    private RichInputDate idaDataDA;
    private RichInputDate idaDataA;
    private String msg;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private static Logger logger = Logger.getLogger(GestioneSpese.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private List<Object> components;
    private String msgPopup;
    private RichOutputText totaleSpese;
    private RichSelectOneChoice tipoDiSpesa;
    private RichSelectOneChoice spagamento;
    private RichInputText itNote;
    //private static final Logger logger = Logger.getLogger(GestioneSpese.class);

    
    public GestioneSpese(){
       Calendar c = Calendar.getInstance();
       c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
       if ( idaDataDA == null ){
           idaDataDA  = new RichInputDate();
       }
       if ( idaDataA == null ){
           idaDataA  = new RichInputDate();
       }
        idaDataDA.setValue(c.getTime());
        idaDataA.setValue(new Date());
        AdfFacesContext.getCurrentInstance().addPartialTarget(idaDataDA);
        AdfFacesContext.getCurrentInstance().addPartialTarget(idaDataA);
    }
    
    public void cerca(ActionEvent actionEvent) {
        
        logger.info(Utils.logStartMethod("cerca"));
        
        try{
            listTableSpese = serviceFacade.getSpeseFindAllMonth(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getTipoSpesa(),getPagamento(),getNote());
            if ( listTableSpese.size() > 0 ){
                SpeseDTO spesa = listTableSpese.get(listTableSpese.size()-1);
                totaleSpese.setValue(spesa.getTotSpeseFormatted());
            }else {
                totaleSpese.setValue(0);
            }
            tSpese.setValue(listTableSpese);
            AdfFacesContext.getCurrentInstance().addPartialTarget(totaleSpese);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tSpese);
            
        }catch (Exception e){
            logger.info("Error initiating gestione Spese");    
        }
        
        
        
        /*
        
        List<SpeseDTO> list = null;
        list = serviceFacade.getSpeseFindAllMonth(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getTipoSpesa(),
                                    getPagamento(),
                                                  getNote());
        logger.info("spese size=" + list.size());
        tSpese.setValue(list);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tSpese);
        if ( list.size() > 0 ){
            SpeseDTO spesa = list.get(list.size()-1);
            totaleSpese.setValue(spesa.getTotSpeseFormatted());
        } else {
            totaleSpese.setValue(0);   
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(totaleSpese);
*/
    }

    public Date getDataDA(){        
        return ViewUtils.getDateValue(getIdaDataDA());   
    }

    public Date getDataA(){
        return ViewUtils.getDateValue(getIdaDataA());    
    }

    private String getImportoSpese(){
        return ViewUtils.getStringValue(getItImportoSpese());
    }
    
    private String getTipoSpesa(){
        return ViewUtils.getStringValue(getTipoDiSpesa());
    }

    public void setTSpese(RichTable tableSpese) {
        this.tSpese = tableSpese;
    }

    public RichTable getTSpese() {
        return tSpese;
    }

    public void setItImportoSpese(RichInputText itImportoSpese) {
        this.itImportoSpese = itImportoSpese;
    }

    public RichInputText getItImportoSpese() {
        return itImportoSpese;
    }

    public List<SpeseDTO> getListTableSpese() {
        return listTableSpese;
    }

    public void openPopup(ActionEvent actionEvent) {
        SpeseDTO speseDTO =(SpeseDTO)tSpese.getSelectedRowData();
        logger.info("Spese" + speseDTO);
        if(speseDTO == null)return;
        setMsgPopup("Vuoi eliminare la spesa: " + speseDTO.getDescrizione());
        popupRemove.show(ph);
    }

    public void setPopupRemove(RichPopup popupRemove) {
        this.popupRemove = popupRemove;
    }

    public RichPopup getPopupRemove() {
        return popupRemove;
    }

    public void dialogRemoveListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        SpeseDTO speseDTO = (SpeseDTO)tSpese.getSelectedRowData();
        if(speseDTO == null)return;
        try{
            serviceFacade.removeSpeseDTO(speseDTO);
            List<SpeseDTO> list = null;
            list = serviceFacade.getSpeseFindAllMonth(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getTipoSpesa(),
                                    getPagamento(),
                                    getNote());
            tSpese.setValue(list);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tSpese);
            if ( list.size() > 0 ){
                SpeseDTO spesa = list.get(list.size()-1);
                totaleSpese.setValue(spesa.getTotSpeseFormatted());
            } else {
                totaleSpese.setValue(0);   
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(totaleSpese);
        }catch(Exception e){
                logger.info("error removing noleggio" + e);
                setMsg("Salvataggio fallito, error=" + e.getMessage());
                    noteWindow.show(ph);
                    return;
            }
        //setMsg("La spesa "+speseDTO.getBarca()+" ??? stato rimosso con successo");
        ViewUtils.showMessageNoBundle("La spesa "+ speseDTO.getDescrizione()+" ??? stata rimossa con successo",
               "cbDelete",
               noteWindow,
               otMsg);
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

    public void setIdaDataDA(RichInputDate idaDataDA) {
        this.idaDataDA = idaDataDA;
    }

    public RichInputDate getIdaDataDA() {
        return idaDataDA;
    }

    public void setIdaDataA(RichInputDate idaDataA) {
        this.idaDataA = idaDataA;
    }

    public RichInputDate getIdaDataA() {
        return idaDataA;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsgPopup(String msgPopup) {
        this.msgPopup = msgPopup;
    }

    public String getMsgPopup() {
        return msgPopup;
    }

    public String actionDetail() {
        SpeseDTO speseDTO = (SpeseDTO)tSpese.getSelectedRowData();
        if(speseDTO == null)return null;
        logger.info("spese selected=" + speseDTO);
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_SPESA, speseDTO);
        ViewUtils.setParamsInSession(Constants.Place.GESTIONE_SPESE, 
                                    Constants.Place.GESTIONE_SPESE,
                                     tSpese, components);
        return Constants.Place.INSERIMENTO_SPESA;
    }
    private void initializeComponents(){
        components = new ArrayList<Object>();
        idaDataA = new RichInputDate();
        idaDataDA = new RichInputDate();        
        idaDataA.setId("idaDataA");
        idaDataDA.setId("idaDataDa");
        totaleSpese = new RichOutputText();
        totaleSpese.setId("totaleSpese");
        populateComponents();
    }
    
    private void populateComponents(){
        if(components ==  null)
            throw new IllegalArgumentException("components can't be null");
        components.clear();
        components.add(idaDataA);
        components.add(idaDataDA);
    }

    public void setTotaleSpese(RichOutputText totaleSpese) {
        this.totaleSpese = totaleSpese;
    }

    public RichOutputText getTotaleSpese() {
        return totaleSpese;
    }

    public void setTipoDiSpesa(RichSelectOneChoice tipoDiSpesa) {
        this.tipoDiSpesa = tipoDiSpesa;
    }

    public RichSelectOneChoice getTipoDiSpesa() {
        return tipoDiSpesa;
    }

    public void setSpagamento(RichSelectOneChoice spagamento) {
        this.spagamento = spagamento;
    }

    public RichSelectOneChoice getSpagamento() {
        return spagamento;
    }
    
    public String getPagamento(){
        return ViewUtils.getStringValue(spagamento);
    }
    
    public String getNote(){
        return ViewUtils.getStringValue(getItNote());
    }

    public void setItNote(RichInputText itNote) {
        this.itNote = itNote;
    }

    public RichInputText getItNote() {
        return itNote;
    }
}
