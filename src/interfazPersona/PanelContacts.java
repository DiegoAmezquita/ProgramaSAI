package interfazPersona;

import Codigo.Contacto;
import Codigo.Datos;
import Codigo.Detalle;
import Codigo.Persona;
import Codigo.Varios;
import DAO.DAOContacto;
import DAO.DAODetalle;
import DAO.DAOPersona;
import DAO.DAOUbicacion;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelContacts extends JPanel {

    private JList listResultContact;
    private JScrollPane scrollPaneResult;
    private DefaultListModel modelListResult;
    private JList listContact;
    private JScrollPane scrollPaneContact;
    private DefaultListModel modelListContact;
    private JLabel labelDetail;
    private JLabel labelPerson;
    private JLabel labelTypeContact;
    private JTextField textPerson;
    private JComboBox comboTypeContact;
    private JButton buttonSave;
    private JButton buttonSearch;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JButton buttonCancel;
    private boolean newDetail = false;
    private DAOContacto daoContacto;
    private DAOvarios daoVarios;
    private DAOPersona daoPersona;
    private DAODetalle daoDetalle;
    private int codContactLoad;
    private int codPersonLoad;
    private boolean edicion = false;
    private JTextArea areaDetails;
    private JScrollPane scrollPaneDetails;

    public PanelContacts(FrameMain frameMain) {
        setLayout(null);



        JLabel labelTitulo = new JLabel("Contactos");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(20, 0, 350, 30);
        add(labelTitulo);
        modelListResult = new DefaultListModel();
        listResultContact = new JList(modelListResult);
        listResultContact.setName("listResultContact");
        listResultContact.addListSelectionListener(frameMain);
        listResultContact.setCellRenderer(new DatosCellRenderer());
        scrollPaneResult = new JScrollPane(listResultContact);
        scrollPaneResult.setBounds(700, 30, 250, 180);
        add(scrollPaneResult);
        scrollPaneResult.setVisible(false);

        labelDetail = new JLabel("Detalles");
        labelDetail.setBounds(20, 160, 350, 30);
        add(labelDetail);

        areaDetails = new JTextArea();
        areaDetails.setEditable(false);

        scrollPaneDetails = new JScrollPane(areaDetails);
        scrollPaneDetails.setBounds(20, 190, 350, 100);
        add(scrollPaneDetails);



        buttonSearch = new JButton("Buscar");
        buttonSearch.setBounds(400, 50, 230, 30);
        buttonSearch.setActionCommand("BUSCARCONTACTO");
        buttonSearch.addActionListener(frameMain);
        add(buttonSearch);


        labelPerson = new JLabel("Persona:");
        labelPerson.setBounds(400, 100, 70, 30);
        add(labelPerson);

        textPerson = new JTextField();
        textPerson.setBounds(470, 100, 200, 30);
        add(textPerson);

        labelTypeContact = new JLabel("Tipo:");
        labelTypeContact.setBounds(400, 140, 70, 30);
        add(labelTypeContact);

        comboTypeContact = new JComboBox();
        comboTypeContact.addItemListener(frameMain);
        comboTypeContact.setBounds(470, 140, 200, 30);
        comboTypeContact.setName("comboTypeContact");
        add(comboTypeContact);





        modelListContact = new DefaultListModel();
        listContact = new JList(modelListContact);
        listContact.setName("listContact");
        listContact.addListSelectionListener(frameMain);

        listContact.setCellRenderer(new DatosCellRenderer());
        scrollPaneContact = new JScrollPane(listContact);
        scrollPaneContact.setBounds(20, 30, 350, 130);
        add(scrollPaneContact);


        buttonEdit = new JButton("EDITAR");
        buttonEdit.setBounds(400, 250, 100, 30);
        buttonEdit.setActionCommand("EDITARCONTACTO");
        buttonEdit.addActionListener(frameMain);
        add(buttonEdit);

        buttonDelete = new JButton("BORRAR");
        buttonDelete.setBounds(520, 250, 100, 30);
        buttonDelete.setActionCommand("BORRARCONTACTO");
        buttonDelete.addActionListener(frameMain);
        add(buttonDelete);

        buttonSave = new JButton("GUARDAR");
        buttonSave.setBounds(640, 250, 100, 30);
        buttonSave.setActionCommand("GUARDARCONTACTO");
        buttonSave.addActionListener(frameMain);
        add(buttonSave);

        buttonCancel = new JButton("CANCELAR");
        buttonCancel.setBounds(760, 250, 100, 30);
        buttonCancel.setActionCommand("CANCELARCONTACTO");
        buttonCancel.addActionListener(frameMain);
        add(buttonCancel);



        daoContacto = new DAOContacto();
        daoVarios = new DAOvarios();
        daoPersona = new DAOPersona();
        daoDetalle = new DAODetalle();
        iniciarTipoContacto();
        codPersonLoad = 0;
        codContactLoad = 0;
        bloquearElementos();

    }

    public void cargarDetallesContacto() {
        ArrayList<Detalle> listDetails = daoDetalle.consultarDetalleCompleto(codContactLoad);
        String detalles = "";
        for (int i = 0; i < listDetails.size(); i++) {
            detalles = detalles + listDetails.get(i).getTipo() + " " + listDetails.get(i).getDescripcion()
                    + " " + listDetails.get(i).getNombre() + "\n";

        }
        areaDetails.setText(detalles);

    }

    public void crearNuevoTipoContacto() {
        if (comboTypeContact.getSelectedItem().equals("--CREAR NUEVO--")) {
            boolean seguir = true;
            while (seguir) {
                String nuevoTipo = JOptionPane.showInputDialog("Nombre del tipo de contacto:");
                if (nuevoTipo == null) {
                    comboTypeContact.setSelectedIndex(0);
                    seguir = false;
                } else if (!nuevoTipo.equals("")) {
                    System.out.println("VOY A CREAR ESTE:" + nuevoTipo + "-");
                    daoVarios.insert(nuevoTipo, "Tipo Contacto");
                    iniciarTipoContacto();
                    comboTypeContact.setSelectedItem(nuevoTipo);
                    seguir = false;
                } else if (nuevoTipo.equals("")) {
                    JOptionPane.showMessageDialog(null, "El tipo de contacto no puede estar vacio");
                }
            }


        }

    }

    public void bloquearElementos() {
        buttonSearch.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(false);
        buttonCancel.setEnabled(false);
        textPerson.setEditable(false);
        comboTypeContact.setEnabled(false);
    }

    public void desbloquearElementos() {
        buttonSearch.setEnabled(true);
        buttonEdit.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonSave.setEnabled(true);
        buttonCancel.setEnabled(true);
        textPerson.setEditable(true);
        comboTypeContact.setEnabled(true);
    }

    public void personaSeleccionada() {
        buttonSearch.setEnabled(true);
    }

    public void desbloquearEdicion() {
        buttonEdit.setEnabled(true);
        buttonDelete.setEnabled(true);
    }

    public void bloquearEdicion() {
        buttonEdit.setEnabled(false);
        buttonDelete.setEnabled(false);
    }

    public void bloquearUso() {
        buttonEdit.setEnabled(false);
        buttonSearch.setEnabled(false);
        buttonSave.setEnabled(true);
        buttonCancel.setEnabled(true);
        buttonDelete.setEnabled(false);
        listContact.setEnabled(false);
        listResultContact.setEnabled(false);
        // textPerson.setEditable(true);
        comboTypeContact.setEnabled(true);
    }

    public void desbloquearUso() {
        buttonSearch.setEnabled(true);
        listContact.setEnabled(true);
        buttonSave.setEnabled(false);
        buttonCancel.setEnabled(false);
        buttonDelete.setEnabled(true);
        listResultContact.setEnabled(true);
        textPerson.setEditable(false);
        comboTypeContact.setEnabled(false);
    }

    public void contactoSeleccionado() {
        buttonSave.setEnabled(true);
        comboTypeContact.setEnabled(true);
        buttonCancel.setEnabled(true);
        listContact.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);
    }

    public void reiniciarInfo() {
        codContactLoad = 0;
        listContact.setSelectedIndex(-1);
        textPerson.setText("");
        comboTypeContact.setSelectedIndex(0);
        scrollPaneResult.setVisible(false);
        buttonEdit.setEnabled(false);
        buttonDelete.setEnabled(false);

    }

    public void cargarContactos() {
        modelListContact.clear();
        ArrayList<Contacto> listaContactos = daoContacto.retornarContactos(codPersonLoad);
        for (int i = 0; i < listaContactos.size(); i++) {
            Persona per = daoPersona.buscarPorCodigo(listaContactos.get(i).getCodContact());
            modelListContact.addElement(new Datos(per.getCodigo(), per.getNombre() + " " + per.getApellido() + "   " + listaContactos.get(i).getTipoContacto()));
        }

    }

    public void cargarListaPersonas(String buscarPersona) {
        ArrayList<Persona> listaPersonas = daoPersona.consultarOptimizado(buscarPersona);
        modelListResult.clear();
        for (int i = 0; i < listaPersonas.size(); i++) {
            Persona per = daoPersona.buscarPorCodigo(listaPersonas.get(i).getCodigo());
            modelListResult.addElement(new Datos(per.getCodigo(), per.getNombre() + " " + per.getApellido()));

        }
        scrollPaneResult.setVisible(true);
    }

    public void iniciarTipoContacto() {
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Tipo Contacto");
        for (int i = 0; i < listaVarios.size(); i++) {
            comboTypeContact.addItem(listaVarios.get(i).getnombreVario());
        }
        comboTypeContact.addItem("--CREAR NUEVO--");
    }

    public void cargarContactoEdicion() {
        Persona personaResultado = daoPersona.consultar(codContactLoad);
        getTextPerson().setText(personaResultado.getNombre() + " " + personaResultado.getApellido());
        comboTypeContact.setSelectedItem(daoContacto.retornarTipoContacto(codContactLoad, codPersonLoad));
        cargarDetallesContacto();
        edicion = true;

    }

    public void cargarContactoNuevo() {
        Persona personaResultado = daoPersona.consultar(codContactLoad);
        getTextPerson().setText(personaResultado.getNombre() + " " + personaResultado.getApellido());
        edicion = false;
    }

    public Contacto crearContacto() {
        Contacto contactoTempo = new Contacto();
        contactoTempo.setCodContact(codContactLoad);
        contactoTempo.setCodPerson(codPersonLoad);
        contactoTempo.setTipoContacto(daoVarios.buscarCodigoVario(comboTypeContact.getSelectedItem() + "") + "");
        return contactoTempo;
    }

    public void reiniciarListaBusqueda() {
        modelListResult.clear();
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(JButton buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public JButton getButtonDelete() {
        return buttonDelete;
    }

    public void setButtonDelete(JButton buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

    public JButton getButtonEdit() {
        return buttonEdit;
    }

    public void setButtonEdit(JButton buttonEdit) {
        this.buttonEdit = buttonEdit;
    }

    public JButton getButtonSave() {
        return buttonSave;
    }

    public void setButtonSave(JButton buttonSave) {
        this.buttonSave = buttonSave;
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public void setButtonSearch(JButton buttonSearch) {
        this.buttonSearch = buttonSearch;
    }

    public JComboBox getComboTypePerson() {
        return comboTypeContact;
    }

    public void setComboTypePerson(JComboBox comboTypePerson) {
        this.comboTypeContact = comboTypePerson;
    }

    public DAOContacto getDaoContacto() {
        return daoContacto;
    }

    public void setDaoContacto(DAOContacto daoContacto) {
        this.daoContacto = daoContacto;
    }

    public JLabel getLabelDetail() {
        return labelDetail;
    }

    public void setLabelDetail(JLabel labelDetail) {
        this.labelDetail = labelDetail;
    }

    public JLabel getLabelPerson() {
        return labelPerson;
    }

    public void setLabelPerson(JLabel labelPerson) {
        this.labelPerson = labelPerson;
    }

    public JLabel getLabelTypeContact() {
        return labelTypeContact;
    }

    public void setLabelTypeContact(JLabel labelTypeContact) {
        this.labelTypeContact = labelTypeContact;
    }

    public JList getListContact() {
        return listContact;
    }

    public void setListContact(JList listContact) {
        this.listContact = listContact;
    }

    public JList getListResultContact() {
        return listResultContact;
    }

    public void setListResultContact(JList listResultContact) {
        this.listResultContact = listResultContact;
    }

    public DefaultListModel getModelListContact() {
        return modelListContact;
    }

    public void setModelListContact(DefaultListModel modelListContact) {
        this.modelListContact = modelListContact;
    }

    public DefaultListModel getModelListResult() {
        return modelListResult;
    }

    public void setModelListResult(DefaultListModel modelListResult) {
        this.modelListResult = modelListResult;
    }

    public boolean isNewDetail() {
        return newDetail;
    }

    public void setNewDetail(boolean newDetail) {
        this.newDetail = newDetail;
    }

    public JScrollPane getScrollPaneContact() {
        return scrollPaneContact;
    }

    public void setScrollPaneContact(JScrollPane scrollPaneContact) {
        this.scrollPaneContact = scrollPaneContact;
    }

    public JScrollPane getScrollPaneResult() {
        return scrollPaneResult;
    }

    public void setScrollPaneResult(JScrollPane scrollPaneResult) {
        this.scrollPaneResult = scrollPaneResult;
    }

    public JTextField getTextPerson() {
        return textPerson;
    }

    public void setTextPerson(JTextField textPerson) {
        this.textPerson = textPerson;
    }

    public int getCodPersonLoad() {
        return codPersonLoad;
    }

    public void setCodPersonLoad(int codPersonLoad) {
        this.codPersonLoad = codPersonLoad;
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public int getCodContactLoad() {
        return codContactLoad;
    }

    public void setCodContactLoad(int codContactLoad) {
        this.codContactLoad = codContactLoad;
    }
}
