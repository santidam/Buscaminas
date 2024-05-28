/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;

import java.util.Date;
import java.util.HashSet;

public class Visita extends Hablado{

    private String direccion;

    public Visita(Date fecha, Comercial comercial, String descripcion, String acuerdos, String direccion) {
        super(fecha, comercial, descripcion, acuerdos);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Direccion: " + direccion + super.toString();
    }
}
