

package com.mycompany.crm;

import com.mycompany.crm.utils.FrequentMethods;
import java.io.IOException;

public class CRM {

    public static void main(String[] args) throws IOException{
        System.out.println("Hello World!");
        FrequentMethods fm = new FrequentMethods();
        String nombre = fm.input().readLine();
        System.out.println("Mi nombre es " + nombre);
        
        
    }
    
    public static String getDni(){
        return "";
    }
}
