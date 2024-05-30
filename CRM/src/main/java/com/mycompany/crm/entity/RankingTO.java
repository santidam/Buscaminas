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
    
    Comercial comercial;
    int accionesTotales;
    int accionLlamada;
    int accionEmail;
    int accionVisita;

    public RankingTO(int accionLlamada, int accionEmail, int accionVisita) {
        this.accionLlamada = accionLlamada;
        this.accionEmail = accionEmail;
        this.accionVisita = accionVisita;
        this.accionesTotales = accionEmail+accionLlamada+accionVisita;
    }

    

    public RankingTO(Comercial comercial, int accionesTotales) {
        this.comercial = comercial;
        this.accionesTotales = accionesTotales;
    
    }
    public int getAccionesTotales(){
        return this.accionesTotales;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public int getAccionLlamada() {
        return accionLlamada;
    }

    public int getAccionEmail() {
        return accionEmail;
    }

    public int getAccionVisita() {
        return accionVisita;
    }

    public void setAccionLlamada(int accionLlamada) {
        this.accionLlamada = accionLlamada;
    }

    public void setAccionEmail(int accionEmail) {
        this.accionEmail = accionEmail;
    }

    public void setAccionVisita(int accionVisita) {
        this.accionVisita = accionVisita;
    }
    
    
    
}
