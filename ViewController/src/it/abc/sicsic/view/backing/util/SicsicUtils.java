package it.abc.sicsic.view.backing.util;

import it.abc.sicsic.model.Barche;
import it.abc.sicsic.model.Clienti;

import it.soulsoftware.utils.ViewUtils;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.component.ValueHolder;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import org.apache.log4j.Logger;
public class SicsicUtils {
    private static Logger logger = Logger.getLogger(ViewUtils.class);
    public static Clienti getObjectValueClienti(ValueHolder holder){
        
        logger.info("Clienti holder.getValue() = "+holder.getValue());
        
        Clienti result=null;
                       
        if ( holder instanceof RichSelectOneChoice){
            Object o = holder.getValue() != null ? holder.getValue() : null; 
            if(o instanceof Clienti) result = (Clienti)o;
        }    
         return result;
    }
    
    public static Barche getObjectValueBarche(ValueHolder holder){
        
        logger.info("Barche holder.getValue() = "+holder.getValue());
        
        Barche result=null;
                       
        if ( holder instanceof RichSelectOneChoice){
            Object o = holder.getValue() != null ? holder.getValue() : null; 
            if(o instanceof Barche) result = (Barche)o;
        }    
         return result;
    }
    public static String reverseStringData(String data){
        String reverse = new StringBuffer(data).reverse().toString();
        String anno = reverse.substring(4);
        String annor = new StringBuffer(anno).reverse().toString();
        String mese = reverse.substring(4, 6);
        String meser = new StringBuffer(mese).reverse().toString();
        String giorno = reverse.substring(6, 8);
        String giornor = new StringBuffer(giorno).reverse().toString();
        return annor.concat("/").concat(meser).concat("/").concat(giornor); 
    }
    
    public static Object getValueExpression(String expression) {
           FacesContext facesContext = FacesContext.getCurrentInstance();
           ELContext el = facesContext.getELContext();
           ExpressionFactory ef = facesContext.getApplication().getExpressionFactory();
           String var = "#{".concat(expression).concat("}");
           ValueExpression ve = ef.createValueExpression(el, var, Object.class);
           return ve.getValue(el);
       }

}
