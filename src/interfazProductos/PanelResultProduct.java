package interfazProductos;

import DAO.DAOOpcion;
import Interfaz.FrameMain;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

public class PanelResultProduct extends JPanel {

    private TitledBorder rotulo;
    JTabbedPane tabbedPane = new JTabbedPane();
    private PanelInfoProducto panelInfoProduct;
    private PanelImagenProducto panelImagenProducto;
//    private PanelContacts panelContacts;
//    private PanelOrders panelOrders;
//    private PanelNewOrder panelNewOrder;
    private String infoProduct = "";
    private DAOOpcion daoOpcion;

    public PanelResultProduct(FrameMain frameMain) {
        panelInfoProduct = new PanelInfoProducto(frameMain);
        panelImagenProducto= new PanelImagenProducto(frameMain);
        setSize(1000, 400);
        rotulo = BorderFactory.createTitledBorder(infoProduct);
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);
        setLayout(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        add(tabbedPane);


        daoOpcion = new DAOOpcion();
                tabbedPane.add("Informacion", panelInfoProduct);
                tabbedPane.add("Imagenes", panelImagenProducto);
    }

    public void actualizarInfoProduct() {
        rotulo = BorderFactory.createTitledBorder(infoProduct);
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);

    }

   

    public void bloquearPestanias() {
        tabbedPane.setEnabled(false);
    }

    public void desbloquearPestanias() {
        tabbedPane.setEnabled(true);
    }

    public PanelInfoProducto getPanelInfoProduct() {
        return panelInfoProduct;
    }

    public void setPanelInfoProduct(PanelInfoProducto panelInfoProduct) {
        this.panelInfoProduct = panelInfoProduct;
    }


    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

	public PanelImagenProducto getPanelImagenProducto() {
		return panelImagenProducto;
	}

	public void setPanelImagenProducto(PanelImagenProducto panelImagenProducto) {
		this.panelImagenProducto = panelImagenProducto;
	}


	public String getInfoProduct() {
		return infoProduct;
	}

	public void setInfoProduct(String infoProduct) {
		this.infoProduct = infoProduct;
	}



}
