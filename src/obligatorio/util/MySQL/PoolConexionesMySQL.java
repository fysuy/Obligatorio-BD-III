package obligatorio.util.MySQL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import obligatorio.exceptions.PersistenciaException;
import obligatorio.util.IConexion;
import obligatorio.util.IPoolConexiones;

import com.mysql.jdbc.Connection;

public class PoolConexionesMySQL implements IPoolConexiones {

	private String driver;
	private String url;
	private String user;
	private String password;

	private int nivelTransaccionalidad = Connection.TRANSACTION_SERIALIZABLE;

	private int tope;
	private int tamanio;
	private int creadas;
	private IConexion[] conexiones;

	public PoolConexionesMySQL() throws PersistenciaException {
		super();
		Properties p = new Properties();
		String nomArch = "config/config.properties";

		try {
			p.load(new FileInputStream(nomArch));

			this.url = p.getProperty("persistencia.url");
			this.user = p.getProperty("persistencia.user");
			this.password = p.getProperty("persistencia.password");
			this.driver = p.getProperty("persistencia.driver");
			this.tamanio = Integer.parseInt(p
					.getProperty("persistencia.tamanio"));
			this.tope = 0;
			this.creadas = 0;
			this.conexiones = new IConexion[this.tamanio];

			Class.forName(driver);
		} catch (FileNotFoundException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (NumberFormatException | ClassNotFoundException | IOException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public synchronized IConexion obtenerConexion(boolean modifica)
			throws PersistenciaException {
		IConexion con;

		try {
			while (this.creadas == this.tamanio && this.tope == 0) {
				wait();
			}

			if (this.creadas < this.tamanio) {
				Connection aux = (Connection) DriverManager.getConnection(url,
						user, password);
				aux.setTransactionIsolation(nivelTransaccionalidad);
				aux.setAutoCommit(false);

				con = new ConexionMySQL(aux);
				this.creadas++;
			} else {
				con = this.conexiones[this.tope - 1];
				this.conexiones[this.tope - 1] = null;
				this.tope--;
			}
		} catch (InterruptedException | SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return con;
	}

	public synchronized void liberarConexion(IConexion ic, boolean ok)
			throws PersistenciaException {
		try {
			if (ok) {
				((ConexionMySQL) ic).getCon().commit();
			} else {
				((ConexionMySQL) ic).getCon().rollback();
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		this.conexiones[this.tope] = ic;
		this.tope++;
		notify();
	}
}
