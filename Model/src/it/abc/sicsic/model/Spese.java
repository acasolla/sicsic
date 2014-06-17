package it.abc.sicsic.model;

import it.abc.sicsic.Constants;

import it.soulsoftware.utils.Utils;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.xml.crypto.Data;

@Entity
@NamedQueries({
  @NamedQuery(name = "Spese.findAll", query = "select o from Spese o")
})
@Table(name = "\"spese\"")
public class Spese implements Serializable {
    
    public final static String FIND_SPESE = 
        "SELECT * FROM SPESE " +
        "WHERE (DATASPESA LIKE ? OR ? IS NULL) " +
        "AND (BARCA LIKE ? OR ? IS NULL)";
    
    @Transient
    private String style="";    
    @Transient
    private String flagSubtotal="n";
    
    @Column(name="dataSpesa")
    private String dataSpesa;
    @Column(name="descrizione")
    private String descrizione;
    @Id
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="idBarca")
    private int idBarca;
    @Column(name="idSkipper")
    private int idSkipper;
    @Column(name="importoSpese")
    private Double importoSpese;
    @Column(name="note")
    private String note;
    @Column(name="pagamento")
    private boolean pagamento;
    
    public boolean isSubtotal(){
        return getFlagSubtotal().equals("y");
    }

    public Spese() {
    }

    public Spese(String dataSpesa, String descrizione, int id, int idBarca, int idSkipper,
                 Double importoSpese, String note, boolean pagamento) {
        this.dataSpesa = dataSpesa;
        this.descrizione = descrizione;
        this.id = id;
        this.idBarca = idBarca;
        this.idSkipper = idSkipper;
        this.importoSpese = importoSpese;
        this.note = note;
        this.pagamento = pagamento;
    }

    public String getDataSpesa() {
        return dataSpesa;
    }

    public void setDataSpesa(String dataSpesa) {
        this.dataSpesa = dataSpesa;
    }
    public void setDataSpesa(Date dataSpesa) {
        this.dataSpesa = Utils.convertDateToString(dataSpesa, Constants.DatePatterns.yyyyMMdd);
    }
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBarca() {
        return idBarca;
    }

    public void setIdBarca(int idBarca) {
        this.idBarca = idBarca;
    }

    public int getIdSkipper() {
        return idSkipper;
    }

    public void setIdSkipper(int idSkipper) {
        this.idSkipper = idSkipper;
    }

    public Double getImportoSpese() {
        return importoSpese;
    }

    public void setImportoSpese(Double importoSpese) {
        this.importoSpese = importoSpese;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("dataSpesa=");
        buffer.append(getDataSpesa());
        buffer.append(',');
        buffer.append("descrizione=");
        buffer.append(getDescrizione());
        buffer.append(',');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("idBarca=");
        buffer.append(getIdBarca());
        buffer.append(',');
        buffer.append("idSkipper=");
        buffer.append(getIdSkipper());
        buffer.append(',');
        buffer.append("importoSpese=");
        buffer.append(getImportoSpese());
        buffer.append(',');
        buffer.append("note=");
        buffer.append(getNote());
        buffer.append(',');
        buffer.append("pagamento=");
        buffer.append(isPagamento());
        buffer.append(']');
        return buffer.toString();
    }
    
    public void setFlagSubtotal(String flagSubtotal) {
        this.flagSubtotal = flagSubtotal;
    }

    public String getFlagSubtotal() {
        return flagSubtotal;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return isSubtotal() ? Constants.Style.SUBTOTAL : ""; 
    }
    
    public Date getFormattedDate(){
        return Utils.convertStringToDate(getDataSpesa(), Constants.DatePatterns.yyyyMMdd);
    }
    
   }
