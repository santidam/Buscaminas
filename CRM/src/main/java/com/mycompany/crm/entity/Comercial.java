package com.mycompany.crm.entity;

public class Comercial {

    private String dni;
    private String nombre;
    private String apellidos;
    private int porcentajeComision;


    public Comercial(){}
    public Comercial(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.porcentajeComision = 5;
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

    public int getPorcentajeComision() {
        return porcentajeComision;
    }

    @Override
    public String toString() {
        return dni + ";" + nombre + ";" + apellidos + ";" + porcentajeComision;
    }
}
