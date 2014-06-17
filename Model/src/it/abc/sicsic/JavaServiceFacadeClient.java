package it.abc.sicsic;

import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;
import it.abc.sicsic.model.Noleggio;
import it.abc.sicsic.model.NoleggioDTO;
import it.abc.sicsic.model.Skipper;
import it.abc.sicsic.model.Spese;

import it.abc.sicsic.model.SpeseDTO;

import java.text.DecimalFormat;

import java.util.List;

import org.apache.log4j.Logger;

public class JavaServiceFacadeClient {
    private static final Logger logger = Logger.getLogger(JavaServiceFacadeClient.class);
    public static void main(String[] args) {
        //try {
          //  logger.info("ok");
            //testMerge();
            //final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
            //List<NoleggioDTO> l = javaServiceFacade.getNoleggiIdBarcaBetween("20000101", "29990101");
            //System.out.println(l.size());
//            for (Barche barche : (List<Barche>)javaServiceFacade.getBarcheFindAll()) {
//                //printBarche(barche);
//            }
//            for (SpeseDTO spese : (List<SpeseDTO>)javaServiceFacade.getSpeseFindAllMonth()) {
//                //printSpese(spese);
//            }
//            for (Spese spese : (List<Spese>)javaServiceFacade.getSpeseFindAll()) {
//                //printSpese(spese);
//            }
//            for (Clienti clienti : (List<Clienti>)javaServiceFacade.getClientiFindAll()) {
//                //printClienti(clienti);
//            }
//            for (Skipper skipper : (List<Skipper>)javaServiceFacade.getSkipperFindAll()) {
//                //printSkipper(skipper);
//            }
           // for (NoleggioDTO noleggio : (List<NoleggioDTO>)javaServiceFacade.getNoleggiIdBarca()) {
             //   printNoleggio(noleggio);
            //}
        //} catch (Exception ex) {
          //  ex.printStackTrace();
        //}
        formattedDoubleToEuro(1212.111111,"$###,###.##");
}
    
    private static void testMerge() throws Exception {
        JavaServiceFacade facade = new JavaServiceFacade();
        List<Clienti> clienti = facade.getClienti("Alessandro", null, null, null, null, null, null);
        for ( Clienti cc : clienti)
            System.out.println(cc.getNome());
        Clienti c = clienti.get(0);
        c.setNome("Test");
        facade.persistOrMergeCliente(c);
        printClienti(c);
        
    }
    

    private static void printBarche(Barche barche) {
        System.out.println("annoCostruzione = " + barche.getAnnoCostruzione());
        System.out.println("assPolizzaCorpi = " + barche.getAssPolizzaCorpi());
        System.out.println("assPolizzaRC = " + barche.getAssPolizzaRC());
        System.out.println("bandiera = " + barche.getBandiera());
        System.out.println("capacitaAcqua = " + barche.getCapacitaAcqua());
        System.out.println("capacitaGasolio = " + barche.getCapacitaGasolio());
        System.out.println("certificazioneCE = " + barche.getCertificazioneCE());
        System.out.println("costruitaDa = " + barche.getCostruitaDa());
        System.out.println("dislocamento = " + barche.getDislocamento());
        System.out.println("id = " + barche.getId());
        System.out.println("larghezza = " + barche.getLarghezza());
        System.out.println("limitiNavigazione = " + barche.getLimitiNavigazione());
        System.out.println("lunghezza = " + barche.getLunghezza());
        System.out.println("matricoleMotori = " + barche.getMatricoleMotori());
        System.out.println("motorizzazione = " + barche.getMotorizzazione());
        System.out.println("nomeBarca = " + barche.getNomeBarca());
        System.out.println("note = " + barche.getNote());
        System.out.println("numMaxEquipaggio = " + barche.getNumMaxEquipaggio());
        System.out.println("numMinEquipaggio = " + barche.getNumMinEquipaggio());
        System.out.println("numeroRegistro = " + barche.getNumeroRegistro());
        System.out.println("pescaggio = " + barche.getPescaggio());
        System.out.println("serialeScafo = " + barche.getSerialeScafo());
        System.out.println("siglaIscrizione = " + barche.getSiglaIscrizione());
        System.out.println("tipoModello = " + barche.getTipoModello());
        
    }

    private static void printSpese(SpeseDTO spese) {
 
        System.out.println("dataSpesa = " + spese.getDataSpesa());
        System.out.println("descrizione = " + spese.getDescrizione());
        System.out.println("id = " + spese.getId());

        System.out.println("importoSpese = " + spese.getImportoSpese());

        System.out.println("pagamento = " + spese.isPagamento());

    }
    
    private static void printNoleggio(NoleggioDTO noleggio) {
        System.out.println("barca = " + noleggio.getNomeBarca());
        System.out.println("dataSpesa = " + noleggio.getDataNoleggio());
        System.out.println("descrizione = " + noleggio.getNote());
        System.out.println("id = " + noleggio.getId());

        System.out.println("importoSpese = " + noleggio.getImportoNolo());
        System.out.println("importoCarburante = " + noleggio.getImportoCarburante());
        System.out.println("pagamento = " + noleggio.isPagamento());

    }

    private static void printClienti(Clienti clienti) {
        System.out.println("citta = " + clienti.getCitta());
        System.out.println("cognome = " + clienti.getCognome());
        System.out.println("documento = " + clienti.getDocumento());
        System.out.println("email = " + clienti.getEmail());
        System.out.println("fax = " + clienti.getFax());
        System.out.println("id = " + clienti.getId());
        System.out.println("indirizzo = " + clienti.getIndirizzo());
        System.out.println("natoA = " + clienti.getNatoA());
        System.out.println("natoIl = " + clienti.getNatoIl());
        System.out.println("nome = " + clienti.getNome());
        System.out.println("note = " + clienti.getNote());
        System.out.println("partitaIva = " + clienti.getPartitaIva());
        System.out.println("provincia = " + clienti.getProvincia());
        System.out.println("ragioneSociale = " + clienti.getRagioneSociale());
        System.out.println("sedeLegale = " + clienti.getSedeLegale());
        System.out.println("telefono = " + clienti.getTelefono());
    }

    private static void printSkipper(Skipper skipper) {
        System.out.println("id = " + skipper.getId());
        System.out.println("natoA = " + skipper.getNatoA());
        System.out.println("natoIl = " + skipper.getNatoIl());
        System.out.println("nome = " + skipper.getNome());
        System.out.println("note = " + skipper.getNote());
        System.out.println("numPatenteNautica = " + skipper.getNumPatenteNautica());
        System.out.println("residenza = " + skipper.getResidenza());
        System.out.println("rilasciataDa = " + skipper.getRilasciataDa());
    }
    
   
    
    public static double formattedDoubleToEuro(double value, String pattern){
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        myFormatter.format(value);
        System.out.println(myFormatter.format(value));
        return value;
    }
}
