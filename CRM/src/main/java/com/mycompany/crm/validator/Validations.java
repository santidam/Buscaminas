/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.validator;


import com.mycompany.crm.utils.CastData;
import com.mycompany.crm.utils.InputData;

/**
 *
 * @author admin
 */
public class Validations {

    public static boolean valPhone(String tel){
        //TODO Valentinalinda
        if(tel.length() == 9) {
            for (int i = 0; i < tel.length(); i++) {
            if (!Character.isDigit(tel.charAt(i))) {
                System.out.println("El número de teléfono introducido no es válido.");
                return false;
            }
        }
        System.out.println("Número de teléfono ingresado correctamente.");
        return true;
       }
        System.out.println("El numero de telefono introducido no es valido.");
        return false;
    }


    public static boolean valName(String name){
        //TODO Santi
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

    public static boolean valDni(String dni){
        //TODO Jordi
        dni = dni.strip().toUpperCase();
        boolean isValid = false;
        int resto = 0;
        String dniNum = getNumDni(dni);
        int numDni = 0;
        String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (dniNum.length() == 8 && dni.length() == 9){
            numDni = CastData.toInt(dniNum);
            char letter = dni.charAt(dni.length()-1);

            resto = numDni%23;
            if (letter == validLetters.charAt(resto)){
                isValid = true;
                System.out.println("DNI registrado correctamente");
            } else{
                System.out.println("El último carácter solo puede ser una letra y tiene que ser válida");
            }
        }else{
            System.out.println("El formato del DNi debe ser '12345678X'");
        }

        return isValid;
    }
    
    public static String getNumDni(String dni){
        String validNumbers = "0123456789";
        String numDni = "";
        for(int i = 0; i<dni.length()-1; i++){
            for(int j = 0; j<validNumbers.length(); j++){
                if (dni.charAt(i) == validNumbers.charAt(j)){
                    numDni += dni.charAt(i);
                }
            }
        }      
        return numDni;
    }
    
}
