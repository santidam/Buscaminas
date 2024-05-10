package com.mycompany.crm.crm.exceptions;


import java.util.Arrays;
import java.util.List;

public class ComandaException  extends Exception{

   public static final int ERROR_DNI = 0;
   public static final int ERROR_TEL = 1;
   public static final int ERROR_CORREO = 2;
   public static final int ERROR_NOM = 3;
   public static final int ERROR_AP = 4;
   public static final int CLIENTE_EXISTE = 5;
   public static final int EMPLEADO_EXISTE = 6;
   public static final int NO_CLIENTES = 7;
   public static final int NO_EMPLEADOS = 8;
   public static final int DATO_INCORRECTO = 9;
   public static final int ERROR_USER = 10;
   public static final int NOEXISTE_EMPLEADO = 11;
   public static final int NOEXISTE_CLIENTE = 12;
   public static final int ARGS_INCORRECTOS = 13;
   public static final int NO_DATO = 14;
   public static final int DOS_APELLIDOS = 15;
   public static final int MAX_NOMBRES = 16;
   public static final int ERROR_CONTRASEÑA = 17;
   
   private final List<String> mensajes = Arrays.asList (
           "ERROR: EL FORMATO DEL DNI DEBE SER '12345678X'",
           "ERROR: NUMERO DE TELEFONO INVALIDO",
           "ERROR: CORREO INVALIDO",
           "ERROR: NOMBRE INVALIDO",
           "ERROR: APELLIDO INVALIDO",
           "ERROR: EL CLIENTE YA SE ENCUENTRA EN LA BASE DE DATOS",
           "ERROR: EL EMPLEADO YA SE ENCUENTRA EN LA BASE DE DATOS",
           "ERROR: NO EXISTEN CLIENTES",
           "ERROR: NO EXISTEN EMPLEADOS",
           "ERROR: DATO INCORRECTO",
           "ERROR: EL USUARIO INGRESADO NO EXISTE",
           "ERROR: EL EMPLEADO INGRESADO NO EXISTE",
           "ERROR: EL CLIENTE INGRESADO NO EXISTE",
           "ERROR: ARGUMENTOS INCORRECTOS",
           "ERROR: NO HAS INTRODUCIDO NINGUN DATO",
           "ERROR: DEBES INTRODUCIR DOS APELLIDOS",
           "ERROR: DEBES INTRODUCIR UN MÁXIMO DE DOS NOMBRES",
           "ERROR: CONTRASEÑA INCORRECTA"
   );

    private final int code;

    public ComandaException(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return mensajes.get(code);
    }
}
