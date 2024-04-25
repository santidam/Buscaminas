package com.mycompany.crm.entity;

import java.util.Date;
import java.util.HashSet;

public class Hablado extends Accion{

    private HashSet<String> acuerdos;

    public Hablado(Date fecha, Comercial comercial, String descripcion, HashSet<String> acuerdos) {
        super(fecha, comercial, descripcion);
        this.acuerdos = acuerdos;
    }
}
