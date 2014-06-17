package it.abc.sicsic;


import javax.naming.InitialContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

import javax.transaction.SystemException;

import org.apache.log4j.Logger;


public class PersistenceManager {

    private static EntityManagerFactory emf;
    private static Logger logger = Logger.getLogger(PersistenceManager.class);

    public static void createEntityManagerFactory() {

        emf = Persistence.createEntityManagerFactory(Constants.System.PERSISTENCE_UNIT);
        logger.debug("============================");
        logger.debug("SICSIC Persistence started..");
        logger.debug("============================");
    }
/**
     *
     * @return
     */
    public static EntityManagerFactory getEntityManagerFactory() {

        if (emf == null)
            createEntityManagerFactory();
        return emf;
    }
/**
 * 
 */
    public static void closeEntityManagerFactory() {

        if (emf != null) {
            emf.close();
            emf = null;
            logger.debug("============================");
            logger.debug("SICSIC Persistence closed..");
            logger.debug("============================");
        }

    }
/**
     *
     * @return
     */

    public static EntityManager getEntityManager() {
        logger.debug("emf=" + emf == null);
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(Constants.System.PERSISTENCE_UNIT);
            logger.debug("emf=" + emf == null);
        }
        return emf.createEntityManager();
    }

  

}
