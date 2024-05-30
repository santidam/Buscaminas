package com.mycompany.crm.persistencia;

import com.mycompany.crm.entity.Empresa;
import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.acciones.Accion;
import com.mycompany.crm.entity.acciones.Telefono;
import com.mycompany.crm.entity.acciones.Visita;
import com.mycompany.crm.entity.acciones.Email;
import com.mycompany.crm.exceptions.ComandaException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CrmDAO {

    //BUSCAR
    public LinkedHashMap<String, Empresa> buscarEmpresas(String phoneNumber, String nombre, String email, String representante, String direccion, String cp, String ciudad, String comunidadAutonoma, String paginaWeb) throws SQLException, ComandaException {
        LinkedHashMap<String, Empresa> empresas = new LinkedHashMap<>();
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
    public LinkedHashMap<String, Comercial> buscarEmpleados(Comercial comercial) throws SQLException, ComandaException {
        LinkedHashMap<String, Comercial> comerciales = new LinkedHashMap<>();
        Connection c = conectar();
        String sql = "SELECT * FROM comercial WHERE dni LIKE ? AND nombre LIKE ? AND apellidos LIKE ? AND porcentaje_comision LIKE ? AND fecha_incorporacion LIKE ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "%" + comercial.getDni() + "%");
        ps.setString(2, "%" + comercial.getNombre() + "%");
        ps.setString(3, "%" + comercial.getApellidos() + "%");
        ps.setString(4, "%" + comercial.getPorcentajeComision() + "%");
        ps.setString(5, "%" + comercial.getFechaIncorporacion() + "%");

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


    //INSERTAR/REGISTRAR
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
    public void registrarEmail(Email email) throws SQLException, ComandaException {
        Empresa empresa = getEmpresaByEmail(email.getEmail());
        if(empresa == null){
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }
        Connection c = conectar();
        String query = "call registrar_accion_email(?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, "email");
        ps.setDate(2, new Date(email.getFecha().getTime()));
        ps.setString(3, email.getDescripcion());
        ps.setString(4, email.getComercial().getDni());
        ps.setString(5, empresa.getPhoneNumber());
        ps.setString(6, email.getEmail());
        ps.setBoolean(7, email.isEsPromocion());
        ps.executeQuery();
        ps.close();
        desconectar(c);
    }

    public void registrarLlamada(Telefono tel) throws SQLException, ComandaException{
        if(!existeEmpresa(tel.getNumTelef())){
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }
        Connection c = conectar();
        String query = "call registrar_accion_llamada(?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, "telefono");
        ps.setDate(2, new Date(tel.getFecha().getTime()));
        ps.setString(3, tel.getDescripcion());
        ps.setString(4, tel.getComercial().getDni());
        ps.setString(5, tel.getNumTelef());
        ps.setString(6, tel.getAcuerdos());
        ps.executeQuery();
        ps.close();
        desconectar(c);
    }

    public void registrarVisita(Visita visita, String phone) throws SQLException, ComandaException{
        if(!existeEmpresa(phone)){
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }
        Connection c = conectar();
        String query = "call registrar_accion_visita(?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, "visita");
        ps.setDate(2, new Date(visita.getFecha().getTime()));
        ps.setString(3, visita.getDescripcion());
        ps.setString(4, visita.getComercial().getDni());
        ps.setString(5, phone);
        ps.setString(6, visita.getAcuerdos());
        ps.setString(7, visita.getDireccion());
        ps.executeQuery();
        ps.close();
        desconectar(c);
    }

    //UPDATE

    public void modificarEmpresa(Empresa empresa) throws SQLException, ComandaException{
        if(!existeEmpresaByCodigo(empresa.getCodigo())){
            throw new ComandaException(ComandaException.NOEXISTE_CLIENTE);
        }

        Connection c = conectar();
        String query = "UPDATE empresa SET email = ?, representante = ?, direccion = ?, CP = ?, ciudad = ?, comunidad_autonoma = ?, pagina_web = ? WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, empresa.getEmail());
        ps.setString(2, empresa.getRepresentante());
        ps.setString(3, empresa.getDireccion());
        ps.setInt(4, empresa.getCp());
        ps.setString(5, empresa.getCiudad());
        ps.setString(6, empresa.getComunidad_autonoma());
        ps.setString(7, empresa.getPagina_web());
        ps.setString(8, empresa.getCodigo());
        ps.executeUpdate();
        ps.close();
        desconectar(c);

    }

    //RANKING
    public Map<String,RankingTO> getRanking() throws SQLException, ComandaException{
        Connection c = conectar();
        Map<String,RankingTO> empleados = new LinkedHashMap<>();
//        String query = "SELECT c.nombre, a.comercial, COUNT(*) as acciones_totales FROM accion as a \n" +
//                "JOIN comercial as c ON a.comercial = c.dni\n" +
//                "GROUP BY comercial\n" +
//                "ORDER BY acciones_totales DESC";
        String query = "SELECT c.nombre, a.comercial, " +
                       "COUNT(*) AS acciones_totales, " +
                       "COUNT(CASE WHEN a.tipo = 'visita' THEN 1 ELSE NULL END) AS visitas, " +
                       "COUNT(CASE WHEN a.tipo = 'email' THEN 1 ELSE NULL END) AS emails, " +
                       "COUNT(CASE WHEN a.tipo = 'telefono' THEN 1 ELSE NULL END) AS llamadas " +
                       "FROM accion AS a " +
                       "JOIN comercial AS c ON a.comercial = c.dni " +
                       "GROUP BY a.comercial, c.nombre " +
                       "ORDER BY acciones_totales DESC";

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }
        while(hayContenido){
            Comercial comercial = new Comercial(rs.getString("comercial"),rs.getString("nombre"));
            RankingTO r = new RankingTO(comercial, rs.getInt("acciones_totales"));
            r.setAccionEmail(rs.getInt("emails"));
            r.setAccionLlamada(rs.getInt("llamadas"));
            r.setAccionVisita(rs.getInt("visitas"));
            empleados.put(rs.getString("comercial"),r);
            hayContenido = rs.next();
        }
        st.close();
        rs.close();
        desconectar(c);
        return empleados;
    }

    //GETTERS
    public Empresa getEmpresaByPhone(String phone) throws SQLException, ComandaException{
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

    public Comercial getComercialByDni(String dni) throws SQLException, ComandaException{
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

    public LinkedHashMap<String,Comercial> allComerciales() throws SQLException, ComandaException{
        Connection c = conectar();
        Statement st = c.createStatement();
        LinkedHashMap<String,Comercial> comerciales = new LinkedHashMap<>();
        Comercial comercial = null;
        String query = "SELECT * FROM comercial ORDER BY codigo";
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

    public LinkedHashMap<String,Empresa> allEmpresas() throws SQLException, ComandaException{
        Connection c = conectar();
        LinkedHashMap<String,Empresa> empresas = new LinkedHashMap<>();
        Statement st = c.createStatement();
        Empresa emp = null;
        String query = "SELECT * FROM empresa ORDER BY codigo";
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

    public LinkedHashMap<String, Accion> allAcciones() throws SQLException, ComandaException{
        Connection c = conectar();
        LinkedHashMap<String,Accion> acciones = new LinkedHashMap<>();
        Statement st = c.createStatement();
        Accion accion = null;
        String query = "SELECT * FROM accion as a\n";
        ResultSet rs = st.executeQuery(query);
        boolean hayContenido = rs.next();
        if(!hayContenido){
            throw new ComandaException(ComandaException.NO_CLIENTES);
        }
        while(hayContenido){
            String tipo = rs.getString("tipo");
            Comercial comercial = getComercialByDni(rs.getString("comercial"));
            Empresa e = getEmpresaByPhone(rs.getString("empresa"));
            accion = getAccion(rs.getInt("accion_id"), tipo, comercial);
            accion.setEmpresa(e);
            accion.setCodigo(rs.getInt("accion_id"));
            accion.setTipo(tipo);
            acciones.put(rs.getString("accion_id"),accion);
            hayContenido = rs.next();
        }
        rs.close();
        st.close();
        desconectar(c);

        return acciones;
    }

    private Accion getAccion(int accionId, String tipo, Comercial comercial) throws SQLException{
        Connection c = conectar();
        Accion accion = null;
        String query = "SELECT * FROM accion as a\n"+
        "JOIN accion_"+tipo+" as h ON a.accion_id = h.accionId\n"+
                "WHERE a.accion_id = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, accionId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            if (tipo.equalsIgnoreCase("visita")){
                accion = new Visita((rs.getDate("fecha")), comercial, rs.getString("descripcion"), rs.getString("acuerdos"), rs.getString("direccion"));
            }else if(tipo.equalsIgnoreCase("email")){
                accion = new Email((rs.getDate("fecha")), comercial, rs.getString("descripcion"), rs.getString("email"), rs.getBoolean("es_promocion"));
            }else{
                accion = new Telefono((rs.getDate("fecha")), comercial, rs.getString("descripcion"), rs.getString("acuerdos"), rs.getString("numero_telefono"));
            }
        }
        return accion;

    }

    public Empresa getEmpresaByEmail(String email) throws SQLException{
        Connection c = conectar();
        String query = "select * from empresa where email = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Empresa empresa = null;
        if (rs.next()) {
            empresa = new Empresa(rs.getString("nombre"), rs.getString("email"), rs.getString("phone_number"), rs.getString("representante"), rs.getString("direccion"), rs.getInt("CP"), rs.getString("ciudad"), rs.getString("comunidad_autonoma"), rs.getString("pagina_web"));
        }
        rs.close();
        ps.close();
        desconectar(c);
        return empresa;
    }

    //DELETE

    public void deleteEmpresa(String phoneNumber) throws SQLException, ComandaException{
        if (!existeEmpresa(phoneNumber)) {
            throw new ComandaException(ComandaException.ERROR_TEL);
        }
        Connection c = conectar();
        String query = "Delete from empresa where phone_number = '"+phoneNumber+"'";
        Statement st = c.createStatement();
        st.executeUpdate(query);
        st.close();
        c.close();
    }

    public void deleteEmpleado(String dni) throws SQLException, ComandaException{
        if (!existeComercial(dni)) {
            throw new ComandaException(ComandaException.NOEXISTE_EMPLEADO);
        }
        Connection c = conectar();
        String query = "Delete from comercial where dni = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, dni);
        ps.executeQuery();
        ps.close();
        c.close();
    }

    //EXISTE

    private boolean existeEmpresa(String phoneNumber) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from empresa where phone_number = '" + phoneNumber + "';";
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

    private boolean existeEmpresaByCodigo(String codigo) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from empresa where codigo = '" + codigo + "';";
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

    //CONNECTION

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/crm";
        String user = "root";
        String pass = "";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }

    
}
