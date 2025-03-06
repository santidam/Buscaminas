/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

import java.util.Random;

/**
 *
 * @author admin
 */
public class Tablero {
    private final int filas, columnas, minas;
    private final Casilla[][] casillas;
    private final Random random = new Random();

    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.casillas = new Casilla[filas][columnas];
        inicializarCasillas();
        colocarMinas();
        calcularNumeros();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private void colocarMinas() {
        int colocadas = 0;
        while (colocadas < minas) {
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);
            if (!casillas[x][y].esMina()) {
                casillas[x][y].colocarMina();
                colocadas++;
            }
        }
    }

    private void calcularNumeros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!casillas[i][j].esMina()) {
                    casillas[i][j].setNumeroMinasCercanas(contarMinasAlrededor(i, j));
                }
            }
        }
    }

    private int contarMinasAlrededor(int x, int y) {
        int contador = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < filas && ny >= 0 && ny < columnas && casillas[nx][ny].esMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public Casilla getCasilla(int x, int y) {
        return casillas[x][y];
    }
}
