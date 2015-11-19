package obligatorio.util;
import com.mysql.jdbc.Connection;

public class Conexion implements IConexion {
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public Conexion(Connection con) {
		super();
		this.con = con;
	}
}	
	
