package obligatorio.logica;

import java.sql.DriverManager;
import java.sql.SQLException;

import obligatorio.logica.valueObjects.VODueño;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.persistencia.daos.DaoDueños;

import com.mysql.jdbc.Connection;

public class Fachada {
	/*
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306";
	private String user = "root";
	private String password = "";

	public void nuevoDueño(VODueño dueño) throws SQLException {
		int ced = dueño.getCedula();
		String nom = dueño.getNombre();
		String ape = dueño.getApellido();
				
		DaoDueños dao = new DaoDueños();
		
		boolean existe = dao.member(ced);
		if (!existe){
			//crear dueño
			dao.insert(ced, nom, ape);
		}
	}
	
	public void nuevaMascota(VOMascota mascota) throws SQLException{
		String apodo = mascota.getApodo();
		int ced = mascota.getCedulaDueño();
		String raza = mascota.getRaza();
		Connection con = (Connection) DriverManager.getConnection
				(url, user, password);
		AccesoBD abd = new AccesoBD ();
		
		
		
	}


*/




}

