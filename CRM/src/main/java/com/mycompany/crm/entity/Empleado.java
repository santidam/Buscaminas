package com.mycompany.crm.entity;

import java.util.ArrayList;

public class Empleado {
    private String dni;
    private String nombre;
    private String apellidos;




    public Empleado(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public void registrarEmpleado(){}
    public void registrarCliente(){
        //si el empleado isAdmin
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


    @Override
    public String toString() {
        return dni + ';' +
               nombre + ';' +
               apellidos;
    }
}
