package obligatorio.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
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
import obligatorio.grafica.controladores.ControladorBorrarDue�oMascota;

public class VentanaBorrarDue�oMascotas {

	private JFrame frame;
	private ControladorBorrarDue�oMascota controlador;
	private JTextField textFieldCedulaDue�o;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBorrarDue�oMascotas window = new VentanaBorrarDue�oMascotas();
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
	public VentanaBorrarDue�oMascotas() {
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

		// C�dula del due�o
		JLabel labelCedula = new JLabel("CI del due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, labelCedula, 20,
				SpringLayout.WEST, panel);
		panel.add(labelCedula);

		textFieldCedulaDue�o = new JTextField();
		textFieldCedulaDue�o.setColumns(30);
		panel.add(textFieldCedulaDue�o);

		// Boton borrar due�o
		controlador = new ControladorBorrarDue�oMascota();
		JButton btnBorrarDue�o = new JButton("Borrar due\u00F1o");
		sl_panel.putConstraint(SpringLayout.EAST, btnBorrarDue�o, -20,
				SpringLayout.EAST, panel);
		btnBorrarDue�o.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnBorrarDue�o);

		btnBorrarDue�o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String strCedula = textFieldCedulaDue�o.getText().trim();

				// Verifica que la cedula no est� vac�a
				if (!strCedula.isEmpty()) {

					// Verifica que la CI del due�o sea solo n�meros
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frame,
								"Formato inv�lido de c�dula.", "",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI v�lida
					if (cedula > 0) {

						try {
							controlador.borrarDue�oMascota(cedula);
							JOptionPane.showMessageDialog(frame,
									"Se borr� el due�o y todas sus mascotas.",
									"Due�o borrado", JOptionPane.PLAIN_MESSAGE);
							textFieldCedulaDue�o.setText("");
							
						} catch (PersistenciaException | Due�oException | LogicaException | RemoteException e) {

							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(frame,
								"El n�mero de c�dula es inv�lido.", "C�dula inv�lida",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Ingresa la CI del due�o.", "Campo obligatorio",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Alineaci�n textfield
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDue�o, 30,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDue�o, 20,
				SpringLayout.EAST, labelCedula);
		sl_panel.putConstraint(SpringLayout.NORTH, btnBorrarDue�o, -2,
				SpringLayout.NORTH, textFieldCedulaDue�o);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDue�o, -20,
				SpringLayout.WEST, btnBorrarDue�o);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedula, 3,
				SpringLayout.NORTH, textFieldCedulaDue�o);

		JLabel lblInfo = new JLabel(
				"Al borrar a un due\u00F1o tambi\u00E9n se eliminar\u00E1n sus mascotas.");
		sl_panel.putConstraint(SpringLayout.NORTH, lblInfo, 10,
				SpringLayout.SOUTH, textFieldCedulaDue�o);
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
