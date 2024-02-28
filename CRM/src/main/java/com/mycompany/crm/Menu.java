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
    
    public static void start() throws IOException{
        String dni = "";
      
        do{
           dni = InputData.inputStrLine("Escribe tu DNI: ");
        }while(!Validations.valDni(dni));
        
        int num = InputData.inputInt("Escribe un número entero: ");
    }
    
}
