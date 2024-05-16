package com.mycompany.crm.entity;

import java.util.Date;

public class Comercial {

    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private Date fechaIncorporacion;
    private int porcentajeComision;


    public Comercial(){}
    
    public Comercial(int id,String dni, String nombre, String apellidos) {
        this.id = id;
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

    public Object getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
