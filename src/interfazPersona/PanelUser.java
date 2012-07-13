package interfazPersona;

import Codigo.Datos;
import Codigo.Detalle;
import Codigo.Login;
import Codigo.Opcion;
import Codigo.Varios;
import DAO.DAOLogin;
import DAO.DAOOpcion;
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

public class PanelUser extends JPanel {

    private JList listPermission;
    private JScrollPane scrollPanePermission;
    private DefaultListModel modelListPermission;
    private JList listPermissionUser;
    private JScrollPane scrollPanePermissionUser;
    private DefaultListModel modelListPermissionUser;
    private JLabel labelTypeUser;
    private JComboBox comboBoxTypeUser;
    private JLabel labelNameUser;
    private JLabel labelPassword;
    private JTextField fieldNameUser;
    private JTextField fieldPassword;
    private JButton buttonSave;
    private JButton buttonDelete;
    private DAOvarios daoVarios;
    private DAOLogin daoLogin;
    private DAOOpcion daoOpcion;
    private int detailLoad;
    private boolean sinUsuario = false;
    private int codigoLogin = 0;
    private JButton buttonAllLR;
    private JButton buttonAllRL;
    private JButton buttonSomeLR;
    private JButton buttonSomeRL;

    public PanelUser(FrameMain frameMain) {
        setLayout(null);



        JLabel labelTitulo = new JLabel("Información Usuario");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(20, 20, 300, 30);
        add(labelTitulo);

        labelTitulo = new JLabel("Permisos Sistema");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(700, 20, 240, 30);
        add(labelTitulo);

        labelTitulo = new JLabel("Permisos Usuario");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(350, 20, 240, 30);
        add(labelTitulo);


        modelListPermissionUser = new DefaultListModel();
        listPermissionUser = new JList(modelListPermissionUser);
        listPermissionUser.setName("listPermissionUser");
        listPermissionUser.setCellRenderer(new DatosCellRenderer());

        scrollPanePermissionUser = new JScrollPane(listPermissionUser);
        scrollPanePermissionUser.setBounds(350, 50, 240, 230);
        add(scrollPanePermissionUser);

        buttonAllLR = new JButton(">>>>");
        buttonAllLR.setBounds(600, 80, 80, 25);
        buttonAllLR.setActionCommand("QUITARTODOSPERMISOS");
        buttonAllLR.addActionListener(frameMain);
        add(buttonAllLR);

        buttonSomeLR = new JButton(">");
        buttonSomeLR.setBounds(600, 110, 80, 25);
        buttonSomeLR.setActionCommand("QUITARUNOSPERMISOS");
        buttonSomeLR.addActionListener(frameMain);
        add(buttonSomeLR);


        buttonAllRL = new JButton("<<<<");
        buttonAllRL.setBounds(600, 180, 80, 25);
        buttonAllRL.setActionCommand("PASARTODOSPERMISOS");
        buttonAllRL.addActionListener(frameMain);
        add(buttonAllRL);

        buttonSomeRL = new JButton("<");
        buttonSomeRL.setBounds(600, 210, 80, 25);
        buttonSomeRL.setActionCommand("PASARUNOSPERMISOS");
        buttonSomeRL.addActionListener(frameMain);
        add(buttonSomeRL);

        modelListPermission = new DefaultListModel();
        listPermission = new JList(modelListPermission);
        listPermission.setName("listPermission");

        listPermission.setCellRenderer(new DatosCellRenderer());
        scrollPanePermission = new JScrollPane(listPermission);
        scrollPanePermission.setBounds(700, 50, 240, 230);
        add(scrollPanePermission);






        labelTypeUser = new JLabel("Tipo Usuario");
        labelTypeUser.setBounds(600, 40, 100, 30);
        // add(labelTypeUser);
        comboBoxTypeUser = new JComboBox();
        comboBoxTypeUser.setBounds(700, 40, 200, 30);
        // add(comboBoxTypeUser);

        labelNameUser = new JLabel("Nombre Usuario");
        labelNameUser.setBounds(20, 80, 100, 30);
        add(labelNameUser);

        fieldNameUser = new JTextField();
        fieldNameUser.setBounds(120, 80, 200, 30);
        add(fieldNameUser);

        labelPassword = new JLabel("ContraseÃ±a");
        labelPassword.setBounds(20, 120, 200, 30);
        add(labelPassword);

        fieldPassword = new JTextField();
        fieldPassword.setBounds(120, 120, 200, 30);
        add(fieldPassword);





        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(20, 200, 100, 30);
        buttonDelete.setActionCommand("BORRARUSUARIO");
        buttonDelete.addActionListener(frameMain);
        add(buttonDelete);

        buttonSave = new JButton("Guardar");
        buttonSave.setBounds(140, 200, 100, 30);
        buttonSave.setActionCommand("GUARDARUSUARIO");
        buttonSave.addActionListener(frameMain);
        add(buttonSave);

        daoVarios = new DAOvarios();
        daoLogin = new DAOLogin();
        daoOpcion = new DAOOpcion();
        bloquearElementos();

    }

    public void cargarUsuario(int codigoPersona) {
        Login login = daoLogin.consultar(codigoPersona);
        cargarTipoUsuario();
        if (!login.getNombreLog().equals("")) {
            fieldNameUser.setText(login.getNombreLog());
            fieldPassword.setText(login.getClaveLog());
            sinUsuario = false;
            codigoLogin = login.getCodigoLog();
            comboBoxTypeUser.setSelectedIndex(login.getTipoUsuario() - 1);
            habilitarBotones();
        } else {
            reiniciarCampos();
            sinUsuario = true;
            codigoLogin = 0;
            deshabilitarBotones();
        }
        cargarPermisosUsuario();

        cargarPermisosSistema();
    }

    public void habilitarBotones(){
        buttonAllLR.setEnabled(true);
        buttonSomeLR.setEnabled(true);
        buttonAllRL.setEnabled(true);
        buttonSomeRL.setEnabled(true);
    }
     public void deshabilitarBotones(){
        buttonAllLR.setEnabled(false);
        buttonSomeLR.setEnabled(false);
        buttonAllRL.setEnabled(false);
        buttonSomeRL.setEnabled(false);
    }

    public void cargarTipoUsuario() {
        ArrayList<Varios> listaTipoUsuario = daoVarios.consultarVariosPorCategoria("Tipo Usuario");
        comboBoxTypeUser.removeAllItems();
        for (int i = 0; i < listaTipoUsuario.size(); i++) {
            comboBoxTypeUser.addItem(listaTipoUsuario.get(i).getnombreVario());

        }
    }

    public void cargarPermisosSistema() {
        modelListPermission.removeAllElements();
        ArrayList<Opcion> listaOpciones = daoOpcion.retornarOpciones();
        for (int i = 0; i < listaOpciones.size(); i++) {
            String opcionTempo = listaOpciones.get(i).getnombre();
            for (int j = 0; j < listaOpciones.get(i).getnivel(); j++) {
                opcionTempo = "-" + opcionTempo;
            }
            modelListPermission.addElement(new Datos(listaOpciones.get(i).getcodigo(), opcionTempo));
        }
    }

    public void cargarPermisosUsuario() {
        modelListPermissionUser.removeAllElements();
        ArrayList<Opcion> listaOpciones = daoOpcion.retornarOpcionesUsuario(codigoLogin);
        for (int i = 0; i < listaOpciones.size(); i++) {
            String opcionTempo = listaOpciones.get(i).getnombre();
            for (int j = 0; j < listaOpciones.get(i).getnivel(); j++) {
                opcionTempo = "-" + opcionTempo;
            }
            modelListPermissionUser.addElement(new Datos(listaOpciones.get(i).getcodigo(), opcionTempo));
        }

    }

    public void agregarTodosPermisos() {
        ArrayList<Opcion> listaOpciones = daoOpcion.retornarOpciones();
        for (int i = 0; i < listaOpciones.size(); i++) {
            agregarPermiso(listaOpciones.get(i).getcodigo());
        }
    }

    public void agregarPermiso(int codOpc) {
        daoOpcion.agregarPermiso(codOpc, codigoLogin);
        cargarPermisosUsuario();
    }

    public void agregarPermiso() {
        Object[] seleccionados = listPermission.getSelectedValues();
        for (int i = 0; i < seleccionados.length; i++) {
            int codOpc = ((Datos) seleccionados[i]).getCodigo();
            daoOpcion.agregarPermiso(codOpc, codigoLogin);
        }


        cargarPermisosUsuario();
    }

    public void quitarPermiso(int codOpc) {
        daoOpcion.borrarPermiso(codOpc, codigoLogin);
        cargarPermisosUsuario();
    }

    public void quitarPermiso() {
        Object[] seleccionados = listPermissionUser.getSelectedValues();
        for (int i = 0; i < seleccionados.length; i++) {
            int codOpc = ((Datos) seleccionados[i]).getCodigo();
            daoOpcion.borrarPermiso(codOpc, codigoLogin);
        }
        cargarPermisosUsuario();
    }

    public void quitarTodosPermisos() {
        daoOpcion.borrarTodosPermisosUsuario(codigoLogin);
        cargarPermisosUsuario();
    }

    public void reiniciarCampos() {
        fieldNameUser.setText("");
        fieldPassword.setText("");
        codigoLogin = 0;
    }

  
    public void bloquearElementos() {
        listPermission.setEnabled(false);
        listPermissionUser.setEnabled(false);
        fieldNameUser.setEnabled(false);
        fieldPassword.setEnabled(false);
        comboBoxTypeUser.setEnabled(false);

        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(false);
    }

    public void desbloquearElementos() {
        listPermission.setEnabled(true);
        listPermissionUser.setEnabled(true);
        fieldNameUser.setEnabled(true);
        fieldPassword.setEnabled(true);
        comboBoxTypeUser.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonSave.setEnabled(true);
    }

    public JButton getButtonDelete() {
        return buttonDelete;
    }

    public void setButtonDelete(JButton buttonDelete) {
        this.buttonDelete = buttonDelete;
    }

    public JButton getButtonSave() {
        return buttonSave;
    }

    public void setButtonSave(JButton buttonSave) {
        this.buttonSave = buttonSave;
    }

    public DAOvarios getDaoVarios() {
        return daoVarios;
    }

    public void setDaoVarios(DAOvarios daoVarios) {
        this.daoVarios = daoVarios;
    }

    public boolean isSinUsuario() {
        return sinUsuario;
    }

    public void setSinUsuario(boolean sinUsuario) {
        this.sinUsuario = sinUsuario;
    }

    public int getDetailLoad() {
        return detailLoad;
    }

    public void setDetailLoad(int detailLoad) {
        this.detailLoad = detailLoad;
    }

    public JComboBox getComboBoxTypeUser() {
        return comboBoxTypeUser;
    }

    public void setComboBoxTypeUser(JComboBox comboBoxTypeUser) {
        this.comboBoxTypeUser = comboBoxTypeUser;
    }

    public JTextField getFieldNameUser() {
        return fieldNameUser;
    }

    public void setFieldNameUser(JTextField fieldNameUser) {
        this.fieldNameUser = fieldNameUser;
    }

    public JTextField getFieldPassword() {
        return fieldPassword;
    }

    public void setFieldPassword(JTextField fieldPassword) {
        this.fieldPassword = fieldPassword;
    }

    public DefaultListModel getModelListPermission() {
        return modelListPermission;
    }

    public void setModelListPermission(DefaultListModel modelListPermission) {
        this.modelListPermission = modelListPermission;
    }

    public DefaultListModel getModelListPermissionUser() {
        return modelListPermissionUser;
    }

    public void setModelListPermissionUser(DefaultListModel modelListPermissionUser) {
        this.modelListPermissionUser = modelListPermissionUser;
    }

    public int getCodigoLogin() {
        return codigoLogin;
    }

    public void setCodigoLogin(int codigoLogin) {
        this.codigoLogin = codigoLogin;
    }
}
