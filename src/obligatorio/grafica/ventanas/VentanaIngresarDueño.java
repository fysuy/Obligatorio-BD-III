package obligatorio.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import obligatorio.exceptions.PersistenciaException;
import obligatorio.grafica.controladores.ControladorIngresarDueño;

public class VentanaIngresarDueño {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIngresarDueño window = new VentanaIngresarDueño();
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
	public VentanaIngresarDueño() {
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

		JLabel labelTitulo = new JLabel("Nuevo due\u00F1o");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		// Cédula
		JLabel labelCedula = new JLabel("C\u00E9dula de identidad");
		sl_panel.putConstraint(SpringLayout.WEST, labelCedula, 20,
				SpringLayout.WEST, panel);
		labelCedula.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelCedula);

		final JTextField textFieldCedula = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedula, 10,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedula, 20,
				SpringLayout.EAST, labelCedula);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedula, 3,
				SpringLayout.NORTH, textFieldCedula);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedula, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldCedula);
		textFieldCedula.setColumns(34);

		// Nombre
		JLabel labelNombre = new JLabel("Nombre");
		sl_panel.putConstraint(SpringLayout.WEST, labelNombre, 20,
				SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, labelNombre, 0,
				SpringLayout.EAST, labelCedula);
		labelNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelNombre);

		final JTextField textFieldNombre = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldNombre, 10,
				SpringLayout.SOUTH, textFieldCedula);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldNombre, 20,
				SpringLayout.EAST, labelNombre);
		sl_panel.putConstraint(SpringLayout.NORTH, labelNombre, 3,
				SpringLayout.NORTH, textFieldNombre);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldNombre, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(34);

		// Apellido
		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		sl_panel.putConstraint(SpringLayout.EAST, labelApellido, 0,
				SpringLayout.EAST, labelCedula);
		sl_panel.putConstraint(SpringLayout.WEST, labelApellido, 20,
				SpringLayout.WEST, panel);

		panel.add(labelApellido);

		final JTextField textFieldApellido = new JTextField();

		// Ingresar dueño
		JButton btnIngresarDueño = new JButton("Ingresar Due\u00F1o");

		btnIngresarDueño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				ControladorIngresarDueño controlador = new ControladorIngresarDueño();

				// Obtiene los valores de los campos
				String strCedula = textFieldCedula.getText().trim();
				String nombre = textFieldNombre.getText().trim();
				String apellido = textFieldApellido.getText().trim();

				// Verifica que no falten campos
				if (!strCedula.isEmpty() && !nombre.isEmpty()
						&& !apellido.isEmpty()) {

					// Verifica que la CI sea solo números
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frame,
								"Formato de cédula inválido.", "",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI válida
					if (cedula != -1) {

						try {
							controlador.ingresarDueño(cedula, nombre, apellido);
							JOptionPane.showMessageDialog(frame,
									"Dueño creado.");

							textFieldCedula.setText("");
							textFieldNombre.setText("");
							textFieldApellido.setText("");

						} catch (LogicaException | DueñoException
								| PersistenciaException e) {
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Completa todos los campos.",
							"Campos obligatorios", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarDueño, -20,
				SpringLayout.EAST, panel);
		btnIngresarDueño.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarDueño);

		sl_panel.putConstraint(SpringLayout.WEST, textFieldApellido, 20,
				SpringLayout.EAST, labelApellido);
		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarDueño, 10,
				SpringLayout.SOUTH, textFieldApellido);
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldApellido, 10,
				SpringLayout.SOUTH, textFieldNombre);
		sl_panel.putConstraint(SpringLayout.NORTH, labelApellido, 3,
				SpringLayout.NORTH, textFieldApellido);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldApellido, -20,
				SpringLayout.EAST, panel);
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(34);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
