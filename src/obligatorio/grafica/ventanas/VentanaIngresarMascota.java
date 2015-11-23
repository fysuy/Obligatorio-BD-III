package obligatorio.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.MascotaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.grafica.controladores.ControladorIngresarMascota;

public class VentanaIngresarMascota {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIngresarMascota window = new VentanaIngresarMascota();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaIngresarMascota() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 218);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel labelTitulo = new JLabel("Nueva mascota");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		// Cédula del dueño
		JLabel labelCedulaDueño = new JLabel("Cédula del dueño");
		sl_panel.putConstraint(SpringLayout.WEST, labelCedulaDueño, 20,
				SpringLayout.WEST, panel);
		labelCedulaDueño.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelCedulaDueño);

		final JTextField textFieldCedulaDueño = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDueño, 10,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDueño, 20,
				SpringLayout.EAST, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedulaDueño, 3,
				SpringLayout.NORTH, textFieldCedulaDueño);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDueño, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldCedulaDueño);
		textFieldCedulaDueño.setColumns(34);

		// Raza
		JLabel labelRaza = new JLabel("Raza");
		sl_panel.putConstraint(SpringLayout.WEST, labelRaza, 20,
				SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, labelRaza, 0,
				SpringLayout.EAST, labelCedulaDueño);
		labelRaza.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelRaza);

		final JTextField textFieldRaza = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldRaza, 10,
				SpringLayout.SOUTH, textFieldCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldRaza, 20,
				SpringLayout.EAST, labelRaza);
		sl_panel.putConstraint(SpringLayout.NORTH, labelRaza, 3,
				SpringLayout.NORTH, textFieldRaza);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldRaza, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldRaza);
		textFieldRaza.setColumns(34);

		// Apodo
		JLabel labelApodo = new JLabel("Apodo");
		labelApodo.setHorizontalAlignment(SwingConstants.TRAILING);
		sl_panel.putConstraint(SpringLayout.EAST, labelApodo, 0,
				SpringLayout.EAST, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, labelApodo, 20,
				SpringLayout.WEST, panel);

		panel.add(labelApodo);

		final JTextField textFieldApodo = new JTextField();

		JButton btnIngresarMascota = new JButton("Ingresar Mascota");
		btnIngresarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				ControladorIngresarMascota controlador = new ControladorIngresarMascota();

				// Obtiene los valores de los campos
				String strCedula = textFieldCedulaDueño.getText().trim();
				String raza = textFieldRaza.getText().trim();
				String apodo = textFieldApodo.getText().trim();

				// Verifica que no falten campos
				if (!strCedula.isEmpty() && !raza.isEmpty() && !apodo.isEmpty()) {

					// Verifica que la CI del dueño sea solo números
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frame,
								"Formato inválido de cédula.", "",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI válida
					if (cedula > 0) {

						try {
							controlador.ingresarMascota(cedula, apodo, raza);
							JOptionPane.showMessageDialog(frame,
									"Mascota agregada.");

							frame.setVisible(false);
							
						} catch (LogicaException | PersistenciaException
								| DueñoException | MascotaException
								| RemoteException e) {
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(frame,
								"El número de cédula es inválido.",
								"Cédula inválida", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Completa todos los campos.",
							"Campos obligatorios", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarMascota, -20,
				SpringLayout.EAST, panel);
		btnIngresarMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarMascota);

		sl_panel.putConstraint(SpringLayout.WEST, textFieldApodo, 20,
				SpringLayout.EAST, labelApodo);
		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarMascota, 10,
				SpringLayout.SOUTH, textFieldApodo);
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldApodo, 10,
				SpringLayout.SOUTH, textFieldRaza);
		sl_panel.putConstraint(SpringLayout.NORTH, labelApodo, 3,
				SpringLayout.NORTH, textFieldApodo);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldApodo, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldApodo);
		textFieldApodo.setColumns(34);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
