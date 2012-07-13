package interfazFacturacion;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DAO.DAODetalle;
import DAO.DAOUbicacion;
import DAO.DAOvarios;




public class FrameNuevoDetalle extends JFrame{
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
	
	
	public FrameNuevoDetalle() {
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
//		buttonNew.addActionListener(frameMain);
		add(buttonNew);

		buttonDelete = new JButton("Borrar");
		buttonDelete.setBounds(720, 200, 100, 30);
		buttonDelete.setActionCommand("BORRARDETALLE");
//		buttonDelete.addActionListener(frameMain);
		add(buttonDelete);

		buttonSave = new JButton("Guardar");
		buttonSave.setBounds(840, 200, 100, 30);
		buttonSave.setActionCommand("GUARDARDETALLE");
//		buttonSave.addActionListener(frameMain);
		add(buttonSave);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
