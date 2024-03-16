package com.mycompany.crm.entity;

import java.util.ArrayList;

public class Empleado {
    private String dni;
    private String nombre;
    private String apellidos;
    private String phoneNumber;
    private String email;
    private ArrayList<Empleado> empleados;

    public Empleado(String dni, String nombre, String apellidos, String phoneNumber, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void registrarEmpleado(){}
    public void registrarCliente(){}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
