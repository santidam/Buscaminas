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
            clientesFile.escribir(cliente, true);
            System.out.println("El cliente ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El cliente ya se encuentra en la base de datos");
        }
    }

    public void altaEmpleado(String dni, String name, String apellidos){

        ArrayList<Object> empleados = empleadosFile.leer(false);

        if (buscarEmpleado(empleados, dni) == -1) {
            Empleado empleado = new Empleado(dni, name, apellidos);
            empleadosFile.escribir(empleado, false);
            System.out.println("El empleado ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El empleado ya se encuentra en la base de datos");
        }
    }
    public void bajaEmpleado(String dni){
        ArrayList<Object> empleados = empleadosFile.leer(false);
        if (empleados.isEmpty()){
            System.out.println("ERROR. No existe ningún empleado en la base de datos");
        }else{
            int indiceEmpleado = buscarEmpleado(empleados,dni);
            if (indiceEmpleado==-1){
                System.out.println("ERROR. El empleado no está registrado en la base de datos");
            }else{
                empleados.remove(indiceEmpleado);
                empleadosFile.sobreEscribir(empleados,false);
                System.out.println("Empleado dado de baja con éxito.");
            }
        }

    }
    public void infoCliente(String phoneNumber){
        ArrayList<Object> clientes = clientesFile.leer(true);
        if (clientes.isEmpty()){
            System.out.println("ERROR no existe ningún cliente en la base de datos");
        }else{
            int posCliente = buscarCliente(clientes, phoneNumber);
            if (posCliente == -1){
                System.out.println("ERROR El cliente no se encuentra registrado en la base de datos");
            }else{
                Cliente c = (Cliente) clientes.get(posCliente);
                System.out.println("*******   INFO CLIENTE   *******"+
                        "\nNombre: "+c.getNombre()+
                        "\nApellido: "+c.getApellidos()+
                        "\nTeléfono: "+c.getPhoneNumber()+
                        "\nEmail: "+c.getEmail());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void infoEmpleado(String dni) {
        ArrayList<Object> empleados = empleadosFile.leer(false);
        if (empleados.isEmpty()) {
            System.out.println("ERROR no existe ningún empleado en la base de datos");
        } else {
            int indiceEmpleado = buscarEmpleado(empleados, dni);
            if (indiceEmpleado == -1) {
                System.out.println("ERROR El empleado no se encuentra registrado en la base de datos");
            } else {
                Empleado e = (Empleado) empleados.get(indiceEmpleado);
                System.out.println("*******   INFO EMPLEADO   *******" +
                        "\nNombre: " + e.getNombre() +
                        "\nApellido: " + e.getApellidos() +
                        "\nDNI: " + e.getDni());

                System.out.println("-----------------------------------");
            }
        }
    }

    public void listClientes(){
        ArrayList<Object> clientes = clientesFile.leer(true);
        if(!clientes.isEmpty()) {
            System.out.println("*******   CLIENTES   *******");
            for (Object cliente : clientes) {
                Cliente c = (Cliente) cliente;
                System.out.println("Nombre: " + c.getNombre() +" "+c.getApellidos()+ "\n" +
                        "Teléfono: " + c.getPhoneNumber() + "\n" +
                        "Email: " + c.getEmail());
                System.out.println("-----------------------------------");
            }

        }else{
            System.out.println("No hay ningún cliente registrado");
        }

    }
    public void listEmpleados(){
        ArrayList<Object> empleados = empleadosFile.leer(false);
        if(!empleados.isEmpty()) {
            System.out.println("*******   Empleados   *******");
            for (Object empleado : empleados) {
                Empleado e = (Empleado) empleado;
                System.out.println("Nombre: " + e.getNombre() +" "+e.getApellidos()+ "\n" +
                        "DNI: " + e.getDni());

                System.out.println("-----------------------------------");
            }

        }else {
            System.out.println("No hay ningún empleado registrado");
        }
    }

    public int buscarCliente(ArrayList<Object> empleados, String phoneNumber){
        int posCliente = -1;
        int i = 0;
        if(!empleados.isEmpty()){
            while(posCliente == -1 && i<empleados.size()){
                Cliente cliente = (Cliente) empleados.get(i);
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
