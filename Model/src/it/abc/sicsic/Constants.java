package it.abc.sicsic;

public class Constants {
    public interface Style{
        public final static String SUBTOTAL="font-weight:bold;background-color:grey"; 
    }
    public interface DatePatterns {

        public final static String yyyyMMdd = "yyyyMMdd";
        public final static String dd_MM_yyyy_HH_mm = "dd/MM/yyyy HH:mm";
        public final static String HH_mm = "HH:mm";
        public final static String dd_MM_yyyy = "dd/MM/yyyy";        
       
    }
    
    public interface MoneyPatterns{
        public final static String EURO = "###,###.00 €";     
    }
    
    public interface Icon{
        public final static String FOLDER = "/img/folder.png"; 
        public final static String INSERT = "/img/insert.png";
        public final static String MANAGEMENT = "/img/managment.png"; 
        public final static String CUSTOMERS = "/img/customers16x16.png"; 
        public final static String BOAT = "/img/boat16x16.png";
        public final static String RENT = "/img/rent16x16.png";
        public final static String CONTRACT = "/img/contract16x16.png";
        public final static String CALENDAR = "/img/calendar16x16.png";        
        public final static String ANCHOR = "/img/anchor16x16.png";        
        public final static String COST = "/img/cost16x16.png";        

    }
    
    public interface Place{
        public final static String GESTIONE_CLIENTI = "GestisciCliente"; 
        public final static String INSERIMENTO_CLIENTE = "InserisciCliente";
        public final static String GESTIONE_BARCHE = "GestisciBarca"; 
        public final static String INSERIMENTO_BARCA = "InserisciBarca";
        public final static String GESTIONE_SKIPPER = "GestisciSkipper"; 
        public final static String INSERIMENTO_SKIPPER = "InserisciSkipper";
        public final static String CALENDARIO = "Calendario"; 
        public final static String GESTIONE_NOLEGGI = "GestisciNautica"; 
        public final static String INSERIMENTO_NOLEGGIO = "InserisciNautica";
        public final static String GESTIONE_SPESE = "GestisciSpese"; 
        public final static String INSERIMENTO_SPESA = "InserisciSpese";
        public final static String CONTRATTO = "Contratto";
        public final static String LOGIN = "Login";
         
    }
    
    public interface System{
        public static final String PERSISTENCE_UNIT = "nauticasicsic";
    }

    public interface Key{
        public final static String USER = "USER";
        public static final String TREE = "TREE";
        public static final String ADMIN = "admin";
        public static final String CANCELLA = "toresalva";
    }
    
    public interface Combo{
        public static final String NOLEGGIO = "Noleggio";
        public static final String LOCAZIONE = "Locazione";
    }
}
