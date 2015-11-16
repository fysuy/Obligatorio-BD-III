package obligatorio.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;


public class PoolConexiones implements IPoolConexiones {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	private int nivelTransaccionalidad;
	
	private int tope = 5;
	private int tamanio = 0;
	private int creadas = 0;
	private Connection [] conexiones;

	public PoolConexiones() throws IOException, ClassNotFoundException {
		super();
		
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		
		p.load(new FileInputStream(nomArch));
		
		this.url = p.getProperty("url");
		this.user = p.getProperty("user");
		this.password = p.getProperty("password");
		
		Class.forName(driver);
	}

	public IConexion obtenerConexion(boolean modifica) throws InterruptedException, SQLException {
		while(this.tamanio == this.tope) {
			wait();
		}
		
		Conexion con;
		
		if(this.conexiones[this.tamanio] == null) {
			Connection aux = (Connection) DriverManager.getConnection(url, user, password);
			con = new Conexion(aux);
			this.creadas++;
		} else {
			con = new Conexion(this.conexiones[this.tamanio]);
		}
		
		this.tamanio++;
		
		return con;
	}

	public void liberarConexion(IConexion ic, boolean ok) {
		// TODO: ok???? para q es ese parametro
		ic.closeCon();
		this.tamanio--;
		notify();
	}
}
