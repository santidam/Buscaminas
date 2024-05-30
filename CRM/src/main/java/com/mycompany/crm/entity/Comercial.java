package com.mycompany.crm.entity;

import com.mycompany.crm.exceptions.ComandaException;

import java.util.Date;

public class Comercial {

    private String dni;
    private int codigo;
    private String nombre;
    private String apellidos;
    private int porcentajeComision;
    private Date fechaIncorporacion;
    private String contrasenya;


    public Comercial(){}

    public Comercial(String dni, int codigo, String nombre, String apellidos, int porcentajeComision, Date fechaIncorporacion, String contrasenya) {
        this.dni = dni;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.porcentajeComision = porcentajeComision;
        this.fechaIncorporacion = fechaIncorporacion;
        this.contrasenya = contrasenya;
    }
    public Comercial(String dni, String nombre, String apellidos, int porcentajeComision, Date fechaIncorporacion) throws ComandaException {
        if(nombre.length() > 45 || apellidos.length() > 45){
            throw new ComandaException(ComandaException.ERROR_LONGITUD_45);
        }
//        if(porcentajeComision < 0 || porcentajeComision > 10){
//            throw new ComandaException(ComandaException.ERROR_COMISION_FORMATO);
//        }
        if (fechaIncorporacion!=null) {
            if(fechaIncorporacion.before(new Date(1900,1,1))){
                
            throw new ComandaException(ComandaException.ERROR_RANGO_FECHAINCORPORACION);
            }
        
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.porcentajeComision = porcentajeComision;
        this.fechaIncorporacion = fechaIncorporacion;
        this.contrasenya = "1234";
    }

    public Comercial(String dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
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

    public int getCodigo() {
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
