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
	
	private int nivelTransaccionalidad = Connection.TRANSACTION_SERIALIZABLE;
	
	private int tope;
	private int tamanio;
	private int creadas;
	private IConexion [] conexiones;

	public PoolConexiones() throws IOException, ClassNotFoundException {
		super();
		
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		
		p.load(new FileInputStream(nomArch));
		
		this.url = p.getProperty("persistencia.url");
		this.user = p.getProperty("persistencia.user");
		this.password = p.getProperty("persistencia.password");
		this.tamanio = Integer.parseInt(p.getProperty("persistencia.tamanio"));
		this.tope = 0;
		this.creadas = 0;
		
		Class.forName(driver);
	}

	public IConexion obtenerConexion(boolean modifica) throws InterruptedException, SQLException {
		while(this.creadas == this.tamanio && this.tope == 0) {
			wait();
		}
		
		IConexion con;
		
		if(this.creadas < this.tamanio) {
			Connection aux = (Connection) DriverManager.getConnection(url, user, password);
			aux.setTransactionIsolation(nivelTransaccionalidad);
			aux.setAutoCommit(false);
			
			con = new Conexion(aux);
			this.creadas++;
		} else {
			con = this.conexiones[this.tope-1];
			this.conexiones[this.tope-1] = null;
			this.tope--;
		}
		
		return con;
	}

	public void liberarConexion(IConexion ic, boolean ok) {		
		try {
			if(ok) {
				ic.getCon().commit();
			} else {
				ic.getCon().rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.conexiones[this.tope] = ic;
		this.tope++;
		notify();
	}
}
