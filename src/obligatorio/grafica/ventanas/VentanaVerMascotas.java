package obligatorio.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.grafica.controladores.ControladorVerMascotas;

public class VentanaVerMascotas {

	private JFrame frame;
	private Object[][] data;
	private ControladorVerMascotas controlador;
	private JTextField textFieldCedulaDueño;
	private SpringLayout sl_panel;
	private JPanel panel;
	private JPanel panelTabla;
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
		sl_panel.putConstraint(SpringLayout.NORTH, labelTitulo, 5,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, labelTitulo, 0,
				SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, labelTitulo, 0,
				SpringLayout.EAST, panel);
		panel.add(labelTitulo);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 34));

		labelCedulaDueño = new JLabel("CI del due\u00F1o");
		sl_panel.putConstraint(SpringLayout.NORTH, labelCedulaDueño, 20,
				SpringLayout.SOUTH, labelTitulo);
		sl_panel.putConstraint(SpringLayout.WEST, labelCedulaDueño, 20,
				SpringLayout.WEST, panel);
		panel.add(labelCedulaDueño);

		textFieldCedulaDueño = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCedulaDueño, -3,
				SpringLayout.NORTH, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCedulaDueño, 20,
				SpringLayout.EAST, labelCedulaDueño);
		panel.add(textFieldCedulaDueño);
		textFieldCedulaDueño.setColumns(32);

		JButton btnVerMascotas = new JButton("Ver mascotas");
		sl_panel.putConstraint(SpringLayout.NORTH, btnVerMascotas, -7,
				SpringLayout.NORTH, labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldCedulaDueño, -20,
				SpringLayout.WEST, btnVerMascotas);
		sl_panel.putConstraint(SpringLayout.EAST, btnVerMascotas, -20,
				SpringLayout.EAST, panel);
		panel.add(btnVerMascotas);

		controlador = new ControladorVerMascotas();

		
		panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout(0, 0));
		sl_panel.putConstraint(SpringLayout.NORTH,
				panelTabla, 23, SpringLayout.SOUTH,
				labelCedulaDueño);
		sl_panel.putConstraint(SpringLayout.WEST,
				panelTabla, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST,
				panelTabla, 0, SpringLayout.EAST, panel);
		panel.add(panelTabla);
		
		btnVerMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String[] columnas = { "APODO", "RAZA", "CI DUEÑO" };
				data = null;
				JTable tableMascotas = new JTable(data, columnas);
				JScrollPane scrollPane = new JScrollPane(tableMascotas);
				String strCedula = textFieldCedulaDueño.getText().trim();

				// Verifica que la cedula no esté vacía
				if (!strCedula.isEmpty()) {

					// Verifica que la CI del dueño sea solo números
					int cedula = -1;
					try {
						cedula = Integer.parseInt(strCedula);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frame,
								"Formato inválido de cédula.", "",
								JOptionPane.ERROR_MESSAGE);
					}

					// CI válida
					// FIXME: cedula > 0
					if (cedula != -1) {

						// Crea la tabla con las mascotas del dueño
						try {
							data = controlador.listarMascotas(cedula);
							panelTabla.removeAll();
							
							tableMascotas = new JTable(data, columnas);
							tableMascotas.setEnabled(false);

							scrollPane = new JScrollPane(tableMascotas);
							tableMascotas.getTableHeader()
									.setReorderingAllowed(false);

							panelTabla.add(scrollPane);
							panelTabla.revalidate();
							panelTabla.repaint();

							textFieldCedulaDueño.setText("");
							

						} catch (LogicaException | PersistenciaException | DueñoException e) {
							// Muestra el error
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
					}

				} else {
					JOptionPane.showMessageDialog(frame,
							"Ingresa la CI del dueño.", "Campo obligatorio",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
