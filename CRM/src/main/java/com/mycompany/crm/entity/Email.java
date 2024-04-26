package com.mycompany.crm.entity;

import java.util.Date;

public class Email extends Accion{

    private String email;
    private boolean esPromocion;

    public Email(Date fecha, Comercial comercial, String descripcion, String email, boolean esPromocion) {
        super(fecha, comercial, descripcion);
        this.email = email;
        this.esPromocion = esPromocion;
    }

    @Override
    public String toString() {
        return "Email: " + email + super.toString();
    }
}

