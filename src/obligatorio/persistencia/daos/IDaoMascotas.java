package obligatorio.persistencia.daos;

import java.sql.SQLException;
import java.util.List;

import obligatorio.logica.Mascota;
import obligatorio.logica.valueObjects.VOMascota;
import obligatorio.util.IConexion;

public interface IDaoMascotas {
	public boolean member (IConexion ic, String apodo) throws SQLException;
	
	public int insert (IConexion ic, Mascota mascota) throws SQLException;
	
	public void borrarMascotas (IConexion ic) throws SQLException;
	
	public List <VOMascota> listarMascotas(IConexion ic) throws SQLException;
}
