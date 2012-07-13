/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazBuzon;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

/**
 *
 * @author Diego
 */
public class PanelActivity extends JPanel {

    JButton buttonGuardar;
    JButton buttonCancelar;
    JButton buttonNueva;
    private JList listActivities;
    private DefaultListModel modelListActivities;
    private JScrollPane scrollPaneActivities;
    private JLabel labelDescriptionActivity;
    private JTextArea areaDescriptionActivity;
    private JScrollPane scrollPaneDescripcionActivity;
    private JLabel labelFechaActividad;
    private JTextField fieldFechaActividad;

    public PanelActivity(FrameMain frameMain) {
        setLayout(null);





        modelListActivities = new DefaultListModel();
        listActivities = new JList(modelListActivities);
        listActivities.setCellRenderer(new DatosCellRenderer());
        scrollPaneActivities = new JScrollPane(listActivities);
        scrollPaneActivities.setBounds(300, 20, 270, 240);
        listActivities.setName("listActivities");
        listActivities.addListSelectionListener(frameMain);
        add(scrollPaneActivities);


        labelFechaActividad = new JLabel("Fecha:");
        labelFechaActividad.setBounds(20, 20, 210, 30);
        add(labelFechaActividad);

        fieldFechaActividad = new JTextField();
        fieldFechaActividad.setBounds(20, 50, 210, 30);
        fieldFechaActividad.setEditable(false);
        add(fieldFechaActividad);


        labelDescriptionActivity = new JLabel("Descripción Actividad");
        labelDescriptionActivity.setBounds(20, 85, 210, 30);
        add(labelDescriptionActivity);

        areaDescriptionActivity = new JTextArea();
        scrollPaneDescripcionActivity = new JScrollPane(areaDescriptionActivity);
        scrollPaneDescripcionActivity.setBounds(20, 115, 210, 70);
        areaDescriptionActivity.setEditable(false);
        add(scrollPaneDescripcionActivity);







        buttonNueva = new JButton("NUEVA");
        buttonNueva.setBounds(20, 200, 230, 30);
        buttonNueva.addActionListener(frameMain);
        buttonNueva.setActionCommand("NUEVAACTIVIDAD");
        add(buttonNueva);

        buttonGuardar = new JButton("GUARDAR");
        buttonGuardar.setBounds(20, 240, 100, 30);
        buttonGuardar.addActionListener(frameMain);
        buttonGuardar.setActionCommand("GUARDARACTIVIDAD");
        add(buttonGuardar);

        buttonCancelar = new JButton("CANCELAR");
        buttonCancelar.setBounds(150, 240, 100, 30);
        buttonCancelar.addActionListener(frameMain);
        buttonCancelar.setActionCommand("CANCELARACTIVIDAD");
        add(buttonCancelar);
        bloquearBotones();
    }

    public void habilitarCampos(){
        areaDescriptionActivity.setEditable(true);
    }
    public void deshabilitarCampos(){
        areaDescriptionActivity.setEditable(false);
    }

     public void bloquearBotones(){
         buttonNueva.setEnabled(false);
        buttonCancelar.setEnabled(false);
        buttonGuardar.setEnabled(false);
    }
    public void desbloquearBotones(){
        buttonNueva.setEnabled(true);
        buttonCancelar.setEnabled(true);
        buttonGuardar.setEnabled(true);
    }

    public void limpiarCampo(){
        areaDescriptionActivity.setText("");
    }

    public JTextArea getAreaDescriptionActivity() {
        return areaDescriptionActivity;
    }

    public void setAreaDescriptionActivity(JTextArea areaDescriptionActivity) {
        this.areaDescriptionActivity = areaDescriptionActivity;
    }

    public DefaultListModel getModelListActivities() {
        return modelListActivities;
    }

    public void setModelListActivities(DefaultListModel modelListActivities) {
        this.modelListActivities = modelListActivities;
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

    public JButton getButtonNueva() {
        return buttonNueva;
    }

    public void setButtonNueva(JButton buttonNueva) {
        this.buttonNueva = buttonNueva;
    }

    public JTextField getFieldFechaActividad() {
        return fieldFechaActividad;
    }

    public void setFieldFechaActividad(JTextField fieldFechaActividad) {
        this.fieldFechaActividad = fieldFechaActividad;
    }

    public JLabel getLabelDescriptionActivity() {
        return labelDescriptionActivity;
    }

    public void setLabelDescriptionActivity(JLabel labelDescriptionActivity) {
        this.labelDescriptionActivity = labelDescriptionActivity;
    }

    public JLabel getLabelFechaActividad() {
        return labelFechaActividad;
    }

    public void setLabelFechaActividad(JLabel labelFechaActividad) {
        this.labelFechaActividad = labelFechaActividad;
    }

    public JList getListActivities() {
        return listActivities;
    }

    public void setListActivities(JList listActivities) {
        this.listActivities = listActivities;
    }

    public JScrollPane getScrollPaneActivities() {
        return scrollPaneActivities;
    }

    public void setScrollPaneActivities(JScrollPane scrollPaneActivities) {
        this.scrollPaneActivities = scrollPaneActivities;
    }

    public JScrollPane getScrollPaneDescripcionActivity() {
        return scrollPaneDescripcionActivity;
    }

    public void setScrollPaneDescripcionActivity(JScrollPane scrollPaneDescripcionActivity) {
        this.scrollPaneDescripcionActivity = scrollPaneDescripcionActivity;
    }




}
