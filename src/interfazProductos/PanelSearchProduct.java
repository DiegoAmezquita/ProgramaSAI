package interfazProductos;

import Codigo.Datos;
import Codigo.Persona;
import Codigo.Producto;
import DAO.DAOPersona;
import DAO.DAOProducto;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

public class PanelSearchProduct extends JPanel {

    private JTextField fieldSearch;
    private JButton buttonSearch;
    private JList listResultProduct;
    private DefaultListModel modelListResult;
    private JScrollPane scrollPaneResult;
    private TitledBorder rotulo;
    private DAOProducto daoProduct;
    private DAOvarios daoVarios;

    public PanelSearchProduct(FrameMain frameMain) {
        setSize(1000, 270);

        rotulo = BorderFactory.createTitledBorder("Busqueda Productos");
        rotulo.setTitleColor(new Color(0, 0, 128));
        setBorder(rotulo);
        setLayout(null);
        fieldSearch = new JTextField();
        fieldSearch.setBounds(20, 20, 500, 30);
        add(fieldSearch);

        buttonSearch = new JButton("BUSCAR");
        buttonSearch.setBounds(530, 20, 100, 30);
        buttonSearch.setActionCommand("BUSCARPRODUCTO");
        buttonSearch.addActionListener(frameMain);
        
        ActionMap mapaAccion = this.getActionMap();
		InputMap map = this.getInputMap(buttonSearch.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke key_F1 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
		map.put(key_F1, "buscar");
		mapaAccion.put("buscar",buscarProducto());

        add(buttonSearch);

        modelListResult = new DefaultListModel();
        listResultProduct = new JList(modelListResult);      
        listResultProduct.setCellRenderer(new DatosCellRenderer());
        scrollPaneResult = new JScrollPane(listResultProduct);
        scrollPaneResult.setBounds(20, 60, 960, 200);
        listResultProduct.setName("listResultProduct");
        listResultProduct.addListSelectionListener(frameMain);
        add(scrollPaneResult);

        //   JEditorPane editor = new JEditorPane();
        //  editor.setContentType("text/html");
        // editor.setText("<b>hola</b><br>");
        // modelListResult.addElement(editor.get));
        daoProduct = new DAOProducto();
        daoVarios = new DAOvarios();

    }
    
    public AbstractAction buscarProducto(){
		return new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getFieldSearch().getText().equals("")) {
					cargarResultados();
				}
				
			}
		};
	}
    
    
    

    public void bloquearElementos() {
        fieldSearch.setEnabled(false);
        buttonSearch.setEnabled(false);
        scrollPaneResult.setEnabled(false);
        listResultProduct.setEnabled(false);

    }

    public void cargarResultados() {
    	//TODO mejorar esta busqueda porque se esta demorando mucho
        ArrayList<Producto> listaProductos = daoProduct.consultarOptimizado(getFieldSearch().getText());
        getModelListResult().clear();
        String mostrar = "";
        for (int i = 0; i < listaProductos.size(); i++) {
//            String documentoFormato = daoVarios.darFormatoNumeros(listaProductos.get(i).getNumeroDocumento() + "");
            mostrar = daoVarios.consultarVariosPorCategoriaNivel2("Tipo de Elemento", listaProductos.get(i).getIdCategoriaProducto())+ " "
            		+ daoVarios.consultarVariosPorCategoriaNivel2("Marca Elemento", listaProductos.get(i).getIdMarcaProducto()) + " "
                    + listaProductos.get(i).getReferenciaProducto()+" "
                    + listaProductos.get(i).getNumeroParteProducto()+" "
            		+ listaProductos.get(i).getAbreviadoProducto(); 
            StringTokenizer tk = new StringTokenizer(getFieldSearch().getText(), " "); // Cambia aquí el separador
            mostrar = "<html>" + mostrar.toUpperCase() + "</html>";
            String nuevo = "";
            String palabra = "";
            while (tk.hasMoreTokens()) {
                palabra = tk.nextToken().toUpperCase();             
                //nuevo = "<font color=blue>" + palabra + "</font>";
                nuevo = "<b>" + palabra + "</b>";
                mostrar = mostrar.replaceAll(palabra, nuevo);
            }
            getModelListResult().addElement(new Datos(listaProductos.get(i).getIdProducto(), mostrar));
            mostrar = "";
        }
    }

    public void desbloquearElementos() {
        fieldSearch.setEnabled(true);
        buttonSearch.setEnabled(true);
        scrollPaneResult.setEnabled(true);
        listResultProduct.setEnabled(true);
    }

    public JTextField getFieldSearch() {
        return fieldSearch;
    }

    public void setFieldSearch(JTextField fieldSearch) {
        this.fieldSearch = fieldSearch;
    }

    public DefaultListModel getModelListResult() {
        return modelListResult;
    }

    public void setModelListResult(DefaultListModel modelListResult) {
        this.modelListResult = modelListResult;
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public void setButtonSearch(JButton buttonSearch) {
        this.buttonSearch = buttonSearch;
    }
}
