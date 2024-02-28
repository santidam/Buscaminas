/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.validator;


import com.mycompany.crm.exception.CastException;

/**
 *
 * @author admin
 */
public class Validations {
    
    public static boolean valDni(String dni){
        dni = dni.strip().toUpperCase();
        boolean isValid = false;
        int contador = 0;
        int resto = 0;
        int numDni = CastException.toInt(getNumDni(dni));
        String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
        
        if (dni.length() == 9){
            
            char letter = dni.charAt(dni.length()-1);
            resto = numDni%23;
            
            if (letter == validLetters.charAt(resto)){
                
                for (int i=0; i<dni.length()-1; i++){
                    contador++;
                }
                if (contador == 8){
                    isValid = true;
                    System.out.println("DNI registrado correctamente");
                }
            } else{
               System.out.println("La letra del DNI no es válida");
              }    
        }else{
            System.out.println("El formato del DNi debe ser '12345678X'");
        }
        return isValid;
    }
    
    public static String getNumDni(String dni){
        String numDni = "";
        for(int i = 0; i<dni.length()-1; i++){
            numDni += dni.charAt(i);
        }      
        return numDni;
    }
    
}
