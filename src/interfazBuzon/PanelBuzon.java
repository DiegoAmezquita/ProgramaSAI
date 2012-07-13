/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazBuzon;

import Codigo.Actividad;
import Codigo.Datos;
import Codigo.Elemento;
import Codigo.Evento;
import Codigo.Persona;
import Codigo.Varios;
import DAO.DAOActividad;
import DAO.DAOElemento;
import DAO.DAOEvento;
import DAO.DAOLogin;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego
 */
public class PanelBuzon extends JPanel {

    private TitledBorder rotulo;
    private JList listMail;
    private DefaultListModel modelListMail;
    private JScrollPane scrollPaneMail;
    private JTextField fieldBrandItem;
    private JTextField fieldTypeItem;
    private JTextField fieldStateItem;
    private JTextField fieldModelItem;
    private JTextField fieldSerialItem;
    private JTextArea areaDescriptionItem;
    private JScrollPane scrollPaneDescripcionItem;
    private JTextArea areaDescriptionEven;
    private JScrollPane scrollPaneDescripcionEven;
    private JLabel labelDescriptionEven;
    private JLabel labelBrand;
    private JLabel labelType;
    private JLabel labelSerial;
    private JLabel labelDescripcionItem;
    private JLabel labelModel;
    private JLabel labelState;
    private DAOEvento daoEvento;
    private DAOElemento daoElemento;
    private DAOLogin daoLogin;
    private DAOvarios daoVarios;
    private DAOActividad daoActividad;
    private int codLog;
    private int codEventSelected;
    private PanelEvent panelEvent;
    private PanelActivity panelActivity;

    public PanelBuzon(FrameMain frameMain, int codLog) {
        setSize(1000, 600);
        rotulo = BorderFactory.createTitledBorder("BUZON MENSAJES");
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);
        setLayout(null);
        this.codLog = codLog;

        modelListMail = new DefaultListModel();
        listMail = new JList(modelListMail);
        listMail.setCellRenderer(new DatosCellRenderer());
        scrollPaneMail = new JScrollPane(listMail);
        scrollPaneMail.setBounds(20, 60, 960, 200);
        listMail.setName("listMail");
        listMail.addListSelectionListener(frameMain);
        add(scrollPaneMail);

        labelBrand = new JLabel("Marca:");
        labelBrand.setBounds(20, 300, 90, 30);
        add(labelBrand);

        labelType = new JLabel("Tipo:");
        labelType.setBounds(20, 335, 90, 30);
        add(labelType);

        labelState = new JLabel("Estado:");
        labelState.setBounds(20, 370, 90, 30);
        add(labelState);

        labelModel = new JLabel("Modelo:");
        labelModel.setBounds(20, 405, 90, 30);
        add(labelModel);

        labelSerial = new JLabel("Serial:");
        labelSerial.setBounds(20, 440, 90, 30);
        add(labelSerial);

        labelDescripcionItem = new JLabel("Descripción:");
        labelDescripcionItem.setBounds(20, 475, 90, 30);
        add(labelDescripcionItem);

        labelDescriptionEven = new JLabel("Motivo Evento:");
        labelDescriptionEven.setBounds(20, 530, 90, 30);
        add(labelDescriptionEven);

        fieldBrandItem = new JTextField();
        fieldBrandItem.setBounds(120, 300, 200, 30);
        add(fieldBrandItem);

        fieldTypeItem = new JTextField();
        fieldTypeItem.setBounds(120, 335, 200, 30);
        add(fieldTypeItem);

        fieldModelItem = new JTextField();
        fieldModelItem.setBounds(120, 370, 200, 30);
        add(fieldModelItem);

        fieldSerialItem = new JTextField();
        fieldSerialItem.setBounds(120, 405, 200, 30);
        add(fieldSerialItem);

        fieldStateItem = new JTextField();
        fieldStateItem.setBounds(120, 440, 200, 30);
        add(fieldStateItem);

        areaDescriptionItem = new JTextArea();
        scrollPaneDescripcionItem = new JScrollPane(areaDescriptionItem);
        scrollPaneDescripcionItem.setBounds(120, 475, 200, 50);
        add(scrollPaneDescripcionItem);

        areaDescriptionEven = new JTextArea();
        scrollPaneDescripcionEven = new JScrollPane(areaDescriptionEven);
        scrollPaneDescripcionEven.setBounds(120, 530, 200, 50);
        add(scrollPaneDescripcionEven);
        JTabbedPane tabbedPane;
        tabbedPane = new JTabbedPane();
        panelEvent = new PanelEvent(frameMain);
        panelActivity = new PanelActivity(frameMain);
        tabbedPane.add("Evento", panelEvent);
        tabbedPane.add("Actividades", panelActivity);
        tabbedPane.setBounds(350, 280, 600, 300);
        add(tabbedPane);

       

        fieldBrandItem.setEditable(false);
        fieldModelItem.setEditable(false);
        fieldSerialItem.setEditable(false);
        fieldStateItem.setEditable(false);
        fieldTypeItem.setEditable(false);
        areaDescriptionItem.setEditable(false);
        areaDescriptionEven.setEditable(false);




        daoEvento = new DAOEvento();
        daoElemento = new DAOElemento();
        daoLogin = new DAOLogin();
        daoVarios = new DAOvarios();
        daoActividad = new DAOActividad();

        cargarMensajes();
        //ocultarElementos();
          cargarUsuarios();
         cargarTiposEventos();
        //  bloquearBotones();


    }

    public void cargarMensajes() {
        modelListMail.clear();
        ArrayList<Evento> listEventos = daoEvento.consultarPorUsuario(codLog);
        for (int i = 0; i < listEventos.size(); i++) {
            Datos d = null;
            if (listEventos.get(i).isLeido()) {
                d = new Datos(listEventos.get(i).getCodigo(),
                        "--LEIDO--" + listEventos.get(i).getCodigoElemento() + " "
                        + listEventos.get(i).getDescripcion() + " "
                        + listEventos.get(i).getFechaCreacion() + " "
                        + listEventos.get(i).getTipo());
            } else {
                d = new Datos(listEventos.get(i).getCodigo(),
                        listEventos.get(i).getCodigoElemento() + " "
                        + listEventos.get(i).getDescripcion() + " "
                        + listEventos.get(i).getFechaCreacion() + " "
                        + listEventos.get(i).getTipo());
            }

            modelListMail.addElement(d);
        }
    }

    public void cargarEvento() {
        Evento even = daoEvento.consultarPorCodigo(codEventSelected);
        areaDescriptionEven.setText(even.getDescripcion());
        Elemento elemento = daoElemento.consultarCompleto(even.getCodigoElemento());
        fieldBrandItem.setText(elemento.getMarca());
        fieldModelItem.setText(elemento.getModelo());
        fieldSerialItem.setText(elemento.getSerial());
        fieldStateItem.setText(elemento.getEstado());
        fieldTypeItem.setText(elemento.getTipo());
        areaDescriptionItem.setText(elemento.getDescripcion());
        daoEvento.cambiarALeido(codEventSelected);
    }

   


    

    public void cargarInfoActividad(int codAct) {
        Actividad actividad = daoActividad.consultarPorCodigo(codAct);
        panelActivity.getFieldFechaActividad().setText(actividad.getFechaCreacion());
         panelActivity.getAreaDescriptionActivity().setText(actividad.getDescripcion());
    }

  

  

    

    public void crearEvento() {
        Evento evento = new Evento();
        System.out.println("codigo elemento: " + daoEvento.consultarPorCodigo(codEventSelected).getCodigoElemento());
        evento.setCodigoElemento(daoEvento.consultarPorCodigo(codEventSelected).getCodigoElemento());
        evento.setDescripcion(panelEvent.getAreaDescriptionNewEven().getText());
        String[] fecha = daoVarios.retornarFechaBD();
        evento.setFechaCreacion(fecha[2] + "/" + fecha[1] + "/" + fecha[0] + " " + fecha[3]);
        evento.setLeido(false);
        evento.setResponsableEven(daoLogin.buscarLoginPorPersona(panelEvent.getComboUsers().getSelectedItem() + ""));
        evento.setSiguienteEven(0);
        evento.setTipo((String) panelEvent.getComboNextEvent().getSelectedItem());
        daoEvento.agregarEventoSiguiente(codEventSelected);
        daoEvento.insert(evento);
    }

    public void cargarUsuarios() {
        ArrayList<Persona> listaUsuarios = daoLogin.retornarTodosUsuarios();
        panelEvent.getComboUsers().removeAllItems();
        System.out.println("PRUEBAAAAAAA " + listaUsuarios.size());
        for (int i = 0; i < listaUsuarios.size(); i++) {
             panelEvent.getComboUsers().addItem(listaUsuarios.get(i).getNombre());
        }
    }

    public void cargarTiposEventos() {
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Tipo Evento");
        panelEvent.getComboNextEvent().removeAllItems();
        for (int i = 0; i < listaVarios.size(); i++) {
            panelEvent.getComboNextEvent().addItem(listaVarios.get(i).getnombreVario());
        }
    }

    public void crearActividad() {
        Actividad actividad = new Actividad();
        actividad.setCodEle(daoEvento.consultarPorCodigo(codEventSelected).getCodigoElemento());
        actividad.setCodLog(codLog + "");
        actividad.setDescripcion(panelActivity.getAreaDescriptionActivity().getText());
        String[] fecha = daoVarios.retornarFechaBD();
        actividad.setFechaCreacion(fecha[2] + "/" + fecha[1] + "/" + fecha[0] + " " + fecha[3]);
        daoActividad.insert(actividad);

    }

    public void cargarActividades() {
        ArrayList<Actividad> listActividades = daoActividad.consultarPorElemento(daoEvento.consultarPorCodigo(codEventSelected).getCodigoElemento());
        panelActivity.getModelListActivities().clear();
        for (int i = 0; i < listActividades.size(); i++) {
            panelActivity.getModelListActivities().addElement(new Datos(listActividades.get(i).getCodAct(), listActividades.get(i).getCodLog() + " "
                    + listActividades.get(i).getFechaCreacion()));
        }
    }

    public int getCodEventSelected() {
        return codEventSelected;
    }

    public void setCodEventSelected(int codEventSelected) {
        this.codEventSelected = codEventSelected;
    }

    public int getCodLog() {
        return codLog;
    }

    public void setCodLog(int codLog) {
        this.codLog = codLog;
    }
    

    public PanelActivity getPanelActivity() {
        return panelActivity;
    }

    public void setPanelActivity(PanelActivity panelActivity) {
        this.panelActivity = panelActivity;
    }

    public PanelEvent getPanelEvent() {
        return panelEvent;
    }

    public void setPanelEvent(PanelEvent panelEvent) {
        this.panelEvent = panelEvent;
    }


}
