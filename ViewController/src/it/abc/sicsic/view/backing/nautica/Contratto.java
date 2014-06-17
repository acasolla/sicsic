package it.abc.sicsic.view.backing.nautica;

import it.abc.sicsic.Constants;
import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;


import it.abc.sicsic.model.Skipper;
import it.abc.sicsic.view.backing.util.JasperUtils;

import it.soulsoftware.utils.Utils;
import it.soulsoftware.utils.ViewUtils;


import java.util.Calendar;
import java.util.Date;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.event.ActionEvent;


import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import org.apache.log4j.Logger;

@ManagedBean(name = "backing_contratto")
@RequestScoped
public class Contratto {
    private RichInputText itStipulato;
    private RichInputDate idData;
    private RichSelectOneChoice scliente;
    private RichSelectOneChoice sbarca;
    private RichSelectOneChoice sskipper;
    private RichSelectOneChoice stipo;
    private RichInputDate idInizio;
    private RichInputDate idFine;
    private RichInputText itLuogoConsegna;
    private RichInputText itFitto;
    private RichInputText itDeposito;
    private RichInputText itLimitiNav;
    private RichInputText itOptionals;
   // private RichInputText itCondizioniPart;
 //   private static ResourceBundle bundle =ResourceBundle.getBundle("sicsic", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    
  

    private void initComponents(){
        if ( idData == null ) idData = new RichInputDate();
        if ( idInizio == null ) idInizio = new RichInputDate();
        if ( idFine == null ) idFine = new RichInputDate();
        
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.set(Calendar.HOUR_OF_DAY, 8);
        c.set(Calendar.MINUTE, 0);
    
        
        idData.setValue(new Date());
        idInizio.setValue(new Date(c.getTimeInMillis()));
        c.set(Calendar.HOUR_OF_DAY, 18);
        idFine.setValue(new Date(c.getTimeInMillis()));
        
        
    }
    private static final Logger logger = Logger.getLogger(Contratto.class);
    
    public Contratto() {
        super();
        initComponents();
    }

    public void setItStipulato(RichInputText itStipulato) {
        this.itStipulato = itStipulato;
    }

    public RichInputText getItStipulato() {
        return itStipulato;
    }

    public void setIdData(RichInputDate idData) {
        this.idData = idData;
    }

    public RichInputDate getIdData() {
        return idData;
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

    public void setIdInizio(RichInputDate idInizio) {
        this.idInizio = idInizio;
    }

    public RichInputDate getIdInizio() {
        return idInizio;
    }

    public void setIdFine(RichInputDate idFine) {
        this.idFine = idFine;
    }

    public RichInputDate getIdFine() {
        return idFine;
    }

    public void setItLuogoConsegna(RichInputText itLuogoConsegna) {
        this.itLuogoConsegna = itLuogoConsegna;
    }

    public RichInputText getItLuogoConsegna() {
        return itLuogoConsegna;
    }

    public void setItFitto(RichInputText itFitto) {
        this.itFitto = itFitto;
    }

    public RichInputText getItFitto() {
        return itFitto;
    }

    public void setItDeposito(RichInputText itDeposito) {
        this.itDeposito = itDeposito;
    }

    public RichInputText getItDeposito() {
        return itDeposito;
    }

    public void setItLimitiNav(RichInputText itLimitiNav) {
        this.itLimitiNav = itLimitiNav;
    }

    public RichInputText getItLimitiNav() {
        return itLimitiNav;
    }

    public void setItOptionals(RichInputText itOptionals) {
        this.itOptionals = itOptionals;
    }

    public RichInputText getItOptionals() {
        return itOptionals;
    }
/*
    public void setItCondizioniPart(RichInputText itCondizioniPart) {
        this.itCondizioniPart = itCondizioniPart;
    }

    public RichInputText getItCondizioniPart() {
        return itCondizioniPart;
    }
*/
    public void printAction(ActionEvent actionEvent) {
        logger.info("actionEvent=" + actionEvent);
            
        JasperUtils.setFields(getLuogoStipula(),
                              getDataStipula(),
                              getCliente(),
                              getDataInizio(),
                              getDataFine(),
                              getConsegna(),
                              getFitto(),
                              getDeposito(),
                              getOptionals(),
                              getBarca(),
                              getSkipper(),
                              getTipo(),
                              getTipoContratto());
        try {
            
            JasperUtils.openReport("report.pdf");
            JasperUtils.sampleReport(getCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Clienti getCliente(){
        return (Clienti)scliente.getValue();
    }
    
    private Barche getBarca(){
        return (Barche)sbarca.getValue();
    }
    
    private String getTipo(){
        String value = ViewUtils.getStringValue(getStipo());
        
        return "CONTRATTO DI ".concat(value).concat(" PER UNITA' DA DIPORTO");
    }
    
    private String getTipoContratto(){
        return ViewUtils.getStringValue(getStipo());
    }
    
    private Skipper getSkipper(){
        Skipper s = (Skipper)sskipper.getValue();
        logger.info("s=" + s);
        if( s == null ) s = new Skipper();
            
        return s;
    }
    private String getLuogoStipula(){
        return ViewUtils.getStringValue(getItStipulato());
    }
    
    private String getDataStipula(){
        return Utils.convertDateToString(ViewUtils.getDateValue(getIdData()), Constants.DatePatterns.dd_MM_yyyy);
    }
    
    private String getDataInizio(){
        Date d = ViewUtils.getDateValue(getIdInizio());
        String g = Utils.convertDateToString(d, Constants.DatePatterns.dd_MM_yyyy); 
        String h = Utils.convertDateToString(d, Constants.DatePatterns.HH_mm);
        return g.concat("  Ore  ").concat(h);
    }
    private String getDataFine(){
        Date d = ViewUtils.getDateValue(getIdFine());
        String g = Utils.convertDateToString(d, Constants.DatePatterns.dd_MM_yyyy); 
        String h = Utils.convertDateToString(d, Constants.DatePatterns.HH_mm);
        return g.concat("  Ore  ").concat(h);
    }
    
    private String getConsegna(){
        return ViewUtils.getStringValue(getItLuogoConsegna());
    }
    
    private String getFitto(){
        String result = "";
        String value = ViewUtils.getStringValue(getItFitto());
        if ( !ViewUtils.isNullOrEmpty(value))
            result = value.concat("  + carburante");
        
        return result;
    }
    private String getDeposito(){
        return ViewUtils.getStringValue(getItDeposito());
    }
    private String getOptionals(){
        return ViewUtils.getStringValue(getItOptionals());
    }


    public void setSskipper(RichSelectOneChoice sskipper) {
        this.sskipper = sskipper;
    }

    public RichSelectOneChoice getSskipper() {
        return sskipper;
    }

    public void setStipo(RichSelectOneChoice sTipo) {
        this.stipo = sTipo;
    }

    public RichSelectOneChoice getStipo() {
        return stipo;
    }
}
