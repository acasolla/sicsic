package it.abc.sicsic.view.backing.clienti;

import it.abc.sicsic.Constants;
import it.abc.sicsic.JavaServiceFacade;

import it.abc.sicsic.model.Clienti;



import it.soulsoftware.utils.Utils;
import it.soulsoftware.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;

@ManagedBean(name = "backing_gestioneClienti")
@RequestScoped

public class GestioneClienti {
    private RichInputText itNome;
    private RichInputText itCognome;
    private RichInputText itRagSociale;
    private RichInputText itCitta;
    private RichInputText itDocumento;
    private RichInputText itTelefono;
    private RichTable tClient;
    private RichCommandButton cerca;
    private List<Clienti> listClientiTable;
    private RichPopup popupRemove;
    private RichDialog dialogRemove;
    private RichPopup noteWindow;
    private String msg;
    private String msgPopup;
    RichPopup.PopupHints ph = new RichPopup.PopupHints();
  //  private static final SicsicFacade serviceFacade = new SicsicFacade();
    private static Logger logger = Logger.getLogger(GestioneClienti.class);
    private static final JavaServiceFacade serviceFacade = new JavaServiceFacade();
    private RichOutputText otMsg;
    private List<Object> components;
    private RichInputDate idDate;
    private RichInputDate idDataNascita;
    private String nomeAzienda;

    public GestioneClienti(){
        logger.info(Utils.logStartMethod("GestioneClienti"));
        try{
            initializeComponents();
            ViewUtils.fillComponentsByParamsInSession(Constants.Place.GESTIONE_CLIENTI,
                                                     tClient, components);
            listClientiTable = serviceFacade.getClienti(getNome(), getCognome(), getRagioneSociale(), getCitta(),getDocumento(), getTelefono(), Utils.convertDateToString(getDataNascita(),Constants.DatePatterns.yyyyMMdd));
            
        } catch ( Exception e){
            logger.info("Error initiating gestionclienti");
        }
    }
    
    public List<Clienti> getListClientiTable(){
       return listClientiTable;
    }
    
    public void setItNome(RichInputText it1) {
        this.itNome = it1;
    }

    public RichInputText getItNome() {
        return itNome;
    }


    public void setItCognome(RichInputText it2) {
        this.itCognome = it2;
    }

    public RichInputText getItCognome() {
        return itCognome;
    }

    public void setItRagSociale(RichInputText it3) {
        this.itRagSociale = it3;
    }

    public RichInputText getItRagSociale() {
        return itRagSociale;
    }

    public void setItCitta(RichInputText it4) {
        this.itCitta = it4;
    }

    public RichInputText getItCitta() {
        return itCitta;
    }

    public void setItDocumento(RichInputText it5) {
        this.itDocumento = it5;
    }

    public RichInputText getItDocumento() {
        return itDocumento;
    }

    public void setItTelefono(RichInputText it6) {
        this.itTelefono = it6;
    }

    public RichInputText getItTelefono() {
        return itTelefono;
    }

    public void setTClient(RichTable t1) {
        this.tClient = t1;
    }

    public RichTable getTClient() {
        return tClient;
    }

    public void setCerca(RichCommandButton cerca) {
        this.cerca = cerca;
    }

    public RichCommandButton getCerca() {
        return cerca;
    }

    public void cerca(ActionEvent actionEvent) {
        
        List<Clienti> list = null;
       // JavaServiceFacade serviceFacade = new JavaServiceFacade();
        list = serviceFacade.getClienti(getNome(), getCognome(), getRagioneSociale(), getCitta(),getDocumento(), getTelefono(),Utils.convertDateToString(getDataNascita(),Constants.DatePatterns.yyyyMMdd));
      //  list = SicsicFacade.instance().getClienti(getNome(), getCognome(), getRagioneSociale(), getCitta(),getDocumento(), getTelefono());
        logger.info("listResult=" + list);
        tClient.setValue(list);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tClient);
    }
    
    private String getNome(){
        return ViewUtils.getStringValue(getItNome());    
    }
    private String getCognome(){
        return ViewUtils.getStringValue(getItCognome());
    }
    private String getRagioneSociale(){
        return ViewUtils.getStringValue(getItRagSociale());
    }
    private String getCitta(){
        return ViewUtils.getStringValue(getItCitta());
    }
    private String getDocumento(){
        return ViewUtils.getStringValue(getItDocumento());
    }
    private String getTelefono(){
        return ViewUtils.getStringValue(getItTelefono());
    }
    private Date getDataNascita(){
        return ViewUtils.getDateValue(getIdDataNascita());
    }

    public void tableSelection(SelectionEvent selectionEvent) {
    //    logger.info("click");
    }
    
    public String actionDetail(){
        
        Clienti cliente = (Clienti)tClient.getSelectedRowData();
        if ( cliente == null )
            return null;
        logger.info("cliente selected=" + cliente);
        ViewUtils.setObjectInSession(Constants.Place.INSERIMENTO_CLIENTE, cliente);   
        ViewUtils.setParamsInSession(Constants.Place.GESTIONE_CLIENTI,
                                    Constants.Place.GESTIONE_CLIENTI,
                                    tClient, 
                                    components);
        return Constants.Place.INSERIMENTO_CLIENTE;
    }

    public void setPopupRemove(RichPopup popupRemove) {
        this.popupRemove = popupRemove;
    }

    public RichPopup getPopupRemove() {
        return popupRemove;
    }
    
    public void openPopup(ActionEvent actionEvent) {
        Clienti cliente = (Clienti)tClient.getSelectedRowData();
        logger.info("cliente=" + cliente);
        if ( cliente == null ) return;
        if(Utils.isNullOrEmpty(cliente.getNome()))
            nomeAzienda = cliente.getRagioneSociale();
        else
            nomeAzienda = cliente.getNome();
            setMsgPopup("Vuoi eliminare il contatto: " + ( nomeAzienda));
            popupRemove.show(ph);
    }
    

    public void dialogRemoveListener(DialogEvent dialogEvent) {
        Clienti cliente = (Clienti)tClient.getSelectedRowData();
        if ( cliente == null ) return;
//             JavaServiceFacade serviceFacade = new JavaServiceFacade();
        try {
            serviceFacade.removeClienti(cliente);
          //  SicsicFacade.instance().removeClienti(cliente);
            List<Clienti> list = null;
            list = serviceFacade.getClienti(getNome(), getCognome(), getRagioneSociale(), getCitta(),getDocumento(), getTelefono(), Utils.convertDateToString(getDataNascita(), Constants.DatePatterns.yyyyMMdd));
           // list = SicsicFacade.instance().getClienti(getNome(), getCognome(), getRagioneSociale(), getCitta(),getDocumento(), getTelefono());              
            tClient.setValue(list);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tClient);
        } catch (Exception e) {
            logger.info("error removing clienti" + e);
            setMsg("Salvataggio fallito, error=" + e.getMessage());
            noteWindow.show(ph);
            return;
        }
             if(Utils.isNullOrEmpty(cliente.getNome()))
                 nomeAzienda = cliente.getRagioneSociale();
             else
                 nomeAzienda = cliente.getNome();
             setMsg("Il cliente "+nomeAzienda+" è stato rimosso con successo");
             ViewUtils.showMessageNoBundle("Il cliente "+nomeAzienda+" è stato rimosso con successo",
                                          "cbDelete",
                                          noteWindow,
                                          otMsg);
        //noteWindow.show(ph);
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


    public void setOtMsg(RichOutputText otMsg) {
        this.otMsg = otMsg;
    }

    public RichOutputText getOtMsg() {
        return otMsg;
    }
    
    private void initializeComponents(){
        components = new ArrayList<Object>();
        itNome = new RichInputText();
        itCognome = new RichInputText();
        itRagSociale = new RichInputText();
        itCitta = new RichInputText();
        itDocumento = new RichInputText();
        itTelefono = new RichInputText();
        tClient = new RichTable();
        idDataNascita = new RichInputDate();
    
        itNome.setId("itNome");
        itCognome.setId("itCognome");
        itRagSociale.setId("itRagSociale");
        itCitta.setId("itCitta");
        itDocumento.setId("itDocumento");
        itTelefono.setId("itTelefono");
        tClient.setId("tClient");
        idDataNascita.setId("idDataNascita");
        
        populateComponents();
    }
    
    private void populateComponents(){
        if (components == null)
            throw new IllegalArgumentException("components can't be null");
        components.clear();
        components.add(itNome);
        components.add(itCognome);
        components.add(itRagSociale);
        components.add(itCitta);
        components.add(itDocumento);
        components.add(itTelefono);
        components.add(tClient);
        components.add(idDataNascita);
        
    }

    public void setIdDate(RichInputDate idDate) {
        this.idDate = idDate;
    }

    public RichInputDate getIdDate() {
        return idDate;
    }

    public void setIdDataNascita(RichInputDate idDataNascita) {
        this.idDataNascita = idDataNascita;
    }

    public RichInputDate getIdDataNascita() {
        return idDataNascita;
    }
}
