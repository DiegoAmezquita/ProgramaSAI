package interfazPersona;

import Codigo.Opcion;
import DAO.DAOOpcion;
import Interfaz.FrameMain;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

public class PanelResult extends JPanel {

    private TitledBorder rotulo;
    JTabbedPane tabbedPane = new JTabbedPane();
    private PanelInfoPersona panelInfoPersona;
    private PanelDetails panelDetails;
    private PanelUser panelUser;
    private PanelContacts panelContacts;
    private PanelOrders panelOrders;
    private PanelNewOrder panelNewOrder;
    private String infoPersona = "";
    private DAOOpcion daoOpcion;

    public PanelResult(FrameMain frameMain) {
        System.out.println("gabe "+frameMain);
        panelInfoPersona = new PanelInfoPersona(frameMain);
        panelContacts = new PanelContacts(frameMain);
        panelDetails = new PanelDetails(frameMain);
        panelUser = new PanelUser(frameMain);
        panelOrders = new PanelOrders(frameMain);
        panelNewOrder = new PanelNewOrder(frameMain);
        setSize(1000, 300);
        rotulo = BorderFactory.createTitledBorder(infoPersona);
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);
        setLayout(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        /*tabbedPane.add("Información", panelInfoPersona);
        tabbedPane.add("Detalles", panelDetails);
        tabbedPane.add("Contactos", panelContacts);
        tabbedPane.add("Ordenes", panelOrders);
        tabbedPane.add("Usuario", panelUser);
         *
         */
        add(tabbedPane);


        daoOpcion = new DAOOpcion();
        ArrayList<Opcion> arrayOpcions = daoOpcion.retornarOpcionesUsuario(frameMain.getCodLog());
        for (int i = 0; i < arrayOpcions.size(); i++) {
            Opcion opcion = arrayOpcions.get(i);
            if (opcion.getnombre().equals("Informacion")) {
                tabbedPane.add("Informacion", panelInfoPersona);
            } else if (opcion.getnombre().equals("Detalles")) {
                tabbedPane.add("Detalles", panelDetails);
            } else if (opcion.getnombre().equals("Contactos")) {
                tabbedPane.add("Contactos", panelContacts);
            } else if (opcion.getnombre().equals("Ordenes")) {
                tabbedPane.add("Ordenes", panelOrders);
            } else if (opcion.getnombre().equals("Usuario")) {
                tabbedPane.add("Usuario", panelUser);
            }

        }
    }

    public void actualizarInfoPersona() {
        rotulo = BorderFactory.createTitledBorder(infoPersona);
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);

    }

   

    public void bloquearPestanias() {
        tabbedPane.setEnabled(false);
    }

    public void desbloquearPestanias() {
        tabbedPane.setEnabled(true);
    }

    public PanelInfoPersona getPanelInfoPersona() {
        return panelInfoPersona;
    }

    public void setPanelInfoPersona(PanelInfoPersona panelInfoPersona) {
        this.panelInfoPersona = panelInfoPersona;
    }

    public PanelDetails getPanelDetails() {
        return panelDetails;
    }

    public void setPanelDetails(PanelDetails panelDetails) {
        this.panelDetails = panelDetails;
    }

    public PanelUser getPanelUser() {
        return panelUser;
    }

    public void setPanelUser(PanelUser panelUser) {
        this.panelUser = panelUser;
    }

    public PanelContacts getPanelContacts() {
        return panelContacts;
    }

    public void setPanelContacts(PanelContacts panelContacts) {
        this.panelContacts = panelContacts;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public PanelNewOrder getPanelNewOrder() {
        return panelNewOrder;
    }

    public void setPanelNewOrder(PanelNewOrder panelNewOrder) {
        this.panelNewOrder = panelNewOrder;
    }

    public PanelOrders getPanelOrders() {
        return panelOrders;
    }

    public void setPanelOrders(PanelOrders panelOrders) {
        this.panelOrders = panelOrders;
    }

    public String getInfoPersona() {
        return infoPersona;
    }

    public void setInfoPersona(String infoPersona) {
        this.infoPersona = infoPersona;
    }
}
