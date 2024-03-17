/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm;

import com.mycompany.crm.validator.Validations;
/**
 *
 * @author admin
 */
public class Menu {

    private Validations val = new Validations();
    public void start(String[] args) {

        try {
            switch(args[0].toLowerCase()){
                case "altacliente":
                    val.valAltaCliente(args);
                    break;

                case "altaempleado":
                    val.valAltaEmpleado(args);
                    break;

                case "bajaempleado":
                    val.valBajaEmpleado(args);
                    break;

                case "cliente":
                    val.valClienteInfo(args);
                    break;

                case "empleado":
                    val.valEmpleadoInfo(args);
                    break;

                case "listclientes":
                    val.valClientesList(args);
                    break;

                case "listempleados":
                   val.valEmpleadosList(args);
                    break;
                case "asignarcliente":
                    val.valAsignarCliente(args);
                    break;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR. No has puesto ningun argumento");
        }
    }
}
