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
    int partidasGandas;

    public RankingTO(User user, int partidasGandas) {
        this.user = user;
        this.partidasGandas = partidasGandas;
    }

    public User getUser() {
        return user;
    }

    public int getPartidasGandas() {
        return partidasGandas;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPartidasGandas(int partidasGandas) {
        this.partidasGandas = partidasGandas;
    }
    
}
