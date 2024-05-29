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
}
