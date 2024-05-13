package com.mycompany.crm.crm.entity;

import java.util.Date;

public class Comercial {

    private String dni;
    private String codigo;
    private String nombre;
    private String apellidos;
    private int porcentajeComision;
    private Date fechaIncorporacion;
    private String contrasenya;


    public Comercial(){}

    public Comercial(String dni, String codigo, String nombre, String apellidos, int porcentajeComision, Date fechaIncorporacion, String contrasenya) {
        this.dni = dni;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.porcentajeComision = porcentajeComision;
        this.fechaIncorporacion = fechaIncorporacion;
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

    public String getCodigo() {
        return codigo;
    }

    public Date getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    @Override
    public String toString() {
        return "DNI: \t" + this.dni + "\n"+
                "CODIGO: \t" + this.codigo + "\n"+
                "NOMBRE: \t" + this.nombre + "\n"+
                "APELLIDOS: \t" + this.apellidos + "\n"+
                "%COMISION: \t" + this.porcentajeComision+"%" + "\n"+
                "INGRESO: \t" + this.fechaIncorporacion + "\n"+
                "------------------------------------------------\n";
    }
}
