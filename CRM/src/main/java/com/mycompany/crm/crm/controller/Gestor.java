package com.mycompany.crm.crm.controller;


import com.mycompany.crm.crm.entity.Comercial;
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

    public void altaEmpresa(String phone, String name, String contacto, String email) throws ComandaException {


    }

    public void altaEmpleado(String dni, String codigo, String name, String apellidos, int porcentajeComision, Date fechaIncorporacion, String contrasenya) throws ComandaException, SQLException {
        crmDAO.insertarComercial(dni, codigo, name, apellidos, porcentajeComision, fechaIncorporacion, contrasenya);
    }
    public void bajaEmpleado(String dni) throws ComandaException {



    }
    public String infoCliente(String phoneNumber) throws ComandaException {
        String info = "";
        try{
            info = crmDAO.mostrarEmpresa(phoneNumber);
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
        }
        return info;

    }

    public String infoEmpleado(String dni) throws ComandaException{
        String info = "";
        try{
            info = crmDAO.mostrarComercial(dni);
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
