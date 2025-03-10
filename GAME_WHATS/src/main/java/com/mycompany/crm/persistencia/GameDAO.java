package com.mycompany.crm.persistencia;


import com.mycompany.crm.controller.Gestor;
import com.mycompany.crm.entity.Partida;
import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.RankingTO2;
import com.mycompany.crm.entity.User;

import com.mycompany.crm.exceptions.ComandaException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GameDAO {

    //BUSCAR
   
    private Gestor gestor;
    public GameDAO(Gestor gestor){
        this.gestor = gestor;
    }


    //INSERTAR/REGISTRAR
    

   public boolean insertarUsuario(User user) throws SQLException, ComandaException {
        if (existUser(user.getNombre())) {
            throw new ComandaException(gestor.getBundle().getString("ERROR_USUARIO_EXISTE"));
       }
       Connection c = conectar();
       String query = "INSERT INTO users (nombre, password) VALUES(?, ?)";
       PreparedStatement ps = c.prepareStatement(query);
       ps.setString(1, user.getNombre());
       ps.setString(2, user.getContrasenya());
       
        int filasAfectadas = ps.executeUpdate();
        ps.close();
        desconectar(c);
        return filasAfectadas > 0;  
       
       
    }
    
     public boolean insertarPartida(Partida partida)throws SQLException{
         Connection c = conectar();
       String query = "INSERT INTO scores (id_user, puntuacion, victoria) VALUES(?, ?, ?)";
       PreparedStatement ps = c.prepareStatement(query);
       ps.setInt(1, partida.getUser().getId());
       ps.setInt(2, partida.getPuntos());
       ps.setBoolean(3, partida.isVictoria());
       
        int filasAfectadas = ps.executeUpdate();
        
        ps.close();
        desconectar(c);
        return filasAfectadas>0;
    }
    
    
    //UPDATE

    

    


    //RANKING
    
     public ArrayList<RankingTO> RankingTO() throws SQLException, ComandaException{
        
       ArrayList<RankingTO> lista = new ArrayList<>();
    
        Connection c = conectar();
        String query = "SELECT u.nombre AS nombre, " +
                       "COUNT(CASE WHEN s.victoria = true THEN 1 END) AS partidasGanadas, " +
                       "COUNT(s.id_user) AS partidasJugadas " +
                       "FROM users u " +
                       "JOIN scores s ON u.id_user = s.id_user " +
                       "GROUP BY u.nombre " +
                       "ORDER BY partidasGanadas DESC, u.nombre ASC";

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query); // Corrección: se usa executeQuery() en vez de execute()

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int partidasGanadas = rs.getInt("partidasGanadas");
            int partidasJugadas = rs.getInt("partidasJugadas");

            User user = new User(nombre); // Corrección: Crear correctamente el objeto User
            RankingTO rankingTO = new RankingTO(user, partidasGanadas, partidasJugadas); // Corrección: Crear correctamente RankingTO
            lista.add(rankingTO);
        }

        rs.close(); // Corrección: Cerrar el ResultSet
        st.close();
        c.close();

    return lista;
    }
     
//    public ArrayList<RankingTO> RankingTO() throws SQLException, ComandaException{
//        
//       ArrayList<RankingTO> lista = new ArrayList<>();
//    
//        Connection c = conectar();
//        String query = "SELECT u.nombre AS nombre, COUNT(s.puntuacion) AS ranking " +
//                       "FROM users u " +
//                       "JOIN scores s ON u.id_user = s.id_user " +
//                       "WHERE s.victoria = true " +
//                       "GROUP BY u.nombre " +
//                       "ORDER BY ranking DESC, u.nombre ASC";
//
//        Statement st = c.createStatement();
//        ResultSet rs = st.executeQuery(query); // Corrección: se usa executeQuery() en vez de execute()
//
//        while (rs.next()) {
//            String nombre = rs.getString("nombre");
//            int ranking = rs.getInt("ranking");
//
//            User user = new User(nombre); // Corrección: Crear correctamente el objeto User
//            RankingTO rankingTO = new RankingTO(user, ranking); // Corrección: Crear correctamente RankingTO
//            lista.add(rankingTO);
//        }
//
//        rs.close(); // Corrección: Cerrar el ResultSet
//        st.close();
//        c.close();
//
//    return lista;
//    }

    //GETTERS
    

    public User getUserByUsername(String name) throws SQLException, ComandaException{
        if(!existUser(name)){
            throw new ComandaException(gestor.getBundle().getString("ERROR_USUARIO_NO_ENCONTRADO"));
        }
        Connection c = conectar();
        User user = null;
        Statement st = c.createStatement();
        String query = "SELECT * FROM users WHERE nombre = '" + name + "';";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            user = new User(rs.getString("password"), rs.getString("nombre"),rs.getInt("id_user"));
        }
        rs.close();
        st.close();
        desconectar(c);

        return user;
    }

   

   



 
    //DELETE

    public void deleteUser(String nameString) throws SQLException, ComandaException{
        if (!existUser(nameString)) {
            throw new ComandaException(gestor.getBundle().getString("ERROR_USUARIO_INEXISTENTE"));
        }
        Connection c = conectar();
        String query = "Delete from users where nombre = '"+nameString+"'";
        Statement st = c.createStatement();
        st.executeUpdate(query);
        st.close();
        c.close();
    }

  
    //EXISTE

   

 

    private boolean existUser(String name) throws SQLException{
        Connection c = conectar();
        String query = "select * from users where nombre = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        ps.close();
        desconectar(c);
        return existe;
    }

    //CONNECTION

    private Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:6060/Buscaminas";
        String user = "postgres";
        String pass = "santi";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }

  

    

   
    
}
