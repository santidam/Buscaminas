/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.crm.gui;

import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.User;
import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.persistencia.GameDAO;
import com.mycompany.crm.validator.Validations;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
//import org.openide.util.Exceptions;

/**
 *
 * @author admin
 */
public class Ranking2 extends javax.swing.JPanel {

    private DefaultTableModel model ;
    private ArrayList<RankingTO> lista;
    
    public Ranking2() {
        initComponents();
        jPanelDemasJugadores.setBackground(Color.white);
        model = (DefaultTableModel) tabla.getModel();
//        loadData(loadListaRanking());
        
        JFrame frame = new JFrame("Cambiar tamaño del JPanel sin Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Desactivar el layout manager

        // Crear el JPanel
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        
        // Establecer el tamaño con setBounds (especifica x, y, width, height)
        panel.setBounds(50, 50, 300, 150); // Establece el tamaño y la posición

        // Añadir el JPanel al JFrame
        frame.add(panel);

        // Ajustar el tamaño de la ventana
        frame.setSize(400, 400);
//        frame.setVisible(true);

        jPanelFiltros.setVisible(false);
        jPanelPrimero.setVisible(false);
        jPanelSegundo.setVisible(false);
        jPanelTercero.setVisible(false);
        jPanelDemasJugadores.setVisible(false);
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterRanking();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterRanking();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterRanking();
        }
    });
        

        mostrarTopTresRanking();

    }
    
    public ArrayList<RankingTO> loadListaRanking(){
        ArrayList<RankingTO> listaRankingMap = new ArrayList<>();
        try {
            listaRankingMap = Validations.getInstance().valRankingList();
            
        } catch (ComandaException | SQLException ex){
            javax.swing.JOptionPane.showMessageDialog(this, ex ,"ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);

        }
        lista = listaRankingMap;
        return listaRankingMap;
    }
    
    public void mostrarTopTresRanking() {
    GameDAO gameDAO = Validations.getInstance().valGetDAO();
    try {
        ArrayList<RankingTO> rankingList = gameDAO.RankingTO();
        
        // Verifica que hay al menos 3 jugadores en el ranking
        if (rankingList.size() >= 1) {
            RankingTO primerJugador = rankingList.get(0);
            
            jPanelFiltros.setVisible(true);
            jPanelPrimero.setVisible(true);
            
            NombreP.setText(primerJugador.getUser().getNombre());
            NumVictoriasP.setText("Wins: " + String.valueOf(primerJugador.getPartidasGanadas()));
            numPartidasTotP.setText("Total: " + String.valueOf(primerJugador.getPartidasJugadas()) + "");
            
            double winrateP = (primerJugador.getPartidasJugadas() > 0) 
                  ? ((double) primerJugador.getPartidasGanadas() / primerJugador.getPartidasJugadas()) * 100
                  : 0;
    
            PorcVictoriasP.setText("W/r: " + String.format("%.2f%%", winrateP)); // Mostrar con dos decimales
            
            if (rankingList.size() >= 2) { 
                RankingTO segundoJugador = rankingList.get(1);

                jPanelSegundo.setVisible(true);

                nombreS.setText(segundoJugador.getUser().getNombre());
                numVictoriasS.setText("Wins: " + String.valueOf(segundoJugador.getPartidasGanadas()));
                numPartidasTotS.setText("Total: " + String.valueOf(segundoJugador.getPartidasJugadas()));
                
                double winrateS = (primerJugador.getPartidasJugadas() > 0) 
                  ? ((double) segundoJugador.getPartidasGanadas() / segundoJugador.getPartidasJugadas()) * 100
                  : 0;
    
                porcVictoriasS.setText("W/r: " + String.format("%.2f%%", winrateS)); // Mostrar con dos decimales
                
                if (rankingList.size() >= 3) {
                    RankingTO tercerJugador = rankingList.get(2);
                    
                    jPanelTercero.setVisible(true);
                    
                    nombreT.setText(tercerJugador.getUser().getNombre());
                    numVictoriasT.setText("Wins: " + String.valueOf(tercerJugador.getPartidasGanadas()));
                    numPartidasTotT.setText("Total: " + String.valueOf(tercerJugador.getPartidasJugadas()));
                    
                    double winrateT = (primerJugador.getPartidasJugadas() > 0) 
                        ? ((double) tercerJugador.getPartidasGanadas() / tercerJugador.getPartidasJugadas()) * 100
                        : 0;
    
                    porcVictoriasT.setText("W/r: " + String.format("%.2f%%", winrateT)); // Mostrar con dos decimales
                    
                    if (rankingList.size() >= 4) {
                        jPanelDemasJugadores.setVisible(true);
                        
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn(Validations.getInstance().valGetBundle().getString("MSG_NOMBRE"));
                        model.addColumn(Validations.getInstance().valGetBundle().getString("MSG_WINRATE"));
                        model.addColumn(Validations.getInstance().valGetBundle().getString("MSG_VICTORIAS"));
                        model.addColumn(Validations.getInstance().valGetBundle().getString("MSG_PARTIDAS_JUGADAS"));
                        
                        for (int i = 3; i < rankingList.size(); i++) {
                            RankingTO jugador = rankingList.get(i);
                            String nombre = jugador.getUser().getNombre();
                            int victorias = jugador.getPartidasGanadas();
                            int partidasTotales = jugador.getPartidasJugadas();
                            
                            double winrate = (partidasTotales > 0) ? ((double) victorias / partidasTotales * 100) : 0;
                            
                            model.addRow(new Object[]{nombre, String.format("%.2f", winrate), victorias, partidasTotales});
                        }
                        
                        tabla.setModel(model);
                    }
                }
            }
        } else {
            // Manejo en caso de que la lista tenga menos de 3 jugadores
            System.out.println("No hay suficientes jugadores para mostrar el ranking.");
        }
    } catch (SQLException | ComandaException ex) {
        ex.printStackTrace();
    }
}

    
    private void ordenarPorNombre() {
        try {
            GameDAO gameDAO = Validations.getInstance().valGetDAO();
            ArrayList<RankingTO> rankingList = gameDAO.RankingTO();
            Collections.sort(rankingList, Comparator.comparing(r -> r.getUser().getNombre()));
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de actualizar
            
            if (rankingList.size() >= 1){
                RankingTO primerJugador = rankingList.get(0);
                NombreP.setText(primerJugador.getUser().getNombre());
                NumVictoriasP.setText("Wins: " + String.valueOf(primerJugador.getPartidasGanadas()));
                numPartidasTotP.setText("Total: " + String.valueOf(primerJugador.getPartidasJugadas()));

                double winrateP = (primerJugador.getPartidasJugadas() > 0) 
                      ? ((double) primerJugador.getPartidasGanadas() / primerJugador.getPartidasJugadas()) * 100
                      : 0;

                PorcVictoriasP.setText("W/r: " + String.format("%.2f%%", winrateP)); // Mostrar con dos decimales

                if (rankingList.size() >= 2) {
                    RankingTO segundoJugador = rankingList.get(1);

                    nombreS.setText(segundoJugador.getUser().getNombre());
                    numVictoriasS.setText("Wins: " + String.valueOf(segundoJugador.getPartidasGanadas()));
                    numPartidasTotS.setText("Total: " + String.valueOf(segundoJugador.getPartidasJugadas()));

                    double winrateS = (primerJugador.getPartidasJugadas() > 0) 
                      ? ((double) segundoJugador.getPartidasGanadas() / segundoJugador.getPartidasJugadas()) * 100
                      : 0;

                    porcVictoriasS.setText("W/r: " + String.format("%.2f%%", winrateS)); // Mostrar con dos decimales

                    if (rankingList.size() >= 3) {
                        RankingTO tercerJugador = rankingList.get(2);
                    
                        nombreT.setText(tercerJugador.getUser().getNombre());
                        numVictoriasT.setText("Wins: " + String.valueOf(tercerJugador.getPartidasGanadas()));
                        numPartidasTotT.setText("Total: " + String.valueOf(tercerJugador.getPartidasJugadas()));

                        double winrateT = (primerJugador.getPartidasJugadas() > 0) 
                            ? ((double) tercerJugador.getPartidasGanadas() / tercerJugador.getPartidasJugadas()) * 100
                            : 0;

                        porcVictoriasT.setText("W/r: " + String.format("%.2f%%", winrateT)); // Mostrar con dos decimales
                        
                        if (rankingList.size() >= 4) {
                            for (int i = 3; i < rankingList.size(); i++) {
                                RankingTO jugador = rankingList.get(i);
                                double winrate = (jugador.getPartidasJugadas() > 0) ? (jugador.getPartidasGanadas() * 100) / jugador.getPartidasJugadas() : 0;

                                model.addRow(new Object[]{
                                    jugador.getUser().getNombre(),
                                    String.format("%.2f%%", winrate),
                                    jugador.getPartidasGanadas(),
                                    jugador.getPartidasJugadas()
                                });
                            }
                        }
                    }
                }
            }

        } catch (SQLException | ComandaException ex) {
        ex.printStackTrace();
    }
}
public void ordenarPorWinrate() {
    try {
        // Obtener la lista de jugadores desde el DAO (igual que en mostrarTopTresRanking)
        GameDAO gameDAO = Validations.getInstance().valGetDAO();
        ArrayList<RankingTO> rankingList = gameDAO.RankingTO();
        
        // Ordenar la lista por winrate (tasa de victorias)
        Collections.sort(rankingList, new Comparator<RankingTO>() {
            @Override
            public int compare(RankingTO jugador1, RankingTO jugador2) {
                double winrate1 = (jugador1.getPartidasJugadas() > 0) 
                                  ? (double) jugador1.getPartidasGanadas() / jugador1.getPartidasJugadas() 
                                  : 0;
                double winrate2 = (jugador2.getPartidasJugadas() > 0) 
                                  ? (double) jugador2.getPartidasGanadas() / jugador2.getPartidasJugadas() 
                                  : 0;

                return Double.compare(winrate2, winrate1); // Ordenar en orden descendente
            }
        });

        // Actualizar la tabla con la lista ordenada
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de actualizar

        if (rankingList.size() >= 1) {
            RankingTO primerJugador = rankingList.get(0);
            NombreP.setText(primerJugador.getUser().getNombre());
            NumVictoriasP.setText("Wins: " + String.valueOf(primerJugador.getPartidasGanadas()));
            numPartidasTotP.setText("Total: " + String.valueOf(primerJugador.getPartidasJugadas()));

            double winrateP = (primerJugador.getPartidasJugadas() > 0) 
                              ? ((double) primerJugador.getPartidasGanadas() / primerJugador.getPartidasJugadas()) * 100
                              : 0;

            PorcVictoriasP.setText("W/r: " + String.format("%.2f%%", winrateP)); // Mostrar con dos decimales

            if (rankingList.size() >= 2) {
                RankingTO segundoJugador = rankingList.get(1);
                nombreS.setText(segundoJugador.getUser().getNombre());
                numVictoriasS.setText("Wins: " + String.valueOf(segundoJugador.getPartidasGanadas()));
                numPartidasTotS.setText("Total: " + String.valueOf(segundoJugador.getPartidasJugadas()));

                double winrateS = (segundoJugador.getPartidasJugadas() > 0) 
                                  ? ((double) segundoJugador.getPartidasGanadas() / segundoJugador.getPartidasJugadas()) * 100
                                  : 0;

                porcVictoriasS.setText("W/r: " + String.format("%.2f%%", winrateS)); // Mostrar con dos decimales

                if (rankingList.size() >= 3) {
                    RankingTO tercerJugador = rankingList.get(2);
                    nombreT.setText(tercerJugador.getUser().getNombre());
                    numVictoriasT.setText("Wins: " + String.valueOf(tercerJugador.getPartidasGanadas()));
                    numPartidasTotT.setText("Total: " + String.valueOf(tercerJugador.getPartidasJugadas()));

                    double winrateT = (tercerJugador.getPartidasJugadas() > 0) 
                                      ? ((double) tercerJugador.getPartidasGanadas() / tercerJugador.getPartidasJugadas()) * 100
                                      : 0;

                    porcVictoriasT.setText("W/r: " + String.format("%.2f%%", winrateT)); // Mostrar con dos decimales
                    
                    if (rankingList.size() >= 4) {
                        for (int i = 3; i < rankingList.size(); i++) {
                            RankingTO jugador = rankingList.get(i);
                            double winrate = (jugador.getPartidasJugadas() > 0) 
                                             ? (double) jugador.getPartidasGanadas() / jugador.getPartidasJugadas() * 100
                                             : 0;

                            model.addRow(new Object[]{
                                jugador.getUser().getNombre(),
                                String.format("%.2f%%", winrate),
                                jugador.getPartidasGanadas(),
                                jugador.getPartidasJugadas()
                            });
                        }
                    }
                }
            }
        }
    } catch (SQLException | ComandaException ex) {
        ex.printStackTrace();
    }
}

    
    public void ordenarPorVictoriasTotales() {
    try {
        // Obtener la lista de jugadores desde el DAO (igual que en los otros métodos)
        GameDAO gameDAO = Validations.getInstance().valGetDAO();
        ArrayList<RankingTO> rankingList = gameDAO.RankingTO();
        
        // Ordenar la lista por victorias totales (partidas ganadas)
        Collections.sort(rankingList, new Comparator<RankingTO>() {
            @Override
            public int compare(RankingTO jugador1, RankingTO jugador2) {
                return Integer.compare(jugador2.getPartidasGanadas(), jugador1.getPartidasGanadas()); // Ordenar en orden descendente
            }
        });

        // Actualizar la tabla con la lista ordenada
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de actualizar

        if (rankingList.size() >= 1) {
            RankingTO primerJugador = rankingList.get(0);
            NombreP.setText(primerJugador.getUser().getNombre());
            NumVictoriasP.setText("Wins: " + String.valueOf(primerJugador.getPartidasGanadas()));
            numPartidasTotP.setText("Total: " + String.valueOf(primerJugador.getPartidasJugadas()));

            double winrateP = (primerJugador.getPartidasJugadas() > 0) 
                              ? ((double) primerJugador.getPartidasGanadas() / primerJugador.getPartidasJugadas()) * 100
                              : 0;

            PorcVictoriasP.setText("W/r: " + String.format("%.2f%%", winrateP)); // Mostrar con dos decimales

            if (rankingList.size() >= 2) {
                RankingTO segundoJugador = rankingList.get(1);
                nombreS.setText(segundoJugador.getUser().getNombre());
                numVictoriasS.setText("Wins: " + String.valueOf(segundoJugador.getPartidasGanadas()));
                numPartidasTotS.setText("Total: " + String.valueOf(segundoJugador.getPartidasJugadas()));

                double winrateS = (segundoJugador.getPartidasJugadas() > 0) 
                                  ? ((double) segundoJugador.getPartidasGanadas() / segundoJugador.getPartidasJugadas()) * 100
                                  : 0;

                porcVictoriasS.setText("W/r: " + String.format("%.2f%%", winrateS)); // Mostrar con dos decimales

                if (rankingList.size() >= 3) {
                    RankingTO tercerJugador = rankingList.get(2);
                    nombreT.setText(tercerJugador.getUser().getNombre());
                    numVictoriasT.setText("Wins: " + String.valueOf(tercerJugador.getPartidasGanadas()));
                    numPartidasTotT.setText("Total: " + String.valueOf(tercerJugador.getPartidasJugadas()));

                    double winrateT = (tercerJugador.getPartidasJugadas() > 0) 
                                      ? ((double) tercerJugador.getPartidasGanadas() / tercerJugador.getPartidasJugadas()) * 100
                                      : 0;

                    porcVictoriasT.setText("W/r: " + String.format("%.2f%%", winrateT)); // Mostrar con dos decimales
                    
                    if (rankingList.size() >= 4) {
                        for (int i = 3; i < rankingList.size(); i++) {
                            RankingTO jugador = rankingList.get(i);
                            double winrate = (jugador.getPartidasJugadas() > 0) 
                                             ? (double) jugador.getPartidasGanadas() / jugador.getPartidasJugadas() * 100
                                             : 0;

                            model.addRow(new Object[]{
                                jugador.getUser().getNombre(),
                                String.format("%.2f%%", winrate),
                                jugador.getPartidasGanadas(),
                                jugador.getPartidasJugadas()
                            });
                        }
                    }
                }
            }
        }
    } catch (SQLException | ComandaException ex) {
        ex.printStackTrace();
    }
}
    
public void filterRanking() {
    try {
        // Obtener la lista completa desde el DAO
        GameDAO gameDAO = Validations.getInstance().valGetDAO();
        ArrayList<RankingTO> fullList = gameDAO.RankingTO();
        String filterText = jTextField1.getText().toLowerCase().trim();
        
        // Crear una lista filtrada que contenga solo los jugadores cuyo nombre 
        // empieza por el texto buscado
        ArrayList<RankingTO> filteredList = new ArrayList<>();
        for (RankingTO jugador : fullList) {
            if (jugador.getUser().getNombre().toLowerCase().startsWith(filterText)) {
                filteredList.add(jugador);
            }
        }
        
        // Si hay más de un jugador filtrado, ordenarlos por nombre
        if (filteredList.size() > 1) {
            Collections.sort(filteredList, Comparator.comparing(r -> r.getUser().getNombre().toLowerCase()));
        }
        
        // Actualizar los jPanels según la cantidad de jugadores filtrados
        
        // Primer jugador:
        if (filteredList.size() >= 1) {
            RankingTO primerJugador = filteredList.get(0);
            NombreP.setText(primerJugador.getUser().getNombre());
            NumVictoriasP.setText("Wins: " + String.valueOf(primerJugador.getPartidasGanadas()));
            numPartidasTotP.setText("Total: " + String.valueOf(primerJugador.getPartidasJugadas()));
            double winrateP = (primerJugador.getPartidasJugadas() > 0)
                    ? ((double) primerJugador.getPartidasGanadas() / primerJugador.getPartidasJugadas()) * 100
                    : 0;
            PorcVictoriasP.setText("W/r: " + String.format("%.2f%%", winrateP));
            jPanelPrimero.setVisible(true);
            Medalla1.setVisible(true);
        } else {
            jPanelPrimero.setVisible(false);
            Medalla1.setVisible(false);
        }
        
        // Segundo jugador:
        if (filteredList.size() >= 2) {
            RankingTO segundoJugador = filteredList.get(1);
            nombreS.setText(segundoJugador.getUser().getNombre());
            numVictoriasS.setText("Wins: " + String.valueOf(segundoJugador.getPartidasGanadas()));
            numPartidasTotS.setText("Total: " + String.valueOf(segundoJugador.getPartidasJugadas()));
            double winrateS = (segundoJugador.getPartidasJugadas() > 0)
                    ? ((double) segundoJugador.getPartidasGanadas() / segundoJugador.getPartidasJugadas()) * 100
                    : 0;
            porcVictoriasS.setText("W/r: " + String.format("%.2f%%", winrateS));
            jPanelSegundo.setVisible(true);
            medalla2.setVisible(true);
        } else {
            jPanelSegundo.setVisible(false);
            medalla2.setVisible(false);
        }
        
        // Tercer jugador:
        if (filteredList.size() >= 3) {
            RankingTO tercerJugador = filteredList.get(2);
            nombreT.setText(tercerJugador.getUser().getNombre());
            numVictoriasT.setText("Wins: " + String.valueOf(tercerJugador.getPartidasGanadas()));
            numPartidasTotT.setText("Total: " + String.valueOf(tercerJugador.getPartidasJugadas()));
            double winrateT = (tercerJugador.getPartidasJugadas() > 0)
                    ? ((double) tercerJugador.getPartidasGanadas() / tercerJugador.getPartidasJugadas()) * 100
                    : 0;
            porcVictoriasT.setText("W/r: " + String.format("%.2f%%", winrateT));
            jPanelTercero.setVisible(true);
            medalla3.setVisible(true);
        } else {
            jPanelTercero.setVisible(false);
            medalla3.setVisible(false);
        }
        
        // Actualizar la tabla para los jugadores desde el cuarto en adelante
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla
        if (filteredList.size() > 3) {
            for (int i = 3; i < filteredList.size(); i++) {
                RankingTO jugador = filteredList.get(i);
                double winrate = (jugador.getPartidasJugadas() > 0)
                        ? ((double) jugador.getPartidasGanadas() / jugador.getPartidasJugadas()) * 100
                        : 0;
                model.addRow(new Object[]{
                    jugador.getUser().getNombre(),
                    String.format("%.2f%%", winrate),
                    jugador.getPartidasGanadas(),
                    jugador.getPartidasJugadas()
                });
            }
            jPanelDemasJugadores.setVisible(true);
        } else {
            jPanelDemasJugadores.setVisible(false);
        }
    } catch (SQLException | ComandaException ex) {
        ex.printStackTrace();
    }
}






   
   
    
    public void cambiarDialog(Dialog a){
        a.setSize(650, 473);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        Titulo = new javax.swing.JLabel();
        jPanelPrimero = new javax.swing.JPanel();
        NombreP = new javax.swing.JLabel();
        PorcVictoriasP = new javax.swing.JLabel();
        NumVictoriasP = new javax.swing.JLabel();
        numPartidasTotP = new javax.swing.JLabel();
        jPanelSegundo = new javax.swing.JPanel();
        nombreS = new javax.swing.JLabel();
        porcVictoriasS = new javax.swing.JLabel();
        numVictoriasS = new javax.swing.JLabel();
        numPartidasTotS = new javax.swing.JLabel();
        jPanelTercero = new javax.swing.JPanel();
        nombreT = new javax.swing.JLabel();
        porcVictoriasT = new javax.swing.JLabel();
        numVictoriasT = new javax.swing.JLabel();
        numPartidasTotT = new javax.swing.JLabel();
        jPanelDemasJugadores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Medalla1 = new javax.swing.JLabel();
        medalla2 = new javax.swing.JLabel();
        medalla3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelFiltros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreFiltro = new javax.swing.JButton();
        winrateFiltro = new javax.swing.JButton();
        numVictoriasFiltro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(650, 473));
        setMinimumSize(new java.awt.Dimension(650, 473));
        setPreferredSize(new java.awt.Dimension(630, 460));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Titulo.setText("RANKING");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        jPanelPrimero.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrimero.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanelPrimero.setAlignmentY(0.0F);
        jPanelPrimero.setMaximumSize(new java.awt.Dimension(500, 300));
        jPanelPrimero.setMinimumSize(new java.awt.Dimension(500, 300));
        jPanelPrimero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreP.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        NombreP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NombreP.setText("ShadowNinja");
        jPanelPrimero.add(NombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 50));

        PorcVictoriasP.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        PorcVictoriasP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PorcVictoriasP.setText("75,33%");
        jPanelPrimero.add(PorcVictoriasP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 110, 50));

        NumVictoriasP.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        NumVictoriasP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumVictoriasP.setText("100");
        jPanelPrimero.add(NumVictoriasP, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 110, 50));

        numPartidasTotP.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        numPartidasTotP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numPartidasTotP.setText("145");
        jPanelPrimero.add(numPartidasTotP, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 110, 50));

        add(jPanelPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 550, 50));

        jPanelSegundo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSegundo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSegundo.setAlignmentX(0.0F);
        jPanelSegundo.setAlignmentY(0.0F);
        jPanelSegundo.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanelSegundo.setMinimumSize(new java.awt.Dimension(500, 500));
        jPanelSegundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombreS.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        nombreS.setText("nombre");
        jPanelSegundo.add(nombreS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 45));

        porcVictoriasS.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        porcVictoriasS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        porcVictoriasS.setText("winrate");
        jPanelSegundo.add(porcVictoriasS, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 100, 45));

        numVictoriasS.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        numVictoriasS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numVictoriasS.setText("num victorias");
        jPanelSegundo.add(numVictoriasS, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 100, 45));

        numPartidasTotS.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        numPartidasTotS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numPartidasTotS.setText("num partidas");
        jPanelSegundo.add(numPartidasTotS, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 100, 45));

        add(jPanelSegundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 510, 45));

        jPanelTercero.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTercero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelTercero.setAlignmentX(0.0F);
        jPanelTercero.setAlignmentY(0.0F);
        jPanelTercero.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanelTercero.setMinimumSize(new java.awt.Dimension(500, 500));
        jPanelTercero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombreT.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        nombreT.setText("nombre");
        jPanelTercero.add(nombreT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        porcVictoriasT.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        porcVictoriasT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        porcVictoriasT.setText("winrate");
        jPanelTercero.add(porcVictoriasT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 90, 40));

        numVictoriasT.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        numVictoriasT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numVictoriasT.setText("num victorias");
        jPanelTercero.add(numVictoriasT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 90, 40));

        numPartidasTotT.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        numPartidasTotT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numPartidasTotT.setText("num partidas");
        jPanelTercero.add(numPartidasTotT, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 90, 40));

        add(jPanelTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 450, 40));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Victorias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jPanelDemasJugadoresLayout = new javax.swing.GroupLayout(jPanelDemasJugadores);
        jPanelDemasJugadores.setLayout(jPanelDemasJugadoresLayout);
        jPanelDemasJugadoresLayout.setHorizontalGroup(
            jPanelDemasJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDemasJugadoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanelDemasJugadoresLayout.setVerticalGroup(
            jPanelDemasJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDemasJugadoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanelDemasJugadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 490, -1));

        Medalla1.setBackground(new java.awt.Color(255, 255, 255));
        Medalla1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Medalla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/top1.png"))); // NOI18N
        Medalla1.setToolTipText("");
        Medalla1.setIconTextGap(0);
        Medalla1.setMaximumSize(new java.awt.Dimension(50, 50));
        Medalla1.setMinimumSize(new java.awt.Dimension(50, 50));
        Medalla1.setPreferredSize(new java.awt.Dimension(50, 50));
        add(Medalla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        medalla2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/top2.png"))); // NOI18N
        medalla2.setText("jLabel2");
        add(medalla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 45, -1));

        medalla3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/top3.png"))); // NOI18N
        medalla3.setText("jLabel2");
        add(medalla3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 40, -1));

        jPanel1.setBackground(new java.awt.Color(75, 190, 255));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 11)); // NOI18N
        jLabel1.setText(Validations.getInstance().valGetBundle().getString("MSG_ORDENAR_POR"));

        nombreFiltro.setFont(new java.awt.Font("Gadugi", 0, 11)); // NOI18N
        nombreFiltro.setText(Validations.getInstance().valGetBundle().getString("MSG_NOMBRE"));
        nombreFiltro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombreFiltro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        nombreFiltro.setIconTextGap(0);
        nombreFiltro.setMargin(new java.awt.Insets(2, 5, 3, 5));
        nombreFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFiltroActionPerformed(evt);
            }
        });

        winrateFiltro.setFont(new java.awt.Font("Gadugi", 0, 11)); // NOI18N
        winrateFiltro.setText(Validations.getInstance().valGetBundle().getString("MSG_PORCENTAJE_VICTORIAS"));
        winrateFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                winrateFiltroActionPerformed(evt);
            }
        });

        numVictoriasFiltro.setFont(new java.awt.Font("Gadugi", 0, 11)); // NOI18N
        numVictoriasFiltro.setText(Validations.getInstance().valGetBundle().getString("MSG_NUMERO_VICTORIAS"));
        numVictoriasFiltro.setIconTextGap(0);
        numVictoriasFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numVictoriasFiltroActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Gadugi", 1, 11)); // NOI18N
        jLabel2.setText(Validations.getInstance().valGetBundle().getString("MSG_FILTRAR_NOMBRE"));

        jTextField1.setFont(new java.awt.Font("Gadugi", 0, 11)); // NOI18N
        jTextField1.setToolTipText("Nombre del jugador");
        jTextField1.setMargin(new java.awt.Insets(1, 5, 1, 5));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFiltrosLayout = new javax.swing.GroupLayout(jPanelFiltros);
        jPanelFiltros.setLayout(jPanelFiltrosLayout);
        jPanelFiltrosLayout.setHorizontalGroup(
            jPanelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(nombreFiltro)
                .addGap(6, 6, 6)
                .addComponent(winrateFiltro)
                .addGap(6, 6, 6)
                .addComponent(numVictoriasFiltro)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelFiltrosLayout.setVerticalGroup(
            jPanelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1))
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(nombreFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(winrateFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(numVictoriasFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFiltros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 70, Short.MAX_VALUE)
                .addComponent(jPanelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        
    }//GEN-LAST:event_tablaMouseClicked

    private void numVictoriasFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numVictoriasFiltroActionPerformed
        numVictoriasFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorVictoriasTotales();
            }
        });
    }//GEN-LAST:event_numVictoriasFiltroActionPerformed

    private void winrateFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_winrateFiltroActionPerformed
        winrateFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorWinrate();
            }
        });
    }//GEN-LAST:event_winrateFiltroActionPerformed

    private void nombreFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFiltroActionPerformed
        nombreFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar la tabla con los datos ordenados
                ordenarPorNombre();
            }
        });
    }//GEN-LAST:event_nombreFiltroActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Medalla1;
    private javax.swing.JLabel NombreP;
    private javax.swing.JLabel NumVictoriasP;
    private javax.swing.JLabel PorcVictoriasP;
    private javax.swing.JLabel Titulo;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDemasJugadores;
    private javax.swing.JPanel jPanelFiltros;
    private javax.swing.JPanel jPanelPrimero;
    private javax.swing.JPanel jPanelSegundo;
    private javax.swing.JPanel jPanelTercero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel medalla2;
    private javax.swing.JLabel medalla3;
    private javax.swing.JButton nombreFiltro;
    private javax.swing.JLabel nombreS;
    private javax.swing.JLabel nombreT;
    private javax.swing.JLabel numPartidasTotP;
    private javax.swing.JLabel numPartidasTotS;
    private javax.swing.JLabel numPartidasTotT;
    private javax.swing.JButton numVictoriasFiltro;
    private javax.swing.JLabel numVictoriasS;
    private javax.swing.JLabel numVictoriasT;
    private javax.swing.JLabel porcVictoriasS;
    private javax.swing.JLabel porcVictoriasT;
    private javax.swing.JTable tabla;
    private javax.swing.JButton winrateFiltro;
    // End of variables declaration//GEN-END:variables
}
