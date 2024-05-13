/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lectorExcel;

import dao.Dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author admin
 */
public class ExcelLoader {

    public ExcelLoader() {
    }
    
    
    public void loadTabla(){
        
        try (FileInputStream fis = new FileInputStream(new File("." +File.separator+"excel"+File.separator+"bd.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = (Sheet) workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();

            // Saltar la fila de cabecera
            rowIterator.next();

            Dao dao = new Dao();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
//                String id = row.getCell(0).getStringCellValue();
                String nombre = row.getCell(2).getStringCellValue();
                String direccion = row.getCell(4).getStringCellValue();
                String cp = row.getCell(5).getStringCellValue();
                String ciudad = row.getCell(6).getStringCellValue();
                String comunidad = row.getCell(7).getStringCellValue();
                String telefono = row.getCell(9).getStringCellValue();
                String email = row.getCell(10).getStringCellValue();
                String homePage = row.getCell(13).getStringCellValue();
                String contacto = row.getCell(15).getStringCellValue();

                dao.insertEmpresa(telefono, nombre, email, contacto, direccion, cp,ciudad,comunidad,homePage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
