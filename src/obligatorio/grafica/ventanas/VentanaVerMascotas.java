package obligatorio.grafica.ventanas;


import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import obligatorio.grafica.controladores.ControladorVerMascotas;
import obligatorio.logica.exceptions.DueñoException;

public class VentanaVerMascotas {

	private JFrame frame;
	private Object[][] data;
	private ControladorVerMascotas controlador;
	private JTextField textFieldCedulaDueño;
	private SpringLayout sl_panel;
	private JPanel panel;
	private JLabel labelCedulaDueño;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerMascotas window = new VentanaVerMascotas();
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
	public VentanaVerMascotas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 552, 315);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel labelTitulo = new JLabel("Mascotas");
		sl_panel.putConstraint(SpringLayout.NORTH, labelTitulo, 5, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, labelTitulo, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, labelTitulo, 0, SpringLayout.EAST, panel);
		panel.add(labelTitulo);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		
		
		
		
		labelCedulaDueño = new JLabel("CI del due\u00F1o");
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedulaDueño, 20, SpringLayout.SOUTH, labelTitulo);
		sl_panel.putConstraint(SpringLayout.WEST, labelCedulaDueño, 20, SpringLayout.WEST, panel);
		panel.add(labelCedulaDueño);
		
		textFieldCedulaDueño = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDueño, -3, SpringLayout.NORTH, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDueño, 20, SpringLayout.EAST, labelCedulaDueño);
		panel.add(textFieldCedulaDueño);
		textFieldCedulaDueño.setColumns(32);
		
		
		
		
		
		JButton btnVerMascotas = new JButton("Ver mascotas");
		sl_panel.putConstraint(SpringLayout.NORTH, btnVerMascotas, -7, SpringLayout.NORTH, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDueño, -20, SpringLayout.WEST, btnVerMascotas);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerMascotas, -20, SpringLayout.EAST, panel);
		panel.add(btnVerMascotas);
		
		controlador = new ControladorVerMascotas();
		
		btnVerMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] columnas = { "APODO", "RAZA", "CI DUEÑO" };
				String strCedula = textFieldCedulaDueño.getText().trim();

				// Verifica que la cedula no esté vacía
				if (!strCedula.isEmpty()) {

					// Verifica que la CI del dueño sea solo números
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);						
					} catch (NumberFormatException e1){
						JOptionPane.showMessageDialog(frame, "Formato inválido de cédula.", "", JOptionPane.ERROR_MESSAGE);
					}

					// CI válida
					if (cedula != -1) {

						// Crea la tabla con las mascotas del dueño
						try {
							data = controlador.listarMascotas(cedula);
							
							JTable tableMascotas = new JTable(data, columnas);
							tableMascotas.setEnabled(false);

							JScrollPane scrollPane = new JScrollPane(tableMascotas);
							tableMascotas.getTableHeader().setReorderingAllowed(false);

							sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 23, SpringLayout.SOUTH, labelCedulaDueño);
							sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel);
							sl_panel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panel);
							panel.add(scrollPane);

							textFieldCedulaDueño.setText("");

						} catch (SQLException | DueñoException | IOException e1) {
							// Muestra el error
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Ingresa la CI del dueño.", "Campo obligatorio", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		
		
		
		
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
