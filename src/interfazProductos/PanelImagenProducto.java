package interfazProductos;

import Codigo.Detalle;
import Codigo.Imagen;
import Codigo.Varios;
import Codigo.Datos;
import DAO.DAODetalle;
import DAO.DAOImagen;
import DAO.DAOUbicacion;
import DAO.DAOvarios;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.ws.handler.MessageContext.Scope;

public class PanelImagenProducto extends JPanel {

	private JScrollPane scrollPaneImagenProducto;
	private JPanel panelImagenes;
	private DAOImagen daoImagen;
	private int posicion = 10;
	ArrayList<JButton> listaBotones;
	private int idProductoLoad;
	String pathImage;
	FrameMain frameMain;
	JButton botonSubirImagen;
	JButton botonTomarFoto;


	public PanelImagenProducto(FrameMain frameMain) {
		setLayout(null);

		this.frameMain = frameMain;
		panelImagenes = new JPanel();
		panelImagenes.setLayout(null);
		scrollPaneImagenProducto = new JScrollPane(panelImagenes);
		scrollPaneImagenProducto.setBounds(20, 10, 960, 270);
		scrollPaneImagenProducto.setAutoscrolls(true);
		add(scrollPaneImagenProducto);

		daoImagen = new DAOImagen();

		listaBotones = new ArrayList<JButton>();





		botonSubirImagen = new JButton("Subir Imagen");
		botonSubirImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				subirFoto();
			}
		});
		botonSubirImagen.setBounds(352, 285, 150, 30);
		add(botonSubirImagen);

		botonTomarFoto = new JButton("Tomar Foto");
		botonTomarFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarEnDesarrollo();	
			}
		});
		botonTomarFoto.setBounds(522, 285, 150, 30);
		add(botonTomarFoto);


		cargarImagenes();

	}

	public void mostrarEnDesarrollo(){
		JOptionPane.showMessageDialog(frameMain, "FUNCION EN DESARROLLO");
	}

	public void cargarImagenes(){
		listaBotones.clear();

		//    	panelImagenes = new JPanel();
		posicion=10;
		ArrayList<Imagen> listaTemporal = daoImagen.consultar(idProductoLoad);
		for (int i = 0; i < listaTemporal.size(); i++) {
			crearBoton(listaTemporal.get(i));

		}
		panelImagenes.removeAll();

		for (int i = 0; i < listaBotones.size(); i++) {
			panelImagenes.add(listaBotones.get(i));
		}
		scrollPaneImagenProducto.repaint();
		panelImagenes.setPreferredSize(new Dimension(posicion, 210));
		panelImagenes.revalidate();
	}

	public void crearBoton(final Imagen imagen){
		ImageIcon icono = new ImageIcon(imagen.getImageFile());
		Image img = icono.getImage();        
		Image newimg = img.getScaledInstance(210, 210,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);  
		icono.getImage().getScaledInstance(30,-1,Image.SCALE_DEFAULT);
		JButton boton = new JButton (newIcon);        
		boton.setBounds(posicion,10,210,210);           
		JButton botonEliminar =  new JButton("ELIMINAR");
		botonEliminar.setBounds(posicion, 220, 210, 30);
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				daoImagen.borrar(imagen.getIdImagen());
				cargarImagenes();
			}
		});
		posicion+=220;



		listaBotones.add(boton);
		listaBotones.add(botonEliminar);


	}

	public void subirFoto(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
		chooser.setFileFilter(filter);
		File file = chooser.getCurrentDirectory();
		chooser.setCurrentDirectory(file);
		if ((chooser.showOpenDialog(this))!=JFileChooser.APPROVE_OPTION) return;
		File file2 = chooser.getSelectedFile();
		pathImage=(file2.getPath());
		System.out.println(pathImage);
		daoImagen.insert(pathImage,idProductoLoad);
		cargarImagenes();
	}
	
	
	
	public void bloquearElementos(){
		botonSubirImagen.setEnabled(false);
		botonTomarFoto.setEnabled(false);
	}
	
	
	public void desbloquearElementos(){
		botonSubirImagen.setEnabled(true);
		botonTomarFoto.setEnabled(true);
	}
	
	
	
	
	
	
	
	
	
	

	public JScrollPane getScrollPaneImagenProducto() {
		return scrollPaneImagenProducto;
	}

	public void setScrollPaneImagenProducto(JScrollPane scrollPaneImagenProducto) {
		this.scrollPaneImagenProducto = scrollPaneImagenProducto;
	}

	public JPanel getPanelImagenes() {
		return panelImagenes;
	}

	public void setPanelImagenes(JPanel panelImagenes) {
		this.panelImagenes = panelImagenes;
	}

	public DAOImagen getDaoImagen() {
		return daoImagen;
	}

	public void setDaoImagen(DAOImagen daoImagen) {
		this.daoImagen = daoImagen;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public ArrayList<JButton> getListaBotones() {
		return listaBotones;
	}

	public void setListaBotones(ArrayList<JButton> listaBotones) {
		this.listaBotones = listaBotones;
	}

	public int getIdProductoLoad() {
		return idProductoLoad;
	}

	public void setIdProductoLoad(int idProductoLoad) {
		this.idProductoLoad = idProductoLoad;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}





}
