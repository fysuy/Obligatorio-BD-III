package obligatorio.consola;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Properties;

import obligatorio.grafica.ventanas.VentanaMenuPrincipal;
import obligatorio.logica.IFachada;

public class Cliente {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, NotBoundException {
		// TODO Auto-generated method stub

		Properties p = new Properties();
		String nomArch = "config/config-cliente.properties";

		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/fac";
		IFachada fac = (IFachada) Naming.lookup(ruta);
		// ojo que esta fiero eso
		VentanaMenuPrincipal window = new VentanaMenuPrincipal();
		window.setVisible(true);
	}
}
