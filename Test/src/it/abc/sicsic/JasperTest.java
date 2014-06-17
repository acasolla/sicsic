package it.abc.sicsic;


import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;

import it.abc.sicsic.model.Noleggio;
import it.abc.sicsic.model.NoleggioDTO;
import it.abc.sicsic.model.Skipper;
import it.abc.sicsic.view.backing.util.JasperUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.junit.Ignore;
import org.junit.Test;

public class JasperTest {
    private final static Logger logger = Logger.getLogger(JasperTest.class);
    private final static JavaServiceFacade facade = new JavaServiceFacade();
    
    @Test //@Ignore
    public void deleteAll(){
        List<Noleggio> l = facade.getNoleggio(null, null);
        logger.info("size=" + l.size());
        facade.deleteAllNoleggio();
        l = facade.getNoleggio(null, null);
               logger.info("size=" + l.size());

    }
    
    @Test @Ignore   
    public void path(){
        String path = "primo/Secondo/terzo/quarto";
        logger.info(path.substring(0, path.lastIndexOf("/")));
    }
    
    @Test @Ignore
    public void testDate() throws ParseException {
        
        
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMdd");
        
        Date s = sdf.parse("20110101");
        
        logger.info("date=" + s);
        
        
        
        
        
    }
    
    @Test @Ignore
    public void testPersistence(){
        
        Clienti c = getCliente();
        logger.info("cliente= " + c);
        
    }
    @Test @Ignore

    public void t(){
        
        logger.info("clienti=" + getClienti());
        logger.info("barche=" + getBarche());
        
        
        JasperUtils.setFields("Sorrento",
                              "20120101",
                              getCliente(),
                              "20120101",
                              "20120101",
                              "sorrento",
                              "10",
                              "10",
                              "",
                              getBarca(),
                              getSkipper(),
                              "noleggio","noleggio");


        try {
            JasperUtils.sampleReport(getCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Clienti> getClienti(){
        return facade.getClienti(null, null, null, null, null, null, null);
    }
    
    public List<Barche> getBarche(){
        return facade.getBarche(null, null);
    }

    public List<Skipper> getSkippers(){
        return facade.getSkipper(null, null);
    }
    
    public Clienti getCliente(){
        return getClienti().get(0);
    }
    
    public Barche getBarca(){
        return getBarche().get(0);
    }
    
    public Skipper getSkipper(){
        return getSkippers().get(0);
    }
}
