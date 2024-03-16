package com.mycompany.crm.controller;

import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.entity.Empleado;
import com.mycompany.crm.persistencia.FileManager;

import java.util.ArrayList;

public class Gestor {
    private FileManager clientesFile = new FileManager("Data", "clientes.txt");
    private FileManager empleadosFile = new FileManager("Data", "empleados.txt");

    public void altaCliente(String phone, String name, String apellidos, String email) {

        ArrayList<Object> clientes = clientesFile.leer(true);

        if (buscarCliente(clientes, phone) == -1) {
            Cliente cliente = new Cliente(name, apellidos, email, phone);
            clientesFile.escribir(cliente);
            System.out.println("El cliente ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El cliente ya se encuentra en la base de datos");
        }
    }

    public void altaEmpleado(String dni, String name, String apellidos){

        ArrayList<Object> empleados = empleadosFile.leer(false);

        if (buscarEmpleado(empleados, dni) == -1) {
            Empleado empleado = new Empleado(dni, name, apellidos);
            empleadosFile.escribir(empleado);
            System.out.println("El empleado ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El empleado ya se encuentra en la base de datos");
        }
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
        ArrayList<Object> clientes = clientesFile.leer(true);
        if(!clientes.isEmpty()) {
            System.out.println("*******   CLIENTES   *******");
            for (Object cliente : clientes) {
                Cliente c = (Cliente) cliente;
                System.out.println("Nombre: " + c.getNombre() + "\n" +
                        "Teléfono: " + c.getPhoneNumber() + "\n" +
                        "Email: " + c.getEmail());
                System.out.println("-----------------------------------");
            }

        }else{
            System.out.println("No hay ningún cliente registrado");
        }

    }
    public void listEmpleados(){
        System.out.println("Esta es la lista de todos los empleados:");
    }

    public int buscarCliente(ArrayList<Object> clientes, String phoneNumber){
        int posCliente = -1;
        int i = 0;
        if(!clientes.isEmpty()){
            while(posCliente == -1 && i<clientes.size()){
                Cliente cliente = (Cliente) clientes.get(i);
                if(cliente.getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                    posCliente = i;
                }
                i++;
            }
        }
        return posCliente;
    }

    public int buscarEmpleado(ArrayList<Object> empleados, String dni){
        int posEmpleado = -1;
        int i = 0;
        if(!empleados.isEmpty()){
            while(posEmpleado == -1 && i<empleados.size()){
                Empleado empleado = (Empleado) empleados.get(i);
                if(empleado.getDni().equalsIgnoreCase(dni)){
                    posEmpleado = i;
                }
                i++;
            }
        }
        return posEmpleado;
    }



}
