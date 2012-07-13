/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Codigo.Evento;
import DAO.DAOEvento;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class HiloRevizarCorreo extends Thread {

    int contador = 0;
    private JMenu menu;
    private int codLog;
    private DAOEvento daoEvento;
    private ArrayList<Evento> arrayEventos;
    private String usuario = "";
    private int contadorActual = 0;

    public HiloRevizarCorreo(JMenu menu, int codLog) {
        this.menu = menu;
        this.codLog = codLog;
        daoEvento = new DAOEvento();
        arrayEventos = new ArrayList<Evento>();
        usuario = menu.getText();
    }

    public void run() {
        try {
            arrayEventos = daoEvento.consultarPorUsuario(codLog);
            for (int i = 0; i < arrayEventos.size(); i++) {
                if (!arrayEventos.get(i).isLeido()) {
                    contador++;

                }
            }
            contadorActual = contador;
            menu.setText(usuario + " " + contador + " Mensajes sin leer");
            if (contador != 0) {
                JOptionPane.showMessageDialog(null, "Tiene " + contador + " Mensajes sin leer");
            }

            while (true) {
                sleep(1000000);
                contador = 0;
                arrayEventos = daoEvento.consultarPorUsuario(codLog);
                for (int i = 0; i < arrayEventos.size(); i++) {
                    if (!arrayEventos.get(i).isLeido()) {
                        contador++;
                    }
                }
                if (contador == 0) {
                    menu.setText(usuario);
                } else {
                    menu.setText(usuario + " " + contador + " Mensajes sin leer");
                    if (contador != contadorActual) {
                        JOptionPane.showMessageDialog(null, "Nuevo Mensaje");
                        contadorActual = contador;
                    }
                }
                contador++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloRevizarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
