/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.validator;



import com.mycompany.crm.controller.Gestor;
import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.User;
import com.mycompany.crm.entity.Partida;

import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.utils.CastData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author admin
 */
public class Validations {

    private Gestor gestor = new Gestor();
    private static Validations v;
    private Validations() {
        
    }
    public static Validations getInstance() {
        if (v == null) {
            v = new Validations();
        }
        return v;
    }
    
    
    //LOGIN
    public boolean valLogin(String dni, String password) throws ComandaException, SQLException{     
        try{
            return gestor.login(dni,password);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new ComandaException(gestor.getBundle().getString("ERROR_SQL"));
        }
        

    }
    
    //REGISTRAR
    
      public void valRegistrarUser(String nombreString, String passwordString) throws ComandaException, SQLException {
          gestor.altaUser(nombreString, passwordString);
    }

   



    
    //BAJAS
    
    public void valBajaUser(String nombre) throws ComandaException{
        try{
            gestor.bajaUser(nombre);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new ComandaException(gestor.getBundle().getString("ERROR_BAJA_USUARIO"));
        }

    }
    
    //GUARDAR PARTIDAS
    
    public void insertPartida(int puntuacion, boolean victoria) throws SQLException{
        gestor.insertGame(new Partida(puntuacion, victoria));
    }
    
    //RANKING
    
     public ArrayList<RankingTO> valRankingList() throws ComandaException, SQLException{
      return gestor.mostrarRanking();
    };
     
     


    public void valSetBundle(String idioma) {
        gestor.setLocale(idioma);
    }
    public ResourceBundle valGetBundle() {
        return gestor.getBundle();
    }   
}
