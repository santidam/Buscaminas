/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;

import com.mycompany.crm.entity.Comercial;

import java.util.Date;

public class Email extends Accion {

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
