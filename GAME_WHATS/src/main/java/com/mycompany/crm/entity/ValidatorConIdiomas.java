/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.entity;

import java.util.Locale;
import java.util.ResourceBundle;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Severity;
import org.netbeans.validation.api.Validator;

public class ValidatorConIdiomas implements Validator<String> {  // Asegurar que es <String>
    private final String nombreCampo;

    public ValidatorConIdiomas(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

//    @Override
//    public Problem validate(String value) {  // Método correctamente implementado
//        ResourceBundle bundle = ResourceBundle.getBundle(
//                "org.netbeans.validation.api.builtin.stringvalidation.Bundle",
//                Locale.getDefault()
//        );
//        
//        String mensaje = bundle.getString("MSG_FIELD_EMPTY");
//        mensaje = mensaje.replace("{0}", nombreCampo);
//
//        if (value == null || value.trim().isEmpty()) {
//            return new Problem(mensaje, Severity.FATAL);
//        }
//        return null;  // No hay error si el campo está lleno
//    }

    @Override
    public void validate(Problems prblms, String string, String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class<String> modelType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
