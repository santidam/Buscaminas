package com.mycompany.crm.crm.entity.acciones;

import com.mycompany.crm.crm.entity.Comercial;

import java.util.Date;
import java.util.HashSet;

public class Visita extends Hablado{

    private String direccion;

    public Visita(Date fecha, Comercial comercial, String descripcion, HashSet<String> acuerdos, String direccion) {
        super(fecha, comercial, descripcion, acuerdos);
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Direccion: " + direccion + super.toString();
    }
}
