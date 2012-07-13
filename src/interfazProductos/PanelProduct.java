/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazProductos;

import javax.swing.JPanel;

/**
 *
 * @author Diego
 */
public class PanelProduct extends JPanel {

    public PanelProduct(PanelSearchProduct panelSearchProduct,PanelResultProduct panelResultProduct) {
       setLayout(null);
       panelSearchProduct.setBounds(0, 0, 1000, 280);
        add(panelSearchProduct);
       
        panelResultProduct.getPanelInfoProduct().iniciarCombobox("Todos");
        panelResultProduct.getPanelInfoProduct().bloquearElementos();
        panelResultProduct.getPanelImagenProducto().bloquearElementos();
        panelResultProduct.getPanelInfoProduct().bloquearEdicion();
        panelResultProduct.setBounds(0, 280, 1000, 450);
        add(panelResultProduct);
    }
}
