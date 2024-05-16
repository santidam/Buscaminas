package com.mycompany.crm.entity;

import java.util.ArrayList;

public class Empresa {
    
    private int codigo;
    private String direccion;
    private String cp;
    private String ciudad;
    private String comunidad;
    private String paginaWeb;
    private String nombre;
    private String email;
    private String phoneNumber;
    private String contacto;
    private Comercial comercial; // Eliminar ahora empresas tienen varios empesas

    private ArrayList<Accion> acciones;


    public Empresa(int codigo,String nombre, String email, String phoneNumber, String contacto) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.contacto = contacto;
        this.comercial = new Comercial();
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
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
