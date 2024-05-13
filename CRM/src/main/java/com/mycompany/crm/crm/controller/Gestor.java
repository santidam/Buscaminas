package com.mycompany.crm.crm.controller;


import com.mycompany.crm.crm.entity.Comercial;
import com.mycompany.crm.crm.entity.Empresa;
import com.mycompany.crm.crm.exceptions.ComandaException;
import com.mycompany.crm.crm.persistencia.CrmDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gestor {

    private Comercial comercial;
    private CrmDAO crmDAO = new CrmDAO();
    
    public void login(String dni) throws ComandaException{
        int indice = 1;
        if (indice == -1) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(ComandaException.ERROR_USER);
        }
    }

    public void altaEmpresa(String nombre, String agente, String phone, String email, String codigo, String direccion, int cp, String region, String web, String ciudad) throws ComandaException, SQLException{
        Empresa empresa = new Empresa(nombre, agente, phone, email, codigo, cp, direccion, region, web, ciudad);
        crmDAO.insertarEmpresa(empresa);
    }

    public void altaEmpleado(String dni, String codigo, String name, String apellidos, int porcentajeComision, Date fechaIncorporacion, String contrasenya) throws ComandaException, SQLException {
        Comercial comercial = new Comercial(dni, codigo, name, apellidos, porcentajeComision, fechaIncorporacion, contrasenya);
        crmDAO.insertarComercial(comercial);
    }
    public void bajaEmpleado(String dni) throws ComandaException {



    }
    public Empresa infoCliente(String phoneNumber) throws ComandaException {
        Empresa empresa = null;
        try{
            empresa = crmDAO.mostrarEmpresa(phoneNumber);
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return empresa;

    }

    public String infoEmpleado(String dni) throws ComandaException{

        String info = "";
        Empresa empresa = null;
        try{
            empresa = crmDAO.mostrarComercial(dni);

        }catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return info;
    }

    public String listClientes()throws ComandaException{
        String info = "";
        try{
            info = crmDAO.listarEmpresas();
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return info;
    }
    public String listEmpleados()throws ComandaException{
        String info = "";
        try{
            info = crmDAO.listarComerciales();
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return info;
    }
}
