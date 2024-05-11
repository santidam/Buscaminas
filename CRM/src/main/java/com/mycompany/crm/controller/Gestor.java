package com.mycompany.crm.controller;

import Exceptions.ComandaException;
import com.mycompany.crm.entity.Empresa;
import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.persistencia.FileManager;
import java.sql.SQLException;

import java.util.ArrayList;

public class Gestor {
    
    private Comercial comercial;
    
    public boolean login(String user, String passwd) throws ComandaException, SQLException{
        Comercial u1 = null; // Metodo user
        if (u1==null) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(ComandaException.ERROR_USER);
        }else{
             if (!u1.getPassword().equals(passwd)) {
            throw new ComandaException(ComandaException.ERROR_USER); // Error de contraseña
        }
        this.comercial = u1;
        return true;
        }
       
    }
    
   

    public void altaCliente(String phone, String name, String contacto, String email) throws ComandaException {

        Empresa c = null; // Añadir DAO alta Cliente

        if (c!=null) {
            System.out.println("ERROR. El cliente ya se encuentra en la base de datos");
            throw new ComandaException(ComandaException.CLIENTE_EXISTE);
            
        } else {
            Empresa cliente = new Empresa(name, email, phone, contacto);
            // Añadir metodo DAO para altaCliente
            System.out.println("El cliente ha sido registrado correctamente");
        }
    }

    public void altaEmpleado(String dni, String name, String apellidos) throws ComandaException {

        Comercial c  = null; // DAO de buscar empleado

        if (c!=null) {
            System.out.println("ERROR. El empleado ya se encuentra en la base de datos");
            throw new ComandaException(ComandaException.EMPLEADO_EXISTE);
            
        } else {
            Comercial empleado = new Comercial(dni, name, apellidos);
            // Añadir metodo añadir empleado DAO
            
            System.out.println("El empleado ha sido registrado correctamente");
        }
    }
    public void bajaEmpleado(String dni) throws ComandaException {
        Comercial c = null; // Metodo de DAO buscar Empleado
        if (c == null){
            System.out.println("ERROR. El empleado no está registrado en la base de datos");
            throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
        }else{
                // Añador metodo de borrado DAO
                System.out.println("Empleado dado de baja con éxito.");
        }

    }
    public String infoCliente(String phoneNumber) throws ComandaException {
        Empresa c = null; // Añadir DAO
        String s = "";
        if (c==null){
            System.out.println("ERROR El cliente no se encuentra registrado en la base de datos");
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }else{
            s+="*******   INFO CLIENTE   *******"+
                        "\nNombre de Empresa: "+c.getNombre()+
                        "\nContacto "+ c.getContacto()+
                        "\nTeléfono: "+c.getPhoneNumber()+
                        "\nEmail: "+c.getEmail();
        }
        return s;
    }

    public String infoEmpleado(String dni) throws ComandaException{
        Comercial e = null; // Añadir metodo DAO
        String s = "";
        if (e==null) {
            System.out.println("ERROR El empleado no se encuentra registrado en la base de datos");
            throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
        } else {
            s+="*******   INFO EMPLEADO   *******" +
                        "\nNombre: " + e.getNombre() +
                        "\nApellido: " + e.getApellidos() +
                        "\nDNI: " + e.getDni();
        }
        return s;
    }

    public String listClientes()throws ComandaException{
        ArrayList<Empresa> clientes = null; // Añadir metodo DAO
        String s = "";
        if(!clientes.isEmpty()) {
            s+="*******   CLIENTES   *******\n";
            for (Empresa c : clientes) {
                
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
        ArrayList<Comercial> empleados = null; //  Añadir Metodo DAO
        String s = "";
        if(!empleados.isEmpty()) {
            s+="*******   Empleados   *******\n";
            for (Comercial e : empleados) {
                
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

   

}
