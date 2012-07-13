/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Diego
 */
//public class Imprimir extends JFrame implements ActionListener {
public class Imprimir extends JFrame implements Printable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hojasUsadas = 0;
    private String nombreCliente = "";
    private String documentoCliente = "";
    private String numeroOrden = "";
    private String fechaIngreso = "";
    private ArrayList<Elemento> listaElementos;
    private String descripcionOrden="";
    JButton boton;
    int inicio;
    int tamanioListaElementos;
    int numeroReportes;
    int elementosPorOrden;
    int ultimaHoja;

    public Imprimir() {
      

    }

    public void iniciarValores() {
        inicio = 0;
        ultimaHoja = -1;
        tamanioListaElementos = listaElementos.size();
        elementosPorOrden = 8;
        numeroReportes = tamanioListaElementos / elementosPorOrden;
        if ((tamanioListaElementos % elementosPorOrden) != 0) {
            numeroReportes = numeroReportes + 1;
        }
    }

   
   

    public int print(Graphics g, PageFormat f, int pageIndex) {     
        // System.out.println("NUMERO DE ELEMENTOS " + listaElementos.size() + " NUMERO PAGINAS: " + hojasUsadas);
        if (pageIndex < hojasUsadas) {
            if (pageIndex == ultimaHoja) {
                inicio = inicio - 16;
            }         
            int posicionY = 50;
            int nReportes = 2;
            if(listaElementos.size()>8){
                nReportes=2;
            }else{
                nReportes=1;
            }
            for (int i = 0; i < nReportes; i++) {
              
                g.setColor(Color.black);
                g.setFont(new Font("arial", 0, 13));
                g.drawString("Soluciones Automatizadas Integrales SAI Ltda.", 10, posicionY);
                posicionY += 20;
                g.drawString("NIT. 800.171.127 - 1 RÉGIMEN COMÚN", 10, posicionY);
                posicionY += 20;
                g.setFont(new Font("arial", 0, 13));
                g.drawString("Numero de Orden No.: " + numeroOrden, 10, posicionY);
                posicionY += 20;
                g.drawString("Fecha Ingreso: " + fechaIngreso, 10, posicionY);
                posicionY += 20;
                g.drawString("Cliente: " + nombreCliente + " Documento: " + documentoCliente, 10, posicionY);
                posicionY += 20;
                g.drawString("Descripción", 10, posicionY);
                posicionY += 20;
                g.drawString(descripcionOrden, 10, posicionY);
                posicionY += 20;
                if(inicio<0){
                    inicio=0;
                }
                for (int j = inicio; j < (inicio + elementosPorOrden); j++) {
                    if (j < listaElementos.size()) {
                        Elemento ele = listaElementos.get(j);                     
                        g.drawString(j + " " + ele.getTipo() + " "
                                + ele.getMarca() + "    "
                                + ele.getModelo() + "    "
                                + ele.getSerial() + "    "
                                + ele.getDescripcion(), 10, posicionY);
                        posicionY += 20;
                    }
                }

                if (posicionY < 400) {
                    posicionY = 400;

                } else {
                    posicionY = 50;
                }
                inicio = inicio + 8;

            }
            ultimaHoja = pageIndex;
         

            return PAGE_EXISTS;
        } else if (pageIndex >= hojasUsadas) {
            return NO_SUCH_PAGE;
        }
        return NO_SUCH_PAGE;


    }

    public void escribirRenglon(String texto) {
    }

    public void mostrarPrevio(PageFormat f) {
        JFrame visualizacion = new JFrame();
        visualizacion.setSize((int) f.getImageableWidth(), (int) f.getImageableHeight());
        visualizacion.setVisible(true);


    }


    public void imprimir(){
        PrinterJob job = PrinterJob.getPrinterJob();

            job.setPrintable(this);

            System.out.println("Información job: " + job.toString());
            if (job.printDialog()) {
                try {
                    iniciarValores();
                    hojasUsadas = (listaElementos.size() / 16) + 1;
                    job.print();
                } catch (PrinterException a) {
                    System.out.println("Error de impresión: " + a);
                }
            }
    }


   

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public ArrayList<Elemento> getListaElementos() {
        return listaElementos;
    }

    public void setListaElementos(ArrayList<Elemento> listaElementos) {
        this.listaElementos = listaElementos;
    }

    public String getDescripcionOrden() {
        return descripcionOrden;
    }

    public void setDescripcionOrden(String descripcionOrden) {
        this.descripcionOrden = descripcionOrden;
    }

}
