package com.mycompany.crm.persistencia;

import com.mycompany.crm.entity.Empresa;
import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.exceptions.ComandaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CrmDAO {

   /* public ArrayList<Propietario> allPropietarios() throws SQLException {
        Connection c = conectar();
        ArrayList<Propietario> propietarios = new ArrayList<>();
        String query = "select * from propietario;";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String poblacion = rs.getString("poblacion");
            propietarios.add(new Propietario(nombre, poblacion));
        }
        rs.close();
        st.close();
        desconectar(c);
        return propietarios;
    }*/


    public HashMap<String, Empresa> buscarEmpresas(String phoneNumber, String nombre, String email, String representante, String direccion, String cp, String ciudad, String comunidadAutonoma, String paginaWeb) throws SQLException, ComandaException {
        HashMap<String, Empresa> empresas = new HashMap<>();
        Connection c = conectar();
        String sql = "SELECT * FROM empresa WHERE " +
            "phone_number LIKE ? AND " +
            "nombre LIKE ? AND " +
            "email LIKE ? AND " +
            "representante LIKE ? AND " +
            "direccion LIKE ? AND " +
            "CP LIKE ? AND " +
            "ciudad LIKE ? AND " +
            "comunidad_autonoma LIKE ? AND " +
            "pagina_web LIKE ?";

        PreparedStatement ps = c.prepareStatement(sql);

        ps.setString(1, "%" + phoneNumber + "%");
        ps.setString(2, "%" + nombre + "%");
        ps.setString(3, "%" + email + "%");
        ps.setString(4, "%" + representante + "%");
        ps.setString(5, "%" + direccion + "%");
        ps.setString(6, "%" + cp + "%");
        ps.setString(7, "%" + ciudad + "%");
        ps.setString(8, "%" + comunidadAutonoma + "%");
        ps.setString(9, "%" + paginaWeb + "%");


        ResultSet rs = ps.executeQuery();
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }
        while(hayContenido){
            Empresa emp = new Empresa(rs.getString("nombre"), rs.getString("email"), rs.getString("phone_number"), rs.getString("representante"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("ciudad"), rs.getString("comunidad_autonoma"), rs.getString("codigo"), rs.getString("pagina_web"));
            empresas.put(rs.getString("codigo"),emp);
            hayContenido = rs.next();
        }
        rs.close();
        ps.close();
        desconectar(c);

        return empresas;

    }
    public HashMap<String, Comercial> buscarEmpleados(String dni, String nombre, String apellidos, String comision, String incorporación) throws SQLException, ComandaException {
        HashMap<String, Comercial> comerciales = new HashMap<>();
        Connection c = conectar();
        String sql = "SELECT * FROM comercial WHERE dni LIKE ? AND nombre LIKE ? AND apellidos LIKE ? AND porcentaje_comision LIKE ? AND fecha_incorporacion LIKE ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "%" + dni + "%");
        ps.setString(2, "%" + nombre + "%");
        ps.setString(3, "%" + apellidos + "%");
        ps.setString(4, "%" + comision + "%");
        ps.setString(5, "%" + incorporación + "%");

        ResultSet rs = ps.executeQuery();
        boolean tieneResultados = false;
        while (rs.next()) {
            tieneResultados = true;
            comerciales.put(rs.getString("codigo"), new Comercial(rs.getString("dni"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("porcentaje_comision"), rs.getDate("fecha_incorporacion"), rs.getString("contrasenya")));
        }

        if (!tieneResultados) {
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        }

        return comerciales;    
    }



    public void insertarEmpresa(Empresa empresa) throws SQLException, ComandaException{
        if (existeEmpresa(empresa.getPhoneNumber())) {
            throw new ComandaException(ComandaException.CLIENTE_EXISTE);
        }
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into empresa (phone_number, nombre, email, representante, direccion, CP, ciudad, comunidad_autonoma, pagina_web, codigo) values (?,?,?,?,?,?,?,?,?,?);");
        ps.setString(1, empresa.getPhoneNumber());
        ps.setString(2, empresa.getNombre());
        ps.setString(3, empresa.getEmail());
        ps.setString(4, empresa.getRepresentante());
        ps.setString(5, empresa.getDireccion());
        ps.setInt(6, empresa.getCp());
        ps.setString(7, empresa.getCiudad());
        ps.setString(8, empresa.getComunidad_autonoma());
        ps.setString(9,empresa.getPagina_web());
        ps.setString(10, empresa.getCodigo());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

    public void insertarComercial(Comercial comercial) throws SQLException, ComandaException{
        if (existeComercial(comercial.getDni())) {
            throw new ComandaException(ComandaException.EMPLEADO_EXISTE);
        }
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into comercial(dni, nombre, apellidos, porcentaje_comision, fecha_incorporacion, contrasenya ) values (?,?,?,?,?,?);");
        ps.setString(1, comercial.getDni());
        ps.setString(2, comercial.getNombre());
        ps.setString(3, comercial.getApellidos());
        ps.setInt(4, comercial.getPorcentajeComision());
        ps.setDate(5, new Date(comercial.getFechaIncorporacion().getTime()));
        ps.setString(6, comercial.getContrasenya());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }
    public Empresa mostrarEmpresa(String phone) throws SQLException, ComandaException{
        if(!existeEmpresa(phone)){
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }
        Connection c = conectar();
        Empresa emp = null;
        Statement st = c.createStatement();
        String query = "SELECT * FROM empresa WHERE phone_number = '" + phone + "';";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            emp = new Empresa(rs.getString("nombre"), rs.getString("email"), rs.getString("phone_number"), rs.getString("representante"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("ciudad"), rs.getString("comunidad_autonoma"), rs.getString("codigo"), rs.getString("pagina_web"));
        }
        rs.close();
        st.close();
        desconectar(c);

        return emp;
    }
    public void deleteEmpresa(String phoneNumber) throws SQLException, ComandaException{
        if (!existeEmpresa(phoneNumber)) {
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }
        Connection c = conectar();
        String query = "Delete from empresa where phone_number = '"+phoneNumber+"'";
        Statement st = c.createStatement();
        st.executeUpdate(query);
        st.close();
        c.close();
    }

    public Comercial mostrarComercial(String dni) throws SQLException, ComandaException{
        if(!existeComercial(dni)){
            throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
        }
        Connection c = conectar();
        Comercial comercial = null;
        Statement st = c.createStatement();
        String query = "SELECT * FROM comercial WHERE dni = '" + dni + "';";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            comercial = new Comercial(rs.getString("dni"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("porcentaje_comision"), rs.getDate("fecha_incorporacion"), rs.getString("contrasenya"));
        }
        rs.close();
        st.close();
        desconectar(c);

        return comercial;
    }

    public HashMap<String,Comercial> allComerciales() throws SQLException, ComandaException{
        Connection c = conectar();
        Statement st = c.createStatement();
        HashMap<String,Comercial> comerciales = new HashMap<>();
        Comercial comercial = null;
        String query = "SELECT * FROM comercial";
        ResultSet rs = st.executeQuery(query);
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        }
        while(hayContenido){
            comercial = new Comercial(rs.getString("dni"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("porcentaje_comision"), rs.getDate("fecha_incorporacion"), rs.getString("contrasenya"));
            comerciales.put(rs.getString("codigo"),comercial);
            hayContenido = rs.next();
        }
        rs.close();
        st.close();
        desconectar(c);

        return comerciales;
    }

    public HashMap<String,Empresa> allEmpresas() throws SQLException, ComandaException{
        Connection c = conectar();
        HashMap<String,Empresa> empresas = new HashMap<>();
        Statement st = c.createStatement();
        Empresa emp = null;
        String query = "SELECT * FROM empresa";
        ResultSet rs = st.executeQuery(query);
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }
        while(hayContenido){
            emp = new Empresa(rs.getString("nombre"), rs.getString("email"), rs.getString("phone_number"), rs.getString("representante"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("ciudad"), rs.getString("comunidad_autonoma"), rs.getString("codigo"), rs.getString("pagina_web"));
            empresas.put(rs.getString("codigo"),emp);
            hayContenido = rs.next();
        }
        rs.close();
        st.close();
        desconectar(c);

        return empresas;
    }


    private boolean existeEmpresa(String phoneNumber) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from Empresa where phone_number = '" + phoneNumber + "';";
        ResultSet rs = st.executeQuery(query);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    private boolean existeComercial(String dni) throws SQLException{
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from comercial where dni = '" + dni + "';";
        ResultSet rs = st.executeQuery(query);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/crm";
        String user = "crm";
        String pass = "1234";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }

    
}
