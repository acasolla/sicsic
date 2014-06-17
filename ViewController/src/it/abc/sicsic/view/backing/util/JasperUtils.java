package it.abc.sicsic.view.backing.util;


import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;

import it.abc.sicsic.model.Skipper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.log4j.Logger;

public class JasperUtils {

private static final Logger logger = Logger.getLogger("JasperUtils");

   //  private static ResourceBundle bundle = null;
    //  private final static String jasperPath = "/Users/ale/workspace/jdev/sicsic/ViewController/public_html/lib/contratto_sicsic.jasper";
    private static ResourceBundle bundle =ResourceBundle.getBundle("sicsic", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    private final static String LUOGO_STIPULA = "luogoStipula";
    private final static String DATA_STIPULA = "dataStipula";
    private final static String ARMATORE_RAG_SOCIALE = "armatoreRagSoc";
    private final static String ARMATORE_INDIRIZZO = "armatoreIndirizzo";
    private final static String ARMATORE_PIVA = "armatorePIva";
    private final static String CONDUTTORE_NOME_COGNOME = "conduttoreNomeCognome";
    private final static String CONDUTTORE_NASCITA_LUOGO_DATA = "conduttoreNascitaLuogoData";
    private final static String DOCUMENTO_TIPO_NUMERO = "documentoTipoNumero";
    private final static String LOCAZIONE_INIZIO = "locazioneInizio";
    private final static String LOCAZIONE_FINE = "locazioneFine";
    private final static String LOCAZIONE_CONSEGNA = "locazioneConsegna";
    private final static String LOCAZIONE_COSTO = "locazioneCosto";
    private final static String LOCAZIONE_DEPOSITO = "locazioneDeposito";
    private final static String OPTIONALS = "optionals";
    private final static String LIMITI_NAVIGAZIONE = "limitiNavigazione";
    private final static String BARCA_NOME = "barcaNome";
    private final static String BARCA_SIGLA_ISCRIZIONE = "barcaSiglaIscrizione";
    private final static String BARCA_COSTRUITA_DA = "barcaCostruitaDa";
    private final static String BARCA_TIPO_MODELLO = "barcaTipoModello";
    private final static String BARCA_PESCAGGIO = "barcaPescaggio";
    private final static String BARCA_MOTORIZZAZIONE = "barcaMotorizzazione";
    private final static String BARCA_CAP_GASOLIO = "barcaCapGasolio";
    private final static String BARCA_ASS_CORPI = "barcaAssCorpi";
    private final static String BARCA_ASS_RC = "barcaAssRC";
    private final static String BARCA_NOTE = "barcaNote";
    private final static String BARCA_EQ_MINIMO = "barcaEqMinimo";
    private final static String BARCA_BANDIERA = "barcaBandiera";
    private final static String BARCA_LUNGHEZZA = "barcaLunghezza";
    private final static String BARCA_ANNO_COSTRUZIONE = "barcaAnnoCostruzione";
    private final static String BARCA_LARGHEZZA = "barcaLarghezza";
    private final static String BARCA_MATR_MOTORI = "barcaMatrMotori";
    private final static String BARCA_MAX_PERSONE = "barcaMaxPersone";
    private final static String BARCA_NUMERO_REGISTRO = "barcaNumeroRegistro";
    private final static String SKIPPER_TITOLO = "skipperTitolo";
    private final static String SKIPPER_NOME_COGNOME = "skipperNomeCognome";
    private final static String SKIPPER_NATO_A_NATO_IL = "skipperNatoANatoIl";
    private final static String SKIPPER_RESIDENTE = "skipperResidente";
    private final static String SKIPPER_PATENTE_NAUTICA = "skipperPatenteNautica";
    private final static String SKIPPER_PATENTE_RILASCIO_DATA = "skipperPatenteRilascioData";
    private final static String SKIPPER_PATENTE_RILASCIO_LUOGO = "skipperPatenteRilascioLuogo";
    
    private final static String TIPO_CONTRATTO = "tipo";
   
    private final static String REPORTS_DIR = "REPORTS_DIR";
    private final static String PDF_PATH = "pdfPath";
    private final static String IMG_PATH = "imgPath";
    private final static String JASPER_SOURCE_PATH = "jasperSourcePath";
    
    private static Map<String, Object> fields;
    private static Map<String, Object> params;
    public static void setFields(String luogoStipula, String dataStipula, Clienti cliente, String inizio, String fine,
                                 String consegna, String fitto, String deposito, String optionals, Barche barca,
                                 Skipper skipper,String tipo,String tipoContratto) {

        if (luogoStipula == null)
            throw new IllegalArgumentException("luogoStipula non puo' essere nullo");
        if (dataStipula == null)
            throw new IllegalArgumentException("dataStipula non puo' essere nullo");
        if (cliente == null)
            throw new IllegalArgumentException("cliente non puo' essere nullo");
        if (inizio == null)
            throw new IllegalArgumentException("inizio non puo' essere nullo");
        if (fine == null)
            throw new IllegalArgumentException("fine non puo' essere nullo");
        if (consegna == null)
            throw new IllegalArgumentException("consegna non puo' essere nullo");
        if (barca == null)
            throw new IllegalArgumentException("barca non puo' essere nullo");

        fields = new HashMap<String, Object>();
        params = new HashMap<String,Object>(1);
        fields.put(LUOGO_STIPULA, luogoStipula);
        fields.put(DATA_STIPULA, dataStipula);
      //        params.put(ARMATORE_RAG_SOCIALE, "armatore rag sociale");
      //       params.put(ARMATORE_INDIRIZZO, "armatore indirizzo");
      //       params.put(ARMATORE_PIVA, "armatore piva");

        fields.put(ARMATORE_RAG_SOCIALE, bundle.getString(ARMATORE_RAG_SOCIALE));
        fields.put(ARMATORE_INDIRIZZO, bundle.getString(ARMATORE_INDIRIZZO));
        fields.put(ARMATORE_PIVA, bundle.getString(ARMATORE_PIVA));

        fields.put(CONDUTTORE_NOME_COGNOME, cliente.getNomeCognome());
        fields.put(CONDUTTORE_NASCITA_LUOGO_DATA, cliente.getNatoANatoIl());
        fields.put(DOCUMENTO_TIPO_NUMERO, cliente.getDocumentoTipoNumero());
        fields.put(LOCAZIONE_INIZIO, inizio);
        fields.put(LOCAZIONE_FINE, fine);
        fields.put(LOCAZIONE_CONSEGNA, consegna);
        fields.put(LOCAZIONE_COSTO, fitto);
        fields.put(LOCAZIONE_DEPOSITO, deposito);
        fields.put(OPTIONALS, optionals);
        fields.put(LIMITI_NAVIGAZIONE, barca.getLimitiNavigazione());
        fields.put(BARCA_NOME, barca.getNomeBarca());
        fields.put(BARCA_SIGLA_ISCRIZIONE, barca.getSiglaIscrizione());
        fields.put(BARCA_COSTRUITA_DA, barca.getCostruitaDa());
        fields.put(BARCA_TIPO_MODELLO, barca.getTipoModello());
        fields.put(BARCA_PESCAGGIO, barca.getPescaggio());
        fields.put(BARCA_MOTORIZZAZIONE, barca.getMotorizzazione());
        fields.put(BARCA_CAP_GASOLIO, barca.getCapacitaGasolio());
        fields.put(BARCA_ASS_CORPI, barca.getAssPolizzaCorpi());
        fields.put(BARCA_ASS_RC, barca.getAssPolizzaRC());
        fields.put(BARCA_NOTE, barca.getNote());
        fields.put(BARCA_EQ_MINIMO, barca.getNumMinEquipaggio());
        fields.put(BARCA_BANDIERA, barca.getBandiera());
        fields.put(BARCA_LUNGHEZZA, barca.getLunghezza());
        fields.put(BARCA_ANNO_COSTRUZIONE, barca.getAnnoCostruzione());
        fields.put(BARCA_LARGHEZZA, barca.getLarghezza());
        fields.put(BARCA_MATR_MOTORI, barca.getMatricoleMotori());
        fields.put(BARCA_MAX_PERSONE, barca.getNumMaxEquipaggio());
        fields.put(BARCA_NUMERO_REGISTRO, barca.getNumeroRegistro());
        fields.put(SKIPPER_TITOLO, skipper.getTitolo(tipoContratto));
        fields.put(SKIPPER_NOME_COGNOME, skipper.getNomeCognome());
        fields.put(SKIPPER_NATO_A_NATO_IL, skipper.getNatoANatoIl());
        fields.put(SKIPPER_RESIDENTE, skipper.getResidente());
        fields.put(SKIPPER_PATENTE_NAUTICA, skipper.getPatenteNautica());
        fields.put(SKIPPER_PATENTE_RILASCIO_LUOGO, skipper.getPatenteRilascioLuogo());
        fields.put(SKIPPER_PATENTE_RILASCIO_DATA, skipper.getPatenteRilascioData());
        fields.put(TIPO_CONTRATTO, tipo);
        params.put(REPORTS_DIR, bundle.getString(IMG_PATH));

    }

    private static Collection buildReportCollection() {
        Map<String, Object> row = new HashMap<String, Object>();
        Collection data = new ArrayList();
        for (String s : fields.keySet()) {

            row.put(s, fields.get(s));
        }
        data.add(row);

        return data;

    }

    public static void sampleReport(Clienti cliente) throws Exception {
        if (fields == null)
            throw new IllegalArgumentException("you need to call setParams before to call sampleReport");
        String jasperPath = bundle.getString(JASPER_SOURCE_PATH);
        
        File jasper = new File(jasperPath);
        
        InputStream is = new FileInputStream(jasper);

        // imposta directory e file di output
        String path = bundle.getString(PDF_PATH);
        
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMdd_HHmmss");

        String date = sdf.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(path).append(cliente.getNomeCognome());
        sb.append("/").append(date).append(".pdf");

        File f = new File(sb.toString());
        f.getParentFile().mkdirs();

        OutputStream os = new FileOutputStream(f);

        // la avvolge in un'implementazione di JRDataSource
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(buildReportCollection());
        
        JasperRunManager.runReportToPdfStream(is, os, params, dataSource);
        
        
    }

 
    public static void openReport(String fileName) throws IOException, JRException, NamingException {
        
    
        String jasperPath = bundle.getString(JASPER_SOURCE_PATH);
        File jasper = new File(jasperPath);
        InputStream is = new FileInputStream(jasper);
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(buildReportCollection());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        JasperPrint print = JasperFillManager.fillReport(is, params, dataSource);
        JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
        
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        ServletOutputStream out = response.getOutputStream();
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        //response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName);
        response.setContentType("application/pdf");

        out.write(byteArrayOutputStream.toByteArray());
        out.flush();
        out.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
