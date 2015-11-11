package obligatorio.logica;

import java.io.IOException;
import java.sql.SQLException;

import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.daos.*;
import obligatorio.util.*;

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
	
   public void nuevaMascota(VOMascota pMascota) throws SQLException {
		String apodo = pMascota.getApodo();
		int cedulaDueño = pMascota.getCedulaDueño();
		String raza = pMascota.getRaza();
		
		IConexion icon = pool.obtenerConexion(true);
		
		Dueño dueño = dueños.find(icon, cedulaDueño);
		Mascota mascota = new Mascota(raza, apodo);
		
		try {
			dueño.addMascota(mascota);
			pool.liberarConexion (icon, true);
		} catch (Exception e) {
			pool.liberarConexion (icon, true);
		}
   }
}

