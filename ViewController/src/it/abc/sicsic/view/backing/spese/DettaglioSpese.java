    package it.abc.sicsic.view.backing.spese;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;
import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Spese;

import it.abc.sicsic.model.SpeseDTO;
import it.abc.sicsic.view.backing.nautica.DettaglioNautica;

import it.abc.sicsic.view.backing.util.SicsicUtils;

import it.soulsoftware.utils.Utils;
import it.soulsoftware.utils.ViewUtils;

import java.util.Arrays;
import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.xml.crypto.Data;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_dettaglioSpese")
@RequestScoped

public class DettaglioSpese {


    private RichInputText itImportoSpese;
    private static SelectItem[] displayValues;
    private RichSelectOneChoice rsBarca;
    private RichInputText itNote;
    private RichInputDate idData;
    private RichPopup popupConfirm;
    private RichDialog dialogConfirm;
    private RichPopup noteWindow;
    private RichInputText itId;
    private String msg;
    private String msgPopup;    
    private static final JavaServiceFacade serviveFacade = new JavaServiceFacade();
    private static Logger logger = Logger.getLogger(DettaglioNautica.class);
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
    private SelectItem barcaSel;
    private RichSelectBooleanRadio raPagatoS;
    private RichSelectBooleanRadio raDaPagareS;
    private boolean pagamento;
    private RichInputText itIdBarca;
    String[] tipoDescrizione = {"Accessori nautici","Alimentare","Assicurazioni","Cancelleria","Carpenteria","Commissioni","Meccanica","Materiali pulizia","Pigioni","Pubblicita","Skipper","Stipendi","Tapezzeria","Telefonia","Varie"};
    String[] pagamentoValues = {"pagato","non pagato"};
    private RichSelectOneChoice tipoDiSpesa;
    private SelectItem tipoDiSpesaSel;
    private static SelectItem[] valuestipoDiSpesa;
    private static SelectItem[] valuesPagamento;
    private RichInputText itIdSpesa;
    private RichCommandButton cbSalva;

    public DettaglioSpese() {
        logger.info("DettaglioSpese");
        fillComponents();
    }   
    public SelectItem[] getDisplayValues(){
        JavaServiceFacade serviceFacade  = new JavaServiceFacade();
        List<Barche> l = serviceFacade.getBarcheFindAll();
//        int sizeCanale = l.size() + 1;
//        if (displayValues == null) {
//            displayValues = new SelectItem[sizeCanale];
//            displayValues[0] = new SelectItem("");
//            for (int i = 1; i < sizeCanale; i++) {
//                String value = l.get(i - 1).getNomeBarca();
//                int code = l.get(i - 1).getId();
//                displayValues[i] = new SelectItem(code, value);
//            }
//        }
          if (true) {
            displayValues = new SelectItem[l.size()];
            for (int i = 0; i < l.size(); i++) {
                displayValues[i] = new SelectItem(l.get(i), l.get(i).getNomeBarca());
          }            
        }
        
        return displayValues;
    }


    public void setItImportoSpese(RichInputText itImportoSpese) {
        this.itImportoSpese = itImportoSpese;
    }

    public RichInputText getItImportoSpese() {
        return itImportoSpese;
    }

    public void setRsBarca(RichSelectOneChoice itBarca) {
        this.rsBarca = itBarca;
    }

    public RichSelectOneChoice getRsBarca() {
        return rsBarca;
    }

    public void setItNote(RichInputText itNote) {
        this.itNote = itNote;
    }

    public RichInputText getItNote() {
        return itNote;
    }

    public void setIdData(RichInputDate idData) {
        this.idData = idData;
    }

    public RichInputDate getIdData() {
        return idData;
    }


    public Barche getBarca(){
        return (Barche)ViewUtils.getObjectValue(getRsBarca());
    }
    public Date getData(){
        return ViewUtils.getDateValue(getIdData());
    }
    public String getNote(){
        return ViewUtils.getStringValue(getItNote());
    }
    public Double getImportoSpese(){
        return ViewUtils.getDoubleValue(getItImportoSpese());
    }
    public boolean getPagato(){
        return ViewUtils.getBooleanValue(getRaPagatoS());
    }
    public boolean getDaPagare(){
        return ViewUtils.getBooleanValue(getRaDaPagareS());
    }
    public int getId(){
        return ViewUtils.getIntValue(getItId());
    }
    public int getIdBarca(){
        return ViewUtils.getIntValue(getItIdBarca());
    }
    private String getTipoSpese(){
        return ViewUtils.getStringValue(getTipoDiSpesa());
    }
    private String getIdSpesa(){
        return ViewUtils.getStringValue(getItIdSpesa());
    }

    public void salva(ActionEvent actionEvent) {

    }
    public Spese buildSpese(){
            logger.info("Spese buildSpese()");
        Spese spese = new Spese();
        spese.setDescrizione(getTipoSpese());
        spese.setId(getId());
        spese.setDataSpesa(getData());
        spese.setNote(getNote());
        spese.setImportoSpese(getImportoSpese());
        logger.info("pagato=" + getPagato());
        logger.info("dapagare=" + getDaPagare());
      //  pagamento = (getPagato()) ? getPagato() : false;
        spese.setPagamento(getPagato());
        
        int bID = (getBarca()!=null) ? getBarca().getId() : 0;

        if(getId()!=0){
        
            if(bID==0)spese.setIdBarca(getIdBarca());
            else spese.setIdBarca(bID);
            
        }else{
            //spese.setIdBarca(getIdBarca());
            spese.setIdBarca(bID);
        }      
        
        String sID = (Utils.isNullOrEmpty(getTipoSpese()) ? null : getTipoSpese());

        if(getId()!=0){
        
            if(sID==null)spese.setDescrizione(getIdSpesa());
            else spese.setDescrizione(getTipoSpese());
            
        }else{
            //spese.setIdBarca(getIdBarca());
            spese.setDescrizione(sID);
        } 
        return spese;
    }

    public void openPopup(ActionEvent actionEvent) {
        setMsgPopup("Vuoi salvare la Spesa: " + (Utils.isNullOrEmpty(getTipoSpese()) ? getIdSpesa() : getTipoSpese()));
        popupConfirm.show(ph);
    }

    public String actionBack() {
        return Constants.Place.GESTIONE_SPESE;
    }   

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public void dialogConfirmListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
        Spese spese = buildSpese();
        Spese result = null;
        try {
            result = serviveFacade.persistOrMergeSpese(spese);
            logger.info("resultid=" + result.getId());
            if ( result == null ){
                logger.info("result == null ");
                return;
            }
            GestioneSpese g = (GestioneSpese) SicsicUtils.getValueExpression("backing_gestioneSpese");
            List<SpeseDTO> list =  g.getListTableSpese();
            SpeseDTO stored = serviveFacade.getSpesaById(result.getId());
            if ( stored == null ){
                logger.info("stored == null ");
                return;
            }
            int index = list.indexOf(stored);
            logger.info("index =" + index);
            if ( index < 0 ){
                logger.info("index < 0");
                return;
            }
            SpeseDTO old = list.get(index);
            old.refresh(stored);
            double tot = 0;
            for ( SpeseDTO s : list ){
                tot += s.getImportoSpese();
                s.setTotSpese(tot);
            }
        
            g.getTSpese().setValue(list);
            AdfFacesContext.getCurrentInstance().addPartialTarget(g.getTSpese());
                //   result = SicsicFacade.instance().persistOrMergeCliente(cliente);
        } catch (Exception e) {
            setMsg("Salvataggio fallito, error=" + e.getMessage());
        }
        setMsg("la spesa "+spese.getDescrizione()+" e' stata salvata con successo");
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

    public void setItId(RichInputText itId) {
        this.itId = itId;
    }

    public RichInputText getItId() {
        return itId;
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
    
    public void fillComponents(){
        SpeseDTO spese = (SpeseDTO)ViewUtils.getObjectFromSession(Constants.Place.INSERIMENTO_SPESA);
        if(spese == null)return;
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_SPESA, null);
        logger.info("spesa found, name=" + spese.getDescrizione());
        
        itId = new RichInputText();
        itId.setValue(spese.getId());
        
        idData = new RichInputDate();
        idData.setValue(spese.getFormattedDate());
        
        //rsBarca = new RichSelectOneChoice();
        //rsBarca.setValue(spese.getBarca());
        
        
        itImportoSpese = new RichInputText();
        itImportoSpese.setValue(spese.getImportoSpese());
        
        itNote = new RichInputText();
        itNote.setValue(spese.getNote());
        
        tipoDiSpesaSel = new SelectItem(null,spese.getDescrizione());
        
    barcaSel = new SelectItem(null,spese.getNomeBarca());

    itIdSpesa = new RichInputText();
    itIdSpesa.setValue(spese.getDescrizione());
        
        raDaPagareS = new RichSelectBooleanRadio();
        raDaPagareS.setId("raDaPagareS");
        raPagatoS = new RichSelectBooleanRadio();
        raPagatoS.setId("raPagatoS");
        if(spese.isPagamento()) raPagatoS.setValue(true);
        else raDaPagareS.setValue(true);
        
        itIdBarca = new RichInputText();
        itIdBarca.setValue(spese.getIdBarca());
      
        
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

    public void setRaPagatoS(RichSelectBooleanRadio raPagatoS) {
        this.raPagatoS = raPagatoS;
    }

    public RichSelectBooleanRadio getRaPagatoS() {
        return raPagatoS;
    }

    public void setRaDaPagareS(RichSelectBooleanRadio raDaPagareS) {
        this.raDaPagareS = raDaPagareS;
    }

    public RichSelectBooleanRadio getRaDaPagareS() {
        return raDaPagareS;
    }

    public void setItIdBarca(RichInputText itIdBarca) {
        this.itIdBarca = itIdBarca;
    }

    public RichInputText getItIdBarca() {
        return itIdBarca;
    }


    public void setTipoDiSpesa(RichSelectOneChoice tipoDiSpesa) {
        this.tipoDiSpesa = tipoDiSpesa;
    }

    public RichSelectOneChoice getTipoDiSpesa() {
        return tipoDiSpesa;
    }


    public SelectItem getTipoDiSpesaSel() {        
        if(tipoDiSpesaSel == null)
            return new SelectItem(null,"Seleziona una spesa");
        else return tipoDiSpesaSel;
    }


    public SelectItem[] getValuestipoDiSpesa() {
        List<String> ts = Arrays.asList(tipoDescrizione);        
        valuestipoDiSpesa = new SelectItem[ts.size()];
        for(int i = 0; i<ts.size();i++){
            valuestipoDiSpesa[i]=new SelectItem(ts.get(i),ts.get(i));
        }        
        return valuestipoDiSpesa;
    }

    public SelectItem[] getValuesPagamento() {
        valuesPagamento = new SelectItem[2];
         valuesPagamento[0] = new SelectItem("0","non pagato");
        valuesPagamento[1] = new SelectItem("1","pagato");
        return valuesPagamento;
    }

    public void setItIdSpesa(RichInputText itIdSpesa) {
        this.itIdSpesa = itIdSpesa;
    }

    public RichInputText getItIdSpesa() {
        return itIdSpesa;
    }

    public void setCbSalva(RichCommandButton cbSalva) {
        this.cbSalva = cbSalva;
    }

    public RichCommandButton getCbSalva() {
        return cbSalva;
    }

    public void pagatoValueChange(ValueChangeEvent valueChangeEvent) {
        logger.info("Pagato old = " + valueChangeEvent.getOldValue() + " new=" + valueChangeEvent.getNewValue() );
    }

    public void dapagareValueChange(ValueChangeEvent valueChangeEvent) {
        logger.info("DAPAGARE old = " + valueChangeEvent.getOldValue() + " new=" + valueChangeEvent.getNewValue() );
        }
}
