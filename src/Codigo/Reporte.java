/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Reporte {

    OutputStream file;
    Document documento;
    PdfPTable tabla;

    public Reporte(String numero) {
        try {
            file = new FileOutputStream(new File("REPORTES\\Reporte"+numero+".pdf"));
            documento = new Document();
            PdfWriter.getInstance(documento, file);
            documento.open();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void crearTabla(int columnas) {
        try {
            tabla = new PdfPTable(columnas);
            tabla.setWidthPercentage(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarCelda(Phrase celda) {
        try {

            
            tabla.addCell(celda);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void agregarTabla(PdfPTable tabla) {
        try {
            tabla.addCell(tabla);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarTabla() {
        try {
            documento.add(tabla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearParrafo(String parrafo) {
        try {
            documento.add(new Paragraph(parrafo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearParrafoConFuente(Paragraph paragraph) {
        try {
            documento.add(paragraph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ponerImagenSAI() {
        try {
            Image foto = Image.getInstance("recursos//btnSAI4_red.GIF");
            foto.scaleToFit(100, 100);
            foto.setAlignment(Chunk.ALIGN_BOTTOM);
            documento.add(foto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarReporte() {

        try {
            documento.close();
            file.close();
          
            System.out.print("REPORTE GENERADO Y CERRADO");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirReportePDF() {
        try {
            File path = new File("Reportes\\Reporte.pdf");
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

   
}
