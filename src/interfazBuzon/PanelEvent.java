/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazBuzon;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Interfaz.FrameMain;

/**
 *
 * @author Diego
 */
public class PanelEvent extends JPanel {

    private JLabel labelUsers;
    private JComboBox comboUsers;
    private JTextArea areaDescriptionNewEven;
    private JScrollPane scrollPaneDescripcionNewEven;
    private JLabel labelDescriptionNewEven;
    private JButton buttonCancelar;
    private JButton buttonGuardar;
    private JLabel labelNextEvent;
    private JComboBox comboNextEvent;

    public PanelEvent(FrameMain frameMain) {
        setLayout(null);

        labelUsers = new JLabel("Usuarios");
        labelUsers.setBounds(20, 20, 100, 30);
        add(labelUsers);

        comboUsers = new JComboBox();
        comboUsers.setBounds(140, 20, 200, 30);
        add(comboUsers);

        labelNextEvent = new JLabel("Siguiente Evento");
        labelNextEvent.setBounds(20, 55, 100, 30);
        add(labelNextEvent);

        comboNextEvent = new JComboBox();
        comboNextEvent.setBounds(140, 55, 200, 30);
        add(comboNextEvent);

        labelDescriptionNewEven = new JLabel("Motivo Asignacion:");
        labelDescriptionNewEven.setBounds(20, 90, 150, 30);
        add(labelDescriptionNewEven);

        areaDescriptionNewEven = new JTextArea();
        scrollPaneDescripcionNewEven = new JScrollPane(areaDescriptionNewEven);
        scrollPaneDescripcionNewEven.setBounds(140, 90, 200, 50);
        add(scrollPaneDescripcionNewEven);

        buttonGuardar = new JButton("GUARDAR");
        buttonGuardar.setBounds(20, 200, 100, 30);
        buttonGuardar.addActionListener(frameMain);
        buttonGuardar.setActionCommand("GUARDAREVENTOBUZON");
        add(buttonGuardar);

        buttonCancelar = new JButton("CANCELAR");
        buttonCancelar.setBounds(150, 200, 100, 30);
        buttonCancelar.addActionListener(frameMain);
        buttonCancelar.setActionCommand("CANCELAREVENTO");
        add(buttonCancelar);
        bloquearBotones();

    }

    public void limpiarCampos() {
        comboNextEvent.setSelectedIndex(0);
        comboUsers.setSelectedIndex(0);
        areaDescriptionNewEven.setText("");

    }

    public void bloquearBotones(){
        buttonCancelar.setEnabled(false);
        buttonGuardar.setEnabled(false);
    }
    public void desbloquearBotones(){
        buttonCancelar.setEnabled(true);
        buttonGuardar.setEnabled(true);
    }

    public JTextArea getAreaDescriptionNewEven() {
        return areaDescriptionNewEven;
    }

    public void setAreaDescriptionNewEven(JTextArea areaDescriptionNewEven) {
        this.areaDescriptionNewEven = areaDescriptionNewEven;
    }

    public JButton getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(JButton buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public JButton getButtonGuardar() {
        return buttonGuardar;
    }

    public void setButtonGuardar(JButton buttonGuardar) {
        this.buttonGuardar = buttonGuardar;
    }

    public JComboBox getComboNextEvent() {
        return comboNextEvent;
    }

    public void setComboNextEvent(JComboBox comboNextEvent) {
        this.comboNextEvent = comboNextEvent;
    }

    public JComboBox getComboUsers() {
        return comboUsers;
    }

    public void setComboUsers(JComboBox comboUsers) {
        this.comboUsers = comboUsers;
    }

    public JLabel getLabelDescriptionNewEven() {
        return labelDescriptionNewEven;
    }

    public void setLabelDescriptionNewEven(JLabel labelDescriptionNewEven) {
        this.labelDescriptionNewEven = labelDescriptionNewEven;
    }

    public JLabel getLabelNextEvent() {
        return labelNextEvent;
    }

    public void setLabelNextEvent(JLabel labelNextEvent) {
        this.labelNextEvent = labelNextEvent;
    }

    public JLabel getLabelUsers() {
        return labelUsers;
    }

    public void setLabelUsers(JLabel labelUsers) {
        this.labelUsers = labelUsers;
    }

    public JScrollPane getScrollPaneDescripcionNewEven() {
        return scrollPaneDescripcionNewEven;
    }

    public void setScrollPaneDescripcionNewEven(JScrollPane scrollPaneDescripcionNewEven) {
        this.scrollPaneDescripcionNewEven = scrollPaneDescripcionNewEven;
    }


}
