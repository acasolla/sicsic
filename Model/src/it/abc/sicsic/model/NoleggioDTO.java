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

@Entity
@NamedQueries({
  @NamedQuery(name = "NoleggioDTO.findAll", query = "select o from NoleggioDTO o")
})
@Table(name = "\"noleggio\"")
public class NoleggioDTO implements Serializable {
    
    public final static String FIND_NOLEGGIO_JOIN_BARCA = 
    "SELECT " +
    "n.dataNoleggio AS DATA_NOLEGGIO, " +
    "n.importoNolo AS IMPORTO_NOLO," +
    "n.importoCarburante AS IMPORTO_CARBURANTE, " +
    "n.pagamento AS PAGAMENTO, " +
    "n.id AS ID_NOLEGGIO, " +
    "n.idBarca AS ID_BARCA, " +
    "n.idSkipper AS ID_SKIPPER, " +
    "n.idCliente AS ID_CLIENTE, " +
    "n.note AS NOTE, " +
    "b.nomeBarca as NOME_BARCA, " +
    "c.nome AS NOME_CLIENTE, " +
    "c.cognome AS COGNOME_CLIENTE, " +
    "c.ragioneSociale AS RAGIONE_SOCIALE_CLIENTE, " +
    "s.nome AS NOME_SKIPPER " +
    "FROM noleggio n " + 
    "left join barche b " + 
    "on n.idBarca=b.id " + 
    "left join skipper s " +
    "on n.idSkipper= s.id " +
    "left join clienti c " +
    "on n.idCliente= c.id ";
    
  /*  public final static String FIND_NOLEGGIO_JOIN_BARCA_BETWEEN = 
    "SELECT " +    
    "n.dataNoleggio AS DATA_NOLEGGIO, " +
    "n.importoNolo AS IMPORTO_NOLO, " +
    "n.importoCarburante AS IMPORTO_CARBURANTE, " +
    "n.pagamento AS PAGAMENTO, " +
    "n.id AS ID_NOLEGGIO, " +
    "n.idBarca AS ID_BARCA, " +
    "n.idSkipper AS ID_SKIPPER, " +
    "n.idCliente AS ID_CLIENTE, " +
    "n.note AS NOTE, " +
    "b.nomeBarca as NOME_BARCA, " +
    "c.nome AS NOME_CLIENTE, " +
    "c.cognome AS COGNOME_CLIENTE, " +
    "c.ragioneSociale AS RAGIONE_SOCIALE_CLIENTE, " +
    "s.nome AS NOME_SKIPPER, " +  
    "FROM noleggio n " + 
    "left join barche b " + 
    "on n.idBarca=b.id " + 
    "left join skipper s " +
    "on n.idSkipper= s.id " +
    "left join clienti c " +
    "on n.idCliente= c.id " +
    "WHERE ((n.dataNoleggio BETWEEN ? and ?) " +
    " and (n.idBarca = ? OR ? = 0) " +
    " and (n.idCliente = ? OR ? = 0) and (n.importoNolo = ? OR ? = 0)) ";
*/
  public final static String FIND_NOLEGGIO_JOIN_BARCA_BETWEEN =   
    "SELECT "+
       " n.dataNoleggio AS DATA_NOLEGGIO, "+
       " n.importoNolo AS IMPORTO_NOLO, "+
       " n.importoCarburante AS IMPORTO_CARBURANTE, "+
       " n.pagamento AS PAGAMENTO, "+ 
       " n.id AS ID_NOLEGGIO, "+
       " n.idBarca AS ID_BARCA, "+
       " n.idSkipper AS ID_SKIPPER, "+
       " n.idCliente AS ID_CLIENTE, "+
       " n.note AS NOTE, "+
       " b.nomeBarca as NOME_BARCA, "+
       " c.nome AS NOME_CLIENTE, "+
       " c.cognome AS COGNOME_CLIENTE, "+
       " c.ragioneSociale AS RAGIONE_SOCIALE_CLIENTE, "+
       " s.nome AS NOME_SKIPPER, "+
       " (SELECT SUM(x.importoNolo) "+
       " FROM noleggio x "+
        " WHERE ((x.dataNoleggio <= n.dataNoleggio) and (x.dataNoleggio BETWEEN ? and ?) "+
        " and (x.idBarca = ? OR ? = 0) "+
        " and (x.pagamento = ? OR ? is null) "+
        " and (x.idCliente = ? OR ? = 0) and (x.importoNolo = ? or ? = 0 )) Order by x.dataNoleggio)  AS TOT_NOLEGGIO, "+
       " (SELECT SUM(x.importoCarburante) "+
          " FROM noleggio x "+
           " WHERE ((x.dataNoleggio <= n.dataNoleggio) and (x.dataNoleggio BETWEEN ? and ?) "+
         " and (x.idBarca = ? OR ? = 0) "+
    " and (x.pagamento = ? OR ? is null) "+
     " and (x.idCliente = ? OR ? = 0) and (x.importoNolo = ? or ? = 0 )) Order by x.dataNoleggio)  AS TOT_CARBURANTE "+
       " FROM noleggio n "+
       " left join barche b "+
       " on n.idBarca=b.id "+
       " left join skipper s "+
       " on n.idSkipper= s.id "+
       " left join clienti c "+
       " on n.idCliente= c.id "+
       " WHERE ((n.dataNoleggio BETWEEN ? and ?) "+
        " and (n.idBarca = ? OR ? = 0) "+
    " and (n.pagamento = ? OR ? is null) "+
    
        " and (n.idCliente = ? OR ? = 0) and (n.importoNolo = ? or ? = 0 )) ORDER BY n.dataNoleggio DESC ";
    
  public final static String FIND_NOLEGGIO_JOIN_BARCA_BETWEEN2 =   
  "SELECT "+
     " n.dataNoleggio AS DATA_NOLEGGIO, "+
     " n.importoNolo AS IMPORTO_NOLO, "+
     " n.importoCarburante AS IMPORTO_CARBURANTE, "+
     " n.pagamento AS PAGAMENTO, "+ 
     " n.id AS ID_NOLEGGIO, "+
     " n.idBarca AS ID_BARCA, "+
     " n.idSkipper AS ID_SKIPPER, "+
     " n.idCliente AS ID_CLIENTE, "+
     " n.note AS NOTE, "+
     " b.nomeBarca as NOME_BARCA, "+
     " c.nome AS NOME_CLIENTE, "+
     " c.cognome AS COGNOME_CLIENTE, "+
     " c.ragioneSociale AS RAGIONE_SOCIALE_CLIENTE, "+
     " s.nome AS NOME_SKIPPER, "+
     " (SELECT SUM(x.importoNolo) "+
     " FROM noleggio x "+
      " WHERE ((x.dataNoleggio <= n.dataNoleggio) and (x.dataNoleggio BETWEEN ? and ?) "+
      " and (x.idBarca = ? OR ? = 0) "+
      " and (x.idCliente = ? OR ? = 0) and (x.importoNolo = ? or ? = 0 )))  AS TOT_NOLEGGIO, "+
     " (SELECT SUM(x.importoCarburante) "+
        " FROM noleggio x "+
         " WHERE ((x.dataNoleggio <= n.dataNoleggio) and (x.dataNoleggio BETWEEN ? and ?) "+
       " and (x.idBarca = ? OR ? = 0) "+
      " and (x.idCliente = ? OR ? = 0) and (x.importoNolo = ? or ? = 0 )))  AS TOT_CARBURANTE "+
     " FROM noleggio n "+
     " left join barche b "+
     " on n.idBarca=b.id "+
     " left join skipper s "+
     " on n.idSkipper= s.id "+
     " left join clienti c "+
     " on n.idCliente= c.id "+
     " WHERE ((n.dataNoleggio BETWEEN ? and ?) "+
      " and (n.idBarca = ? OR ? = 0) "+
      " and (n.idCliente = ? OR ? = 0) and (n.importoNolo = ? or ? = 0 )) ";
    
    @Column(name="DATA_NOLEGGIO")
    private String dataNoleggio;
    @Id
    @Column(name="ID_NOLEGGIO", nullable = false)
    private int id;
    @Column(name="ID_BARCA")
    private int idBarca;
    @Column(name="ID_CLIENTE")
    private int idCliente;
    @Column(name="ID_SKIPPER")
    private int idSkipper;
    @Column(name="IMPORTO_CARBURANTE")
    private double importoCarburante;
    @Column(name="IMPORTO_NOLO")
    private double importoNolo;
    @Column(name="NOTE")
    private String note;
    @Column(name="PAGAMENTO")
    private boolean pagamento;
    @Column(name = "NOME_BARCA")
    private String nomeBarca;    
    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;
    @Column(name="COGNOME_CLIENTE")
    private String cognomeCliente;
    @Column(name="RAGIONE_SOCIALE_CLIENTE")
    private String ragioneSociale;
    @Column(name = "NOME_SKIPPER")
    private String nomeSkipper;
    @Column(name = "TOT_NOLEGGIO")
    private double importoTotNolo;
    @Column(name = "TOT_CARBURANTE")
    private double importoTotCarburante;

    public NoleggioDTO() {
    }

    public NoleggioDTO(String dataNoleggio, int id, int idBarca, int idCliente, int idSkipper,
                       double importoCarburante, double importoNolo, String note, boolean pagamento) {
        this.dataNoleggio = dataNoleggio;
        this.id = id;
        this.idBarca = idBarca;
        this.idCliente = idCliente;
        this.idSkipper = idSkipper;
        this.importoCarburante = importoCarburante;
        this.importoNolo = importoNolo;
        this.note = note;
        this.pagamento = pagamento;
    }


    public String getDataNoleggio() {
        return dataNoleggio;
    }

    public void setDataNoleggio(String dataNoleggio) {
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
        StringBuffer sb = new StringBuffer();
        sb.append("\n=========================")
           .append("\nNoleggioDTO")
           .append("\n=========================")
           .append("\nData=" + getDataNoleggio())
            .append("\nnomeBarca=" + getNomeBarca())
            .append("\nnomeSkipper=" + getNomeSkipper())
            .append("\nnomeCliente=" + getNomeCliente())
            .append("\n=========================");
        return sb.toString();
    }
    /*
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()+"@"+Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("dataNoleggio=");
        buffer.append(getDataNoleggio());
        buffer.append(',');
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
        buffer.append("pagamento=");
        buffer.append(isPagamento());
        buffer.append(']');
        return buffer.toString();
    }
*/

    public void setNomeBarca(String nomeBarca) {
        this.nomeBarca = nomeBarca;
    }

    public String getNomeBarca() {
        return nomeBarca;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setCognomeCliente(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }

    public String getCognomeCliente() {
        return cognomeCliente;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setNomeSkipper(String nomeSkipper) {
        this.nomeSkipper = nomeSkipper;
    }

    public String getNomeSkipper() {
        return nomeSkipper;
    }
    
    public void setDataNoleggio(Date dataNoleggio) {
        this.dataNoleggio = Utils.convertDateToString(dataNoleggio, Constants.DatePatterns.yyyyMMdd);
    }

    public Date getFormattedDate(){
        return Utils.convertStringToDate(getDataNoleggio(), Constants.DatePatterns.yyyyMMdd);
    }
    
    public String getFormattedValueEuroNolo(){
        return Utils.convertDoubleToMoneyFormatted(getImportoNolo(), Constants.MoneyPatterns.EURO);
    }
    
    public String getFormattedValueEuroCarburante(){
        return Utils.convertDoubleToMoneyFormatted(getImportoCarburante(), Constants.MoneyPatterns.EURO);
    }
    
    public String getPagatoNonPagato(){
        String paga = "non pagato";
        if(isPagamento())paga = "pagato";
        return paga;
    }

    public void setImportoTotNolo(double importoTotNolo) {
        this.importoTotNolo = importoTotNolo;
    }

    public double getImportoTotNolo() {
        return importoTotNolo;
    }
    
    public String getFormattedValueEuroNoloTotale(){
        return Utils.convertDoubleToMoneyFormatted(getImportoTotNolo(), Constants.MoneyPatterns.EURO);
    }

    public void setImportoTotCarburante(double importoTotCarburante) {
        this.importoTotCarburante = importoTotCarburante;
    }

    public double getImportoTotCarburante() {
        return importoTotCarburante;
    }
    
    public String getFormattedValueEuroCarburanteTotale(){
        return Utils.convertDoubleToMoneyFormatted(getImportoTotCarburante(), Constants.MoneyPatterns.EURO);
    }
    
    public String getFormattedValueEuroImportoTotale(){
               
        return Utils.convertDoubleToMoneyFormatted((getImportoTotCarburante()+getImportoTotNolo()), Constants.MoneyPatterns.EURO);
    }

}
