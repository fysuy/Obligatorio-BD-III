package obligatorio.consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;

import obligatorio.grafica.ventanas.VentanaMenuPrincipal;

public class Cliente {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, NotBoundException {
		VentanaMenuPrincipal window = new VentanaMenuPrincipal();
		window.setVisible(true);
	}
}
