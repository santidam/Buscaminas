/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;
/**
 *
 * @author admin
 */
public class Casilla {
    private boolean esMina;
    private int numeroMinasCercanas;

    public Casilla() {
        this.esMina = false;
        this.numeroMinasCercanas = 0;
    }

    public boolean esMina() {
        return esMina;
    }

    public void colocarMina() {
        this.esMina = true;
    }

    public int getNumeroMinasCercanas() {
        return numeroMinasCercanas;
    }

    public void setNumeroMinasCercanas(int numeroMinasCercanas) {
        this.numeroMinasCercanas = numeroMinasCercanas;
    }
}
