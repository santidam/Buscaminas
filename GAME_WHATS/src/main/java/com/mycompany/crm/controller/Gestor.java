package com.mycompany.crm.controller;


import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.User;
import com.mycompany.crm.entity.Partida;


import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.persistencia.GameDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class Gestor {

    private User user;
    private GameDAO gameDAO;
    private Locale locale;
    
    public Gestor(){
        this.locale = Locale.getDefault();
       
        gameDAO = new GameDAO(this);
    }
    
    public ResourceBundle getBundle(){
        return ResourceBundle.getBundle(
            "org.netbeans.validation.api.builtin.stringvalidation.Bundle",
            locale.getDefault());
    }
    public void setLocale(String idioma){
        Locale.setDefault(new Locale(idioma));
    
    }
    
    public boolean login(String name, String passwd) throws ComandaException, SQLException{
        User u1 = gameDAO.getUserByUsername(name); // Metodo user
        if (u1==null) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(getBundle().getString("ERROR_USUARIO_INEXISTENTE"));
        }else{
             if (!u1.getContrasenya().equals(passwd)) {
                 throw new ComandaException(getBundle().getString("ERROR_CONTRASENA_INCORRECTA"));
             }
             this.user = u1;
             return true;
        }
    }

    //REGISTRAR
    public void altaUser(String nombre, String password) throws ComandaException, SQLException{
        User user = new User(password, nombre);
        gameDAO.insertarUsuario(user);
    }
    
    public void insertGame(Partida partida)throws SQLException{
        partida.setUser(this.user);
        gameDAO.insertarPartida(partida);
    }



    //BAJA
 
    public void bajaUser(String nombreString) throws ComandaException, SQLException {
        gameDAO.deleteUser(nombreString);

    }
    
    //RANKING
    
    public ArrayList<RankingTO> mostrarRanking() throws SQLException, ComandaException{
        return gameDAO.RankingTO();
    }




}


