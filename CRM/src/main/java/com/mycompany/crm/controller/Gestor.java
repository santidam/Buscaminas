package com.mycompany.crm.controller;


import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.Empresa;

import com.mycompany.crm.entity.RankingTO;
import com.mycompany.crm.entity.acciones.Accion;
import com.mycompany.crm.entity.acciones.Telefono;
import com.mycompany.crm.entity.acciones.Visita;

import com.mycompany.crm.entity.acciones.Email;

import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.persistencia.CrmDAO;
import com.mycompany.crm.utils.CastData;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
        Telefono telf = new Telefono(fecha, this.comercial, descripcion, acuerdo, numTelf);
        crmDAO.registrarLlamada(telf);
    }

    public void registrarVisita(String descripcion, Date fecha, String acuerdo, String phone, String direccion) throws ComandaException, SQLException{
        Visita visita = new Visita(fecha, this.comercial, descripcion, acuerdo, direccion);
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
    public LinkedHashMap<String,Empresa> busquedaEmpresa(String phoneNumber, String nombre, String email, String representante, String direccion, String cp, String ciudad, String comunidadAutonoma, String paginaWeb) throws SQLException, ComandaException{
        String nuevaCp="";
        int num;
        if(cp.equalsIgnoreCase("")){
            nuevaCp = "1";
            num = CastData.toInt(nuevaCp);
        }else{
            num = CastData.toInt(cp);
        }
        Empresa empresa = new Empresa(nombre,  email, phoneNumber, representante, direccion, num, ciudad, comunidadAutonoma, paginaWeb);
        return crmDAO.buscarEmpresas(empresa, cp);
    }
    public LinkedHashMap<String, Comercial> busquedaEmpleado(String dni, String nombre, String apellidos, String comision, Date incorporacion) throws SQLException, ComandaException {
        String nuevaComision = "";
        int num;
        if(comision.equalsIgnoreCase("")){
            nuevaComision = "0";
            num = CastData.toInt(nuevaComision);
        }else{
            num = CastData.toInt(comision);
        }
        Comercial comercial = new Comercial(dni, nombre, apellidos, num, incorporacion);
        return crmDAO.buscarEmpleados(comercial, comision);
    }

    //LISTAR
    public LinkedHashMap<String,Empresa> listClientes()throws ComandaException, SQLException{
        return crmDAO.allEmpresas();
    }
    public LinkedHashMap<String,Comercial> listEmpleados()throws ComandaException, SQLException{
        return crmDAO.allComerciales();
    }
    public LinkedHashMap<String, Accion> listaAcciones()throws ComandaException, SQLException{
        return crmDAO.allAcciones();
    }


    public Map<String,RankingTO> ranking()throws ComandaException, SQLException{
        return crmDAO.getRanking();
    }

    //UPDATE
    public void modificarEmpresa(Empresa empresa) throws ComandaException, SQLException{
        crmDAO.modificarEmpresa(empresa);
    }

    public void modificarEmpleado(Comercial comercial) throws ComandaException, SQLException{
        crmDAO.modificarComercial(comercial);
    }

    public Comercial getComercial() {
        return comercial;
    }
}
