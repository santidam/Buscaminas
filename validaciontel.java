package com.mycompany.pruebajava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Admin
 */
public class validaciontel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tel = "";
        do {
          System.out.println("Introduce tu numero de telefono:");
          tel = br.readLine();
        } while (!validatePhone(tel));
    }
    private static boolean validatePhone(String tel){
        if(tel.length() == 9) {
            System.out.println("Numero de telefono ingresado correctamente.");
            return tel.matches("\\d+");
        }
        System.out.println("El numero de telefono introducido no es valido.");
        return false;
    }   
}
