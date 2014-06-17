package it.abc.sicsic.view.backing.nautica;



import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;
import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;
import it.abc.sicsic.model.Noleggio;
import it.abc.sicsic.model.NoleggioDTO;
import it.abc.sicsic.model.Skipper;

import it.abc.sicsic.model.SpeseDTO;
import it.abc.sicsic.view.backing.util.SicsicUtils;

import it.soulsoftware.utils.FacesUtils;
import it.soulsoftware.utils.Utils;

import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_gestioneNautica")
@SessionScoped
public class GestioneNautica {

    public void beforePhase(PhaseEvent phaseEvent) {
            logger.info("beforePhase");
            Boolean reload = (Boolean) FacesUtils.getSessionMapValue("reloadNoleggio");
            
            if ( reload != null && reload ){
                FacesUtils.setSessionMapValue("reloadNoleggio", false);
                noleggioTable = serviceFacade.getNoleggiIdBarcaBetween(Utils.convertDateToString(getDataDA(), 
                                        Constants.DatePatterns.yyyyMMdd), 
                                        Utils.convertDateToString(getDataA(), 
                                        Constants.DatePatterns.yyyyMMdd),
                                        getBarca(),
                                        getCliente(),
                                        getImportoNolo(),
                                        getPagamento());
                logger.info("noleggioTable reload=" + noleggioTable);
                
                if ( noleggioTable.size() > 0 ){
                    logger.info("noleggioTable size = "+noleggioTable.size());
                    NoleggioDTO imp = noleggioTable.get(noleggioTable.size()-1);
                    tNautic.setValue(noleggioTable);
                    totaleNolo.setValue(imp.getFormattedValueEuroNoloTotale());
                    totaleCarburante.setValue(imp.getFormattedValueEuroCarburanteTotale());
                    importoTotale.setValue(imp.getFormattedValueEuroImportoTotale());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(totaleNolo);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(totaleCarburante);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(importoTotale);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(tNautic);
                }
            }
           }
           
           
    private RichInputText itBarca;
    private RichTable tNautic;
    private RichInputDate idaDataDA;
    private RichInputDate idaDataA;
    private RichPopup popupRemove;
    private RichDialog dialogRemove;
    private RichPopup noteWindow;
    private RichOutputText otMsg;
    private String msg;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private static Logger logger = Logger.getLogger(GestioneNautica.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private List<Object> components;
    private String msgPopup;
    private List<NoleggioDTO> noleggioTable;
    String dataDAFormat = Utils.convertDateToString(getDataDA(), Constants.DatePatterns.yyyyMMdd);
    String dataAFormat = Utils.convertDateToString(getDataA(), Constants.DatePatterns.yyyyMMdd);
    private RichSelectOneChoice scliente;
    private RichSelectOneChoice sbarca;
    private RichSelectOneChoice spagamento;
    private RichInputText itImportoNolo;
    private RichOutputText totaleNolo;
    private RichOutputText totaleCarburante;
    private RichOutputText importoTotale;

    public GestioneNautica(){
        logger.info(Utils.logStartMethod("GestioneNautica"));
        try{
            initializeComponents();
            if ( idaDataDA.getValue() == null ){
                Calendar c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                idaDataDA.setValue(c.getTime());
            }
            if ( idaDataA.getValue() == null ){
                idaDataA.setValue(new Date());
            }
            tNautic.resetStampState();
            noleggioTable = serviceFacade.getNoleggiIdBarcaBetween(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getBarca(),
                                    getCliente(),
                                    getImportoNolo(),
                                    getPagamento());
            
            if ( noleggioTable.size() > 0 ){
                logger.info("noleggioTable size = "+noleggioTable.size());
                NoleggioDTO imp = noleggioTable.get(noleggioTable.size()-1);
                totaleNolo.setValue(imp.getFormattedValueEuroNoloTotale());
                totaleCarburante.setValue(imp.getFormattedValueEuroCarburanteTotale());
                importoTotale.setValue(imp.getFormattedValueEuroImportoTotale());
                tNautic.setValue(noleggioTable);
                AdfFacesContext.getCurrentInstance().addPartialTarget(totaleNolo);
                AdfFacesContext.getCurrentInstance().addPartialTarget(totaleCarburante);
                AdfFacesContext.getCurrentInstance().addPartialTarget(importoTotale);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tNautic);
            }
        }catch (Exception e){
            logger.info("Error initiating gestione Noleggio");    
        }
    }
    
    public void setItBarca(RichInputText itBarca) {
        this.itBarca = itBarca;
    }

    public RichInputText getItBarca() {
        return itBarca;
    }

    public void setTNautic(RichTable tableNautica) {
        this.tNautic = tableNautica;
    }

    public RichTable getTNautic() {
        return tNautic;
    }

    public void cerca(ActionEvent actionEvent) {
        List<NoleggioDTO> list = null;
        logger.info("barca=" + getBarca());
        logger.info("cliente=" + getCliente());
        
        list = serviceFacade.getNoleggiIdBarcaBetween(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getBarca(),
                                    getCliente(),
                                    getImportoNolo(),
                                    getPagamento());
        
        tNautic.setValue(list);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tNautic);
        if ( list.size() > 0 ){
            logger.info("noleggioTable size = "+list.size());
            NoleggioDTO imp = list.get(list.size()-1);
            totaleNolo.setValue(imp.getFormattedValueEuroNoloTotale());
            totaleCarburante.setValue(imp.getFormattedValueEuroCarburanteTotale());
            importoTotale.setValue(imp.getFormattedValueEuroImportoTotale());
            AdfFacesContext.getCurrentInstance().addPartialTarget(totaleNolo);
            AdfFacesContext.getCurrentInstance().addPartialTarget(totaleCarburante);
            AdfFacesContext.getCurrentInstance().addPartialTarget(importoTotale);
        } else {
            totaleNolo.setValue(0);
            totaleCarburante.setValue(0);
            importoTotale.setValue(0);
        }
    }
    
    public Date getDataDA(){        
        return ViewUtils.getDateValue(getIdaDataDA());   
    }

    public void setIdaDataDA(RichInputDate idaDataDA) {
        this.idaDataDA = idaDataDA;
    }

    public RichInputDate getIdaDataDA() {
        return idaDataDA;
    }

    public Date getDataA(){
        return ViewUtils.getDateValue(getIdaDataA());    
    }
    
    public Barche getBarca(){
        return SicsicUtils.getObjectValueBarche(getSbarca());
    }
    
    public Clienti getCliente(){
        return SicsicUtils.getObjectValueClienti(getScliente());
    }
    
    public double getImportoNolo(){
       return ViewUtils.getDoubleValue(getItImportoNolo());
    }
    
    public String getPagamento(){
        return ViewUtils.getStringValue(spagamento);
    }

    public void setIdaDataA(RichInputDate idaDataA) {
        this.idaDataA = idaDataA;
    }

    public RichInputDate getIdaDataA() {
        return idaDataA;
    }

    public void openPopup(ActionEvent actionEvent) {
        NoleggioDTO noleggio =(NoleggioDTO)tNautic.getSelectedRowData();
        logger.info("Noleggio" + noleggio);
        if(noleggio == null)return;
        setMsgPopup("Vuoi eliminare il noleggio: " + noleggio.getNomeBarca());
        popupRemove.show(ph);
    }

    public String actionDetail() {
        NoleggioDTO noleggio = (NoleggioDTO)tNautic.getSelectedRowData();
        if(noleggio == null)return null;
        logger.info("noleggio selected=" + noleggio);
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_NOLEGGIO, noleggio);
        return Constants.Place.INSERIMENTO_NOLEGGIO;
    }

    public void setPopupRemove(RichPopup popupRemove) {
        this.popupRemove = popupRemove;
    }

    public RichPopup getPopupRemove() {
        return popupRemove;
    }

    public void dialogRemoveListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        NoleggioDTO noleggio = (NoleggioDTO)tNautic.getSelectedRowData();
        if(noleggio == null)return;
        try{
            serviceFacade.removeNoleggioDTO(noleggio);
            List<NoleggioDTO> list = null;
            list = serviceFacade.getNoleggiIdBarcaBetween(Utils.convertDateToString(getDataDA(), 
                                    Constants.DatePatterns.yyyyMMdd), 
                                    Utils.convertDateToString(getDataA(), 
                                    Constants.DatePatterns.yyyyMMdd),
                                    getBarca(),
                                    getCliente(),
                                    getImportoNolo(),
                                    getPagamento());
            tNautic.setValue(list);
            if ( list.size() > 0 ){
                logger.info("noleggioTable size = "+list.size());
                NoleggioDTO imp = list.get(list.size()-1);
                totaleNolo.setValue(imp.getFormattedValueEuroNoloTotale());
                totaleCarburante.setValue(imp.getFormattedValueEuroCarburanteTotale());
                importoTotale.setValue(imp.getFormattedValueEuroImportoTotale());
                AdfFacesContext.getCurrentInstance().addPartialTarget(totaleNolo);
                AdfFacesContext.getCurrentInstance().addPartialTarget(totaleCarburante);
                AdfFacesContext.getCurrentInstance().addPartialTarget(importoTotale);
            } else {
                totaleNolo.setValue(0);
                totaleCarburante.setValue(0);
                importoTotale.setValue(0);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(tNautic);
        }catch(Exception e){
                logger.info("error removing noleggio" + e);
                setMsg("Salvataggio fallito, error=" + e.getMessage());
                    noteWindow.show(ph);
                    return;
            }
        setMsg("Il noleggio "+noleggio.getIdBarca()+" ? stato rimosso con successo");
        ViewUtils.showMessageNoBundle("Il noleggio "+noleggio.getIdBarca()+" ? stato rimosso con successo",
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

    public void setNoleggioTable(List<NoleggioDTO> noleggioTable) {
        this.noleggioTable = noleggioTable;
    }

    public List<NoleggioDTO> getNoleggioTable() {
        logger.info("getNoleggioTable="+noleggioTable);
        return noleggioTable;
    }
    private void initializeComponents(){
        components = new ArrayList<Object>();
        idaDataA = new RichInputDate();
        idaDataDA = new RichInputDate();

        itImportoNolo = new RichInputText();
        itImportoNolo.setId("itImportoNolo");
        
        idaDataA.setId("idaDataA");
        idaDataDA.setId("idaDataDa");
        
        totaleNolo = new RichOutputText();
        totaleNolo.setId("totaleNolo");
        totaleCarburante = new RichOutputText();
        totaleCarburante.setId("totaleCarburante");
        importoTotale = new RichOutputText();
        importoTotale.setId("importoTotale");
        
        populateComponents();
    }
    
    private void populateComponents(){
        if(components ==  null)
            throw new IllegalArgumentException("components can't be null");
        components.clear();
        components.add(idaDataA);
        components.add(idaDataDA);
    }

    public void setScliente(RichSelectOneChoice scliente) {
        this.scliente = scliente;
    }

    public RichSelectOneChoice getScliente() {
        return scliente;
    }

    public void setSbarca(RichSelectOneChoice sbarca) {
        this.sbarca = sbarca;
    }

    public RichSelectOneChoice getSbarca() {
        return sbarca;
    }

    public void setItImportoNolo(RichInputText itImportoNolo) {
        this.itImportoNolo = itImportoNolo;
    }

    public RichInputText getItImportoNolo() {
        return itImportoNolo;
    }

    public void setTotaleNolo(RichOutputText totaleNolo) {
        this.totaleNolo = totaleNolo;
    }

    public RichOutputText getTotaleNolo() {
        return totaleNolo;
    }

    public void setTotaleCarburante(RichOutputText totaleCarburante) {
        this.totaleCarburante = totaleCarburante;
    }

    public RichOutputText getTotaleCarburante() {
        return totaleCarburante;
    }

    public void setImportoTotale(RichOutputText importoTotale) {
        this.importoTotale = importoTotale;
    }

    public RichOutputText getImportoTotale() {
        return importoTotale;
    }

    public void setSpagamento(RichSelectOneChoice spagamento) {
        this.spagamento = spagamento;
    }

    public RichSelectOneChoice getSpagamento() {
        return spagamento;
    }
}
