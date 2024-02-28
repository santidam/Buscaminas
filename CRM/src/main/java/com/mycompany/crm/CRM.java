

package com.mycompany.crm;

import com.mycompany.crm.utils.FrequentMethods;
import java.io.IOException;

public class CRM {

    public static void main(String[] args) throws IOException{
        FrequentMethods fm = new FrequentMethods();
        System.out.println("Introduce tu nombre completo");
        String nombre = fm.input().readLine();
        if (validateName(nombre)) {
          System.out.println("Mi nombre es " + nombre);  
        }
        
        
    }
    
    public static String getDni(){
        return "";
    }
    
    public static boolean validateName(String name){
        name = name.trim();
        
        if (name.isEmpty()){
            System.out.println("Error; no has introducio ningun dato");
            return false; 
        }
        String[] partes = name.split("\\s+");
        
        if (partes.length != 2){
            System.out.println("Error: debes introducir el nombre y el apellido");
            return false;
        } 
        
        for (String part : partes) {
        if (part.length() < 2 || part.length() > 20) {
            System.out.println("Error: cada parte del nombre debe tener al menos 2 caracteres y un máximo de 20.");
            return false;
        } else if (!part.matches("[a-zA-Z]+")) {
            System.out.println("Error: solo puedes introducir caracteres alfabéticos en cada parte del nombre.");
            return false;
        }  
    }
        
    return true;    
    }
}
