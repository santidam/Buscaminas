package com.mycompany.crm.entity;

public class Client {

    private String nombre;
    private String apellidos;
    private String email;
    private String phoneNumber;

    public Client(String nombre, String apellidos, String email, String phoneNumber) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenNumber(String phonenNumber) {
        this.phoneNumber = phonenNumber;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos(){ return apellidos;}

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return " nombre= " + nombre + '\n' +
                ", apellidos= " + apellidos + '\n' +
                ", email= " + email + '\n' +
                ", phoneNumber= " + phoneNumber + '\n';
    }
}
