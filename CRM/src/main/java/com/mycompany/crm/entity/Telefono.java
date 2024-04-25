package com.mycompany.crm.entity;

import java.util.Date;
import java.util.HashSet;

public class Telefono extends Hablado{

    private String numTelef;

    public Telefono(Date fecha, Comercial comercial, String descripcion,HashSet<String> acuerdos, String numTelef) {
        super(fecha,comercial, descripcion, acuerdos);
        this.numTelef = numTelef;
    }

    @Override
    public String toString() {
        return "Telefeno: " + numTelef + super.toString();
    }
}
