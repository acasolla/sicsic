package it.abc.sicsic.view.backing.nautica;

import it.abc.sicsic.view.backing.util.JasperUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.model.CalendarActivity;

import org.eclipse.persistence.config.SystemProperties;


@ManagedBean(name = "backing_calendario")
@SessionScoped
public class Calendario {
 
    public Calendario() {
        super();
    
        
        
    }


    public void createReport(ActionEvent actionEvent) {
        try {
            sampleReport("/template/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args) throws Exception{
        // imposta directory e file di output
        String path = System.getProperty("user.home");
        File f = new File(path,"contratto_sicsic.jasper");
        InputStream is = new FileInputStream(f);
        
        File file = new File(path,"testReport.pdf");
   
        OutputStream os = new FileOutputStream(file);
        
        // crea una java.util.Collection contenente dei dati
        Collection data = null;
        // la avvolge in un'implementazione di JRDataSource
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("name", "test");
        try {
        JasperRunManager.runReportToPdfStream(is, os, null, dataSource);
        // JasperRunManager.runReportToPdf(is, m);
        } catch (JRException e) {
        System.out.println("JRException");
        e.printStackTrace();
        }

      

    }
    
    public void sampleReport(String outputDir) throws IOException {
            // carica il report compilato
            InputStream is = getClass().getResourceAsStream("/template/contratto.jasper");
            System.out.println("is==null?? " + is==null);
            // imposta directory e file di output
            File outDir = new File(outputDir);
            String path = System.getProperty("user.home");
            File file = new File(path,"testReport.pdf");
            
            if ( !file.exists()){
                boolean f = file.createNewFile();
                System.out.println("is created=" + f);
            }
            outDir.mkdirs();
            OutputStream os = new FileOutputStream(file);
     
            // crea una java.util.Collection contenente dei dati
            Collection data = createSampleData();
            // la avvolge in un'implementazione di JRDataSource
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("name", "test");
        try {
            JasperRunManager.runReportToPdfStream(is, os, null, dataSource);
           // JasperRunManager.runReportToPdf(is, m);
        } catch (JRException e) {
            System.out.println("JRException");
            e.printStackTrace();
        }
    }
     
        private Collection createSampleData() {
            Map row;
            Collection data = new ArrayList();
     
            row = new HashMap();
            row.put("name", "Lorem");
            data.add(row);
     
            row = new HashMap();
            row.put("name", "Ipsum");
            data.add(row);
     
            row = new HashMap();
            row.put("name", "Docet");
            data.add(row);
     
            return data;
        }

}
