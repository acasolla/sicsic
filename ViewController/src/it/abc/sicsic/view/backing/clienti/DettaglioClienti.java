package it.abc.sicsic.view.backing.clienti;

import com.sun.org.apache.bcel.internal.generic.Select;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;
import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;


import it.soulsoftware.cmc.mgwt.client.utils.CountryList;
import it.soulsoftware.utils.Utils;
import it.soulsoftware.utils.ViewUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailHeader;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.DisclosureEvent;

@ManagedBean(name = "backing_dettaglioClienti")
@RequestScoped

public class DettaglioClienti {
    private RichInputText itNome;
    private RichInputText itCognome;
    private RichInputText itRagSociale;
    private RichInputText itNatoA;
    private RichInputText itIndirizzo;
    //private RichInputText itSedeLegale;
    private RichInputText itCitta;
    private RichInputText itProvincia;
    private RichInputText itDocumento;
    private RichInputText itTelefono;
    private RichInputText itFax;
    private RichInputText itEmail;
    private RichPopup popupConfirm;
    private RichDialog dialogConfirm;
    private RichPopup noteWindow;
    private String msg;
    private String msgPopup;
    private static Logger logger = Logger.getLogger(DettaglioClienti.class);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private RichInputText itId;
    private RichInputText idId;
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private RichInputDate idaNatoIl;
    private RichShowDetailHeader showDetailPrivato;
    private RichShowDetailHeader showDetailAzienda;
    private RichSelectOneChoice tipoDocumento;
    private SelectItem tipoDocumentoSel;
    String[] tipoDoc = {"Passaporto", "Carta di identità", "Patente", "Patente nautica", "Carta di identita", "Partita iva", "altro"};
    private static SelectItem[] valuesTipoDocumento;
    private String nomeAzienda;
    private RichInputText itCfPiva;
    private RichSelectOneChoice socNazione;
    private SelectItem nazioneSel;
    private static SelectItem[] valuesNazione;
    String[] elencoNazioni = {"Italia","Germania","Francia"};
    private RichInputText itIdNazione;
    private RichInputText itIdTipoDocumento;


    public DettaglioClienti(){
        logger.info("DettaglioClienti start");
        showDetailAzienda = new RichShowDetailHeader();
        showDetailPrivato = new RichShowDetailHeader();
        getShowDetailPrivato().setDisclosed(true);
        getShowDetailAzienda().setDisclosed(false);
        ViewUtils.refreshComponents(getShowDetailPrivato(),getShowDetailAzienda());
                 
        fillComponents();
    }
    
    public void salva(ActionEvent actionEvent) {

    }



    public void setItNome(RichInputText nome) {
        this.itNome = nome;
    }

    public RichInputText getItNome() {
        return itNome;
    }

    public void setItCognome(RichInputText cognome) {
        this.itCognome = cognome;
    }

    public RichInputText getItCognome() {
        return itCognome;
    }

    public void setItRagSociale(RichInputText ragSociale) {
        this.itRagSociale = ragSociale;
    }

    public RichInputText getItRagSociale() {
        return itRagSociale;
    }


    public void setItNatoA(RichInputText natoA) {
        this.itNatoA = natoA;
    }

    public RichInputText getItNatoA() {
        return itNatoA;
    }

    public void setItIndirizzo(RichInputText indirizzo) {
        this.itIndirizzo = indirizzo;
    }

    public RichInputText getItIndirizzo() {
        return itIndirizzo;
    }

//    public void setItSedeLegale(RichInputText sedeLegale) {
//        this.itSedeLegale = sedeLegale;
//    }
//
//    public RichInputText getItSedeLegale() {
//        return itSedeLegale;
//    }

    public void setItCitta(RichInputText citta) {
        this.itCitta = citta;
    }

    public RichInputText getItCitta() {
        return itCitta;
    }

    public void setItProvincia(RichInputText provincia) {
        this.itProvincia = provincia;
    }

    public RichInputText getItProvincia() {
        return itProvincia;
    }

    public void setItDocumento(RichInputText documento) {
        this.itDocumento = documento;
    }

    public RichInputText getItDocumento() {
        return itDocumento;
    }

    public void setItTelefono(RichInputText telefono) {
        this.itTelefono = telefono;
    }

    public RichInputText getItTelefono() {
        return itTelefono;
    }

    public void setItFax(RichInputText fax) {
        this.itFax = fax;
    }

    public RichInputText getItFax() {
        return itFax;
    }

    public void setItEmail(RichInputText email) {
        this.itEmail = email;
    }

    public RichInputText getItEmail() {
        return itEmail;
    }
    
    private String getNome(){
        return ViewUtils.getStringValue(getItNome());   
    }
    private String getCognome(){
        return ViewUtils.getStringValue(getItCognome());   
    }
    private String getRagSociale(){
        return ViewUtils.getStringValue(getItRagSociale());   
    }
    private Date getNatoIl(){
        return ViewUtils.getDateValue(getIdaNatoIl());
    }
    private String getNatoA(){
        return ViewUtils.getStringValue(getItNatoA());
    }
    private String getIndirizzo(){
        return ViewUtils.getStringValue(getItIndirizzo());
    }
    //private String getSedeLegale(){
      //  return ViewUtils.getStringValue(getItSedeLegale());
    //}
    private String getCitta(){
        return ViewUtils.getStringValue(getItCitta());
    }
    private String getProvincia(){
        return ViewUtils.getStringValue(getItProvincia());
    } 
 
    private String getDocumento(){
        return ViewUtils.getStringValue(getItDocumento());
    }
    private String getCfPiva(){
        return ViewUtils.getStringValue(getItCfPiva());
    }
    private String getTelefono(){
        return ViewUtils.getStringValue(getItTelefono());
    }
    private String getFax(){
        return ViewUtils.getStringValue(getItFax());
    }
    private String getEmai(){
        return ViewUtils.getStringValue(getItEmail());
    }
    private int getId(){
        return ViewUtils.getIntValue(getItId());
    }
    private String getTipoDoc(){
        return ViewUtils.getStringValue(getTipoDocumento());
    }

    private String getNazione(){
        return ViewUtils.getStringValue(getSocNazione());
    }
    private String getIdNazione(){
        return ViewUtils.getStringValue(getItIdNazione());
    }
    private String getIdTipoDocumento(){
        return ViewUtils.getStringValue(getItIdTipoDocumento());
    }
    
    /** SETTA VALORI DEL CLIENTE */
    private Clienti buildCliente(){
        Clienti cliente = new Clienti();
        cliente.setId(getId());
        cliente.setNome(getNome());
        cliente.setCognome(getCognome());
        cliente.setNatoIl(getNatoIl());
        cliente.setNatoA(getNatoA());
        cliente.setRagioneSociale(getRagSociale());
        cliente.setIndirizzo(getIndirizzo());
        //cliente.setSedeLegale(getSedeLegale());
        cliente.setProvincia(getProvincia());
        cliente.setCitta(getCitta());
        cliente.setDocumento(getDocumento());
        cliente.setTelefono(getTelefono());
        cliente.setFax(getFax());
        cliente.setEmail(getEmai());
        if(getCfPiva()==null){
        cliente.setTipoDocumento(getTipoDoc());
        }else{
        cliente.setPartitaIva(getCfPiva());
        cliente.setTipoDocumento("CF/P.iva");
        }   
        logger.info("CFPIVA = "+getCfPiva());
        cliente.setNazione(getNazione());
        
        String nazioneTemp = (getNazione()!=null ? getNazione() : "");
        
        if(nazioneTemp == "")cliente.setNazione(getIdNazione());
        else cliente.setNazione(nazioneTemp);
        
        String tipoDocumentoTemp = (getTipoDoc()!=null ? getTipoDoc() : "");
        
        if(tipoDocumentoTemp == "")cliente.setTipoDocumento(getIdTipoDocumento());
        else cliente.setTipoDocumento(tipoDocumentoTemp);
        
        return cliente;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
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

    public void dialogConfirmListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
    //    JavaServiceFacade serviceFacade = new JavaServiceFacade();
        Clienti cliente = buildCliente();
        boolean result = false;
        try {
            result = serviceFacade.persistOrMergeCliente(cliente);
        //   result = SicsicFacade.instance().persistOrMergeCliente(cliente);
        } catch (Exception e) {
            setMsg("Salvataggio fallito, error=" + e.getMessage());
        }
        
            if(Utils.isNullOrEmpty(getNome()))
                nomeAzienda = getRagSociale();
            else
                nomeAzienda = getNome();
            
        setMsg("Il cliente "+nomeAzienda+" è stata salvata con successo");
        noteWindow.show(ph);
        
        }
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.no)) {
            return;
        }
    }

    public String getMsgPopup() {        
        return msgPopup;
    }
    
    public void setMsgPopup( String msg ){
        this.msgPopup = msg;       
    }
    public void openPopup(ActionEvent actionEvent) {        
       
        if(Utils.isNullOrEmpty(getNome()))
            nomeAzienda = getRagSociale();
        else
            nomeAzienda = getNome();
        
            setMsgPopup("Vuoi salvare il contatto: " +  nomeAzienda);
            popupConfirm.show(ph);       
    }
    
    private void fillComponents(){
        Clienti cliente = (Clienti)ViewUtils.getObjectFromSession(Constants.Place.INSERIMENTO_CLIENTE);
        if ( cliente == null ) return;
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_CLIENTE, null);
        logger.info("cliente found, RAGIONE SOCIALE=" + cliente.getRagioneSociale() );
        
        itId = new RichInputText();
        itId.setValue(cliente.getId());
        
        itNome = new RichInputText();
        itNome.setValue(cliente.getNome());
     
        itCognome = new RichInputText();
        itCognome.setValue(cliente.getCognome());
        
        idaNatoIl = new RichInputDate();
        idaNatoIl.setValue(cliente.getFormattedDate());
        
        itNatoA = new RichInputText();
        itNatoA.setValue(cliente.getNatoA());
        
        itIndirizzo = new RichInputText();
        itIndirizzo.setValue(cliente.getIndirizzo());
        
        //itSedeLegale = new RichInputText();
        //itSedeLegale.setValue(cliente.getSedeLegale());
        
        itCitta = new RichInputText();
        itCitta.setValue(cliente.getCitta());
        
        itProvincia = new RichInputText();
        itProvincia.setValue(cliente.getProvincia());
        
        itDocumento = new RichInputText();
        itDocumento.setValue(cliente.getDocumento());
        
        itCfPiva = new RichInputText();
        itCfPiva.setValue(cliente.getPartitaIva());
        
        itTelefono = new RichInputText();
        itTelefono.setValue(cliente.getTelefono());
        
        itFax = new RichInputText();
        itFax.setValue(cliente.getFax());
        
        itEmail = new RichInputText();
        itEmail.setValue(cliente.getEmail());
        
        itRagSociale = new RichInputText();
        itRagSociale.setValue(cliente.getRagioneSociale());
        
        showDetailAzienda = new RichShowDetailHeader();
        showDetailPrivato = new RichShowDetailHeader();
        
        tipoDocumentoSel = new SelectItem("",cliente.getTipoDocumento());
        nazioneSel = new SelectItem("",cliente.getNazione());
        itIdNazione = new RichInputText();
        itIdNazione.setValue(cliente.getNazione());
        
        itIdTipoDocumento = new RichInputText();
        itIdTipoDocumento.setValue(cliente.getTipoDocumento());
                
        if(Utils.isNullOrEmpty(cliente.getRagioneSociale())){
            logger.info("Senza Ragione");
            getShowDetailPrivato().setDisclosed(true);
            getShowDetailAzienda().setDisclosed(false);
        }
        else{
            logger.info("Senza Nome");
            getShowDetailAzienda().setDisclosed(true);
            getShowDetailPrivato().setDisclosed(false);
            logger.info("getShowDetailAzienda() =  "+getShowDetailAzienda().isDisclosed());
        }
        ViewUtils.refreshComponents(getShowDetailPrivato(),getShowDetailAzienda());
           
    }
    
    public String actionBack(){
        return Constants.Place.GESTIONE_CLIENTI;
    }


    public void setItId(RichInputText itId) {
        this.itId = itId;
    }

    public RichInputText getItId() {
        return itId;
    }

    public void setIdId(RichInputText idId) {
        this.idId = idId;
    }

    public RichInputText getIdId() {
        return idId;
    }

    public void setIdaNatoIl(RichInputDate idaNatoIl) {
        this.idaNatoIl = idaNatoIl;
    }

    public RichInputDate getIdaNatoIl() {
        return idaNatoIl;
    }


    public void showDetailPrivatoAction(DisclosureEvent disclosureEvent) {
        if(!getShowDetailAzienda().isDisclosed())
            getShowDetailAzienda().setDisclosed(true);
        else
            getShowDetailAzienda().setDisclosed(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getShowDetailAzienda());
    }

    public void setShowDetailPrivato(RichShowDetailHeader showDetailPrivato) {
        this.showDetailPrivato = showDetailPrivato;
    }

    public RichShowDetailHeader getShowDetailPrivato() {
        return showDetailPrivato;
    }

    public void setShowDetailAzienda(RichShowDetailHeader showDetailAzienda) {
        this.showDetailAzienda = showDetailAzienda;
    }

    public RichShowDetailHeader getShowDetailAzienda() {
        return showDetailAzienda;
    }

    public void showDetailAziendaAction(DisclosureEvent disclosureEvent) {
        if(!getShowDetailPrivato().isDisclosed())
            getShowDetailPrivato().setDisclosed(true);
        else
            getShowDetailPrivato().setDisclosed(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getShowDetailPrivato());
    }

    public void setTipoDocumento(RichSelectOneChoice selectValueTipoDocumento) {
        this.tipoDocumento = selectValueTipoDocumento;
    }

    public RichSelectOneChoice getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumentoSel(SelectItem td) {
        if (   td == null) return;        
        else this.tipoDocumentoSel = td;
    }

    public SelectItem getTipoDocumentoSel() {
        
        if ( tipoDocumentoSel == null) 
            return new SelectItem("","Seleziona tipo documento");
        else return  tipoDocumentoSel;
    }

    public SelectItem[] getValuesTipoDocumento() {
            List<String> td= Arrays.asList(tipoDoc);
    
            if (true) {
                valuesTipoDocumento = new SelectItem[td.size()];
                for (int i = 0; i < td.size(); i++) {
                    valuesTipoDocumento[i] = new SelectItem(td.get(i), td.get(i));
                }            
            }
            return valuesTipoDocumento;
        }

    public void setItCfPiva(RichInputText itCfPiva) {
        this.itCfPiva = itCfPiva;
    }

    public RichInputText getItCfPiva() {
        return itCfPiva;
    }

    public void setSocNazione(RichSelectOneChoice socNazione) {
        this.socNazione = socNazione;
    }

    public RichSelectOneChoice getSocNazione() {
        return socNazione;
    }

    public void setNazioneSel(SelectItem na) {
        logger.info("setting nazione = "+na);
        if (   na == null) return;        
        else this.nazioneSel = na;
        logger.info("setting nazione=" + na.getLabel());
    }

    public SelectItem getNazioneSel() {
        logger.info(nazioneSel == null ? "nazione sel null" : "nazioneSel= "+ nazioneSel.getLabel());
        if ( nazioneSel == null) 
            return new SelectItem("","Seleziona nazione");
        else return  nazioneSel;
    }

    public static void setValuesNazione(SelectItem[] valuesNazione) {
        DettaglioClienti.valuesNazione = valuesNazione;
    }

    public SelectItem[] getValuesNazione() {
        List<String> en= CountryList.getAllCountries();
        
        if (true) {
            valuesNazione = new SelectItem[en.size()];
            for (int i = 0; i < en.size(); i++) {
                valuesNazione[i] = new SelectItem(en.get(i), en.get(i));
            }            
        }
        return valuesNazione;
    }

    public void setItIdNazione(RichInputText itIdNazione) {
        this.itIdNazione = itIdNazione;
    }

    public RichInputText getItIdNazione() {
        return itIdNazione;
    }

    public void setItIdTipoDocumento(RichInputText itIdTipoDocumento) {
        this.itIdTipoDocumento = itIdTipoDocumento;
    }

    public RichInputText getItIdTipoDocumento() {
        return itIdTipoDocumento;
    }
}
