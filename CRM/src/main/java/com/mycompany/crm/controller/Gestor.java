package com.mycompany.crm.controller;


import com.mycompany.crm.crm.entity.Comercial;
import com.mycompany.crm.crm.entity.Empresa;
import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.persistencia.CrmDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gestor {

    private Comercial comercial;
    private CrmDAO crmDAO = new CrmDAO();
    
    public boolean login(String dni, String passwd) throws ComandaException, SQLException{
        Comercial u1 = null; // Metodo user
        if (u1==null) {
            System.out.println("ERROR. Usuario no existe");
            throw new ComandaException(ComandaException.ERROR_USER);
        }else{
             if (!u1.getContrasenya().equals(passwd)) {
            throw new ComandaException(ComandaException.ERROR_USER); // Error de contrase�a
        }
        this.comercial = u1;
        return true;
        }
    }

    public void altaEmpresa(String nombre, String agente, String phone, String email, String codigo, String direccion, int cp, String region, String web, String ciudad) throws ComandaException, SQLException{
        Empresa empresa = new Empresa(nombre, agente, phone, email, codigo, cp, direccion, region, web, ciudad);
        crmDAO.insertarEmpresa(empresa);
    }

    public void altaEmpleado(String dni, String name, String apellidos, int porcentajeComision, Date fechaIncorporacion, String contrasenya) throws ComandaException, SQLException {
        Comercial comercial = new Comercial(dni, name, apellidos, porcentajeComision, fechaIncorporacion, contrasenya);
        crmDAO.insertarComercial(comercial);
    }
    public void bajaEmpleado(String dni) throws ComandaException {



    }
    public Empresa infoCliente(String phoneNumber) throws ComandaException, SQLException {
        return crmDAO.mostrarEmpresa(phoneNumber);
    }

    public Comercial infoEmpleado(String dni) throws ComandaException, SQLException{
        return crmDAO.mostrarComercial(dni);
    }

    public ArrayList<Empresa> listClientes()throws ComandaException, SQLException{
        return crmDAO.allEmpresas();
    }
    public ArrayList<Comercial> listEmpleados()throws ComandaException, SQLException{
        return crmDAO.allComerciales();
    }
}
