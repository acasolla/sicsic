package it.abc.sicsic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "Barche.findAll", query = "select o from Barche o") })
@Table(name = "\"barche\"")
public class Barche implements Serializable {
    
    public final static String FIND_BARCHE = 
        "SELECT * FROM BARCHE " +
        "WHERE (NOMEBARCA LIKE ? OR ? IS NULL) " +
        "AND (TIPOMODELLO LIKE ? OR ? IS NULL)"; 
    
    public final static String BARCHE_FIND_ALL = 
        "SELECT * FROM BARCHE ";       
    
    @Column(name = "annoCostruzione")
    private int annoCostruzione;
    @Column(name = "assPolizzaCorpi")
    private String assPolizzaCorpi;
    @Column(name = "assPolizzaRC")
    private String assPolizzaRC;
    @Column(name = "bandiera")
    private String bandiera;
    @Column(name = "capacitaAcqua")
    private Double capacitaAcqua;
    @Column(name = "capacitaGasolio")
    private Double capacitaGasolio;
    @Column(name = "certificazioneCE")
    private String certificazioneCE;
    @Column(name = "costruitaDa")
    private String costruitaDa;
    @Column(name = "dislocamento")
    private Double dislocamento;
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "larghezza")
    private Double larghezza;
    @Column(name = "limitiNavigazione")
    private String limitiNavigazione;
    @Column(name = "lunghezza")
    private Double lunghezza;
    @Column(name = "matricoleMotori")
    private String matricoleMotori;
    @Column(name = "motorizzazione")
    private String motorizzazione;
    @Column(name = "nomeBarca")
    private String nomeBarca;
    @Column(name = "note")
    private String note;
    @Column(name = "numMaxEquipaggio")
    private int numMaxEquipaggio;
    @Column(name = "numMinEquipaggio")
    private int numMinEquipaggio;
    @Column(name = "numeroRegistro")
    private String numeroRegistro;
    @Column(name = "pescaggio")
    private Double pescaggio;
    @Column(name = "serialeScafo")
    private String serialeScafo;
    @Column(name = "siglaIscrizione")
    private String siglaIscrizione;
    @Column(name = "tipoModello")
    private String tipoModello;

    public Barche() {
    }

    public Barche(int annoCostruzione, String assPolizzaCorpi, String assPolizzaRC, String bandiera,
                  Double capacitaAcqua, Double capacitaGasolio, String certificazioneCE, String costruitaDa,
                  Double dislocamento, int id, Double larghezza, String limitiNavigazione, Double lunghezza,
                  String matricoleMotori, String motorizzazione, String nomeBarca, String note,
                  int numMaxEquipaggio, int numMinEquipaggio, String numeroRegistro, Double pescaggio,
                  String serialeScafo, String siglaIscrizione, String tipoModello) {
        this.annoCostruzione = annoCostruzione;
        this.assPolizzaCorpi = assPolizzaCorpi;
        this.assPolizzaRC = assPolizzaRC;
        this.bandiera = bandiera;
        this.capacitaAcqua = capacitaAcqua;
        this.capacitaGasolio = capacitaGasolio;
        this.certificazioneCE = certificazioneCE;
        this.costruitaDa = costruitaDa;
        this.dislocamento = dislocamento;
        this.id = id;
        this.larghezza = larghezza;
        this.limitiNavigazione = limitiNavigazione;
        this.lunghezza = lunghezza;
        this.matricoleMotori = matricoleMotori;
        this.motorizzazione = motorizzazione;
        this.nomeBarca = nomeBarca;
        this.note = note;
        this.numMaxEquipaggio = numMaxEquipaggio;
        this.numMinEquipaggio = numMinEquipaggio;
        this.numeroRegistro = numeroRegistro;
        this.pescaggio = pescaggio;
        this.serialeScafo = serialeScafo;
        this.siglaIscrizione = siglaIscrizione;
        this.tipoModello = tipoModello;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append(']');
        return buffer.toString();
    }

    public int getAnnoCostruzione() {
        return annoCostruzione;
    }

    public void setAnnoCostruzione(int annoCostruzione) {
        this.annoCostruzione = annoCostruzione;
    }

    public String getAssPolizzaCorpi() {
        return assPolizzaCorpi;
    }

    public void setAssPolizzaCorpi(String assPolizzaCorpi) {
        this.assPolizzaCorpi = assPolizzaCorpi;
    }

    public String getAssPolizzaRC() {
        return assPolizzaRC;
    }

    public void setAssPolizzaRC(String assPolizzaRC) {
        this.assPolizzaRC = assPolizzaRC;
    }

    public String getBandiera() {
        return bandiera;
    }

    public void setBandiera(String bandiera) {
        this.bandiera = bandiera;
    }

    public Double getCapacitaAcqua() {
        return capacitaAcqua;
    }

    public void setCapacitaAcqua(Double capacitaAcqua) {
        this.capacitaAcqua = capacitaAcqua;
    }

    public Double getCapacitaGasolio() {
        return capacitaGasolio;
    }

    public void setCapacitaGasolio(Double capacitaGasolio) {
        this.capacitaGasolio = capacitaGasolio;
    }

    public String getCertificazioneCE() {
        return certificazioneCE;
    }

    public void setCertificazioneCE(String certificazioneCE) {
        this.certificazioneCE = certificazioneCE;
    }

    public String getCostruitaDa() {
        return costruitaDa;
    }

    public void setCostruitaDa(String costruitaDa) {
        this.costruitaDa = costruitaDa;
    }

    public Double getDislocamento() {
        return dislocamento;
    }

    public void setDislocamento(Double dislocamento) {
        this.dislocamento = dislocamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(Double larghezza) {
        this.larghezza = larghezza;
    }

    public String getLimitiNavigazione() {
        return limitiNavigazione;
    }

    public void setLimitiNavigazione(String limitiNavigazione) {
        this.limitiNavigazione = limitiNavigazione;
    }

    public Double getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(Double lunghezza) {
        this.lunghezza = lunghezza;
    }

    public String getMatricoleMotori() {
        return matricoleMotori;
    }

    public void setMatricoleMotori(String matricoleMotori) {
        this.matricoleMotori = matricoleMotori;
    }

    public String getMotorizzazione() {
        return motorizzazione;
    }

    public void setMotorizzazione(String motorizzazione) {
        this.motorizzazione = motorizzazione;
    }

    public String getNomeBarca() {
        return nomeBarca;
    }

    public void setNomeBarca(String nomeBarca) {
        this.nomeBarca = nomeBarca;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumMaxEquipaggio() {
        return numMaxEquipaggio;
    }

    public void setNumMaxEquipaggio(int numMaxEquipaggio) {
        this.numMaxEquipaggio = numMaxEquipaggio;
    }

    public int getNumMinEquipaggio() {
        return numMinEquipaggio;
    }

    public void setNumMinEquipaggio(int numMinEquipaggio) {
        this.numMinEquipaggio = numMinEquipaggio;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Double getPescaggio() {
        return pescaggio;
    }

    public void setPescaggio(Double pescaggio) {
        this.pescaggio = pescaggio;
    }

    public String getSerialeScafo() {
        return serialeScafo;
    }

    public void setSerialeScafo(String serialeScafo) {
        this.serialeScafo = serialeScafo;
    }

    public String getSiglaIscrizione() {
        return siglaIscrizione;
    }

    public void setSiglaIscrizione(String siglaIscrizione) {
        this.siglaIscrizione = siglaIscrizione;
    }

    public String getTipoModello() {
        return tipoModello;
    }

    public void setTipoModello(String tipoModello) {
        this.tipoModello = tipoModello;
    }


    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
