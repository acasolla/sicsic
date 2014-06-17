package it.abc.sicsic.view.backing.nautica;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;
import it.abc.sicsic.model.Barche;

import it.abc.sicsic.model.Clienti;
import it.abc.sicsic.model.Noleggio;
import it.abc.sicsic.model.NoleggioDTO;
import it.abc.sicsic.model.Skipper;

import it.abc.sicsic.view.backing.util.SicsicUtils;

import it.soulsoftware.utils.FacesUtils;
import it.soulsoftware.utils.Utils;
import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectMany;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.xml.crypto.Data;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_dettaglioNoleggio")
@RequestScoped

public class DettaglioNautica {
    private RichOutputText itData;
    private static SelectItem[] displayValuesBarca;
    private static SelectItem[] displayValuesCliente;
    private static SelectItem[] displayValuesSkipper;
    private static SelectItem[] displayValuesTipo;
    private RichSelectOneChoice sBarche;
    private RichInputDate idaNoleggio;
    private RichInputText itImportoNolo;
    private RichInputText itImportoCarburante;
    private RichInputText itNote;
    private RichPopup popupConfirm;
    private RichPopup noteWindow;
    private RichInputText itId;
    private RichDialog dialogConfirm;
    private String msg;
    private String msgPopup;
    private static final JavaServiceFacade serviveFacade = new JavaServiceFacade();
    private static Logger logger = Logger.getLogger(DettaglioNautica.class);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private RichSelectBooleanRadio raPagato;
    private RichSelectBooleanRadio raDaPagare;
    private RichSelectOneChoice sCliente;
    private RichSelectOneChoice sSkipper;
    private boolean pagamento;
    private SelectItem barcaSel;
    private SelectItem clienteSel;
    private SelectItem skipperSel;
    private RichInputText itIdBarca;
    private RichInputText itIdCliente;
    private RichInputText itIdSkipper;
    private String cognome;
    private String nome;
    private String ragSociale;
    public String natoIl;
    private RichCommandButton cbSalva;
    private RichInputText nomeBarcaTemp;

    public DettaglioNautica(){
        logger.info("DttaglioNautica start");
        fillComponents();
    }

    public void setItData(RichOutputText itData) {
        this.itData = itData;
    }

    public RichOutputText getItData() {
        return itData;
    }
    public SelectItem[] getDisplayValuesBarca(){
        JavaServiceFacade serviceFacade  = new JavaServiceFacade();
        List<Barche> l = serviceFacade.getBarcheFindAll();
        if (true) {
            displayValuesBarca = new SelectItem[l.size()];
            for (int i = 0; i < l.size(); i++) {
                displayValuesBarca[i] = new SelectItem(l.get(i), l.get(i).getNomeBarca());
            }            
        }
        return displayValuesBarca;
    }

    public SelectItem[] getDisplayValuesTipo(){
        List<String> l = new ArrayList<String>();
        l.add(Constants.Combo.LOCAZIONE);
        l.add(Constants.Combo.NOLEGGIO);
            displayValuesTipo = new SelectItem[2];
            for (int i = 0; i < l.size(); i++) {
                displayValuesTipo[i] = new SelectItem(l.get(i), l.get(i));
        }
        return displayValuesTipo;
    }

    public void setSBarche(RichSelectOneChoice barche) {
        this.sBarche = barche;
    }

    public RichSelectOneChoice getSBarche() {
        return sBarche;
    }

    public void setIdaNoleggio(RichInputDate idaNoleggio) {
        this.idaNoleggio = idaNoleggio;
    }

    public RichInputDate getIdaNoleggio() {
        return idaNoleggio;
    }

    public void setItImportoNolo(RichInputText itImportoNolo) {
        this.itImportoNolo = itImportoNolo;
    }

    public RichInputText getItImportoNolo() {
        return itImportoNolo;
    }

    public void setItImportoCarburante(RichInputText itImportoCarburante) {
        this.itImportoCarburante = itImportoCarburante;
    }

    public RichInputText getItImportoCarburante() {
        return itImportoCarburante;
    }

    public void setItNote(RichInputText itNote) {
        this.itNote = itNote;
    }

    public RichInputText getItNote() {
        return itNote;
    }
    
    public Date getData(){
        return ViewUtils.getDateValue(getIdaNoleggio());
    } 
    public Double getImportoNolo(){
        return ViewUtils.getDoubleValue(getItImportoNolo());    
    }
    public Double getImportoCarburante(){
        return ViewUtils.getDoubleValue(getItImportoCarburante());
    }
    public Barche getBarca(){
        return (Barche)ViewUtils.getObjectValue(getSBarche());    
    }
    public Skipper getSkipper(){
        return (Skipper)ViewUtils.getObjectValue(getSSkipper());     
    }
    public Clienti getClienti(){
        return (Clienti)ViewUtils.getObjectValue(getSCliente());  
    }
    public String getNote(){
        return ViewUtils.getStringValue(getItNote());
    }
    public boolean getPagato(){
        return ViewUtils.getBoolueanValue(getRaPagato());
    }
    public boolean getDaParare(){
        return ViewUtils.getBoolueanValue(getRaDaPagare());
    }
    public void salva(ActionEvent actionEvent) {

    }
    private int getId(){
        return ViewUtils.getIntValue(getItId());
    }
    
    private int getIdBarca(){
        return ViewUtils.getIntValue(getItIdBarca());
    }
    
    private int getIdCliente(){
        return ViewUtils.getIntValue(getItIdCliente());
    }
    
    private int getIdSkipper(){
        return ViewUtils.getIntValue(getItIdSkipper());
    }
    //
    private String getNomeBarca(){
        return ViewUtils.getStringValue(getNomeBarcaTemp());
    }
    
    private String getBarcaDaSoc(){
        return ViewUtils.getStringValue(getSBarche());
    }
    
    public Noleggio buildNoleggio(){
        Noleggio noleggio = new Noleggio();
        noleggio.setDataNoleggio(getData());
        noleggio.setImportoNolo(getImportoNolo());
        noleggio.setImportoCarburante(getImportoCarburante());        
        noleggio.setId(getId());
        noleggio.setNote(getNote());
        pagamento = (getPagato()) ? getPagato() : false;
        noleggio.setPagato(pagamento);        
        
        int bID = (getBarca()!=null) ? getBarca().getId() : 0;
        int cID = (getClienti()!=null) ? getClienti().getId(): 0;
        int kID = (getSkipper()!=null) ? getSkipper().getId(): 0;
        
        if(getId()!=0){
            if(bID==0)noleggio.setIdBarca(getIdBarca());
            else noleggio.setIdBarca(bID);
            if(cID==0)noleggio.setIdCliente(getIdCliente());
            else noleggio.setIdCliente(cID);
            if(kID==0)noleggio.setIdSkipper(getIdSkipper());
            else noleggio.setIdSkipper(kID);
            
        }else{
            noleggio.setIdBarca(bID);
            noleggio.setIdCliente(cID);
            noleggio.setIdSkipper(kID);
        }
        
//        if(getBarcaSel().getValue()=="")noleggio.setIdBarca(noleggio.getIdBarca());
//            else noleggio.setIdBarca(bID);
//        if(getClienteSel().getValue()=="")noleggio.setIdCliente(noleggio.getIdCliente());
//            else noleggio.setIdCliente(cID);
//        if(getSkipperSel().getValue()=="")noleggio.setIdSkipper(noleggio.getIdSkipper());
//            else noleggio.setIdSkipper(kID);
      
        logger.info(noleggio);
        return noleggio;
    }

    public void openPopup(ActionEvent actionEvent) {
        
            setMsgPopup("Vuoi salvare il noleggio");
        popupConfirm.show(ph);
    }

    public String actionBack() {
        return Constants.Place.GESTIONE_NOLEGGI;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public void dialogConfirmListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        Noleggio noleggio = buildNoleggio();
        boolean result = false;
        try {
            result = serviveFacade.persistOrMergeNoleggio(noleggio);
            if ( result ){
                FacesUtils.setSessionMapValue("reloadNoleggio", true);
                GestioneNautica n = (GestioneNautica) SicsicUtils.getValueExpression("backing_gestioneNautica");
                List<NoleggioDTO> noleggioTable  = n.getNoleggioTable();
                logger.info("noleggiotable=" + noleggioTable);
                AdfFacesContext.getCurrentInstance().addPartialTarget(n.getTNautic());
                
            }
        //   result = SicsicFacade.instance().persistOrMergeCliente(cliente);
        } catch (Exception e) {
            setMsg("Salvataggio fallito, error=" + e.getMessage());
        }
        setMsg("il noleggio e' stato salvato con successo");
        noteWindow.show(ph);
        }
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.no)) {
            return;
        }        
    }

    public void setNoteWindow(RichPopup noteWindow) {
        this.noteWindow = noteWindow;
    }

    public RichPopup getNoteWindow() {
        return noteWindow;
    }

    public void setItId(RichInputText itId) {
        this.itId = itId;
    }

    public RichInputText getItId() {
        return itId;
    }

    public void setDialogConfirm(RichDialog dialogConfirm) {
        this.dialogConfirm = dialogConfirm;
    }

    public RichDialog getDialogConfirm() {
        return dialogConfirm;
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
     
    public void setRaPagato(RichSelectBooleanRadio raPagato) {
        this.raPagato = raPagato;
    }

    public RichSelectBooleanRadio getRaPagato() {
        return raPagato;
    }

    public void setRaDaPagare(RichSelectBooleanRadio raDaPagare) {
        this.raDaPagare = raDaPagare;
    }
    
    public RichSelectBooleanRadio getRaDaPagare() {
        return raDaPagare;
    }

    public void setDisplayValuesCliente(SelectItem[] displayValuesCliente) {
        this.displayValuesCliente = displayValuesCliente;
    }

    public  SelectItem[] getDisplayValuesCliente() {
        JavaServiceFacade serviceFacade  = new JavaServiceFacade();
        List<Clienti> c = serviceFacade.getClientiFindAll();
        displayValuesCliente = new SelectItem[c.size()];
        //displayValuesCliente[0] = new SelectItem("","Seleziona Cliente");
        for (int i = 0; i < c.size(); i++) {
            if(!Utils.isNullOrEmpty(c.get(i).getNatoIl())){
                Date d = Utils.convertStringToDate(c.get(i).getNatoIl(), Constants.DatePatterns.yyyyMMdd);
                natoIl = Utils.convertDateToString(d, Constants.DatePatterns.dd_MM_yyyy);;
            }
                else natoIl = ""; 
            if(!Utils.isNullOrEmpty(c.get(i).getRagioneSociale()))ragSociale = c.get(i).getRagioneSociale(); 
                else ragSociale = "";
            displayValuesCliente[i] = new SelectItem(c.get(i), c.get(i).getCognomeNome().concat(" ").concat(natoIl));
        }
        return displayValuesCliente;
    }

    public void setDisplayValuesSkipper(SelectItem[] displayValuesSkipper) {
        this.displayValuesSkipper = displayValuesSkipper;
    }

    public  SelectItem[] getDisplayValuesSkipper() {
        JavaServiceFacade serviceFacade  = new JavaServiceFacade();
        List<Skipper> s = serviceFacade.getSkipperFindAll();
        displayValuesSkipper = new SelectItem[s.size()];
        displayValuesSkipper[0] = new SelectItem("");
        for (int i = 0; i < s.size(); i++) {
            String nome = "";
            String cognome = "";
            
            if(!Utils.isNullOrEmpty(s.get(i).getCognome()))cognome = s.get(i).getCognome();
            if(!Utils.isNullOrEmpty(s.get(i).getNome()))nome = s.get(i).getNome();
            
            displayValuesSkipper[i] = new SelectItem(s.get(i), nome.concat(" ").concat(cognome));
        }
        return displayValuesSkipper;
    }

    public void setSCliente(RichSelectOneChoice cliente) {
        this.sCliente = cliente;
    }

    public RichSelectOneChoice getSCliente() {
        return sCliente;
    }

    public void setSSkipper(RichSelectOneChoice skipper) {
        this.sSkipper = skipper;
    }

    public RichSelectOneChoice getSSkipper() {
        return sSkipper;
    }

    public void fillComponents(){

        NoleggioDTO noleggio = (NoleggioDTO)ViewUtils.getObjectFromSession(Constants.Place.INSERIMENTO_NOLEGGIO);
        if (noleggio == null)return;
        
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_NOLEGGIO, null);  
        logger.info("noleggio found, name=" + noleggio.getIdBarca());        
        
        itId = new RichInputText();
        itId.setValue(noleggio.getId());
        
        itIdBarca = new RichInputText();
        itIdBarca.setValue(noleggio.getIdBarca());
        
        itIdCliente = new RichInputText();
        itIdCliente.setValue(noleggio.getIdCliente());
        
        itIdSkipper = new RichInputText();
        itIdSkipper.setValue(noleggio.getIdSkipper());
        
        idaNoleggio = new RichInputDate();
        idaNoleggio.setValue(noleggio.getFormattedDate());
        
        itImportoCarburante = new RichInputText();
        itImportoCarburante.setValue(noleggio.getImportoCarburante());
        
        itImportoNolo =new RichInputText();
        itImportoNolo.setValue(noleggio.getImportoNolo());
        
        itNote =new RichInputText();
        itNote.setValue(noleggio.getNote());
        
        raPagato = new RichSelectBooleanRadio();
        raDaPagare = new RichSelectBooleanRadio();        
         
        if(noleggio.isPagamento()){
        raPagato.setValue(true);
        raDaPagare.setValue(false);
        }
        else{ 
            raDaPagare.setValue(true);
            raPagato.setValue(false);
        }
        logger.info("noleggio.isPagamento()= " + noleggio.isPagamento());
        //barcaSelezionata = new UISelectItem(); 
        //barcaSelezionata.setItemValue("sto cazzo");
        //barcaSelezionata.setItemLabel("sto cazzo");
        //sBarche.getChildren().add(barcaSelezionata);
    
        barcaSel = new SelectItem("",noleggio.getNomeBarca());
        
        if(!Utils.isNullOrEmpty(noleggio.getCognomeCliente()))cognome = noleggio.getCognomeCliente();
            else cognome = "";   
        if(!Utils.isNullOrEmpty(noleggio.getNomeCliente()))nome = noleggio.getNomeCliente();
            else nome = "";
        if(!Utils.isNullOrEmpty(noleggio.getRagioneSociale()))ragSociale = noleggio.getRagioneSociale(); 
            else ragSociale = "";
        
        clienteSel = new SelectItem("",nome+" "+cognome+" "+ragSociale);
        skipperSel = new SelectItem("",noleggio.getNomeSkipper());        
        
        nomeBarcaTemp = new RichInputText();
        nomeBarcaTemp.setValue(noleggio.getNomeBarca());
    }
    
    public SelectItem getBarcaSel(){
          logger.info(barcaSel == null ? "barca sel null" : "barcasel= "+ barcaSel.getLabel());
        if ( barcaSel == null) 
            return new SelectItem("","Seleziona una barca");
        else return  barcaSel;
    }
    
     public void setBarcaSel( SelectItem si){
         logger.info("setting barcaval=" + si);
         if (   si == null) return;
        
         else this.barcaSel = si;
         logger.info("setting barcaval=" + si.getLabel());
     }

    public void setClienteSel(SelectItem clienteSel) {
        this.clienteSel = clienteSel;
    }

    public SelectItem getClienteSel() {
        if ( clienteSel == null) 
            return new SelectItem("","Seleziona un cliente");
        else return  clienteSel;
    }

    public void setSkipperSel(SelectItem skipperSel) {
        this.skipperSel = skipperSel;
    }

    public SelectItem getSkipperSel() {
        if ( skipperSel == null) 
            return new SelectItem("","Seleziona uno skipper");
        else return  skipperSel;
    }

    public void setItIdBarca(RichInputText itIdBarca) {
        this.itIdBarca = itIdBarca;
    }

    public RichInputText getItIdBarca() {
        return itIdBarca;
    }

    public void setItIdCliente(RichInputText itIdCliente) {
        this.itIdCliente = itIdCliente;
    }

    public RichInputText getItIdCliente() {
        return itIdCliente;
    }

    public void setItIdSkipper(RichInputText itIdSkipper) {
        this.itIdSkipper = itIdSkipper;
    }

    public RichInputText getItIdSkipper() {
        return itIdSkipper;
    }

    public void setCbSalva(RichCommandButton cbSalva) {
        this.cbSalva = cbSalva;
    }

    public RichCommandButton getCbSalva() {
        return cbSalva;
    }

    public void setNomeBarcaTemp(RichInputText nomeBarcaTemp) {
        this.nomeBarcaTemp = nomeBarcaTemp;
    }

    public RichInputText getNomeBarcaTemp() {
        return nomeBarcaTemp;
    }
}
