/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;


import com.mycompany.crm.entity.Comercial;

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

    public Accion(Date fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Fecha: " + fecha + "Descripcion: "+ descripcion;
    }
}