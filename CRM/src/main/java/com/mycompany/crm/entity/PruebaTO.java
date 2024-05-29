/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

/**
 *
 * @author admin
 */
public class PruebaTO {
    
    String nombre;
    String apellidos;
    int pendientes;
    int completadas;

    public PruebaTO(String nombre, String apellidos, int pendientes, int completadas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pendientes = pendientes;
        this.completadas = completadas;
    }
    public int getTotal(){
        return pendientes+completadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getPendientes() {
        return pendientes;
    }

    public int getCompletadas() {
        return completadas;
    }
    
    
}
