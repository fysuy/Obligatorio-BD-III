package obligatorio.consola;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import obligatorio.exceptions.LogicaException;
import obligatorio.logica.Fachada;
import obligatorio.logica.IFachada;

public class Server {

	public static void main(String[] args) throws LogicaException {
		try {
			Properties pe = new Properties();
			IFachada fac = new Fachada();
			String nomArch2 = "config/config-servidor.properties";
			pe.load(new FileInputStream(nomArch2));
			String ip = pe.getProperty("ipServidor");
			String puerto = pe.getProperty("puertoServidor");
			int port = Integer.parseInt(puerto);
			LocateRegistry.createRegistry(port);
			String ruta = "//" + ip + ":" + puerto + "/fac";
			Naming.rebind(ruta, fac);
			System.out.println("Server ON");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
