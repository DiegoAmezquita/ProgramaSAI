package Interfaz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import Codigo.Datos;
import java.awt.Font;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vivis
 */
public class DatosCellRenderer extends JLabel implements ListCellRenderer {


    public DatosCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Datos entry = (Datos) value;
        setText(entry.getNombre());
        setFont(new Font("Arial", java.awt.Font.ITALIC, 12));




        if (isSelected) {
            setBackground(Color.RED);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
}










