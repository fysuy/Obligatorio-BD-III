package obligatorio.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
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
import obligatorio.grafica.controladores.ControladorBorrarDueñoMascota;

public class VentanaBorrarDueñoMascotas {

	private JFrame frame;
	private ControladorBorrarDueñoMascota controlador;
	private JTextField textFieldCedulaDueño;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBorrarDueñoMascotas window = new VentanaBorrarDueñoMascotas();
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
	public VentanaBorrarDueñoMascotas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 186);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel labelTitulo = new JLabel("Borrar due\u00F1o");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		// Cédula del dueño
		JLabel labelCedula = new JLabel("CI del due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, labelCedula, 20,
				SpringLayout.WEST, panel);
		panel.add(labelCedula);

		textFieldCedulaDueño = new JTextField();
		textFieldCedulaDueño.setColumns(30);
		panel.add(textFieldCedulaDueño);

		// Boton borrar dueño
		controlador = new ControladorBorrarDueñoMascota();
		JButton btnBorrarDueño = new JButton("Borrar due\u00F1o");
		sl_panel.putConstraint(SpringLayout.EAST, btnBorrarDueño, -20,
				SpringLayout.EAST, panel);
		btnBorrarDueño.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnBorrarDueño);

		btnBorrarDueño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String strCedula = textFieldCedulaDueño.getText().trim();

				// Verifica que la cedula no esté vacía
				if (!strCedula.isEmpty()) {

					// Verifica que la CI del dueño sea solo números
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frame,
								"Formato inválido de cédula.", "",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI válida
					if (cedula != -1) {

						try {
							controlador.borrarDueñoMascota(cedula);
							JOptionPane.showMessageDialog(frame, "Se borró el dueño y todas sus mascotas.", "Dueño borrado", JOptionPane.PLAIN_MESSAGE);
							textFieldCedulaDueño.setText("");
						} catch (LogicaException | PersistenciaException | DueñoException e) {
							JOptionPane.showMessageDialog(frame,
									e.getMessage());
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Ingresa la CI del dueño.", "Campo obligatorio",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Alineación textfield
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDueño, 30,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDueño, 20,
				SpringLayout.EAST, labelCedula);
		sl_panel.putConstraint(SpringLayout.NORTH, btnBorrarDueño, -2,
				SpringLayout.NORTH, textFieldCedulaDueño);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDueño, -20,
				SpringLayout.WEST, btnBorrarDueño);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedula, 3,
				SpringLayout.NORTH, textFieldCedulaDueño);

		JLabel lblInfo = new JLabel(
				"Al borrar a un due\u00F1o tambi\u00E9n se eliminar\u00E1n sus mascotas.");
		sl_panel.putConstraint(SpringLayout.NORTH, lblInfo, 10,
				SpringLayout.SOUTH, textFieldCedulaDueño);
		lblInfo.setForeground(new Color(105, 105, 105));
		sl_panel.putConstraint(SpringLayout.WEST, lblInfo, 0,
				SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblInfo, 0,
				SpringLayout.EAST, panel);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblInfo, -10,
				SpringLayout.SOUTH, panel);
		panel.add(lblInfo);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
