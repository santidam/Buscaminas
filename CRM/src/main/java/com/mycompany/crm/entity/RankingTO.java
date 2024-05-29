/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

/**
 *
 * @author admin
 */
public class RankingTO {
    
    String nombre;
    String dni;
    int accionesTotales;

    public RankingTO(String nombre, String dni, int accionesTotales) {
        this.nombre = nombre;
        this.dni = dni;
        this.accionesTotales = accionesTotales;
    }
    public int getAccionesTotales(){
        return this.accionesTotales;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }


    
}
