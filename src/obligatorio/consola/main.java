package obligatorio.consola;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class main {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load(new FileInputStream(nomArch));
			String driver = p.getProperty("persistencia.driver");
			String url = p.getProperty("persistencia.url");
			String user = p.getProperty("persistencia.user");
			String password = p.getProperty("persistencia.password");

			/* cargo el driver */
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, password);

			/* creo la base de datos */
			String database = "CREATE DATABASE dym";
			PreparedStatement pstmt = (PreparedStatement) con
					.prepareStatement(database);
			pstmt.executeUpdate();
			pstmt.close();

			/* creo la tabla Due�os */
			String due�os = "CREATE TABLE Dym.Due�os "
					+ "(cedula INT NOT NULL PRIMARY KEY, "
					+ " nombre VARCHAR(45) NOT NULL, "
					+ " apellido VARCHAR(45) NOT NULL)";
			pstmt = (PreparedStatement) con.prepareStatement(due�os);
			pstmt.executeUpdate();
			pstmt.close();

			/* creo la tabla Mascotas */
			String mascotas = "CREATE TABLE Dym.Mascotas "
					+ "(apodo varchar (45) NOT NULL, "
					+ " cedulaDue�o INT NOT NULL,"
					+ " raza varchar (45) NOT NULL,"
					+ " FOREIGN KEY (cedulaDue�o) REFERENCES Dym.Due�os(cedula),"
					+ " PRIMARY KEY (apodo,cedulaDue�o))";

			pstmt = (PreparedStatement) con.prepareStatement(mascotas);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (FileNotFoundException e) {
			/* si no encuentra el archivo de configuracion */
			e.printStackTrace();
		} catch (IOException e) {
			/* si hay problema al leer el archivo de configuracion */
			e.printStackTrace();
		} catch (SQLException e) {
			/* si hay algun problema vinculado al DBMS o la BD */
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			/* si no se puede hallar la clase correspondiente al driver */
			e.printStackTrace();
		} finally {
			try {
				/* en cualquier caso, cierro la conexion */
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}