/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm;

import com.mycompany.crm.utils.InputData;
import com.mycompany.crm.validator.Validations;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class Menu {
    
    public void start() throws IOException{

        //variables
        String dni = "";
        String tel = "";

        //dni
        /*do{
           dni = InputData.inputStrLine("Escribe tu DNI: ");
        }while(!Validations.valDni(dni));*/

        //teléfono
        do {
            tel = InputData.inputStrLine("Escribe tu número de teléfono: ");
        } while (!Validations.valPhone(tel));

        //nombre
        /*String nombre = InputData.inputStrLine("Escribe tu nombre completo: ");
        if (Validations.valName(nombre)) {
            System.out.println("Mi nombre es " + nombre);
        }*/

        //email

    }
    
}
