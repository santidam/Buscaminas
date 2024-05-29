/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;

import java.util.Date;
import java.util.HashSet;

public class Hablado extends Accion {

    private String acuerdos;

    public Hablado(Date fecha, Comercial comercial, String descripcion, String acuerdos) {
        super(fecha, comercial, descripcion);
        this.acuerdos = acuerdos;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\tAcuerdos: " +this.acuerdos;
    }
}
