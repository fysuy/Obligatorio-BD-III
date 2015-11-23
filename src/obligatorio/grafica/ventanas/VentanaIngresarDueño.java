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

import obligatorio.exceptions.Due�oException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.grafica.controladores.ControladorIngresarDue�o;

public class VentanaIngresarDue�o {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIngresarDue�o window = new VentanaIngresarDue�o();
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
	public VentanaIngresarDue�o() {
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

		// C�dula
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

		// Ingresar due�o
		JButton btnIngresarDue�o = new JButton("Ingresar Due\u00F1o");

		btnIngresarDue�o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				ControladorIngresarDue�o controlador = new ControladorIngresarDue�o();

				// Obtiene los valores de los campos
				String strCedula = textFieldCedula.getText().trim();
				String nombre = textFieldNombre.getText().trim();
				String apellido = textFieldApellido.getText().trim();

				// Verifica que no falten campos
				if (!strCedula.isEmpty() && !nombre.isEmpty()
						&& !apellido.isEmpty()) {

					// Verifica que la CI sea solo n�meros
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frame,
								"Formato de c�dula inv�lido.", "C�dula inv�lida",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI v�lida
					if (cedula > 0) {

						try {
							controlador.ingresarDue�o(cedula, nombre, apellido);
							JOptionPane.showMessageDialog(frame,
									"Due�o creado.");

							frame.setVisible(false);

						} catch (LogicaException | Due�oException
								| PersistenciaException | RemoteException e) {
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(frame,
								"El n�mero de c�dula es inv�lido.", "C�dula inv�lida",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Completa todos los campos.",
							"Campos obligatorios", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarDue�o, -20,
				SpringLayout.EAST, panel);
		btnIngresarDue�o.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarDue�o);

		sl_panel.putConstraint(SpringLayout.WEST, textFieldApellido, 20,
				SpringLayout.EAST, labelApellido);
		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarDue�o, 10,
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
