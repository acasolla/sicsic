package it.soulsoftware.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.log4j.Logger;

public class Utils {
    
    private static Logger logger = Logger.getLogger(Utils.class);
    
    /**
     *Convert a Date in a String, by the given pattern
     * @param date The Date to convert, if null, returns null
     * @param pattern The String patter, Ex: yyyyMMdd
     * @return The converted String
     * @Author Alessandro Casolla
     */
    public static String convertDateToString(Date date, String pattern) {
        String sDate = "";
        try {
            if (date == null)
                return null;

            if (date != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                sDate = simpleDateFormat.format(date);
            }

        } catch (Exception e) {
            logger.error("Error in convertDateToString, " + e);
        }
        return sDate;
    }


    /**
     * Convert a String in a Date, by the given pattern of the String value, if Date is null, returns null
     * @param date A String rapresentation of a Date
     * @param pattern The String pattern, Ex : yyyyMMdd
     * @return The parsed Date, null if parse valid
     * @Author Alessandro Casolla
     */

    public static Date convertStringToDate(String date, String pattern) {

        if (date == null)
            return null;
        Date d = null;
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            try {
                d = simpleDateFormat.parse(date);

            } catch (ParseException e) {
                logger.error("error in convertStringToDate, " + e);
                return null;
            }
        }
        return d;
    }
    

    public interface DatePatterns {

        public final static String yyyyMMdd = "yyyyMMdd";

        public final static String dd_MM_yyyy = "dd/MM/yyyy";
        
       
    }



    /**
     *Convert a length 8 String , to a locale italian string date.
     * Ex. :  formatStringDateForColumn( '20991231' ) returns 31/12/2099
     * @param date Must be 8 length or returns null
     * @return a formatted italian locale String date
     * @Author Alessandro Casolla
     */

    public static String formatStringDateForColumn(String date) {

        if (isNullOrEmpty(date) || date.length() != 8)
            return null;

        String y = date.substring(0, 4);
        String m = date.substring(4, 6);
        String d = date.substring(6, 8);

        return d + "/" + m + "/" + y;
    }



    /**
     *Convert a length 10 String representation of italian date, to a String date suitable for saving in tables.
     * Ex. :  formatColumnDateForSave( '31/12/2099' ) returns 20993112
     * @param date Must be 10 length or returns null
     * @return a String
     * @Author Alessandro Casolla
     */

    public static String formatColumnDateForSave(String date) {

        if (isNullOrEmpty(date) || date.length() != 10)
            return null;

        String d = date.substring(0, 2);
        String m = date.substring(3, 5);
        String y = date.substring(6, 10);

        return y + m + d;
    }

    /**
     *Checks if the string is null or empty
     * @param value The String value
     * @return If the String is null or is empty, return true
     * @Author Alessandro Casolla
     */
    public static boolean isNullOrEmpty(String value) {

        if (value == null)
            return true;

        return value.isEmpty();
    }

    /**
     *Creates a String representing the start of the method, to be used for the method WITHOUT arguments.
     * Ex : String log = Utils.logStartMethod('action()');
     * @param method The name of the method to represent
     * @return A Formatted String
     * @Author Alessandro Casolla
     */

    public static String logStartMethod(String method) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==========================================================");
        sb.append("\nSTARTING...==>" + method);
        sb.append("\nAttributes : ");
        sb.append("No attributes");
        sb.append("\n------------------------------------------------------------");

        return sb.toString();
    }

  
    /**
     *Creates a String representing the start of the method.
     * Ex: String log = Utils.logStartMethod('action(String x, Date y, Object z)', x,y,z);
     * @param method The name of the method, including the attributes
     * @param attributes The param of the method
     * @return A formatted String
     * @Author Alessandro Casolla
     */
    public static String logStartMethod(String method, Object... attributes) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==========================================================");
        sb.append("\nSTARTING...==>" + method);
        sb.append("\nAttributes : ");
        if (attributes == null)
            sb.append("No attributes");
        else {
            for (Object o : attributes)
                sb.append(" [" + o + "] ");
        }
        sb.append("\n------------------------------------------------------------");

        return sb.toString();
    }

    /**
     *Creates a String representing the end of the method, prints also the result of the method
     * Ex: String log = Utils.logEndMethod('action()', result);
     * @param method The name of the method
     * @param result The result of the method
     * @return A formatted String
     * @Author Alessandro Casolla
     */
    public static String logEndMethod(String method, Object result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------------------------------------------------------------");

        sb.append("\nENDED...==>" + method);
        sb.append("\nResult :\n" +
                result);
        sb.append("\n==========================================================");

        return sb.toString();
    }

    /**
     *Creates a String representing the end of the method, to be used for the void methods
     * Ex: String log = Utils.logEndMethod('action()');
     * @param method The name of the method
     * @return A formatted String
     * @Author Alessandro Casolla
     */
    public static String logEndMethod(String method) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------------------------------------------------------------");

        sb.append("\nENDED...==>" + method);
        sb.append("\n==========================================================");


        return sb.toString();
    }


    /**
     * @param s
     * @param n
     * @param padChar
     * @return String
     */
    public static String rightPadding(String s, int n, char padChar) {
        return String.format("%1$-" + n + "s", s).replace(' ', padChar);
    }

    /**
     * @param String
     * @param int
     * @param char
     * @return String
     */
    public static String leftPadding(String s, int n, char padChar) {
        return String.format("%1$#" + n + "s", s).replace(' ', padChar);
    }

    
    public static void printSql(String sql, Object[] params){
        
        logger.debug("===========================================");
        logger.debug("EXECUTING QUERY :");
        if ( sql == null || params == null) {
            logger.error("Error in printSql, params can't be null");
        }
        for ( int i = 0; i < params.length ; i++){
            
            int ind = sql.indexOf("?");
            Object o = params[i];
            if ( o instanceof String)
                sql = sql.substring(0,ind) + "'" + o + "'" + sql.substring(ind +1);
            else if ( o instanceof Number )
                sql = sql.substring(0,ind) +  o  + sql.substring(ind +1);
            else if ( o instanceof Date )
                sql = sql.substring(0,ind)  + o  + sql.substring(ind +1);
            else
                sql = sql.substring(0,ind) + "'" + o + "'" + sql.substring(ind +1);
        }
        
        logger.debug(sql);
        logger.debug("===========================================");      
    }

    /**
     * @param value
     * @param pattern
     * @return
     */
    public static String convertDoubleToMoneyFormatted(double value, String pattern){
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        myFormatter.format(value);
        return myFormatter.format(value);
    }
}
