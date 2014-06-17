package it.soulsoftware.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


import java.lang.reflect.InvocationTargetException;

import java.util.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichNoteWindow;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.component.UIXEditableValue;
import org.apache.myfaces.trinidad.component.html.HtmlCellFormat;
import org.apache.myfaces.trinidad.component.html.HtmlRowLayout;
import org.apache.myfaces.trinidad.component.html.HtmlTableLayout;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class ViewUtils {
    public final static String FROM_KEY = "FROM_KEY";
    
    private static Logger logger = Logger.getLogger(ViewUtils.class);
    
    /** CAMBIA OGGETTO DA NULLO A STRINGA SE VALORE NON è NULLO */     
    public static String getStringValue(ValueHolder holder){
        return holder.getValue() != null ? holder.getValue().toString() : null; 
    }    
    public static Double getDoubleValue(ValueHolder holder){        
        Double dret = 0.0;
        if ( holder.getValue() != null ){
            try{
                dret = Double.parseDouble(holder.getValue().toString());
                } catch (Exception e){
                    logger.info("can't parse value");
                    dret = 0.;
                }
        }
        return dret;
    }
    public static boolean getBoolueanValue(ValueHolder holder){
        return holder.getValue() != null ? Boolean.parseBoolean(holder.getValue().toString()) : null;
    }   
    
    public static boolean getBooleanValue( RichSelectBooleanRadio radio){
        if ( radio == null || radio.getValue()== null) return false;
        
            logger.info("radio id =" + radio.getId() + " value=" + radio.getValue());
         return  Boolean.parseBoolean(radio.getValue().toString());
    }
    public static int getIntValue(ValueHolder holder){
        int iret = 0;
        if ( holder.getValue() != null ){
            try{
                iret = Integer.parseInt(holder.getValue().toString());
                } catch (Exception e){
                    logger.info("can't parse value");
                    iret = 0;
                }
        }
        return iret;
    }
    
    public static Date getDateValue(ValueHolder holder){
        if ( holder instanceof RichInputDate)
          return holder.getValue() != null ? (Date)holder.getValue() : null; 
        
        return null;
    }
    
    public static Object getObjectValue(ValueHolder holder){
        if ( holder instanceof RichSelectOneChoice)
          return holder.getValue() != null ? (Object)holder.getValue() : null; 
        
        return null;    
    }
       
    public static void setRequestFromPage(String value){
        FacesUtils.setRequestMapValue(FROM_KEY, value);
    }    
    public static String getRequestFromPage(){
        return (String)FacesUtils.getRequestMapValue(FROM_KEY);
    }
    public static void setSessionFromPage(String value){
        FacesUtils.setSessionMapValue(FROM_KEY, value);
    }
    public static String getSessionFromPage(){
        return (String)FacesUtils.getSessionMapValue(FROM_KEY);
    }
    public static final String VERSION_ASSORTMENT="VER: 1.2";
    public static final boolean WORK_DEVELOP_MODE=false;
    
    public static final String TITLE_ASSORTMENT="Carrefour Assortimenti";
    public static final String END_DATE = "20991231";
    public static final String PROFILEACCESSOR = "profileAccessor";
    public static final String FILTER_KEY = "SIC_FILTER";
    public static final String HOME_PAGE="/faces/jsp/main.jspx";
    public static final String LIST_SECTOR_REFERENCE = "listSectorReference";
   
    
    /**
          *
          * @param value
          * @return
          * if value contains only numbers returns true, otherwise false.
          * @author Luigi Porritiello
          */
         
         public static boolean isNumber(String value){
             logger.info(Utils.logStartMethod("isNumber"));
             char[] c = value.toCharArray();
             boolean verify = false;
             boolean noNumber = false;
             for(int i = 0 ; i<c.length ; i++){
                 if(Character.toString(c[i]).hashCode() >= 48 && Character.toString(c[i]).hashCode() <= 57){
                     logger.info("Character is Number: "+c[i]);
                 }else{
                     logger.info("Character is not Number: "+c[i]);
                     noNumber = true;
                 }       
             }
             if(!noNumber)
                 verify = true;
             else
                 verify = false;
             logger.info(Utils.logEndMethod("isNumber",verify));
             return verify;
         }

    /**
     * Check if the value is null, if the value is a String, checks also if the length is > 0
     * @param value
     * @return True if the value is null or empty
     * @Author Alessandro Casolla
     */
    public static boolean isNullOrEmpty(Object value) {
    
        if (value == null)
            return true;

        if (value instanceof String) {
            String app = (String)value;
            return app.isEmpty();
        }

        return false;
    }
    
    /**
     *Checks the value of the components, and return true if almost one is valorized
     * @param components
     * @return True of almost a component is valorized
     * @Author Alessandro Casolla
     */
    public static boolean isOneFilled(Object... components) {

        if (components == null) return false;
        
        for (Object component : components) {
            if( component instanceof ValueHolder){
                ValueHolder holder = (ValueHolder)component;
            if (!isNullOrEmpty(holder.getValue()))
                return true;
            }
            if( component instanceof RichTable){
                RichTable table = (RichTable)component;
            if ( table.getValue() != null && !((List)table.getValue()).isEmpty())
                return true;
            }
        }
        return false;
    }

    /**
     *Check if all the components are valorized. If one is null or empty, return false.
     * @param components
     * @return False if one component is not valorized
     * @Author Alessandro Casolla
     */
    public static boolean areAllFilled(Object... components) {

        for (Object component : components) {
            if( component instanceof ValueHolder){
                ValueHolder holder = (ValueHolder) component;
                if (isNullOrEmpty(holder.getValue()))
                    return false;
            }
            if( component instanceof RichTable){
                RichTable table = (RichTable)component;
            if ( table.getValue() == null || ((List)table.getValue()).isEmpty())
                  return false;
            }
        }
        return true;
    }

    private static Map<String, Object> getParamsFromSession(String key) {

        return (Map<String, Object>)FacesUtils.getSessionMapValue(key);
    }
    
    /**
     *Sets the given components value in session. If a tableToFill is given, it returns filled (to the return page) in fillComponentsByParamsInSession.
     * @param returnPage
     * @param tableToFill
     * @param components
     * @Author Alessandro Casolla
     */
    public static void setParamsInSession(String key,String returnPage, RichTable tableToFill, List<Object> components) {

        logger.debug(Utils.logStartMethod("void setParamsInSession(String key,String returnPage, RichTable tableToFill, Object[] components)",
                                          key,returnPage, tableToFill, components));

        if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
        if (components == null) {
            FacesUtils.setSessionMapValue(key, null);
            logger.debug(Utils.logEndMethod("void setParamsInSession(String returnPage, RichTable tableToFill, Object[] components)"));
            return;
        }

        if (Utils.isNullOrEmpty(returnPage))
            throw new IllegalArgumentException("returnPage is mandatory");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("PAGE_KEY", returnPage);
        if (tableToFill != null) {
            map.put("TABLE_TO_FILL", tableToFill.getValue());
        }
        for (Object o : components) {
            if (o instanceof UIXEditableValue) {
                UIXEditableValue h = (UIXEditableValue)o;
                logger.debug("SETTING PARAMS key=" + h.getId() + " value=" + h.getValue());
                map.put(h.getId(), h.getValue());
            }
            if (o instanceof RichOutputText) {
                RichOutputText ot = (RichOutputText)o;
                logger.debug("SETTING PARAMS key=" + ot.getId() + " value=" + ot.getValue());
                map.put(ot.getId(), ot.getValue());
            }
            if (o instanceof RichTable) {
                RichTable t = (RichTable)o;
                map.put(t.getId(), t.getValue());
            }
            if (o instanceof RichPanelBox) {
                RichPanelBox pb = (RichPanelBox)o;
                map.put(pb.getId(), pb.getText());
               
            }
            
            if (o instanceof String) {
                String s = (String)o;
                map.put("" + s.hashCode(), s);
            }
        }

        for (String s : map.keySet()) {
            logger.debug("key=" + s + " value=" + map.get(s));
        }
        logger.debug(Utils.logEndMethod("void setParamsInSession(String returnPage, RichTable tableToFill, Object[] components)"));
        FacesUtils.setSessionMapValue(key, map);
    }

    public static List getListToFillFromSession(String key){
        if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
        Map<String,Object> map = getParamsFromSession(key);
        
        
        return (List)map.get("TABLE_TO_FILL");
    }
    
    public static String getReturnPageFromSession(String key){
        if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
        Map<String,Object> map = getParamsFromSession(key);
        
        if ( map != null)
            return (String)map.get("PAGE_KEY");
        else
            return null;
    }
    
    public static void setListToFillInSession(String key,List tableToFill){
      
      if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
      if (tableToFill == null) throw new IllegalArgumentException("tableToFill cannot be null");
      
        Map<String,Object> map = getParamsFromSession(key);
        
        if ( map == null ) throw new IllegalArgumentException("map in session cannot be null");
        
        map.put("TABLE_TO_FILL", tableToFill);
        
        return;
    }
    /**
     *Fills the components of the list with the value assigned by setParamsInSession, it the components id's doesn't
     * match withe the ones in sessione, thorws new IllegalArgumentException.
     * It the tableToFill is given, it will be filled with the return value.
     * @param tableToFill
     * @param components
     * @Author Alessandro Casolla
     */
    public static void fillComponentsByParamsInSession(String key,RichTable tableToFill, List<Object> components) {

        if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
        logger.debug(Utils.logStartMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))",
                                          tableToFill, components));

        Map<String, Object> map = getParamsFromSession(key);

        if (map == null) {
            logger.warn("No params in session.. ");
            logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
            return;
        }

        logger.debug("params catched :");
        logger.debug("------------------------------");
        for (String s : map.keySet()) {
            logger.debug("key=" + s + " value=" + map.get(s));
        }

        logger.debug("------------------------------");


        if (tableToFill != null && map.containsKey("TABLE_TO_FILL")) {
            tableToFill.setValue(map.get("TABLE_TO_FILL"));
        }

        if (components == null || components.isEmpty()) {
            logger.debug("components null or Empty, returning...");
            logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
            return;
        }

        for (Object o : components) {
            if (o instanceof UIXEditableValue) {
                UIXEditableValue h = (UIXEditableValue)o;
                if (!map.containsKey(h.getId())) {
                    throw new IllegalArgumentException("The components List, is not the same in the session. The UIXEditableValue id=" + h.getId() +" is not present");
                }
                h.setValue(map.get(h.getId()));
                logger.debug("component : [" + h + "], setted value=[" + map.get(h.getId()));
            }
            if (o instanceof RichOutputText){
                RichOutputText ot = (RichOutputText)o;
                if (!map.containsKey(ot.getId())) {
                    throw new IllegalArgumentException("The components List, is not the same in the session. The RichOutputText id=" + ot.getId() +" is not present");
                }
                ot.setValue(map.get(ot.getId()));
                logger.debug("component : [" + ot + "], setted value=[" + map.get(ot.getId()));
            }
            if (o instanceof RichTable) {
                RichTable t = (RichTable)o;
                if (!map.containsKey(t.getId())) {
                    throw new IllegalArgumentException("The components List, is not the same in the session. The RichTable id=" + t.getId() +" is not present");
                }
                t.setValue(map.get(t.getId()));
                //   logger.debug("component : [" + t + "], setted value=[" + map.get(t.getId()));
            }
            
            if (o instanceof RichPanelBox) {
                RichPanelBox pb = (RichPanelBox)o;
                if (!map.containsKey(pb.getId())) {
                    throw new IllegalArgumentException("The components List, is not the same in the session. The PanelBox id=" + pb.getId() +"  is not present");
                }
                pb.setText((String)map.get(pb.getId()));
               
            }
            
            
            /*
            if (o instanceof String) {
                String s = (String)o;
                if (!map.containsKey("" + s.hashCode())) {
                    throw new IllegalArgumentException("The components List, is not the same in the session. The String is not present");
                }
                s = (String)map.get("" + s.hashCode());
            }
            */
        }

        logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
        FacesUtils.setSessionMapValue(key, null);
    }

    public static void fillComponentsByParamsInSession(String key,RichTable tableToFill, List<Object> components,RichPopup noteWindow,RichOutputText otNW) {

        if (Utils.isNullOrEmpty(key) ) throw new IllegalArgumentException("returnPage is mandatory");
        logger.debug(Utils.logStartMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))",
                                          tableToFill, components));

        Map<String, Object> map = getParamsFromSession(key);

        if (map == null) {
            logger.warn("No params in session.. ");
            logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
            return;
        }

        logger.debug("params catched :");
        logger.debug("------------------------------");
        for (String s : map.keySet()) {
            logger.debug("key=" + s + " value=" + map.get(s));
        }

        logger.debug("------------------------------");

        
        if (tableToFill != null && map.containsKey("TABLE_TO_FILL")) {
                    tableToFill.setValue(map.get("TABLE_TO_FILL"));
                }

                if (components == null || components.isEmpty()) {
                    logger.debug("components null or Empty, returning...");
                    logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
                    return;
                }

                for (Object o : components) {
                    if (o instanceof UIXEditableValue) {
                        UIXEditableValue h = (UIXEditableValue)o;
                        if (!map.containsKey(h.getId())) {
                            throw new IllegalArgumentException("The components List, is not the same in the session. The UIXEditableValue id=" + h.getId() +" is not present");
                        }
                        h.setValue(map.get(h.getId()));
                        logger.debug("component : [" + h + "], setted value=[" + map.get(h.getId()));
                    }
                    if (o instanceof RichOutputText){
                        RichOutputText ot = (RichOutputText)o;
                        if (!map.containsKey(ot.getId())) {
                            throw new IllegalArgumentException("The components List, is not the same in the session. The RichOutputText id=" + ot.getId() +" is not present");
                        }
                        ot.setValue(map.get(ot.getId()));
                        logger.debug("component : [" + ot + "], setted value=[" + map.get(ot.getId()));
                    }
                    if (o instanceof RichTable) {
                        RichTable t = (RichTable)o;
                        if (!map.containsKey(t.getId())) {
                            throw new IllegalArgumentException("The components List, is not the same in the session. The RichTable id=" + t.getId() +" is not present");
                        }
                        t.setValue(map.get(t.getId()));
                        //   logger.debug("component : [" + t + "], setted value=[" + map.get(t.getId()));
                    }
                    
                    if (o instanceof RichPanelBox) {
                        RichPanelBox pb = (RichPanelBox)o;
                        if (!map.containsKey(pb.getId())) {
                            throw new IllegalArgumentException("The components List, is not the same in the session. The PanelBox id=" + pb.getId() +"  is not present");
                        }
                        pb.setText((String)map.get(pb.getId()));
                       
                    }
                    
                    
                    /*
                    if (o instanceof String) {
                        String s = (String)o;
                        if (!map.containsKey("" + s.hashCode())) {
                            throw new IllegalArgumentException("The components List, is not the same in the session. The String is not present");
                        }
                        s = (String)map.get("" + s.hashCode());
                    }
                    */
                }

                logger.debug(Utils.logEndMethod("fillComponentsByParamsInSession (RichTable tableToFill, List<Object> components))"));
                FacesUtils.setSessionMapValue(key, null);
            }
    

    /**
     *Resets all the given components value. Add a partial trigger for the components to referesh them.
     * @param components
     * @Author Alessandro Casolla
     */
    
    public static void resetFieldsValues(Object... components) {

    if ( components == null) {
        logger.warn("The componenst are null");
        return;
    }
    
        for (Object o : components) {
            if ( o instanceof UIXEditableValue ){
               UIXEditableValue e = (UIXEditableValue)o;
               e.resetValue();
              
            } else if ( o instanceof RichTable ){
                RichTable t = (RichTable)o;
                t.setValue(null);
            }
        }
    }

    public static void resetSession(){
       logger.info("resetSession ");
       Map<String,Object> session=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
       Set<String> sessionKey=session.keySet();
       Iterator<String> iterator = sessionKey.iterator();
       while (iterator.hasNext()){
           
           String key=iterator.next();
           logger.info("key : " + key);
           if (!key.equalsIgnoreCase(LIST_SECTOR_REFERENCE)&& !key.equalsIgnoreCase(FILTER_KEY)  && !key.equalsIgnoreCase(PROFILEACCESSOR) &&
               (!key.startsWith("oracle")) &&
               (!key.startsWith("ADS")) &&
               (!key.startsWith("org.apache.myfaces")) &&
               (!key.startsWith("javax"))) setObjectInSession( key, null);
       }
    }
        
    public static void setObjectInSession(String key, Object object){
        
        FacesUtils.setSessionMapValue(key, object);
    }
    
    public static Object getObjectFromSession(String key){
        Object obj = FacesUtils.getSessionMapValue(key);
        
        return obj;
    }
    
    public static void resetSessionKey(String key){
        FacesUtils.setSessionMapValue(key, null);
    
    }

    public static String messageError(FacesContext ctx, String message) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "");
        ctx.addMessage(null, fm);
        return null;
    }
    
    public static void refreshComponents (UIComponent ... components){
        for ( UIComponent component : components)
        AdfFacesContext.getCurrentInstance().addPartialTarget(component);
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    public static void downloadTemplate(String filePath, OutputStream out, String message) throws IOException {
        logger.info("downloadTemplate....start");
        java.io.File f = new java.io.File(filePath);
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(f);

            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                out.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            ViewUtils.messageError(FacesContext.getCurrentInstance(), message);
            throw e;
        }
        out.flush();
        logger.info("downloadTemplate....end");
    }

    /**
     *Shows a message in the upper left corner of the page. The suggested documentId is 'd1'.
     * @param bundleKey
     * @param documentId
     * @param popup
     * @param ot
     * @Author Alessandro Casolla
     */

    public static void showMessage(String bundleKey, String documentId, RichPopup popup, RichOutputText ot) {
        if (bundleKey == null)
            throw new IllegalArgumentException("bundle key cannot be null");
        if (documentId == null)
            throw new IllegalArgumentException("document Id cannot be null");
        if (popup == null)
            throw new IllegalArgumentException("popup cannot be null");
        if (ot == null)
            throw new IllegalArgumentException("ot cannot be null");
        logger.debug(Utils.logStartMethod("showMessage", bundleKey, documentId, popup, ot));

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, documentId);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_BEFORE_END);
        //String msg = bundle.containsKey(bundleKey) ? bundle.getString(bundleKey) : "Missing resource :" + bundleKey;
        //ot.setValue(msg);
        popup.show(ph);
        logger.debug(Utils.logEndMethod("showMessage"));
    }
    
    public static void showMessageSuccessInsert(String bundleKey,String subBundle,String documentId, RichPopup popup, RichOutputText ot) {
        if (bundleKey == null)
            throw new IllegalArgumentException("bundle key cannot be null");
        if (documentId == null)
            throw new IllegalArgumentException("document Id cannot be null");
        if (popup == null)
            throw new IllegalArgumentException("popup cannot be null");
        if (ot == null)
            throw new IllegalArgumentException("ot cannot be null");
        logger.debug(Utils.logStartMethod("showMessage", bundleKey, subBundle,documentId, popup, ot));

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, documentId);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_BEFORE_END);
        //String msg = bundle.containsKey(bundleKey) ? bundle.getString(bundleKey) : "Missing resource :" + bundleKey;

        popup.show(ph);
        logger.debug(Utils.logEndMethod("showMessage"));
    }
    
    
    public static void showMessage(String bundleKey,Object object, String documentId, RichPopup popup, RichNoteWindow nw,RichOutputFormatted ot) {
        if (bundleKey == null)
            throw new IllegalArgumentException("bundle key cannot be null");
        if (documentId == null)
            throw new IllegalArgumentException("document Id cannot be null");
        if (popup == null)
            throw new IllegalArgumentException("popup cannot be null");
        if (ot == null)
            throw new IllegalArgumentException("ot cannot be null");
        logger.debug(Utils.logStartMethod("showMessage", bundleKey, documentId, popup, ot));

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, documentId);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_BEFORE_END);
        //String msg = bundle.containsKey(bundleKey) ? bundle.getString(bundleKey) : "Missing resource :" + bundleKey;
        nw.setInlineStyle("height: 500px;width: 400px; ");
        nw.setAutoDismissalTimeout(25);
        //ot.setValue("&lt;b&gt;CAPUOCCHIO&lt;b&gt;&lt;/bn&gt;&lt;b&gt;Accapucchiato&lt;b&gt;");
        ot.setValue("&lt;h3>output &amp; heading&lt;/h3>");
        popup.show(ph);
        logger.debug(Utils.logEndMethod("showMessage"));
    }

    /**
     * @param message
     * @param documentId
     * @param popup
     * @param ot
     *  ï¿½author Salvatore Aprano
     */
    public static void showMessageNoBundle(String message, String documentId, RichPopup popup, RichOutputText ot) {
        if (message == null)
            throw new IllegalArgumentException("message cannot be null");
        if (documentId == null)
            throw new IllegalArgumentException("document Id cannot be null");
        if (popup == null)
            throw new IllegalArgumentException("popup cannot be null");
        if (ot == null)
            throw new IllegalArgumentException("ot cannot be null");
        logger.debug(Utils.logStartMethod("showMessage", message, documentId, popup, ot));

        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, documentId);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_BEFORE_END);
        ot.setValue(message);
        popup.show(ph);
        logger.debug(Utils.logEndMethod("showMessage"));
    }
    
    /**
     * @param idComponent
     * @param popup
     * @Author Luigi Porritiello
     */
    
    public static void showSimulate(String idComponent,RichPopup popup){
        logger.debug(Utils.logStartMethod("void showSimulate(String idComponent,RichPopup popup)",idComponent,popup));
        
        if (idComponent == null)
            throw new IllegalArgumentException("idComponent cannot be null");
        if (popup == null)
            throw new IllegalArgumentException("popup cannot be null");
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, idComponent);
        hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_BEFORE_END);
        
        popup.show(hints);
        
        logger.debug(Utils.logEndMethod("void showSimulate(String idComponent,RichPopup popup)"));
    }
    
    /**
     *
     * @return String
     */
    public static String toProfile() {
        ViewUtils.setObjectInSession(LIST_SECTOR_REFERENCE,null);
        
        return "toProfile";          
    }


    public static void resetDateValidation(RichInputDate ... date){
        for ( RichInputDate d : date){
            d.setMinValue(null);
            d.setMaxValue(null);
        }
    }
}
