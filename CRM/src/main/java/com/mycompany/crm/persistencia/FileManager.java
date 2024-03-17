package com.mycompany.crm.persistencia;

import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.entity.Empleado;
import com.mycompany.crm.utils.CastData;
import com.mycompany.crm.validator.Validations;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private String folder;
    private String fileNameClientes;
    private String fileNameEmpleados;
    private String pathFileClientes;
    private String pathFileEmpleados;
    private File folderFile;

    public FileManager() {
        folder = "." +File.separator+"dades";
        fileNameClientes = "clientes.txt";
        fileNameEmpleados = "empleados.txt";
        pathFileClientes = folder + File.separator + fileNameClientes;
        pathFileEmpleados = folder + File.separator + fileNameEmpleados;

        folderFile = new File(folder);
        if (!folderFile.exists()){
            folderFile.mkdir();
        }

    }

    public void writeCliente(Cliente c){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFileClientes, false));
            bw.write((c.toString()));
            bw.newLine();
            bw.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public ArrayList<Cliente> readClient() {
        // Ejemplo de lectura pero falta implementar que lea esas líneas y las
        // cargue en un ArrayList<CoffeShop>
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        File f = new File(pathFileClientes);
        if (!f.exists()) {
            return listaClientes;
            // aquí devolverías el arrayList de coffeshop vacío, ya que si no existe la carpeta no hay datos por leer
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Aquí en realidad leeríais la línea y harías split, etc para crear los objetos y añadirlos al ArrayList que retornariais
                    String[] parameters = linea.split(";");
                    Cliente c = readClient(parameters);
                    if (c != null) {
                        listaClientes.add(c);
                    }
                }
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

//        Gestor.setListaCasa(listaClientes);
        return listaClientes;
    }


    public Cliente readClient(String[] parameters){
        if (parameters.length==4){
            String number = parameters[0];
            String name = parameters[1];
            String lastname = parameters[2];
            String email = parameters[3];

            Cliente c = new Cliente(number,name,lastname,email);
            return c;
        }
        return null;
    }

    public void writeEmpleado(Empleado e){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFileEmpleados, false));
            bw.write((e.toString()));
            bw.newLine();
            bw.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Empleado> readEmpleado() {
        // Ejemplo de lectura pero falta implementar que lea esas líneas y las
        // cargue en un ArrayList<CoffeShop>
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        File f = new File(pathFileEmpleados);
        if (!f.exists()) {
            return listaEmpleados;
            // aquí devolverías el arrayList de coffeshop vacío, ya que si no existe la carpeta no hay datos por leer
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Aquí en realidad leeríais la línea y harías split, etc para crear los objetos y añadirlos al ArrayList que retornariais
                    String[] parameters = linea.split(";");
                    Empleado e = readEmpleado(parameters);
                    if (e != null) {
                        listaEmpleados.add(e);
                    }
                }
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

//        Gestor.setListaCasa(listaClientes);
        return listaEmpleados;
    }
    public Empleado readEmpleado(String[] parameters) {
        if (parameters.length == 5) {
            String dni = parameters[0];
            String name = parameters[1];
            String lastname = parameters[2];
            String telefono = parameters[3];
            String email = parameters[4];

            Empleado e = new Empleado(dni, name, lastname, telefono, email);
            return e;
        }
        return null;
    }
}

    //leer
    //Un fichero para clientes; Un fichero para empleados
    //escribir

