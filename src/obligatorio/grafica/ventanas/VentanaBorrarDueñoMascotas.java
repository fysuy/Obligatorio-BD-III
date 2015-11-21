package obligatorio.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.*;

import obligatorio.grafica.controladores.ControladorBorrarDue�oMascota;
import obligatorio.logica.exceptions.Due�oException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

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
		sl_panel.putConstraint(SpringLayout.WEST, labelCedula, 20, SpringLayout.WEST, panel);
		panel.add(labelCedula);
			
		textFieldCedulaDue�o = new JTextField();
		textFieldCedulaDue�o.setColumns(30);
		panel.add(textFieldCedulaDue�o);
		
		// Boton borrar due�o
		controlador = new ControladorBorrarDue�oMascota();
		JButton btnBorrarDue�o = new JButton("Borrar due\u00F1o");
		sl_panel.putConstraint(SpringLayout.EAST, btnBorrarDue�o, -20, SpringLayout.EAST, panel);
		btnBorrarDue�o.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnBorrarDue�o);
		
		btnBorrarDue�o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String strCedula = textFieldCedulaDue�o.getText().trim();
				
				// Verifica que la cedula no est� vac�a
				if (!strCedula.isEmpty()) {

					// Verifica que la CI del due�o sea solo n�meros
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);						
					} catch (NumberFormatException e1){
						JOptionPane.showMessageDialog(frame, "Formato inv�lido de c�dula.", "", JOptionPane.ERROR_MESSAGE);
					}

					// CI v�lida
					if (cedula != -1) {

						try {
							controlador.borrarDue�oMascota(cedula);
						} catch (SQLException | Due�oException | IOException e1) {
							// Muestra el error
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
					} 
					
				} else {
					JOptionPane.showMessageDialog(frame, "Ingresa la CI del due�o.", "Campo obligatorio", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		
		
		
		// Alineaci�n textfield
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDue�o, 30, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDue�o, 20, SpringLayout.EAST, labelCedula);
		sl_panel.putConstraint(SpringLayout.NORTH, btnBorrarDue�o, -2, SpringLayout.NORTH, textFieldCedulaDue�o);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDue�o, -20, SpringLayout.WEST, btnBorrarDue�o);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedula, 3, SpringLayout.NORTH, textFieldCedulaDue�o);
		
		
		JLabel lblInfo = new JLabel("Al borrar a un due\u00F1o tambi\u00E9n se eliminar\u00E1n sus mascotas.");
		sl_panel.putConstraint(SpringLayout.NORTH, lblInfo, 10, SpringLayout.SOUTH, textFieldCedulaDue�o);
		lblInfo.setForeground(new Color(105, 105, 105));
		sl_panel.putConstraint(SpringLayout.WEST, lblInfo, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblInfo, 0, SpringLayout.EAST, panel);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblInfo, -10, SpringLayout.SOUTH, panel);
		panel.add(lblInfo);
		
		
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
