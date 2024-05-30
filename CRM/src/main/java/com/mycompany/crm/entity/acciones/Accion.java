/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity.acciones;


import com.mycompany.crm.entity.Comercial;
import com.mycompany.crm.entity.Empresa;
import com.mycompany.crm.exceptions.ComandaException;

import java.util.Date;

public class Accion {
    private Date fecha;
    private String descripcion;
    private Comercial comercial;
    private Empresa empresa;
    private int codigo;
    private String tipo;

    public Accion(Date fecha, Comercial comercial, String descripcion) throws ComandaException{
        if(descripcion.length() > 255){
            throw new ComandaException(ComandaException.ERROR_LONGITUD_255);
        }
        if(fecha.before(new Date(1900,1,1)) && fecha.after(new Date())){
            throw new ComandaException(ComandaException.ERROR_RANGO_FECHAINCORPORACION);
        }
        this.fecha = fecha;
        this.comercial = comercial;
        this.descripcion = descripcion;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }
    

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
    


    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Comercial getComercial() {
        return comercial;
    }


    @Override
    public String toString() {
        return "Fecha: " + fecha + "\tDescripcion: "+ descripcion;
    }
}