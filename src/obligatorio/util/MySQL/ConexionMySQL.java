package obligatorio.util.MySQL;
import com.mysql.jdbc.Connection;

import obligatorio.util.IConexion;

public class ConexionMySQL implements IConexion {
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public ConexionMySQL(Connection con) {
		super();
		this.con = con;
	}
}	
	
