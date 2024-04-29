package com.mycompany.crm.crm.exceptions;

import java.util.Arrays;
import java.util.List;

public class ComandaException extends Exception {
   public static final int OP_INCORRECTA = 0;
   public static final int NUM_ARGS_INCORRECTO = 1;
   public static final int EXISTE_JUGADOR = 2;
   public static final int EXISTE_PERSONAJE = 3;
   public static final int ESTUDIO_INCORRECTO = 4;
   public static final int LUGAR_INCORRECTO = 5;
   public static final int PREF_INORRECTA = 6;
   public static final int EXISTE_OBJETO = 7;
   public static final int TIPO_INCORRECTO = 8;
   public static final int DATO_INCORRECTO = 9;
   public static final int PRECIO_INCORRECTO = 10;
   public static final int COLOR_INCORRECTO = 11;
   public static final int USO_INCORRECTO = 12;
   public static final int MISMO_NOMBRE_JUGADOR = 13;
   public static final int MISMO_NOMBRE_OBJETO = 14;
   public static final int JUGADOR_TIENE_OBJETO = 15;
   public static final int JUGADOR_CALACOINS = 16;
   public static final int JUGADOR_BAR = 17;
   public static final int NO_EXISTE = 18;
   public static final int JUGADOR_PERSONAJE_LUGAR = 19;
   public static final int JUGADOR_NO_TIENE_OBJETO = 20;
   public static final int PERSONA_LUGAR = 21;
   public static final int PERSONA_INCORRECTA = 22;
   
   private final List<String> mensajes = Arrays.asList (
           "ERROR: DNI INVALIDO",
           "ERROR: NUMERO DE TELEFONO INVALIDO",
           "ERROR: CORREO INVALIDO",
           "ERROR: NOMBRE INVALIDO",
           "ERROR: APELLIDO INVALIDO",
           "ERROR: DEBE INGRESAR DOS APELLIDOS",
           "ERROR: EL CLIENTE YA SE ENCUENTRA EN LA BASE DE DATOS",
           "ERROR: EL EMPLEADO YA SE ENCUENTRA EN LA BASE DE DATOS",
           "ERROR: NO EXISTEN CLIENTES",
           "ERROR: NO EXISTEN EMPLEADOS",
           "ERROR: DATO INCORRECTO"
   );
   private final int code;
   public ComandaException (int code) {
      this.code = code;
   }
   public String getMensaje() {
      return mensajes.get(code);
   }
}