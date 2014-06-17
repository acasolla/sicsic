package it.abc.sicsic.listener;

import it.abc.sicsic.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.apache.log4j.Logger;


public class ContextListener implements ServletContextListener {
 
    private static Logger logger =
        Logger.getLogger(ContextListener.class);
    
 
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("=============================");
        logger.debug("=============================");
        logger.debug("contextInitialized");
        logger.debug("=============================");
        logger.debug("=============================");
        PersistenceManager.createEntityManagerFactory();
                
                
    }

    public void contextDestroyed(ServletContextEvent event) {
        PersistenceManager.closeEntityManagerFactory();
        logger.debug("=============================");
        logger.debug("=============================");
        logger.debug("contextDestroyed");
        logger.debug("=============================");
        logger.debug("=============================");

    }
}
