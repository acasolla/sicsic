package it.abc.sicsic.view.backing.barche;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;

import it.abc.sicsic.model.Barche;
import it.abc.sicsic.view.backing.clienti.DettaglioClienti;

import it.soulsoftware.utils.ViewUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_dettaglioBarche")
@RequestScoped

public class DettaglioBarche {
    private RichInputText itNomeBarca;
    private RichInputText itSiglaIsrcizione;
    private RichInputText itCostruitaDa;
    private RichInputText itSerialeScafo;
    private RichInputText itLunghezza;
    private RichInputText itPescaggio;
    private RichInputText itMotorizzazione;
    private RichInputText itCapacitaGasolio;
    private RichInputText itNumMinEquipaggio;
    private RichInputText itNumMaxPersone;
    private RichInputText itBandiera;
    private RichInputText itAnnoCostruzione;
    private RichInputText itTipoModello;
    private RichInputText itCertificazioneCE;
    private RichInputText itLarghezza;
    private RichInputText itDislocamento;
    private RichInputText itMatricoleMotori;
    private RichInputText itCapacitaAcqua;
    private RichInputText itLimitiNavigazione;
    private RichInputText itAssPolizzaCorpi;
    private RichInputText itAssPolizzaRC;
    private RichInputText itNumeroRegistro;
    private RichPopup popupConfirm;
    private RichDialog dialogConfirm;
    private String nomeBarcaPopup;
    private String msg;
    private RichPopup noteWindow;
    private String msgPopup;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private static Logger logger = Logger.getLogger(DettaglioBarche.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private RichInputText itId;

    public DettaglioBarche() {
        logger.info("DettaglioBarche start");
        fillComponents();
    }
    
    public void salva(ActionEvent actionEvent) {
   }
    
    public void setItNomeBarca(RichInputText itNomeBarca) {
        this.itNomeBarca = itNomeBarca;
    }

    public RichInputText getItNomeBarca() {
        return itNomeBarca;
    }

    public void setItSiglaIsrcizione(RichInputText itSiglaIsrcizione) {
        this.itSiglaIsrcizione = itSiglaIsrcizione;
    }

    public RichInputText getItSiglaIsrcizione() {
        return itSiglaIsrcizione;
    }

    public void setItCostruitaDa(RichInputText itCostruitaDa) {
        this.itCostruitaDa = itCostruitaDa;
    }

    public RichInputText getItCostruitaDa() {
        return itCostruitaDa;
    }

    public void setItSerialeScafo(RichInputText itSerialeScafo) {
        this.itSerialeScafo = itSerialeScafo;
    }

    public RichInputText getItSerialeScafo() {
        return itSerialeScafo;
    }

    public void setItLunghezza(RichInputText itLunghezza) {
        this.itLunghezza = itLunghezza;
    }

    public RichInputText getItLunghezza() {
        return itLunghezza;
    }

    public void setItPescaggio(RichInputText itPescaggio) {
        this.itPescaggio = itPescaggio;
    }

    public RichInputText getItPescaggio() {
        return itPescaggio;
    }

    public void setItMotorizzazione(RichInputText itMotorizzazione) {
        this.itMotorizzazione = itMotorizzazione;
    }

    public RichInputText getItMotorizzazione() {
        return itMotorizzazione;
    }

    public void setItCapacitaGasolio(RichInputText itCapacitaGasolio) {
        this.itCapacitaGasolio = itCapacitaGasolio;
    }

    public RichInputText getItCapacitaGasolio() {
        return itCapacitaGasolio;
    }

    public void setItNumMinEquipaggio(RichInputText itNumMinEquipaggio) {
        this.itNumMinEquipaggio = itNumMinEquipaggio;
    }

    public RichInputText getItNumMinEquipaggio() {
        return itNumMinEquipaggio;
    }

    public void setItNumMaxPersone(RichInputText itNumMaxPersone) {
        this.itNumMaxPersone = itNumMaxPersone;
    }

    public RichInputText getItNumMaxPersone() {
        return itNumMaxPersone;
    }

    public void setItBandiera(RichInputText itBandiera) {
        this.itBandiera = itBandiera;
    }

    public RichInputText getItBandiera() {
        return itBandiera;
    }

    public void setItAnnoCostruzione(RichInputText itAnnoCostruzione) {
        this.itAnnoCostruzione = itAnnoCostruzione;
    }

    public RichInputText getItAnnoCostruzione() {
        return itAnnoCostruzione;
    }

    public void setItTipoModello(RichInputText itTipoModello) {
        this.itTipoModello = itTipoModello;
    }

    public RichInputText getItTipoModello() {
        return itTipoModello;
    }

    public void setItCertificazioneCE(RichInputText itCertificazioneCE) {
        this.itCertificazioneCE = itCertificazioneCE;
    }

    public RichInputText getItCertificazioneCE() {
        return itCertificazioneCE;
    }

    public void setItLarghezza(RichInputText itLarghezza) {
        this.itLarghezza = itLarghezza;
    }

    public RichInputText getItLarghezza() {
        return itLarghezza;
    }

    public void setItDislocamento(RichInputText itDislocamento) {
        this.itDislocamento = itDislocamento;
    }

    public RichInputText getItDislocamento() {
        return itDislocamento;
    }

    public void setItMatricoleMotori(RichInputText itMatricoleMotori) {
        this.itMatricoleMotori = itMatricoleMotori;
    }

    public RichInputText getItMatricoleMotori() {
        return itMatricoleMotori;
    }

    public void setItCapacitaAcqua(RichInputText itCapacitaAcqua) {
        this.itCapacitaAcqua = itCapacitaAcqua;
    }

    public RichInputText getItCapacitaAcqua() {
        return itCapacitaAcqua;
    }

    public void setItLimitiNavigazione(RichInputText itLimitiNavigazione) {
        this.itLimitiNavigazione = itLimitiNavigazione;
    }

    public RichInputText getItLimitiNavigazione() {
        return itLimitiNavigazione;
    }

    public void setItAssPolizzaCorpi(RichInputText itAssPolizzaCorpi) {
        this.itAssPolizzaCorpi = itAssPolizzaCorpi;
    }

    public RichInputText getItAssPolizzaCorpi() {
        return itAssPolizzaCorpi;
    }

    public void setItAssPolizzaRC(RichInputText itAssPolizzaRC) {
        this.itAssPolizzaRC = itAssPolizzaRC;
    }

    public RichInputText getItAssPolizzaRC() {
        return itAssPolizzaRC;
    }

    public void setItNumeroRegistro(RichInputText itNumeroRegistro) {
        this.itNumeroRegistro = itNumeroRegistro;
    }

    public RichInputText getItNumeroRegistro() {
        return itNumeroRegistro;
    }
    
    private String getNomeBarca(){
        return ViewUtils.getStringValue(getItNomeBarca());
    }
    

    
    private String getSiglaIsrcizione(){
        return ViewUtils.getStringValue(getItSiglaIsrcizione());
    }
    private String getCostruitaDa(){
        return ViewUtils.getStringValue(getItCostruitaDa());    
    }
    private String getSerialeScafo(){
        return ViewUtils.getStringValue(getItSerialeScafo());
    }
    private Double getLunghezza(){
        return ViewUtils.getDoubleValue(getItLunghezza());
    }
    private Double getPescaggio(){
        return ViewUtils.getDoubleValue(getItPescaggio());
    }
    private String getMotorizzazione(){
        return ViewUtils.getStringValue(getItMotorizzazione());
    }
    private Double getCapacitaGasolio(){
        return ViewUtils.getDoubleValue(getItCapacitaGasolio());
    }
    private int getNumMinEquipaggio(){
        return ViewUtils.getIntValue(getItNumMinEquipaggio());
    }
    private int getNumMaxPersone(){
        return ViewUtils.getIntValue(getItNumMaxPersone());
    }
    private String getBandiera(){
        return ViewUtils.getStringValue(getItBandiera());
    }
    private int getAnnoCostruzione(){
        return ViewUtils.getIntValue(getItAnnoCostruzione());
    }
    private String getTipoModello(){
        return ViewUtils.getStringValue(getItTipoModello());
    }
    private String getCertificazioneCE(){
        return ViewUtils.getStringValue(getItCertificazioneCE());
    }
    private Double getLarghezza(){
        return ViewUtils.getDoubleValue(getItLarghezza());
    }
    private Double getDislocamento(){
        return ViewUtils.getDoubleValue(getItDislocamento());
    }
    private String getMatricoleMotori(){
        return ViewUtils.getStringValue(getItMatricoleMotori());
    }
    private Double getCapacitaAcqua(){
        return ViewUtils.getDoubleValue(getItCapacitaAcqua());
    }
    private String getLimitiNavigazione(){
        return ViewUtils.getStringValue(getItLimitiNavigazione());
    }
    private String getAssPolizzaCorpi(){
        return ViewUtils.getStringValue(getItAssPolizzaCorpi());
    }
    private String getAssPolizzaRC(){
        return ViewUtils.getStringValue(getItAssPolizzaRC());
    }
    private String getNumeroRegistro(){
        return ViewUtils.getStringValue(getItNumeroRegistro());
    }
    private int getId(){
        return ViewUtils.getIntValue(getItId());
    }
    
    /** SETTA VALORI DELLA BARCA */
    private Barche buildBarche(){
        Barche barche = new Barche();
        barche.setId(getId());
        barche.setNomeBarca(getNomeBarca());
        barche.setSiglaIscrizione(getSiglaIsrcizione());
        barche.setCostruitaDa(getCostruitaDa());
        barche.setSerialeScafo(getSerialeScafo());
        barche.setLunghezza(getLunghezza());
        barche.setPescaggio(getPescaggio());
        barche.setMotorizzazione(getMotorizzazione());
        barche.setCapacitaGasolio(getCapacitaGasolio());
        barche.setNumMinEquipaggio(getNumMinEquipaggio());
        barche.setNumMaxEquipaggio(getNumMaxPersone());
        barche.setBandiera(getBandiera());
        barche.setAnnoCostruzione(getAnnoCostruzione());
        barche.setTipoModello(getTipoModello());
        barche.setCertificazioneCE(getCertificazioneCE());
        barche.setLarghezza(getLarghezza());
        barche.setDislocamento(getDislocamento());
        barche.setMatricoleMotori(getMatricoleMotori());
        barche.setCapacitaAcqua(getCapacitaAcqua());
        barche.setLimitiNavigazione(getLimitiNavigazione());
        barche.setAssPolizzaCorpi(getAssPolizzaCorpi());
        barche.setAssPolizzaRC(getAssPolizzaRC());
        barche.setNumeroRegistro(getNumeroRegistro());
        return barche;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public void dialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        Barche barca = buildBarche();
        boolean result = false;       

        try {              
            result = serviceFacade.persistOrMergeBarca(barca);
            } catch (Exception e) {
                setMsg("Salvataggio fallito, error=" + e.getMessage());
            }
            setMsg("la barca "+barca.getNomeBarca()+" è stata salvata con successo");
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
    
    public void openPopup(ActionEvent actionEvent) {
        setMsgPopup("Vuoi salvare la barca: " +  getNomeBarca());
        popupConfirm.show(ph);
    }

    public String getNomeBarcaPopup() {        
        return nomeBarcaPopup = "Vuoi salvare la barca: "+ getNomeBarca();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setNoteWindow(RichPopup noteWindow) {
        this.noteWindow = noteWindow;
    }

    public RichPopup getNoteWindow() {
        return noteWindow;
    }

    public void setMsgPopup(String msg) {
        this.msgPopup = msg;
    }

    public String getMsgPopup() {
        return msgPopup;
    }
    
    private void fillComponents(){
        Barche barche = (Barche)ViewUtils.getObjectFromSession(Constants.Place.INSERIMENTO_BARCA);
        if (barche == null)return;
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_BARCA, null);
        logger.info("barca found name=" + barche.getNomeBarca());
        
        itId = new RichInputText();
        itId.setValue(barche.getId());
        
        itAnnoCostruzione = new RichInputText();
        itAnnoCostruzione.setValue(barche.getAnnoCostruzione());
        
        itAssPolizzaCorpi = new RichInputText();
        itAssPolizzaCorpi.setValue(barche.getAssPolizzaCorpi());
        
        itAssPolizzaRC = new RichInputText();
        itAssPolizzaRC.setValue(barche.getAssPolizzaRC());
        
        itBandiera = new RichInputText();
        itBandiera.setValue(barche.getBandiera());
        
        itCapacitaAcqua = new RichInputText();
        itCapacitaAcqua.setValue(barche.getCapacitaAcqua());
        
        itCapacitaGasolio = new RichInputText();
        itCapacitaGasolio.setValue(barche.getCapacitaGasolio());
        
        itCertificazioneCE = new RichInputText();
        itCertificazioneCE.setValue(barche.getCertificazioneCE());
        
        itCostruitaDa = new RichInputText();
        itCostruitaDa.setValue(barche.getCostruitaDa());
        
        itDislocamento = new RichInputText();
        itDislocamento.setValue(barche.getDislocamento());
        
        itLarghezza = new RichInputText();
        itLarghezza.setValue(barche.getLarghezza());
        
        itLimitiNavigazione = new RichInputText();
        itLimitiNavigazione.setValue(barche.getLimitiNavigazione());
        
        itLunghezza = new RichInputText();
        itLunghezza.setValue(barche.getLunghezza());
        
        itMatricoleMotori = new RichInputText();
        itMatricoleMotori.setValue(barche.getMatricoleMotori());
        
        itMotorizzazione = new RichInputText();
        itMotorizzazione.setValue(barche.getMotorizzazione());
        
        itNomeBarca = new RichInputText();
        itNomeBarca.setValue(barche.getNomeBarca());
        
        itNumMaxPersone = new RichInputText();
        itNumMaxPersone.setValue(barche.getNumMaxEquipaggio());
        
        itNumMinEquipaggio = new RichInputText();
        itNumMinEquipaggio.setValue(barche.getNumMinEquipaggio());
        
        itNumeroRegistro = new RichInputText();
        itNumeroRegistro.setValue(barche.getNumeroRegistro());
        
        itPescaggio = new RichInputText();
        itPescaggio.setValue(barche.getPescaggio());
        
        itSerialeScafo = new RichInputText();
        itSerialeScafo.setValue(barche.getSerialeScafo());
        
        itSiglaIsrcizione = new RichInputText();
        itSiglaIsrcizione.setValue(barche.getSiglaIscrizione());
        
        itTipoModello = new RichInputText();
        itTipoModello.setValue(barche.getTipoModello());
        
    }
    
    public String actionBack(){
        return Constants.Place.GESTIONE_BARCHE;
    }


    public void setItId(RichInputText itId) {
        this.itId = itId;
    }

    public RichInputText getItId() {
        return itId;
    }
}
