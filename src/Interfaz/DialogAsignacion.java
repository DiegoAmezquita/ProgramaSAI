/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Codigo.Evento;
import Codigo.Persona;
import Codigo.Varios;
import DAO.DAOEvento;
import DAO.DAOLogin;
import DAO.DAOvarios;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Diego
 */
public class DialogAsignacion extends JDialog {

    private JLabel labelUsers;
    private JComboBox comboUsers;
    private JComboBox comboNextEvent;
    private JLabel labelNextEvent;
    private JLabel labelDescriptionNewEven;
    private JScrollPane scrollPaneDescripcionNewEven;
    private JTextArea areaDescriptionNewEven;
    private JButton buttonCancelar;
    private JButton buttonGuardar;
    private DAOLogin daoLogin;
    private DAOvarios daoVarios;
    private DAOEvento daoEvento;
    private int codItemSelected;

    public DialogAsignacion(FrameMain frameMain) {
        setSize(500, 280);
        setLayout(null);
        setLocationRelativeTo(null);


        labelUsers = new JLabel("Usuarios");
        labelUsers.setBounds(20, 50, 100, 30);
        add(labelUsers);

        comboUsers = new JComboBox();
        comboUsers.setBounds(200, 50, 200, 30);
        add(comboUsers);



        labelNextEvent = new JLabel("Siguiente Evento");
        labelNextEvent.setBounds(20, 90, 100, 30);
        add(labelNextEvent);

        comboNextEvent = new JComboBox();
        comboNextEvent.setBounds(200, 90, 200, 30);
        add(comboNextEvent);

        labelDescriptionNewEven = new JLabel("Motivo Asignacion:");
        labelDescriptionNewEven.setBounds(20, 130, 150, 30);
        add(labelDescriptionNewEven);

        areaDescriptionNewEven = new JTextArea();
        scrollPaneDescripcionNewEven = new JScrollPane(areaDescriptionNewEven);
        scrollPaneDescripcionNewEven.setBounds(200, 130, 200, 50);
        add(scrollPaneDescripcionNewEven);

        buttonGuardar = new JButton("GUARDAR");
        buttonGuardar.setBounds(20, 190, 200, 30);
        buttonGuardar.addActionListener(frameMain);
        buttonGuardar.setActionCommand("GUARDAREVENTO");
        add(buttonGuardar);

        buttonCancelar = new JButton("CANCELAR");
        buttonCancelar.setBounds(240, 190, 200, 30);
        buttonCancelar.addActionListener(frameMain);
        buttonCancelar.setActionCommand("CANCELAREVENTO");
        add(buttonCancelar);

        
        daoLogin = new DAOLogin();
        daoVarios = new DAOvarios();
        daoEvento = new DAOEvento();
        cargarUsuarios();
        cargarTiposEventos();

    }

    public void cargarUsuarios() {
        ArrayList<Persona> listaUsuarios = daoLogin.retornarTodosUsuarios();
        comboUsers.removeAllItems();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            comboUsers.addItem(listaUsuarios.get(i).getNombre());
        }
    }

    public void cargarTiposEventos() {
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Tipo Evento");
        comboNextEvent.removeAllItems();
        for (int i = 0; i < listaVarios.size(); i++) {
            comboNextEvent.addItem(listaVarios.get(i).getnombreVario());
        }
    }

    public void limpiarCampos(){
        comboUsers.setSelectedIndex(0);
        comboNextEvent.setSelectedIndex(0);
        areaDescriptionNewEven.setText("");
    }

    public void crearEvento() {
        Evento evento = new Evento();
        int codigoElemento =codItemSelected;
        evento.setCodigoElemento(codigoElemento);
        evento.setDescripcion(areaDescriptionNewEven.getText());
        String[] fecha = daoVarios.retornarFechaBD();
        evento.setFechaCreacion(fecha[2] + "/" + fecha[1] + "/" + fecha[0] + " " + fecha[3]);
        evento.setLeido(false);
        evento.setResponsableEven(daoLogin.buscarLoginPorPersona(comboUsers.getSelectedItem() + ""));
        evento.setSiguienteEven(0);
        evento.setTipo((String) comboNextEvent.getSelectedItem());
        daoEvento.agregarEventoSiguiente(codigoElemento);
        daoEvento.insert(evento);
        JOptionPane.showMessageDialog(null, "ITEM ASIGNADO");
        this.dispose();
    }

    public void cancelarNuevoEvento(){
        this.dispose();

    }

   

    public int getCodItemSelected() {
        return codItemSelected;
    }

    public void setCodItemSelected(int codItemSelected) {
        this.codItemSelected = codItemSelected;
    }





}
