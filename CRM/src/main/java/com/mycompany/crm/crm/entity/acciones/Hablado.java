package com.mycompany.crm.crm.entity.acciones;

import com.mycompany.crm.crm.entity.Comercial;
import com.mycompany.crm.crm.entity.acciones.Accion;

import java.util.Date;
import java.util.HashSet;

public class Hablado extends Accion {

    private HashSet<String> acuerdos;

    public Hablado(Date fecha, Comercial comercial, String descripcion, HashSet<String> acuerdos) {
        super(fecha, comercial, descripcion);
        this.acuerdos = acuerdos;
    }
}
