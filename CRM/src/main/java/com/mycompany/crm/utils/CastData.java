package com.mycompany.crm.utils;

import com.mycompany.crm.exceptions.ComandaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CastData {

    public static int toInt(String num) throws ComandaException{
        int numEntero = 0;
        try{
            numEntero = Integer.parseInt(num);
        }catch(NumberFormatException e){
            System.out.println("ERROR. Debes poner un número entero entre [-2147483648, 2147483647]");
            throw new ComandaException(ComandaException.ERROR_NUM_ENTERO);
        }
        return numEntero;
    }

    public static double toDouble(String num){
        double numDecimal = 0.0d;
        try{
            numDecimal = Double.parseDouble(num);
        }catch(NumberFormatException e){
            System.out.println("ERROR. Debes poner un número (decimal) entre [-1.79769313486231570E+308, 1.79769313486231570E+308]");
        }
        return numDecimal;
    }
//    public static Date toDate(String date){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        
//        try {
//            java.sql.Date sqlDate = new java.sql.Date(dateFormat.parse(date).getTime());
//        } catch (ParseException ex) {
//            Logger.getLogger(CastData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sqlDate;
//    }
}
