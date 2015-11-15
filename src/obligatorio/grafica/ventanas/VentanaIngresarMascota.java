package obligatorio.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Font;

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
		
		JButton btnIngresarMascota = new JButton("Ingresar Mascota");
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarMascota, -20, SpringLayout.EAST, panel);
		btnIngresarMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarMascota);
		
		// Cédula del dueño
		JLabel labelCedulaDueño = new JLabel("Cédula del dueño");
		sl_panel.putConstraint(SpringLayout.WEST, labelCedulaDueño, 20, SpringLayout.WEST, panel);
		labelCedulaDueño.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelCedulaDueño);
			
		JTextField textFieldCedulaDueño = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDueño, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDueño, 20, SpringLayout.EAST, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedulaDueño, 3, SpringLayout.NORTH, textFieldCedulaDueño);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDueño, -20, SpringLayout.EAST, panel);
		panel.add(textFieldCedulaDueño);
		textFieldCedulaDueño.setColumns(34);
		
		// Raza
		JLabel labelRaza = new JLabel("Raza");
		sl_panel.putConstraint(SpringLayout.WEST, labelRaza, 20, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, labelRaza, 0, SpringLayout.EAST, labelCedulaDueño);
		labelRaza.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(labelRaza);
			
		JTextField textFieldRaza = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldRaza, 10, SpringLayout.SOUTH, textFieldCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldRaza, 20, SpringLayout.EAST, labelRaza);
		sl_panel.putConstraint(SpringLayout.NORTH, labelRaza, 3, SpringLayout.NORTH, textFieldRaza);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldRaza, -20, SpringLayout.EAST, panel);
		panel.add(textFieldRaza);
		textFieldRaza.setColumns(34);
		
		
		// Apodo
		JLabel labelApodo = new JLabel("Apodo");
		labelApodo.setHorizontalAlignment(SwingConstants.TRAILING);
		sl_panel.putConstraint(SpringLayout.EAST, labelApodo, 0, SpringLayout.EAST, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, labelApodo, 20, SpringLayout.WEST, panel);
		
		panel.add(labelApodo);
			
		JTextField textFieldApodo = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textFieldApodo, 20, SpringLayout.EAST, labelApodo);
		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarMascota, 10, SpringLayout.SOUTH, textFieldApodo);
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldApodo, 10, SpringLayout.SOUTH, textFieldRaza);
		sl_panel.putConstraint(SpringLayout.NORTH, labelApodo, 3, SpringLayout.NORTH, textFieldApodo);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldApodo, -20, SpringLayout.EAST, panel);
		panel.add(textFieldApodo);
		textFieldApodo.setColumns(34);
		
		
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
