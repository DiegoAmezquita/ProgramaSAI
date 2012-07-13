/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazPersona;

import javax.swing.JPanel;

public class PanelPersona extends JPanel {
	
	private PanelResult panelResult;

    public PanelPersona(PanelSearch panelSearch,PanelResult panelResult) {
       setLayout(null);
       
       this.panelResult = panelResult;
       
        panelSearch.setBounds(0, 0, 1000, 300);
        add(panelSearch);
       
        panelResult.getPanelInfoPersona().iniciarCombobox();
        panelResult.getPanelDetails().bloquearElementos();
        panelResult.getPanelInfoPersona().bloquearElementos();
        panelResult.getPanelInfoPersona().bloquearEdicion();
        panelResult.setBounds(0, 300, 1000, 400);
        add(panelResult);
    }

	public PanelResult getPanelResult() {
		return panelResult;
	}

	public void setPanelResult(PanelResult panelResult) {
		this.panelResult = panelResult;
	}
    
    
}
