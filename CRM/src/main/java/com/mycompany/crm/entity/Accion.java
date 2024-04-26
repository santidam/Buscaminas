package com.mycompany.crm.entity;

import java.util.Date;

public class Accion {
    private Date fecha;
    private String descripcion;
    private Comercial comercial;

    public Accion(Date fecha, Comercial comercial, String descripcion) {
        this.fecha = fecha;
        this.comercial = comercial;
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Fecha: " + fecha + "Descripcion: "+ descripcion;
    }
}
