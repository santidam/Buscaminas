package com.mycompany.crm.persistencia;
import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.entity.Empleado;

import java.io.*;
import java.util.ArrayList;

import com.mycompany.crm.entity.Cliente;
import com.mycompany.crm.entity.Empleado;
import com.mycompany.crm.utils.CastData;
import com.mycompany.crm.validator.Validations;

import java.io.*;
import java.util.ArrayList;

public class FileManager {




    //leer
    //Un fichero para clientes; Un fichero para empleados
    //escribir


    private File directory;
    private String pathFile;

    public FileManager(String folder, String file) {
        this.directory = new File(folder);
        this.pathFile = folder + File.separator + file;
    }

    public void escribir(Object obj, boolean esCliente) {

        if (!this.directory.exists()) {
            this.directory.mkdir();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true));
            if (esCliente){
                Cliente c = (Cliente) obj;
                bw.write(c.toString());
                bw.newLine();
            }else{
                Empleado e = (Empleado) obj;
                bw.write(e.toString());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("ERROR. El fichero no existe");
        }
    }

    public void sobreEscribir(ArrayList<Object> objetos, boolean esCliente) {

        if (!this.directory.exists()) {
            this.directory.mkdir();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, false));
            for (Object o: objetos) {
                if (!esCliente) {
                    Empleado e = (Empleado) o;
                    bw.write(e.toString());
                }else {
                    Cliente c = (Cliente) o;
                    bw.write(c.toString());
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("ERROR. El fichero no existe");
        }
    }

    public ArrayList<Object> leer(boolean esCliente) {
        String linea;
        ArrayList<Object> objects = new ArrayList<>();
        Object obj;
        if (this.directory.exists()) {
            if (new File(this.pathFile).exists()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(this.pathFile));
                    while((linea = br.readLine()) != null){
                        obj = toObject(linea, esCliente);
                        objects.add(obj);
                    }
                    br.close();

                } catch (IOException e) {
                    System.out.println("ERROR. El fichero no existe");
                }
            } else {
                System.out.println("ERROR. El fichero no existe");
            }

        }
        return objects;
    }

    private Object toObject(String linea, boolean esCliente) {
        String[] datos = linea.split(";");
        Object obj;
        if (esCliente) {
            obj = new Cliente(datos[0], datos[1], datos[2], datos[3]);

        } else {
            obj = new Empleado(datos[0], datos[1], datos[2]);
        }
        return obj;
    }

}


