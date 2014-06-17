package it.abc.sicsic.model;

import it.abc.sicsic.Constants;

import it.soulsoftware.utils.Utils;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@Table(name = "\"spese\"")

public class SpeseDTO implements Serializable {
    
    //public final static String FIND_SPESE_PER_MESE = 
      //"SELECT date(dataSpesa) AS DATASPESA,MONTH(dataSpesa) AS MESE," +
    //"id,dataSpesa,importoSpese,barca,descrizione,pagamento,(SELECT SUM(importoSpese) " +
      //  "FROM nauticasicsic.spese WHERE MONTH(dataSpesa) = MESE)  AS subTotale " +
        //"FROM nauticasicsic.spese";
    
        public final static String FIND_SPESE_JOIN_BARCA_BETWEEN =         
        "SELECT " +
        "n.id AS ID_SPESE, " +
        "n.dataSpesa AS DATA_SPESA, " +
        "n.importoSpese AS IMPORTO_SPESA, " +
        "n.descrizione AS DESCRIZIONE, " +
        "n.pagamento AS PAGAMENTO, " +
        "n.idBarca AS ID_BARCA, " +
        "b.nomeBarca as NOME_BARCA, " +
        "n.note AS NOTE, " +
        "(SELECT SUM(x.importoSpese) " +
          " FROM spese x " +
           " WHERE (x.dataSpesa >= n.dataSpesa) and (x.dataSpesa BETWEEN ? and ?) " +
            " and (x.pagamento = ? OR ? is null) and (x.note like ? OR ? is null) and (x.descrizione LIKE ? OR ? IS NULL) ORDER BY x.dataSpesa DESC) AS TOT_SPESE " +
        "FROM spese n " +
        "left join barche b " +
        "on n.idBarca=b.id " +
        "WHERE ((n.dataSpesa BETWEEN ? and ?) and (n.descrizione LIKE ? OR ? IS NULL) " +
            " and (n.pagamento = ? OR ? is null) and (n.note like ? OR ? is null)) ORDER BY n.dataSpesa DESC ";
   
    public final static String FIND_SPESE_BY_ID =         
    "SELECT " +
    "n.id AS ID_SPESE, " +
    "n.dataSpesa AS DATA_SPESA, " +
    "n.importoSpese AS IMPORTO_SPESA, " +
    "n.descrizione AS DESCRIZIONE, " +
    "n.pagamento AS PAGAMENTO, " +
    "n.idBarca AS ID_BARCA, " +
    "b.nomeBarca as NOME_BARCA, " +
    "n.note AS NOTE " +
    "FROM spese n " +
    "left join barche b  on n.idBarca=b.id " +
    "WHERE n.id=? ";
    
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATASPESA")
    private Date dataFormatDate;
    //@Column(name="MESE")
    //private int mese;
    //@Column(name="subTotale")
    //private Double subTotale;

    @Id
    @Column(name="ID_SPESE", nullable = false)
    private int id;
    @Column(name="DATA_SPESA")
    private String dataSpesa;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @Column(name="IMPORTO_SPESA")
    private double importoSpese;
    @Column(name="PAGAMENTO")
    private boolean pagamento;    
    @Column(name="NOTE")
    private String note;
    @Column(name="ID_BARCA")
    private int idBarca;
    @Column(name = "NOME_BARCA")
    private String nomeBarca; 
    @Column(name = "TOT_SPESE")
    private double totSpese; 
    
    public SpeseDTO() {        
    }
    
    public SpeseDTO(String dataSpesa, String descrizione, int id, int idBarca,
                 double importoSpese, String note, boolean pagamento) {
        this.dataSpesa = dataSpesa;
        this.descrizione = descrizione;
        this.id = id;
        this.idBarca = idBarca;
        this.importoSpese = importoSpese;
        this.note = note;
        this.pagamento = pagamento;
    }
    
    public void setDataSpesa(Date dataSpesa) {
        this.dataSpesa = Utils.convertDateToString(dataSpesa, Constants.DatePatterns.yyyyMMdd);
    }

    public void setDataFormatDate(Date dataFormatDate) {
        this.dataFormatDate = dataFormatDate;
    }

    public Date getDataFormatDate() {
        return dataFormatDate;
    }

    //public void setMese(int mese) {
      //  this.mese = mese;
    //}

//    public int getMese() {
//        return mese;
//    }
//
//    public void setSubTotale(Double subTotale) {
//        this.subTotale = subTotale;
//    }
//
//    public Double getSubTotale() {
//        return subTotale;
//    }

    public void setDataSpesa(String dataSpesa) {
        this.dataSpesa = dataSpesa;
    }

    public String getDataSpesa() {
        return dataSpesa;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImportoSpese(double importoSpese) {
        this.importoSpese = importoSpese;
    }

    public Double getImportoSpese() {
        return importoSpese;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    public boolean isPagamento() {
        return pagamento;
    }
    public Date getFormattedDate(){
        return Utils.convertStringToDate(getDataSpesa(), Constants.DatePatterns.yyyyMMdd);
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setIdBarca(int idBarca) {
        this.idBarca = idBarca;
    }

    public int getIdBarca() {
        return idBarca;
    }

    public void setNomeBarca(String nomeBarca) {
        this.nomeBarca = nomeBarca;
    }

    public String getNomeBarca() {
        return nomeBarca;
    }
    
    public String getFormattedValueEuroSpese(){
        return Utils.convertDoubleToMoneyFormatted(getImportoSpese(), Constants.MoneyPatterns.EURO);
    }
    
    public String getPagatoNonPagato(){
        return isPagamento() ? "pagato" : "non pagato";
    }

    public void setTotSpese(double totSpese) {
        this.totSpese = totSpese;
    }

    public double getTotSpese() {
        return totSpese;
    }
    public String getTotSpeseFormatted(){
        return Utils.convertDoubleToMoneyFormatted(getTotSpese(), Constants.MoneyPatterns.EURO);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SpeseDTO)) {
            return false;
        }
        final SpeseDTO other = (SpeseDTO) object;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + id;
        return result;
    }
    
    
    public void refresh( SpeseDTO s ){
        setDataSpesa(s.getDataSpesa());
        setDescrizione(s.getDescrizione());
        setIdBarca(s.getIdBarca());
        setImportoSpese(s.getImportoSpese());
        setNomeBarca(s.getNomeBarca());
        setNote(s.getNote());
        setPagamento(s.isPagamento());
        setTotSpese(s.getTotSpese());
    
    }
}
