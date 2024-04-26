package Exceptions;


import java.util.Arrays;
import java.util.List;

public class ComandaExeption  extends Exception{

    public static final int OPERACION_INCORRECTA = 0;
    public static final int ARGS_INCORRECTOS = 1;
    public static final int WORKER_EXISTENTE = 2;
    public static final int HABITACION_EXISTENTE = 3;
    public static final int ESTUDIO_INCORRECTO = 4;
    public static final int SERVICIO_INCORRECTO = 5;
    public static final int HABILIDAD_INCORRECTA = 6;
    public static final int HABITACION_SIN_CLIENTE = 7;
    public static final int TIPO_INCORRECTO = 8;
    public static final int DATO_INCORRECTO = 9;
    public static final int NUMERO_MENOR_UNO = 10;
    public static final int COLOR_INCORRECTO = 11;
    public static final int USO_FUERA_DE_RANGO = 12;
    public static final int JUGADOR_NO_REGISTRADO = 13;
    public static final int HABITACIONES_NO_CUMPLEN_REQUERIMIENTOS = 14;
    public static final int JUGADOR_CON_OBJETO = 15;
    public static final int CALACOINS_INSUFICIENTES = 16;
    public static final int JUGADOR_FUERA_BAR = 17;
    public static final int CLIENTE_EXISTENTE = 18;
    public static final int JUGADOR_Y_PERSONAJE_EN_OTROS_SITIOS = 19;
    public static final int JUGADOR_SIN_OBJETO = 20;
    public static final int HABITACION_NO_EXISTE = 21;
    public static final int TIPO_PERSONA_INCORRECTA = 22;
    public static final int TIPO_ENTERO_INCORRECTO = 23;
    public static final int OBJETO_NO_DISPONIBLE = 24;
    public static final int DNI_NO_VALIDO = 25;
    private final List<String> mensajes = Arrays.asList(
            "< ERROR 001: Operació incorrecta >",
           " < ERROR 002: Wrong number of arguments >",
            "< ERROR 003: Worker already exists >",
            "< ERROR 004: ROOM already exists >",
            "< ERROR 005: Estudi incorrecte >",
            "< ERROR 006: Wrong service >",
            "< ERROR 007: habilidad incorrecta >",
            "< ERROR 008: There isn't a customer in the ROOM >",
            "< ERROR 009: Tipus incorrecte >",
            "< ERROR 010: Dada incorrecta >",
            "< ERROR 011: Numero introduit menor a 1 >",
            "< ERROR 012: Color incorrecte >",
            "< ERROR 013: Ús fora de rang >",
            "< ERROR 014: No existeix un jugador amb aquest nom >",
            "< ERROR 015: There isn't any room available. Customer not asigned. You have lost 100€ >",
            "< ERROR 016: El jugador ja té l'objecte >",
            "< ERROR 017: El jugador no té suficients calacoins per a la compra >",
            "< ERROR 018: El jugador no està al BAR >",
            "< ERROR 019: Customer already exists >",
            "< ERROR 020: El jugador i el personatge no estan al mateix lloc >",
            "< ERROR 021: El jugador no té l'objecte >",
            "< ERROR 022: There isn't room with this number >",
            "< ERROR 023: Tipus de persona incorrecte >",
            "< ERROR 024. Numero en formato incorrecto. Introduce un numero entero >",
            "< ERROR 025. El objeto no está disponible >",
            "< ERROR 026. El  DNI introdouit no es valid. Ha de tenir 8 caracters númerics >"
    );

    private final int code;


    public ComandaExeption(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return mensajes.get(code);
    }
}
