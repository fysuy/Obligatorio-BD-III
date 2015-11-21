package obligatorio.logica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import obligatorio.exceptions.DueñoException;
import obligatorio.logica.valueObjects.*;
import obligatorio.persistencia.daos.*;
import obligatorio.util.*;
import obligatorio.util.MySQL.PoolConexionesMySQL;

public class Fachada {
   private static Fachada instance = null;
   private IDaoDueños dueños;
   private IPoolConexiones pool;
   
   protected Fachada() throws IOException {
      dueños = null;
      pool = new PoolConexionesMySQL();
   }
   
   public static Fachada getInstance() throws IOException {
      if(instance == null) {
         instance = new Fachada();
      }
      
      return instance;
   }

   public void nuevoDueño(VODueño dueño) throws SQLException,DueñoException {
	   IConexion icon = pool.obtenerConexion(true);
	   int ced = dueño.getCedula();
	   String nom = dueño.getNombre();
	   String ape = dueño.getApellido();
	   
	   try {
			if (!dueños.member(icon, ced)){
				Dueño d = new Dueño(ced, nom, ape);
				dueños.insert(icon, d);
			}else{
				pool.liberarConexion (icon, true);
				throw new DueñoException("Error: ya existe el dueño con el numero de cedula ingresado.");
			}
			pool.liberarConexion (icon, true);
		} catch (Exception e) {
			pool.liberarConexion (icon, true);
		}
	}
	
   public void nuevaMascota(VOMascota pMascota) throws SQLException,DueñoException {
		String apodo = pMascota.getApodo();
		int cedulaDueño = pMascota.getCedulaDueño();
		String raza = pMascota.getRaza();
		
		IConexion icon = pool.obtenerConexion(true);
		if(dueños.member(icon, cedulaDueño))
		{
			Dueño dueño = dueños.find(icon, cedulaDueño);
			
			Mascota mascota = new Mascota(raza, apodo);
			
			try {
				dueño.addMascota(icon, mascota);
				pool.liberarConexion (icon, true);
			} catch (Exception e) {
				pool.liberarConexion (icon, true);
			}
		}else
		{
			pool.liberarConexion (icon, true);
			throw new DueñoException("Error: no existe dueño");
		}
   }
   
	public List<VODueño> listarDueños() throws SQLException {
		IConexion icon = pool.obtenerConexion(true);
		return dueños.listarDueños(icon);
	}
	
	public List<VOMascota> listarMascotas(int cedulaDueño) throws SQLException, DueñoException {
		IConexion icon = pool.obtenerConexion(true);
		
		if (dueños.member(icon, cedulaDueño)) {
			return dueños.find(icon, cedulaDueño).listarMascotas(icon);			
		} else {
			throw new DueñoException("Error: no existe dueño");
		}
	}
	
	public void borrarDueñoMascotas(int cedulaDueño) throws SQLException, DueñoException {
		IConexion icon = pool.obtenerConexion(true);
		
		if (dueños.member(icon, cedulaDueño)) {
			dueños.find(icon, cedulaDueño).borrarMascotas(icon);
			dueños.delete(icon, cedulaDueño);
		} else {
			throw new DueñoException("Error: no existe dueño");
		}
	}
}

