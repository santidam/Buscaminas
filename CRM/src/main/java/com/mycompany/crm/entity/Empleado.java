package com.mycompany.crm.entity;

import java.util.ArrayList;

public class Empleado {
    private String dni;
    private String nombre;
    private String apellidos;

    private ArrayList<Cliente> clientes;


    public Empleado(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clientes = new ArrayList<>();
    }
    

    public void addCliente(Cliente c){

        this.clientes.add(c);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;

    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String clientesToString() {
        StringBuilder stringClientes = new StringBuilder();
        if (clientes.isEmpty()) {
            return "";
        }
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            stringClientes.append(c.getPhoneNumber());
            if (i != clientes.size() - 1) {
                stringClientes.append(",");
            }
        }
        return stringClientes.toString();
    }


    @Override
    public String toString() {
        return dni + ';' +
               nombre + ';' +
               apellidos;
    }
}
