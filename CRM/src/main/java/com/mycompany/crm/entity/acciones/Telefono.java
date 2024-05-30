/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.exceptions.ComandaException;

import java.util.Date;
import java.util.HashSet;

public class Telefono extends Hablado{

    private String numTelef;

    public Telefono(Date fecha, Comercial comercial, String descripcion, String acuerdos, String numTelef) throws ComandaException {
        super(fecha,comercial, descripcion, acuerdos);
        if(numTelef.length() > 12){
            throw new ComandaException(ComandaException.ERROR_LONGITUD_12);
        }
        this.numTelef = numTelef;
    }

    public String getNumTelef() {
        return numTelef;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\tTelefeno: " + numTelef;
    }
}
