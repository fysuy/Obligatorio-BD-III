package obligatorio.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManageString {

		//Metodo para obtener la ruta de propierties
		public static String getProperty(String s) throws IOException {
			try {
				Properties p = new Properties();
				String f = "config/config-cliente.properties";
				p.load(new FileInputStream(f));
				return p.getProperty(s);
			} catch (IOException e) {
				throw e;
		}

		}
}
