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

public class Jugar3 extends JPanel {
    private final int filas, columnas, minas;
    private JButton[][] botones;
    private Tablero tablero;
    private JLabel cronometroLabel;
    private JLabel banderaLabel;
    private JLabel bombaLabel;
    private Timer timer;
    private int segundos;
    private int contadorBanderas = 0;
    private final int MAX_BANDERAS = 10;
    boolean victoria = true;
    private int totalBombas = 10;

    public Jugar3(int filas, int columnas, int minas) {
    this.filas = filas;
    this.columnas = columnas;
    this.minas = minas;
    this.tablero = new Tablero(filas, columnas, minas);
    this.botones = new JButton[filas][columnas];
    this.segundos = 0;

    setLayout(new BorderLayout());

    // Panel superior con BorderLayout para manejar los elementos alineados
    JPanel panelCronometro = new JPanel(new BorderLayout());

    // Cargar imágenes
    ImageIcon bombaIcon = new ImageIcon(getClass().getResource("/images/bomba.png"));
    ImageIcon banderaIcon = new ImageIcon(getClass().getResource("/images/bandera.png"));
    ImageIcon relojIcon = new ImageIcon(getClass().getResource("/images/reloj.png"));

    // Redimensionar imágenes
    bombaIcon = new ImageIcon(bombaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    banderaIcon = new ImageIcon(banderaIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    relojIcon = new ImageIcon(relojIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

    // Crear etiquetas con iconos y valores
    bombaLabel = new JLabel("" + totalBombas + "\t", bombaIcon, JLabel.LEFT);
    banderaLabel = new JLabel("" + contadorBanderas, banderaIcon, JLabel.LEFT);
    cronometroLabel = new JLabel(" 000", relojIcon, JLabel.CENTER);

    // Configuración de fuente y alineación del texto
    Font font = new Font("Courier New", Font.BOLD, 20);
    bombaLabel.setFont(font);
    banderaLabel.setFont(font);
    cronometroLabel.setFont(font);

    // Ajustar alineación del texto a la izquierda de los iconos
    bombaLabel.setHorizontalTextPosition(JLabel.RIGHT);
    banderaLabel.setHorizontalTextPosition(JLabel.RIGHT);
    banderaLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    cronometroLabel.setHorizontalTextPosition(JLabel.RIGHT);
    cronometroLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 150)); // margen: top, left, bottom, right
    

    // Crear un panel para los elementos alineados a la izquierda
    JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelIzquierda.add(bombaLabel);
    panelIzquierda.add(banderaLabel);

    // Crear un panel para el cronómetro centrado
    JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelCentro.add(cronometroLabel);

    // Agregar los paneles al panel superior con BorderLayout
    panelCronometro.add(panelIzquierda, BorderLayout.WEST);  // Bombas y banderas a la izquierda
    panelCronometro.add(panelCentro, BorderLayout.CENTER);   // Cronómetro al centro

    // Agregar el panel superior al JPanel principal
    add(panelCronometro, BorderLayout.NORTH);

    // Panel para los botones del tablero
    JPanel panelBotones = new JPanel(new GridLayout(filas, columnas));
    panelBotones.setPreferredSize(new Dimension(50 * columnas, 50 * filas)); // Ajustar tamaño
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
                            // Ruta de la imagen de la bandera (ajustar la ruta según sea necesario)
                            ImageIcon banderaIcon = new ImageIcon(getClass().getResource("/images/bandera.png"));
                            banderaIcon = new ImageIcon(banderaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

                            
                            if (boton.getIcon() != null) {
                                // Si ya tiene la bandera, la eliminamos
                                boton.setIcon(null);
                                contadorBanderas--;
                                
                            } else if (contadorBanderas < MAX_BANDERAS) {
                                // Si hay espacio para más banderas, la colocamos
                                boton.setIcon(banderaIcon);
                                contadorBanderas++;
                              
                            }
                        }
                    }
                    banderaLabel.setText("" + contadorBanderas);

                            // Si lo deseas, también puedes actualizar las bombas restantes (opcional)
                    bombaLabel.setText("" + (totalBombas - contadorBanderas));
                    
                }
            });
            panelBotones.add(botones[i][j]);
        }
    }
}


    private void revelarCasilla(int x, int y) {
        Casilla casilla = tablero.getCasilla(x, y);
        
        if (botones[x][y].getIcon() != null) {
        botones[x][y].setIcon(null);  // Eliminar el icono de la bandera
        contadorBanderas--;           // Reducir el contador de banderas
        banderaLabel.setText("" + contadorBanderas);  // Actualizar la etiqueta de banderas
        bombaLabel.setText("" + (totalBombas - contadorBanderas));  // Actualizar bombas restantes
    }
        
        if (casilla.esMina()) {
             ImageIcon minaIcon = new ImageIcon(getClass().getResource("/images/bomba.png"));
             
             Image imagen = minaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            minaIcon = new ImageIcon(imagen);
        botones[x][y].setIcon(minaIcon);
            botones[x][y].setEnabled(false);
            this.victoria =false;
            mostrarDialogoFinJuego(Validations.getInstance().valGetBundle().getString("MSG_HAS_PERDIDO"));
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
            mostrarDialogoFinJuego(Validations.getInstance().valGetBundle().getString("MSG_HAS_GANADO"));
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
            
            if (botones[cx][cy].getIcon() != null) {
            botones[cx][cy].setIcon(null);  // Eliminar bandera
            contadorBanderas--;  // Reducir el contador de banderas
            banderaLabel.setText("" + contadorBanderas);  // Actualizar la etiqueta de banderas
            bombaLabel.setText("" + (totalBombas - contadorBanderas));  // Actualizar bombas restantes
        }
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
        contadorBanderas = 0;
        tablero = new Tablero(filas, columnas, minas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
                botones[i][j].setIcon(null);
            }
        }
        segundos = 0;
        cronometroLabel.setText("000");
        banderaLabel.setText("" + contadorBanderas);
        bombaLabel.setText("" + totalBombas);
        timer.restart();
        victoria = true;
    }
}
