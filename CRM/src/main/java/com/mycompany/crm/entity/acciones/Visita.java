/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.exceptions.ComandaException;

import java.util.Date;
import java.util.HashSet;

public class Visita extends Hablado{

    private String direccion;

    public Visita(Date fecha, Comercial comercial, String descripcion, String acuerdos, String direccion) throws ComandaException {
        super(fecha, comercial, descripcion, acuerdos);
        if(direccion.length() > 60){
            throw new ComandaException(ComandaException.ERROR_LONGITUD_60);
        }
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\tDireccion: " + direccion;
    }
}
