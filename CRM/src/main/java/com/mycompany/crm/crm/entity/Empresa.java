package com.mycompany.crm.crm.entity;

import java.util.ArrayList;

public class Empresa {

    private String nombre;
    private String email;
    private String phoneNumber;
    private String representante;
    private String direccion;
    private int cp;
    private String ciudad;
    private String comunidad_autonoma;
    private String codigo;
    private String pagina_web;

    public Empresa(String nombre, String email, String phoneNumber, String representante, String direccion, int cp, String ciudad, String comunidad_autonoma, String codigo, String pagina_web) {
        this.nombre = nombre;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.representante = representante;
        this.direccion = direccion;
        this.cp = cp;
        this.ciudad = ciudad;
        this.comunidad_autonoma = comunidad_autonoma;
        this.codigo = codigo;
        this.pagina_web = pagina_web;
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


    public String getRepresentante() {
        return representante;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCp() {
        return cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getComunidad_autonoma() {
        return comunidad_autonoma;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    @Override
    public String toString() {
        return "TLFNO: \t" + rs.getString("phone_number") + "\n"+
                "CODIGO: \t" + rs.getString("codigo") + "\n"+
                "NOMBRE: \t" + rs.getString("nombre") + "\n"+
                "EMAIL: \t" + rs.getString("email") + "\n"+
                "AGENTE: \t" + rs.getString("representante")+ "\n"+
                "DIRECCION  : \t" + rs.getString("direccion")+ "\n"+
                "CP: \t" + rs.getInt("CP")+ "\n"+
                "CIUDAD: \t" + rs.getString("ciudad")+ "\n"+
                "REGION: \t" + rs.getString("comunidad_autonoma") + "\n"+
                "WEB: \t" + rs.getString("pagina_web")+ "\n";
    }
}
