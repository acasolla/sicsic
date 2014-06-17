package it.soulsoftware.utils;

import javax.faces.context.FacesContext;

public class FacesUtils {
    /**
     *Gets an Object from the session
     * @param key
     * @return
     */
    public static Object getSessionMapValue(String key) {
    return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
    /**
     *Sets an Object in the session
     * @param key
     * @param value
     */
    public static void setSessionMapValue(String key, Object value) {
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    /**
     *Gets an Object fom the request
     * @param key
     * @return
     */
    public static Object getRequestMapValue(String key) {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
    }
    /**
     *Sets an Object in the request
     * @param key
     * @param value
     */
    public static void setRequestMapValue(String key, Object value) {
    FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
    }
}
