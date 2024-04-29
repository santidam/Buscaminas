package com.mycompany.crm.controller;

import Exceptions.ComandaException;
import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.persistencia.FileManager;

import java.util.ArrayList;

public class Gestor {
    private FileManager clientesFile = new FileManager("Data", "clientes.txt");
    private FileManager empleadosFile = new FileManager("Data", "empleados.txt");
    private Comercial comercial;
    
    public void login(String dni) throws ComandaException{
        ArrayList<Object> empleados = empleadosFile.leer(false);
        int indice = buscarEmpleado(empleados, dni);

        if (indice == -1) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(ComandaException.ERROR_USER);
        } else {
            this.comercial = (Comercial) empleados.get(indice);
        }
    }

    public void altaCliente(String phone, String name, String contacto, String email) throws ComandaException {

        ArrayList<Object> clientes = clientesFile.leer(true);

        if (buscarCliente(clientes, phone) == -1) {
            Cliente cliente = new Cliente(name, email, phone, contacto);
            cliente.asignarComercial(this.comercial);
            clientesFile.escribir(cliente, true);
            System.out.println("El cliente ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El cliente ya se encuentra en la base de datos");
            throw new ComandaException(ComandaException.CLIENTE_EXISTE);
        }
    }

    public void altaEmpleado(String dni, String name, String apellidos) throws ComandaException {

        ArrayList<Object> empleados = empleadosFile.leer(false);

        if (buscarEmpleado(empleados, dni) == -1) {
            Comercial empleado = new Comercial(dni, name, apellidos);
            empleadosFile.escribir(empleado, false);
            System.out.println("El empleado ha sido registrado correctamente");
        } else {
            System.out.println("ERROR. El empleado ya se encuentra en la base de datos");
            throw new ComandaException(ComandaException.EMPLEADO_EXISTE);
        }
    }
    public void bajaEmpleado(String dni) throws ComandaException {
        ArrayList<Object> empleados = empleadosFile.leer(false);
        if (empleados.isEmpty()){
            System.out.println("ERROR. No existe ningún empleado en la base de datos");
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        }else{
            int indiceEmpleado = buscarEmpleado(empleados,dni);
            if (indiceEmpleado==-1){
                System.out.println("ERROR. El empleado no está registrado en la base de datos");
                throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
            }else{
                empleados.remove(indiceEmpleado);
                empleadosFile.sobreEscribir(empleados,false);
                System.out.println("Empleado dado de baja con éxito.");
            }
        }

    }
    public String infoCliente(String phoneNumber) throws ComandaException {
        ArrayList<Object> clientes = clientesFile.leer(true);
        String s = "";
        if (clientes.isEmpty()){
            System.out.println("ERROR no existe ningún cliente en la base de datos");
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }else{
            int posCliente = buscarCliente(clientes, phoneNumber);
            if (posCliente == -1){
                System.out.println("ERROR El cliente no se encuentra registrado en la base de datos");
                throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
            }else{
                Cliente c = (Cliente) clientes.get(posCliente);
                s+="*******   INFO CLIENTE   *******"+
                        "\nNombre de Empresa: "+c.getNombre()+
                        "\nContacto "+ c.getContacto()+
                        "\nTeléfono: "+c.getPhoneNumber()+
                        "\nEmail: "+c.getEmail();
                
            }
        }
        return s;
    }

    public String infoEmpleado(String dni) throws ComandaException{
        ArrayList<Object> empleados = empleadosFile.leer(false);
        String s = "";
        if (empleados.isEmpty()) {
            System.out.println("ERROR no existe ningún empleado en la base de datos");
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        } else {
            int indiceEmpleado = buscarEmpleado(empleados, dni);
            if (indiceEmpleado == -1) {
                System.out.println("ERROR El empleado no se encuentra registrado en la base de datos");
                throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
            } else {
                Comercial e = (Comercial) empleados.get(indiceEmpleado);
                s+="*******   INFO EMPLEADO   *******" +
                        "\nNombre: " + e.getNombre() +
                        "\nApellido: " + e.getApellidos() +
                        "\nDNI: " + e.getDni();

                
            }
        }
        return s;
    }

    public String listClientes()throws ComandaException{
        ArrayList<Object> clientes = clientesFile.leer(true);
        String s = "";
        if(!clientes.isEmpty()) {
            s+="*******   CLIENTES   *******\n";
            for (Object cliente : clientes) {
                Cliente c = (Cliente) cliente;
                s+="Nombre: " + c.getNombre() + "\n" +
                        "Contacto "+c.getContacto()+"\n"+
                        "Teléfono: " + c.getPhoneNumber() + "\n" +
                        "Email: " + c.getEmail()+"\n";
                s+="-----------------------------------\n";
            }

        }else{
            System.out.println("No hay ningún cliente registrado");
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }
        return s;    
    }
    public String listEmpleados()throws ComandaException{
        ArrayList<Object> empleados = empleadosFile.leer(false);
        String s = "";
        if(!empleados.isEmpty()) {
            s+="*******   Empleados   *******\n";
            for (Object empleado : empleados) {
                Comercial e = (Comercial) empleado;
                s+="Nombre: " + e.getNombre() +" "+e.getApellidos()+ "\n" +
                        "DNI: " + e.getDni()+"\n";

                s+="-----------------------------------\n";
            }

        }else {
            System.out.println("No hay ningún empleado registrado");
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        }
        return s;
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
                Comercial empleado = (Comercial) empleados.get(i);
                if(empleado.getDni().equalsIgnoreCase(dni)){
                    posEmpleado = i;
                }
                i++;
            }
        }
        return posEmpleado;
    }

}
