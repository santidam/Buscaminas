/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

import com.mycompany.crm.entity.User;

/**
 *
 * @author admin
 */
public class Partida {
    
    private int puntos;
    private boolean victoria;
    private User user;

    public Partida(int puntos, boolean victoria, User user) {
        this.puntos = puntos;
        this.victoria = victoria;
        this.user = user;
    }

    public Partida(int puntuacion, boolean victoria) {
        this.puntos = puntuacion;
        this.victoria = victoria;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public User getUser() {
        return user;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
