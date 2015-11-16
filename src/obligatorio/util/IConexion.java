package obligatorio.util;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public interface IConexion {
	public Connection getCon();
	public void closeCon();
}
