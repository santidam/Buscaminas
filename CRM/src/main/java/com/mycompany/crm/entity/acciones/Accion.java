/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;


import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.Empresa;

import java.util.Date;

public class Accion {
    private Date fecha;
    private String descripcion;
    private Comercial comercial;
    private Empresa empresa;

    public Accion(Date fecha, Comercial comercial, String descripcion) {
        this.fecha = fecha;
        this.comercial = comercial;
        this.descripcion = descripcion;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
    


    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Comercial getComercial() {
        return comercial;
    }


    @Override
    public String toString() {
        return "Fecha: " + fecha + "\tDescripcion: "+ descripcion;
    }
}