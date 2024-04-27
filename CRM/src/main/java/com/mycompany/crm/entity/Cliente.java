package com.mycompany.crm.entity;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private String email;
    private String phoneNumber;
    private String contacto;
    private Comercial comercial;

    private ArrayList<Accion> acciones;


    public Cliente(String nombre, String email, String phoneNumber, String contacto) {

        this.nombre = nombre;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.contacto = contacto;
        this.comercial = new Comercial();
    }

    public void asignarComercial(Comercial comercial){
        this.comercial = comercial;
    }

    public void guardarAccion(Accion accion){
        this.acciones.add(accion);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContacto() {
        return contacto;
    }
    

    @Override
    public String toString() {

        return  nombre + ';' +
                email + ';' +
                phoneNumber+ ";"+
                contacto;


    }
}
