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

		JLabel labelTitulo = new JLabel("Men� Principal");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		// INGRESAR DUE�O
		JButton btnIngresarDue�o = new JButton("Ingresar Due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, btnIngresarDue�o, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnIngresarDue�o, -140, SpringLayout.EAST, panel);

		btnIngresarDue�o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaIngresarDue�o ventanaIngresarDue�o = new VentanaIngresarDue�o();
				ventanaIngresarDue�o.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarDue�o, 30, SpringLayout.NORTH, panel);
		btnIngresarDue�o.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarDue�o);

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

		sl_panel.putConstraint(SpringLayout.NORTH, btnIngresarMascota, 10, SpringLayout.SOUTH, btnIngresarDue�o);
		btnIngresarMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnIngresarMascota);

		// VER DUE�OS
		JButton btnVerDue�os = new JButton("Ver Due\u00F1os");
		sl_panel.putConstraint(SpringLayout.WEST, btnVerDue�os, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerDue�os, -140, SpringLayout.EAST, panel);

		btnVerDue�os.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerDue�os ventanaVerDue�os = new VentanaVerDue�os();
				ventanaVerDue�os.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnVerDue�os, 10, SpringLayout.SOUTH, btnIngresarMascota);
		btnVerDue�os.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnVerDue�os);

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

		sl_panel.putConstraint(SpringLayout.NORTH, btnVerMascotas, 10, SpringLayout.SOUTH, btnVerDue�os);
		btnVerMascotas.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnVerMascotas);

		// BORRAR DUE�O Y MASCOTAS
		JButton btnBorrarDue�oMascotas = new JButton("Borrar Due\u00F1o");
		sl_panel.putConstraint(SpringLayout.WEST, btnBorrarDue�oMascotas, 140, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnBorrarDue�oMascotas, -140, SpringLayout.EAST, panel);

		btnBorrarDue�oMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBorrarDue�oMascotas ventanaBorrarDue�oMascotas = new VentanaBorrarDue�oMascotas();
				ventanaBorrarDue�oMascotas.setVisible(true);
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, btnBorrarDue�oMascotas, 10, SpringLayout.SOUTH, btnVerMascotas);
		panel.add(btnBorrarDue�oMascotas);
		btnBorrarDue�oMascotas.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
