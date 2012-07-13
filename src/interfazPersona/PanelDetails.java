package interfazPersona;

import Codigo.Detalle;
import Codigo.Varios;
import Codigo.Datos;
import DAO.DAODetalle;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelDetails extends JPanel {

    private JList listDetails;
    private JScrollPane scrollPaneDetails;
    private DefaultListModel modelListDetails;
    private JComboBox comboBoxType;
    private JComboBox comboBoxUbication;
    private JLabel labelUbication;
    private JLabel labelType;
    private JLabel labelName;
    private JLabel labelDescription;
    private JTextField fieldName;
    private JTextField fieldDescripcion;
    private JButton buttonSave;
    private JButton buttonNew;
    private JButton buttonDelete;
    private JButton buttonCancel;
    private JButton buttonEdit;
    private DAOvarios daoVarios;
    private DAOUbicacion daoUbicacion;
    private DAODetalle daoDetalle;
    private boolean newDetail = false;
    private int detailLoad;

    public PanelDetails(FrameMain frameMain) {
        setLayout(null);



        JLabel labelTitulo = new JLabel("Detalles");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(20, 20, 440, 30);
        add(labelTitulo);
        modelListDetails = new DefaultListModel();
        listDetails = new JList(modelListDetails);
        listDetails.setName("listDetails");
        listDetails.addListSelectionListener(frameMain);
        scrollPaneDetails = new JScrollPane(listDetails);
        scrollPaneDetails.setBounds(20, 50, 440, 230);
        listDetails.setCellRenderer(new DatosCellRenderer());
        add(scrollPaneDetails);

        labelType = new JLabel("Tipo Detalle");
        labelType.setBounds(600, 40, 100, 30);
        add(labelType);

        comboBoxType = new JComboBox();
        comboBoxType.addItemListener(frameMain);
        comboBoxType.setName("comboBoxType");
        comboBoxType.setBounds(700, 40, 200, 30);
        add(comboBoxType);

        labelName = new JLabel("Nombre");
        labelName.setBounds(600, 80, 100, 30);
        add(labelName);

        fieldName = new JTextField();
        fieldName.setBounds(700, 80, 200, 30);
        add(fieldName);

        labelDescription = new JLabel("Descripcion");
        labelDescription.setBounds(600, 120, 200, 30);
        add(labelDescription);

        fieldDescripcion = new JTextField();
        fieldDescripcion.setBounds(700, 120, 200, 30);
        add(fieldDescripcion);


        labelUbication = new JLabel("Ubicacion");
        labelUbication.setBounds(600, 160, 100, 30);
        add(labelUbication);

        comboBoxUbication = new JComboBox();
        comboBoxUbication.setBounds(700, 160, 200, 30);
        add(comboBoxUbication);

        buttonNew = new JButton("Nuevo");
        buttonNew.setBounds(600, 200, 100, 30);
        buttonNew.setActionCommand("NUEVODETALLE");
        buttonNew.addActionListener(frameMain);
        add(buttonNew);

        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(720, 200, 100, 30);
        buttonDelete.setActionCommand("BORRARDETALLE");
        buttonDelete.addActionListener(frameMain);
        add(buttonDelete);

        buttonSave = new JButton("Guardar");
        buttonSave.setBounds(840, 200, 100, 30);
        buttonSave.setActionCommand("GUARDARDETALLE");
        buttonSave.addActionListener(frameMain);
        add(buttonSave);

        buttonEdit = new JButton("EDITAR");
        buttonEdit.addActionListener(frameMain);
        buttonEdit.setActionCommand("EDITARDETALLE");
        buttonEdit.setBounds(600, 250, 100, 30);
        add(buttonEdit);

        buttonCancel = new JButton("Cancelar");
        buttonCancel.setBounds(720, 250, 100, 30);
        buttonCancel.setActionCommand("CANCELARDETALLE");
        buttonCancel.addActionListener(frameMain);
        add(buttonCancel);

        daoVarios = new DAOvarios();
        daoUbicacion = new DAOUbicacion();
        daoDetalle = new DAODetalle();
        iniciarTipoDetalle();

    }

    public void ocultarPorSeleccion() {
        if (comboBoxType.getSelectedItem().equals("Celular")
                || comboBoxType.getSelectedItem().equals("web")
                || comboBoxType.getSelectedItem().equals("Correo Electronico")) {
            labelUbication.setVisible(false);
            comboBoxUbication.setVisible(false);
        } else {
            labelUbication.setVisible(true);
            comboBoxUbication.setVisible(true);
        }
    }

    public void iniciarTipoDetalle() {
        ArrayList<Varios> listaVarios = daoVarios.consultarVariosPorCategoria("Tipo de Informacion");
        for (int i = 0; i < listaVarios.size(); i++) {
            comboBoxType.addItem(listaVarios.get(i).getnombreVario());
        }
        ArrayList<String> listaUbicaciones = daoUbicacion.retornarUbicaciones();
        for (int i = 0; i < listaUbicaciones.size(); i++) {
            comboBoxUbication.addItem(listaUbicaciones.get(i));
        }
    }

    public void cargarDetalles(int codigoPersona) {
        ArrayList<Detalle> listaDetalles = daoDetalle.consultar(codigoPersona);
        modelListDetails.clear();
        for (int i = 0; i < listaDetalles.size(); i++) {
            String mostrar = listaDetalles.get(i).getTipo() + " "
                    + listaDetalles.get(i).getNombre() + " "
                    + listaDetalles.get(i).getDescripcion() + " "
                    + listaDetalles.get(i).getFechaCreacion() + " ";
            if (!listaDetalles.get(i).getTipo().equals("Celular")
                    && !listaDetalles.get(i).getTipo().equals("web")
                    && !listaDetalles.get(i).getTipo().equals("Correo Electronico")) {
                mostrar = mostrar + listaDetalles.get(i).getNombreUbicacion();
            }
            Datos datos = new Datos(listaDetalles.get(i).getCodigo(), mostrar);
            modelListDetails.addElement(datos);

        }


    }

    public boolean validarDetalle() {
        if (!fieldName.getText().equals("") || !fieldDescripcion.getText().equals("")) {
            return true;
        }
        return false;

    }

    public void reiniciarCampos() {
        comboBoxType.setSelectedIndex(0);
        fieldDescripcion.setText("");
        fieldName.setText("");
        comboBoxUbication.setSelectedIndex(0);
    }

    public void bloquearElementos() {
        fieldDescripcion.setEditable(false);
        fieldName.setEditable(false);
        comboBoxType.setEnabled(false);
        comboBoxUbication.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(false);
        buttonNew.setEnabled(false);
        buttonCancel.setEnabled(false);
        buttonEdit.setEnabled(false);

    }

    public void desbloquearElementos() {
        fieldDescripcion.setEditable(true);
        fieldName.setEditable(true);
        comboBoxType.setEnabled(true);
        comboBoxUbication.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonNew.setEnabled(true);
        buttonSave.setEnabled(true);
        buttonCancel.setEnabled(true);
        buttonEdit.setEnabled(true);
    }

    public void desbloquearEdicion() {
        buttonEdit.setEnabled(true);
    }

    public void bloquearEdicion() {
        buttonEdit.setEnabled(false);
    }

    public void bloquearEnUso() {
        buttonNew.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonSave.setEnabled(true);
        buttonCancel.setEnabled(true);
    }

    public void desbloquearEnUso() {
        buttonNew.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonEdit.setEnabled(false);
        buttonSave.setEnabled(false);
        buttonCancel.setEnabled(false);
    }

    public void desbloquearPersonaSeleccionada() {
        buttonNew.setEnabled(true);
    }

    public JButton getButtonDelete() {
        return buttonDelete;
    }

    public void setButtonDelete(JButton buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

    public JButton getButtonNew() {
        return buttonNew;
    }

    public void setButtonNew(JButton buttonNew) {
        this.buttonNew = buttonNew;
    }

    public JButton getButtonSave() {
        return buttonSave;
    }

    public void setButtonSave(JButton buttonSave) {
        this.buttonSave = buttonSave;
    }

    public JComboBox getComboBoxType() {
        return comboBoxType;
    }

    public void setComboBoxType(JComboBox comboBoxType) {
        this.comboBoxType = comboBoxType;
    }

    public DAOvarios getDaoVarios() {
        return daoVarios;
    }

    public void setDaoVarios(DAOvarios daoVarios) {
        this.daoVarios = daoVarios;
    }

    public JTextField getFieldDescripcion() {
        return fieldDescripcion;
    }

    public void setFieldDescripcion(JTextField fieldDescripcion) {
        this.fieldDescripcion = fieldDescripcion;
    }

    public JTextField getFieldName() {
        return fieldName;
    }

    public void setFieldName(JTextField fieldName) {
        this.fieldName = fieldName;
    }

    public JLabel getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(JLabel labelDescription) {
        this.labelDescription = labelDescription;
    }

    public JLabel getLabelName() {
        return labelName;
    }

    public void setLabelName(JLabel labelName) {
        this.labelName = labelName;
    }

    public JLabel getLabelType() {
        return labelType;
    }

    public void setLabelType(JLabel labelType) {
        this.labelType = labelType;
    }

    public JList getListDetails() {
        return listDetails;
    }

    public void setListDetails(JList listDetails) {
        this.listDetails = listDetails;
    }

    public DefaultListModel getModelListDetails() {
        return modelListDetails;
    }

    public void setModelListDetails(DefaultListModel modelListDetails) {
        this.modelListDetails = modelListDetails;
    }

    public JScrollPane getScrollPaneDetails() {
        return scrollPaneDetails;
    }

    public void setScrollPaneDetails(JScrollPane scrollPaneDetails) {
        this.scrollPaneDetails = scrollPaneDetails;
    }

    public JComboBox getComboBoxUbication() {
        return comboBoxUbication;
    }

    public void setComboBoxUbication(JComboBox comboBoxUbication) {
        this.comboBoxUbication = comboBoxUbication;
    }

    public boolean isNewDetail() {
        return newDetail;
    }

    public void setNewDetail(boolean newDetail) {
        this.newDetail = newDetail;
    }

    public int getDetailLoad() {
        return detailLoad;
    }

    public void setDetailLoad(int detailLoad) {
        this.detailLoad = detailLoad;
    }
}
