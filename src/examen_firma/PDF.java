/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_firma;

/**
 *
 * @author tonis
 */

import com.itextpdf.text.DocumentException;
import com.sun.awt.AWTUtilities;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PDF {
    
    public void generar(String nombre, String edad, String mensaje) throws FileNotFoundException, DocumentException{
        FileOutputStream archivo = new FileOutputStream("Archivo"+".pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        
        Paragraph parrafo = new Paragraph("Datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("Nombre: " + nombre));
        documento.add(new Paragraph("Edad: " + edad));
        documento.add(new Paragraph("Mensaje: " + mensaje));
        
        documento.close();
        JOptionPane.showMessageDialog(null, "Archivo PDF creado exitosamente :)");
    }
    
    public void leer(File archivo) throws FileNotFoundException, IOException{
        byte[] buffer = new byte[1000];
        byte[] bufferCifrado;


        //vamos a generar el archivo

        FileInputStream in = new FileInputStream("Archivo.pdf");
        FileOutputStream out = new FileOutputStream("ArchivoModified"+".pdf");

        //tenemos que empezar por la lectura del archivo y converlo en bytes

        int bytesleidos = in.read(buffer, 0, 1000);
        while (bytesleidos != -1) {
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }

        //cuando termine de leer el archivo
        bufferCifrado = cifrado.doFinal();
        //escribir el archivo de salida
        out.write(bufferCifrado);

        in.close();
        out.close();
    }
    
}
