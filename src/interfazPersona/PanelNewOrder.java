package interfazPersona;

import Codigo.Datos;
import Codigo.Elemento;
import Codigo.Evento;
import Codigo.OrdenServicio;
import Codigo.Varios;
import DAO.DAOElemento;
import DAO.DAOEvento;
import DAO.DAOLogin;
import DAO.DAOOrdenServicio;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
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

public class PanelNewOrder extends JPanel {

    private JList listItems;
    private JScrollPane scrollPaneItems;
    private DefaultListModel modelListItems;
    private JButton buttonNew;
    private JButton buttonEdit;
    private JButton buttonSave;
    private JButton buttonErase;
    private JButton buttonCancel;
    private JButton buttonAddItem;
    private JButton buttonAsignar;
    private int codPerson;
    private int codUser;
    private String nombreUser;
    private JLabel labelDescripcion;
    private JScrollPane scrollPaneDescripcion;
    private JTextArea textDescripcion;
    private JLabel labelBrand;
    private JLabel labelType;
    private JLabel labelSerial;
    private JLabel labelDescripcionItem;
    private JLabel labelModel;
    private JLabel labelState;
    private JLabel labelInfoOrden;
    private JComboBox comboBrand;
    private JComboBox comboType;
    private JComboBox comboState;
    private JTextField textSerial;
    private JTextField textModel;
    private JScrollPane scrollPaneDescripcionItem;
    private JTextArea textDescripcionItem;
    private ArrayList<Elemento> arrayItemsTemp;
    private DAOvarios daoVarios;
    private DAOOrdenServicio daoOrdenServicio;
    private DAOElemento daoElemento;
    private boolean edicion = false;
    private int codOrdenEdicion;
    private int codElemSel;
    private DAOLogin daoLogin;
    private DAOEvento daoEvento;
    private int indexEdicion = -1;

    public PanelNewOrder(FrameMain frameMain) {
        setLayout(null);

        //JLabel labelTitulo = new JLabel("Nueva Ordenes");
        //labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        // labelTitulo.setBounds(20, 10, 850, 30);
        // add(labelTitulo);

        labelInfoOrden = new JLabel("");
        //labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelInfoOrden.setBounds(20, 10, 400, 30);
        add(labelInfoOrden);


        labelDescripcion = new JLabel("Descripción:");
        labelDescripcion.setBounds(20, 40, 90, 30);
        add(labelDescripcion);

        textDescripcion = new JTextArea();
        scrollPaneDescripcion = new JScrollPane(textDescripcion);
        scrollPaneDescripcion.setBounds(100, 40, 300, 50);
        add(scrollPaneDescripcion);

        modelListItems = new DefaultListModel();
        listItems = new JList(modelListItems);
        listItems.setName("listItems");
        listItems.addListSelectionListener(frameMain);
        listItems.setCellRenderer(new DatosCellRenderer());
        scrollPaneItems = new JScrollPane(listItems);
        scrollPaneItems.setBounds(20, 100, 380, 180);
        add(scrollPaneItems);

        labelBrand = new JLabel("Marca:");
        labelBrand.setBounds(550, 30, 90, 30);
        add(labelBrand);

        labelType = new JLabel("Elemento:");
        labelType.setBounds(550, 65, 90, 30);
        add(labelType);

        labelState = new JLabel("Estado:");
        labelState.setBounds(550, 100, 90, 30);
        add(labelState);

        labelModel = new JLabel("Modelo:");
        labelModel.setBounds(550, 135, 90, 30);
        add(labelModel);

        labelSerial = new JLabel("Serial:");
        labelSerial.setBounds(550, 170, 90, 30);
        add(labelSerial);

        labelDescripcionItem = new JLabel("Descripción:");
        labelDescripcionItem.setBounds(550, 205, 90, 30);
        add(labelDescripcionItem);

        comboBrand = new JComboBox();
        comboBrand.setBounds(650, 30, 200, 30);
        comboBrand.addItemListener(frameMain);
        comboBrand.setName("comboBrandNewOrder");
        add(comboBrand);

        comboType = new JComboBox();
        comboType.setBounds(650, 65, 200, 30);
        comboType.addItemListener(frameMain);
        comboType.setName("comboTypeNewOrder");
        add(comboType);

        comboState = new JComboBox();
        comboState.setBounds(650, 100, 200, 30);
        comboState.addItemListener(frameMain);
        comboState.setName("comboStateNewOrder");
        add(comboState);

        textModel = new JTextField();
        textModel.setBounds(650, 135, 200, 30);
        add(textModel);

        textSerial = new JTextField();
        textSerial.setBounds(650, 170, 200, 30);
        add(textSerial);


        textDescripcionItem = new JTextArea();
        scrollPaneDescripcionItem = new JScrollPane(textDescripcionItem);
        scrollPaneDescripcionItem.setBounds(650, 205, 200, 30);
        add(scrollPaneDescripcionItem);


        buttonAddItem = new JButton("<<<<<<");
        buttonAddItem.setBounds(420, 135, 100, 30);
        buttonAddItem.setActionCommand("AGREGARELEMENTO");
        buttonAddItem.addActionListener(frameMain);
        add(buttonAddItem);


        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(530, 250, 100, 30);
        buttonEdit.setActionCommand("EDITARELEMENTO");
        buttonEdit.addActionListener(frameMain);
        add(buttonEdit);

        buttonSave = new JButton("Guardar");
        buttonSave.setBounds(640, 250, 100, 30);
        buttonSave.setActionCommand("GUARDARORDENSERVICIO");
        buttonSave.addActionListener(frameMain);
        add(buttonSave);

        buttonErase = new JButton("Borrar");
        buttonErase.setBounds(750, 250, 100, 30);
        buttonErase.setActionCommand("BORRARELEMENTO");
        buttonErase.addActionListener(frameMain);
        add(buttonErase);

        buttonCancel = new JButton("Cancelar");
        buttonCancel.setBounds(860, 250, 100, 30);
        buttonCancel.setActionCommand("CANCELARORDENSERVICIO");
        buttonCancel.addActionListener(frameMain);
        add(buttonCancel);

        arrayItemsTemp = new ArrayList<Elemento>();

        buttonAsignar = new JButton("Asignar");
        buttonAsignar.setBounds(870, 135, 80, 30);
        buttonAsignar.setActionCommand("ASIGNARITEM");
        buttonAsignar.addActionListener(frameMain);
        add(buttonAsignar);

        daoVarios = new DAOvarios();
        daoOrdenServicio = new DAOOrdenServicio();
        daoElemento = new DAOElemento();
        daoLogin = new DAOLogin();
        daoEvento = new DAOEvento();
        cargarMarcas();
        cargarEstado();
        cargarTipos();

    }

    public void borrarElemento() {
        if (listItems.getSelectedIndex() >= 0) {
            arrayItemsTemp.remove(listItems.getSelectedIndex());
            actualizarListaElementos();
        }
    }

    public void cargarMarcas() {
        comboBrand.removeAllItems();
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Marca Elemento");
        for (int i = 0; i < listaVarios.size(); i++) {
            comboBrand.addItem(listaVarios.get(i).getnombreVario());
        }
        comboBrand.addItem("--CREAR NUEVO--");
    }

    public void cargarTipos() {
        comboType.removeAllItems();
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Tipo de Elemento");
        for (int i = 0; i < listaVarios.size(); i++) {
            comboType.addItem(listaVarios.get(i).getnombreVario());
        }
        comboType.addItem("--CREAR NUEVO--");
    }

    public void cargarEstado() {
        comboState.removeAllItems();
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Estado del Elemento");
        for (int i = 0; i < listaVarios.size(); i++) {
            comboState.addItem(listaVarios.get(i).getnombreVario());
        }
        comboState.addItem("--CREAR NUEVO--");
    }

    public void actualizarListaElementos() {
        modelListItems.clear();
        for (int i = 0; i < arrayItemsTemp.size(); i++) {
            modelListItems.addElement(new Datos(i + 1, arrayItemsTemp.get(i).getMarca() + " "
                    + arrayItemsTemp.get(i).getTipo() + " "
                    + arrayItemsTemp.get(i).getEstado() + " "
                    + arrayItemsTemp.get(i).getModelo() + " "
                    + arrayItemsTemp.get(i).getSerial() + " "));

        }

    }

    public void reiniciarListaElementos() {
        arrayItemsTemp.clear();
    }

    public Elemento crearElemento() {
        Elemento elementoTempo = new Elemento();
        String marca = (String) comboBrand.getSelectedItem();
        String estado = (String) comboState.getSelectedItem();
        String tipo = (String) comboType.getSelectedItem();
        elementoTempo.setMarca(marca);
        elementoTempo.setEstado(estado);
        elementoTempo.setTipo(tipo);
        elementoTempo.setDescripcion(textDescripcionItem.getText());
        elementoTempo.setModelo(textModel.getText());
        elementoTempo.setSerial(textSerial.getText());
        if (edicion) {
            elementoTempo.setCodigoElemento(codElemSel);
        }
        return elementoTempo;
    }

    public OrdenServicio crearOrdenServicio() {
        OrdenServicio ordenServicioTempo = new OrdenServicio();
        ordenServicioTempo.setCodPersDuenio(codPerson);
        ordenServicioTempo.setNumero(daoOrdenServicio.aumentarNumeroOS());
        Calendar c = Calendar.getInstance();
        String dia = c.get(Calendar.DATE) + "";
        String mes = c.get(Calendar.MONTH) + "";
        String annio = c.get(Calendar.YEAR) + "";
        String hora = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        ordenServicioTempo.setFechaCreacion(annio + "-" + mes + "-" + dia + " " + hora);
        ordenServicioTempo.setMotivo(textDescripcion.getText());
        codUser = daoLogin.buscarCodigoLogin(nombreUser);
        ordenServicioTempo.setUsuarioRecibe(codUser);
        if (edicion && listItems.getSelectedIndex() >= 0) {
            ordenServicioTempo.setCodigo(codOrdenEdicion);
        }
        return ordenServicioTempo;
    }

    public void crearEventosPorOS(int codigoOrden) {
        ArrayList<Elemento> listTemp = daoElemento.retornarElementosOrden(codigoOrden);
        Calendar c = Calendar.getInstance();
        String dia = c.get(Calendar.DATE) + "";
        String mes = c.get(Calendar.MONTH) + "";
        String annio = c.get(Calendar.YEAR) + "";
        String hora = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        for (int i = 0; i < listTemp.size(); i++) {
            Evento eventoTempo = new Evento();
            eventoTempo.setCodigoElemento(listTemp.get(i).getCodigoElemento());
            eventoTempo.setDescripcion("INGRESO");
            eventoTempo.setFechaCreacion(annio + "-" + mes + "-" + dia + " " + hora);
            eventoTempo.setLeido(false);
            eventoTempo.setResponsableEven(0);
            eventoTempo.setSiguienteEven(0);
            eventoTempo.setTipo("INGRESO");
            crearEvento(eventoTempo);
        }

    }

    public void edicionOrdenDesbloquear() {
        buttonEdit.setEnabled(false);
        buttonErase.setEnabled(false);
    }

    public void nuevaOrdenDesbloquear() {
        buttonEdit.setEnabled(true);
        buttonErase.setEnabled(true);
    }

    public void crearEvento(Evento evento) {
        daoEvento.insert(evento);
    }

    public void guardarElementosEnOrdenServicio(int codigoOrden) {
        for (int i = 0; i < arrayItemsTemp.size(); i++) {
            Elemento eleTempo = arrayItemsTemp.get(i);
            eleTempo.setCodigoOrdenServicio(codigoOrden);
            daoElemento.insert(eleTempo);
        }
    }

    public void guardarElementoEdicion(Elemento elemento) {
        System.out.println("CODIGO ELEMENTO PARA EDITAR " + elemento.getCodigoElemento());
        if (listItems.getSelectedIndex() >= 0) {
            daoElemento.editar(elemento);
        } else {
            elemento.setCodigoOrdenServicio(codOrdenEdicion);
            daoElemento.insert(elemento);
        }
        listItems.clearSelection();
        reiniciarInfoElemento();
        cargarElementosOrdenEdicion();


    }

    public void cargarOrdenEdicion() {
        OrdenServicio ordenTempo = daoOrdenServicio.consultar(codOrdenEdicion);
        textDescripcion.setText(ordenTempo.getMotivo());
        labelInfoOrden.setText("Numero: " + ordenTempo.getNumero() + "   Fecha Creación: " + ordenTempo.getFechaCreacion());
        cargarElementosOrdenEdicion();
    }

    public void cargarElementosOrdenEdicion() {
        ArrayList<Elemento> listaTempo = daoElemento.retornarElementosOrden(codOrdenEdicion);
        modelListItems.clear();
        for (int i = 0; i < listaTempo.size(); i++) {
            modelListItems.addElement(new Datos(listaTempo.get(i).getCodigoElemento(),
                    listaTempo.get(i).getMarca() + " "
                    + listaTempo.get(i).getModelo() + " "
                    + listaTempo.get(i).getSerial() + " "
                    + listaTempo.get(i).getTipo()));
        }
    }

    public void cargarElementoSeleccionado() {
        if (isEdicion()) {
            Elemento eleTempo = daoElemento.consultarCompleto(codElemSel);
            comboBrand.setSelectedItem(eleTempo.getMarca());
            comboState.setSelectedItem(eleTempo.getEstado());
            comboType.setSelectedItem(eleTempo.getTipo());
            textDescripcionItem.setText(eleTempo.getDescripcion());
            textModel.setText(eleTempo.getModelo());
            textSerial.setText(eleTempo.getSerial());
            buttonAsignar.setEnabled(true);
        } else {
            indexEdicion = listItems.getSelectedIndex();
            Elemento eleTempo = arrayItemsTemp.get(listItems.getSelectedIndex());
            comboBrand.setSelectedItem(eleTempo.getMarca());
            comboState.setSelectedItem(eleTempo.getEstado());
            comboType.setSelectedItem(eleTempo.getTipo());
            textDescripcionItem.setText(eleTempo.getDescripcion());
            textModel.setText(eleTempo.getModelo());
            textSerial.setText(eleTempo.getSerial());

        }
    }

    public void crearNuevoVario() {
        if (comboBrand.getSelectedItem().equals("--CREAR NUEVO--")) {
            boolean seguir = true;
            while (seguir) {
                String nuevoMarca = JOptionPane.showInputDialog("Nueva marca:");
                if (nuevoMarca == null) {
                    comboBrand.setSelectedIndex(0);
                    seguir = false;
                } else if (!nuevoMarca.equals("")) {
                    daoVarios.insert(nuevoMarca, "Marca Elemento");
                    cargarMarcas();
                    comboBrand.setSelectedItem(nuevoMarca);
                    seguir = false;
                } else if (nuevoMarca.equals("")) {
                    JOptionPane.showMessageDialog(null, "El nombre de la marca no puede estar vacio");
                }
            }
        } else if (comboType.getSelectedItem().equals("--CREAR NUEVO--")) {
            boolean seguir = true;
            while (seguir) {
                String nuevoElemento = JOptionPane.showInputDialog("Nuevo Elemento");
                if (nuevoElemento == null) {
                    comboType.setSelectedIndex(0);
                    seguir = false;
                } else if (!nuevoElemento.equals("")) {
                    daoVarios.insert(nuevoElemento, "Tipo de Elemento");
                    cargarTipos();
                    comboType.setSelectedItem(nuevoElemento);
                    seguir = false;
                } else if (nuevoElemento.equals("")) {
                    JOptionPane.showMessageDialog(null, "El nombre del elemento no puede estar vacio");
                }
            }
        } else if (comboState.getSelectedItem().equals("--CREAR NUEVO--")) {
            boolean seguir = true;
            while (seguir) {
                String nuevoEstado = JOptionPane.showInputDialog("Nuevo Estado:");
                if (nuevoEstado == null) {
                    comboState.setSelectedIndex(0);
                    seguir = false;
                } else if (!nuevoEstado.equals("")) {
                    daoVarios.insert(nuevoEstado, "Estado del Elemento");
                    cargarEstado();
                    comboState.setSelectedItem(nuevoEstado);
                    seguir = false;
                } else if (nuevoEstado.equals("")) {
                    JOptionPane.showMessageDialog(null, "El nombre del estado no puede estar vacio");
                }
            }
        }

    }

    public void reiniciarInfoElemento() {
        comboBrand.setSelectedItem(0);
        comboState.setSelectedItem(0);
        comboType.setSelectedItem(0);
        textDescripcionItem.setText("");
        textModel.setText("");
        textSerial.setText("");
    }

    public void reiniciarCampos() {
        comboBrand.setSelectedIndex(0);
        comboState.setSelectedIndex(0);
        comboType.setSelectedIndex(0);
        textDescripcionItem.setText("");
        textModel.setText("");
        textSerial.setText("");
        modelListItems.clear();
        textDescripcion.setText("");
        labelInfoOrden.setText("");
        buttonAsignar.setEnabled(false);
    }

    public void bloquearEdicion() {
        buttonErase.setEnabled(false);

    }

    public void desbloquearEdicion() {
        buttonErase.setEnabled(true);
    }

    public int getCodPerson() {
        return codPerson;
    }

    public void setCodPerson(int codPerson) {
        this.codPerson = codPerson;
    }

    public ArrayList<Elemento> getArrayItemsTemp() {
        return arrayItemsTemp;
    }

    public void setArrayItemsTemp(ArrayList<Elemento> arrayItemsTemp) {
        this.arrayItemsTemp = arrayItemsTemp;
    }

    public DefaultListModel getModelListItems() {
        return modelListItems;
    }

    public void setModelListItems(DefaultListModel modelListItems) {
        this.modelListItems = modelListItems;
    }

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    public DAOOrdenServicio getDaoOrdenServicio() {
        return daoOrdenServicio;
    }

    public void setDaoOrdenServicio(DAOOrdenServicio daoOrdenServicio) {
        this.daoOrdenServicio = daoOrdenServicio;
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public int getCodOrdenEdicion() {
        return codOrdenEdicion;
    }

    public void setCodOrdenEdicion(int codOrdenEdicion) {
        this.codOrdenEdicion = codOrdenEdicion;
    }

    public int getCodElemSel() {
        return codElemSel;
    }

    public void setCodElemSel(int codElemSel) {
        this.codElemSel = codElemSel;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public int getIndexEdicion() {
        return indexEdicion;
    }

    public void setIndexEdicion(int indexEdicion) {
        this.indexEdicion = indexEdicion;
    }

    public JList getListItems() {
        return listItems;
    }

    public void setListItems(JList listItems) {
        this.listItems = listItems;
    }
}
