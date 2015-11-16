package obligatorio.util;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexion implements IConexion {
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void closeCon() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO manejar aca la exception o tirarla?
			e.printStackTrace();
		}
	}
	
	public Conexion(Connection con) {
		super();
		this.con = con;
	}	
}	
	
