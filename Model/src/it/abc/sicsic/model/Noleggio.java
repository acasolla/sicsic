package it.abc.sicsic.model;

import it.abc.sicsic.Constants;

import it.soulsoftware.utils.Utils;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Noleggio.findAll", query = "select o from Noleggio o")
})
@Table(name = "\"noleggio\"")

public class Noleggio implements Serializable {
    
    public final static String FIND_NOLEGGIO = 
        "SELECT * FROM NOLEGGIO " +
        "WHERE (DATANOLEGGIO BETWEEN ? AND ? OR ? IS NULL OR ? IS NULL) ";
    
    @Id
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="idBarca")
    private int idBarca;
    @Column(name="idCliente")
    private int idCliente;
    @Column(name="idSkipper")
    private int idSkipper;
    @Column(name="importoCarburante")
    private double importoCarburante;
    @Column(name="importoNolo")
    private double importoNolo;
    @Column(name="note")
    private String note;
    @Column(name="pagamento")
    private boolean pagamento;
    @Column(name="dataNoleggio")
    private String dataNoleggio;
    

    public Noleggio() {
    }

    public Noleggio(int id, int idBarca, int idCliente, int idSkipper, double importoCarburante, double importoNolo,
                    String note, boolean pagamento, String dataNoleggio) {
        this.id = id;
        this.idBarca = idBarca;
        this.idCliente = idCliente;
        this.idSkipper = idSkipper;
        this.importoCarburante = importoCarburante;
        this.importoNolo = importoNolo;
        this.note = note;
        this.pagamento = pagamento;
        this.dataNoleggio = dataNoleggio;
        
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSkipper() {
        return idSkipper;
    }

    public void setIdSkipper(int idSkipper) {
        this.idSkipper = idSkipper;
    }

    public double getImportoCarburante() {
        return importoCarburante;
    }

    public void setImportoCarburante(double importoCarburante) {
        this.importoCarburante = importoCarburante;
    }

    public double getImportoNolo() {
        return importoNolo;
    }

    public void setImportoNolo(double importoNolo) {
        this.importoNolo = importoNolo;
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

    public void setPagato(boolean pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("idBarca=");
        buffer.append(getIdBarca());
        buffer.append(',');
        buffer.append("idCliente=");
        buffer.append(getIdCliente());
        buffer.append(',');
        buffer.append("idSkipper=");
        buffer.append(getIdSkipper());
        buffer.append(',');
        buffer.append("importoCarburante=");
        buffer.append(getImportoCarburante());
        buffer.append(',');
        buffer.append("importoNolo=");
        buffer.append(getImportoNolo());
        buffer.append(',');
        buffer.append("note=");
        buffer.append(getNote());
        buffer.append(',');
        buffer.append("pagato=");
        buffer.append(isPagamento());
        buffer.append(']');
        return buffer.toString();
    }

    public void setDataNoleggio(Date dataNoleggio) {
        this.dataNoleggio = Utils.convertDateToString(dataNoleggio, Constants.DatePatterns.yyyyMMdd);
    }

    public String getDataNoleggio() {
        return dataNoleggio;
    }
    
    public Date getFormattedDate(){
        return Utils.convertStringToDate(getDataNoleggio(), Constants.DatePatterns.yyyyMMdd);
    }
}
