package obligatorio.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.mysql.jdbc.Connection;


public class PoolConexiones implements IPoolConexiones {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	private int nivelTransaccionalidad;
	
	private int tope;
	private int tamanio;
	private int creadas;
	private Connection [] conexiones;

	public PoolConexiones() throws IOException {
		super();
		
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		
		p.load(new FileInputStream(nomArch));
		
		this.url = p.getProperty("url");
		this.user = p.getProperty("user");
		this.password = p.getProperty("password");
	}

	@Override
	public IConexion obtenerConexion(boolean modifica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void liberarConexion(IConexion ic, boolean ok) {
		// TODO Auto-generated method stub
		
	}

}
