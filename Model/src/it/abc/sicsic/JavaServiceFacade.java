package it.abc.sicsic;

import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;
import it.abc.sicsic.model.Noleggio;
import it.abc.sicsic.model.NoleggioDTO;
import it.abc.sicsic.model.Skipper;
import it.abc.sicsic.model.Spese;

import it.abc.sicsic.model.SpeseDTO;


import it.soulsoftware.utils.Utils;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import org.eclipse.persistence.config.QueryHints;

public class JavaServiceFacade {
    private static final boolean isAutoCommit = true;
    private final EntityManagerHelper entityManagerHelper;
    private static final Logger logger = Logger.getLogger(JavaServiceFacade.class);
    public JavaServiceFacade() {
        entityManagerHelper = new EntityManagerHelper(Constants.System.PERSISTENCE_UNIT, isAutoCommit);
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = entityManagerHelper.getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>QUERY BARCHE</code> */
    
     public List<Barche> getBarche(String nomeBarca, String modello){
         String nB = Utils.isNullOrEmpty(nomeBarca) ? null : nomeBarca.concat("%");
         String mB = Utils.isNullOrEmpty(modello) ? null : modello.concat("%"); 
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(Barche.FIND_BARCHE, Barche.class).setHint(QueryHints.MAINTAIN_CACHE,false);
         q.setParameter(1, nB)
             .setParameter(2, nB)
             .setParameter(3, mB)
             .setParameter(4, mB);
         return q.getResultList();        
     }

    public Barche persistBarche(Barche barche) throws Exception {
        Barche result = null;
        try{
            result = (Barche)entityManagerHelper.persistEntity(barche);
        }catch(Exception e){
            throw e;
        }
        
        return result;
    }

    public Barche mergeBarche(Barche barche) {
        return (Barche)entityManagerHelper.mergeEntity(barche);
    }

    public void removeBarche(Barche barche) {
        barche = entityManagerHelper.getEntityManager().find(Barche.class, barche.getId());
        entityManagerHelper.removeEntity(barche);
    }
  
    public List<Barche> getBarcheFindAll() {
        return entityManagerHelper.getEntityManager().
            createNativeQuery(Barche.BARCHE_FIND_ALL, Barche.class)
            .setHint(QueryHints.MAINTAIN_CACHE,false).getResultList();
    }

     public boolean persistOrMergeBarca(Barche barca) throws Exception {
         logger.info(Utils.logStartMethod("persistOrMergeBarca", barca));
         if ( barca == null ) 
             throw new IllegalArgumentException("Barca non puo' essere null!");
         
         if ( barca.getId() == 0){
             try{
                 logger.info("id=0, saving barche");
                  entityManagerHelper.persistEntity(barca);
                 }
             catch(Exception e){
                     throw e;
                 }
         
         } else {
             
             try{  
                 logger.info("updating barche");               
                 entityManagerHelper.mergeEntity(barca);
                 }
             catch(Exception e){
                     throw e;
                 }
         }
         return true;
     }


    /** <code>QUERY SPESE</code> */
    
     public List<Spese> getSpese(String data, String barca){
         String dS = Utils.isNullOrEmpty(data) ? null : data.concat("%");
         String bS = Utils.isNullOrEmpty(barca) ? null : barca.concat("%"); 
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(Spese.FIND_SPESE, Spese.class).setHint(QueryHints.MAINTAIN_CACHE,false);
         q.setParameter(1, dS)
             .setParameter(2, dS)
             .setParameter(3, bS)
             .setParameter(4, bS);
         return q.getResultList();        
     }


     public SpeseDTO getSpesaById(Integer id){
         logger.info("getSpesaById id = " + id);
         SpeseDTO result = null;
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(SpeseDTO.FIND_SPESE_BY_ID, SpeseDTO.class).setHint(QueryHints.MAINTAIN_CACHE,false);
         q.setParameter(1, id);
         result = (SpeseDTO) q.getSingleResult();
         logger.info("getSpesaById result = " + result.getId());
         return result;        
     }

    public List<SpeseDTO> getSpeseFindAllMonth(String dataSpeseDA, String dataSpeseA, String tipoSpesa,String pagamento,String note) {
        logger.info(Utils.logStartMethod("getSpeseFindAllMonth",dataSpeseDA,dataSpeseA,tipoSpesa,pagamento,note));
       int i = 0;
        String from = Utils.isNullOrEmpty(dataSpeseDA) ? "20010101" : dataSpeseDA;
        String to = Utils.isNullOrEmpty(dataSpeseA) ? "29990101" : dataSpeseA;
        String type = Utils.isNullOrEmpty(tipoSpesa) ? null : tipoSpesa.concat("%");
        String noteParam = Utils.isNullOrEmpty(note) ? null : note.concat("%"); 
       Query q =  entityManagerHelper.getEntityManager().createNativeQuery(SpeseDTO.FIND_SPESE_JOIN_BARCA_BETWEEN, SpeseDTO.class)
           .setHint(QueryHints.MAINTAIN_CACHE,false);
        q.setParameter(++i, from)
            .setParameter(++i, to)
        .setParameter(++i, pagamento)
        .setParameter(++i, pagamento)
        .setParameter(++i, noteParam)
        .setParameter(++i, noteParam)
        
        .setParameter(++i, type)
            .setParameter(++i, type)
            .setParameter(++i, from)
            .setParameter(++i, to)
            .setParameter(++i, type)
            .setParameter(++i, type)
        .setParameter(++i, pagamento)
        .setParameter(++i, pagamento)
        .setParameter(++i, noteParam)
        .setParameter(++i, noteParam);
        List<SpeseDTO> result = q.getResultList();
        logger.info(Utils.logEndMethod("getSpeseFindAllMonth",result));
        
        return result;
    }
    
    public Spese persistSpese(Spese spese) {
        return (Spese)entityManagerHelper.persistEntity(spese);
    }

    public Spese mergeSpese(Spese spese) {
        return (Spese)entityManagerHelper.mergeEntity(spese);
    }

    public void removeSpese(Spese spese) {
        spese = entityManagerHelper.getEntityManager().find(Spese.class, spese.getId());
        entityManagerHelper.removeEntity(spese);
    }
     public void removeSpeseDTO(SpeseDTO spese) {
         Spese spe;
         spe = entityManagerHelper.getEntityManager().find(Spese.class, spese.getId());
         entityManagerHelper.removeEntity(spe);
     }

     public Spese persistOrMergeSpese(Spese spese) throws Exception {
          logger.info(Utils.logStartMethod("persistOrMergeSpese", spese));
          if ( spese == null ) 
              throw new IllegalArgumentException("Spese non puo' essere null!");
          
          if ( spese.getId() == 0){
              try{
                  logger.info("id=0, saving noleggio");
                   entityManagerHelper.persistEntity(spese);
                  }
              catch(Exception e){
                      throw e;
                  }
          
          } else {
              
              try{  
                  logger.info("updating spese");               
                  entityManagerHelper.mergeEntity(spese);
                  }
              catch(Exception e){
                      throw e;
                  }
          }
          return spese;
      }
  
    
    /** <code>QUERY NOLEGGIO</code> */
    
     
      public List<Noleggio> getNoleggio(String dataNoleggioDA, String dataNoleggioA){
          String nS = Utils.isNullOrEmpty(dataNoleggioDA) ? null : dataNoleggioDA;
          String rS = Utils.isNullOrEmpty(dataNoleggioA) ? null : dataNoleggioA; 
          Query q = entityManagerHelper.getEntityManager().createNativeQuery(Noleggio.FIND_NOLEGGIO, Noleggio.class).setHint(QueryHints.MAINTAIN_CACHE,false);
          q.setParameter(1, nS)
              .setParameter(2, nS)
              .setParameter(3, rS)
              .setParameter(4, rS);
          return q.getResultList();        
      }

    public Noleggio persistNoleggio(Noleggio noleggio) {
        return (Noleggio)entityManagerHelper.persistEntity(noleggio);
    }

    public Noleggio mergeNoleggio(Noleggio noleggio) {
        return (Noleggio)entityManagerHelper.mergeEntity(noleggio);
    }

    public void removeNoleggio(Noleggio noleggio) {
        noleggio = entityManagerHelper.getEntityManager().find(Noleggio.class, noleggio.getId());
        entityManagerHelper.removeEntity(noleggio);
    }
     public void removeNoleggioDTO(NoleggioDTO noleggio) {
         Noleggio nol;
         nol = entityManagerHelper.getEntityManager().find(Noleggio.class, noleggio.getId());
         entityManagerHelper.removeEntity(nol);
     }

    public boolean persistOrMergeNoleggio(Noleggio noleggio) throws Exception {
         logger.info(Utils.logStartMethod("persistOrMergeNoleggio", noleggio));
         if ( noleggio == null ) 
             throw new IllegalArgumentException("Noleggio non puo' essere null!");
         
         if ( noleggio.getId() == 0){
             try{
                 logger.info("id=0, saving noleggio");
                  entityManagerHelper.persistEntity(noleggio);
                 }
             catch(Exception e){
                     throw e;
                 }
         
         } else {
             
             try{  
                 logger.info("updating noleggio");               
                 entityManagerHelper.mergeEntity(noleggio);
                 }
             catch(Exception e){
                     throw e;
                 }
         }
         return true;
     }
    
     public List<NoleggioDTO> getNoleggiIdBarca(){
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(NoleggioDTO.FIND_NOLEGGIO_JOIN_BARCA_BETWEEN, NoleggioDTO.class).setHint(QueryHints.MAINTAIN_CACHE,false);

        return q.getResultList();
     }
     
     public List<NoleggioDTO> getNoleggiIdBarcaBetween(String dataNoleggioDA, 
                                                       String dataNoleggioA, 
                                                       Barche barca, 
                                                       Clienti cliente, 
                                                       double impNolo,
                                                       String pagamento){
         logger.info(Utils.logStartMethod("getNoleggiIdBarcaBetween",dataNoleggioDA,dataNoleggioA,barca,cliente,impNolo,pagamento));
         
         String dataDa = Utils.isNullOrEmpty(dataNoleggioDA) ? "20010101" : dataNoleggioDA;
         String dataA = Utils.isNullOrEmpty(dataNoleggioA) ? "29990101" : dataNoleggioA;
         
         int idBarca = (barca!=null) ? barca.getId() : 0;
         int idCliente = (cliente!=null) ? cliente.getId(): 0;
         int i = 0;
         logger.info(Utils.logStartMethod("barca", barca));
         logger.info(Utils.logStartMethod("cliente", cliente));
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(NoleggioDTO.FIND_NOLEGGIO_JOIN_BARCA_BETWEEN, 
                                                                 NoleggioDTO.class).setHint(QueryHints.MAINTAIN_CACHE,false);
       q.setParameter(++i, dataDa)
         .setParameter(++i, dataA)
         .setParameter(++i, idBarca)
         .setParameter(++i, idBarca)
         .setParameter(++i, pagamento)
         .setParameter(++i, pagamento)
         
         .setParameter(++i, idCliente)
         .setParameter(++i, idCliente)
         .setParameter(++i, impNolo)
         .setParameter(++i, impNolo)
         .setParameter(++i, dataDa)
         .setParameter(++i, dataA)
         .setParameter(++i, idBarca)
         .setParameter(++i, idBarca)
         .setParameter(++i, pagamento)
         .setParameter(++i, pagamento)
         
         .setParameter(++i, idCliente)
         .setParameter(++i, idCliente)
         .setParameter(++i, impNolo)
         .setParameter(++i, impNolo)
         .setParameter(++i, dataDa)
         .setParameter(++i, dataA)
         .setParameter(++i, idBarca)
         .setParameter(++i, idBarca)
         .setParameter(++i, pagamento)
         .setParameter(++i, pagamento)
         
         .setParameter(++i, idCliente)
         .setParameter(++i, idCliente)
         .setParameter(++i, impNolo)
         .setParameter(++i, impNolo);
         List<NoleggioDTO> result = q.getResultList();
         logger.info(Utils.logEndMethod("getNoleggiIdBarcaBetween",result));
         
        return result;
     }
     
     public void deleteAllNoleggio(){
         entityManagerHelper.getEntityTransaction().begin();
         entityManagerHelper.getEntityManager().createNativeQuery("DELETE FROM noleggio").executeUpdate();
         entityManagerHelper.getEntityTransaction().commit();
         
     }
     
  
     public void deleteAllSpese(){
         entityManagerHelper.getEntityTransaction().begin();
         entityManagerHelper.getEntityManager().createNativeQuery("DELETE FROM spese").executeUpdate();
         entityManagerHelper.getEntityTransaction().commit();
         
     }
     
   /** <code>QUERY CLIENTI</code> */

    public List<Clienti> getClienti( String nome,
                                      String cognome,
                                      String ragioneSociale,
                                      String citta,
                                      String documento,
                                      String telefono,
                                      String dataNascita ) {
       
         String n = Utils.isNullOrEmpty(nome) ? null : nome.concat("%");
         String s = Utils.isNullOrEmpty(cognome)  ?  null : cognome.concat("%");        
         String r = Utils.isNullOrEmpty(ragioneSociale) ? null : ragioneSociale.concat("%");
         String c = Utils.isNullOrEmpty(citta)  ?  null : citta.concat("%"); 
         String d = Utils.isNullOrEmpty(documento) ? null : documento.concat("%");
         String t = Utils.isNullOrEmpty(telefono) ? null : telefono.concat("%");
         String dn = Utils.isNullOrEmpty(dataNascita) ? null : dataNascita.concat("%");

         Query q = entityManagerHelper.getEntityManager()
                 .createNativeQuery(Clienti.FIND_CLIENTI, Clienti.class).setHint(QueryHints.MAINTAIN_CACHE,false);
        Query parameter =
            q.setParameter(1, n)
             .setParameter(2, n)
             .setParameter(3, s)
             .setParameter(4, s)
         .setParameter(5, r)
         .setParameter(6, r)
         .setParameter(7, c)
         .setParameter(8, c)
         .setParameter(9, d)
         .setParameter(10, d)
         .setParameter(11, t)
         .setParameter(12, t)
         .setParameter(13, dn)
         .setParameter(14, dn);
        return q.getResultList();    
     }    

    public Clienti persistClienti(Clienti clienti) throws Exception {
        Clienti result = null;
        try{            
                result =(Clienti)entityManagerHelper.persistEntity(clienti);
            }
        catch(Exception e){
                throw e;
            }        
        return result;
    }

    public boolean persistOrMergeCliente(Clienti cliente) throws Exception {
        logger.info(Utils.logStartMethod("persistOrMergeCliente", cliente));
        if ( cliente == null ) 
            throw new IllegalArgumentException("Cliente non puo' essere null!");
        
        if ( cliente.getId() == 0){
            try{
                logger.info("id=0, saving clienti");
                 entityManagerHelper.persistEntity(cliente);
                }
            catch(Exception e){
                    throw e;
                }
        
        } else {
            
            try{  
                logger.info("updating clienti");
               //Clienti c = entityManagerHelper.getEntityManager().find(Clienti.class, cliente.getId());
                entityManagerHelper.mergeEntity(cliente);
                }
            catch(Exception e){
                    throw e;
                }
        }
        return true;
    }

    public Clienti mergeClienti(Clienti clienti) {
        return (Clienti)entityManagerHelper.mergeEntity(clienti);
    }

    public void removeClienti(Clienti clienti) {
        clienti = entityManagerHelper.getEntityManager().find(Clienti.class, clienti.getId());
        entityManagerHelper.removeEntity(clienti);
    }

     public List<Clienti> getClientiFindAll() {
         return entityManagerHelper.getEntityManager().
             createNativeQuery(Clienti.CLIENTI_FIND_ALL, Clienti.class).
             setHint(QueryHints.MAINTAIN_CACHE,false).getResultList();            
     }


    /** <code>QUERY SKIPPER</code> */
    
     public List<Skipper> getSkipper(String nomeSkipper, String residenza){
         String nS = Utils.isNullOrEmpty(nomeSkipper) ? null : nomeSkipper.concat("%");
         String rS = Utils.isNullOrEmpty(residenza) ? null : residenza.concat("%"); 
         Query q = entityManagerHelper.getEntityManager().createNativeQuery(Skipper.FIND_SKIPPER, Skipper.class).setHint(QueryHints.MAINTAIN_CACHE,false);
         q.setParameter(1, nS)
             .setParameter(2, nS)
             .setParameter(3, rS)
             .setParameter(4, rS);
         return q.getResultList();        
     }

    public Skipper persistSkipper(Skipper skipper) {
        return (Skipper)entityManagerHelper.persistEntity(skipper);
    }

    public Skipper mergeSkipper(Skipper skipper) {
        return (Skipper)entityManagerHelper.mergeEntity(skipper);
    }

    public void removeSkipper(Skipper skipper) {
        skipper = entityManagerHelper.getEntityManager().find(Skipper.class, skipper.getId());
        entityManagerHelper.removeEntity(skipper);
    }
    
     public boolean persistOrMergeSkipper(Skipper skipper) throws Exception {
         logger.info(Utils.logStartMethod("persistOrMergeSkipper", skipper));
         if ( skipper == null ) 
             throw new IllegalArgumentException("Skipper non puo' essere null!");
         
         if ( skipper.getId() == 0){
             try{
                 logger.info("id=0, saving skipper");
                  entityManagerHelper.persistEntity(skipper);
                 }
             catch(Exception e){
                     throw e;
                 }
         
         } else {
             
             try{  
                 logger.info("updating skipper");               
                 entityManagerHelper.mergeEntity(skipper);
                 }
             catch(Exception e){
                     throw e;
                 }
         }
         return true;
     }

     public List<Skipper> getSkipperFindAll() {
             return entityManagerHelper.getEntityManager().
                 createNativeQuery(Skipper.SKIPPER_FIND_ALL, Skipper.class).
                 setHint(QueryHints.MAINTAIN_CACHE,false).getResultList();
     }

    
    private class EntityManagerHelper {
    //    final private EntityManagerFactory _entityManagerFactory;
        final private boolean _isAutoCommit;

        private EntityManager _entityManager;

        EntityManagerHelper(String persistenceUnit, boolean isAutoCommit) {
  //          _entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
            _isAutoCommit = isAutoCommit;
        }

        public EntityManager getEntityManager() {
            if (_entityManager == null) {
                _entityManager = PersistenceManager.getEntityManager();
            }

            return _entityManager;
        }

        public EntityTransaction getEntityTransaction() {
            return getEntityManager().getTransaction();
        }

        public void commitTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.commit();
            }

            _closeEntityManager();
        }

        public void rollbackTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            _closeEntityManager();
        }

        public boolean isTransactionDirty() {
            return (!_isAutoCommit && getEntityTransaction().isActive());
        }

        public Object persistEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.persist(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public Object mergeEntity(Object entity) {
            _beginTransactionIfNeeded();
            entity = _entityManager.merge(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public void removeEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.remove(entity);
            _commitTransactionIfNeeded();
        }

        private void _beginTransactionIfNeeded() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
        }

        private void _commitTransactionIfNeeded() {
            if (_isAutoCommit) {
                commitTransaction();
            }
        }

        private void _closeEntityManager() {
            if (_entityManager != null && _entityManager.isOpen()) {
                _entityManager.close();
            }

            _entityManager = null;
        }
    }
    
 }
