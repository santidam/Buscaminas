/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.gui;

import com.mycompany.crm.entity.Casilla;
import com.mycompany.crm.entity.Tablero;
import com.mycompany.crm.validator.Validations;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Stack;
import org.openide.util.Exceptions;

public class Jugar extends JPanel {
    private final int filas, columnas, minas;
    private JButton[][] botones;
    private Tablero tablero;
    private JLabel cronometroLabel;
    private Timer timer;
    private int segundos;
    private int contadorBanderas = 0;
    private final int MAX_BANDERAS = 10;
    boolean victoria = true;

    public Jugar(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.tablero = new Tablero(filas, columnas, minas);
        this.botones = new JButton[filas][columnas];
        this.segundos = 0;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Panel del cronómetro
        JPanel panelCronometro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCronometro.setBackground(Color.BLACK);
        
        cronometroLabel = new JLabel("000", JLabel.CENTER);
        cronometroLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        cronometroLabel.setForeground(Color.RED);
        cronometroLabel.setPreferredSize(new Dimension(50, 40));
        panelCronometro.add(cronometroLabel);
        
        add(panelCronometro, BorderLayout.NORTH);

        // Panel para los botones del tablero
        JPanel panelBotones = new JPanel(new GridLayout(filas, columnas));
        add(panelBotones, BorderLayout.CENTER);
        inicializarBotones(panelBotones);

        // Configurar el temporizador
        timer = new Timer(1000, e -> actualizarCronometro());
        timer.start();
    }

    private void actualizarCronometro() {
        segundos++;
        cronometroLabel.setText(String.format("%03d", segundos));
    }

    private void inicializarBotones(JPanel panelBotones) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                final int x = i, y = j;
                botones[i][j].addActionListener(e -> revelarCasilla(x, y));
                botones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            JButton boton = (JButton) e.getSource();
                            if (boton.isEnabled()) {
                                if (boton.getIcon() != null) {
                                    boton.setIcon(null);
                                    contadorBanderas--;
                                } else if (contadorBanderas < MAX_BANDERAS) {
                                    boton.setIcon(new ImageIcon(getClass().getResource("/pikabandera.png")));
                                    contadorBanderas++;
                                }
                            }
                        }
                    }
                });
                panelBotones.add(botones[i][j]);
            }
        }
    }

    private void revelarCasilla(int x, int y) {
        Casilla casilla = tablero.getCasilla(x, y);
        if (casilla.esMina()) {
            botones[x][y].setText("💣");
            botones[x][y].setEnabled(false);
            this.victoria =false;
            mostrarDialogoFinJuego("¡Perdiste!");
        } else {
            int minasCercanas = casilla.getNumeroMinasCercanas();
            if (minasCercanas > 0) {
                botones[x][y].setText(String.valueOf(minasCercanas));
                botones[x][y].setForeground(colorPorNumero(minasCercanas));
                botones[x][y].setEnabled(false);
            } else {
                revelarAdyacentes(x, y);
            }
        }
        
        if (verificarVictoria()) {
            mostrarDialogoFinJuego("¡Ganaste!");
        }
    }

    private Color colorPorNumero(int numero) {
        return switch (numero) {
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN.darker();
            case 3 -> Color.RED;
            case 4 -> Color.MAGENTA;
            case 5 -> Color.ORANGE;
            case 6 -> Color.CYAN;
            case 7 -> Color.BLACK;
            case 8 -> Color.GRAY;
            default -> Color.DARK_GRAY;
        };
    }

    private void revelarAdyacentes(int x, int y) {
        Stack<int[]> pila = new Stack<>();
        pila.push(new int[]{x, y});
        while (!pila.isEmpty()) {
            int[] coordenada = pila.pop();
            int cx = coordenada[0], cy = coordenada[1];
            if (!botones[cx][cy].isEnabled()) continue;
            Casilla casilla = tablero.getCasilla(cx, cy);
            int minasCercanas = casilla.getNumeroMinasCercanas();
            botones[cx][cy].setEnabled(false);
            if (minasCercanas > 0) {
                botones[cx][cy].setText(String.valueOf(minasCercanas));
                botones[cx][cy].setForeground(colorPorNumero(minasCercanas));
            } else {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = cx + dx, ny = cy + dy;
                        if (nx >= 0 && nx < filas && ny >= 0 && ny < columnas && botones[nx][ny].isEnabled()) {
                            pila.push(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }

    private boolean verificarVictoria() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!tablero.getCasilla(i, j).esMina() && botones[i][j].isEnabled()) {
                    return false;
                }
            }
        }
        timer.stop();
        return true;
    }

    private void mostrarDialogoFinJuego(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
        try {
            Validations.getInstance().insertPartida(1, victoria);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        reiniciarJuego();
    }

    private void reiniciarJuego() {
        tablero = new Tablero(filas, columnas, minas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
            }
        }
        segundos = 0;
        cronometroLabel.setText("000");
        timer.restart();
        victoria = true;
    }
}
