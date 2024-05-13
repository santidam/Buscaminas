package com.mycompany.crm.crm.persistencia;

import com.mycompany.crm.crm.entity.Comercial;
import com.mycompany.crm.crm.entity.Empresa;
import com.mycompany.crm.crm.exceptions.ComandaException;

import java.sql.*;
import java.util.ArrayList;

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

    public void insertarEmpresa(Empresa empresa) throws SQLException, ComandaException{
        if (existeComercial(empresa.getPhoneNumber())) {
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
        PreparedStatement ps = c.prepareStatement("insert into comercial(dni, codigo, nombre, apellidos, porcentaje_comision, fecha_incorporacion, contrasenya ) values (?,?,?,?,?,?,?);");
        ps.setString(1, comercial.getDni());
        ps.setString(2, comercial.getCodigo());
        ps.setString(3, comercial.getNombre());
        ps.setString(4, comercial.getApellidos());
        ps.setInt(5, comercial.getPorcentajeComision());
        ps.setDate(6, new Date(comercial.getFechaIncorporacion().getTime()));
        ps.setString(7, comercial.getContrasenya());
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

    public String mostrarComercial(String dni) throws SQLException, ComandaException{
        if(!existeComercial(dni)){
            throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
        }
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "SELECT * FROM comercial WHERE dni = '" + dni + "';";
        ResultSet rs = st.executeQuery(query);
        String infoComercial = "";
        if(rs.next()){
            infoComercial = "DNI: \t" + rs.getString("dni") + "\n"+
                    "CODIGO: \t" + rs.getString("codigo") + "\n"+
                    "NOMBRE: \t" + rs.getString("nombre") + "\n"+
                    "APELLIDOS: \t" + rs.getString("apellidos") + "\n"+
                    "%COMISION: \t" + rs.getInt("porcentaje_comision")+"%" + "\n"+
                    "INGRESO: \t" + rs.getDate("fecha_incorporacion") + "\n";
        }
        rs.close();
        st.close();
        desconectar(c);

        return infoComercial;
    }

    public String listarComerciales() throws SQLException, ComandaException{
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "SELECT * FROM comercial";
        ResultSet rs = st.executeQuery(query);
        String infoComercial = "";
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_EMPLEADOS);
        }
        while(hayContenido){
            infoComercial += "DNI: \t" + rs.getString("dni") + "\n"+
                    "CODIGO: \t" + rs.getString("codigo") + "\n"+
                    "NOMBRE: \t" + rs.getString("nombre") + "\n"+
                    "APELLIDOS: \t" + rs.getString("apellidos") + "\n"+
                    "%COMISION: \t" + rs.getInt("porcentaje_comision")+"%" + "\n"+
                    "INGRESO: \t" + rs.getDate("fecha_incorporacion") + "\n"+
            "-----------------------------------------------------------\n";
            hayContenido = rs.next();
        }
        rs.close();
        st.close();
        desconectar(c);

        return infoComercial;
    }

    public ArrayList<Empresa> allEmpresas() throws SQLException, ComandaException{
        Connection c = conectar();
        ArrayList<Empresa> empresas = new ArrayList<>();
        Statement st = c.createStatement();
        String query = "SELECT * FROM empresa";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){

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
        String user = "root";
        String pass = ".Aa654321.";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }
}
