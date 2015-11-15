package obligatorio.grafica.ventanas;


import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;

import java.awt.Font;
import obligatorio.grafica.controladores.ControladorVerDueños;

public class VentanaVerDueños {

	private JFrame frame;
	private ControladorVerDueños controladorVentanaDueños;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerDueños window = new VentanaVerDueños();
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
	public VentanaVerDueños() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		controladorVentanaDueños = new ControladorVerDueños();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 218);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitulo = new JLabel("Due\u00F1os");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frame.getContentPane().add(labelTitulo, BorderLayout.NORTH);
		
		
		String[] columnas = { "CI", "NOMBRE", "APELLIDO" };
		
		// FIXME: Rodear con try catch
		
		Object[][] data = controladorVentanaDueños.listarDueños();
		
		JTable tableDueños = new JTable(data, columnas);
		tableDueños.getTableHeader().setReorderingAllowed(false);
		tableDueños.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(tableDueños);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
