/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.exceptions.ComandaException;

import java.util.Date;
import java.util.HashSet;

public class Hablado extends Accion {

    private String acuerdos;

    public Hablado(Date fecha, Comercial comercial, String descripcion, String acuerdos) throws ComandaException {
        super(fecha, comercial, descripcion);
        if(acuerdos.length() > 255){
            throw new ComandaException(ComandaException.ERROR_LONGITUD_255);
        }
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
