package org.example;
public class Main {
    public static void validarCorreoElectronico(String email) {

        if (email.isEmpty()) {
            System.out.println("El correo electrónico no puede estar vacío.");
        } else {
            if (email.startsWith("-") || email.endsWith("-") || email.startsWith(".") || email.endsWith(".") || email.contains("..")) {
                System.out.println("El correo electrónico no puede comenzar o terminar con '-' o '.' ni contener '..'");
            } else {
                if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@")) {
                    System.out.println("El correo electrónico debe contener un solo '@'.");
                } else {
                    String[] emailSeparado = email.split("@");
                    if (emailSeparado[0].isEmpty() || emailSeparado[1].isEmpty()) {
                        System.out.println("El correo electrónico debe tener caracteres antes y después de '@'.");
                    } else {
                        String[] dominioEmail = emailSeparado[1].split("\\.");
                        if (dominioEmail.length < 2) {
                            System.out.println("El dominio del correo electrónico debe contener '.'.");
                        } else {
                            if (dominioEmail[0].length() > 63 || dominioEmail[1].length() > 63) {
                                System.out.println("Ninguna parte del dominio del correo electrónico puede exceder los 63 caracteres.");
                            } else {
                                if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+$")) {
                                    System.out.println("El correo electrónico solo puede contener caracteres alfanuméricos y . _ -");
                                } else {
                                    if (emailSeparado[0].length() < 1 || emailSeparado[0].length() > 64) {
                                        System.out.println("ERROR 8: La parte local del correo electrónico (antes de @) debe tener entre 1 y 64 caracteres.");
                                    } else {
                                        if (emailSeparado[1].length() < 1 || emailSeparado[1].length() > 64) {
                                            System.out.println("ERROR 9: La parte del dominio del correo electrónico (después de @) debe tener entre 1 y 64 caracteres.");
                                        } else {
                                            System.out.println("El correo electrónico es válido.");
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