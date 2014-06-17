package it.abc.sicsic.view.backing.skipper;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;

import it.abc.sicsic.model.Skipper;

import it.soulsoftware.utils.ViewUtils;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_dettaglioSkipper")
@RequestScoped

public class DettaglioSkipper {
    private RichInputText itNome;
    private RichInputText itNatoA;
    private RichInputText itResidenza;
    private RichInputText itPatenteNautica;
    private RichInputText itRilasciataDa;
    private RichInputText itId;
    private RichPopup popupConfirm;
    private RichDialog dialogConfirm;
    private RichPopup noteWindow;
    private String msg;
    private String msgPopup;
    private static final JavaServiceFacade serviveFacade = new JavaServiceFacade();
    private static Logger logger = Logger.getLogger(DettaglioSkipper.class);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private RichInputDate idaNatoIl;
    private RichInputText itCognome;
    private RichInputText itIndirizzo;
    private RichInputDate itRilasciataIl;

    public DettaglioSkipper(){
        logger.info("DettaglioSkipper start");
        fillComponents();
    }
    
    public void setItNome(RichInputText itNome) {
        this.itNome = itNome;
    }

    public RichInputText getItNome() {
        return itNome;
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

    public void setItPatenteNautica(RichInputText itPatenteNauitica) {
        this.itPatenteNautica = itPatenteNauitica;
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
    private Date getNatoIl(){
        return ViewUtils.getDateValue(getIdaNatoIl());
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
    private Date getRilasciataIl(){
        return ViewUtils.getDateValue(getItRilasciataIl());
    }
    private int getId(){
        return ViewUtils.getIntValue(getItId());
    }
    private String getCognome(){
        return  ViewUtils.getStringValue(getItCognome());
    }
    private String getIndirizzo(){
        return ViewUtils.getStringValue(getItIndirizzo());
    }

    public void salva(ActionEvent actionEvent) {

    }
    private Skipper buildSkipper(){
        Skipper skipper = new Skipper();
        skipper.setId(getId());
        skipper.setNome(getNome());
        skipper.setNatoIl(getNatoIl());
        skipper.setNatoA(getNatoA());
        skipper.setResidenza(getResidenza());
        skipper.setNumPatenteNautica(getPatenteNautica());
        skipper.setRilasciataDa(getRilasciataDa());
        skipper.setCognome(getCognome());
        skipper.setIndirizzo(getIndirizzo());
        skipper.setRilasciataIl(getRilasciataIl());
        return skipper;
    }

    public void setItId(RichInputText itId) {
        this.itId = itId;
    }

    public RichInputText getItId() {
        return itId;
    }

    public void openPopup(ActionEvent actionEvent) {
        setMsgPopup("Vuoi salvare lo skipper: " +  getNome());
        popupConfirm.show(ph);
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public void dialogConfirmListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        //    JavaServiceFacade serviceFacade = new JavaServiceFacade();
            Skipper skipper = buildSkipper();
            boolean result = false;
            try {
                result = serviveFacade.persistOrMergeSkipper(skipper);
            //   result = SicsicFacade.instance().persistOrMergeCliente(cliente);
            } catch (Exception e) {
                setMsg("Salvataggio fallito, error=" + e.getMessage());
            }
            setMsg("lo skipper "+skipper.getNome()+" è stato salvato con successo");
            noteWindow.show(ph);
           
        }
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.no)) {
            return;
        }
            
    }

    public void setDialogConfirm(RichDialog dialogConfirm) {
        this.dialogConfirm = dialogConfirm;
    }

    public RichDialog getDialogConfirm() {
        return dialogConfirm;
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

    public void setMsgPopup(String msgPopup) {
        this.msgPopup = msgPopup;
    }

    public String getMsgPopup() {
        return msgPopup;
    }
    
    private void fillComponents(){
        Skipper skipper = (Skipper)ViewUtils.getObjectFromSession(Constants.Place.INSERIMENTO_SKIPPER);
        if(skipper == null)return;
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_SKIPPER, null);
        logger.info("skipper found, name=" + skipper.getNome());
        
        itId = new RichInputText();
        itId.setValue(skipper.getId());
        
        itNatoA = new RichInputText();
        itNatoA.setValue(skipper.getNatoA());
        
        itNome = new RichInputText();
        itNome.setValue(skipper.getNome());
        
        itPatenteNautica = new RichInputText();
        itPatenteNautica.setValue(skipper.getNumPatenteNautica());
        
        itResidenza = new RichInputText();
        itResidenza.setValue(skipper.getResidenza());
        
        itRilasciataDa = new RichInputText();
        itRilasciataDa.setValue(skipper.getRilasciataDa()); 
        
        itCognome = new RichInputText();
        itCognome.setValue(skipper.getCognome());
        
        itIndirizzo = new RichInputText();
        itIndirizzo.setValue(skipper.getIndirizzo());        
        
        idaNatoIl = new RichInputDate();
        idaNatoIl.setValue(skipper.getFormattedDate());
        
        itRilasciataIl = new RichInputDate();
        itRilasciataIl.setValue(skipper.getFormattedDateRilasciata());
    }
    public String actionBack(){
        return Constants.Place.GESTIONE_SKIPPER;
    }

    public void setIdaNatoIl(RichInputDate idaNatoIl) {
        this.idaNatoIl = idaNatoIl;
    }

    public RichInputDate getIdaNatoIl() {
        return idaNatoIl;
    }

    public void setItCognome(RichInputText itCognome) {
        this.itCognome = itCognome;
    }

    public RichInputText getItCognome() {
        return itCognome;
    }

    public void setItIndirizzo(RichInputText itIndirizzo) {
        this.itIndirizzo = itIndirizzo;
    }

    public RichInputText getItIndirizzo() {
        return itIndirizzo;
    }

    public void setItRilasciataIl(RichInputDate itRilasciataIl) {
        this.itRilasciataIl = itRilasciataIl;
    }

    public RichInputDate getItRilasciataIl() {
        return itRilasciataIl;
    }
}
