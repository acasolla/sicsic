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

@Entity
@NamedQueries( { @NamedQuery(name = "Clienti.findAll", query = "select o from Clienti o") })
@Table(name = "\"clienti\"")
public class Clienti implements Serializable {
    final static String q = "select * from clienti";
    
    public final static String CLIENTI_FIND_ALL = 
        "SELECT * FROM CLIENTI ORDER BY RAGIONESOCIALE, COGNOME";
    
    public final static String FIND_CLIENTI = 
        "SELECT * FROM CLIENTI " +
        "WHERE (NOME LIKE ? OR ? IS NULL) " +
        "AND (COGNOME LIKE ? OR ? IS NULL) " +
        "AND (RAGIONESOCIALE LIKE ? OR ? IS NULL) " +
        "AND (CITTA LIKE ? OR ? IS NULL) " +
        "AND (DOCUMENTO LIKE ? OR ? IS NULL) " +
        "AND (TELEFONO LIKE ? OR ? IS NULL)" +
        "AND (NATOIL LIKE ? OR ? IS NULL) ORDER BY RAGIONESOCIALE, COGNOME";
    
    @Column(name = "citta")
    private String citta;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "documento")
    private String documento;
    @Column(name = "email")
    private String email;
    @Column(name = "fax")
    private String fax;
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "natoA")
    private String natoA;
    @Column(name = "natoIl")
    private String natoIl;
    @Column(name = "nome")
    private String nome;
    @Column(name = "note")
    private String note;
    @Column(name = "partitaIva")
    private String partitaIva;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "ragioneSociale")
    private String ragioneSociale;
    @Column(name = "sedeLegale")
    private String sedeLegale;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Column(name = "nazione")
    private String nazione;

    
    public Clienti() {
    }

    public Clienti(String citta, String cognome, String documento, String email, String fax, int id, String indirizzo,
                   String natoA, String natoIl, String nome, String note, String partitaIva, String provincia,
                   String ragioneSociale, String sedeLegale, String telefono, String tipoDocumento, String nazione) {
        this.citta = citta;
        this.cognome = cognome;
        this.documento = documento;
        this.email = email;
        this.fax = fax;
        this.id = id;
        this.indirizzo = indirizzo;
        this.natoA = natoA;
        this.natoIl = natoIl;
        this.nome = nome;
        this.note = note;
        this.partitaIva = partitaIva;
        this.provincia = provincia;
        this.ragioneSociale = ragioneSociale;
        this.sedeLegale = sedeLegale;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
        this.nazione = nazione;
    }


    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getSedeLegale() {
        return sedeLegale;
    }

    public void setSedeLegale(String sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
        
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n=========================")
           .append("\nClienti")
           .append("\n=========================")
           .append("\nNome=" + getNome())
            .append("\nCognome=" + getCognome())
            .append("\nCitta=" + getCitta())
            .append("\nindirizzo=" + getIndirizzo())
            .append("\n=========================");
        return sb.toString();
    }

//    @Override
//    public String toString() {
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
//        buffer.append('[');
//        buffer.append("\ncitta=");
//        buffer.append(getCitta());
//        buffer.append(',');
//        buffer.append("\ncognome=");
//        buffer.append(getCognome());
//        buffer.append(',');
//        buffer.append("\ndocumento=");
//        buffer.append(getDocumento());
//        buffer.append(',');
//        buffer.append("\nemail=");
//        buffer.append(getEmail());
//        buffer.append(',');
//        buffer.append("\nfax=");
//        buffer.append(getFax());
//        buffer.append(',');
//        buffer.append("\nid=");
//        buffer.append(getId());
//        buffer.append(',');
//        buffer.append("\nindirizzo=");
//        buffer.append(getIndirizzo());
//        buffer.append(',');
//        buffer.append("\nnatoA=");
//        buffer.append(getNatoA());
//        buffer.append(',');
//        buffer.append("\nnatoIl=");
//        buffer.append(getNatoIl());
//        buffer.append(',');
//        buffer.append("\nnome=");
//        buffer.append(getNome());
//        buffer.append(',');
//        buffer.append("\nnote=");
//        buffer.append(getNote());
//        buffer.append(',');
//        buffer.append("\npartitaIva=");
//        buffer.append(getPartitaIva());
//        buffer.append(',');
//        buffer.append("\nprovincia=");
//        buffer.append(getProvincia());
//        buffer.append(',');
//        buffer.append("\nragioneSociale=");
//        buffer.append(getRagioneSociale());
//        buffer.append(',');
//        buffer.append("\nsedeLegale=");
//        buffer.append(getSedeLegale());
//        buffer.append(',');
//        buffer.append("\ntelefono=");
//        buffer.append(getTelefono());
//        buffer.append(']');
//        return buffer.toString();
//    }
        public Date getFormattedDate(){
        return Utils.convertStringToDate(getNatoIl(), Constants.DatePatterns.yyyyMMdd);
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public String getNomeCognome(){
        StringBuilder sb = new StringBuilder();
        if ( !Utils.isNullOrEmpty(getNome()))
             sb.append(getNome()).append(" ");
        
        if ( !Utils.isNullOrEmpty( getCognome() ))
            sb.append(getCognome());
        
        if ( sb.length() == 0 )
            sb.append(getRagioneSociale());
        
        return sb.toString();
        
    }

    public String getCognomeNome(){
        StringBuilder sb = new StringBuilder();
        if ( !Utils.isNullOrEmpty(getCognome()))
             sb.append(getCognome()).append(" ");
        
        if ( !Utils.isNullOrEmpty( getNome() ))
            sb.append(getNome());
        
        if ( sb.length() == 0 )
            sb.append(getRagioneSociale());
        
        return sb.toString();
        
    }

    public String getDocumentoTipoNumero(){
        StringBuilder sb = new StringBuilder();
        if ( !Utils.isNullOrEmpty(getTipoDocumento()))
             sb.append(getTipoDocumento()).append(" ");
        
        sb.append(" Nr.: ");
        if ( !Utils.isNullOrEmpty( getDocumento() ))
            sb.append(getDocumento());
        
        
        return sb.toString();
        
    }
    
    public String getNatoANatoIl(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        
       StringBuilder sb = new StringBuilder();
         if ( !Utils.isNullOrEmpty(getNatoA()))
              sb.append(getNatoA());
         
         if ( !Utils.isNullOrEmpty( getNatoIl() )){
             sdf.applyPattern("yyyyMMdd");
             Date d = null;
             String s = "";
             try {
                 d = sdf.parse(getNatoIl());
             } catch (ParseException e) {
                 e.printStackTrace();
                 d = new Date();
                 sb.append(s);
                 return sb.toString();
             }
             sdf.applyPattern("dd/MM/yyyy");
            sb.append(" il ").append(sdf.format(d));
             
             
         }
         return sb.toString();
        
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getNazione() {
        return nazione;
    }
}
