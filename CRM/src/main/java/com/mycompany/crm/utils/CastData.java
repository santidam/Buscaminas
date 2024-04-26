package com.mycompany.crm.utils;

public class CastData {

    public static int toInt(String num){
        int numEntero = 0;
        try{
            numEntero = Integer.parseInt(num);
        }catch(NumberFormatException e){
            System.out.println("ERROR. Debes poner un número entero entre [-2147483648, 2147483647]");
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
}
