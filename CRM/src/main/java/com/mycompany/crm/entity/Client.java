package com.mycompany.crm.entity;

public class Client {

    private String dni;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String phonenNumber;

    public Client(String dni, String nombre, String primerApellido, String segundoApellido, String email, String phonenNumber) {
        this.dni = dni;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.phonenNumber = phonenNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenNumber(String phonenNumber) {
        this.phonenNumber = phonenNumber;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenNumber() {
        return phonenNumber;
    }
}
