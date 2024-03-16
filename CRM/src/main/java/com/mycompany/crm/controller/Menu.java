/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.controller;

import com.mycompany.crm.validator.Validations;
/**
 *
 * @author admin
 */
public class Menu {

    private Validations val = new Validations();
    public void start(String[] args) {


        if (args[1].equalsIgnoreCase("altaCliente")) {
            if (val.valLength(args.length, 6)) {
                if(val.valPhone(args[2])) {
                    if(val.valName(args[3], "nombre")) {
                        if (val.valName(args[4], "apellido")){
                            if (val.valEmail(args[5])){
                                System.out.println("Cliente dado de alta con éxito.");
                            }
                        }
                    }
                }
            }
        }


        if (args[1].equalsIgnoreCase("altaEmpleado")) {
            if (val.valLength(args.length, 7)) {
                if (val.valDni(args[2])) {
                    if (val.valName(args[3], "nombre")) {
                        if (val.valName(args[4], "apellido")) {
//                            if (val.area) {
//                                if (val.posicion) {
//                                    System.out.println("Empleado dado de alta con éxito.");
//                                }
//                            }
                        }
                    }
                }
            }
        }

        if (args[1].equalsIgnoreCase("bajaEmpleado")) {
            if (val.valLength(args.length, 3)) {
                if (val.valDni(args[2])) {
                    System.out.println("Empleado dado de baja con éxito.");
                }
            }
        }

        if (args[1].equalsIgnoreCase("cliente")) {
            if (val.valLength(args.length, 3)) {
                if (val.valPhone(args[2])) {
                    System.out.println("Esta es toda la información del cliente con teléfono:" + args[2]);
                }
            }
        }

        if (args[1].equalsIgnoreCase("empleado")) {
            if (val.valLength(args.length, 3)) {
                if (val.valDni(args[2])) {
                    System.out.println("Esta es toda la información del empleado con DNI:" + args[2]);
                }
            }
        }

        if (args[1].equalsIgnoreCase("listClientes")) {
            if (val.valLength(args.length, 2)) {
                System.out.println("Esta es la lista de todos los clientes:");
            }
        }

        if (args[1].equalsIgnoreCase("listEmpleados")) {
            if (val.valLength(args.length, 2)) {
                System.out.println("Esta es la lista de todos los empleados:");
            }
        }

    }
}
