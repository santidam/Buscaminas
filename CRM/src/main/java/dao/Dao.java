/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Dao {
     public Connection openConection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/crm";
        String user = "root";
        String pass = "";
        Connection c = DriverManager.getConnection(url,user,pass);
      
        return c;
     }
     
     public void insertEmpresa(String telefono, String nombre,String email, String contacto,String direccion, String cp,String ciudad,String comunidad,String homePage) {
        String sql = "INSERT INTO empresa (phone_number, nombre, email,representante, direccion, CP, ciudad, comunidad_autonoma, pagina_web) VALUES (?, ?, ?,?,?,?,?,?,?)";

        try (Connection conn = openConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, telefono);
            pstmt.setString(2, nombre);
            pstmt.setString(3, email);
            pstmt.setString(4, contacto);
            pstmt.setString(5, direccion);
            pstmt.setString(6, cp);
            pstmt.setString(7, ciudad);
            pstmt.setString(8, comunidad);
            pstmt.setString(9, homePage);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public ArrayList<Empresa> listaClientes(){
        ArrayList<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (Connection conn = openConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Empresa(rs.getString("nombre"), rs.getString("email"), rs.getString("phone_number"), rs.getString("representante")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
