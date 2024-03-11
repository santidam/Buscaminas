package com.mycompany.crm.utils;

public class CastData {

    public static int toInt(String num){
        //habrá que poner una excepcioón para asegurar que sea un int
        return Integer.parseInt(num);
    }

    public static double toDouble(String num){
        //habrá que poner una excepcioón para asegurar que sea un double
        return Double.parseDouble(num);
    }
}
