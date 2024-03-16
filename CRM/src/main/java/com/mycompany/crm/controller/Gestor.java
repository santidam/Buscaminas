package com.mycompany.crm.controller;

import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.persistencia.FileManager;

import java.util.ArrayList;

public class Gestor {
    private FileManager clientesFile = new FileManager("Data", "clientes.txt");
    private FileManager empleadosFile = new FileManager("Data", "empleados.txt");

    public void altaCliente(String phone, String name, String apellidos, String email){
        Cliente cliente = new Cliente(name, apellidos, email, phone);
        System.out.println("Cliente dado de alta con éxito.");
    }

    public void altaEmpleado(String dni, String name, String apellidos){
        System.out.println("Empleado dado de alta con éxito.");
    }
    public void bajaEmpleado(String dni){
        System.out.println("Empleado dado de baja con éxito.");
    }
    public void infoCliente(String phoneNumber){
        System.out.println("Esta es toda la información del cliente con teléfono:");
    }

    public void infoEmpleado(String dni){
        System.out.println("Esta es toda la información del empleado con DNI:");
    }
    public void listClientes(){
        System.out.println("Esta es la lista de todos los clientes:");
    }
    public void listEmpleados(){
        System.out.println("Esta es la lista de todos los empleados:");
    }

    public int buscarCliente(ArrayList<Cliente> clientes, String phoneNumber){
        int posCliente = -1;
        int i = 0;
        if(!clientes.isEmpty()){
            while(posCliente == -1 && i<clientes.size()){
                if(clientes.get(i).getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                    posCliente = i;
                }
                i++;
            }
        }
        return posCliente;
    }

    public int buscarEmpleado(ArrayList<Cliente> empleados, String dni){
        int posEmpleado = -1;
        int i = 0;
        if(!empleados.isEmpty()){
            while(posEmpleado == -1 && i<empleados.size()){
                if(empleados.get(i).getPhoneNumber().equalsIgnoreCase(dni)){
                    posEmpleado = i;
                }
                i++;
            }
        }
        return posEmpleado;
    }



}
