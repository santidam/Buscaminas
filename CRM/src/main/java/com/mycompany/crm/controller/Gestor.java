package com.mycompany.crm.controller;


import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.Empresa;

import com.mycompany.crm.entity.acciones.Telefono;
import com.mycompany.crm.entity.acciones.Visita;

import com.mycompany.crm.entity.acciones.Email;

import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.persistencia.CrmDAO;

import java.sql.Date;
import java.sql.SQLException;

import java.util.HashMap;

public class Gestor {

    private Comercial comercial;
    private CrmDAO crmDAO = new CrmDAO();
    
    public boolean login(String dni, String passwd) throws ComandaException, SQLException{
        Comercial u1 = crmDAO.getComercialByDni(dni); // Metodo user
        if (u1==null) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(ComandaException.ERROR_USER);
        }else{
             if (!u1.getContrasenya().equals(passwd)) {
                 throw new ComandaException(ComandaException.ERROR_CONTRASEÑA);
             }
             this.comercial = u1;
             return true;
        }
    }

    //REGISTRAR
    public void altaEmpresa(String nombre, String email, String phoneNumber, String representante, String direccion, int cp, String ciudad, String comunidad_autonoma, String pagina_web) throws ComandaException, SQLException{
        Empresa empresa = new Empresa(nombre, email, phoneNumber, representante, direccion, cp, ciudad, comunidad_autonoma, pagina_web);
        crmDAO.insertarEmpresa(empresa);
    }

    public void altaEmpleado(String dni, String name, String apellidos, int porcentajeComision, Date fechaIncorporacion) throws ComandaException, SQLException {
        Comercial comercial = new Comercial(dni, name, apellidos, porcentajeComision, fechaIncorporacion);
        crmDAO.insertarComercial(comercial);
    }
    public void registrarEmail(String correo, String desc, Date fecha, boolean esPromocion) throws SQLException, ComandaException{
        Email email = new Email(fecha, this.comercial, desc, correo, esPromocion);
        crmDAO.registrarEmail(email);
    }

    public void registrarLlamada(String descripcion, Date fecha, String acuerdo, String numTelf) throws ComandaException, SQLException{
        Telefono telf = new Telefono(fecha, comercial, descripcion, acuerdo, numTelf);
        crmDAO.registrarLlamada(telf);
    }

    public void registrarVisita(String descripcion, Date fecha, String acuerdo, String phone, String direccion) throws ComandaException, SQLException{
        Visita visita = new Visita(fecha, comercial, descripcion, acuerdo, direccion);
        crmDAO.registrarVisita(visita, phone);
    }

    //BAJA
    public void bajaEmpleado(String dni) throws ComandaException, SQLException {
       //Hacer metodo
        crmDAO.deleteEmpleado(dni);
    }
    public void bajaEmpresa(String numero) throws ComandaException, SQLException {
        crmDAO.deleteEmpresa(numero);
    }

    //BUSCAR
    public HashMap<String,Empresa> busquedaEmpresa(String phoneNumber, String nombre, String email, String representante, String direccion, String cp, String ciudad, String comunidadAutonoma, String paginaWeb) throws SQLException, ComandaException{
        return crmDAO.buscarEmpresas(phoneNumber, nombre, email, representante, direccion, cp, ciudad, comunidadAutonoma, paginaWeb);
    }
    public HashMap<String, Comercial> busquedaEmpleado(String dni, String nombre, String apellidos, String comision, String incorporacion) throws SQLException, ComandaException {
        return crmDAO.buscarEmpleados(dni, nombre, apellidos, comision, incorporacion);
    }

    public Empresa infoCliente(String phoneNumber) throws ComandaException, SQLException {
        return crmDAO.getEmpresaByPhone(phoneNumber);
    }

    public Comercial infoEmpleado(String dni) throws ComandaException, SQLException{
        return crmDAO.getComercialByDni(dni);
    }

    //LISTAR
    public HashMap<String,Empresa> listClientes()throws ComandaException, SQLException{
        return crmDAO.allEmpresas();
    }
    public HashMap<String,Comercial> listEmpleados()throws ComandaException, SQLException{
        return crmDAO.allComerciales();
    }

    //UPDATE
    public void modificarEmpresa(Empresa empresa) throws ComandaException, SQLException{
        crmDAO.modificarEmpresa(empresa);
    }

}
