package obligatorio.grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuPrincipal window = new VentanaMenuPrincipal();
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
	public VentanaMenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel labelTitulo = new JLabel("Adivina la Mascota");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		// INGRESAR DUEÑO
		JButton btnIngresarDueño = new JButton("Ingresar Due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, btnIngresarDueño, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarDueño, -140, SpringLayout.EAST, panel);

		btnIngresarDueño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaIngresarDueño ventanaIngresarDueño = new VentanaIngresarDueño();
				ventanaIngresarDueño.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarDueño, 30, SpringLayout.NORTH, panel);
		btnIngresarDueño.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarDueño);

		// INGRESAR MASCOTA
		JButton btnIngresarMascota = new JButton("Ingresar Mascota");
		sl_panel.putConstraint(SpringLayout.WEST, btnIngresarMascota, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarMascota, -140, SpringLayout.EAST, panel);

		btnIngresarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaIngresarMascota ventanaIngresarMascota = new VentanaIngresarMascota();
				ventanaIngresarMascota.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarMascota, 10, SpringLayout.SOUTH, btnIngresarDueño);
		btnIngresarMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarMascota);

		// VER DUEÑOS
		JButton btnVerDueños = new JButton("Ver Due\u00F1os");
		sl_panel.putConstraint(SpringLayout.WEST, btnVerDueños, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerDueños, -140, SpringLayout.EAST, panel);

		btnVerDueños.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerDueños ventanaVerDueños = new VentanaVerDueños();
				ventanaVerDueños.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnVerDueños, 10, SpringLayout.SOUTH, btnIngresarMascota);
		btnVerDueños.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnVerDueños);

		// VER MASCOTAS
		JButton btnVerMascotas = new JButton("Ver Mascotas");
		sl_panel.putConstraint(SpringLayout.WEST, btnVerMascotas, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerMascotas, -140, SpringLayout.EAST, panel);

		btnVerMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerMascotas ventanaVerMascotas = new VentanaVerMascotas();
				ventanaVerMascotas.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnVerMascotas, 10, SpringLayout.SOUTH, btnVerDueños);
		btnVerMascotas.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnVerMascotas);

		// BORRAR DUEÑO Y MASCOTAS
		JButton btnBorrarDueñoMascotas = new JButton("Borrar Due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, btnBorrarDueñoMascotas, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnBorrarDueñoMascotas, -140, SpringLayout.EAST, panel);

		btnBorrarDueñoMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBorrarDueñoMascotas ventanaBorrarDueñoMascotas = new VentanaBorrarDueñoMascotas();
				ventanaBorrarDueñoMascotas.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnBorrarDueñoMascotas, 10, SpringLayout.SOUTH, btnVerMascotas);
		panel.add(btnBorrarDueñoMascotas);
		btnBorrarDueñoMascotas.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
