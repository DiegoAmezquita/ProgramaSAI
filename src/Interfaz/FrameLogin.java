package Interfaz;

import Codigo.Configuracion;
import DAO.DAOConnectionLogin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameLogin extends JFrame implements ActionListener {

	private JLabel labelInicioSesion;
	private JLabel labelUserName;
	private JLabel labelPassword;
	private JTextField fieldUserName;
	private JPasswordField fieldPassword;
	private JButton buttonConnect;
	public DAOConnectionLogin m_DAOConnectionLogin;
	private String host;
	private String db;
	private String userDb;
	private String passwordDb;
	private Configuracion config;

	public FrameLogin() {

		setSize(300, 200);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		setTitle("INICIO DE SESION");
		labelInicioSesion = new JLabel("INICIO DE SESION", JLabel.CENTER);
		labelInicioSesion.setBounds(0, 0, 300, 40);
		labelInicioSesion.setFont(new Font("Arial", 1, 15));
		add(labelInicioSesion);

		labelUserName = new JLabel("Usuario: ");
		labelUserName.setBounds(20, 40, 100, 40);
		add(labelUserName);

		labelPassword = new JLabel("Contrasena: ");
		labelPassword.setBounds(20, 80, 100, 40);
		add(labelPassword);

		fieldUserName = new JTextField("diego");
		fieldUserName.setBounds(100, 40, 150, 30);
		add(fieldUserName);

		fieldPassword = new JPasswordField("123");
		fieldPassword.setBounds(100, 80, 150, 30);
		add(fieldPassword);

		buttonConnect = new JButton("CONECTAR");
		buttonConnect.setBounds(20, 120, 250, 30);
		buttonConnect.addActionListener(this);
		buttonConnect.setActionCommand("CONECTAR");
		add(buttonConnect);

		config = new Configuracion();

	}

	public void cargarConfiguracion() {

		config.cargarConfigXML();
		if (config.isConfiguracionCargada()) {
			host = config.buscarValorXML("host");
			db = config.buscarValorXML("nombre");
			userDb = config.buscarValorXML("usuario");
			passwordDb = config.buscarValorXML("clave");
		}

	}

	public static void main(String[] args) {
		FrameLogin frame = new FrameLogin();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if (command.equals("CONECTAR")) {
			cargarConfiguracion();
			System.out.println(fieldUserName.getText());
			System.out.println(fieldPassword.getText());

			if (config.isConfiguracionCargada()) {
				System.out.println("VOY ACA");
				m_DAOConnectionLogin = DAOConnectionLogin.getInstancia(host,
						db, userDb, passwordDb, fieldUserName.getText(),
						fieldPassword.getText());
				if (m_DAOConnectionLogin != null
						&& m_DAOConnectionLogin.isStateLogin()) {
					System.out.println("ENTRA");
					FrameMain frame = new FrameMain(fieldUserName.getText());
					frame.setVisible(true);
					this.setVisible(false);
				} else if (m_DAOConnectionLogin != null
						&& m_DAOConnectionLogin.isConectadoDB()) {
					JOptionPane.showMessageDialog(this,
							"Usuario y/o contraseña incorrecta");
				}

			} else {
				System.out.println("NO CARGA");
			}

		}

	}
}
