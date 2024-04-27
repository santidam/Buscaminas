/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.validator;


import com.mycompany.crm.controller.Gestor;
import com.mycompany.crm.utils.CastData;
import Exceptions.ComandaException;

/**
 *
 * @author admin
 */
public class Validations {

    private Gestor gestor = new Gestor();
    private static Validations v;
    private Validations() {

    }
    public static Validations getInstance() {
        if (v == null) {
            v = new Validations();
        }
        return v;
    }
    public void valLogin(String dni, String password) throws ComandaException{
        if (!password.equals("1234")) {
            System.out.println("ERROR Cntraseña");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }
        gestor.login(dni);

    }

    public void valAltaCliente(String phone, String nameEmpresa, String contacto, String email) throws ComandaException {
        if (valPhone(phone)) {
                if (valName(nameEmpresa, "nombre")) {
                    if (valName(contacto, "apellido")) {
                        if (valEmail(email)) {
                            gestor.altaCliente(phone, nameEmpresa, contacto, email);
                        }
                    }
                }
            }
    }

    public void valAltaEmpleado(String dni, String name, String apellidos) throws ComandaException {
        if (valDni(dni)) {
            if (valName(name, "nombre")) {
                if (valName(apellidos, "apellido")) {
                    gestor.altaEmpleado(dni,name,apellidos);
                }
            }
        }
    }

    public void valBajaEmpleado(String dni) throws ComandaException {
        if (valDni(dni)) {
            gestor.bajaEmpleado(dni);
        }
    }

    public String valClienteInfo(String phone) throws ComandaException {
        valPhone(phone);
        return gestor.infoCliente(phone);
        
    }

    public String valEmpleadoInfo(String dni) throws ComandaException{
        valDni(dni);
            
        return gestor.infoEmpleado(dni);
    }

    public void valClientesList(String[] args) throws ComandaException {
        gestor.listClientes();
    }

    public void valEmpleadosList(String[] args) throws ComandaException {
        gestor.listEmpleados();
    }

//    public void valAsignarCliente(String[] args){
//        if (valLength(args.length, 3)){
//            if (valPhone(args[1])){
//                if (valDni(args[2])){
//                    gestor.asignarCliente(args[1],args[2]);
//                }
//            }
//        }
//    }
    public boolean valLength(int argsLength,int lengthEsperada) throws ComandaException {
        boolean Validacion = false;
        if (argsLength == lengthEsperada){
            Validacion = true;
        } else {
            System.out.println("ERROR. El número de argumentos es incorrecto.");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }
        return Validacion;
    }
    public boolean valPhone(String tel)throws ComandaException{
        //TODO Valentinal
        boolean esCorrecto = true;
        if(tel.length() == 9) {
            for (int i = 0; i < tel.length(); i++) {
                if (!Character.isDigit(tel.charAt(i))) {
                    System.out.println("El número de teléfono introducido no es válido.");
                    esCorrecto = false;
                    throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                }
            }
       }else{
            System.out.println("El numero de telefono introducido no es valido.");
            esCorrecto =false;
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }

        return esCorrecto;
    }


    public boolean valName(String name, String tipo) throws ComandaException {
        //TODO Santi
        name = name.trim();

        if (name.isEmpty()){
            System.out.println("Error; no has introducio ningun dato");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }
        String[] partes = name.split("\\s+");
        
        if (tipo.equals("apellido") && partes.length != 2){
            System.out.println("Error: debes introducir dos apellidos");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }
        if (tipo.equals("nombre") && partes.length > 2){
            System.out.println("Error: debes introducir un máximo de dos nombres");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }

        for (String part : partes) {
            if (part.length() < 2 || part.length() > 20) {
                System.out.println("Error: cada parte del " + tipo + " debe tener al menos 2 caracteres y un máximo de 20.");
                throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
            } else if (!part.matches("[\\p{L}]+")) {
                System.out.println("Error: solo puedes introducir caracteres alfabéticos en cada parte del " + tipo);
                throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
            }
        }

        return true;
    }

    public boolean valDni(String dni) throws ComandaException {
        //TODO Jordi
        dni = dni.strip().toUpperCase();
        boolean isValid = true;
        int resto = 0;
        String dniNum = getNumDni(dni);
        int numDni = 0;
        String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (dniNum.length() == 8 && dni.length() == 9){
            numDni = CastData.toInt(dniNum);
            char letter = dni.charAt(dni.length()-1);

            resto = numDni%23;
            if (letter == validLetters.charAt(resto)){
                isValid = true;
            } else{
                System.out.println("El último carácter solo puede ser una letra y tiene que ser válida");
                throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
            }
        }else{
            System.out.println("El formato del DNi debe ser '12345678X'");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        }

        return isValid;
    }
    
    public String getNumDni(String dni){
        String validNumbers = "0123456789";
        String numDni = "";
        for(int i = 0; i<dni.length()-1; i++){
            for(int j = 0; j<validNumbers.length(); j++){
                if (dni.charAt(i) == validNumbers.charAt(j)){
                    numDni += dni.charAt(i);
                }
            }
        }      
        return numDni;
    }

    public boolean valEmail(String email) throws ComandaException {
        if (email.isEmpty()) {
            System.out.println("El correo electrónico no puede estar vacío.");
            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
        } else {
            if (email.startsWith("-") || email.endsWith("-") || email.startsWith(".") || email.endsWith(".") || email.contains("..")) {
                System.out.println("El correo electrónico no puede comenzar o terminar con '-' o '.' ni contener '..'");
                throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
            } else {
                if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@")) {
                    System.out.println("El correo electrónico debe contener un solo '@'.");
                    throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                } else {
                    String[] emailSeparado = email.split("@");
                    if (emailSeparado.length < 2) {
                        System.out.println("El correo electrónico debe tener caracteres antes y después de '@'.");
                        throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                    } else {
                        String[] dominioEmail = emailSeparado[1].split("\\.");
                        if (dominioEmail.length < 2) {
                            System.out.println("El dominio del correo electrónico debe contener '.'.");
                            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                        } else {
                            if (dominioEmail[0].length() > 63 || dominioEmail[1].length() > 63) {
                                System.out.println("Ninguna parte del dominio del correo electrónico puede exceder los 63 caracteres.");
                                throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                            } else {
                                if (!email.matches("^[a-zA-Z0-9.ñÑ_-]+@[a-zA-Z0-9.ñÑ-]+$")) {
                                    System.out.println("El correo electrónico solo puede contener caracteres alfanuméricos y . _ -");
                                    throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                                } else {
                                    if (emailSeparado[0].isEmpty() || emailSeparado[0].length() > 64) {
                                        System.out.println("La parte local del correo electrónico (antes de @) debe tener entre 1 y 64 caracteres.");
                                        throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                                    } else {
                                        if (emailSeparado[1].isEmpty() || emailSeparado[1].length() > 64) {
                                            System.out.println("La parte del dominio del correo electrónico (después de @) debe tener entre 1 y 64 caracteres.");
                                            throw new ComandaException(ComandaException.ARGS_INCORRECTOS);
                                        } else {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
