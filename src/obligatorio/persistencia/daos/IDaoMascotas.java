package obligatorio.persistencia.daos;

import java.sql.SQLException;
import java.util.List;

import obligatorio.logica.Mascota;
import obligatorio.logica.exceptions.ExceptionsDueños;
import obligatorio.logica.exceptions.ExceptionsMascotas;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.util.IConexion;

public interface IDaoMascotas {
	public boolean member (IConexion ic, String apodo) throws ExceptionsMascotas;
	
	public int insert (IConexion ic, Mascota mascota) throws ExceptionsMascotas,IOException;
	
	public void borrarMascotas (IConexion ic) throws ExceptionsMascotas;
	
	public List <VOMascota> listarMascotas(IConexion ic) throws ExceptionsMascotas;
}
