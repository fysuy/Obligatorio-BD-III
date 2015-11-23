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
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");

			/* cargo el driver */
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, password);

			/* creo la base de datos */
			String database = "CREATE DATABASE dym";
			PreparedStatement pstmt = (PreparedStatement) con
					.prepareStatement(database);
			pstmt.executeUpdate();
			pstmt.close();

			/* creo la tabla Dueños */
			String dueños = "CREATE TABLE Dym.Dueños "
					+ "(cedula INT NOT NULL PRIMARY KEY, "
					+ " nombre VARCHAR(45) NOT NULL, "
					+ " apellido VARCHAR(45) NOT NULL)";
			pstmt = (PreparedStatement) con.prepareStatement(dueños);
			pstmt.executeUpdate();
			pstmt.close();

			/* creo la tabla Mascotas */
			String mascotas = "CREATE TABLE Dym.Mascotas "
					+ "(apodo varchar (45) NOT NULL, "
					+ " cedulaDueño INT NOT NULL,"
					+ " raza varchar (45) NOT NULL,"
					+ " FOREIGN KEY (cedulaDueño) REFERENCES Dym.Dueños(cedula)),"
					+ " PRIMARY KEY (apodo,cedulaDueño)";

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