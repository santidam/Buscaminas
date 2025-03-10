/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

/**
 *
 * @author admin
 */
public class RankingTO2 {
    private User user;
    private int partidasGanadas;
    private int partidasJugadas;  // Nuevo campo

    public RankingTO2(User user, int partidasGanadas, int partidasJugadas) {
        this.user = user;
        this.partidasGanadas = partidasGanadas;
        this.partidasJugadas = partidasJugadas;
    }

    public User getUser() {
        return user;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
}

