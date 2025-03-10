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
    User user;
    int partidasGanadas;
    private int partidasJugadas;  // Nuevo campo


    public RankingTO(User user, int partidasGanadas, int partidasJugadas) {
        this.user = user;
        this.partidasGanadas = partidasGanadas;
        this.partidasJugadas = partidasJugadas;
    }
    
     public int getPartidasJugadas() {
        return partidasJugadas;
    }
     public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public User getUser() {
        return user;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPartidasGanadas(int partidasGandas) {
        this.partidasGanadas = partidasGandas;
    }
    
}
