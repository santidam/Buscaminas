package com.mycompany.crm.persistencia;
import java.io.*;

public class FileManager {

    private File directory;
    private String pathFile;

    public FileManager(String folder, String file){
        this.directory = new File(folder);
        this.pathFile = folder + File.separator + file;
    }

    public void escribir(Object obj) {

        if (!this.directory.exists()) {
            this.directory.mkdir();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true));
            bw.write(obj.toString());
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println("ERROR. El fichero no existe");
        }

    }

    public void leer(){
        String linea;
        if(this.directory.exists()){
            if(new File(this.pathFile).exists()){
                try{
                    BufferedReader br = new BufferedReader(new FileReader(this.pathFile));
                    do {
                        linea = br.readLine();
                        toObject(linea);
                    }while(linea != null);


                }catch(IOException e){
                    System.out.println("ERROR. El fichero no existe");
                }
            }else{
                System.out.println("ERROR. El fichero no existe");
            }

        }else{
            System.out.println("ERROR. El directorio '" + this.directory.getName() + "' no existe");
        }
    }

    public void toObject(String linea){

    }

}

