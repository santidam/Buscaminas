/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;

import java.util.Date;
import java.util.HashSet;

public class Telefono extends Hablado{

    private String numTelef;

    public Telefono(Date fecha, Comercial comercial, String descripcion, String acuerdos, String numTelef) {
        super(fecha,comercial, descripcion, acuerdos);
        this.numTelef = numTelef;
    }

    public String getNumTelef() {
        return numTelef;
    }

    @Override
    public String toString() {
        return "Telefeno: " + numTelef + super.toString();
    }
}
