package obligatorio.logica;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.daos.*;
import obligatorio.util.*;

import com.mysql.jdbc.Connection;

public class Fachada {
   private static Fachada instance = null;
   private DaoDueños dueños;
   private IPoolConexiones pool;
   
   protected Fachada() throws IOException {
      dueños = null;
      pool = new PoolConexiones();
   }
   
   public static Fachada getInstance() throws IOException {
      if(instance == null) {
         instance = new Fachada();
      }
      
      return instance;
   }

   public void nuevoDueño(VODueño dueño) throws SQLException {
	   IConexion icon = pool.obtenerConexion(true);
	   int ced = dueño.getCedula();
	   String nom = dueño.getNombre();
	   String ape = dueño.getApellido();
	   
	   try {
			if (!dueños.member(icon, ced)){
				Dueño d = new Dueño(ced, nom, ape);
				dueños.insert(icon, d);
			}
			pool.liberarConexion (icon, true);
		} catch (Exception e) {
			pool.liberarConexion (icon, true);
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
}

