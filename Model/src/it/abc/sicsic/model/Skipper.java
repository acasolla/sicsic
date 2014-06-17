package it.abc.sicsic.model;

import it.abc.sicsic.Constants;

import it.soulsoftware.utils.Utils;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@NamedQueries( { @NamedQuery(name = "Skipper.findAll", query = "select o from Skipper o") })
@Table(name = "\"skipper\"")
public class Skipper implements Serializable {
    
    private final static Logger logger = Logger.getLogger("Skipper");
    
    
    public final static String SKIPPER_FIND_ALL = 
        "SELECT * FROM SKIPPER ORDER BY COGNOME,NOME";
    
    public final static String FIND_SKIPPER = 
        "SELECT * FROM SKIPPER " +
        "WHERE (NOME LIKE ? OR ? IS NULL) " +
        "AND (RESIDENZA LIKE ? OR ? IS NULL)";    
    
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "natoA")
    private String natoA;
    @Column(name = "natoIl")
    private String natoIl;
    @Column(name = "nome")
    private String nome;
    @Column(name = "note")
    private String note;
    @Column(name = "numPatenteNautica")
    private String numPatenteNautica;
    @Column(name = "residenza")
    private String residenza;
    @Column(name = "rilasciataDa")
    private String rilasciataDa;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "rilasciataIl")
    private String rilasciataIl;

    public Skipper() {
    }

    public Skipper(int id, String natoA, String natoIl, String nome, String note, String numPatenteNautica,
                   String residenza, String rilasciataDa, String cognome, String indirizzo, String rilasciataIl) {
        this.id = id;
        this.natoA = natoA;
        this.natoIl = natoIl;
        this.nome = nome;
        this.note = note;
        this.numPatenteNautica = numPatenteNautica;
        this.residenza = residenza;
        this.rilasciataDa = rilasciataDa;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.rilasciataIl = rilasciataIl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNatoA() {
        return natoA;
    }

    public void setNatoA(String natoA) {
        this.natoA = natoA;
    }

    public String getNatoIl() {
        return natoIl;
    }

    public void setNatoIl(Date natoIl) {
        this.natoIl = Utils.convertDateToString(natoIl, Constants.DatePatterns.yyyyMMdd);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumPatenteNautica() {
        return numPatenteNautica;
    }

    public void setNumPatenteNautica(String numPatenteNautica) {
        this.numPatenteNautica = numPatenteNautica;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getRilasciataDa() {
        return rilasciataDa;
    }

    public void setRilasciataDa(String rilasciataDa) {
        this.rilasciataDa = rilasciataDa;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("natoA=");
        buffer.append(getNatoA());
        buffer.append(',');
        buffer.append("natoIl=");
        buffer.append(getNatoIl());
        buffer.append(',');
        buffer.append("nome=");
        buffer.append(getNome());
        buffer.append(',');
        buffer.append("note=");
        buffer.append(getNote());
        buffer.append(',');
        buffer.append("numPatenteNautica=");
        buffer.append(getNumPatenteNautica());
        buffer.append(',');
        buffer.append("residenza=");
        buffer.append(getResidenza());
        buffer.append(',');
        buffer.append("rilasciataDa=");
        buffer.append(getRilasciataDa());
        buffer.append(']');
        return buffer.toString();
    }
    public Date getFormattedDate(){
    return Utils.convertStringToDate(getNatoIl(), Constants.DatePatterns.yyyyMMdd);
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setRilasciataIl(Date rilasciataIl) {
        this.rilasciataIl = Utils.convertDateToString(rilasciataIl, Constants.DatePatterns.yyyyMMdd);
}

    public String getRilasciataIl() {
        return rilasciataIl;
    }
    public Date getFormattedDateRilasciata(){
        return Utils.convertStringToDate(getRilasciataIl(), Constants.DatePatterns.yyyyMMdd);
    }
 
    public String getTitolo( String tipo){
        logger.info("getTitolo,tipo=" + tipo);
        StringBuilder sb = new StringBuilder();
        sb.append("Assume il comando dell'unita' ");
        if ( getId() != 0 && tipo != null){
            logger.info("id!=null");
            if( Constants.Combo.LOCAZIONE.equals(tipo) )
                sb.append("per conto del Conduttore");
        }
        logger.info("result=" + sb.toString());
        return sb.toString();
    }
    
    public String getNomeCognome(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if ( !Utils.isNullOrEmpty(getNome()))
             sb.append(getNome()).append(" ");
        
        if ( !Utils.isNullOrEmpty( getCognome() ))
            sb.append(getCognome());
        
        return sb.toString();

    }
    
    public String getNatoANatoIl(){
        SimpleDateFormat sdf = new SimpleDateFormat();
       StringBuilder sb = new StringBuilder();
       sb.append("");
         if ( !Utils.isNullOrEmpty(getNatoA()))
              sb.append("nato a ").append(getNatoA());
         if ( !Utils.isNullOrEmpty( getNatoIl() )){
             sdf.applyPattern("yyyyMMdd");
             Date d = null;
             try {
                 d = sdf.parse(getNatoIl());
             } catch (ParseException e) {
                 e.printStackTrace();
                 d = new Date();
             }
             sdf.applyPattern("dd/MM/yyyy");
             String s = sdf.format(d);
            sb.append(" il ").append(s);
         }
         return sb.toString();
    }


    public String getResidente(){
       StringBuilder sb = new StringBuilder();
       sb.append("");
         if ( !Utils.isNullOrEmpty(getResidenza()) )
              sb.append("Residente a ").append(getResidenza());
         return sb.toString();
        
    }

    public String getPatenteNautica(){
       StringBuilder sb = new StringBuilder();
       sb.append("");
         if ( !Utils.isNullOrEmpty(getNumPatenteNautica()) )
              sb.append("Patente nautica nr ").append(getNumPatenteNautica());
         
         
         return sb.toString();
        
    }

    public String getPatenteRilascioData(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        StringBuilder sb = new StringBuilder();
       sb.append("");
       if ( !Utils.isNullOrEmpty(getRilasciataIl()) ){
           
           sdf.applyPattern("yyyyMMdd");
           Date d = null;
           try {
               d = sdf.parse(getRilasciataIl());
           } catch (ParseException e) {
               e.printStackTrace();
               d = new Date();
           }
           sdf.applyPattern("dd/MM/yyyy");
           String s = sdf.format(d);
           
           
              sb.append("il ").append(s);
       }
         
         return sb.toString();
        
    }
    
    public String getPatenteRilascioLuogo(){
       StringBuilder sb = new StringBuilder();
       sb.append("");
         if (!Utils.isNullOrEmpty(getRilasciataDa()) )
              sb.append("rilasciata dalla Capitaneria di porto di ").append(getRilasciataDa());
         
         
         return sb.toString();
        
    }


}
